package com.liulishuo.filedownloader.event;

import com.liulishuo.filedownloader.g.b;
import com.liulishuo.filedownloader.g.d;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executor;

public class a {
    private final Executor a = b.a(10, "EventPool");
    private final HashMap<String, LinkedList<c>> b = new HashMap();

    public boolean a(String str, c cVar) {
        if (d.a) {
            d.e(this, "setListener %s", str);
        }
        if (cVar == null) {
            throw new IllegalArgumentException("listener must not be null!");
        }
        boolean add;
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
            add = linkedList.add(cVar);
        }
        return add;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.liulishuo.filedownloader.event.b r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r0 = com.liulishuo.filedownloader.g.d.a;
        if (r0 == 0) goto L_0x0014;
    L_0x0006:
        r0 = "publish %s";
        r3 = new java.lang.Object[r2];
        r4 = r7.b();
        r3[r1] = r4;
        com.liulishuo.filedownloader.g.d.e(r6, r0, r3);
    L_0x0014:
        if (r7 != 0) goto L_0x001f;
    L_0x0016:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "event must not be null!";
        r0.<init>(r1);
        throw r0;
    L_0x001f:
        r3 = r7.b();
        r0 = r6.b;
        r0 = r0.get(r3);
        r0 = (java.util.LinkedList) r0;
        if (r0 != 0) goto L_0x0050;
    L_0x002d:
        r4 = r3.intern();
        monitor-enter(r4);
        r0 = r6.b;	 Catch:{ all -> 0x0055 }
        r0 = r0.get(r3);	 Catch:{ all -> 0x0055 }
        r0 = (java.util.LinkedList) r0;	 Catch:{ all -> 0x0055 }
        if (r0 != 0) goto L_0x004f;
    L_0x003c:
        r0 = com.liulishuo.filedownloader.g.d.a;	 Catch:{ all -> 0x0055 }
        if (r0 == 0) goto L_0x004c;
    L_0x0040:
        r0 = "No listener for this event %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0055 }
        r5 = 0;
        r2[r5] = r3;	 Catch:{ all -> 0x0055 }
        com.liulishuo.filedownloader.g.d.c(r6, r0, r2);	 Catch:{ all -> 0x0055 }
    L_0x004c:
        monitor-exit(r4);	 Catch:{ all -> 0x0055 }
        r0 = r1;
    L_0x004e:
        return r0;
    L_0x004f:
        monitor-exit(r4);	 Catch:{ all -> 0x0055 }
    L_0x0050:
        r6.a(r0, r7);
        r0 = r2;
        goto L_0x004e;
    L_0x0055:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0055 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.event.a.a(com.liulishuo.filedownloader.event.b):boolean");
    }

    public void b(final b bVar) {
        if (d.a) {
            d.e(this, "asyncPublishInNewThread %s", bVar.b());
        }
        if (bVar == null) {
            throw new IllegalArgumentException("event must not be null!");
        }
        this.a.execute(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                this.b.a(bVar);
            }
        });
    }

    private void a(LinkedList<c> linkedList, b bVar) {
        for (Object obj : linkedList.toArray()) {
            if (obj != null && ((c) obj).a(bVar)) {
                break;
            }
        }
        if (bVar.a != null) {
            bVar.a.run();
        }
    }
}
