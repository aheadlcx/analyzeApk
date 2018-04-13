package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.PullToRefreshView;
import cn.v6.sixrooms.widgets.phone.PullToRefreshView.OnHeaderRefreshListener;

final class v implements OnHeaderRefreshListener {
    final /* synthetic */ ChatListPopupWindow a;

    v(ChatListPopupWindow chatListPopupWindow) {
        this.a = chatListPopupWindow;
    }

    public final void onHeaderRefresh(PullToRefreshView pullToRefreshView) {
        if (this.a.g == null || this.a.i == null || this.a.e == null) {
            this.a.f.onHeaderRefreshComplete();
        } else {
            this.a.g.getRoomList(this.a.i.getRoominfoBean().getId(), this.a.e.getLastUpadateTime());
        }
    }
}
