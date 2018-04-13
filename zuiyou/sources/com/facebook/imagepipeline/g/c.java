package com.facebook.imagepipeline.g;

import com.facebook.common.c.a;
import java.io.Closeable;

public abstract class c implements f, Closeable {
    public abstract boolean c();

    public abstract void close();

    public abstract int d();

    public h g() {
        return g.a;
    }

    public boolean e() {
        return false;
    }

    protected void finalize() throws Throwable {
        if (!c()) {
            a.b("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }
}
