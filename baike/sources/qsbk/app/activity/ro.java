package qsbk.app.activity;

import qsbk.app.QsbkApp;

class ro implements Runnable {
    final /* synthetic */ MainActivity a;

    ro(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        if (!QsbkApp.hasVerify) {
            this.a.startMyService();
        }
    }
}
