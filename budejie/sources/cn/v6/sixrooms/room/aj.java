package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.game.PigPkYellowDuckBean;
import cn.v6.sixrooms.socket.chat.PigPkYellowDuckSocketCallBack;

final class aj implements PigPkYellowDuckSocketCallBack {
    final /* synthetic */ RoomActivity a;

    aj(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onPigPkYellowDuckChange(PigPkYellowDuckBean pigPkYellowDuckBean) {
        RoomActivity.a(this.a, pigPkYellowDuckBean, 1514);
    }
}
