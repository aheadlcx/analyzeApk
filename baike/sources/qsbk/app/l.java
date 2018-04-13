package qsbk.app;

class l implements Runnable {
    final /* synthetic */ QsbkApp a;

    l(QsbkApp qsbkApp) {
        this.a = qsbkApp;
    }

    public void run() {
        QsbkApp.initVoteHandler();
    }
}
