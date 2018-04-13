package com.alipay.sdk.widget;

final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (a.c(this.a) == null) {
            a.a(this.a, new a$a(this.a, a.a(this.a)));
        }
        try {
            if (!a.c(this.a).isShowing()) {
                a.c(this.a).show();
            }
        } catch (Exception e) {
        }
    }
}
