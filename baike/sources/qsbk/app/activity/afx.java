package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;

class afx implements OnClickListener {
    final /* synthetic */ WalletActivity a;

    afx(WalletActivity walletActivity) {
        this.a = walletActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (QsbkApp.currentUser.hasPhone()) {
            PayPasswordSetActivity.launch(this.a);
        } else {
            new Builder(this.a).setTitle("账号安全系数低，请先绑定手机").setPositiveButton("绑定手机", new afy(this)).setNegativeButton("取消", null).show();
        }
    }
}
