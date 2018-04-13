package qsbk.app.live.widget;

class dr implements Runnable {
    final /* synthetic */ GameWinDialog a;

    dr(GameWinDialog gameWinDialog) {
        this.a = gameWinDialog;
    }

    public void run() {
        if (!this.a.e.isSelected()) {
            this.a.C.remove(Integer.valueOf(0));
        }
        if (!this.a.f.isSelected()) {
            this.a.C.remove(Integer.valueOf(1));
        }
        if (!this.a.g.isSelected()) {
            this.a.C.remove(Integer.valueOf(2));
        }
        super.dismiss();
    }
}
