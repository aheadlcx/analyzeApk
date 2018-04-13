package com.qiniu.android.storage;

class t implements UpCancellationSignal {
    final /* synthetic */ UploadOptions a;

    t(UploadOptions uploadOptions) {
        this.a = uploadOptions;
    }

    public boolean isCancelled() {
        return false;
    }
}
