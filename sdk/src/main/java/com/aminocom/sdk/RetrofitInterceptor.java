package com.aminocom.sdk;

import com.burgstaller.okhttp.CacheKeyProvider;
import com.burgstaller.okhttp.DefaultCacheKeyProvider;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.burgstaller.okhttp.digest.DigestAuthenticator;

import java.io.IOException;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class RetrofitInterceptor implements Interceptor {

    private static final String USER_AGENT = "BM-NATIVE-APP";

    private final Map<String, CachingAuthenticator> authCache;
    private final CacheKeyProvider cacheKeyProvider;
    final Credentials userCredentials;
    final DigestAuthenticator userAuthenticator;

    public RetrofitInterceptor(DigestAuthenticator userAuthenticator, Credentials userCredentials, Map<String, CachingAuthenticator> authCache) {
        this.authCache = authCache;
        this.cacheKeyProvider = new DefaultCacheKeyProvider();
        this.userCredentials = userCredentials;
        this.userAuthenticator = userAuthenticator;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        /*Request.Builder builder = chain.request().newBuilder();

        Request oldRequest = chain.request();

        builder.addHeader("User-Agent", USER_AGENT);

        String cookie = AccountUtil.getCookie();

        if (cookie != null && !cookie.isEmpty()) {
            builder.addHeader("Cookie", cookie);
        }

        builder.url(oldRequest.url());
        builder.method(oldRequest.method(), oldRequest.body());
        builder.build();

        final Request request = builder.build();*/
        Request request = chain.request();
        final String key = cacheKeyProvider.getCachingKey(request);
        CachingAuthenticator authenticator = authCache.get(key);
        Request authRequest = null;
        Connection connection = chain.connection();
        Route route = connection != null ? connection.route() : null;
        if (authenticator != null) {
            authRequest = authenticator.authenticateWithState(route, request);
        }
        if (authRequest == null) {
            authRequest = request;
        }
        Response response = chain.proceed(authRequest);

        // Cached response was used, but it produced unauthorized response (cache expired).
        int responseCode = response != null ? response.code() : 0;
        //if (authenticator != null && (responseCode == HTTP_UNAUTHORIZED || responseCode == HTTP_PROXY_AUTH)) {
        // Remove cached authenticator and resend request
        /*if (authCache.remove(key) != null) {

            if (response.body() != null) {
                response.body().close();
            }

            if (response.headers() != null && response.headers().toString().contains("realm=\"user\"")) {
                //userCredentials.setUserName("aleksei@test.com");
                //userCredentials.setPassword("1234");

                //CachingAuthenticator localAuthenticator = new DigestAuthenticator(new Credentials("aleksei@test.com", "1234"));
                *//*Request.Builder builder = chain.request().newBuilder();

                Request oldRequest = chain.request();

                String headers = response.headers().toString();

                String nonceString = headers.substring(headers.indexOf("nonce="), headers.indexOf("opaque=") - 1);
                String nonceValue = nonceString.substring(7, nonceString.length() - 1);

                String opaqueString = headers.substring(headers.indexOf("opaque="));
                String opaqueValue = opaqueString.substring(8, opaqueString.length() - 2);

                String auth = "Digest username=\"aleksei@test.com\", realm=\"user\", nonce=\"" + nonceValue + "\", uri=\"/api/user/aleksei@test.com/login\", qop=auth, algorithm=MD5, opaque=\"" + opaqueValue + "\"";

                builder.addHeader("Authorization", auth);

                builder.url(oldRequest.url());
                builder.method(oldRequest.method(), oldRequest.body());

                request = builder.build();*//*
            }

            Platform.get().log(Platform.INFO, "Cached authentication expired. Sending a new request.", null);
            // Force sending a new request without "Authorization" header
            response = chain.proceed(request);
        }*/
        //}
        return response;
    }
}