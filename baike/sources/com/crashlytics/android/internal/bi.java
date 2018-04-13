package com.crashlytics.android.internal;

final class bi implements Runnable {
    private final o a;
    private final be b;

    public bi(o oVar, be beVar) {
        this.a = oVar;
        this.b = beVar;
    }

    public final void run() {
        try {
            C0003ab.c("Performing time based analytics file roll over.");
            if (!this.a.a()) {
                this.b.c();
            }
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to roll over session analytics file");
        }
    }
}
