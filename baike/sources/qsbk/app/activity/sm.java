package qsbk.app.activity;

class sm implements Runnable {
    final /* synthetic */ MainActivity a;

    sm(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        System.exit(0);
    }
}
