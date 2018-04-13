package qsbk.app.activity;

class um implements Runnable {
    final /* synthetic */ MyInfoActivity a;

    um(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void run() {
        if (this.a.bG > 0 && this.a.bG <= 100) {
            this.a.f.setProgress(this.a.bG);
            this.a.bG = this.a.bG - 1;
        } else if (this.a.bG == 0) {
            this.a.f.setProgress(this.a.bG);
            this.a.bI.removeCallbacks(this.a.bK);
            if (this.a.bH != null) {
                this.a.bH.cancel();
            }
            this.a.bJ = true;
        }
    }
}
