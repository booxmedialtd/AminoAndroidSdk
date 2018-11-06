package com.aminocom.sdk;

import android.net.Uri;

public class AndroidCookieManager implements CookieManager {

    private final String cookieUrl;

    public AndroidCookieManager() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(BuildConfig.BASE_SCHEME);
        builder.authority(BuildConfig.BASE_DOMAIN);
        cookieUrl = builder.build().toString();
    }

    @Override
    public boolean isCookieExists() {
        String cookie = getCookie();

        return cookie != null && !cookie.isEmpty();
    }

    @Override
    public void setCookie(String value) {
        android.webkit.CookieManager.getInstance().setCookie(cookieUrl, value);
    }

    @Override
    public String getCookie() {
        return android.webkit.CookieManager.getInstance().getCookie(cookieUrl);
    }
}
