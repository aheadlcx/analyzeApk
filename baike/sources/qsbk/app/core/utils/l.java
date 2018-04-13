package qsbk.app.core.utils;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;

final class l extends FileDownloadListener {
    l() {
    }

    protected void a(BaseDownloadTask baseDownloadTask, int i, int i2) {
        LogUtils.d(GiftResSync.a, "pending " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void a(BaseDownloadTask baseDownloadTask, String str, boolean z, int i, int i2) {
        LogUtils.d(GiftResSync.a, "connected " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void b(BaseDownloadTask baseDownloadTask, int i, int i2) {
        LogUtils.d(GiftResSync.a, "progress " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void b(BaseDownloadTask baseDownloadTask) {
        LogUtils.d(GiftResSync.a, "blockComplete " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void a(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
        LogUtils.d(GiftResSync.a, "retry " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void c(BaseDownloadTask baseDownloadTask) {
        LogUtils.d(GiftResSync.a, "completed " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl() + " and " + baseDownloadTask.getFilename() + " -> " + baseDownloadTask.getTargetFilePath());
        GiftResSync.b.execute(new m(this, baseDownloadTask));
    }

    protected void c(BaseDownloadTask baseDownloadTask, int i, int i2) {
        LogUtils.d(GiftResSync.a, "paused " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void a(BaseDownloadTask baseDownloadTask, Throwable th) {
        LogUtils.d(GiftResSync.a, "error " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }

    protected void d(BaseDownloadTask baseDownloadTask) {
        LogUtils.d(GiftResSync.a, "warn " + baseDownloadTask.getTag() + " -> " + baseDownloadTask.getUrl());
    }
}
