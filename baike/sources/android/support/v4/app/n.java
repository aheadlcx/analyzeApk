package android.support.v4.app;

class n implements Runnable {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public void run() {
        this.a.a.endViewTransition(this.a.b);
        if (this.a.c.getAnimatingAway() != null) {
            this.a.c.setAnimatingAway(null);
            this.a.d.a(this.a.c, this.a.c.getStateAfterAnimating(), 0, 0, false);
        }
    }
}
