package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.fragment.FullScreenRoomFragment.FullPopShowListener;

final class ba implements FullPopShowListener {
    final /* synthetic */ RoomActivity a;

    ba(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void isShow(boolean z) {
        this.a.aa = z;
        if (z && this.a.E != null) {
            this.a.E.closeAllAnimation();
        }
    }
}
