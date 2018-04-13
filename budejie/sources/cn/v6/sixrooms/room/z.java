package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.presenter.InroomPresenter;
import cn.v6.sixrooms.utils.LogUtils;

final class z implements Runnable {
    final /* synthetic */ RoomActivity a;

    z(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void run() {
        if (!this.a.isFinishing()) {
            LogUtils.i("RoomActivity", "重新连接socket");
            this.a.h();
            this.a.createSocket(InroomPresenter.getInstance().getLocalRoomInfo());
        }
    }
}
