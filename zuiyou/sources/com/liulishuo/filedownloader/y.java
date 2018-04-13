package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.a.b;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.g.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class y extends e implements u {
    private final ArrayList<b> a = new ArrayList();

    public void a() {
        v d = q.a().d();
        if (d.a) {
            d.c(this, "The downloader service is connected.", new Object[0]);
        }
        synchronized (this.a) {
            List<b> list = (List) this.a.clone();
            this.a.clear();
            List arrayList = new ArrayList(d.b());
            for (b bVar : list) {
                int B = bVar.B();
                if (d.a(B)) {
                    bVar.y().a().a();
                    if (!arrayList.contains(Integer.valueOf(B))) {
                        arrayList.add(Integer.valueOf(B));
                    }
                } else {
                    bVar.G();
                }
            }
            d.a(arrayList);
        }
    }

    public void b() {
        if (c() == ConnectStatus.lost) {
            v d = q.a().d();
            if (d.a) {
                d.c(this, "lost the connection to the file download service, and current active task size is %d", Integer.valueOf(h.a().c()));
            }
            if (h.a().c() > 0) {
                synchronized (this.a) {
                    h.a().a(this.a);
                    Iterator it = this.a.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).F();
                    }
                    d.a();
                }
                q.a().b();
            }
        } else if (h.a().c() > 0) {
            d.d(this, "file download service has be unbound but the size of active tasks are not empty %d ", Integer.valueOf(h.a().c()));
        }
    }

    public boolean a(b bVar) {
        return !this.a.isEmpty() && this.a.contains(bVar);
    }

    public void b(b bVar) {
        if (!this.a.isEmpty()) {
            synchronized (this.a) {
                this.a.remove(bVar);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(com.liulishuo.filedownloader.a.b r7) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r2 = com.liulishuo.filedownloader.q.a();
        r2 = r2.c();
        if (r2 != 0) goto L_0x0053;
    L_0x000c:
        r2 = r6.a;
        monitor-enter(r2);
        r3 = com.liulishuo.filedownloader.q.a();	 Catch:{ all -> 0x0058 }
        r3 = r3.c();	 Catch:{ all -> 0x0058 }
        if (r3 != 0) goto L_0x0052;
    L_0x0019:
        r1 = com.liulishuo.filedownloader.g.d.a;	 Catch:{ all -> 0x0058 }
        if (r1 == 0) goto L_0x0035;
    L_0x001d:
        r1 = "Waiting for connecting with the downloader service... %d";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0058 }
        r4 = 0;
        r5 = r7.y();	 Catch:{ all -> 0x0058 }
        r5 = r5.d();	 Catch:{ all -> 0x0058 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x0058 }
        r3[r4] = r5;	 Catch:{ all -> 0x0058 }
        com.liulishuo.filedownloader.g.d.c(r6, r1, r3);	 Catch:{ all -> 0x0058 }
    L_0x0035:
        r1 = com.liulishuo.filedownloader.m.a();	 Catch:{ all -> 0x0058 }
        r3 = com.liulishuo.filedownloader.g.c.a();	 Catch:{ all -> 0x0058 }
        r1.a(r3);	 Catch:{ all -> 0x0058 }
        r1 = r6.a;	 Catch:{ all -> 0x0058 }
        r1 = r1.contains(r7);	 Catch:{ all -> 0x0058 }
        if (r1 != 0) goto L_0x0050;
    L_0x0048:
        r7.F();	 Catch:{ all -> 0x0058 }
        r1 = r6.a;	 Catch:{ all -> 0x0058 }
        r1.add(r7);	 Catch:{ all -> 0x0058 }
    L_0x0050:
        monitor-exit(r2);	 Catch:{ all -> 0x0058 }
    L_0x0051:
        return r0;
    L_0x0052:
        monitor-exit(r2);	 Catch:{ all -> 0x0058 }
    L_0x0053:
        r6.b(r7);
        r0 = r1;
        goto L_0x0051;
    L_0x0058:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0058 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.y.c(com.liulishuo.filedownloader.a$b):boolean");
    }
}
