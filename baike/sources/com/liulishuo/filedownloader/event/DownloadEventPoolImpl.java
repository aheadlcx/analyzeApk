package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import junit.framework.Assert;

public class DownloadEventPoolImpl {
    private final Executor a = FileDownloadExecutors.newDefaultThreadPool(10, "EventPool");
    private final HashMap<String, LinkedList<IDownloadListener>> b = new HashMap();

    public boolean addListener(String str, IDownloadListener iDownloadListener) {
        boolean add;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "setListener %s", new Object[]{str});
        }
        Assert.assertNotNull("EventPoolImpl.add", iDownloadListener);
        LinkedList linkedList = (LinkedList) this.b.get(str);
        if (linkedList == null) {
            synchronized (str.intern()) {
                linkedList = (LinkedList) this.b.get(str);
                if (linkedList == null) {
                    HashMap hashMap = this.b;
                    linkedList = new LinkedList();
                    hashMap.put(str, linkedList);
                }
            }
        }
        synchronized (str.intern()) {
            add = linkedList.add(iDownloadListener);
        }
        return add;
    }

    public boolean removeListener(String str, IDownloadListener iDownloadListener) {
        LinkedList linkedList;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "removeListener %s", new Object[]{str});
        }
        LinkedList linkedList2 = (LinkedList) this.b.get(str);
        if (linkedList2 == null) {
            synchronized (str.intern()) {
                linkedList2 = (LinkedList) this.b.get(str);
            }
            linkedList = linkedList2;
        } else {
            linkedList = linkedList2;
        }
        if (linkedList == null || iDownloadListener == null) {
            return false;
        }
        boolean remove;
        synchronized (str.intern()) {
            remove = linkedList.remove(iDownloadListener);
            if (linkedList.size() <= 0) {
                this.b.remove(str);
            }
        }
        return remove;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean publish(com.liulishuo.filedownloader.event.IDownloadEvent r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG;
        if (r0 == 0) goto L_0x0013;
    L_0x0006:
        r0 = "publish %s";
        r3 = new java.lang.Object[r2];
        r4 = r7.getId();
        r3[r1] = r4;
        com.liulishuo.filedownloader.util.FileDownloadLog.v(r6, r0, r3);
    L_0x0013:
        r0 = "EventPoolImpl.publish";
        junit.framework.Assert.assertNotNull(r0, r7);
        r3 = r7.getId();
        r0 = r6.b;
        r0 = r0.get(r3);
        r0 = (java.util.LinkedList) r0;
        if (r0 != 0) goto L_0x0048;
    L_0x0026:
        r4 = r3.intern();
        monitor-enter(r4);
        r0 = r6.b;	 Catch:{ all -> 0x004d }
        r0 = r0.get(r3);	 Catch:{ all -> 0x004d }
        r0 = (java.util.LinkedList) r0;	 Catch:{ all -> 0x004d }
        if (r0 != 0) goto L_0x0047;
    L_0x0035:
        r0 = com.liulishuo.filedownloader.util.FileDownloadLog.NEED_LOG;	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x0044;
    L_0x0039:
        r0 = "No listener for this event %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x004d }
        r5 = 0;
        r2[r5] = r3;	 Catch:{ all -> 0x004d }
        com.liulishuo.filedownloader.util.FileDownloadLog.d(r6, r0, r2);	 Catch:{ all -> 0x004d }
    L_0x0044:
        monitor-exit(r4);	 Catch:{ all -> 0x004d }
        r0 = r1;
    L_0x0046:
        return r0;
    L_0x0047:
        monitor-exit(r4);	 Catch:{ all -> 0x004d }
    L_0x0048:
        r6.a(r0, r7);
        r0 = r2;
        goto L_0x0046;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x004d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.event.DownloadEventPoolImpl.publish(com.liulishuo.filedownloader.event.IDownloadEvent):boolean");
    }

    public void asyncPublishInNewThread(IDownloadEvent iDownloadEvent) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.v(this, "asyncPublishInNewThread %s", new Object[]{iDownloadEvent.getId()});
        }
        Assert.assertNotNull("EventPoolImpl.asyncPublish event", iDownloadEvent);
        this.a.execute(new a(this, iDownloadEvent));
    }

    private void a(LinkedList<IDownloadListener> linkedList, IDownloadEvent iDownloadEvent) {
        Object[] toArray = linkedList.toArray();
        int length = toArray.length;
        int i = 0;
        while (i < length && !((IDownloadListener) toArray[i]).callback(iDownloadEvent)) {
            i++;
        }
        if (iDownloadEvent.callback != null) {
            iDownloadEvent.callback.run();
        }
    }
}
