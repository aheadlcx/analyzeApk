package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class afz implements OnClickListener {
    final /* synthetic */ WalletActivity a;

    afz(WalletActivity walletActivity) {
        this.a = walletActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BindPhoneActivity.launchForResult(this.a, 0);
    }
}
