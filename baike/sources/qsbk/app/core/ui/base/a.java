package qsbk.app.core.ui.base;

import android.os.Handler;
import android.os.Message;

class a extends Handler {
    final /* synthetic */ BaseActivity a;

    a(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void handleMessage(Message message) {
        this.a.onHandleMessage(message);
    }
}
