package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class a extends Handler {
    final /* synthetic */ LocalBroadcastManager a;

    a(LocalBroadcastManager localBroadcastManager, Looper looper) {
        this.a = localBroadcastManager;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.a();
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
