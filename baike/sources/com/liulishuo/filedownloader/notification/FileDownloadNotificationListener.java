package com.liulishuo.filedownloader.notification;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.FileDownloadList;
import com.liulishuo.filedownloader.FileDownloadListener;
import junit.framework.Assert;

public abstract class FileDownloadNotificationListener extends FileDownloadListener {
    private final FileDownloadNotificationHelper a;

    protected abstract BaseNotificationItem e(BaseDownloadTask baseDownloadTask);

    public FileDownloadNotificationListener(FileDownloadNotificationHelper fileDownloadNotificationHelper) {
        Assert.assertNotNull("FileDownloadNotificationHelper must not null", fileDownloadNotificationHelper);
        this.a = fileDownloadNotificationHelper;
    }

    public FileDownloadNotificationHelper getHelper() {
        return this.a;
    }

    public void addNotificationItem(int i) {
        if (i != 0) {
            IRunningTask iRunningTask = FileDownloadList.getImpl().get(i);
            if (iRunningTask != null) {
                addNotificationItem(iRunningTask.getOrigin());
            }
        }
    }

    public void addNotificationItem(BaseDownloadTask baseDownloadTask) {
        if (!f(baseDownloadTask)) {
            BaseNotificationItem e = e(baseDownloadTask);
            if (e != null) {
                this.a.add(e);
            }
        }
    }

    public void destroyNotification(BaseDownloadTask baseDownloadTask) {
        if (!f(baseDownloadTask)) {
            this.a.showIndeterminate(baseDownloadTask.getId(), baseDownloadTask.getStatus());
            BaseNotificationItem remove = this.a.remove(baseDownloadTask.getId());
            if (!a(baseDownloadTask, remove) && remove != null) {
                remove.cancel();
            }
        }
    }

    public void showIndeterminate(BaseDownloadTask baseDownloadTask) {
        if (!f(baseDownloadTask)) {
            this.a.showIndeterminate(baseDownloadTask.getId(), baseDownloadTask.getStatus());
        }
    }

    public void showProgress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        if (!f(baseDownloadTask)) {
            this.a.showProgress(baseDownloadTask.getId(), baseDownloadTask.getSmallFileSoFarBytes(), baseDownloadTask.getSmallFileTotalBytes());
        }
    }

    protected boolean a(BaseDownloadTask baseDownloadTask, BaseNotificationItem baseNotificationItem) {
        return false;
    }

    protected boolean f(BaseDownloadTask baseDownloadTask) {
        return false;
    }
}
