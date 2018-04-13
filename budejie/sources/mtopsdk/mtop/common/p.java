package mtopsdk.mtop.common;

import mtopsdk.a.b.e;
import mtopsdk.common.util.m;

class p implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ Object b;
    final /* synthetic */ o c;

    p(o oVar, e eVar, Object obj) {
        this.c = oVar;
        this.a = eVar;
        this.b = obj;
    }

    public void run() {
        try {
            if (this.c.b != null) {
                this.c.b.onHeader(new j(this.a.a(), this.a.b()), this.b);
            }
        } catch (Throwable th) {
            m.b("mtopsdk.NetworkListenerAdapter", "onHeader failed.", th);
        }
    }
}
