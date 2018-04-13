package cz.msebera.android.httpclient.pool;

class a extends f<T, C, E> {
    final /* synthetic */ Object a;
    final /* synthetic */ AbstractConnPool b;

    a(AbstractConnPool abstractConnPool, Object obj, Object obj2) {
        this.b = abstractConnPool;
        this.a = obj2;
        super(obj);
    }

    protected E a(C c) {
        return this.b.a(this.a, c);
    }
}
