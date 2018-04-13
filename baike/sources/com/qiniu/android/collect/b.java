package com.qiniu.android.collect;

import com.qiniu.android.storage.UpToken;

class b implements Runnable {
    final /* synthetic */ UpToken a;
    final /* synthetic */ UploadInfoCollector b;

    b(UploadInfoCollector uploadInfoCollector, UpToken upToken) {
        this.b = uploadInfoCollector;
        this.a = upToken;
    }

    public void run() {
        if (Config.isRecord && Config.isUpload) {
            try {
                this.b.a(this.a);
            } catch (Throwable th) {
            }
        }
    }
}
