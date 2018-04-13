package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;

public interface IFileDownloadServiceProxy {
    void bindStartByContext(Context context);

    void bindStartByContext(Context context, Runnable runnable);

    void clearAllTaskData();

    boolean clearTaskData(int i);

    long getSofar(int i);

    byte getStatus(int i);

    long getTotal(int i);

    boolean isConnected();

    boolean isDownloading(String str, String str2);

    boolean isIdle();

    boolean pause(int i);

    void pauseAllTasks();

    boolean setMaxNetworkThreadCount(int i);

    boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3);

    void startForeground(int i, Notification notification);

    void stopForeground(boolean z);

    void unbindByContext(Context context);
}
