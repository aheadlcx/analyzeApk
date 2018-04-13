package com.nostra13.universalimageloader.core.b;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.a;
import pl.droidsonroids.gif.GifDrawable;

public final class d implements a {
    public void a(Bitmap bitmap, a aVar, LoadedFrom loadedFrom) {
        aVar.a(bitmap);
    }

    public void a(GifDrawable gifDrawable, a aVar, LoadedFrom loadedFrom) {
        com.nostra13.universalimageloader.core.d.a().a(gifDrawable);
        aVar.a((Drawable) gifDrawable);
    }
}
