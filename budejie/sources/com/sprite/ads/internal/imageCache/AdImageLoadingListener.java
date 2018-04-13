package com.sprite.ads.internal.imageCache;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.d.a;
import pl.droidsonroids.gif.GifDrawable;

public class AdImageLoadingListener implements a {
    public void onLoadingCancelled(String str, View view) {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
    }

    public void onLoadingStarted(String str, View view) {
    }
}
