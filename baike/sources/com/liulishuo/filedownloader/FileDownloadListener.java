package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.util.FileDownloadLog;

public abstract class FileDownloadListener {
    protected abstract void a(BaseDownloadTask baseDownloadTask, int i, int i2);

    protected abstract void a(BaseDownloadTask baseDownloadTask, Throwable th);

    protected abstract void b(BaseDownloadTask baseDownloadTask, int i, int i2);

    protected abstract void c(BaseDownloadTask baseDownloadTask);

    protected abstract void c(BaseDownloadTask baseDownloadTask, int i, int i2);

    protected abstract void d(BaseDownloadTask baseDownloadTask);

    public FileDownloadListener(int i) {
        FileDownloadLog.w(this, "not handle priority any more", new Object[0]);
    }

    protected boolean a() {
        return false;
    }

    protected void a(BaseDownloadTask baseDownloadTask) {
    }

    protected void a(BaseDownloadTask baseDownloadTask, String str, boolean z, int i, int i2) {
    }

    protected void b(BaseDownloadTask baseDownloadTask) throws Throwable {
    }

    protected void a(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
    }
}
