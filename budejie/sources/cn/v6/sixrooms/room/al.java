package cn.v6.sixrooms.room;

import cn.v6.sixrooms.socket.chat.SuperGMsgCallBack;
import cn.v6.sixrooms.socket.common.SocketUtil;

final class al implements SuperGMsgCallBack {
    final /* synthetic */ RoomActivity a;

    al(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void receiveStartlightCount(StarlightCount starlightCount) {
        RoomActivity.a(this.a, starlightCount, SocketUtil.TYPEID_201002);
    }
}
