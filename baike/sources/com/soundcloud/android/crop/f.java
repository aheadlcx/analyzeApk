package com.soundcloud.android.crop;

import android.graphics.Bitmap;

class f implements Runnable {
    final /* synthetic */ Bitmap a;
    final /* synthetic */ CropImageActivity b;

    f(CropImageActivity cropImageActivity, Bitmap bitmap) {
        this.b = cropImageActivity;
        this.a = bitmap;
    }

    public void run() {
        this.b.b(this.a);
    }
}
