package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.FileDownloadServiceProxy;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService.Stub;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import java.lang.ref.WeakReference;

public class FDServiceSharedHandler extends Stub implements e {
    private final c a;
    private final WeakReference<FileDownloadService> b;

    public interface FileDownloadServiceSharedConnection {
        void onConnected(FDServiceSharedHandler fDServiceSharedHandler);

        void onDisconnected();
    }

    FDServiceSharedHandler(WeakReference<FileDownloadService> weakReference, c cVar) {
        this.b = weakReference;
        this.a = cVar;
    }

    public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) {
    }

    public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) {
    }

    public boolean checkDownloading(String str, String str2) {
        return this.a.isDownloading(str, str2);
    }

    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        this.a.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) {
        return this.a.pause(i);
    }

    public void pauseAllTasks() {
        this.a.pauseAll();
    }

    public boolean setMaxNetworkThreadCount(int i) {
        return this.a.setMaxNetworkThreadCount(i);
    }

    public long getSofar(int i) {
        return this.a.getSoFar(i);
    }

    public long getTotal(int i) {
        return this.a.getTotal(i);
    }

    public byte getStatus(int i) {
        return this.a.getStatus(i);
    }

    public boolean isIdle() {
        return this.a.isIdle();
    }

    public void startForeground(int i, Notification notification) {
        if (this.b != null && this.b.get() != null) {
            ((FileDownloadService) this.b.get()).startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) {
        if (this.b != null && this.b.get() != null) {
            ((FileDownloadService) this.b.get()).stopForeground(z);
        }
    }

    public boolean clearTaskData(int i) {
        return this.a.clearTaskData(i);
    }

    public void clearAllTaskData() {
        this.a.clearAllTaskData();
    }

    public void onStartCommand(Intent intent, int i, int i2) {
        FileDownloadServiceProxy.getConnectionListener().onConnected(this);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        FileDownloadServiceProxy.getConnectionListener().onDisconnected();
    }
}
