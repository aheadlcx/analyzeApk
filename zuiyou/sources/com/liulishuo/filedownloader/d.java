package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.f;
import com.liulishuo.filedownloader.w.b;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class d implements com.liulishuo.filedownloader.a.d, w, com.liulishuo.filedownloader.w.a, b {
    private s a;
    private final Object b;
    private final a c;
    private volatile byte d = (byte) 0;
    private Throwable e = null;
    private final r.b f;
    private final com.liulishuo.filedownloader.r.a g;
    private long h;
    private long i;
    private int j;
    private boolean k;
    private boolean l;
    private String m;
    private boolean n = false;

    interface a {
        com.liulishuo.filedownloader.d.b L();

        a.b M();

        ArrayList<com.liulishuo.filedownloader.a.a> N();

        void b(String str);
    }

    public boolean a(MessageSnapshot messageSnapshot) {
        if (com.liulishuo.filedownloader.d.d.a(f(), messageSnapshot.b())) {
            e(messageSnapshot);
            return true;
        } else if (!com.liulishuo.filedownloader.g.d.a) {
            return false;
        } else {
            com.liulishuo.filedownloader.g.d.c(this, "can't update mStatus change by keep ahead, %d, but the current mStatus is %d, %d", Byte.valueOf(this.d), Byte.valueOf(f()), Integer.valueOf(n()));
            return false;
        }
    }

    public boolean b(MessageSnapshot messageSnapshot) {
        byte f = f();
        byte b = messageSnapshot.b();
        if ((byte) -2 == f && com.liulishuo.filedownloader.d.d.b(b)) {
            if (!com.liulishuo.filedownloader.g.d.a) {
                return true;
            }
            com.liulishuo.filedownloader.g.d.c(this, "High concurrent cause, callback pending, but has already be paused %d", Integer.valueOf(n()));
            return true;
        } else if (com.liulishuo.filedownloader.d.d.b(f, b)) {
            e(messageSnapshot);
            return true;
        } else {
            if (com.liulishuo.filedownloader.g.d.a) {
                com.liulishuo.filedownloader.g.d.c(this, "can't update mStatus change by keep flow, %d, but the current mStatus is %d, %d", Byte.valueOf(this.d), Byte.valueOf(f()), Integer.valueOf(n()));
            }
            return false;
        }
    }

    public boolean c(MessageSnapshot messageSnapshot) {
        if (!com.liulishuo.filedownloader.d.d.a(this.c.M().y())) {
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    public boolean d(MessageSnapshot messageSnapshot) {
        if (!this.c.M().y().i() || messageSnapshot.b() != (byte) -4 || f() != (byte) 2) {
            return false;
        }
        e(messageSnapshot);
        return true;
    }

    public s d() {
        return this.a;
    }

    public MessageSnapshot a(Throwable th) {
        this.d = (byte) -1;
        this.e = th;
        return f.a(n(), g(), th);
    }

    private void e(MessageSnapshot messageSnapshot) {
        a y = this.c.M().y();
        byte b = messageSnapshot.b();
        this.d = b;
        this.k = messageSnapshot.n();
        switch (b) {
            case (byte) -4:
                int i;
                this.f.a();
                int a = h.a().a(y.d());
                if (a > 1 || !y.i()) {
                    i = 0;
                } else {
                    i = h.a().a(com.liulishuo.filedownloader.g.f.b(y.e(), y.k()));
                }
                if (i + a <= 1) {
                    com.liulishuo.filedownloader.g.d.d(this, "warn, but no mListener to receive, switch to pending %d %d", Integer.valueOf(y.d()), Integer.valueOf(m.a().b(y.d())));
                    if (com.liulishuo.filedownloader.d.d.b(m.a().b(y.d()))) {
                        this.d = (byte) 1;
                        this.i = messageSnapshot.d();
                        this.h = messageSnapshot.i();
                        this.f.a(this.h);
                        this.a.a(((com.liulishuo.filedownloader.message.MessageSnapshot.a) messageSnapshot).l());
                        return;
                    }
                }
                h.a().a(this.c.M(), messageSnapshot);
                return;
            case (byte) -3:
                this.n = messageSnapshot.e();
                this.h = messageSnapshot.d();
                this.i = messageSnapshot.d();
                h.a().a(this.c.M(), messageSnapshot);
                return;
            case (byte) -1:
                this.e = messageSnapshot.j();
                this.h = messageSnapshot.i();
                h.a().a(this.c.M(), messageSnapshot);
                return;
            case (byte) 1:
                this.h = messageSnapshot.i();
                this.i = messageSnapshot.d();
                this.a.a(messageSnapshot);
                return;
            case (byte) 2:
                this.i = messageSnapshot.d();
                this.l = messageSnapshot.g();
                this.m = messageSnapshot.h();
                String f = messageSnapshot.f();
                if (f != null) {
                    if (y.j() != null) {
                        com.liulishuo.filedownloader.g.d.d(this, "already has mFilename[%s], but assign mFilename[%s] again", y.j(), f);
                    }
                    this.c.b(f);
                }
                this.f.a(this.h);
                this.a.c(messageSnapshot);
                return;
            case (byte) 3:
                this.h = messageSnapshot.i();
                this.f.c(messageSnapshot.i());
                this.a.d(messageSnapshot);
                return;
            case (byte) 5:
                this.h = messageSnapshot.i();
                this.e = messageSnapshot.j();
                this.j = messageSnapshot.k();
                this.f.a();
                this.a.f(messageSnapshot);
                return;
            case (byte) 6:
                this.a.b(messageSnapshot);
                return;
            default:
                return;
        }
    }

    public void a() {
        if (l.b()) {
            l.a().b(this.c.M().y());
        }
        if (com.liulishuo.filedownloader.g.d.a) {
            com.liulishuo.filedownloader.g.d.e(this, "filedownloader:lifecycle:start %s by %d ", toString(), Byte.valueOf(f()));
        }
    }

    public void b() {
        if (l.b() && f() == (byte) 6) {
            l.a().c(this.c.M().y());
        }
    }

    public void c() {
        a y = this.c.M().y();
        if (l.b()) {
            l.a().d(y);
        }
        if (com.liulishuo.filedownloader.g.d.a) {
            com.liulishuo.filedownloader.g.d.e(this, "filedownloader:lifecycle:over %s by %d ", toString(), Byte.valueOf(f()));
        }
        this.f.b(this.h);
        if (this.c.N() != null) {
            ArrayList arrayList = (ArrayList) this.c.N().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((com.liulishuo.filedownloader.a.a) arrayList.get(i)).a(y);
            }
        }
        q.a().e().b(this.c.M());
    }

    d(a aVar, Object obj) {
        this.b = obj;
        this.c = aVar;
        Object bVar = new b();
        this.f = bVar;
        this.g = bVar;
        this.a = new k(aVar.M(), this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
        r8 = this;
        r7 = 2;
        r1 = 1;
        r2 = 0;
        r3 = r8.b;
        monitor-enter(r3);
        r0 = r8.d;	 Catch:{ all -> 0x008f }
        if (r0 == 0) goto L_0x0029;
    L_0x000a:
        r0 = "High concurrent cause, this task %d will not input to launch pool, because of the status isn't idle : %d";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x008f }
        r2 = 0;
        r4 = r8.n();	 Catch:{ all -> 0x008f }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x008f }
        r1[r2] = r4;	 Catch:{ all -> 0x008f }
        r2 = 1;
        r4 = r8.d;	 Catch:{ all -> 0x008f }
        r4 = java.lang.Byte.valueOf(r4);	 Catch:{ all -> 0x008f }
        r1[r2] = r4;	 Catch:{ all -> 0x008f }
        com.liulishuo.filedownloader.g.d.d(r8, r0, r1);	 Catch:{ all -> 0x008f }
        monitor-exit(r3);	 Catch:{ all -> 0x008f }
    L_0x0028:
        return;
    L_0x0029:
        r0 = 10;
        r8.d = r0;	 Catch:{ all -> 0x008f }
        monitor-exit(r3);	 Catch:{ all -> 0x008f }
        r0 = r8.c;
        r0 = r0.M();
        r3 = r0.y();
        r4 = com.liulishuo.filedownloader.l.b();
        if (r4 == 0) goto L_0x0045;
    L_0x003e:
        r4 = com.liulishuo.filedownloader.l.a();
        r4.a(r3);
    L_0x0045:
        r4 = com.liulishuo.filedownloader.g.d.a;
        if (r4 == 0) goto L_0x006b;
    L_0x0049:
        r4 = "call start Url[%s], Path[%s] Listener[%s], Tag[%s]";
        r5 = 4;
        r5 = new java.lang.Object[r5];
        r6 = r3.e();
        r5[r2] = r6;
        r6 = r3.h();
        r5[r1] = r6;
        r6 = r3.l();
        r5[r7] = r6;
        r6 = 3;
        r3 = r3.t();
        r5[r6] = r3;
        com.liulishuo.filedownloader.g.d.e(r8, r4, r5);
    L_0x006b:
        r8.m();	 Catch:{ Throwable -> 0x0092 }
        r0 = r1;
    L_0x006f:
        if (r0 == 0) goto L_0x0078;
    L_0x0071:
        r0 = com.liulishuo.filedownloader.p.a();
        r0.a(r8);
    L_0x0078:
        r0 = com.liulishuo.filedownloader.g.d.a;
        if (r0 == 0) goto L_0x0028;
    L_0x007c:
        r0 = "the task[%d] has been into the launch pool.";
        r1 = new java.lang.Object[r1];
        r3 = r8.n();
        r3 = java.lang.Integer.valueOf(r3);
        r1[r2] = r3;
        com.liulishuo.filedownloader.g.d.e(r8, r0, r1);
        goto L_0x0028;
    L_0x008f:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x008f }
        throw r0;
    L_0x0092:
        r3 = move-exception;
        r4 = com.liulishuo.filedownloader.h.a();
        r4.b(r0);
        r4 = com.liulishuo.filedownloader.h.a();
        r3 = r8.a(r3);
        r4.a(r0, r3);
        r0 = r2;
        goto L_0x006f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.d.e():void");
    }

    public byte f() {
        return this.d;
    }

    public long g() {
        return this.h;
    }

    public long h() {
        return this.i;
    }

    public Throwable i() {
        return this.e;
    }

    public int j() {
        return this.j;
    }

    public void k() {
        if (com.liulishuo.filedownloader.g.d.a) {
            com.liulishuo.filedownloader.g.d.c(this, "free the task %d, when the status is %d", Integer.valueOf(n()), Byte.valueOf(this.d));
        }
        this.d = (byte) 0;
    }

    private void m() throws IOException {
        File file;
        a y = this.c.M().y();
        if (y.h() == null) {
            y.a(com.liulishuo.filedownloader.g.f.b(y.e()));
            if (com.liulishuo.filedownloader.g.d.a) {
                com.liulishuo.filedownloader.g.d.c(this, "save Path is null to %s", y.h());
            }
        }
        if (y.i()) {
            file = new File(y.h());
        } else {
            String i = com.liulishuo.filedownloader.g.f.i(y.h());
            if (i == null) {
                throw new InvalidParameterException(com.liulishuo.filedownloader.g.f.a("the provided mPath[%s] is invalid, can't find its directory", y.h()));
            }
            file = new File(i);
        }
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            throw new IOException(com.liulishuo.filedownloader.g.f.a("Create parent directory failed, please make sure you have permission to create file or directory on the path: %s", file.getAbsolutePath()));
        }
    }

    private int n() {
        return this.c.M().y().d();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void l() {
        /*
        r12 = this;
        r2 = 10;
        r1 = 2;
        r4 = 0;
        r3 = 1;
        r0 = r12.d;
        if (r0 == r2) goto L_0x0024;
    L_0x0009:
        r0 = "High concurrent cause, this task %d will not start, because the of status isn't toLaunchPool: %d";
        r1 = new java.lang.Object[r1];
        r2 = r12.n();
        r2 = java.lang.Integer.valueOf(r2);
        r1[r4] = r2;
        r2 = r12.d;
        r2 = java.lang.Byte.valueOf(r2);
        r1[r3] = r2;
        com.liulishuo.filedownloader.g.d.d(r12, r0, r1);
    L_0x0023:
        return;
    L_0x0024:
        r0 = r12.c;
        r10 = r0.M();
        r9 = r10.y();
        r0 = com.liulishuo.filedownloader.q.a();
        r11 = r0.e();
        r0 = r11.c(r10);	 Catch:{ Throwable -> 0x0065 }
        if (r0 != 0) goto L_0x0023;
    L_0x003c:
        r1 = r12.b;	 Catch:{ Throwable -> 0x0065 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0065 }
        r0 = r12.d;	 Catch:{ all -> 0x0062 }
        if (r0 == r2) goto L_0x0075;
    L_0x0043:
        r0 = "High concurrent cause, this task %d will not start, the status can't assign to toFileDownloadService, because the status isn't toLaunchPool: %d";
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0062 }
        r3 = 0;
        r4 = r12.n();	 Catch:{ all -> 0x0062 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0062 }
        r2[r3] = r4;	 Catch:{ all -> 0x0062 }
        r3 = 1;
        r4 = r12.d;	 Catch:{ all -> 0x0062 }
        r4 = java.lang.Byte.valueOf(r4);	 Catch:{ all -> 0x0062 }
        r2[r3] = r4;	 Catch:{ all -> 0x0062 }
        com.liulishuo.filedownloader.g.d.d(r12, r0, r2);	 Catch:{ all -> 0x0062 }
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        goto L_0x0023;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        throw r0;	 Catch:{ Throwable -> 0x0065 }
    L_0x0065:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = com.liulishuo.filedownloader.h.a();
        r0 = r12.a(r0);
        r1.a(r10, r0);
        goto L_0x0023;
    L_0x0075:
        r0 = 11;
        r12.d = r0;	 Catch:{ all -> 0x0062 }
        monitor-exit(r1);	 Catch:{ all -> 0x0062 }
        r0 = com.liulishuo.filedownloader.h.a();	 Catch:{ Throwable -> 0x0065 }
        r0.b(r10);	 Catch:{ Throwable -> 0x0065 }
        r0 = r9.d();	 Catch:{ Throwable -> 0x0065 }
        r1 = r9.k();	 Catch:{ Throwable -> 0x0065 }
        r2 = r9.r();	 Catch:{ Throwable -> 0x0065 }
        r3 = 1;
        r0 = com.liulishuo.filedownloader.g.c.a(r0, r1, r2, r3);	 Catch:{ Throwable -> 0x0065 }
        if (r0 != 0) goto L_0x0023;
    L_0x0094:
        r0 = com.liulishuo.filedownloader.m.a();	 Catch:{ Throwable -> 0x0065 }
        r1 = r9.e();	 Catch:{ Throwable -> 0x0065 }
        r2 = r9.h();	 Catch:{ Throwable -> 0x0065 }
        r3 = r9.i();	 Catch:{ Throwable -> 0x0065 }
        r4 = r9.f();	 Catch:{ Throwable -> 0x0065 }
        r5 = r9.g();	 Catch:{ Throwable -> 0x0065 }
        r6 = r9.u();	 Catch:{ Throwable -> 0x0065 }
        r7 = r9.r();	 Catch:{ Throwable -> 0x0065 }
        r8 = r12.c;	 Catch:{ Throwable -> 0x0065 }
        r8 = r8.L();	 Catch:{ Throwable -> 0x0065 }
        r9 = r9.x();	 Catch:{ Throwable -> 0x0065 }
        r0 = r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Throwable -> 0x0065 }
        r1 = r12.d;	 Catch:{ Throwable -> 0x0065 }
        r2 = -2;
        if (r1 != r2) goto L_0x00ea;
    L_0x00c7:
        r1 = "High concurrent cause, this task %d will be paused,because of the status is paused, so the pause action must be applied";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0065 }
        r3 = 0;
        r4 = r12.n();	 Catch:{ Throwable -> 0x0065 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0065 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0065 }
        com.liulishuo.filedownloader.g.d.d(r12, r1, r2);	 Catch:{ Throwable -> 0x0065 }
        if (r0 == 0) goto L_0x0023;
    L_0x00dd:
        r0 = com.liulishuo.filedownloader.m.a();	 Catch:{ Throwable -> 0x0065 }
        r1 = r12.n();	 Catch:{ Throwable -> 0x0065 }
        r0.a(r1);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0023;
    L_0x00ea:
        if (r0 != 0) goto L_0x011b;
    L_0x00ec:
        r0 = r11.c(r10);	 Catch:{ Throwable -> 0x0065 }
        if (r0 != 0) goto L_0x0023;
    L_0x00f2:
        r0 = new java.lang.RuntimeException;	 Catch:{ Throwable -> 0x0065 }
        r1 = "Occur Unknown Error, when request to start maybe some problem in binder, maybe the process was killed in unexpected.";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0065 }
        r0 = r12.a(r0);	 Catch:{ Throwable -> 0x0065 }
        r1 = com.liulishuo.filedownloader.h.a();	 Catch:{ Throwable -> 0x0065 }
        r1 = r1.a(r10);	 Catch:{ Throwable -> 0x0065 }
        if (r1 == 0) goto L_0x0112;
    L_0x0108:
        r11.b(r10);	 Catch:{ Throwable -> 0x0065 }
        r1 = com.liulishuo.filedownloader.h.a();	 Catch:{ Throwable -> 0x0065 }
        r1.b(r10);	 Catch:{ Throwable -> 0x0065 }
    L_0x0112:
        r1 = com.liulishuo.filedownloader.h.a();	 Catch:{ Throwable -> 0x0065 }
        r1.a(r10, r0);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0023;
    L_0x011b:
        r11.b(r10);	 Catch:{ Throwable -> 0x0065 }
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.d.l():void");
    }
}
