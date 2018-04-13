package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class j {
    static int a = 10;
    static int b = 5;
    private final Executor c;
    private final Handler d;
    private final LinkedBlockingQueue<s> e;
    private final Object f;
    private final ArrayList<s> g;

    private static final class a {
        private static final j a = new j();
    }

    private static class b implements Callback {
        private b() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((s) message.obj).b();
            } else if (message.what == 2) {
                a((ArrayList) message.obj);
                j.a().c();
            }
            return true;
        }

        private void a(ArrayList<s> arrayList) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((s) it.next()).b();
            }
            arrayList.clear();
        }
    }

    public static j a() {
        return a.a;
    }

    private j() {
        this.c = com.liulishuo.filedownloader.g.b.a(5, "BlockCompleted");
        this.f = new Object();
        this.g = new ArrayList();
        this.d = new Handler(Looper.getMainLooper(), new b());
        this.e = new LinkedBlockingQueue();
    }

    void a(s sVar) {
        a(sVar, false);
    }

    void a(final s sVar, boolean z) {
        if (sVar.c()) {
            sVar.b();
        } else if (sVar.d()) {
            this.c.execute(new Runnable(this) {
                final /* synthetic */ j b;

                public void run() {
                    sVar.b();
                }
            });
        } else {
            if (!(b() || this.e.isEmpty())) {
                synchronized (this.f) {
                    if (!this.e.isEmpty()) {
                        Iterator it = this.e.iterator();
                        while (it.hasNext()) {
                            b((s) it.next());
                        }
                    }
                    this.e.clear();
                }
            }
            if (!b() || z) {
                b(sVar);
            } else {
                c(sVar);
            }
        }
    }

    private void b(s sVar) {
        this.d.sendMessage(this.d.obtainMessage(1, sVar));
    }

    private void c(s sVar) {
        synchronized (this.f) {
            this.e.offer(sVar);
        }
        c();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
        r6 = this;
        r0 = 0;
        r2 = r6.f;
        monitor-enter(r2);
        r1 = r6.g;	 Catch:{ all -> 0x0018 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0018 }
        if (r1 != 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
    L_0x000d:
        return;
    L_0x000e:
        r1 = r6.e;	 Catch:{ all -> 0x0018 }
        r1 = r1.isEmpty();	 Catch:{ all -> 0x0018 }
        if (r1 == 0) goto L_0x001b;
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
        goto L_0x000d;
    L_0x0018:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
        throw r0;
    L_0x001b:
        r1 = b();	 Catch:{ all -> 0x0018 }
        if (r1 != 0) goto L_0x0039;
    L_0x0021:
        r1 = r6.e;	 Catch:{ all -> 0x0018 }
        r3 = r6.g;	 Catch:{ all -> 0x0018 }
        r1.drainTo(r3);	 Catch:{ all -> 0x0018 }
    L_0x0028:
        monitor-exit(r2);	 Catch:{ all -> 0x0018 }
        r1 = r6.d;
        r2 = r6.d;
        r3 = 2;
        r4 = r6.g;
        r2 = r2.obtainMessage(r3, r4);
        r4 = (long) r0;
        r1.sendMessageDelayed(r2, r4);
        goto L_0x000d;
    L_0x0039:
        r1 = a;	 Catch:{ all -> 0x0018 }
        r3 = r6.e;	 Catch:{ all -> 0x0018 }
        r3 = r3.size();	 Catch:{ all -> 0x0018 }
        r4 = b;	 Catch:{ all -> 0x0018 }
        r3 = java.lang.Math.min(r3, r4);	 Catch:{ all -> 0x0018 }
    L_0x0047:
        if (r0 >= r3) goto L_0x0057;
    L_0x0049:
        r4 = r6.g;	 Catch:{ all -> 0x0018 }
        r5 = r6.e;	 Catch:{ all -> 0x0018 }
        r5 = r5.remove();	 Catch:{ all -> 0x0018 }
        r4.add(r5);	 Catch:{ all -> 0x0018 }
        r0 = r0 + 1;
        goto L_0x0047;
    L_0x0057:
        r0 = r1;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.j.c():void");
    }

    public static boolean b() {
        return a > 0;
    }
}
