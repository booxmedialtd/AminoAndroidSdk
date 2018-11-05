package com.aminocom.sdk.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Cookie;
import okhttp3.Response;

public class CookieParser {

    private CookieParser() {
    }

    public static List<String> parseCookies(Response response) {
        List<String> result = new ArrayList<>();

        result.add(parseSsidCookie(response.request().header("Cookie")));

        String username = parseUsername(response.request().header("Authorization"));

        List<Cookie> cookies = Cookie.parseAll(response.request().url(), response.headers());

        for (Cookie cookie : cookies) {
            String name = cookie.name();

            if (name.equals("usid")) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh:mm:ss z", Locale.US);
                String expires = sdf.format(cookie.expiresAt());

                result.add("usid=" + cookie.value() + "; Path=" + cookie.path() + "; Expires=" + expires);
                result.add("username=" + username + "; Path=" + cookie.path() + "; Expires=" + expires);
            }
        }

        return result;
    }

    private static String parseSsidCookie(String cookie) {
        String result = "";

        if (cookie != null) {
            String[] cookiePairs = cookie.split(";");

            for (String pair : cookiePairs) {
                String[] parameters = pair.split("=");

                if (parameters.length > 1 && parameters[0].contains("ssid")) {
                    result = "ssid=" + parameters[1] + "; Path=/";
                }
            }
        }

        return result;
    }

    private static String parseUsername(String authString) {
        String username = "";

        if (authString != null) {
            String[] authPairs = authString.split(",");

            for (String pair : authPairs) {
                String[] parameters = pair.split("=");

                if (parameters.length > 1 && parameters[0].contains("username")) {
                    username = parameters[1].substring(1, parameters[1].length() - 1); // username without brackets
                }
            }
        }

        return username;
    }
}