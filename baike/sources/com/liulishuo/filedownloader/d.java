package com.liulishuo.filedownloader;

class d implements b {
    final /* synthetic */ int a;
    final /* synthetic */ FileDownloadLine b;
    private long c;

    d(FileDownloadLine fileDownloadLine, int i) {
        this.b = fileDownloadLine;
        this.a = i;
    }

    public void connected() {
        this.c = FileDownloader.getImpl().getSoFar(this.a);
    }

    public Object getValue() {
        return Long.valueOf(this.c);
    }
}
