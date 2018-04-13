package com.budejie.www.e;

import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.b.a;
import com.nostra13.universalimageloader.core.c;

public class d {
    public static c a(int i, int i2) {
        return a(i, i2, new com.nostra13.universalimageloader.core.b.d());
    }

    public static c a(a aVar) {
        return new c.a().a(true).b(true).c(true).a(Config.RGB_565).a(aVar).a();
    }

    public static c a(int i, int i2, a aVar) {
        return new c.a().a(i).b(i2).c(i2).a(true).b(true).c(true).a(Config.RGB_565).a(aVar).a();
    }

    public static c a(a aVar, Drawable drawable) {
        if (aVar == null) {
            aVar = new com.nostra13.universalimageloader.core.b.d();
        }
        return new c.a().a(drawable).b(drawable).a(true).b(true).c(true).a(Config.RGB_565).a(aVar).a();
    }

    public static c a() {
        return new c.a().a(false).b(true).a(Config.RGB_565).a();
    }
}
