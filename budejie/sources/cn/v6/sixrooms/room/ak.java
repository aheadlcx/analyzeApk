package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.gift.HeadLineListener;
import cn.v6.sixrooms.room.gift.InitTopGiftBean;

final class ak implements HeadLineListener {
    final /* synthetic */ RoomActivity a;

    ak(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onHeadLine(InitTopGiftBean initTopGiftBean) {
        RoomActivity.a(this.a, initTopGiftBean, BaseRoomActivity.HEAD_LINE);
    }
}
