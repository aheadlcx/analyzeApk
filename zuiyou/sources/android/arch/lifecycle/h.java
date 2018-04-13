package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class h extends Lifecycle {
    private android.arch.a.b.a<f, a> a = new android.arch.a.b.a();
    private State b;
    private final WeakReference<g> c;
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<State> g = new ArrayList();

    static class a {
        State a;
        GenericLifecycleObserver b;

        void a(g gVar, Event event) {
            State b = h.b(event);
            this.a = h.a(this.a, b);
            this.b.a(gVar, event);
            this.a = b;
        }
    }

    public h(@NonNull g gVar) {
        this.c = new WeakReference(gVar);
        this.b = State.INITIALIZED;
    }

    @MainThread
    public void a(@NonNull State state) {
        b(state);
    }

    public void a(@NonNull Event event) {
        b(b(event));
    }

    private void b(State state) {
        if (this.b != state) {
            this.b = state;
            if (this.e || this.d != 0) {
                this.f = true;
                return;
            }
            this.e = true;
            d();
            this.e = false;
        }
    }

    private boolean b() {
        if (this.a.a() == 0) {
            return true;
        }
        State state = ((a) this.a.d().getValue()).a;
        State state2 = ((a) this.a.e().getValue()).a;
        boolean z = state == state2 && this.b == state2;
        return z;
    }

    private void c() {
        this.g.remove(this.g.size() - 1);
    }

    private void c(State state) {
        this.g.add(state);
    }

    public void a(@NonNull f fVar) {
        this.a.b(fVar);
    }

    @NonNull
    public State a() {
        return this.b;
    }

    static State b(Event event) {
        switch (event) {
            case ON_CREATE:
            case ON_STOP:
                return State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return State.STARTED;
            case ON_RESUME:
                return State.RESUMED;
            case ON_DESTROY:
                return State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    private static Event d(State state) {
        switch (state) {
            case INITIALIZED:
                throw new IllegalArgumentException();
            case CREATED:
                return Event.ON_DESTROY;
            case STARTED:
                return Event.ON_STOP;
            case RESUMED:
                return Event.ON_PAUSE;
            case DESTROYED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private static Event e(State state) {
        switch (state) {
            case INITIALIZED:
            case DESTROYED:
                return Event.ON_CREATE;
            case CREATED:
                return Event.ON_START;
            case STARTED:
                return Event.ON_RESUME;
            case RESUMED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private void a(g gVar) {
        Iterator c = this.a.c();
        while (c.hasNext() && !this.f) {
            Entry entry = (Entry) c.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) < 0 && !this.f && this.a.c(entry.getKey())) {
                c(aVar.a);
                aVar.a(gVar, e(aVar.a));
                c();
            }
        }
    }

    private void b(g gVar) {
        Iterator b = this.a.b();
        while (b.hasNext() && !this.f) {
            Entry entry = (Entry) b.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) > 0 && !this.f && this.a.c(entry.getKey())) {
                Event d = d(aVar.a);
                c(b(d));
                aVar.a(gVar, d);
                c();
            }
        }
    }

    private void d() {
        g gVar = (g) this.c.get();
        if (gVar == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!b()) {
            this.f = false;
            if (this.b.compareTo(((a) this.a.d().getValue()).a) < 0) {
                b(gVar);
            }
            Entry e = this.a.e();
            if (!(this.f || e == null || this.b.compareTo(((a) e.getValue()).a) <= 0)) {
                a(gVar);
            }
        }
        this.f = false;
    }

    static State a(@NonNull State state, @Nullable State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }
}
