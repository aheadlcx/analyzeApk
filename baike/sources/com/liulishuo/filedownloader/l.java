package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler.FileDownloadServiceSharedConnection;
import com.liulishuo.filedownloader.services.FileDownloadService.SharedMainProcessService;
import com.liulishuo.filedownloader.util.DownloadServiceNotConnectedHelper;
import java.util.ArrayList;
import java.util.List;

class l implements IFileDownloadServiceProxy, FileDownloadServiceSharedConnection {
    private static final Class<?> a = SharedMainProcessService.class;
    private final ArrayList<Runnable> b = new ArrayList();
    private FDServiceSharedHandler c;

    l() {
    }

    public boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.start(str, str2, z);
        }
        this.c.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
        return true;
    }

    public boolean pause(int i) {
        if (isConnected()) {
            return this.c.pause(i);
        }
        return DownloadServiceNotConnectedHelper.pause(i);
    }

    public boolean isDownloading(String str, String str2) {
        if (isConnected()) {
            return this.c.checkDownloading(str, str2);
        }
        return DownloadServiceNotConnectedHelper.isDownloading(str, str2);
    }

    public long getSofar(int i) {
        if (isConnected()) {
            return this.c.getSofar(i);
        }
        return DownloadServiceNotConnectedHelper.getSofar(i);
    }

    public long getTotal(int i) {
        if (isConnected()) {
            return this.c.getTotal(i);
        }
        return DownloadServiceNotConnectedHelper.getTotal(i);
    }

    public byte getStatus(int i) {
        if (isConnected()) {
            return this.c.getStatus(i);
        }
        return DownloadServiceNotConnectedHelper.getStatus(i);
    }

    public void pauseAllTasks() {
        if (isConnected()) {
            this.c.pauseAllTasks();
        } else {
            DownloadServiceNotConnectedHelper.pauseAllTasks();
        }
    }

    public boolean isIdle() {
        if (isConnected()) {
            return this.c.isIdle();
        }
        return DownloadServiceNotConnectedHelper.isIdle();
    }

    public boolean isConnected() {
        return this.c != null;
    }

    public void bindStartByContext(Context context) {
        bindStartByContext(context, null);
    }

    public void bindStartByContext(Context context, Runnable runnable) {
        if (!(runnable == null || this.b.contains(runnable))) {
            this.b.add(runnable);
        }
        context.startService(new Intent(context, a));
    }

    public void unbindByContext(Context context) {
        context.stopService(new Intent(context, a));
        this.c = null;
    }

    public void startForeground(int i, Notification notification) {
        if (isConnected()) {
            this.c.startForeground(i, notification);
        } else {
            DownloadServiceNotConnectedHelper.startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) {
        if (isConnected()) {
            this.c.stopForeground(z);
        } else {
            DownloadServiceNotConnectedHelper.stopForeground(z);
        }
    }

    public boolean setMaxNetworkThreadCount(int i) {
        if (isConnected()) {
            return this.c.setMaxNetworkThreadCount(i);
        }
        return DownloadServiceNotConnectedHelper.setMaxNetworkThreadCount(i);
    }

    public boolean clearTaskData(int i) {
        if (isConnected()) {
            return this.c.clearTaskData(i);
        }
        return DownloadServiceNotConnectedHelper.clearTaskData(i);
    }

    public void clearAllTaskData() {
        if (isConnected()) {
            this.c.clearAllTaskData();
        } else {
            DownloadServiceNotConnectedHelper.clearAllTaskData();
        }
    }

    public void onConnected(FDServiceSharedHandler fDServiceSharedHandler) {
        this.c = fDServiceSharedHandler;
        List<Runnable> list = (List) this.b.clone();
        this.b.clear();
        for (Runnable run : list) {
            run.run();
        }
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(ConnectStatus.connected, a));
    }

    public void onDisconnected() {
        this.c = null;
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(ConnectStatus.disconnected, a));
    }
}
