package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService.Stub;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow.MessageReceiver;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.lang.ref.WeakReference;

public class FDServiceSeparateHandler extends Stub implements MessageReceiver, e {
    private final RemoteCallbackList<IFileDownloadIPCCallback> a = new RemoteCallbackList();
    private final c b;
    private final WeakReference<FileDownloadService> c;

    private synchronized int a(MessageSnapshot messageSnapshot) {
        int beginBroadcast;
        beginBroadcast = this.a.beginBroadcast();
        int i = 0;
        while (i < beginBroadcast) {
            try {
                ((IFileDownloadIPCCallback) this.a.getBroadcastItem(i)).callback(messageSnapshot);
                i++;
            } catch (Throwable e) {
                FileDownloadLog.e(this, e, "callback error", new Object[0]);
                this.a.finishBroadcast();
            } catch (Throwable th) {
                this.a.finishBroadcast();
            }
        }
        this.a.finishBroadcast();
        return beginBroadcast;
    }

    FDServiceSeparateHandler(WeakReference<FileDownloadService> weakReference, c cVar) {
        this.c = weakReference;
        this.b = cVar;
        MessageSnapshotFlow.getImpl().setReceiver(this);
    }

    public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
        this.a.register(iFileDownloadIPCCallback);
    }

    public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
        this.a.unregister(iFileDownloadIPCCallback);
    }

    public boolean checkDownloading(String str, String str2) throws RemoteException {
        return this.b.isDownloading(str, str2);
    }

    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
        this.b.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) throws RemoteException {
        return this.b.pause(i);
    }

    public void pauseAllTasks() throws RemoteException {
        this.b.pauseAll();
    }

    public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
        return this.b.setMaxNetworkThreadCount(i);
    }

    public long getSofar(int i) throws RemoteException {
        return this.b.getSoFar(i);
    }

    public long getTotal(int i) throws RemoteException {
        return this.b.getTotal(i);
    }

    public byte getStatus(int i) throws RemoteException {
        return this.b.getStatus(i);
    }

    public boolean isIdle() throws RemoteException {
        return this.b.isIdle();
    }

    public void startForeground(int i, Notification notification) throws RemoteException {
        if (this.c != null && this.c.get() != null) {
            ((FileDownloadService) this.c.get()).startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) throws RemoteException {
        if (this.c != null && this.c.get() != null) {
            ((FileDownloadService) this.c.get()).stopForeground(z);
        }
    }

    public boolean clearTaskData(int i) throws RemoteException {
        return this.b.clearTaskData(i);
    }

    public void clearAllTaskData() throws RemoteException {
        this.b.clearAllTaskData();
    }

    public void onStartCommand(Intent intent, int i, int i2) {
    }

    public IBinder onBind(Intent intent) {
        return this;
    }

    public void onDestroy() {
        MessageSnapshotFlow.getImpl().setReceiver(null);
    }

    public void receive(MessageSnapshot messageSnapshot) {
        a(messageSnapshot);
    }
}
