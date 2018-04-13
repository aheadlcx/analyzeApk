package com.liulishuo.filedownloader;

class n implements Runnable {
    final /* synthetic */ FileDownloader a;

    n(FileDownloader fileDownloader) {
        this.a = fileDownloader;
    }

    public void run() {
        FileDownloadServiceProxy.getImpl().pauseAllTasks();
    }
}
