package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    private final FullLifecycleObserver a;

    public void a(g gVar, Event event) {
        switch (event) {
            case ON_CREATE:
                this.a.a(gVar);
                return;
            case ON_START:
                this.a.b(gVar);
                return;
            case ON_RESUME:
                this.a.c(gVar);
                return;
            case ON_PAUSE:
                this.a.d(gVar);
                return;
            case ON_STOP:
                this.a.e(gVar);
                return;
            case ON_DESTROY:
                this.a.f(gVar);
                return;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
