package qsbk.app.pay.ui;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

class ad implements OnRefreshListener {
    final /* synthetic */ WithdrawHelpActivity a;

    ad(WithdrawHelpActivity withdrawHelpActivity) {
        this.a = withdrawHelpActivity;
    }

    public void onRefresh() {
        this.a.a(this.a.c);
    }
}
