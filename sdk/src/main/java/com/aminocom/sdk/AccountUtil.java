package com.aminocom.sdk;

import android.net.Uri;
import android.webkit.CookieManager;

import java.util.UUID;

class AccountUtil {
    public static String getCookie() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(BuildConfig.BASE_SCHEME);
        builder.authority(BuildConfig.BASE_DOMAIN);
        String cookieUrl = builder.build().toString();

        return CookieManager.getInstance().getCookie(cookieUrl);
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}