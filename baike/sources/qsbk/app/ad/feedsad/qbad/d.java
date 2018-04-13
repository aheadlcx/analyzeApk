package qsbk.app.ad.feedsad.qbad;

import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader.ApkDownloadListener;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ApkDownloadListener b;

    d(ApkDownloadListener apkDownloadListener, String str) {
        this.b = apkDownloadListener;
        this.a = str;
    }

    public void run() {
        this.b.displayNotificationMessage(this.a, this.b.mAppName, 0);
    }
}
