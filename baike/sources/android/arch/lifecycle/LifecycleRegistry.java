package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class LifecycleRegistry extends Lifecycle {
    private FastSafeIterableMap<LifecycleObserver, a> a = new FastSafeIterableMap();
    private State b;
    private final LifecycleOwner c;
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<State> g = new ArrayList();

    static class a {
        State a;
        GenericLifecycleObserver b;

        a(LifecycleObserver lifecycleObserver, State state) {
            this.b = b.a((Object) lifecycleObserver);
            this.a = state;
        }

        void a(LifecycleOwner lifecycleOwner, Event event) {
            State a = LifecycleRegistry.a(event);
            this.a = LifecycleRegistry.a(this.a, a);
            this.b.onStateChanged(lifecycleOwner, event);
            this.a = a;
        }
    }

    public LifecycleRegistry(@NonNull LifecycleOwner lifecycleOwner) {
        this.c = lifecycleOwner;
        this.b = State.INITIALIZED;
    }

    public void markState(State state) {
        this.b = state;
    }

    public void handleLifecycleEvent(Event event) {
        this.b = a(event);
        if (this.e || this.d != 0) {
            this.f = true;
            return;
        }
        this.e = true;
        e();
        this.e = false;
    }

    private boolean a() {
        if (this.a.size() == 0) {
            return true;
        }
        State state = ((a) this.a.eldest().getValue()).a;
        State state2 = ((a) this.a.newest().getValue()).a;
        boolean z = state == state2 && this.b == state2;
        return z;
    }

    private State a(LifecycleObserver lifecycleObserver) {
        State state;
        State state2;
        Entry ceil = this.a.ceil(lifecycleObserver);
        if (ceil != null) {
            state = ((a) ceil.getValue()).a;
        } else {
            state = null;
        }
        if (this.g.isEmpty()) {
            state2 = null;
        } else {
            state2 = (State) this.g.get(this.g.size() - 1);
        }
        return a(a(this.b, state), state2);
    }

    public void addObserver(LifecycleObserver lifecycleObserver) {
        a aVar = new a(lifecycleObserver, this.b == State.DESTROYED ? State.DESTROYED : State.INITIALIZED);
        if (((a) this.a.putIfAbsent(lifecycleObserver, aVar)) == null) {
            Object obj = (this.d != 0 || this.e) ? 1 : null;
            Enum a = a(lifecycleObserver);
            this.d++;
            while (aVar.a.compareTo(a) < 0 && this.a.contains(lifecycleObserver)) {
                a(aVar.a);
                aVar.a(this.c, c(aVar.a));
                b();
                a = a(lifecycleObserver);
            }
            if (obj == null) {
                e();
            }
            this.d--;
        }
    }

    private void b() {
        this.g.remove(this.g.size() - 1);
    }

    private void a(State state) {
        this.g.add(state);
    }

    public void removeObserver(LifecycleObserver lifecycleObserver) {
        this.a.remove(lifecycleObserver);
    }

    public int getObserverCount() {
        return this.a.size();
    }

    public State getCurrentState() {
        return this.b;
    }

    static State a(Event event) {
        switch (a.a[event.ordinal()]) {
            case 1:
            case 2:
                return State.CREATED;
            case 3:
            case 4:
                return State.STARTED;
            case 5:
                return State.RESUMED;
            case 6:
                return State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    private static Event b(State state) {
        switch (a.b[state.ordinal()]) {
            case 1:
                throw new IllegalArgumentException();
            case 2:
                return Event.ON_DESTROY;
            case 3:
                return Event.ON_STOP;
            case 4:
                return Event.ON_PAUSE;
            case 5:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private static Event c(State state) {
        switch (a.b[state.ordinal()]) {
            case 1:
            case 5:
                return Event.ON_CREATE;
            case 2:
                return Event.ON_START;
            case 3:
                return Event.ON_RESUME;
            case 4:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private void c() {
        Iterator iteratorWithAdditions = this.a.iteratorWithAdditions();
        while (iteratorWithAdditions.hasNext() && !this.f) {
            Entry entry = (Entry) iteratorWithAdditions.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) < 0 && !this.f && this.a.contains(entry.getKey())) {
                a(aVar.a);
                aVar.a(this.c, c(aVar.a));
                b();
            }
        }
    }

    private void d() {
        Iterator descendingIterator = this.a.descendingIterator();
        while (descendingIterator.hasNext() && !this.f) {
            Entry entry = (Entry) descendingIterator.next();
            a aVar = (a) entry.getValue();
            while (aVar.a.compareTo(this.b) > 0 && !this.f && this.a.contains(entry.getKey())) {
                Event b = b(aVar.a);
                a(a(b));
                aVar.a(this.c, b);
                b();
            }
        }
    }

    private void e() {
        while (!a()) {
            this.f = false;
            if (this.b.compareTo(((a) this.a.eldest().getValue()).a) < 0) {
                d();
            }
            Entry newest = this.a.newest();
            if (!(this.f || newest == null || this.b.compareTo(((a) newest.getValue()).a) <= 0)) {
                c();
            }
        }
        this.f = false;
    }

    static State a(@NonNull State state, @Nullable State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }
}
