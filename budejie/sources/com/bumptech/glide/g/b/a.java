package com.bumptech.glide.g.b;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.g.c;

public abstract class a<Z> implements j<Z> {
    private c request;

    public void setRequest(c cVar) {
        this.request = cVar;
    }

    public c getRequest() {
        return this.request;
    }

    public void onLoadCleared(Drawable drawable) {
    }

    public void onLoadStarted(Drawable drawable) {
    }

    public void onLoadFailed(Exception exception, Drawable drawable) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }
}
