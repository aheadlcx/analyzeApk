package qsbk.app.activity;

import qsbk.app.utils.TipsManager;

class rr implements Runnable {
    final /* synthetic */ MainActivity a;

    rr(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        TipsManager.checkMyInfo(this.a);
    }
}
