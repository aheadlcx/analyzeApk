package com.crashlytics.android.internal;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class Z {
    private static String a = ("Crashlytics Android SDK/" + v.a().getVersion());
    private static final Pattern b = Pattern.compile("http(s?)://[^\\/]+", 2);
    private final String c;
    private final C0011av d;
    private final C0012ax e;
    private final String f;

    public Z(String str, String str2, C0011av c0011av, C0012ax c0012ax) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (c0011av == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.f = str;
            if (!C0003ab.e(this.f)) {
                str2 = b.matcher(str2).replaceFirst(this.f);
            }
            this.c = str2;
            this.d = c0011av;
            this.e = c0012ax;
        }
    }

    protected final String a() {
        return this.c;
    }

    protected final C0013ay b() {
        return a(Collections.emptyMap());
    }

    protected final C0013ay a(Map<String, String> map) {
        return this.d.a(this.e, this.c, map).a(false).a(10000).a("User-Agent", a).a("X-CRASHLYTICS-DEVELOPER-TOKEN", "bca6990fc3c15a8105800c0673517a4b579634a1");
    }
}
