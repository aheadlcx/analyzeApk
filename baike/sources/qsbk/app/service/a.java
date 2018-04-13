package qsbk.app.service;

import android.os.Handler;
import android.os.Message;

class a extends Handler {
    final /* synthetic */ ConfigService a;

    a(ConfigService configService) {
        this.a = configService;
    }

    public void handleMessage(Message message) {
        this.a.stopSelf();
    }
}
