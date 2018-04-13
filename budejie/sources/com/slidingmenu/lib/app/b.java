package com.slidingmenu.lib.app;

class b implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ boolean b;
    final /* synthetic */ a c;

    b(a aVar, boolean z, boolean z2) {
        this.c = aVar;
        this.a = z;
        this.b = z2;
    }

    public void run() {
        if (!this.a) {
            a.a(this.c).c(false);
        } else if (this.b) {
            a.a(this.c).b(false);
        } else {
            a.a(this.c).a(false);
        }
    }
}
