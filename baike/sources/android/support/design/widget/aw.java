package android.support.design.widget;

import android.os.Handler.Callback;
import android.os.Message;

class aw implements Callback {
    final /* synthetic */ av a;

    aw(av avVar) {
        this.a = avVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.a.a((b) message.obj);
                return true;
            default:
                return false;
        }
    }
}
