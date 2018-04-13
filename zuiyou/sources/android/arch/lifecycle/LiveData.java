package android.arch.lifecycle;

import android.arch.a.a.a;
import android.arch.a.b.b;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    private static final Object a = new Object();
    private static final g b = new g() {
        private h a = a();

        private h a() {
            h hVar = new h(this);
            hVar.a(Event.ON_CREATE);
            hVar.a(Event.ON_START);
            hVar.a(Event.ON_RESUME);
            return hVar;
        }

        public Lifecycle getLifecycle() {
            return this.a;
        }
    };
    private b<k<T>, LifecycleBoundObserver> c;
    private int d;
    private volatile Object e;
    private int f;
    private boolean g;
    private boolean h;

    class LifecycleBoundObserver implements GenericLifecycleObserver {
        public final g a;
        public final k<T> b;
        public boolean c;
        public int d;
        final /* synthetic */ LiveData e;

        public void a(g gVar, Event event) {
            if (this.a.getLifecycle().a() == State.DESTROYED) {
                this.e.a(this.b);
            } else {
                a(LiveData.a(this.a.getLifecycle().a()));
            }
        }

        void a(boolean z) {
            int i = 1;
            if (z != this.c) {
                this.c = z;
                int i2 = this.e.d == 0 ? 1 : 0;
                LiveData liveData = this.e;
                int a = liveData.d;
                if (!this.c) {
                    i = -1;
                }
                liveData.d = i + a;
                if (i2 != 0 && this.c) {
                    this.e.a();
                }
                if (this.e.d == 0 && !this.c) {
                    this.e.b();
                }
                if (this.c) {
                    this.e.b(this);
                }
            }
        }
    }

    private void a(LifecycleBoundObserver lifecycleBoundObserver) {
        if (!lifecycleBoundObserver.c) {
            return;
        }
        if (!a(lifecycleBoundObserver.a.getLifecycle().a())) {
            lifecycleBoundObserver.a(false);
        } else if (lifecycleBoundObserver.d < this.f) {
            lifecycleBoundObserver.d = this.f;
            lifecycleBoundObserver.b.a(this.e);
        }
    }

    private void b(@Nullable LifecycleBoundObserver lifecycleBoundObserver) {
        if (this.g) {
            this.h = true;
            return;
        }
        this.g = true;
        do {
            this.h = false;
            if (lifecycleBoundObserver == null) {
                Iterator c = this.c.c();
                while (c.hasNext()) {
                    a((LifecycleBoundObserver) ((Entry) c.next()).getValue());
                    if (this.h) {
                        break;
                    }
                }
            }
            a((LifecycleBoundObserver) lifecycleBoundObserver);
            lifecycleBoundObserver = null;
        } while (this.h);
        this.g = false;
    }

    @MainThread
    public void a(@NonNull k<T> kVar) {
        a("removeObserver");
        LifecycleBoundObserver lifecycleBoundObserver = (LifecycleBoundObserver) this.c.b(kVar);
        if (lifecycleBoundObserver != null) {
            lifecycleBoundObserver.a.getLifecycle().a(lifecycleBoundObserver);
            lifecycleBoundObserver.a(false);
        }
    }

    protected void a() {
    }

    protected void b() {
    }

    static boolean a(State state) {
        return state.isAtLeast(State.STARTED);
    }

    private void a(String str) {
        if (!a.a().b()) {
            throw new IllegalStateException("Cannot invoke " + str + " on a background" + " thread");
        }
    }
}
