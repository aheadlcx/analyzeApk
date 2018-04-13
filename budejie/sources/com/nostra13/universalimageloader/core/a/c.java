package com.nostra13.universalimageloader.core.a;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

public class c {
    private final String a;
    private final String b;
    private final String c;
    private final com.nostra13.universalimageloader.core.assist.c d;
    private final ImageScaleType e;
    private final ViewScaleType f;
    private final ImageDownloader g;
    private final Object h;
    private final boolean i;
    private final Options j = new Options();

    public c(String str, String str2, String str3, com.nostra13.universalimageloader.core.assist.c cVar, ViewScaleType viewScaleType, ImageDownloader imageDownloader, com.nostra13.universalimageloader.core.c cVar2) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = cVar;
        this.e = cVar2.j();
        this.f = viewScaleType;
        this.g = imageDownloader;
        this.h = cVar2.n();
        this.i = cVar2.m();
        a(cVar2.k(), this.j);
    }

    private void a(Options options, Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            b(options, options2);
        }
        if (VERSION.SDK_INT >= 11) {
            c(options, options2);
        }
    }

    @TargetApi(10)
    private void b(Options options, Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    private void c(Options options, Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public com.nostra13.universalimageloader.core.assist.c c() {
        return this.d;
    }

    public ImageScaleType d() {
        return this.e;
    }

    public ViewScaleType e() {
        return this.f;
    }

    public ImageDownloader f() {
        return this.g;
    }

    public Object g() {
        return this.h;
    }

    public boolean h() {
        return this.i;
    }

    public Options i() {
        return this.j;
    }
}
