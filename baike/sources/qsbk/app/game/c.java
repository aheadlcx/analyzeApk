package qsbk.app.game;

class c implements Runnable {
    final /* synthetic */ DownListener a;
    final /* synthetic */ String b;
    final /* synthetic */ ContinueDownloader c;

    c(ContinueDownloader continueDownloader, DownListener downListener, String str) {
        this.c = continueDownloader;
        this.a = downListener;
        this.b = str;
    }

    public void run() {
        this.a.onDownload(this.b, true, "");
    }
}
