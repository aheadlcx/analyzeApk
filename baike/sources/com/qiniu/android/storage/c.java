package com.qiniu.android.storage;

import com.qiniu.android.http.ProgressHandler;

final class c implements ProgressHandler {
    final /* synthetic */ UploadOptions a;
    final /* synthetic */ String b;

    c(UploadOptions uploadOptions, String str) {
        this.a = uploadOptions;
        this.b = str;
    }

    public void onProgress(int i, int i2) {
        double d = 0.95d;
        double d2 = ((double) i) / ((double) i2);
        if (d2 <= 0.95d) {
            d = d2;
        }
        this.a.d.progress(this.b, d);
    }
}
