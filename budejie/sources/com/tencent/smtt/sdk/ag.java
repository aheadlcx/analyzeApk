package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.n.a;

final class ag implements a {
    final /* synthetic */ boolean a;
    final /* synthetic */ TbsDownloadConfig b;

    ag(boolean z, TbsDownloadConfig tbsDownloadConfig) {
        this.a = z;
        this.b = tbsDownloadConfig;
    }

    public void a(int i) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.sendRequest] httpResponseCode=" + i);
        if (i < 300) {
            return;
        }
        if (this.a) {
            this.b.setDownloadInterruptCode(-107);
        } else {
            this.b.setDownloadInterruptCode(-207);
        }
    }
}
