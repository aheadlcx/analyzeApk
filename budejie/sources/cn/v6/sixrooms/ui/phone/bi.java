package cn.v6.sixrooms.ui.phone;

import android.os.Handler;
import android.os.Message;

final class bi extends Handler {
    final /* synthetic */ MyPropActivity a;

    bi(MyPropActivity myPropActivity) {
        this.a = myPropActivity;
    }

    public final void handleMessage(Message message) {
        if (message.what == 1) {
            MyPropActivity.a(this.a);
            this.a.e.setOnClickListener(null);
        }
    }
}
