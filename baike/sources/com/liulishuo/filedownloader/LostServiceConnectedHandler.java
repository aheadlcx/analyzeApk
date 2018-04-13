package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LostServiceConnectedHandler extends FileDownloadConnectListener implements ILostServiceConnectedHandler {
    private final ArrayList<IRunningTask> a = new ArrayList();

    public void connected() {
        IQueuesHandler a = FileDownloader.getImpl().a();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "The downloader service is connected.", new Object[0]);
        }
        synchronized (this.a) {
            List<IRunningTask> list = (List) this.a.clone();
            this.a.clear();
            List arrayList = new ArrayList(a.serialQueueSize());
            for (IRunningTask iRunningTask : list) {
                int attachKey = iRunningTask.getAttachKey();
                if (a.contain(attachKey)) {
                    iRunningTask.getOrigin().asInQueueTask().enqueue();
                    if (!arrayList.contains(Integer.valueOf(attachKey))) {
                        arrayList.add(Integer.valueOf(attachKey));
                    }
                } else {
                    iRunningTask.startTaskByRescue();
                }
            }
            a.unFreezeSerialQueues(arrayList);
        }
    }

    public void disconnected() {
        if (getConnectStatus() == ConnectStatus.lost) {
            IQueuesHandler a = FileDownloader.getImpl().a();
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "lost the connection to the file download service, and current active task size is %d", new Object[]{Integer.valueOf(FileDownloadList.getImpl().b())});
            }
            if (FileDownloadList.getImpl().b() > 0) {
                synchronized (this.a) {
                    FileDownloadList.getImpl().a(this.a);
                    Iterator it = this.a.iterator();
                    while (it.hasNext()) {
                        ((IRunningTask) it.next()).free();
                    }
                    a.freezeAllSerialQueues();
                }
                FileDownloader.getImpl().bindService();
            }
        } else if (FileDownloadList.getImpl().b() > 0) {
            FileDownloadLog.w(this, "file download service has be unbound but the size of active tasks are not empty %d ", new Object[]{Integer.valueOf(FileDownloadList.getImpl().b())});
        }
    }

    public boolean isInWaitingList(IRunningTask iRunningTask) {
        return !this.a.isEmpty() && this.a.contains(iRunningTask);
    }

    public void taskWorkFine(IRunningTask iRunningTask) {
        if (!this.a.isEmpty()) {
            synchronized (this.a) {
                this.a.remove(iRunningTask);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTaskStart(com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask r7) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r2 = com.liulishuo.filedownloader.FileDownloader.getImpl();
        r2 = r2.isServiceConnected();
        if (r2 != 0) goto L_0x0052;
    L_0x000c:
        r2 = r6.a;
        monitor-enter(r2);
        r3 = com.liulishuo.filedownloader.FileDownloader.getImpl();	 Catch:{ all -> 0x0057 }
        r3 = r3.isServiceConnected();	 Catch:{ all -> 0x0057 }
        if (r3 != 0) goto L_0x0051;
    L_0x0019:
        r1 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG;	 Catch:{ all -> 0x0057 }
        if (r1 == 0) goto L_0x0034;
    L_0x001d:
        r1 = "Waiting for connecting with the downloader service... %d";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0057 }
        r4 = 0;
        r5 = r7.getOrigin();	 Catch:{ all -> 0x0057 }
        r5 = r5.getId();	 Catch:{ all -> 0x0057 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0057 }
        r3[r4] = r5;	 Catch:{ all -> 0x0057 }
        com.liulishuo.filedownloader.util.FileDownloadLog.d(r6, r1, r3);	 Catch:{ all -> 0x0057 }
    L_0x0034:
        r1 = com.liulishuo.filedownloader.FileDownloadServiceProxy.getImpl();	 Catch:{ all -> 0x0057 }
        r3 = com.liulishuo.filedownloader.util.FileDownloadHelper.getAppContext();	 Catch:{ all -> 0x0057 }
        r1.bindStartByContext(r3);	 Catch:{ all -> 0x0057 }
        r1 = r6.a;	 Catch:{ all -> 0x0057 }
        r1 = r1.contains(r7);	 Catch:{ all -> 0x0057 }
        if (r1 != 0) goto L_0x004f;
    L_0x0047:
        r7.free();	 Catch:{ all -> 0x0057 }
        r1 = r6.a;	 Catch:{ all -> 0x0057 }
        r1.add(r7);	 Catch:{ all -> 0x0057 }
    L_0x004f:
        monitor-exit(r2);	 Catch:{ all -> 0x0057 }
    L_0x0050:
        return r0;
    L_0x0051:
        monitor-exit(r2);	 Catch:{ all -> 0x0057 }
    L_0x0052:
        r6.taskWorkFine(r7);
        r0 = r1;
        goto L_0x0050;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0057 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.LostServiceConnectedHandler.dispatchTaskStart(com.liulishuo.filedownloader.BaseDownloadTask$IRunningTask):boolean");
    }
}
