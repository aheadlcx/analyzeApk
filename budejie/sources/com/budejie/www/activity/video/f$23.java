package com.budejie.www.activity.video;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView.ScaleType;
import com.androidex.widget.asyncimage.AsyncImageView.ImageListener;
import com.nostra13.universalimageloader.core.assist.FailReason;
import pl.droidsonroids.gif.GifDrawable;

class f$23 implements ImageListener {
    final /* synthetic */ f a;

    f$23(f fVar) {
        this.a = fVar;
    }

    public void onProgressUpdate(String str, View view, int i) {
    }

    public void onLoadingStarted(String str, View view) {
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
        if (!f.n(this.a)) {
            if (f.a(this.a) == 0) {
                f.f(this.a).setScaleType(ScaleType.FIT_XY);
            }
            f.f(this.a).setVisibility(0);
        }
    }
}
