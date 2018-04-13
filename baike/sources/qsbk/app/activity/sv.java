package qsbk.app.activity;

class sv implements Runnable {
    final /* synthetic */ MainActivity a;

    sv(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MainActivity.a(Boolean.valueOf(false));
        MainActivity.b(Boolean.valueOf(false));
    }
}
