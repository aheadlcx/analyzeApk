package com.qiniu.android.storage;

import com.qiniu.android.http.ProgressHandler;

class i implements ProgressHandler {
    final /* synthetic */ long a;
    final /* synthetic */ f b;

    i(f fVar, long j) {
        this.b = fVar;
        this.a = j;
    }

    public void onProgress(int i, int i2) {
        double d = 0.95d;
        double h = ((double) (this.a + ((long) i))) / ((double) this.b.a);
        if (h <= 0.95d) {
            d = h;
        }
        this.b.d.d.progress(this.b.b, d);
    }
}
