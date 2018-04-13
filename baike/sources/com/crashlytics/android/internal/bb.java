package com.crashlytics.android.internal;

final class bb implements Runnable {
    private /* synthetic */ aK a;
    private /* synthetic */ String b;
    private /* synthetic */ ac c;

    bb(ac acVar, aK aKVar, String str) {
        this.c = acVar;
        this.a = aKVar;
        this.b = str;
    }

    public final void run() {
        try {
            this.c.a.a(this.a, this.b);
        } catch (Exception e) {
            C0003ab.d("Crashlytics failed to set analytics settings data.");
        }
    }
}
