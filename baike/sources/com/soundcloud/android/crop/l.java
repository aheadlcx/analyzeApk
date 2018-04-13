package com.soundcloud.android.crop;

class l implements Runnable {
    final /* synthetic */ o a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ImageViewTouchBase c;

    l(ImageViewTouchBase imageViewTouchBase, o oVar, boolean z) {
        this.c = imageViewTouchBase;
        this.a = oVar;
        this.b = z;
    }

    public void run() {
        this.c.setImageRotateBitmapResetBase(this.a, this.b);
    }
}
