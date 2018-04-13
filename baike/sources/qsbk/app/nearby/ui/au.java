package qsbk.app.nearby.ui;

class au implements Runnable {
    final /* synthetic */ ShakeDialogFragment a;

    au(ShakeDialogFragment shakeDialogFragment) {
        this.a = shakeDialogFragment;
    }

    public void run() {
        if (this.a.K > 0 && this.a.K < 100) {
            this.a.w.setText(this.a.K + "%");
            this.a.a(this.a.K);
            this.a.K = this.a.K - 1;
        } else if (this.a.K == 0) {
            this.a.w.setText(this.a.K + "%");
            this.a.a(this.a.K);
            this.a.j.removeCallbacks(this.a.P);
            if (this.a.k != null) {
                this.a.k.cancel();
            }
        } else if (this.a.K == 100) {
            if (this.a.k != null) {
                this.a.k.cancel();
            }
            this.a.w.setText(this.a.K + "%");
            this.a.j.removeCallbacks(this.a.O);
            this.a.j.removeCallbacks(this.a.D);
            this.a.b();
            this.a.b(2);
            this.a.d();
            this.a.K = 0;
        }
    }
}
