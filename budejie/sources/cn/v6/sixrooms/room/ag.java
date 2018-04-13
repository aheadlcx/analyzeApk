package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.ChangzhanBeginBean;
import cn.v6.sixrooms.bean.ChangzhanFinalUsersBean;
import cn.v6.sixrooms.bean.ChangzhanFinishBean;
import cn.v6.sixrooms.bean.ChangzhanStatusBean;
import cn.v6.sixrooms.bean.ChangzhanTimeBean;
import cn.v6.sixrooms.socket.chat.ChangzhanSocketCallBack;

final class ag implements ChangzhanSocketCallBack {
    final /* synthetic */ RoomActivity a;

    ag(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void onChangzhanTimeChange(ChangzhanTimeBean changzhanTimeBean) {
        RoomActivity.a(this.a, changzhanTimeBean, 119);
    }

    public final void onChangzhanStatusUpdate(ChangzhanStatusBean changzhanStatusBean) {
        RoomActivity.a(this.a, changzhanStatusBean, 1306);
    }

    public final void onChangzhanFinish(ChangzhanFinishBean changzhanFinishBean) {
        RoomActivity.a(this.a, changzhanFinishBean, 117);
    }

    public final void onChangzhanBegin(ChangzhanBeginBean changzhanBeginBean) {
        RoomActivity.a(this.a, changzhanBeginBean, 113);
    }

    public final void onChangzhanFinalUsersChange(ChangzhanFinalUsersBean changzhanFinalUsersBean) {
        RoomActivity.a(this.a, changzhanFinalUsersBean.getContent(), 129);
    }
}
