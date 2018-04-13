package cn.v6.sixrooms.hall;

import android.os.Handler;
import android.os.Message;

final class ap extends Handler {
    final /* synthetic */ MineFragment a;

    ap(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                MineFragment.a(this.a);
                MineFragment.b(this.a);
                return;
            case 1:
                MineFragment.c(this.a);
                this.a.getBundlePhoneInfo();
                return;
            case 5:
                MineFragment.d(this.a);
                return;
            default:
                return;
        }
    }
}
