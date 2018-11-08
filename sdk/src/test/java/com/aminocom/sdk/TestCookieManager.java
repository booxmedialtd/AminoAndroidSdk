package com.aminocom.sdk;

public class TestCookieManager implements CookieManager {
    private String cookie = "";

    @Override
    public boolean isCookieExists() {
        return cookie != null && !cookie.isEmpty();
    }

    @Override
    public void setCookie(String value) {
        cookie += value.substring(0, value.indexOf(";")) + "; ";
    }

    @Override
    public String getCookie() {
        if (cookie.length() > 2) {
            return cookie.substring(0, cookie.length() - 2);
        } else {
            return cookie;
        }
    }
}