package com.facebook.imagepipeline.producers;

import com.facebook.common.c.a;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class b<T> implements j<T> {
    private boolean a = false;

    protected abstract void a();

    protected abstract void a(T t, boolean z);

    protected abstract void a(Throwable th);

    public synchronized void b(@Nullable T t, boolean z) {
        if (!this.a) {
            this.a = z;
            try {
                a(t, z);
            } catch (Exception e) {
                a(e);
            }
        }
    }

    public synchronized void b(Throwable th) {
        if (!this.a) {
            this.a = true;
            try {
                a(th);
            } catch (Exception e) {
                a(e);
            }
        }
    }

    public synchronized void b() {
        if (!this.a) {
            this.a = true;
            try {
                a();
            } catch (Exception e) {
                a(e);
            }
        }
    }

    public synchronized void b(float f) {
        if (!this.a) {
            try {
                a(f);
            } catch (Exception e) {
                a(e);
            }
        }
    }

    protected void a(float f) {
    }

    protected void a(Exception exception) {
        a.c(getClass(), "unhandled exception", (Throwable) exception);
    }
}
