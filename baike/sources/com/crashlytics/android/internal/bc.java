package com.crashlytics.android.internal;

final class bc implements Runnable {
    private /* synthetic */ ac a;

    bc(ac acVar) {
        this.a = acVar;
    }

    public final void run() {
        try {
            this.a.a.a();
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to send analytics files.");
        }
    }
}
