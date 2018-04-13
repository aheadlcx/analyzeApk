package com.airbnb.lottie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable.Callback;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Assert;

class ab {
    private final Context a;
    private String b;
    private final Map<String, aa> c;
    private final Map<String, Bitmap> d = new HashMap();

    ab(Callback callback, String str, Map<String, aa> map) {
        Assert.assertNotNull(callback);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalStateException("You must specify an image assets folder by calling setImageAssetsFolder on LottieAnimationView or LottieDrawable.");
        }
        this.b = str;
        if (this.b.charAt(this.b.length() - 1) != '/') {
            this.b += '/';
        }
        if (callback instanceof View) {
            this.a = ((View) callback).getContext();
            this.c = map;
            return;
        }
        Log.w("LOTTIE", "LottieDrawable must be inside of a view for images to work.");
        this.c = new HashMap();
        this.a = null;
    }

    Bitmap a(String str) {
        Bitmap bitmap = (Bitmap) this.d.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        aa aaVar = (aa) this.c.get(str);
        if (aaVar == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(this.b)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            InputStream open = this.a.getAssets().open(this.b + aaVar.b());
            Options options = new Options();
            options.inScaled = true;
            options.inDensity = 160;
            bitmap = BitmapFactory.decodeStream(open, null, options);
            this.d.put(str, bitmap);
            return bitmap;
        } catch (Throwable e) {
            Log.w("LOTTIE", "Unable to open asset.", e);
            return null;
        }
    }

    void a() {
        Iterator it = this.d.entrySet().iterator();
        while (it.hasNext()) {
            ((Bitmap) ((Entry) it.next()).getValue()).recycle();
            it.remove();
        }
    }

    boolean a(Context context) {
        return (context == null && this.a == null) || (context != null && this.a.equals(context));
    }
}
