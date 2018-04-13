package com.linkedin.urls.detection;

import android.text.TextUtils;
import java.util.ArrayList;

public class a {
    public static boolean a(char c) {
        return (c >= '0' && c <= '9') || ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }

    public static boolean b(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean c(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean d(char c) {
        return b(c) || c(c);
    }

    public static boolean e(char c) {
        return d(c) || c == '-' || c == '.' || c == '_' || c == '~';
    }

    public static boolean f(char c) {
        return c == '.' || c == '。' || c == '．' || c == '｡';
    }

    public static boolean g(char c) {
        return c == '\n' || c == '\t' || c == '\r' || c == ' ';
    }

    public static String[] a(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            return new String[]{""};
        }
        b bVar = new b(str);
        while (!bVar.b()) {
            char a = bVar.a();
            if (f(a)) {
                arrayList.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            } else if (a == '%' && bVar.c(2) && bVar.a(2).equalsIgnoreCase("2e")) {
                bVar.a();
                bVar.a();
                arrayList.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            } else {
                stringBuilder.append(a);
            }
        }
        arrayList.add(stringBuilder.toString());
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
