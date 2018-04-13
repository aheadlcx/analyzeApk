package qsbk.app.live.widget;

class ci implements Runnable {
    final /* synthetic */ GameResultDialog a;

    ci(GameResultDialog gameResultDialog) {
        this.a = gameResultDialog;
    }

    public void run() {
        this.a.dismiss();
    }
}
