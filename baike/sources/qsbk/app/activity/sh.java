package qsbk.app.activity;

class sh implements Runnable {
    final /* synthetic */ MainActivity a;

    sh(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MainActivity.a(this.a, MainActivity.TAB_MESSAGE_ID);
        this.a.selecteTab(MainActivity.i(this.a));
    }
}
