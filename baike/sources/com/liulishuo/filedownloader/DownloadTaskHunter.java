package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.BaseDownloadTask.LifeCycleCallback;
import com.liulishuo.filedownloader.IDownloadSpeed.Lookup;
import com.liulishuo.filedownloader.IDownloadSpeed.Monitor;
import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;
import com.liulishuo.filedownloader.ITaskHunter.IStarter;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot.IWarnMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class DownloadTaskHunter implements LifeCycleCallback, ITaskHunter, IMessageHandler, IStarter {
    private o a;
    private final Object b;
    private final a c;
    private volatile byte d = (byte) 0;
    private Throwable e = null;
    private final Monitor f;
    private final Lookup g;
    private long h;
    private long i;
    private int j;
    private boolean k;
    private boolean l;
    private String m;
    private boolean n = false;

    interface a {
        ArrayList<FinishListener> getFinishListenerList();

        FileDownloadHeader getHeader();

        IRunningTask getRunningTask();

        void setFileName(String str);
    }

    public boolean updateKeepAhead(MessageSnapshot messageSnapshot) {
        if (FileDownloadStatus.isKeepAhead(getStatus(), messageSnapshot.getStatus())) {
            a(messageSnapshot);
            return true;
        } else if (!FileDownloadLog.NEED_LOG) {
            return false;
        } else {
            FileDownloadLog.d(this, "can't update mStatus change by keep ahead, %d, but the current mStatus is %d, %d", new Object[]{Byte.valueOf(this.d), Byte.valueOf(getStatus()), Integer.valueOf(b())});
            return false;
        }
    }

    public boolean updateKeepFlow(MessageSnapshot messageSnapshot) {
        byte status = getStatus();
        byte status2 = messageSnapshot.getStatus();
        if ((byte) -2 == status && FileDownloadStatus.isIng(status2)) {
            if (!FileDownloadLog.NEED_LOG) {
                return true;
            }
            FileDownloadLog.d(this, "High concurrent cause, callback pending, but has already be paused %d", new Object[]{Integer.valueOf(b())});
            return true;
        } else if (FileDownloadStatus.isKeepFlow(status, status2)) {
            a(messageSnapshot);
            return true;
        } else {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "can't update mStatus change by keep flow, %d, but the current mStatus is %d, %d", new Object[]{Byte.valueOf(this.d), Byte.valueOf(getStatus()), Integer.valueOf(b())});
            }
            return false;
        }
    }

    public boolean updateMoreLikelyCompleted(MessageSnapshot messageSnapshot) {
        if (!FileDownloadStatus.isMoreLikelyCompleted(this.c.getRunningTask().getOrigin())) {
            return false;
        }
        a(messageSnapshot);
        return true;
    }

    public boolean updateSameFilePathTaskRunning(MessageSnapshot messageSnapshot) {
        if (!this.c.getRunningTask().getOrigin().isPathAsDirectory() || messageSnapshot.getStatus() != (byte) -4 || getStatus() != (byte) 2) {
            return false;
        }
        a(messageSnapshot);
        return true;
    }

    public o getMessenger() {
        return this.a;
    }

    public MessageSnapshot prepareErrorMessage(Throwable th) {
        this.d = (byte) -1;
        this.e = th;
        return MessageSnapshotTaker.catchException(b(), getSofarBytes(), th);
    }

    private void a(MessageSnapshot messageSnapshot) {
        BaseDownloadTask origin = this.c.getRunningTask().getOrigin();
        byte status = messageSnapshot.getStatus();
        this.d = status;
        this.k = messageSnapshot.isLargeFile();
        switch (status) {
            case (byte) -4:
                int i;
                this.f.reset();
                int a = FileDownloadList.getImpl().a(origin.getId());
                if (a > 1 || !origin.isPathAsDirectory()) {
                    i = 0;
                } else {
                    i = FileDownloadList.getImpl().a(FileDownloadUtils.generateId(origin.getUrl(), origin.getTargetFilePath()));
                }
                if (i + a <= 1) {
                    FileDownloadLog.w(this, "warn, but no mListener to receive, switch to pending %d %d", new Object[]{Integer.valueOf(origin.getId()), Integer.valueOf(FileDownloadServiceProxy.getImpl().getStatus(origin.getId()))});
                    if (FileDownloadStatus.isIng(FileDownloadServiceProxy.getImpl().getStatus(origin.getId()))) {
                        this.d = (byte) 1;
                        this.i = messageSnapshot.getLargeTotalBytes();
                        this.h = messageSnapshot.getLargeSofarBytes();
                        this.f.start();
                        this.a.notifyPending(((IWarnMessageSnapshot) messageSnapshot).turnToPending());
                        return;
                    }
                }
                FileDownloadList.getImpl().remove(this.c.getRunningTask(), messageSnapshot);
                return;
            case (byte) -3:
                this.n = messageSnapshot.isReusedDownloadedFile();
                this.h = messageSnapshot.getLargeTotalBytes();
                this.i = messageSnapshot.getLargeTotalBytes();
                this.f.end(this.h);
                FileDownloadList.getImpl().remove(this.c.getRunningTask(), messageSnapshot);
                return;
            case (byte) -1:
                this.e = messageSnapshot.getThrowable();
                this.h = messageSnapshot.getLargeSofarBytes();
                this.f.end(this.h);
                FileDownloadList.getImpl().remove(this.c.getRunningTask(), messageSnapshot);
                return;
            case (byte) 1:
                this.h = messageSnapshot.getLargeSofarBytes();
                this.i = messageSnapshot.getLargeTotalBytes();
                this.a.notifyPending(messageSnapshot);
                return;
            case (byte) 2:
                this.i = messageSnapshot.getLargeTotalBytes();
                this.l = messageSnapshot.isResuming();
                this.m = messageSnapshot.getEtag();
                String fileName = messageSnapshot.getFileName();
                if (fileName != null) {
                    if (origin.getFilename() != null) {
                        FileDownloadLog.w(this, "already has mFilename[%s], but assign mFilename[%s] again", new Object[]{origin.getFilename(), fileName});
                    }
                    this.c.setFileName(fileName);
                }
                this.f.start();
                this.a.notifyConnected(messageSnapshot);
                return;
            case (byte) 3:
                this.h = messageSnapshot.getLargeSofarBytes();
                this.f.update(messageSnapshot.getLargeSofarBytes());
                this.a.notifyProgress(messageSnapshot);
                return;
            case (byte) 5:
                this.h = messageSnapshot.getLargeSofarBytes();
                this.e = messageSnapshot.getThrowable();
                this.j = messageSnapshot.getRetryingTimes();
                this.f.reset();
                this.a.notifyRetry(messageSnapshot);
                return;
            case (byte) 6:
                this.a.notifyStarted(messageSnapshot);
                return;
            default:
                return;
        }
    }

    public void onBegin() {
        if (FileDownloadMonitor.isValid()) {
            FileDownloadMonitor.getMonitor().onTaskBegin(this.c.getRunningTask().getOrigin());
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "filedownloader:lifecycle:start %s by %d ", new Object[]{toString(), Byte.valueOf(getStatus())});
        }
    }

    public void onIng() {
        if (FileDownloadMonitor.isValid() && getStatus() == (byte) 6) {
            FileDownloadMonitor.getMonitor().onTaskStarted(this.c.getRunningTask().getOrigin());
        }
    }

    public void onOver() {
        BaseDownloadTask origin = this.c.getRunningTask().getOrigin();
        if (FileDownloadMonitor.isValid()) {
            FileDownloadMonitor.getMonitor().onTaskOver(origin);
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "filedownloader:lifecycle:over %s by %d ", new Object[]{toString(), Byte.valueOf(getStatus())});
        }
        if (this.c.getFinishListenerList() != null) {
            ArrayList arrayList = (ArrayList) this.c.getFinishListenerList().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((FinishListener) arrayList.get(i)).over(origin);
            }
        }
        FileDownloader.getImpl().b().taskWorkFine(this.c.getRunningTask());
    }

    DownloadTaskHunter(a aVar, Object obj) {
        this.b = obj;
        this.c = aVar;
        Object downloadSpeedMonitor = new DownloadSpeedMonitor();
        this.f = downloadSpeedMonitor;
        this.g = downloadSpeedMonitor;
        this.a = new j(aVar.getRunningTask(), this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void intoLaunchPool() {
        /*
        r8 = this;
        r7 = 2;
        r1 = 1;
        r2 = 0;
        r3 = r8.b;
        monitor-enter(r3);
        r0 = r8.d;	 Catch:{ all -> 0x008c }
        if (r0 == 0) goto L_0x0028;
    L_0x000a:
        r0 = "High concurrent cause, this task %d will not input to launch pool, because of the status isn't idle : %d";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x008c }
        r2 = 0;
        r4 = r8.b();	 Catch:{ all -> 0x008c }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x008c }
        r1[r2] = r4;	 Catch:{ all -> 0x008c }
        r2 = 1;
        r4 = r8.d;	 Catch:{ all -> 0x008c }
        r4 = java.lang.Byte.valueOf(r4);	 Catch:{ all -> 0x008c }
        r1[r2] = r4;	 Catch:{ all -> 0x008c }
        com.liulishuo.filedownloader.util.FileDownloadLog.w(r8, r0, r1);	 Catch:{ all -> 0x008c }
        monitor-exit(r3);	 Catch:{ all -> 0x008c }
    L_0x0027:
        return;
    L_0x0028:
        r0 = 10;
        r8.d = r0;	 Catch:{ all -> 0x008c }
        monitor-exit(r3);	 Catch:{ all -> 0x008c }
        r0 = r8.c;
        r0 = r0.getRunningTask();
        r3 = r0.getOrigin();
        r4 = com.liulishuo.filedownloader.FileDownloadMonitor.isValid();
        if (r4 == 0) goto L_0x0044;
    L_0x003d:
        r4 = com.liulishuo.filedownloader.FileDownloadMonitor.getMonitor();
        r4.onRequestStart(r3);
    L_0x0044:
        r4 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG;
        if (r4 == 0) goto L_0x0069;
    L_0x0048:
        r4 = "call start Url[%s], Path[%s] Listener[%s], Tag[%s]";
        r5 = 4;
        r5 = new java.lang.Object[r5];
        r6 = r3.getUrl();
        r5[r2] = r6;
        r6 = r3.getPath();
        r5[r1] = r6;
        r6 = r3.getListener();
        r5[r7] = r6;
        r6 = 3;
        r3 = r3.getTag();
        r5[r6] = r3;
        com.liulishuo.filedownloader.util.FileDownloadLog.v(r8, r4, r5);
    L_0x0069:
        r8.a();	 Catch:{ Throwable -> 0x008f }
        r0 = r1;
    L_0x006d:
        if (r0 == 0) goto L_0x0076;
    L_0x006f:
        r0 = com.liulishuo.filedownloader.m.getImpl();
        r0.a(r8);
    L_0x0076:
        r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG;
        if (r0 == 0) goto L_0x0027;
    L_0x007a:
        r0 = "the task[%d] has been into the launch pool.";
        r1 = new java.lang.Object[r1];
        r3 = r8.b();
        r3 = java.lang.Integer.valueOf(r3);
        r1[r2] = r3;
        com.liulishuo.filedownloader.util.FileDownloadLog.v(r8, r0, r1);
        goto L_0x0027;
    L_0x008c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x008c }
        throw r0;
    L_0x008f:
        r3 = move-exception;
        r4 = com.liulishuo.filedownloader.FileDownloadList.getImpl();
        r4.b(r0);
        r4 = com.liulishuo.filedownloader.FileDownloadList.getImpl();
        r3 = r8.prepareErrorMessage(r3);
        r4.remove(r0, r3);
        r0 = r2;
        goto L_0x006d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.DownloadTaskHunter.intoLaunchPool():void");
    }

    public boolean pause() {
        if (!FileDownloadStatus.isOver(getStatus())) {
            this.d = (byte) -2;
            IRunningTask runningTask = this.c.getRunningTask();
            BaseDownloadTask origin = runningTask.getOrigin();
            m.getImpl().b(this);
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.v(this, "the task[%d] has been expired from the launch pool.", new Object[]{Integer.valueOf(b())});
            }
            if (FileDownloader.getImpl().isServiceConnected()) {
                FileDownloadServiceProxy.getImpl().pause(origin.getId());
            } else if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "request pause the task[%d] to the download service, but the download service isn't connected yet.", new Object[]{Integer.valueOf(origin.getId())});
            }
            this.f.end(this.h);
            FileDownloadList.getImpl().b(runningTask);
            FileDownloadList.getImpl().remove(runningTask, MessageSnapshotTaker.catchPause(origin));
            FileDownloader.getImpl().b().taskWorkFine(runningTask);
            return true;
        } else if (!FileDownloadLog.NEED_LOG) {
            return false;
        } else {
            FileDownloadLog.d(this, "High concurrent cause, Already is over, can't pause again, %d %d", new Object[]{Byte.valueOf(getStatus()), Integer.valueOf(this.c.getRunningTask().getOrigin().getId())});
            return false;
        }
    }

    public byte getStatus() {
        return this.d;
    }

    public void reset() {
        this.e = null;
        this.m = null;
        this.l = false;
        this.j = 0;
        this.n = false;
        this.k = false;
        this.h = 0;
        this.i = 0;
        this.f.reset();
        if (FileDownloadStatus.isOver(this.d)) {
            this.a.discard();
            this.a = new j(this.c.getRunningTask(), this);
        } else {
            this.a.reAppointment(this.c.getRunningTask(), this);
        }
        this.d = (byte) 0;
    }

    public void setMinIntervalUpdateSpeed(int i) {
        this.g.setMinIntervalUpdateSpeed(i);
    }

    public int getSpeed() {
        return this.g.getSpeed();
    }

    public long getSofarBytes() {
        return this.h;
    }

    public long getTotalBytes() {
        return this.i;
    }

    public Throwable getErrorCause() {
        return this.e;
    }

    public int getRetryingTimes() {
        return this.j;
    }

    public boolean isReusedOldFile() {
        return this.n;
    }

    public boolean isResuming() {
        return this.l;
    }

    public String getEtag() {
        return this.m;
    }

    public boolean isLargeFile() {
        return this.k;
    }

    public void free() {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "free the task %d, when the status is %d", new Object[]{Integer.valueOf(b()), Byte.valueOf(this.d)});
        }
        this.d = (byte) 0;
    }

    private void a() {
        File file;
        BaseDownloadTask origin = this.c.getRunningTask().getOrigin();
        if (origin.getPath() == null) {
            origin.setPath(FileDownloadUtils.getDefaultSaveFilePath(origin.getUrl()));
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "save Path is null to %s", new Object[]{origin.getPath()});
            }
        }
        if (origin.isPathAsDirectory()) {
            file = new File(origin.getPath());
        } else {
            String parent = FileDownloadUtils.getParent(origin.getPath());
            if (parent == null) {
                throw new InvalidParameterException(FileDownloadUtils.formatString("the provided mPath[%s] is invalid, can't find its directory", new Object[]{origin.getPath()}));
            }
            file = new File(parent);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private int b() {
        return this.c.getRunningTask().getOrigin().getId();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void start() {
        /*
        r12 = this;
        r2 = 10;
        r1 = 2;
        r4 = 0;
        r3 = 1;
        r0 = r12.d;
        if (r0 == r2) goto L_0x0023;
    L_0x0009:
        r0 = "High concurrent cause, this task %d will not start, because the of status isn't toLaunchPool: %d";
        r1 = new java.lang.Object[r1];
        r2 = r12.b();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = r12.d;
        r2 = java.lang.Byte.valueOf(r2);
        r1[r3] = r2;
        com.liulishuo.filedownloader.util.FileDownloadLog.w(r12, r0, r1);
    L_0x0022:
        return;
    L_0x0023:
        r0 = r12.c;
        r10 = r0.getRunningTask();
        r9 = r10.getOrigin();
        r0 = com.liulishuo.filedownloader.FileDownloader.getImpl();
        r11 = r0.b();
        r0 = r11.dispatchTaskStart(r10);	 Catch:{ Throwable -> 0x0063 }
        if (r0 != 0) goto L_0x0022;
    L_0x003b:
        r1 = r12.b;	 Catch:{ Throwable -> 0x0063 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0063 }
        r0 = r12.d;	 Catch:{ all -> 0x0060 }
        if (r0 == r2) goto L_0x0073;
    L_0x0042:
        r0 = "High concurrent cause, this task %d will not start, the status can't assign to toFileDownloadService, because the status isn't toLaunchPool: %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0060 }
        r3 = 0;
        r4 = r12.b();	 Catch:{ all -> 0x0060 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0060 }
        r2[r3] = r4;	 Catch:{ all -> 0x0060 }
        r3 = 1;
        r4 = r12.d;	 Catch:{ all -> 0x0060 }
        r4 = java.lang.Byte.valueOf(r4);	 Catch:{ all -> 0x0060 }
        r2[r3] = r4;	 Catch:{ all -> 0x0060 }
        com.liulishuo.filedownloader.util.FileDownloadLog.w(r12, r0, r2);	 Catch:{ all -> 0x0060 }
        monitor-exit(r1);	 Catch:{ all -> 0x0060 }
        goto L_0x0022;
    L_0x0060:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0060 }
        throw r0;	 Catch:{ Throwable -> 0x0063 }
    L_0x0063:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = com.liulishuo.filedownloader.FileDownloadList.getImpl();
        r0 = r12.prepareErrorMessage(r0);
        r1.remove(r10, r0);
        goto L_0x0022;
    L_0x0073:
        r0 = 11;
        r12.d = r0;	 Catch:{ all -> 0x0060 }
        monitor-exit(r1);	 Catch:{ all -> 0x0060 }
        r0 = com.liulishuo.filedownloader.FileDownloadList.getImpl();	 Catch:{ Throwable -> 0x0063 }
        r0.b(r10);	 Catch:{ Throwable -> 0x0063 }
        r0 = r9.getId();	 Catch:{ Throwable -> 0x0063 }
        r1 = r9.getTargetFilePath();	 Catch:{ Throwable -> 0x0063 }
        r2 = r9.isForceReDownload();	 Catch:{ Throwable -> 0x0063 }
        r3 = 1;
        r0 = com.liulishuo.filedownloader.util.FileDownloadHelper.inspectAndInflowDownloaded(r0, r1, r2, r3);	 Catch:{ Throwable -> 0x0063 }
        if (r0 != 0) goto L_0x0022;
    L_0x0092:
        r0 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl();	 Catch:{ Throwable -> 0x0063 }
        r1 = r9.getUrl();	 Catch:{ Throwable -> 0x0063 }
        r2 = r9.getPath();	 Catch:{ Throwable -> 0x0063 }
        r3 = r9.isPathAsDirectory();	 Catch:{ Throwable -> 0x0063 }
        r4 = r9.getCallbackProgressTimes();	 Catch:{ Throwable -> 0x0063 }
        r5 = r9.getCallbackProgressMinInterval();	 Catch:{ Throwable -> 0x0063 }
        r6 = r9.getAutoRetryTimes();	 Catch:{ Throwable -> 0x0063 }
        r7 = r9.isForceReDownload();	 Catch:{ Throwable -> 0x0063 }
        r8 = r12.c;	 Catch:{ Throwable -> 0x0063 }
        r8 = r8.getHeader();	 Catch:{ Throwable -> 0x0063 }
        r9 = r9.isWifiRequired();	 Catch:{ Throwable -> 0x0063 }
        r0 = r0.start(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x0063 }
        r1 = r12.d;	 Catch:{ Throwable -> 0x0063 }
        r2 = -2;
        if (r1 != r2) goto L_0x00e7;
    L_0x00c5:
        r1 = "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0063 }
        r3 = 0;
        r4 = r12.b();	 Catch:{ Throwable -> 0x0063 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0063 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0063 }
        com.liulishuo.filedownloader.util.FileDownloadLog.w(r12, r1, r2);	 Catch:{ Throwable -> 0x0063 }
        if (r0 == 0) goto L_0x0022;
    L_0x00da:
        r0 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl();	 Catch:{ Throwable -> 0x0063 }
        r1 = r12.b();	 Catch:{ Throwable -> 0x0063 }
        r0.pause(r1);	 Catch:{ Throwable -> 0x0063 }
        goto L_0x0022;
    L_0x00e7:
        if (r0 != 0) goto L_0x0117;
    L_0x00e9:
        r0 = r11.dispatchTaskStart(r10);	 Catch:{ Throwable -> 0x0063 }
        if (r0 != 0) goto L_0x0022;
    L_0x00ef:
        r0 = new java.lang.RuntimeException;	 Catch:{ Throwable -> 0x0063 }
        r1 = "Occur Unknow Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected.";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0063 }
        r0 = r12.prepareErrorMessage(r0);	 Catch:{ Throwable -> 0x0063 }
        r1 = com.liulishuo.filedownloader.FileDownloadList.getImpl();	 Catch:{ Throwable -> 0x0063 }
        r1 = r1.a(r10);	 Catch:{ Throwable -> 0x0063 }
        if (r1 == 0) goto L_0x010e;
    L_0x0104:
        r11.taskWorkFine(r10);	 Catch:{ Throwable -> 0x0063 }
        r1 = com.liulishuo.filedownloader.FileDownloadList.getImpl();	 Catch:{ Throwable -> 0x0063 }
        r1.b(r10);	 Catch:{ Throwable -> 0x0063 }
    L_0x010e:
        r1 = com.liulishuo.filedownloader.FileDownloadList.getImpl();	 Catch:{ Throwable -> 0x0063 }
        r1.remove(r10, r0);	 Catch:{ Throwable -> 0x0063 }
        goto L_0x0022;
    L_0x0117:
        r11.taskWorkFine(r10);	 Catch:{ Throwable -> 0x0063 }
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.DownloadTaskHunter.start():void");
    }

    public boolean equalListener(FileDownloadListener fileDownloadListener) {
        return this.c.getRunningTask().getOrigin().getListener() == fileDownloadListener;
    }
}
