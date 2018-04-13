package cn.v6.sixrooms.hall;

final class ax implements Runnable {
    final /* synthetic */ aw a;

    ax(aw awVar) {
        this.a = awVar;
    }

    public final void run() {
        MineFragment.e(this.a.a).sendEmptyMessage(1);
    }
}
