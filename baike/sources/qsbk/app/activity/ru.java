package qsbk.app.activity;

class ru implements Runnable {
    final /* synthetic */ MainActivity a;

    ru(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MainActivity.g(this.a);
    }
}
