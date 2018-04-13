package qsbk.app.live.ui;

class cb implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    cb(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        if (this.a.aa()) {
            this.a.an.setDollCatching(true);
            LiveBaseActivity.a(this.a, "doll_show_timeout", "show_control_view", Long.toString(this.a.ax.getOriginId()), "", "");
        }
    }
}
