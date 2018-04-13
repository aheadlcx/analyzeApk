package com.qiniu.io;

import com.qiniu.utils.IOnProcess;

class a implements IOnProcess {
    final /* synthetic */ SimpleUploadTask a;

    a(SimpleUploadTask simpleUploadTask) {
        this.a = simpleUploadTask;
    }

    public void onProcess(long j, long j2) {
        this.a.publishProgress(new Object[]{Long.valueOf(j), Long.valueOf(j2)});
    }
}
