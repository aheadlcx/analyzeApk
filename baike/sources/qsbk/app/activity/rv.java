package qsbk.app.activity;

import qsbk.app.utils.SplashAdManager;
import qsbk.app.utils.timer.ITimerProcessor;

class rv implements ITimerProcessor {
    final /* synthetic */ MainActivity a;

    rv(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void process() {
        SplashAdManager.instance().reset();
        SplashAdManager.instance().loadSplashAd();
    }
}
