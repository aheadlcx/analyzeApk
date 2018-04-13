package com.liulishuo.filedownloader.download;

import android.os.Process;
import com.liulishuo.filedownloader.d.c;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.exception.FileDownloadHttpException;
import com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;
import com.liulishuo.filedownloader.exception.FileDownloadOutOfSpaceException;
import com.liulishuo.filedownloader.g.b;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.e;
import com.liulishuo.filedownloader.g.f;
import com.liulishuo.filedownloader.x;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

public class DownloadLaunchRunnable implements g, Runnable {
    private static final ThreadPoolExecutor p = b.a("ConnectionBlock");
    private long A;
    private long B;
    int a;
    private final e b;
    private final int c;
    private final c d;
    private final com.liulishuo.filedownloader.d.b e;
    private final boolean f;
    private final boolean g;
    private final com.liulishuo.filedownloader.b.a h;
    private final x i;
    private boolean j;
    private boolean k;
    private final boolean l;
    private final ArrayList<d> m;
    private d n;
    private boolean o;
    private boolean q;
    private boolean r;
    private boolean s;
    private final AtomicBoolean t;
    private volatile boolean u;
    private volatile boolean v;
    private volatile Exception w;
    private String x;
    private long y;
    private long z;

    class DiscardSafely extends Throwable {
        DiscardSafely() {
        }
    }

    class RetryDirectly extends Throwable {
        RetryDirectly() {
        }
    }

    public static class a {
        private c a;
        private com.liulishuo.filedownloader.d.b b;
        private x c;
        private Integer d;
        private Integer e;
        private Boolean f;
        private Boolean g;
        private Integer h;

        public a a(c cVar) {
            this.a = cVar;
            return this;
        }

        public a a(com.liulishuo.filedownloader.d.b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(x xVar) {
            this.c = xVar;
            return this;
        }

        public a a(Integer num) {
            this.d = num;
            return this;
        }

        public a b(Integer num) {
            this.e = num;
            return this;
        }

        public a a(Boolean bool) {
            this.f = bool;
            return this;
        }

        public a b(Boolean bool) {
            this.g = bool;
            return this;
        }

        public a c(Integer num) {
            this.h = num;
            return this;
        }

        public DownloadLaunchRunnable a() {
            if (this.a != null && this.c != null && this.d != null && this.e != null && this.f != null && this.g != null && this.h != null) {
                return new DownloadLaunchRunnable(this.a, this.b, this.c, this.d.intValue(), this.e.intValue(), this.f.booleanValue(), this.g.booleanValue(), this.h.intValue());
            }
            throw new IllegalArgumentException();
        }
    }

    private DownloadLaunchRunnable(c cVar, com.liulishuo.filedownloader.d.b bVar, x xVar, int i, int i2, boolean z, boolean z2, int i3) {
        this.c = 5;
        this.k = false;
        this.m = new ArrayList(5);
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.t = new AtomicBoolean(true);
        this.u = false;
        this.j = false;
        this.d = cVar;
        this.e = bVar;
        this.f = z;
        this.g = z2;
        this.h = c.a().c();
        this.l = c.a().e();
        this.i = xVar;
        this.a = i3;
        this.b = new e(cVar, i3, i, i2);
    }

    public void a() {
        this.u = true;
        if (this.n != null) {
            this.n.a();
        }
        Iterator it = ((ArrayList) this.m.clone()).iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            if (dVar != null) {
                dVar.a();
            }
        }
    }

    public void b() {
        a(this.h.c(this.d.a()));
        this.b.c();
    }

    public void run() {
        Exception e;
        Process.setThreadPriority(10);
        if (this.d.f() != (byte) 1) {
            if (this.d.f() != (byte) -2) {
                b(new RuntimeException(f.a("Task[%d] can't start the download runnable, because its status is %d not %d", Integer.valueOf(this.d.a()), Byte.valueOf(this.d.f()), Byte.valueOf((byte) 1))));
            } else if (d.a) {
                d.c(this, "High concurrent cause, start runnable but already paused %d", Integer.valueOf(this.d.a()));
            }
            this.b.b();
            if (this.u) {
                this.b.f();
            } else if (this.v) {
                this.b.a(this.w);
            } else {
                try {
                    this.b.g();
                } catch (Exception e2) {
                    this.b.a(e2);
                }
            }
            this.t.set(false);
            return;
        }
        if (!this.u) {
            this.b.d();
        }
        while (!this.u) {
            try {
                i();
                g();
                j();
                List c = this.h.c(this.d.a());
                a(c);
                if (this.u) {
                    this.d.a((byte) -2);
                    this.b.b();
                    if (this.u) {
                        this.b.f();
                    } else if (this.v) {
                        this.b.a(this.w);
                    } else {
                        try {
                            this.b.g();
                        } catch (Exception e22) {
                            this.b.a(e22);
                        }
                    }
                    this.t.set(false);
                    return;
                }
                long h = this.d.h();
                a(h, this.d.e());
                int b = b(h);
                if (b <= 0) {
                    throw new IllegalAccessException(f.a("invalid connection count %d, the connection count must be larger than 0", Integer.valueOf(b)));
                } else if (h == 0) {
                    this.b.b();
                    if (this.u) {
                        this.b.f();
                    } else if (this.v) {
                        this.b.a(this.w);
                    } else {
                        try {
                            this.b.g();
                        } catch (Exception e222) {
                            this.b.a(e222);
                        }
                    }
                    this.t.set(false);
                    return;
                } else if (this.u) {
                    this.d.a((byte) -2);
                    this.b.b();
                    if (this.u) {
                        this.b.f();
                    } else if (this.v) {
                        this.b.a(this.w);
                    } else {
                        try {
                            this.b.g();
                        } catch (Exception e2222) {
                            this.b.a(e2222);
                        }
                    }
                    this.t.set(false);
                    return;
                } else {
                    this.o = b == 1;
                    if (this.o) {
                        c(h);
                    } else {
                        this.b.e();
                        if (this.q) {
                            a(b, c);
                        } else {
                            a(h, b);
                        }
                    }
                    this.b.b();
                    if (this.u) {
                        this.b.f();
                    } else if (this.v) {
                        try {
                            this.b.g();
                        } catch (Exception e22222) {
                            this.b.a(e22222);
                        }
                    } else {
                        this.b.a(this.w);
                    }
                    this.t.set(false);
                }
            } catch (IOException e3) {
                e22222 = e3;
            } catch (IllegalAccessException e4) {
                e22222 = e4;
            } catch (InterruptedException e5) {
                e22222 = e5;
            } catch (IllegalArgumentException e6) {
                e22222 = e6;
            } catch (FileDownloadGiveUpRetryException e7) {
                e22222 = e7;
            } catch (DiscardSafely e8) {
                this.b.b();
                if (this.u) {
                    this.b.f();
                } else if (this.v) {
                    this.b.a(this.w);
                } else {
                    try {
                        this.b.g();
                    } catch (Exception e222222) {
                        this.b.a(e222222);
                    }
                }
                this.t.set(false);
                return;
            } catch (RetryDirectly e9) {
                this.d.a((byte) 5);
            } catch (Throwable th) {
                this.b.b();
                if (this.u) {
                    this.b.f();
                } else if (this.v) {
                    this.b.a(this.w);
                } else {
                    try {
                        this.b.g();
                    } catch (Exception e10) {
                        this.b.a(e10);
                    }
                }
                this.t.set(false);
            }
        }
        if (d.a) {
            d.c(this, "High concurrent cause, start runnable but already paused %d", Integer.valueOf(this.d.a()));
        }
        this.b.b();
        if (this.u) {
            this.b.f();
        } else if (this.v) {
            this.b.a(this.w);
        } else {
            try {
                this.b.g();
            } catch (Exception e2222222) {
                this.b.a(e2222222);
            }
        }
        this.t.set(false);
        return;
        if (a(e2222222)) {
            c(e2222222);
        } else {
            b(e2222222);
            this.b.b();
            if (this.u) {
                this.b.f();
            } else if (this.v) {
                this.b.g();
            } else {
                this.b.a(this.w);
            }
            this.t.set(false);
        }
    }

    private int b(long j) {
        if (!h()) {
            return 1;
        }
        if (this.q) {
            return this.d.n();
        }
        return c.a().a(this.d.a(), this.d.b(), this.d.c(), j);
    }

    private void g() throws IOException, RetryDirectly, IllegalAccessException {
        com.liulishuo.filedownloader.a.b bVar = null;
        try {
            b b;
            if (this.k) {
                b = com.liulishuo.filedownloader.download.b.a.b();
            } else {
                b = com.liulishuo.filedownloader.download.b.a.a();
            }
            a a = new a().a(this.d.a()).a(this.d.b()).b(this.d.j()).a(this.e).a(b).a();
            bVar = a.a();
            a(a.d(), a, bVar);
        } finally {
            if (bVar != null) {
                bVar.f();
            }
        }
    }

    private boolean h() {
        boolean z = true;
        if (this.q && this.d.n() <= 1) {
            return false;
        }
        if (!(this.r && this.l && !this.s)) {
            z = false;
        }
        return z;
    }

    void a(List<com.liulishuo.filedownloader.d.a> list) {
        boolean z = true;
        int n = this.d.n();
        String e = this.d.e();
        String d = this.d.d();
        boolean z2 = n > 1;
        long length = this.k ? 0 : (!z2 || this.l) ? f.a(this.d.a(), this.d) ? !this.l ? new File(e).length() : z2 ? n != list.size() ? 0 : com.liulishuo.filedownloader.d.a.a((List) list) : this.d.g() : 0 : 0;
        this.d.a(length);
        if (length <= 0) {
            z = false;
        }
        this.q = z;
        if (!this.q) {
            this.h.d(this.d.a());
            f.c(d, e);
        }
    }

    private void a(Map<String, List<String>> map, a aVar, com.liulishuo.filedownloader.a.b bVar) throws IOException, RetryDirectly, IllegalArgumentException {
        int a = this.d.a();
        int e = bVar.e();
        this.r = f.b(e, bVar);
        Object obj = (e == 200 || e == 201 || e == 0) ? 1 : null;
        String j = this.d.j();
        String a2 = f.a(a, bVar);
        Object obj2 = null;
        if (e == 412) {
            obj2 = 1;
        } else if (j != null && !j.equals(a2) && (obj != null || this.r)) {
            obj2 = 1;
        } else if (e == 201 && aVar.b()) {
            obj2 = 1;
        } else if (e == 416) {
            if (this.d.g() > 0) {
                obj2 = 1;
            } else if (!this.k) {
                this.k = true;
                obj2 = 1;
            }
        }
        if (obj2 != null) {
            if (this.q) {
                d.d(this, "there is precondition failed on this request[%d] with old etag[%s]、new etag[%s]、response code is %d", Integer.valueOf(a), j, a2, Integer.valueOf(e));
            }
            this.h.d(this.d.a());
            f.c(this.d.d(), this.d.e());
            this.q = false;
            if (j != null && j.equals(a2)) {
                d.d(this, "the old etag[%s] is the same to the new etag[%s], but the response status code is %d not Partial(206), so wo have to start this task from very beginning for task[%d]!", j, a2, Integer.valueOf(e), Integer.valueOf(a));
                a2 = null;
            }
            this.d.a(0);
            this.d.c(0);
            this.d.b(a2);
            this.d.o();
            this.h.a(a, this.d.j(), this.d.g(), this.d.h(), this.d.n());
            throw new RetryDirectly();
        }
        this.x = aVar.c();
        if (this.r || obj != null) {
            long a3 = f.a(bVar);
            j = null;
            if (this.d.l()) {
                j = f.a(bVar, this.d.b());
            }
            this.s = a3 == -1;
            e eVar = this.b;
            boolean z = this.q && this.r;
            eVar.a(z, a3, a2, j);
            return;
        }
        throw new FileDownloadHttpException(e, map, bVar.c());
    }

    private void c(long j) throws IOException, IllegalAccessException {
        b a;
        if (this.r) {
            a = com.liulishuo.filedownloader.download.b.a.a(this.d.g(), this.d.g(), j - this.d.g());
        } else {
            this.d.a(0);
            a = com.liulishuo.filedownloader.download.b.a.a(j);
        }
        this.n = new com.liulishuo.filedownloader.download.d.a().a(this.d.a()).a(Integer.valueOf(-1)).a((g) this).a(this.d.b()).b(this.d.j()).a(this.e).a(this.g).a(a).c(this.d.e()).a();
        this.d.b(1);
        this.h.a(this.d.a(), 1);
        if (this.u) {
            this.d.a((byte) -2);
            this.n.a();
            return;
        }
        this.n.run();
    }

    private void a(int i, List<com.liulishuo.filedownloader.d.a> list) throws InterruptedException {
        if (i <= 1 || list.size() != i) {
            throw new IllegalArgumentException();
        }
        a((List) list, this.d.h());
    }

    private void a(long j, int i) throws InterruptedException {
        long j2 = j / ((long) i);
        int a = this.d.a();
        List arrayList = new ArrayList();
        long j3 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long j4;
            if (i2 == i - 1) {
                j4 = -1;
            } else {
                j4 = (j3 + j2) - 1;
            }
            com.liulishuo.filedownloader.d.a aVar = new com.liulishuo.filedownloader.d.a();
            aVar.a(a);
            aVar.b(i2);
            aVar.a(j3);
            aVar.b(j3);
            aVar.c(j4);
            arrayList.add(aVar);
            this.h.a(aVar);
            j3 += j2;
        }
        this.d.b(i);
        this.h.a(a, i);
        a(arrayList, j);
    }

    private void a(List<com.liulishuo.filedownloader.d.a> list, long j) throws InterruptedException {
        String str;
        int a = this.d.a();
        String j2 = this.d.j();
        if (this.x != null) {
            str = this.x;
        } else {
            str = this.d.b();
        }
        String e = this.d.e();
        if (d.a) {
            d.c(this, "fetch data with multiple connection(count: [%d]) for task[%d] totalLength[%d]", Integer.valueOf(list.size()), Integer.valueOf(a), Long.valueOf(j));
        }
        boolean z = this.q;
        long j3 = 0;
        for (com.liulishuo.filedownloader.d.a aVar : list) {
            long d;
            d a2;
            if (aVar.e() == -1) {
                d = j - aVar.d();
            } else {
                d = (aVar.e() - aVar.d()) + 1;
            }
            long d2 = j3 + (aVar.d() - aVar.c());
            if (d != 0) {
                a2 = new com.liulishuo.filedownloader.download.d.a().a(a).a(Integer.valueOf(aVar.b())).a((g) this).a(str).b(z ? j2 : null).a(this.e).a(this.g).a(com.liulishuo.filedownloader.download.b.a.a(aVar.c(), aVar.d(), aVar.e(), d)).c(e).a();
                if (d.a) {
                    d.c(this, "enable multiple connection: %s", aVar);
                }
                if (a2 == null) {
                    throw new IllegalArgumentException("the download runnable must not be null!");
                }
                this.m.add(a2);
                j3 = d2;
            } else if (d.a) {
                d.c(this, "pass connection[%d-%d], because it has been completed", Integer.valueOf(aVar.a()), Integer.valueOf(aVar.b()));
                j3 = d2;
            } else {
                j3 = d2;
            }
        }
        if (j3 != this.d.g()) {
            d.d(this, "correct the sofar[%d] from connection table[%d]", Long.valueOf(this.d.g()), Long.valueOf(j3));
            this.d.a(j3);
        }
        Collection arrayList = new ArrayList(this.m.size());
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            a2 = (d) it.next();
            if (this.u) {
                a2.a();
            } else {
                arrayList.add(Executors.callable(a2));
            }
        }
        if (this.u) {
            this.d.a((byte) -2);
            return;
        }
        List<Future> invokeAll = p.invokeAll(arrayList);
        if (d.a) {
            for (Future future : invokeAll) {
                d.c(this, "finish sub-task for [%d] %B %B", Integer.valueOf(a), Boolean.valueOf(future.isDone()), Boolean.valueOf(future.isCancelled()));
            }
        }
    }

    private void a(long j, String str) throws IOException, IllegalAccessException {
        com.liulishuo.filedownloader.f.a n;
        Throwable th;
        com.liulishuo.filedownloader.f.a aVar = null;
        if (j != -1) {
            try {
                n = f.n(this.d.e());
                try {
                    long length = new File(str).length();
                    long j2 = j - length;
                    long f = f.f(str);
                    if (f < j2) {
                        throw new FileDownloadOutOfSpaceException(f, j2, length);
                    } else if (!e.a().f) {
                        n.b(j);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    aVar = n;
                    if (aVar != null) {
                        aVar.b();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (aVar != null) {
                    aVar.b();
                }
                throw th;
            }
        }
        n = null;
        if (n != null) {
            n.b();
        }
    }

    public void a(long j) {
        if (!this.u) {
            this.b.a(j);
        }
    }

    public void a(d dVar, long j, long j2) {
        if (!this.u) {
            int i = dVar.a;
            if (d.a) {
                d.c(this, "the connection has been completed(%d): [%d, %d)  %d", Integer.valueOf(i), Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.d.h()));
            }
            if (!this.o) {
                synchronized (this.m) {
                    this.m.remove(dVar);
                }
            } else if (j != 0 && j2 != this.d.h()) {
                d.a(this, "the single task not completed corrected(%d, %d != %d) for task(%d)", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(this.d.h()), Integer.valueOf(this.d.a()));
            }
        } else if (d.a) {
            d.c(this, "the task[%d] has already been paused, so pass the completed callback", Integer.valueOf(this.d.a()));
        }
    }

    public boolean a(Exception exception) {
        if (exception instanceof FileDownloadHttpException) {
            int code = ((FileDownloadHttpException) exception).getCode();
            if (this.o && code == 416 && !this.j) {
                f.c(this.d.d(), this.d.e());
                this.j = true;
                return true;
            }
        }
        boolean z = this.a > 0 && !(exception instanceof FileDownloadGiveUpRetryException);
        return z;
    }

    public void b(Exception exception) {
        this.v = true;
        this.w = exception;
        if (!this.u) {
            Iterator it = ((ArrayList) this.m.clone()).iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                if (dVar != null) {
                    dVar.b();
                }
            }
        } else if (d.a) {
            d.c(this, "the task[%d] has already been paused, so pass the error callback", Integer.valueOf(this.d.a()));
        }
    }

    public void c(Exception exception) {
        if (!this.u) {
            int i = this.a;
            this.a = i - 1;
            if (i < 0) {
                d.a(this, "valid retry times is less than 0(%d) for download task(%d)", Integer.valueOf(this.a), Integer.valueOf(this.d.a()));
            }
            this.b.a(exception, this.a);
        } else if (d.a) {
            d.c(this, "the task[%d] has already been paused, so pass the retry callback", Integer.valueOf(this.d.a()));
        }
    }

    public void c() {
        this.h.a(this.d.a(), this.d.g());
    }

    private void i() throws FileDownloadGiveUpRetryException {
        if (this.g && !f.k("android.permission.ACCESS_NETWORK_STATE")) {
            throw new FileDownloadGiveUpRetryException(f.a("Task[%d] can't start the download runnable, because this task require wifi, but user application nor current process has %s, so we can't check whether the network type connection.", Integer.valueOf(this.d.a()), "android.permission.ACCESS_NETWORK_STATE"));
        } else if (this.g && f.d()) {
            throw new FileDownloadNetworkPolicyException();
        }
    }

    private void j() throws RetryDirectly, DiscardSafely {
        int a = this.d.a();
        if (this.d.l()) {
            String d = this.d.d();
            int b = f.b(this.d.b(), d);
            if (com.liulishuo.filedownloader.g.c.a(a, d, this.f, false)) {
                this.h.e(a);
                this.h.d(a);
                throw new DiscardSafely();
            }
            c b2 = this.h.b(b);
            if (b2 != null) {
                if (com.liulishuo.filedownloader.g.c.a(a, b2, this.i, false)) {
                    this.h.e(a);
                    this.h.d(a);
                    throw new DiscardSafely();
                }
                List<com.liulishuo.filedownloader.d.a> c = this.h.c(b);
                this.h.e(b);
                this.h.d(b);
                f.p(this.d.d());
                if (f.a(b, b2)) {
                    this.d.a(b2.g());
                    this.d.c(b2.h());
                    this.d.b(b2.j());
                    this.d.b(b2.n());
                    this.h.b(this.d);
                    if (c != null) {
                        for (com.liulishuo.filedownloader.d.a aVar : c) {
                            aVar.a(a);
                            this.h.a(aVar);
                        }
                    }
                    throw new RetryDirectly();
                }
            }
            if (com.liulishuo.filedownloader.g.c.a(a, this.d.g(), this.d.e(), d, this.i)) {
                this.h.e(a);
                this.h.d(a);
                throw new DiscardSafely();
            }
        }
    }

    public int d() {
        return this.d.a();
    }

    public boolean e() {
        return this.t.get() || this.b.a();
    }

    public String f() {
        return this.d.e();
    }
}
