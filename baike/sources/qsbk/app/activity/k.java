package qsbk.app.activity;

class k implements Runnable {
    final /* synthetic */ ActionBarLoginActivity a;

    k(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void run() {
        ActionBarLoginActivity.a(this.a, false);
        ActionBarLoginActivity.a(this.a).dismiss();
    }
}
