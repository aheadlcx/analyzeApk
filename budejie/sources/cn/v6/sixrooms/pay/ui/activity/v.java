package cn.v6.sixrooms.pay.ui.activity;

import android.os.Handler;
import android.os.Message;

final class v extends Handler {
    final /* synthetic */ BanklineActivity a;

    v(BanklineActivity banklineActivity) {
        this.a = banklineActivity;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1:
                this.a.n.dismiss();
                return;
            case 2:
                this.a.m.dismiss();
                return;
            default:
                return;
        }
    }
}
