package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class n implements be {
    private final ScheduledExecutorService a;
    private final o b;
    private final C0011av c;
    private ScheduledFuture<?> d;
    private int e = -1;
    private y f;

    public n(ScheduledExecutorService scheduledExecutorService, o oVar, C0011av c0011av) {
        this.a = scheduledExecutorService;
        this.b = oVar;
        this.c = c0011av;
    }

    public final void a() {
        Exception e;
        if (this.f == null) {
            C0003ab.c("skipping analytics files send because we don't yet know the target endpoint");
            return;
        }
        C0003ab.c("Sending all analytics files");
        List b = this.b.b();
        int i = 0;
        while (b.size() > 0) {
            int size;
            try {
                boolean a = this.f.a(r.a(D.a().getContext(), false), b);
                if (a) {
                    size = b.size() + i;
                    try {
                        this.b.a(b);
                        i = size;
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                Locale locale = Locale.US;
                String str = "attempt to send batch of %d analytics files %s";
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(b.size());
                objArr[1] = a ? "succeeded" : "did not succeed";
                C0003ab.c(String.format(locale, str, objArr));
                if (!a) {
                    break;
                }
                b = this.b.b();
            } catch (Exception e3) {
                Exception exception = e3;
                size = i;
                e = exception;
            }
        }
        if (i == 0) {
            this.b.d();
        }
        C0003ab.d("Crashlytics failed to send batch of analytics files to server: " + e.getMessage());
        i = size;
        if (i == 0) {
            this.b.d();
        }
    }

    private void a(int i, int i2) {
        try {
            Runnable biVar = new bi(this.b, this);
            C0003ab.c("Scheduling time based file roll over every " + i2 + " seconds");
            this.d = this.a.scheduleAtFixedRate(biVar, (long) i, (long) i2, TimeUnit.SECONDS);
        } catch (RejectedExecutionException e) {
            C0003ab.d("Crashlytics failed to schedule time based analytics file roll over");
        }
    }

    public final void c() {
        if (this.d != null) {
            C0003ab.c("Cancelling time-based rollover because no events are currently being generated.");
            this.d.cancel(false);
            this.d = null;
        }
    }

    public final void a(aK aKVar, String str) {
        this.f = new i(str, aKVar.a, this.c);
        this.b.a(aKVar);
        this.e = aKVar.b;
        a(0, this.e);
    }

    public final void b() {
        this.b.c();
    }

    public final void a(bf bfVar) {
        Object obj;
        Object obj2 = 1;
        C0003ab.c(bfVar.toString());
        try {
            this.b.a(bfVar);
        } catch (IOException e) {
            C0003ab.d("Crashlytics failed to write session event.");
        }
        if (this.e != -1) {
            obj = 1;
        } else {
            obj = null;
        }
        if (this.d != null) {
            obj2 = null;
        }
        if (obj != null && r1 != null) {
            a(this.e, this.e);
        }
    }

    public final void d() {
        try {
            this.b.a();
        } catch (IOException e) {
            C0003ab.d("Crashlytics failed to roll analytics file over.");
        }
    }
}
