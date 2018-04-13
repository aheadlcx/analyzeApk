package qsbk.app.activity;

class sd implements Runnable {
    final /* synthetic */ MainActivity a;

    sd(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MainActivity.a(this.a, MainActivity.TAB_QIUYOUCIRCLE_ID);
        this.a.a.setSelectedTab(MainActivity.i(this.a));
        this.a.getIntent().putExtra("fromSignUp", false);
    }
}
