package com.aminocom.sdk;

import android.content.Context;

import com.aminocom.sdk.provider.local.DbProvider;
import com.aminocom.sdk.provider.local.LocalProvider;
import com.aminocom.sdk.provider.network.CategoryProvider;
import com.aminocom.sdk.provider.network.ChannelProvider;
import com.aminocom.sdk.provider.network.EpgProvider;
import com.aminocom.sdk.provider.network.ProviderFactory;
import com.aminocom.sdk.provider.network.ProviderType;
import com.aminocom.sdk.provider.network.Providers;
import com.aminocom.sdk.provider.network.RecordingProvider;
import com.aminocom.sdk.provider.network.StreamProvider;
import com.aminocom.sdk.provider.network.UserProvider;
import com.aminocom.sdk.settings.Settings;
import com.aminocom.sdk.settings.DefaultSettings;
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

public class Sdk implements Providers {

    private Providers providers;


    /**
     * Simplified constructor which creates instance of the SDK with default CookieManager, LocalProvider and Settings
     *
     * @param context - Android context, better to use application context
     * @param baseUrl - base url to the service
     * @param service - parameter for service authentication
     * @param servicePassword - password for service authentication
     * @param type - Type of data provider. For example it can be ProviderType.AMINO
     */
    public Sdk(Context context, String baseUrl, String service, String servicePassword, ProviderType type) {
        new Sdk(
                baseUrl,
                service,
                servicePassword,
                type,
                new AndroidCookieManager(),
                new DbProvider(context),
                new DefaultSettings(context, new DefaultCacheTtlManager())
        );
    }


    /**
     * Base constructor which creates instance of the SDK for fetching of data
     *
     * @param baseUrl - base url to the service
     * @param service - parameter for service authentication
     * @param servicePassword - password for service authentication
     * @param type - Type of data provider. For example it can be ProviderType.AMINO
     * @param cookieManager - manager to store cookies
     * @param dbRepository - local provider for caching of fetched data
     * @param settings - user settings
     */
    public Sdk(String baseUrl, String service, String servicePassword, ProviderType type, CookieManager cookieManager, LocalProvider dbRepository, Settings settings) {

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
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ServerApi api = retrofit.create(ServerApi.class);

        this.providers = ProviderFactory.getProvider(type, api, authenticator, service, cookieManager, dbRepository, settings);
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

    @Override
    public EpgProvider epg() {
        return providers.epg();
    }

    @Override
    public RecordingProvider recording() {
        return providers.recording();
    }

    @Override
    public StreamProvider stream() {
        return providers.stream();
    }
}