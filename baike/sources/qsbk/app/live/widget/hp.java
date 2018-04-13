package qsbk.app.live.widget;

class hp implements Runnable {
    final /* synthetic */ PokerGroup a;

    hp(PokerGroup pokerGroup) {
        this.a = pokerGroup;
    }

    public void run() {
        this.a.removeCallbacks(this);
        if (this.a.r <= this.a.s) {
            this.a.invalidate();
            this.a.postDelayed(this.a.q, (long) this.a.u);
            return;
        }
        this.a.r = this.a.s;
        this.a.invalidate();
        this.a.w = false;
    }
}
