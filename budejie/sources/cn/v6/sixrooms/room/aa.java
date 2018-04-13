package cn.v6.sixrooms.room;

import cn.v6.sixrooms.room.statistic.StatisticManager;

final class aa implements Runnable {
    final /* synthetic */ RoomActivity a;

    aa(RoomActivity roomActivity) {
        this.a = roomActivity;
    }

    public final void run() {
        if (this.a.A != null) {
            StatisticManager.getInstance().startWatchTime(this.a.A.getId());
        }
    }
}
