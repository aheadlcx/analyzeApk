package qsbk.app.activity;

import qsbk.app.QsbkApp;

class rp implements Runnable {
    final /* synthetic */ MainActivity a;

    rp(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        QsbkApp.getInstance().checkAndGotoMarketIfNecessary(this.a, false);
        MainActivity.hasShowMarket = true;
    }
}
