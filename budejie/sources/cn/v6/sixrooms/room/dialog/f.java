package cn.v6.sixrooms.room.dialog;

import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView;
import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView.OnFooterRefreshListener;

final class f implements OnFooterRefreshListener {
    final /* synthetic */ SpectatorsDialog a;

    f(SpectatorsDialog spectatorsDialog) {
        this.a = spectatorsDialog;
    }

    public final void onFooterRefresh(ReplyWeiBoListView replyWeiBoListView) {
        this.a.sendLoadAllRoomList();
    }
}
