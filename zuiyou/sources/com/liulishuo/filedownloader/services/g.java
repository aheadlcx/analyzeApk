package com.liulishuo.filedownloader.services;

import android.text.TextUtils;
import com.liulishuo.filedownloader.b.a;
import com.liulishuo.filedownloader.d.b;
import com.liulishuo.filedownloader.download.DownloadLaunchRunnable;
import com.liulishuo.filedownloader.download.c;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.g.f;
import com.liulishuo.filedownloader.x;
import java.util.List;

class g implements x {
    private final a a;
    private final h b;

    g() {
        c a = c.a();
        this.a = a.c();
        this.b = new h(a.d());
    }

    public synchronized void a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, b bVar, boolean z3) {
        if (d.a) {
            d.c(this, "request start the task with url(%s) path(%s) isDirectory(%B)", str, str2, Boolean.valueOf(z));
        }
        int a = f.a(str, str2, z);
        com.liulishuo.filedownloader.d.c b = this.a.b(a);
        List list = null;
        if (!z && b == null) {
            int a2 = f.a(str, f.i(str2), true);
            b = this.a.b(a2);
            if (b != null && str2.equals(b.d())) {
                if (d.a) {
                    d.c(this, "task[%d] find model by dirCaseId[%d]", Integer.valueOf(a), Integer.valueOf(a2));
                }
                list = this.a.c(a2);
            }
        }
        if (!com.liulishuo.filedownloader.g.c.a(a, b, (x) this, true)) {
            String d;
            if (b != null) {
                d = b.d();
            } else {
                d = f.a(str2, z, null);
            }
            if (!com.liulishuo.filedownloader.g.c.a(a, d, z2, true)) {
                String e;
                long g = b != null ? b.g() : 0;
                if (b != null) {
                    e = b.e();
                } else {
                    e = f.d(d);
                }
                if (com.liulishuo.filedownloader.g.c.a(a, g, e, d, this)) {
                    if (d.a) {
                        d.c(this, "there is an another task with the same target-file-path %d %s", Integer.valueOf(a), d);
                    }
                    if (b != null) {
                        this.a.e(a);
                        this.a.d(a);
                    }
                } else {
                    Object obj;
                    com.liulishuo.filedownloader.d.c cVar;
                    if (b == null || !(b.f() == (byte) -2 || b.f() == (byte) -1 || b.f() == (byte) 1 || b.f() == (byte) 6 || b.f() == (byte) 2)) {
                        com.liulishuo.filedownloader.d.c cVar2;
                        if (b == null) {
                            cVar2 = new com.liulishuo.filedownloader.d.c();
                        } else {
                            cVar2 = b;
                        }
                        cVar2.a(str);
                        cVar2.a(str2, z);
                        cVar2.a(a);
                        cVar2.a(0);
                        cVar2.c(0);
                        cVar2.a((byte) 1);
                        cVar2.b(1);
                        obj = 1;
                        cVar = cVar2;
                    } else if (b.a() != a) {
                        this.a.e(b.a());
                        this.a.d(b.a());
                        b.a(a);
                        b.a(str2, z);
                        if (r2 != null) {
                            for (com.liulishuo.filedownloader.d.a aVar : r2) {
                                aVar.a(a);
                                this.a.a(aVar);
                            }
                        }
                        obj = 1;
                        cVar = b;
                    } else if (TextUtils.equals(str, b.b())) {
                        obj = null;
                        cVar = b;
                    } else {
                        b.a(str);
                        obj = 1;
                        cVar = b;
                    }
                    if (obj != null) {
                        this.a.b(cVar);
                    }
                    this.b.a(new DownloadLaunchRunnable.a().a(cVar).a(bVar).a((x) this).a(Integer.valueOf(i2)).b(Integer.valueOf(i)).a(Boolean.valueOf(z2)).b(Boolean.valueOf(z3)).c(Integer.valueOf(i3)).a());
                }
            } else if (d.a) {
                d.c(this, "has already completed downloading %d", Integer.valueOf(a));
            }
        } else if (d.a) {
            d.c(this, "has already started download %d", Integer.valueOf(a));
        }
    }

    public boolean a(String str, String str2) {
        return a(f.b(str, str2));
    }

    public boolean a(int i) {
        return a(this.a.b(i));
    }

    public boolean b(int i) {
        if (d.a) {
            d.c(this, "request pause the task %d", Integer.valueOf(i));
        }
        com.liulishuo.filedownloader.d.c b = this.a.b(i);
        if (b == null) {
            return false;
        }
        b.a((byte) -2);
        this.b.b(i);
        return true;
    }

    public void a() {
        List<Integer> b = this.b.b();
        if (d.a) {
            d.c(this, "pause all tasks %d", Integer.valueOf(b.size()));
        }
        for (Integer intValue : b) {
            b(intValue.intValue());
        }
    }

    public long c(int i) {
        com.liulishuo.filedownloader.d.c b = this.a.b(i);
        if (b == null) {
            return 0;
        }
        int n = b.n();
        if (n <= 1) {
            return b.g();
        }
        List c = this.a.c(i);
        if (c == null || c.size() != n) {
            return 0;
        }
        return com.liulishuo.filedownloader.d.a.a(c);
    }

    public long d(int i) {
        com.liulishuo.filedownloader.d.c b = this.a.b(i);
        if (b == null) {
            return 0;
        }
        return b.h();
    }

    public byte e(int i) {
        com.liulishuo.filedownloader.d.c b = this.a.b(i);
        if (b == null) {
            return (byte) 0;
        }
        return b.f();
    }

    public boolean b() {
        return this.b.a() <= 0;
    }

    public synchronized boolean f(int i) {
        return this.b.a(i);
    }

    public boolean a(com.liulishuo.filedownloader.d.c cVar) {
        boolean z = true;
        if (cVar == null) {
            return false;
        }
        boolean c = this.b.c(cVar.a());
        if (com.liulishuo.filedownloader.d.d.a(cVar.f())) {
            if (!c) {
                z = false;
            }
        } else if (!c) {
            d.a(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(cVar.a()), Byte.valueOf(cVar.f()));
            z = false;
        }
        return z;
    }

    public int a(String str, int i) {
        return this.b.a(str, i);
    }

    public boolean g(int i) {
        if (i == 0) {
            d.d(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        } else if (a(i)) {
            d.d(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        } else {
            this.a.e(i);
            this.a.d(i);
            return true;
        }
    }

    public void c() {
        this.a.a();
    }
}
