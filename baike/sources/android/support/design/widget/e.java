package android.support.design.widget;

import android.os.Handler.Callback;
import android.os.Message;

final class e implements Callback {
    e() {
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                ((BaseTransientBottomBar) message.obj).a();
                return true;
            case 1:
                ((BaseTransientBottomBar) message.obj).b(message.arg1);
                return true;
            default:
                return false;
        }
    }
}
