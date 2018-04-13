package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.PullToRefreshView;
import cn.v6.sixrooms.widgets.phone.PullToRefreshView.OnFooterRefreshListener;

final class w implements OnFooterRefreshListener {
    final /* synthetic */ ChatListPopupWindow a;

    w(ChatListPopupWindow chatListPopupWindow) {
        this.a = chatListPopupWindow;
    }

    public final void onFooterRefresh(PullToRefreshView pullToRefreshView) {
        if (this.a.g == null || this.a.i == null || this.a.e == null) {
            this.a.f.onFooterRefreshComplete();
            return;
        }
        if (this.a.a.size() < 50) {
            this.a.g.getRoomList(this.a.i.getRoominfoBean().getId(), this.a.e.getLastUpadateTime());
        } else {
            this.a.e.sendLoadAllRoomList();
        }
        this.a.f.onFooterRefreshComplete();
    }
}
