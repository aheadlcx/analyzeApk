package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.widgets.CommonEventDialog.VoteClickListener;

final class av implements VoteClickListener {
    final /* synthetic */ RoomActivity a;

    av(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onVoteClick(String str) {
        this.a.v.openGiftBox(str);
    }

    public final void onFreeVoteClick(String str) {
        this.a.sendFreeVoteRequest(this.a.A.getId(), str);
    }
}
