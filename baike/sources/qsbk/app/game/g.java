package qsbk.app.game;

class g implements Runnable {
    final /* synthetic */ DownListener a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ long d;
    final /* synthetic */ ContinueDownloader e;

    g(ContinueDownloader continueDownloader, DownListener downListener, String str, long j, long j2) {
        this.e = continueDownloader;
        this.a = downListener;
        this.b = str;
        this.c = j;
        this.d = j2;
    }

    public void run() {
        this.a.onProgress(this.b, this.c, this.d);
    }
}
