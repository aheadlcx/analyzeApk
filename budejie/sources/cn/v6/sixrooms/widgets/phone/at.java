package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.widgets.phone.SpectorPullToRefresh.OnFooterRefreshListener;

final class at implements OnFooterRefreshListener {
    final /* synthetic */ SpectatorPage a;

    at(SpectatorPage spectatorPage) {
        this.a = spectatorPage;
    }

    public final void onFooterRefresh(SpectorPullToRefresh spectorPullToRefresh) {
        this.a.isLoadingDate = true;
        this.a.a.sendLoadAllRoomList();
    }
}
