package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

class ahb implements OnClickListener {
    final /* synthetic */ aha a;

    ahb(aha aha) {
        this.a = aha;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPWDUniversalActivity.launch(this.a.a, 102, "输入支付密码", "为了你的账户安全，请输入支付密码进行验证，提现账户设置后，将不能更改，请谨慎操作", null, 0.0d);
    }
}
