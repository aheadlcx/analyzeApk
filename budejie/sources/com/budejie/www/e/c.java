package com.budejie.www.e;

import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.c.b;

public class c extends b {
    protected final com.nostra13.universalimageloader.core.assist.c a;

    public c(ImageView imageView, com.nostra13.universalimageloader.core.assist.c cVar) {
        super(imageView, false);
        this.a = cVar;
    }

    public int a() {
        return this.a.a();
    }

    public int b() {
        return this.a.b();
    }

    public ViewScaleType c() {
        return ViewScaleType.CROP;
    }
}
