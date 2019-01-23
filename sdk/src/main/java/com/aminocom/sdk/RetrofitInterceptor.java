package com.aminocom.sdk;

import com.aminocom.sdk.util.CookieUtil;
import com.burgstaller.okhttp.CacheKeyProvider;
import com.burgstaller.okhttp.DefaultCacheKeyProvider;
import com.burgstaller.okhttp.digest.CachingAuthenticator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.platform.Platform;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_PROXY_AUTH;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class RetrofitInterceptor implements Interceptor {

    private static final String USER_AGENT = "BM-NATIVE-APP";

    private final Map<String, CachingAuthenticator> authCache;
    private final CacheKeyProvider cacheKeyProvider;
    private CookieManager cookieManager;

    public RetrofitInterceptor(Map<String, CachingAuthenticator> authCache, CookieManager cookieManager) {
        this.authCache = authCache;
        this.cookieManager = cookieManager;
        this.cacheKeyProvider = new DefaultCacheKeyProvider();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        Request oldRequest = chain.request();

        builder.addHeader("User-Agent", USER_AGENT);

        String cookie = cookieManager.getCookie();

        if (cookie != null && !cookie.isEmpty()) {
            builder.addHeader("Cookie", cookie);
        }

        builder.url(oldRequest.url());
        builder.method(oldRequest.method(), oldRequest.body());
        builder.build();

        final Request request = builder.build();
        //Request request = chain.request();
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

        if (response != null && response.request() != null
                && response.request().url().toString().contains("/login") && responseCode == HTTP_OK) {

            List<String> loginCookies = CookieUtil.parseCookies(response);

            if (!loginCookies.isEmpty()) {
                for (String cookieString : loginCookies) {
                    cookieManager.setCookie(cookieString);
                }
            }
        }

        if (authenticator != null && (responseCode == HTTP_UNAUTHORIZED || responseCode == HTTP_PROXY_AUTH)) {
            // Remove cached authenticator and resend request
            if (authCache.remove(key) != null) {
                response.body().close();
                Platform.get().log(Platform.INFO, "Cached authentication expired. Sending a new request.", null);
                // Force sending a new request without "Authorization" header
                response = chain.proceed(request);
            }
        }
        return response;
    }
}