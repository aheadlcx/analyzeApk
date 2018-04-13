package qsbk.app.game;

class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ String c;
    final /* synthetic */ ContinueDownloader d;

    e(ContinueDownloader continueDownloader, String str, boolean z, String str2) {
        this.d = continueDownloader;
        this.a = str;
        this.b = z;
        this.c = str2;
    }

    public void run() {
        this.d.c.onDownload(this.a, this.b, this.c);
    }
}
