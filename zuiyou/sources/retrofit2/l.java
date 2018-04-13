package retrofit2;

import javax.annotation.Nullable;
import okhttp3.aa;
import okhttp3.ab;

public final class l<T> {
    private final aa a;
    @Nullable
    private final T b;
    @Nullable
    private final ab c;

    public static <T> l<T> a(@Nullable T t, aa aaVar) {
        o.a(aaVar, "rawResponse == null");
        if (aaVar.c()) {
            return new l(aaVar, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    public static <T> l<T> a(ab abVar, aa aaVar) {
        o.a(abVar, "body == null");
        o.a(aaVar, "rawResponse == null");
        if (!aaVar.c()) {
            return new l(aaVar, null, abVar);
        }
        throw new IllegalArgumentException("rawResponse should not be successful response");
    }

    private l(aa aaVar, @Nullable T t, @Nullable ab abVar) {
        this.a = aaVar;
        this.b = t;
        this.c = abVar;
    }

    public aa a() {
        return this.a;
    }

    public int b() {
        return this.a.b();
    }

    public String c() {
        return this.a.d();
    }

    public boolean d() {
        return this.a.c();
    }

    @Nullable
    public T e() {
        return this.b;
    }

    public String toString() {
        return this.a.toString();
    }
}
