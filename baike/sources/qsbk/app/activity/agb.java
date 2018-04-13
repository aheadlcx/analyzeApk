package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class agb implements OnClickListener {
    final /* synthetic */ WalletBalanceActivity a;

    agb(WalletBalanceActivity walletBalanceActivity) {
        this.a = walletBalanceActivity;
    }

    public void onClick(View view) {
        LaiseeChargeActivity.launch(this.a);
    }
}
