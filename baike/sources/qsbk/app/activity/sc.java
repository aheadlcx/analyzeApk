package qsbk.app.activity;

class sc implements Runnable {
    final /* synthetic */ MainActivity a;

    sc(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        this.a.setSmallTips(MainActivity.TAB_MIME_ID);
    }
}
