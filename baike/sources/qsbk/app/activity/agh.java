package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class agh implements OnClickListener {
    final /* synthetic */ WalletBalanceActivity a;

    agh(WalletBalanceActivity walletBalanceActivity) {
        this.a = walletBalanceActivity;
    }

    public void onClick(View view) {
        if (!QsbkApp.currentUser.hasPaypass()) {
            PayPasswordSetActivity.launch(this.a);
            ToastAndDialog.makeText(this.a, "为了您的资金安全，请先设置支付密码").show();
        } else if (this.a.i) {
            WithdrawActivity.launch(this.a, this.a.n, this.a.g.doubleValue(), this.a.h.doubleValue());
        } else {
            WithdrawSetupActivity.launchForResult(this.a, 3);
        }
    }
}
