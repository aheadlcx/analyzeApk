package com.linkedin.urls;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class a {
    private static final Map<String, Integer> a = new HashMap();
    private b b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g = 0;
    private String h;
    private String i;
    private String j;
    private String k;

    static {
        a.put("http", Integer.valueOf(80));
        a.put("https", Integer.valueOf(443));
        a.put("ftp", Integer.valueOf(21));
    }

    protected a(b bVar) {
        this.b = bVar;
        this.k = bVar.b();
    }

    public String toString() {
        return a();
    }

    public String a() {
        return b() + a(j());
    }

    public static String a(String str) {
        return str == null ? "" : str;
    }

    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(c())) {
            stringBuilder.append(c());
            stringBuilder.append(":");
        }
        stringBuilder.append("//");
        if (!TextUtils.isEmpty(d())) {
            stringBuilder.append(d());
            if (!TextUtils.isEmpty(e())) {
                stringBuilder.append(":");
                stringBuilder.append(e());
            }
            stringBuilder.append("@");
        }
        stringBuilder.append(f());
        if (g() > 0 && g() != ((Integer) a.get(c())).intValue()) {
            stringBuilder.append(":");
            stringBuilder.append(g());
        }
        stringBuilder.append(h());
        stringBuilder.append(i());
        return stringBuilder.toString();
    }

    public String c() {
        if (this.c == null) {
            if (a(UrlPart.SCHEME)) {
                this.c = c(UrlPart.SCHEME);
                int indexOf = this.c.indexOf(":");
                if (indexOf != -1) {
                    this.c = this.c.substring(0, indexOf);
                }
            } else if (!this.k.startsWith("//")) {
                this.c = "http";
            }
        }
        return a(this.c);
    }

    public String d() {
        if (this.d == null) {
            k();
        }
        return a(this.d);
    }

    public String e() {
        if (this.e == null) {
            k();
        }
        return a(this.e);
    }

    public String f() {
        if (this.f == null) {
            this.f = c(UrlPart.HOST);
            if (a(UrlPart.PORT)) {
                this.f = this.f.substring(0, this.f.length() - 1);
            }
        }
        return this.f;
    }

    public int g() {
        if (this.g == 0) {
            String c = c(UrlPart.PORT);
            if (c != null && !c.isEmpty()) {
                try {
                    this.g = Integer.parseInt(c);
                } catch (NumberFormatException e) {
                    this.g = -1;
                }
            } else if (a.containsKey(c())) {
                this.g = ((Integer) a.get(c())).intValue();
            } else {
                this.g = -1;
            }
        }
        return this.g;
    }

    public String h() {
        if (this.h == null) {
            this.h = a(UrlPart.PATH) ? c(UrlPart.PATH) : "/";
        }
        return this.h;
    }

    public String i() {
        if (this.i == null) {
            this.i = c(UrlPart.QUERY);
        }
        return a(this.i);
    }

    public String j() {
        if (this.j == null) {
            this.j = c(UrlPart.FRAGMENT);
        }
        return a(this.j);
    }

    private void k() {
        if (a(UrlPart.USERNAME_PASSWORD)) {
            String c = c(UrlPart.USERNAME_PASSWORD);
            String[] split = c.substring(0, c.length() - 1).split(":");
            if (split.length == 1) {
                this.d = split[0];
            } else if (split.length == 2) {
                this.d = split[0];
                this.e = split[1];
            }
        }
    }

    private boolean a(UrlPart urlPart) {
        return urlPart != null && this.b.a(urlPart) >= 0;
    }

    private UrlPart b(UrlPart urlPart) {
        UrlPart nextPart = urlPart.getNextPart();
        if (a(nextPart)) {
            return nextPart;
        }
        if (nextPart == null) {
            return null;
        }
        return b(nextPart);
    }

    private String c(UrlPart urlPart) {
        if (!a(urlPart)) {
            return null;
        }
        UrlPart b = b(urlPart);
        if (b == null) {
            return this.k.substring(this.b.a(urlPart));
        }
        return this.k.substring(this.b.a(urlPart), this.b.a(b));
    }
}
