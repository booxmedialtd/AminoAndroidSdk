package com.aminocom.sdk.util;

import java.util.UUID;

public class AccountUtil {
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}