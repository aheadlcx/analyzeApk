package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView.OnHeaderRefreshListener;

final class av implements OnHeaderRefreshListener {
    final /* synthetic */ SpectatorsPop a;

    av(SpectatorsPop spectatorsPop) {
        this.a = spectatorsPop;
    }

    public final void onHeaderRefresh(ReplyWeiBoListView replyWeiBoListView) {
        SpectatorsPop.a(this.a).getWrapUserInfo(this.a.mWrapRoomInfo.getRoominfoBean().getId(), this.a.mBaseRoomActivity.getLastUpadateTime());
    }
}
