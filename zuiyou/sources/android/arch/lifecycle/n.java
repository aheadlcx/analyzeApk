package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.os.Handler;
import android.support.annotation.NonNull;

public class n {
    private final h a;
    private final Handler b = new Handler();
    private a c;

    static class a implements Runnable {
        final Event a;
        private final h b;
        private boolean c = false;

        a(@NonNull h hVar, Event event) {
            this.b = hVar;
            this.a = event;
        }

        public void run() {
            if (!this.c) {
                this.b.a(this.a);
                this.c = true;
            }
        }
    }

    public n(@NonNull g gVar) {
        this.a = new h(gVar);
    }

    private void a(Event event) {
        if (this.c != null) {
            this.c.run();
        }
        this.c = new a(this.a, event);
        this.b.postAtFrontOfQueue(this.c);
    }

    public void a() {
        a(Event.ON_CREATE);
    }

    public void b() {
        a(Event.ON_START);
    }

    public void c() {
        a(Event.ON_START);
    }

    public void d() {
        a(Event.ON_STOP);
        a(Event.ON_DESTROY);
    }

    public Lifecycle e() {
        return this.a;
    }
}
