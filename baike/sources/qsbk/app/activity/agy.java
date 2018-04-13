package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

class agy implements OnClickListener {
    final /* synthetic */ WithdrawSetupActivity a;

    agy(WithdrawSetupActivity withdrawSetupActivity) {
        this.a = withdrawSetupActivity;
    }

    public void onClick(View view) {
        PayPWDUniversalActivity.launch(this.a, 102, "输入支付密码", "为了你的账户安全，请输入支付密码进行验证，提现账户设置后，将不能更改，请谨慎操作", null, 0.0d);
    }
}
