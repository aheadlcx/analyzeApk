package qsbk.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.pay.LaiseePaymentActivity;
import qsbk.app.model.Laisee;
import qsbk.app.utils.ToastUtil;

class qi implements OnClickListener {
    final /* synthetic */ LaiseeChargeActivity a;

    qi(LaiseeChargeActivity laiseeChargeActivity) {
        this.a = laiseeChargeActivity;
    }

    public void onClick(View view) {
        if (!QsbkApp.currentUser.hasPhone()) {
            AlertDialog create = new Builder(this.a).setTitle("账号安全系数低，请先绑定手机，并设置支付密码").setPositiveButton("绑定手机", new qj(this)).create();
            create.setCanceledOnTouchOutside(true);
            create.show();
        } else if (!QsbkApp.currentUser.hasPaypass()) {
            ToastUtil.Short("为了您的资金安全，请先设置支付密码");
            PayPasswordSetActivity.launch(this.a);
        } else if (this.a.j == 3) {
            this.a.r = new Laisee();
            this.a.r.totalMoney = this.a.i;
            this.a.r.content = this.a.p;
            LaiseePaymentActivity.launch(this.a, this.a.r, this.a.k, this.a.k(), this.a.j, 1);
        }
    }
}
