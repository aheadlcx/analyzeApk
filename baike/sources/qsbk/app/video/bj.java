package qsbk.app.video;

class bj implements Runnable {
    final /* synthetic */ VideoRecordActivity a;

    bj(VideoRecordActivity videoRecordActivity) {
        this.a = videoRecordActivity;
    }

    public void run() {
        if (this.a.k()) {
            this.a.t.postDelayed(this.a.u, 50);
            long d = this.a.m();
            this.a.e.setCurrentSnippetTime((int) d);
            if (d >= 3000) {
                this.a.i.setAlpha(1.0f);
                this.a.i.setEnabled(true);
                this.a.g.setVisibility(8);
                return;
            }
            this.a.i.setAlpha(0.3f);
            this.a.i.setEnabled(false);
        }
    }
}
