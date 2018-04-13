package android.support.v4.provider;

import android.os.Handler.Callback;
import android.os.Message;

class q implements Callback {
    final /* synthetic */ SelfDestructiveThread a;

    q(SelfDestructiveThread selfDestructiveThread) {
        this.a = selfDestructiveThread;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.a.a();
                break;
            case 1:
                this.a.b((Runnable) message.obj);
                break;
        }
        return true;
    }
}
