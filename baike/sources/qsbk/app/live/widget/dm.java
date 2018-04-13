package qsbk.app.live.widget;

class dm implements Runnable {
    final /* synthetic */ GameWinDialog a;

    dm(GameWinDialog gameWinDialog) {
        this.a = gameWinDialog;
    }

    public void run() {
        try {
            this.a.w.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
