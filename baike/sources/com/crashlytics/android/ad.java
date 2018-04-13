package com.crashlytics.android;

import com.crashlytics.android.internal.aa;
import com.crashlytics.android.internal.v;
import java.util.List;

final class ad extends aa {
    private final float a;
    private /* synthetic */ ab b;

    ad(ab abVar, float f) {
        this.b = abVar;
        this.a = f;
    }

    public final void a() {
        try {
            v.a().b().a(Crashlytics.TAG, "Starting report processing in " + this.a + " second(s)...");
            if (this.a > 0.0f) {
                try {
                    Thread.sleep((long) (this.a * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Crashlytics instance = Crashlytics.getInstance();
            ba l = instance.l();
            List<z> a = this.b.a();
            if (!l.a()) {
                if (a.isEmpty() || instance.p()) {
                    int i = 0;
                    while (!a.isEmpty() && !Crashlytics.getInstance().l().a()) {
                        v.a().b().a(Crashlytics.TAG, "Attempting to send " + a.size() + " report(s)");
                        for (z a2 : a) {
                            this.b.a(a2);
                        }
                        List a3 = this.b.a();
                        if (!a3.isEmpty()) {
                            int i2 = i + 1;
                            long j = (long) ab.c[Math.min(i, ab.c.length - 1)];
                            v.a().b().a(Crashlytics.TAG, "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                } else {
                    v.a().b().a(Crashlytics.TAG, "User declined to send. Removing " + a.size() + " Report(s).");
                    for (z a22 : a) {
                        a22.a();
                    }
                }
            }
        } catch (Throwable e3) {
            v.a().b().a(Crashlytics.TAG, "An unexpected error occurred while attempting to upload crash reports.", e3);
        }
        this.b.f = null;
    }
}
