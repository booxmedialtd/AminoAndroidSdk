package com.aminocom.sdk;

public class TestCookieManager implements CookieManager {
    private String cookie;

    @Override
    public boolean isCookieExists() {
        return cookie != null && !cookie.isEmpty();
    }

    @Override
    public void setCookie(String value) {
        cookie = value;
    }

    @Override
    public String getCookie() {
        return cookie;
    }
}