package qsbk.app.live.widget;

class im implements Runnable {
    final /* synthetic */ SendContinueButton a;

    im(SendContinueButton sendContinueButton) {
        this.a = sendContinueButton;
    }

    public void run() {
        this.a.invalidate();
        this.a.l = this.a.l + 4;
        if (this.a.m != null && this.a.l >= this.a.m.length) {
            this.a.l = 0;
        }
        this.a.b();
    }
}
