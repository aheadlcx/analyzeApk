package cn.v6.sixrooms.room.dialog;

import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView;
import cn.v6.sixrooms.widgets.phone.ReplyWeiBoListView.OnHeaderRefreshListener;

final class e implements OnHeaderRefreshListener {
    final /* synthetic */ SpectatorsDialog a;

    e(SpectatorsDialog spectatorsDialog) {
        this.a = spectatorsDialog;
    }

    public final void onHeaderRefresh(ReplyWeiBoListView replyWeiBoListView) {
        this.a.n.getWrapUserInfo(this.a.mWrapRoomInfo.getRoominfoBean().getId(), this.a.mBaseRoomActivity.getLastUpadateTime());
    }
}
