package com.aminocom.sdk;

public interface CookieManager {
    boolean isCookieExists();

    void setCookie(String value);

    String getCookie();
}