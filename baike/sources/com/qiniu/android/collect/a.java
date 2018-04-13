package com.qiniu.android.collect;

import com.qiniu.android.collect.UploadInfoCollector.RecordMsg;

class a implements Runnable {
    final /* synthetic */ RecordMsg a;
    final /* synthetic */ UploadInfoCollector b;

    a(UploadInfoCollector uploadInfoCollector, RecordMsg recordMsg) {
        this.b = uploadInfoCollector;
        this.a = recordMsg;
    }

    public void run() {
        if (Config.isRecord) {
            try {
                this.b.b(this.a.toRecordMsg());
            } catch (Throwable th) {
            }
        }
    }
}
