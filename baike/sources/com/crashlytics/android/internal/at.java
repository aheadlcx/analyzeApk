package com.crashlytics.android.internal;

final class at implements Runnable {
    private /* synthetic */ bf a;
    private /* synthetic */ boolean b;
    private /* synthetic */ ac c;

    at(ac acVar, bf bfVar, boolean z) {
        this.c = acVar;
        this.a = bfVar;
        this.b = z;
    }

    public final void run() {
        try {
            this.c.a.a(this.a);
            if (this.b) {
                this.c.a.d();
            }
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to record session event.");
        }
    }
}
