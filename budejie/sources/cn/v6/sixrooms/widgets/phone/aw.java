package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView.OnFooterRefreshListener;

final class aw implements OnFooterRefreshListener {
    final /* synthetic */ SpectatorsPop a;

    aw(SpectatorsPop spectatorsPop) {
        this.a = spectatorsPop;
    }

    public final void onFooterRefresh(ReplyWeiBoListView replyWeiBoListView) {
        this.a.sendLoadAllRoomList();
    }
}
