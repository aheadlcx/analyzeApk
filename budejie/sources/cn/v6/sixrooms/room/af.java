package cn.v6.sixrooms.room;

import android.os.Message;
import cn.v6.sixrooms.socket.chat.CommonEventStatusBean;
import cn.v6.sixrooms.socket.chat.CommonEventVoteMsgCallBack;

final class af implements CommonEventVoteMsgCallBack {
    final /* synthetic */ RoomActivity a;

    af(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onCommonEventStatusUpdate(CommonEventStatusBean commonEventStatusBean) {
        Message obtain = Message.obtain();
        obtain.obj = commonEventStatusBean;
        obtain.what = 1350;
        this.a.af.sendMessage(obtain);
    }
}
