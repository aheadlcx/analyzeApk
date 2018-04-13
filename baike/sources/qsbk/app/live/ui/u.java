package qsbk.app.live.ui;

class u implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    u(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        this.a.mHandler.removeCallbacks(this);
        this.a.L.smoothScrollToPosition(this.a.O.getItemCount() - 1);
    }
}
