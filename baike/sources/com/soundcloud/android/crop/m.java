package com.soundcloud.android.crop;

class m implements Runnable {
    final /* synthetic */ float a;
    final /* synthetic */ long b;
    final /* synthetic */ float c;
    final /* synthetic */ float d;
    final /* synthetic */ float e;
    final /* synthetic */ float f;
    final /* synthetic */ ImageViewTouchBase g;

    m(ImageViewTouchBase imageViewTouchBase, float f, long j, float f2, float f3, float f4, float f5) {
        this.g = imageViewTouchBase;
        this.a = f;
        this.b = j;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
    }

    public void run() {
        float min = Math.min(this.a, (float) (System.currentTimeMillis() - this.b));
        this.g.a(this.c + (this.d * min), this.e, this.f);
        if (min < this.a) {
            this.g.j.post(this);
        }
    }
}
