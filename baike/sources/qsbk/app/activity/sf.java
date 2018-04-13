package qsbk.app.activity;

class sf implements Runnable {
    final /* synthetic */ MainActivity a;

    sf(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MainActivity.a(this.a, MainActivity.TAB_QIUYOUCIRCLE_ID);
        this.a.selecteTab(MainActivity.i(this.a));
        this.a.getIntent().putExtra("fromSignUp", false);
    }
}
