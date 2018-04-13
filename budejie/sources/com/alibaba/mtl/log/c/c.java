package com.alibaba.mtl.log.c;

import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.r;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class c {
    private static c a;
    /* renamed from: a */
    private a f31a = new b(com.alibaba.mtl.log.a.getContext());
    private Runnable b = new Runnable(this) {
        final /* synthetic */ c b;

        {
            this.b = r1;
        }

        public void run() {
            this.b.G();
        }
    };
    private List<com.alibaba.mtl.log.model.a> l = new CopyOnWriteArrayList();

    class a implements Runnable {
        final /* synthetic */ c b;

        a(c cVar) {
            this.b = cVar;
        }

        public void run() {
            this.b.f31a;
            if (this.b.f31a.g() > 9000) {
                this.b.I();
            }
        }
    }

    private c() {
        com.alibaba.mtl.log.d.a.a().start();
        r.a().b(new a(this));
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public void a(com.alibaba.mtl.log.model.a aVar) {
        i.a("LogStoreMgr", "[add] :", aVar.X);
        com.alibaba.mtl.log.b.a.m(aVar.T);
        this.l.add(aVar);
        if (this.l.size() >= 100) {
            r.a().f(1);
            r.a().a(1, this.b, 0);
        } else if (!r.a().b(1)) {
            r.a().a(1, this.b, 5000);
        }
    }

    public int a(List<com.alibaba.mtl.log.model.a> list) {
        i.a("LogStoreMgr", list);
        return this.f31a.a((List) list);
    }

    public List<com.alibaba.mtl.log.model.a> a(String str, int i) {
        i.a("LogStoreMgr", "[get]", this.f31a.a(str, i));
        return this.f31a.a(str, i);
    }

    public synchronized void G() {
        i.a("LogStoreMgr", "[store]");
        List list = null;
        try {
            synchronized (this.l) {
                if (this.l.size() > 0) {
                    list = new ArrayList(this.l);
                    this.l.clear();
                }
            }
            if (list != null) {
                if (list.size() > 0) {
                    this.f31a.a(list);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void clear() {
        i.a("LogStoreMgr", "[clear]");
        this.f31a.clear();
        this.l.clear();
    }

    private void H() {
        Calendar instance = Calendar.getInstance();
        instance.add(5, -3);
        this.f31a.c(AppLinkConstants.TIME, String.valueOf(instance.getTimeInMillis()));
    }

    private void I() {
        this.f31a.e(1000);
    }
}
