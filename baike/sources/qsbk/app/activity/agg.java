package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class agg implements OnClickListener {
    final /* synthetic */ agd a;

    agg(agd agd) {
        this.a = agd;
    }

    public void onClick(View view) {
        if (WalletBalanceActivity.ACTION_WALLET_RECORD.equals(this.a.a.m)) {
            WalletTradeListActivity.launch(this.a.a);
        } else if (WalletBalanceActivity.ACTION_WITHDRAW_BIND.equals(this.a.a.m)) {
            WithdrawSetupActivity.launchForResult(this.a.a, 3);
        }
    }
}
