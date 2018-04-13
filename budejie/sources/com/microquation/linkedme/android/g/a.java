package com.microquation.linkedme.android.g;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class a {
    private static final b a;

    private static class b {
        private b() {
        }

        public int a(Context context, String str, String str2) {
            return 1;
        }

        public String a(String str) {
            return null;
        }
    }

    private static class a extends b {
        private a() {
            super();
        }

        public int a(Context context, String str, String str2) {
            return b.a(context, str, str2);
        }

        public String a(String str) {
            return b.a(str);
        }
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static int a(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        return a.a(context, str, str2);
    }

    public static String a(@NonNull String str) {
        return a.a(str);
    }
}
