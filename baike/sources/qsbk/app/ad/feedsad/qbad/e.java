package qsbk.app.ad.feedsad.qbad;

import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader.ApkDownloadListener;

class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ ApkDownloadListener c;

    e(ApkDownloadListener apkDownloadListener, String str, int i) {
        this.c = apkDownloadListener;
        this.a = str;
        this.b = i;
    }

    public void run() {
        this.c.displayNotificationMessage(this.a, this.c.mAppName, this.b);
    }
}
