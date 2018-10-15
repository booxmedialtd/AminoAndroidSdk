package com.aminocom.sdk;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Provider {

    private static final String TAG = Provider.class.getSimpleName();

    private ServerApi api;

    public Provider() {
        final HttpLoggingInterceptor.Logger logger = message -> {
            if (!message.isEmpty()) {
                Log.d(TAG, "HttpInterceptor: " + message);
            }
        };

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setDateFormat(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH).toPattern())
                .create();

        OkHttpClient client = new OkHttpClient().newBuilder()
                //.authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                //.addInterceptor(new RetrofitInterceptor(authCache))
                .addNetworkInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(ServerApi.class);
    }
}