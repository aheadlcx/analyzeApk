package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams.InitCustomMaker;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler.FileDownloadServiceSharedConnection;
import com.liulishuo.filedownloader.util.FileDownloadProperties;

public class FileDownloadServiceProxy implements IFileDownloadServiceProxy {
    private InitCustomMaker a;
    private final IFileDownloadServiceProxy b;

    private static final class a {
        private static final FileDownloadServiceProxy a = new FileDownloadServiceProxy();
    }

    public static FileDownloadServiceProxy getImpl() {
        return a.a;
    }

    public static FileDownloadServiceSharedConnection getConnectionListener() {
        if (getImpl().b instanceof l) {
            return (FileDownloadServiceSharedConnection) getImpl().b;
        }
        return null;
    }

    private FileDownloadServiceProxy() {
        this.b = FileDownloadProperties.getImpl().PROCESS_NON_SEPARATE ? new l() : new FileDownloadServiceUIGuard();
    }

    void a(InitCustomMaker initCustomMaker) {
        this.a = initCustomMaker;
    }

    public DownloadMgrInitialParams getDownloadMgrInitialParams() {
        return new DownloadMgrInitialParams(this.a);
    }

    public boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        return this.b.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) {
        return this.b.pause(i);
    }

    public boolean isDownloading(String str, String str2) {
        return this.b.isDownloading(str, str2);
    }

    public long getSofar(int i) {
        return this.b.getSofar(i);
    }

    public long getTotal(int i) {
        return this.b.getTotal(i);
    }

    public byte getStatus(int i) {
        return this.b.getStatus(i);
    }

    public void pauseAllTasks() {
        this.b.pauseAllTasks();
    }

    public boolean isIdle() {
        return this.b.isIdle();
    }

    public boolean isConnected() {
        return this.b.isConnected();
    }

    public void bindStartByContext(Context context) {
        this.b.bindStartByContext(context);
    }

    public void bindStartByContext(Context context, Runnable runnable) {
        this.b.bindStartByContext(context, runnable);
    }

    public void unbindByContext(Context context) {
        this.b.unbindByContext(context);
    }

    public void startForeground(int i, Notification notification) {
        this.b.startForeground(i, notification);
    }

    public void stopForeground(boolean z) {
        this.b.stopForeground(z);
    }

    public boolean setMaxNetworkThreadCount(int i) {
        return this.b.setMaxNetworkThreadCount(i);
    }

    public boolean clearTaskData(int i) {
        return this.b.clearTaskData(i);
    }

    public void clearAllTaskData() {
        this.b.clearAllTaskData();
    }
}
