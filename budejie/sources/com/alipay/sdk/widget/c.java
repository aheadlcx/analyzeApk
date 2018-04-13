package com.alipay.sdk.widget;

final class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (a.c(this.a) != null) {
            try {
                a.c(this.a).dismiss();
            } catch (Exception e) {
            }
        }
    }
}
