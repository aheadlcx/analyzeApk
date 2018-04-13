package com.facebook.imagepipeline.producers;

import com.facebook.common.references.a;

class ah$a$2 implements Runnable {
    final /* synthetic */ ah$a a;

    ah$a$2(ah$a ah_a) {
        this.a = ah_a;
    }

    public void run() {
        synchronized (this.a) {
            a b = ah$a.b(this.a);
            boolean c = ah$a.c(this.a);
            ah$a.a(this.a, null);
            ah$a.a(this.a, false);
        }
        if (a.a(b)) {
            try {
                ah$a.a(this.a, b, c);
            } finally {
                a.c(b);
            }
        }
        ah$a.d(this.a);
    }
}
