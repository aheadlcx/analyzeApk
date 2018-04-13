package com.qiniu.android.storage;

import android.util.Log;

class s implements UpProgressHandler {
    final /* synthetic */ UploadOptions a;

    s(UploadOptions uploadOptions) {
        this.a = uploadOptions;
    }

    public void progress(String str, double d) {
        Log.d("qiniu up progress", "" + d);
    }
}
