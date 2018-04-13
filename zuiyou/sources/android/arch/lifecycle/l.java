package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

public class l implements g {
    private static final l i = new l();
    private int a = 0;
    private int b = 0;
    private boolean c = true;
    private boolean d = true;
    private Handler e;
    private final h f = new h(this);
    private Runnable g = new Runnable(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.e();
            this.a.f();
        }
    };
    private a h = new a(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public void a() {
        }

        public void b() {
            this.a.a();
        }

        public void c() {
            this.a.b();
        }
    };

    static void a(Context context) {
        i.b(context);
    }

    void a() {
        this.a++;
        if (this.a == 1 && this.d) {
            this.f.a(Event.ON_START);
            this.d = false;
        }
    }

    void b() {
        this.b++;
        if (this.b != 1) {
            return;
        }
        if (this.c) {
            this.f.a(Event.ON_RESUME);
            this.c = false;
            return;
        }
        this.e.removeCallbacks(this.g);
    }

    void c() {
        this.b--;
        if (this.b == 0) {
            this.e.postDelayed(this.g, 700);
        }
    }

    void d() {
        this.a--;
        f();
    }

    private void e() {
        if (this.b == 0) {
            this.c = true;
            this.f.a(Event.ON_PAUSE);
        }
    }

    private void f() {
        if (this.a == 0 && this.c) {
            this.f.a(Event.ON_STOP);
            this.d = true;
        }
    }

    private l() {
    }

    void b(Context context) {
        this.e = new Handler();
        this.f.a(Event.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new b(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                m.b(activity).a(this.a.h);
            }

            public void onActivityPaused(Activity activity) {
                this.a.c();
            }

            public void onActivityStopped(Activity activity) {
                this.a.d();
            }
        });
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return this.f;
    }
}
