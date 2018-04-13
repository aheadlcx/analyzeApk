package qsbk.app.nearby.ui;

class ar implements Runnable {
    final /* synthetic */ ShakeDialogFragment a;

    ar(ShakeDialogFragment shakeDialogFragment) {
        this.a = shakeDialogFragment;
    }

    public void run() {
        this.a.dismissAllowingStateLoss();
    }
}
