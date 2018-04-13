package qsbk.app.game;

class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ long b;
    final /* synthetic */ long c;
    final /* synthetic */ ContinueDownloader d;

    h(ContinueDownloader continueDownloader, String str, long j, long j2) {
        this.d = continueDownloader;
        this.a = str;
        this.b = j;
        this.c = j2;
    }

    public void run() {
        this.d.c.onProgress(this.a, this.b, this.c);
    }
}
