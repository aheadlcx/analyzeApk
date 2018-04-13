package qsbk.app.activity;

import qsbk.app.widget.LoadingLayout.OnLoadingClickListener;

class agc implements OnLoadingClickListener {
    final /* synthetic */ WalletBalanceActivity a;

    agc(WalletBalanceActivity walletBalanceActivity) {
        this.a = walletBalanceActivity;
    }

    public void onLoadingClick() {
        this.a.g();
    }
}
