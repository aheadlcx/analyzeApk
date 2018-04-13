package qsbk.app.activity;

class ri implements Runnable {
    final /* synthetic */ MainActivity a;

    ri(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        this.a.setSmallTips(MainActivity.TAB_LIVE_ID);
    }
}
