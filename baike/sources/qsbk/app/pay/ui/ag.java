package qsbk.app.pay.ui;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

class ag implements OnRefreshListener {
    final /* synthetic */ WithdrawNoticeActivity a;

    ag(WithdrawNoticeActivity withdrawNoticeActivity) {
        this.a = withdrawNoticeActivity;
    }

    public void onRefresh() {
        this.a.a(this.a.c);
    }
}
