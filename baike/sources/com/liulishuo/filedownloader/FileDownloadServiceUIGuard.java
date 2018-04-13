package com.liulishuo.filedownloader;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.BaseFileServiceUIGuard;
import com.liulishuo.filedownloader.services.FileDownloadService.SeparateProcessService;
import com.liulishuo.filedownloader.util.DownloadServiceNotConnectedHelper;

class FileDownloadServiceUIGuard extends BaseFileServiceUIGuard<FileDownloadServiceCallback, IFileDownloadIPCService> {

    protected static class FileDownloadServiceCallback extends Stub {
        protected FileDownloadServiceCallback() {
        }

        public void callback(MessageSnapshot messageSnapshot) throws RemoteException {
            MessageSnapshotFlow.getImpl().inflow(messageSnapshot);
        }
    }

    protected /* synthetic */ void a(IInterface iInterface, Binder binder) throws RemoteException {
        b((IFileDownloadIPCService) iInterface, (FileDownloadServiceCallback) binder);
    }

    protected /* synthetic */ Binder b() {
        return a();
    }

    protected /* synthetic */ IInterface b(IBinder iBinder) {
        return a(iBinder);
    }

    protected /* synthetic */ void b(IInterface iInterface, Binder binder) throws RemoteException {
        a((IFileDownloadIPCService) iInterface, (FileDownloadServiceCallback) binder);
    }

    FileDownloadServiceUIGuard() {
        super(SeparateProcessService.class);
    }

    protected FileDownloadServiceCallback a() {
        return new FileDownloadServiceCallback();
    }

    protected IFileDownloadIPCService a(IBinder iBinder) {
        return IFileDownloadIPCService.Stub.asInterface(iBinder);
    }

    protected void a(IFileDownloadIPCService iFileDownloadIPCService, FileDownloadServiceCallback fileDownloadServiceCallback) throws RemoteException {
        iFileDownloadIPCService.registerCallback(fileDownloadServiceCallback);
    }

    protected void b(IFileDownloadIPCService iFileDownloadIPCService, FileDownloadServiceCallback fileDownloadServiceCallback) throws RemoteException {
        iFileDownloadIPCService.unregisterCallback(fileDownloadServiceCallback);
    }

    public boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.start(str, str2, z);
        }
        try {
            ((IFileDownloadIPCService) c()).start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pause(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.pause(i);
        }
        try {
            return ((IFileDownloadIPCService) c()).pause(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDownloading(String str, String str2) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.isDownloading(str, str2);
        }
        try {
            return ((IFileDownloadIPCService) c()).checkDownloading(str, str2);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getSofar(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.getSofar(i);
        }
        try {
            return ((IFileDownloadIPCService) c()).getSofar(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long getTotal(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.getTotal(i);
        }
        try {
            return ((IFileDownloadIPCService) c()).getTotal(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public byte getStatus(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.getStatus(i);
        }
        try {
            return ((IFileDownloadIPCService) c()).getStatus(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return (byte) 0;
        }
    }

    public void pauseAllTasks() {
        if (isConnected()) {
            try {
                ((IFileDownloadIPCService) c()).pauseAllTasks();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        DownloadServiceNotConnectedHelper.pauseAllTasks();
    }

    public boolean isIdle() {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.isIdle();
        }
        try {
            ((IFileDownloadIPCService) c()).isIdle();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void startForeground(int i, Notification notification) {
        if (isConnected()) {
            try {
                ((IFileDownloadIPCService) c()).startForeground(i, notification);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        DownloadServiceNotConnectedHelper.startForeground(i, notification);
    }

    public void stopForeground(boolean z) {
        if (isConnected()) {
            try {
                ((IFileDownloadIPCService) c()).stopForeground(z);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        DownloadServiceNotConnectedHelper.stopForeground(z);
    }

    public boolean setMaxNetworkThreadCount(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.setMaxNetworkThreadCount(i);
        }
        try {
            return ((IFileDownloadIPCService) c()).setMaxNetworkThreadCount(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearTaskData(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.clearTaskData(i);
        }
        try {
            return ((IFileDownloadIPCService) c()).clearTaskData(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clearAllTaskData() {
        if (isConnected()) {
            try {
                ((IFileDownloadIPCService) c()).clearAllTaskData();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        DownloadServiceNotConnectedHelper.clearAllTaskData();
    }
}
