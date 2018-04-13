package cn.v6.sixrooms.room.statistic;

final class e implements Runnable {
    final /* synthetic */ StatisticManager a;

    e(StatisticManager statisticManager) {
        this.a = statisticManager;
    }

    public final void run() {
        StatisticManager.getInstance().pollSendWatchTime(StatisticManager.a(this.a));
        StatisticManager.b(this.a).postDelayed(this, 3000);
    }
}
