package com.liulishuo.filedownloader;

import android.app.Notification;

class c implements b {
    final /* synthetic */ int a;
    final /* synthetic */ Notification b;
    final /* synthetic */ FileDownloadLine c;

    c(FileDownloadLine fileDownloadLine, int i, Notification notification) {
        this.c = fileDownloadLine;
        this.a = i;
        this.b = notification;
    }

    public void connected() {
        FileDownloader.getImpl().startForeground(this.a, this.b);
    }

    public Object getValue() {
        return null;
    }
}
