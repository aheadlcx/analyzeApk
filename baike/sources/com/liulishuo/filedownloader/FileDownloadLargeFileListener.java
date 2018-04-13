package com.liulishuo.filedownloader;

public abstract class FileDownloadLargeFileListener extends FileDownloadListener {
    protected abstract void a(BaseDownloadTask baseDownloadTask, long j, long j2);

    protected abstract void b(BaseDownloadTask baseDownloadTask, long j, long j2);

    protected abstract void c(BaseDownloadTask baseDownloadTask, long j, long j2);

    public FileDownloadLargeFileListener(int i) {
        super(i);
    }

    protected void a(BaseDownloadTask baseDownloadTask, String str, boolean z, long j, long j2) {
    }

    protected void a(BaseDownloadTask baseDownloadTask, Throwable th, int i, long j) {
    }
}
