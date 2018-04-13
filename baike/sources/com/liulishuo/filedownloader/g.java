package com.liulishuo.filedownloader;

import android.app.Notification;

class g implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Notification b;
    final /* synthetic */ FileDownloadLineAsync c;

    g(FileDownloadLineAsync fileDownloadLineAsync, int i, Notification notification) {
        this.c = fileDownloadLineAsync;
        this.a = i;
        this.b = notification;
    }

    public void run() {
        FileDownloader.getImpl().startForeground(this.a, this.b);
    }
}
