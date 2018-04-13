package qsbk.app.live.ui.bag;

import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

class s implements OnRefreshListener {
    final /* synthetic */ MarketPayDialog a;

    s(MarketPayDialog marketPayDialog) {
        this.a = marketPayDialog;
    }

    public void onRefresh() {
        this.a.k();
    }
}
