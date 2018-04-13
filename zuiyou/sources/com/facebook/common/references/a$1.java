package com.facebook.common.references;

import com.facebook.common.internal.b;
import java.io.Closeable;
import java.io.IOException;

class a$1 implements c<Closeable> {
    a$1() {
    }

    public /* synthetic */ void release(Object obj) {
        a((Closeable) obj);
    }

    public void a(Closeable closeable) {
        try {
            b.a(closeable, true);
        } catch (IOException e) {
        }
    }
}
