package android.support.v4.provider;

import android.os.Handler;
import android.support.v4.provider.SelfDestructiveThread.ReplyCallback;
import java.util.concurrent.Callable;

class r implements Runnable {
    final /* synthetic */ Callable a;
    final /* synthetic */ Handler b;
    final /* synthetic */ ReplyCallback c;
    final /* synthetic */ SelfDestructiveThread d;

    r(SelfDestructiveThread selfDestructiveThread, Callable callable, Handler handler, ReplyCallback replyCallback) {
        this.d = selfDestructiveThread;
        this.a = callable;
        this.b = handler;
        this.c = replyCallback;
    }

    public void run() {
        Object call;
        try {
            call = this.a.call();
        } catch (Exception e) {
            call = null;
        }
        this.b.post(new s(this, call));
    }
}
