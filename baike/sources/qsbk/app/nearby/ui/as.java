package qsbk.app.nearby.ui;

class as implements Runnable {
    final /* synthetic */ ShakeDialogFragment a;

    as(ShakeDialogFragment shakeDialogFragment) {
        this.a = shakeDialogFragment;
    }

    public void run() {
        if (this.a.C != 3 && this.a.B != 100) {
            this.a.b(3);
        }
    }
}
