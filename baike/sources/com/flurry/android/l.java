package com.flurry.android;

final class l implements Runnable {
    private /* synthetic */ b a;

    l(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        FlurryAgent.b(this.a.b, this.a.a);
    }
}
