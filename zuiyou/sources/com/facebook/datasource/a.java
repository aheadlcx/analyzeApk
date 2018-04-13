package com.facebook.datasource;

public abstract class a<T> implements d<T> {
    protected abstract void b(b<T> bVar);

    protected abstract void c(b<T> bVar);

    public void d(b<T> bVar) {
        boolean b = bVar.b();
        try {
            b(bVar);
        } finally {
            if (b) {
                bVar.h();
            }
        }
    }

    public void e(b<T> bVar) {
        try {
            c(bVar);
        } finally {
            bVar.h();
        }
    }

    public void f(b<T> bVar) {
    }

    public void a(b<T> bVar) {
    }
}
