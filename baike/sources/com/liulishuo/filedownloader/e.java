package com.liulishuo.filedownloader;

class e implements b {
    final /* synthetic */ int a;
    final /* synthetic */ FileDownloadLine b;
    private long c;

    e(FileDownloadLine fileDownloadLine, int i) {
        this.b = fileDownloadLine;
        this.a = i;
    }

    public void connected() {
        this.c = FileDownloader.getImpl().getTotal(this.a);
    }

    public Object getValue() {
        return Long.valueOf(this.c);
    }
}
