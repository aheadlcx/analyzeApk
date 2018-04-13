package cn.v6.sixrooms.room;

import cn.v6.sixrooms.bean.RoomUpgradeMsg;

final class ao implements Runnable {
    final /* synthetic */ RoomUpgradeMsg a;
    final /* synthetic */ RoomActivity b;

    ao(RoomActivity roomActivity, RoomUpgradeMsg roomUpgradeMsg) {
        this.b = roomActivity;
        this.a = roomUpgradeMsg;
    }

    public final void run() {
        if (this.b.am == null) {
            this.b.am = new RoomUpgradeWindowManager(this.b);
        }
        this.b.am.addShowMsg(this.a);
    }
}
