package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.widgets.phone.SpectorPullToRefresh.OnHeaderRefreshListener;

final class as implements OnHeaderRefreshListener {
    final /* synthetic */ SpectatorPage a;

    as(SpectatorPage spectatorPage) {
        this.a = spectatorPage;
    }

    public final void onHeaderRefresh(SpectorPullToRefresh spectorPullToRefresh) {
        this.a.isLoadingDate = true;
        this.a.getDate();
    }
}
