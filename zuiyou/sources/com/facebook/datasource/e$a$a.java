package com.facebook.datasource;

class e$a$a implements d<T> {
    final /* synthetic */ e$a a;

    private e$a$a(e$a e_a) {
        this.a = e_a;
    }

    public void e(b<T> bVar) {
        e$a.a(this.a, bVar);
    }

    public void f(b<T> bVar) {
    }

    public void d(b<T> bVar) {
        if (bVar.c()) {
            e$a.b(this.a, bVar);
        } else if (bVar.b()) {
            e$a.a(this.a, bVar);
        }
    }

    public void a(b<T> bVar) {
        this.a.a(Math.max(this.a.g(), bVar.g()));
    }
}
