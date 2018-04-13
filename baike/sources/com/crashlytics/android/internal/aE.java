package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.concurrent.Callable;

public abstract class aE<V> implements Callable<V> {
    protected abstract V a() throws aD, IOException;

    protected abstract void b() throws IOException;

    protected aE() {
    }

    public V call() throws aD {
        Throwable th;
        Object obj = 1;
        try {
            V a = a();
            try {
                b();
                return a;
            } catch (IOException e) {
                throw new aD(e);
            }
        } catch (aD e2) {
            throw e2;
        } catch (IOException e3) {
            throw new aD(e3);
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            b();
        } catch (IOException e4) {
            if (obj == null) {
                throw new aD(e4);
            }
        }
        throw th;
    }
}
