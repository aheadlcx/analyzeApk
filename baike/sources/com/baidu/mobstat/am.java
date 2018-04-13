package com.baidu.mobstat;

import android.content.Context;

class am implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ al c;

    am(al alVar, String str, Context context) {
        this.c = alVar;
        this.a = str;
        this.b = context;
    }

    public void run() {
        try {
            this.c.a(this.a);
            if (this.b != null) {
                this.c.a(this.b.getApplicationContext());
            }
        } catch (Throwable th) {
            bd.b(th);
        }
    }
}
