package com.aminocom.sdk;

import com.aminocom.sdk.provider.CategoryProvider;
import com.aminocom.sdk.provider.ChannelProvider;
import com.aminocom.sdk.provider.Providers;
import com.aminocom.sdk.provider.UserProvider;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SdkBuilder implements Providers {

    private Providers providers;

    public SdkBuilder(String baseUrl, String service, String servicePassword, Providers providers, CookieManager cookieManager) {

        this.providers = providers;

        final HttpLoggingInterceptor.Logger logger = message -> {
            if (!message.isEmpty()) {
                System.out.println("HttpInterceptor: " + message);
            }
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setDateFormat(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).toPattern())
                .create();

        //Credentials serviceCredentials = new Credentials(service, "qn05BON1hXGCUsw");
        Credentials serviceCredentials = new Credentials(service, servicePassword);

        CustomDigestAuthenticator authenticator = new CustomDigestAuthenticator(serviceCredentials, null);

        final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .addInterceptor(new RetrofitInterceptor(authCache, cookieManager))
                .addNetworkInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://nebtest1.auto.neb.amo.booxmedia.xyz/")
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ServerApi api = retrofit.create(ServerApi.class);

        LocalRepository localRepository = new CacheRepository();
    }

    @Override
    public UserProvider user() {
        return providers.user();
    }

    @Override
    public ChannelProvider channels() {
        return providers.channels();
    }

    @Override
    public CategoryProvider categories() {
        return providers.categories();
    }
}