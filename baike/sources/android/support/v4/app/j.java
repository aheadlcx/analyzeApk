package android.support.v4.app;

import android.os.Handler;
import android.os.Message;

class j extends Handler {
    final /* synthetic */ FragmentActivity a;

    j(FragmentActivity fragmentActivity) {
        this.a = fragmentActivity;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.mStopped) {
                    this.a.doReallyStop(false);
                    return;
                }
                return;
            case 2:
                this.a.onResumeFragments();
                this.a.mFragments.execPendingActions();
                return;
            default:
                super.handleMessage(message);
                return;
        }
    }
}
