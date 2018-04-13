package cn.v6.sixrooms.room;

import cn.v6.sixrooms.surfaceanim.view.AnimSurfaceViewTouch.AnimCallback;

final class ax implements AnimCallback {
    final /* synthetic */ RoomActivity a;

    ax(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void goRoom(String str, String str2) {
        if (this.a.A == null || !(this.a.A.getRid().equals(str) || this.a.A.getId().equals(str2))) {
            this.a.showEnterRoomDialog(str, str2);
        }
    }
}
