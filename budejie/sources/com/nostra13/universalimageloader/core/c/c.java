package com.nostra13.universalimageloader.core.c;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;

public class c implements a {
    protected final String a;
    protected final com.nostra13.universalimageloader.core.assist.c b;
    protected final ViewScaleType c;

    public c(String str, com.nostra13.universalimageloader.core.assist.c cVar, ViewScaleType viewScaleType) {
        if (cVar == null) {
            throw new IllegalArgumentException("imageSize must not be null");
        } else if (viewScaleType == null) {
            throw new IllegalArgumentException("scaleType must not be null");
        } else {
            this.a = str;
            this.b = cVar;
            this.c = viewScaleType;
        }
    }

    public int a() {
        return this.b.a();
    }

    public int b() {
        return this.b.b();
    }

    public ViewScaleType c() {
        return this.c;
    }

    public View d() {
        return null;
    }

    public boolean e() {
        return false;
    }

    public int f() {
        return TextUtils.isEmpty(this.a) ? super.hashCode() : this.a.hashCode();
    }

    public boolean a(Drawable drawable) {
        return true;
    }

    public boolean a(Bitmap bitmap) {
        return true;
    }
}
