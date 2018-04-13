package cn.v6.sixrooms.pay.ui.activity;

import android.os.Handler;
import android.os.Message;

final class ap extends Handler {
    final /* synthetic */ MobilePayActivity a;

    ap(MobilePayActivity mobilePayActivity) {
        this.a = mobilePayActivity;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 0:
                this.a.f.showSoftInput(this.a.g, 0);
                return;
            case 1:
                this.a.r.dismiss();
                return;
            case 2:
                this.a.q.dismiss();
                return;
            default:
                return;
        }
    }
}
