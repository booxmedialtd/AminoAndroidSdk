package com.aminocom.sdk.util;

import android.net.Uri;
import android.webkit.CookieManager;

import com.aminocom.sdk.BuildConfig;

import java.util.UUID;

public class AccountUtil {
    public static String getCookie() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(BuildConfig.BASE_SCHEME);
        builder.authority(BuildConfig.BASE_DOMAIN);
        String cookieUrl = builder.build().toString();

        return CookieManager.getInstance().getCookie(cookieUrl);
    }

    public static void setCookie(String cookie) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(BuildConfig.BASE_SCHEME);
        builder.authority(BuildConfig.BASE_DOMAIN);
        String cookieUrl = builder.build().toString();

        CookieManager.getInstance().setCookie(cookieUrl, cookie);
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}