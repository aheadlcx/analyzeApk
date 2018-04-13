package qsbk.app.game;

class d implements Runnable {
    final /* synthetic */ DownListener a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ String d;
    final /* synthetic */ ContinueDownloader e;

    d(ContinueDownloader continueDownloader, DownListener downListener, String str, boolean z, String str2) {
        this.e = continueDownloader;
        this.a = downListener;
        this.b = str;
        this.c = z;
        this.d = str2;
    }

    public void run() {
        this.a.onDownload(this.b, this.c, this.d);
    }
}
