package cn.v6.sixrooms.hall;

final class az implements Runnable {
    final /* synthetic */ MineFragment a;

    az(MineFragment mineFragment) {
        this.a = mineFragment;
    }

    public final void run() {
        MineFragment.e(this.a).sendEmptyMessage(1);
    }
}
