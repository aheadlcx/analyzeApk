package com.umeng.update.net;

import android.os.Handler;
import android.os.Message;
import u.upd.b;

class a$b extends Handler {
    final /* synthetic */ a a;

    a$b(a aVar) {
        this.a = aVar;
    }

    public void handleMessage(Message message) {
        try {
            b.c(a.b(), "DownloadAgent.handleMessage(" + message.what + "): ");
            switch (message.what) {
                case 1:
                    if (a.k(this.a) != null) {
                        a.k(this.a).d();
                        return;
                    }
                    return;
                case 2:
                    a.k(this.a).b(message.arg1);
                    return;
                case 3:
                    if (a.k(this.a) != null) {
                        a.k(this.a).a(message.arg1);
                        return;
                    }
                    return;
                case 5:
                    a.l(this.a).unbindService(a.m(this.a));
                    if (a.k(this.a) == null) {
                        return;
                    }
                    if (message.arg1 == 1 || message.arg1 == 3 || message.arg1 == 5) {
                        a.k(this.a).a(message.arg1, message.arg2, message.getData().getString("filename"));
                        return;
                    }
                    a.k(this.a).a(0, 0, null);
                    b.c(a.b(), "DownloadAgent.handleMessage(DownloadingService.DOWNLOAD_COMPLETE_FAIL): ");
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            b.c(a.b(), "DownloadAgent.handleMessage(" + message.what + "): " + e.getMessage());
        }
        e.printStackTrace();
        b.c(a.b(), "DownloadAgent.handleMessage(" + message.what + "): " + e.getMessage());
    }
}
