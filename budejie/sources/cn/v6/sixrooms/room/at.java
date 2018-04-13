package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.engine.CommonEventStatusEngine.CallBack;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;

final class at implements CallBack {
    final /* synthetic */ RoomActivity a;

    at(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void result(CommonEventStatusBean commonEventStatusBean) {
        this.a.au = commonEventStatusBean;
        RoomActivity.M(this.a);
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }

    public final void error(int i) {
        this.a.showErrorToast(i);
    }
}
