package com.lt.a.b;

import android.util.Base64;

public class a {
    public static String a(String str) {
        return new String(Base64.encode(str.getBytes(), 2));
    }
}
