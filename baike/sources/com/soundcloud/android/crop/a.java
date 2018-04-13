package com.soundcloud.android.crop;

import android.graphics.Bitmap;
import com.soundcloud.android.crop.ImageViewTouchBase.Recycler;

class a implements Recycler {
    final /* synthetic */ CropImageActivity a;

    a(CropImageActivity cropImageActivity) {
        this.a = cropImageActivity;
    }

    public void recycle(Bitmap bitmap) {
        bitmap.recycle();
        System.gc();
    }
}
