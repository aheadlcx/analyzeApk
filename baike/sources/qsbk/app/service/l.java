package qsbk.app.service;

import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;

class l extends Handler {
    final /* synthetic */ VersionService a;

    l(VersionService versionService) {
        this.a = versionService;
    }

    public void handleMessage(Message message) {
        if (QsbkApp.loading_process > 99) {
            this.a.a.cancel(0);
            this.a.stopSelf();
            return;
        }
        if (QsbkApp.loading_process > this.a.b) {
            this.a.a(QsbkApp.loading_process);
        }
        this.a.b = QsbkApp.loading_process;
        this.a.c = false;
        this.a.f.sendMessageDelayed(this.a.f.obtainMessage(), 500);
    }
}
