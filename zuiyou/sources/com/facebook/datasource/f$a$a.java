package com.facebook.datasource;

class f$a$a implements d<T> {
    final /* synthetic */ f$a a;
    private int b;

    public f$a$a(f$a f_a, int i) {
        this.a = f_a;
        this.b = i;
    }

    public void d(b<T> bVar) {
        if (bVar.c()) {
            f$a.a(this.a, this.b, bVar);
        } else if (bVar.b()) {
            f$a.b(this.a, this.b, bVar);
        }
    }

    public void e(b<T> bVar) {
        f$a.b(this.a, this.b, bVar);
    }

    public void f(b<T> bVar) {
    }

    public void a(b<T> bVar) {
        if (this.b == 0) {
            this.a.a(bVar.g());
        }
    }
}
