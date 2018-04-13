package com.davemorrissey.labs.subscaleview;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class a {
    private final Uri a;
    private final Bitmap b;
    private final Integer c;
    private boolean d;
    private int e;
    private int f;
    private Rect g;
    private boolean h;

    private a(Uri uri) {
        String uri2 = uri.toString();
        if (uri2.startsWith("file:///") && !new File(uri2.substring("file:///".length() - 1)).exists()) {
            try {
                uri = Uri.parse(URLDecoder.decode(uri2, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
        }
        this.b = null;
        this.a = uri;
        this.c = null;
        this.d = true;
    }

    private a(int i) {
        this.b = null;
        this.a = null;
        this.c = Integer.valueOf(i);
        this.d = true;
    }

    public static a a(int i) {
        return new a(i);
    }

    public static a a(String str) {
        if (str != null) {
            return b("file:///android_asset/" + str);
        }
        throw new NullPointerException("Asset name must not be null");
    }

    public static a b(String str) {
        if (str == null) {
            throw new NullPointerException("Uri must not be null");
        }
        if (!str.contains("://")) {
            if (str.startsWith("/")) {
                str = str.substring(1);
            }
            str = "file:///" + str;
        }
        return new a(Uri.parse(str));
    }

    public static a a(Uri uri) {
        if (uri != null) {
            return new a(uri);
        }
        throw new NullPointerException("Uri must not be null");
    }

    public a a() {
        return a(true);
    }

    public a a(boolean z) {
        this.d = z;
        return this;
    }

    protected final Uri b() {
        return this.a;
    }

    protected final Bitmap c() {
        return this.b;
    }

    protected final Integer d() {
        return this.c;
    }

    protected final boolean e() {
        return this.d;
    }

    protected final int f() {
        return this.e;
    }

    protected final int g() {
        return this.f;
    }

    protected final Rect h() {
        return this.g;
    }

    protected final boolean i() {
        return this.h;
    }
}
