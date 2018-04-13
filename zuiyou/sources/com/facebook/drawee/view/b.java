package com.facebook.drawee.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.f;
import com.facebook.common.internal.g;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.DraweeEventTracker.Event;
import com.facebook.drawee.d.a;
import com.facebook.drawee.drawable.q;
import com.facebook.drawee.drawable.r;
import com.meizu.cloud.pushsdk.pushtracer.storage.EventStoreHelper;
import javax.annotation.Nullable;

public class b<DH extends com.facebook.drawee.d.b> implements r {
    private boolean a = false;
    private boolean b = false;
    private boolean c = true;
    private DH d;
    private a e = null;
    private final DraweeEventTracker f = DraweeEventTracker.a();

    public static <DH extends com.facebook.drawee.d.b> b<DH> a(@Nullable DH dh, Context context) {
        b<DH> bVar = new b(dh);
        bVar.a(context);
        return bVar;
    }

    public void a(Context context) {
    }

    public b(@Nullable DH dh) {
        if (dh != null) {
            a((com.facebook.drawee.d.b) dh);
        }
    }

    public void b() {
        this.f.a(Event.ON_HOLDER_ATTACH);
        this.b = true;
        i();
    }

    public void c() {
        this.f.a(Event.ON_HOLDER_DETACH);
        this.b = false;
        i();
    }

    public boolean a(MotionEvent motionEvent) {
        if (j()) {
            return this.e.a(motionEvent);
        }
        return false;
    }

    public void a(boolean z) {
        if (this.c != z) {
            this.f.a(z ? Event.ON_DRAWABLE_SHOW : Event.ON_DRAWABLE_HIDE);
            this.c = z;
            i();
        }
    }

    public void a() {
        if (!this.a) {
            com.facebook.common.c.a.d(DraweeEventTracker.class, "%x: Draw requested for a non-attached controller %x. %s", new Object[]{Integer.valueOf(System.identityHashCode(this)), Integer.valueOf(System.identityHashCode(this.e)), toString()});
            this.b = true;
            this.c = true;
            i();
        }
    }

    private void a(@Nullable r rVar) {
        Drawable f = f();
        if (f instanceof q) {
            ((q) f).a(rVar);
        }
    }

    public void a(@Nullable a aVar) {
        boolean z = this.a;
        if (z) {
            h();
        }
        if (j()) {
            this.f.a(Event.ON_CLEAR_OLD_CONTROLLER);
            this.e.a(null);
        }
        this.e = aVar;
        if (this.e != null) {
            this.f.a(Event.ON_SET_CONTROLLER);
            this.e.a(this.d);
        } else {
            this.f.a(Event.ON_CLEAR_CONTROLLER);
        }
        if (z) {
            g();
        }
    }

    @Nullable
    public a d() {
        return this.e;
    }

    public void a(DH dh) {
        this.f.a(Event.ON_SET_HIERARCHY);
        boolean j = j();
        a(null);
        this.d = (com.facebook.drawee.d.b) g.a(dh);
        Drawable a = this.d.a();
        boolean z = a == null || a.isVisible();
        a(z);
        a((r) this);
        if (j) {
            this.e.a((com.facebook.drawee.d.b) dh);
        }
    }

    public DH e() {
        return (com.facebook.drawee.d.b) g.a(this.d);
    }

    public Drawable f() {
        return this.d == null ? null : this.d.a();
    }

    private void g() {
        if (!this.a) {
            this.f.a(Event.ON_ATTACH_CONTROLLER);
            this.a = true;
            if (this.e != null && this.e.i() != null) {
                this.e.k();
            }
        }
    }

    private void h() {
        if (this.a) {
            this.f.a(Event.ON_DETACH_CONTROLLER);
            this.a = false;
            if (j()) {
                this.e.l();
            }
        }
    }

    private void i() {
        if (this.b && this.c) {
            g();
        } else {
            h();
        }
    }

    public String toString() {
        return f.a((Object) this).a("controllerAttached", this.a).a("holderAttached", this.b).a("drawableVisible", this.c).a(EventStoreHelper.TABLE_EVENTS, this.f.toString()).toString();
    }

    private boolean j() {
        return this.e != null && this.e.i() == this.d;
    }
}
