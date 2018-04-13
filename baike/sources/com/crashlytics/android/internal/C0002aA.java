package com.crashlytics.android.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* renamed from: com.crashlytics.android.internal.aA */
public abstract class C0002aA<V> extends aE<V> {
    private final Closeable a;
    private final boolean b;

    protected C0002aA(Closeable closeable, boolean z) {
        this.a = closeable;
        this.b = z;
    }

    protected final void b() throws IOException {
        if (this.a instanceof Flushable) {
            ((Flushable) this.a).flush();
        }
        if (this.b) {
            try {
                this.a.close();
                return;
            } catch (IOException e) {
                return;
            }
        }
        this.a.close();
    }
}
