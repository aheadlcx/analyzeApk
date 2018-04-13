package com.liulishuo.filedownloader.event;

class a implements Runnable {
    final /* synthetic */ IDownloadEvent a;
    final /* synthetic */ DownloadEventPoolImpl b;

    a(DownloadEventPoolImpl downloadEventPoolImpl, IDownloadEvent iDownloadEvent) {
        this.b = downloadEventPoolImpl;
        this.a = iDownloadEvent;
    }

    public void run() {
        this.b.publish(this.a);
    }
}
