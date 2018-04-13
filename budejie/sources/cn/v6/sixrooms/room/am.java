package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.game.MiniGameBean;
import cn.v6.sixrooms.room.gift.MiniGameListener;

final class am implements MiniGameListener {
    final /* synthetic */ RoomActivity a;

    am(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onMiniGame(MiniGameBean miniGameBean) {
        RoomActivity.a(this.a, miniGameBean, BaseRoomActivity.MINI_GAME);
    }
}
