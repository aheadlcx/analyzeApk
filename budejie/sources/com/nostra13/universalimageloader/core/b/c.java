package com.nostra13.universalimageloader.core.b;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.c.a;
import pl.droidsonroids.gif.GifDrawable;

public class c implements a {
    private final int a;
    private final boolean b;
    private final boolean c;
    private final boolean d;

    public void a(Bitmap bitmap, a aVar, LoadedFrom loadedFrom) {
        aVar.a(bitmap);
        if ((this.b && loadedFrom == LoadedFrom.NETWORK) || ((this.c && loadedFrom == LoadedFrom.DISC_CACHE) || (this.d && loadedFrom == LoadedFrom.MEMORY_CACHE))) {
            a(aVar.d(), this.a);
        }
    }

    public static void a(View view, int i) {
        if (view != null) {
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) i);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            view.startAnimation(alphaAnimation);
        }
    }

    public void a(GifDrawable gifDrawable, a aVar, LoadedFrom loadedFrom) {
        aVar.a(gifDrawable);
    }
}
