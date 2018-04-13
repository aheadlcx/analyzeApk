package com.bumptech.glide.g.b;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.d.h;
import com.bumptech.glide.g.c;

public interface j<R> extends h {
    c getRequest();

    void getSize(h hVar);

    void onLoadCleared(Drawable drawable);

    void onLoadFailed(Exception exception, Drawable drawable);

    void onLoadStarted(Drawable drawable);

    void onResourceReady(R r, com.bumptech.glide.g.a.c<? super R> cVar);

    void setRequest(c cVar);
}
