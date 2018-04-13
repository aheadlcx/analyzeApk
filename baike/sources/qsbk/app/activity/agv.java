package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.pay.PayPWDUniversalActivity;

class agv implements OnClickListener {
    final /* synthetic */ agu a;

    agv(agu agu) {
        this.a = agu;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPWDUniversalActivity.launch(this.a.a, 11, "输入支付密码", "到账金额:", String.format("提现金额：%s元\n 支付宝手续费：%s元", new Object[]{this.a.a.p, this.a.a.s}), this.a.a.r);
    }
}
