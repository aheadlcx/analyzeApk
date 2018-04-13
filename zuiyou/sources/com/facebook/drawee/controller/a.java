package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.f;
import com.facebook.common.internal.g;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.components.b;
import com.facebook.drawee.d.c;
import com.meizu.cloud.pushsdk.pushtracer.storage.EventStoreHelper;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class a<T, INFO> implements com.facebook.drawee.c.a.a, com.facebook.drawee.components.a.a, com.facebook.drawee.d.a {
    private static final Class<?> a = a.class;
    private final DraweeEventTracker b = DraweeEventTracker.a();
    private final com.facebook.drawee.components.a c;
    private final Executor d;
    @Nullable
    private b e;
    @Nullable
    private com.facebook.drawee.c.a f;
    @Nullable
    private c<INFO> g;
    @Nullable
    private d h;
    @Nullable
    private c i;
    @Nullable
    private Drawable j;
    private String k;
    private Object l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    @Nullable
    private String r;
    @Nullable
    private com.facebook.datasource.b<T> s;
    @Nullable
    private T t;
    @Nullable
    private Drawable u;

    protected abstract com.facebook.datasource.b<T> a();

    protected abstract void a(@Nullable Drawable drawable);

    protected abstract void a(@Nullable T t);

    @Nullable
    protected abstract INFO c(T t);

    protected abstract Drawable d(T t);

    public a(com.facebook.drawee.components.a aVar, Executor executor, String str, Object obj) {
        this.c = aVar;
        this.d = executor;
        a(str, obj, true);
    }

    protected void a(String str, Object obj) {
        a(str, obj, false);
    }

    private void a(String str, Object obj, boolean z) {
        this.b.a(Event.ON_INIT_CONTROLLER);
        if (!(z || this.c == null)) {
            this.c.b(this);
        }
        this.m = false;
        this.o = false;
        b();
        this.q = false;
        if (this.e != null) {
            this.e.b();
        }
        if (this.f != null) {
            this.f.a();
            this.f.a(this);
        }
        if (this.g instanceof a$a) {
            ((a$a) this.g).a();
        } else {
            this.g = null;
        }
        this.h = null;
        if (this.i != null) {
            this.i.b();
            this.i.a(null);
            this.i = null;
        }
        this.j = null;
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.k, str);
        }
        this.k = str;
        this.l = obj;
    }

    public void d() {
        this.b.a(Event.ON_RELEASE_CONTROLLER);
        if (this.e != null) {
            this.e.c();
        }
        if (this.f != null) {
            this.f.b();
        }
        if (this.i != null) {
            this.i.b();
        }
        b();
    }

    private void b() {
        boolean z = this.n;
        this.n = false;
        this.p = false;
        if (this.s != null) {
            this.s.h();
            this.s = null;
        }
        if (this.u != null) {
            a(this.u);
        }
        if (this.r != null) {
            this.r = null;
        }
        this.u = null;
        if (this.t != null) {
            b("release", this.t);
            a(this.t);
            this.t = null;
        }
        if (z) {
            h().a(this.k);
        }
    }

    public String e() {
        return this.k;
    }

    @Nullable
    protected b f() {
        return this.e;
    }

    protected void a(@Nullable b bVar) {
        this.e = bVar;
    }

    @Nullable
    protected com.facebook.drawee.c.a g() {
        return this.f;
    }

    protected void a(@Nullable com.facebook.drawee.c.a aVar) {
        this.f = aVar;
        if (this.f != null) {
            this.f.a(this);
        }
    }

    protected void b(boolean z) {
        this.q = z;
    }

    public void a(@Nullable String str) {
        this.r = str;
    }

    public void a(c<? super INFO> cVar) {
        g.a(cVar);
        if (this.g instanceof a$a) {
            ((a$a) this.g).a(cVar);
        } else if (this.g != null) {
            this.g = a$a.a(this.g, cVar);
        } else {
            this.g = cVar;
        }
    }

    protected c<INFO> h() {
        if (this.g == null) {
            return b.a();
        }
        return this.g;
    }

    public void a(@Nullable d dVar) {
        this.h = dVar;
    }

    @Nullable
    public com.facebook.drawee.d.b i() {
        return this.i;
    }

    public void a(@Nullable com.facebook.drawee.d.b bVar) {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.k, bVar);
        }
        this.b.a(bVar != null ? Event.ON_SET_HIERARCHY : Event.ON_CLEAR_HIERARCHY);
        if (this.n) {
            this.c.b(this);
            d();
        }
        if (this.i != null) {
            this.i.a(null);
            this.i = null;
        }
        if (bVar != null) {
            g.a(bVar instanceof c);
            this.i = (c) bVar;
            this.i.a(this.j);
        }
    }

    protected void b(@Nullable Drawable drawable) {
        this.j = drawable;
        if (this.i != null) {
            this.i.a(this.j);
        }
    }

    @Nullable
    protected Drawable j() {
        return this.j;
    }

    public void k() {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: onAttach: %s", Integer.valueOf(System.identityHashCode(this)), this.k, this.n ? "request already submitted" : "request needs submit");
        }
        this.b.a(Event.ON_ATTACH_CONTROLLER);
        g.a(this.i);
        this.c.b(this);
        this.m = true;
        if (!this.n) {
            n();
        }
    }

    public void l() {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: onDetach", Integer.valueOf(System.identityHashCode(this)), this.k);
        }
        this.b.a(Event.ON_DETACH_CONTROLLER);
        this.m = false;
        this.c.a(this);
    }

    public boolean a(MotionEvent motionEvent) {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: onTouchEvent %s", Integer.valueOf(System.identityHashCode(this)), this.k, motionEvent);
        }
        if (this.f == null) {
            return false;
        }
        if (!this.f.c() && !m()) {
            return false;
        }
        this.f.a(motionEvent);
        return true;
    }

    protected boolean m() {
        return p();
    }

    private boolean p() {
        return this.p && this.e != null && this.e.d();
    }

    public boolean onClick() {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: onClick", Integer.valueOf(System.identityHashCode(this)), this.k);
        }
        if (!p()) {
            return false;
        }
        this.e.e();
        this.i.b();
        n();
        return true;
    }

    protected void n() {
        Object c = c();
        if (c != null) {
            this.s = null;
            this.n = true;
            this.p = false;
            this.b.a(Event.ON_SUBMIT_CACHE_HIT);
            h().a(this.k, this.l);
            a(this.k, this.s, c, 1.0f, true, true);
            return;
        }
        this.b.a(Event.ON_DATASOURCE_SUBMIT);
        h().a(this.k, this.l);
        this.i.a(0.0f, true);
        this.n = true;
        this.p = false;
        this.s = a();
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.k, Integer.valueOf(System.identityHashCode(this.s)));
        }
        this.s.a(new a$1(this, this.k, this.s.c()), this.d);
    }

    private void a(String str, com.facebook.datasource.b<T> bVar, @Nullable T t, float f, boolean z, boolean z2) {
        if (a(str, (com.facebook.datasource.b) bVar)) {
            this.b.a(z ? Event.ON_DATASOURCE_RESULT : Event.ON_DATASOURCE_RESULT_INT);
            try {
                Drawable d = d(t);
                Object obj = this.t;
                Drawable drawable = this.u;
                this.t = t;
                this.u = d;
                if (z) {
                    try {
                        b("set_final_result @ onNewResult", t);
                        this.s = null;
                        this.i.a(d, 1.0f, z2);
                        h().a(str, c(t), o());
                    } catch (Throwable th) {
                        if (!(drawable == null || drawable == d)) {
                            a(drawable);
                        }
                        if (!(obj == null || obj == t)) {
                            b("release_previous_result @ onNewResult", obj);
                            a(obj);
                        }
                    }
                } else {
                    b("set_intermediate_result @ onNewResult", t);
                    this.i.a(d, f, z2);
                    h().b(str, c(t));
                }
                if (!(drawable == null || drawable == d)) {
                    a(drawable);
                }
                if (obj != null && obj != t) {
                    b("release_previous_result @ onNewResult", obj);
                    a(obj);
                    return;
                }
                return;
            } catch (Throwable e) {
                b("drawable_failed @ onNewResult", t);
                a((Object) t);
                a(str, (com.facebook.datasource.b) bVar, e, z);
                return;
            }
        }
        b("ignore_old_datasource @ onNewResult", t);
        a((Object) t);
        bVar.h();
    }

    private void a(String str, com.facebook.datasource.b<T> bVar, Throwable th, boolean z) {
        if (a(str, (com.facebook.datasource.b) bVar)) {
            this.b.a(z ? Event.ON_DATASOURCE_FAILURE : Event.ON_DATASOURCE_FAILURE_INT);
            if (z) {
                a("final_failed @ onFailure", th);
                this.s = null;
                this.p = true;
                if (this.q && this.u != null) {
                    this.i.a(this.u, 1.0f, true);
                } else if (p()) {
                    this.i.b(th);
                } else {
                    this.i.a(th);
                }
                h().a(this.k, th);
                return;
            }
            a("intermediate_failed @ onFailure", th);
            h().b(this.k, th);
            return;
        }
        a("ignore_old_datasource @ onFailure", th);
        bVar.h();
    }

    private void a(String str, com.facebook.datasource.b<T> bVar, float f, boolean z) {
        if (!a(str, (com.facebook.datasource.b) bVar)) {
            a("ignore_old_datasource @ onProgress", null);
            bVar.h();
        } else if (!z) {
            this.i.a(f, false);
        }
    }

    private boolean a(String str, com.facebook.datasource.b<T> bVar) {
        if (bVar == null && this.s == null) {
            return true;
        }
        if (str.equals(this.k) && bVar == this.s && this.n) {
            return true;
        }
        return false;
    }

    private void b(String str, T t) {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: %s: image: %s %x", new Object[]{Integer.valueOf(System.identityHashCode(this)), this.k, str, e(t), Integer.valueOf(b((Object) t))});
        }
    }

    private void a(String str, Throwable th) {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x %s: %s: failure: %s", Integer.valueOf(System.identityHashCode(this)), this.k, str, th);
        }
    }

    @Nullable
    public Animatable o() {
        return this.u instanceof Animatable ? (Animatable) this.u : null;
    }

    protected String e(@Nullable T t) {
        return t != null ? t.getClass().getSimpleName() : "<null>";
    }

    protected int b(@Nullable T t) {
        return System.identityHashCode(t);
    }

    public String toString() {
        return f.a((Object) this).a("isAttached", this.m).a("isRequestSubmitted", this.n).a("hasFetchFailed", this.p).a("fetchedImage", b(this.t)).a(EventStoreHelper.TABLE_EVENTS, this.b.toString()).toString();
    }

    protected T c() {
        return null;
    }
}
