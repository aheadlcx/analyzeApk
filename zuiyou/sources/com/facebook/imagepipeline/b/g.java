package com.facebook.imagepipeline.b;

import android.graphics.Bitmap;
import com.facebook.common.references.c;

public class g implements c<Bitmap> {
    private static g a;

    public /* synthetic */ void release(Object obj) {
        a((Bitmap) obj);
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private g() {
    }

    public void a(Bitmap bitmap) {
        bitmap.recycle();
    }
}
