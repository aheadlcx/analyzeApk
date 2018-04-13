package com.crashlytics.android.internal;

final class bd implements Runnable {
    private /* synthetic */ ac a;

    bd(ac acVar) {
        this.a = acVar;
    }

    public final void run() {
        try {
            be beVar = this.a.a;
            this.a.a = new j();
            beVar.b();
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to disable analytics.");
        }
    }
}
