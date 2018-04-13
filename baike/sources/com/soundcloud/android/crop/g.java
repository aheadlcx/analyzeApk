package com.soundcloud.android.crop;

import android.graphics.Bitmap;

class g implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ CropImageActivity b;

    g(CropImageActivity cropImageActivity, Bitmap bitmap) {
        this.b = cropImageActivity;
        this.a = bitmap;
    }

    public void run() {
        this.b.l.clear();
        this.a.recycle();
    }
}
