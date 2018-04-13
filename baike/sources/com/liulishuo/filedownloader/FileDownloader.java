package com.liulishuo.filedownloader;

import android.app.Notification;
import android.content.Context;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.model.FileDownloadTaskAtom;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams.InitCustomMaker;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.List;

public class FileDownloader {
    private static final Object b = new Object();
    private static final Object d = new Object();
    private Runnable a;
    private IQueuesHandler c;
    private ILostServiceConnectedHandler e;

    private static final class a {
        private static final FileDownloader a = new FileDownloader();
    }

    public static void init(Context context) {
        init(context.getApplicationContext(), null);
    }

    public static void init(Context context, InitCustomMaker initCustomMaker) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(FileDownloader.class, "init Downloader", new Object[0]);
        }
        FileDownloadHelper.holdContext(context);
        FileDownloadServiceProxy.getImpl().a(initCustomMaker);
    }

    public static FileDownloader getImpl() {
        return a.a;
    }

    public static void setGlobalPost2UIInterval(int i) {
        FileDownloadMessageStation.a = i;
    }

    public static void setGlobalHandleSubPackageSize(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("sub package size must more than 0");
        }
        FileDownloadMessageStation.b = i;
    }

    public static void enableAvoidDropFrame() {
        setGlobalPost2UIInterval(10);
    }

    public static void disableAvoidDropFrame() {
        setGlobalPost2UIInterval(-1);
    }

    public static boolean isEnabledAvoidDropFrame() {
        return FileDownloadMessageStation.isIntervalValid();
    }

    public BaseDownloadTask create(String str) {
        return new DownloadTask(str);
    }

    public boolean start(FileDownloadListener fileDownloadListener, boolean z) {
        if (fileDownloadListener == null) {
            FileDownloadLog.w(this, "Tasks with the listener can't start, because the listener provided is null: [null, %B]", new Object[]{Boolean.valueOf(z)});
            return false;
        } else if (z) {
            return a().startQueueSerial(fileDownloadListener);
        } else {
            return a().startQueueParallel(fileDownloadListener);
        }
    }

    public void pause(FileDownloadListener fileDownloadListener) {
        m.getImpl().a(fileDownloadListener);
        for (IRunningTask origin : FileDownloadList.getImpl().a(fileDownloadListener)) {
            origin.getOrigin().pause();
        }
    }

    public void pauseAll() {
        m.getImpl().a();
        for (IRunningTask origin : FileDownloadList.getImpl().c()) {
            origin.getOrigin().pause();
        }
        if (FileDownloadServiceProxy.getImpl().isConnected()) {
            FileDownloadServiceProxy.getImpl().pauseAllTasks();
            return;
        }
        if (this.a == null) {
            this.a = new n(this);
        }
        FileDownloadServiceProxy.getImpl().bindStartByContext(FileDownloadHelper.getAppContext(), this.a);
    }

    public int pause(int i) {
        List<IRunningTask> c = FileDownloadList.getImpl().c(i);
        if (c == null || c.isEmpty()) {
            FileDownloadLog.w(this, "request pause but not exist %d", new Object[]{Integer.valueOf(i)});
            return 0;
        }
        for (IRunningTask origin : c) {
            origin.getOrigin().pause();
        }
        return c.size();
    }

    public boolean clear(int i, String str) {
        pause(i);
        if (!FileDownloadServiceProxy.getImpl().clearTaskData(i)) {
            return false;
        }
        File file = new File(FileDownloadUtils.getTempPath(str));
        if (file.exists()) {
            file.delete();
        }
        file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        return true;
    }

    public void clearAllTaskData() {
        pauseAll();
        FileDownloadServiceProxy.getImpl().clearAllTaskData();
    }

    public long getSoFar(int i) {
        IRunningTask iRunningTask = FileDownloadList.getImpl().get(i);
        if (iRunningTask == null) {
            return FileDownloadServiceProxy.getImpl().getSofar(i);
        }
        return iRunningTask.getOrigin().getLargeFileSoFarBytes();
    }

    public long getTotal(int i) {
        IRunningTask iRunningTask = FileDownloadList.getImpl().get(i);
        if (iRunningTask == null) {
            return FileDownloadServiceProxy.getImpl().getTotal(i);
        }
        return iRunningTask.getOrigin().getLargeFileTotalBytes();
    }

    public byte getStatusIgnoreCompleted(int i) {
        return getStatus(i, null);
    }

    public byte getStatus(String str, String str2) {
        return getStatus(FileDownloadUtils.generateId(str, str2), str2);
    }

    public byte getStatus(int i, String str) {
        byte status;
        IRunningTask iRunningTask = FileDownloadList.getImpl().get(i);
        if (iRunningTask == null) {
            status = FileDownloadServiceProxy.getImpl().getStatus(i);
        } else {
            status = iRunningTask.getOrigin().getStatus();
        }
        if (str != null && status == (byte) 0 && FileDownloadUtils.isFilenameConverted(FileDownloadHelper.getAppContext()) && new File(str).exists()) {
            return (byte) -3;
        }
        return status;
    }

    public int replaceListener(String str, FileDownloadListener fileDownloadListener) {
        return replaceListener(str, FileDownloadUtils.getDefaultSaveFilePath(str), fileDownloadListener);
    }

    public int replaceListener(String str, String str2, FileDownloadListener fileDownloadListener) {
        return replaceListener(FileDownloadUtils.generateId(str, str2), fileDownloadListener);
    }

    public int replaceListener(int i, FileDownloadListener fileDownloadListener) {
        IRunningTask iRunningTask = FileDownloadList.getImpl().get(i);
        if (iRunningTask == null) {
            return 0;
        }
        iRunningTask.getOrigin().setListener(fileDownloadListener);
        return iRunningTask.getOrigin().getId();
    }

    public void bindService() {
        if (!isServiceConnected()) {
            FileDownloadServiceProxy.getImpl().bindStartByContext(FileDownloadHelper.getAppContext());
        }
    }

    public void bindService(Runnable runnable) {
        if (isServiceConnected()) {
            runnable.run();
        } else {
            FileDownloadServiceProxy.getImpl().bindStartByContext(FileDownloadHelper.getAppContext(), runnable);
        }
    }

    public void unBindService() {
        if (isServiceConnected()) {
            FileDownloadServiceProxy.getImpl().unbindByContext(FileDownloadHelper.getAppContext());
        }
    }

    public boolean unBindServiceIfIdle() {
        if (!isServiceConnected() || !FileDownloadList.getImpl().a() || !FileDownloadServiceProxy.getImpl().isIdle()) {
            return false;
        }
        unBindService();
        return true;
    }

    public boolean isServiceConnected() {
        return FileDownloadServiceProxy.getImpl().isConnected();
    }

    public void addServiceConnectListener(FileDownloadConnectListener fileDownloadConnectListener) {
        FileDownloadEventPool.getImpl().addListener(DownloadServiceConnectChangedEvent.ID, fileDownloadConnectListener);
    }

    public void removeServiceConnectListener(FileDownloadConnectListener fileDownloadConnectListener) {
        FileDownloadEventPool.getImpl().removeListener(DownloadServiceConnectChangedEvent.ID, fileDownloadConnectListener);
    }

    public void startForeground(int i, Notification notification) {
        FileDownloadServiceProxy.getImpl().startForeground(i, notification);
    }

    public void stopForeground(boolean z) {
        FileDownloadServiceProxy.getImpl().stopForeground(z);
    }

    public boolean setTaskCompleted(String str, String str2, long j) {
        FileDownloadLog.w(this, "If you invoked this method, please remove it directly feel free, it doesn't need any longer", new Object[0]);
        return true;
    }

    public boolean setTaskCompleted(List<FileDownloadTaskAtom> list) {
        FileDownloadLog.w(this, "If you invoked this method, please remove it directly feel free, it doesn't need any longer", new Object[0]);
        return true;
    }

    public boolean setMaxNetworkThreadCount(int i) {
        if (FileDownloadList.getImpl().a()) {
            return FileDownloadServiceProxy.getImpl().setMaxNetworkThreadCount(i);
        }
        FileDownloadLog.w(this, "Can't change the max network thread count, because there are actively executing tasks in FileDownloader, please try again after all actively executing tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
        return false;
    }

    public FileDownloadLine insureServiceBind() {
        return new FileDownloadLine();
    }

    public FileDownloadLineAsync insureServiceBindAsync() {
        return new FileDownloadLineAsync();
    }

    IQueuesHandler a() {
        if (this.c == null) {
            synchronized (b) {
                if (this.c == null) {
                    this.c = new p();
                }
            }
        }
        return this.c;
    }

    ILostServiceConnectedHandler b() {
        if (this.e == null) {
            synchronized (d) {
                if (this.e == null) {
                    this.e = new LostServiceConnectedHandler();
                    addServiceConnectListener((FileDownloadConnectListener) this.e);
                }
            }
        }
        return this.e;
    }
}
