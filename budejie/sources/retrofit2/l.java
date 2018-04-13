package retrofit2;

import okhttp3.aa;
import okhttp3.ab;
import okhttp3.r;

public final class l<T> {
    private final aa a;
    private final T b;
    private final ab c;

    public static <T> l<T> a(T t, aa aaVar) {
        if (aaVar == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (aaVar.d()) {
            return new l(aaVar, t, null);
        } else {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
    }

    public static <T> l<T> a(ab abVar, aa aaVar) {
        if (abVar == null) {
            throw new NullPointerException("body == null");
        } else if (aaVar == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (!aaVar.d()) {
            return new l(aaVar, null, abVar);
        } else {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
    }

    private l(aa aaVar, T t, ab abVar) {
        this.a = aaVar;
        this.b = t;
        this.c = abVar;
    }

    public int a() {
        return this.a.c();
    }

    public String b() {
        return this.a.e();
    }

    public r c() {
        return this.a.g();
    }

    public boolean d() {
        return this.a.d();
    }

    public T e() {
        return this.b;
    }

    public String toString() {
        return this.a.toString();
    }
}
