package mtopsdk.mtop.common.a;

import mtopsdk.common.util.m;
import mtopsdk.mtop.common.b;
import mtopsdk.mtop.common.e;
import mtopsdk.mtop.common.f;
import mtopsdk.mtop.common.i;
import mtopsdk.mtop.common.j;
import mtopsdk.mtop.common.k;
import mtopsdk.mtop.domain.MtopResponse;

public class a extends b {
    protected k a = null;
    public MtopResponse b = null;
    public Object c = null;
    protected boolean d = false;

    public a(k kVar) {
        this.a = kVar;
    }

    public void onFinished(i iVar, Object obj) {
        if (!(iVar == null || iVar.a() == null)) {
            this.b = iVar.a();
            this.c = obj;
        }
        synchronized (this) {
            try {
                notifyAll();
            } catch (Exception e) {
                m.d("mtopsdk.MtopListenerProxy", "[onFinished] notify error");
            }
        }
        if (!(this.a instanceof e)) {
            return;
        }
        if (!this.d || (this.b != null && this.b.isApiSuccess())) {
            ((e) this.a).onFinished(iVar, obj);
        }
    }

    public void onHeader(j jVar, Object obj) {
        if (this.a instanceof f) {
            ((f) this.a).onHeader(jVar, obj);
        }
    }
}
