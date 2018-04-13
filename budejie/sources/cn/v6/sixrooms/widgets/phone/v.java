package cn.v6.sixrooms.widgets.phone;

import android.os.Handler;
import android.os.Message;

final class v extends Handler {
    final /* synthetic */ MsgVerifyDialog a;

    v(MsgVerifyDialog msgVerifyDialog) {
        this.a = msgVerifyDialog;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                int i = message.arg1 - 1;
                if (i > 0) {
                    MsgVerifyDialog.a(this.a, i);
                    return;
                } else if (MsgVerifyDialog.a(this.a) != null) {
                    MsgVerifyDialog.a(this.a).setText("获取验证码");
                    this.a.switchCodeBtnClickable(true);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
