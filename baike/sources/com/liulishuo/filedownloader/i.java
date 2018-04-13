package com.liulishuo.filedownloader;

class i implements Runnable {
    final /* synthetic */ o a;
    final /* synthetic */ FileDownloadMessageStation b;

    i(FileDownloadMessageStation fileDownloadMessageStation, o oVar) {
        this.b = fileDownloadMessageStation;
        this.a = oVar;
    }

    public void run() {
        this.a.handoverMessage();
    }
}
