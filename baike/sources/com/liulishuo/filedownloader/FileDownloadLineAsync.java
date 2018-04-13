package com.liulishuo.filedownloader;

import android.app.Notification;

public class FileDownloadLineAsync {
    public boolean startForeground(int i, Notification notification) {
        if (FileDownloader.getImpl().isServiceConnected()) {
            FileDownloader.getImpl().startForeground(i, notification);
            return true;
        }
        FileDownloader.getImpl().bindService(new g(this, i, notification));
        return false;
    }
}
