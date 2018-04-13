package com.liulishuo.filedownloader;

class f implements b {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ FileDownloadLine c;
    private byte d;

    f(FileDownloadLine fileDownloadLine, int i, String str) {
        this.c = fileDownloadLine;
        this.a = i;
        this.b = str;
    }

    public void connected() {
        this.d = FileDownloader.getImpl().getStatus(this.a, this.b);
    }

    public Object getValue() {
        return Byte.valueOf(this.d);
    }
}
