package retrofit2.adapter.rxjava2;

import retrofit2.l;

public final class d<T> {
    private final l<T> a;
    private final Throwable b;

    public static <T> d<T> a(Throwable th) {
        if (th != null) {
            return new d(null, th);
        }
        throw new NullPointerException("error == null");
    }

    public static <T> d<T> a(l<T> lVar) {
        if (lVar != null) {
            return new d(lVar, null);
        }
        throw new NullPointerException("response == null");
    }

    private d(l<T> lVar, Throwable th) {
        this.a = lVar;
        this.b = th;
    }
}
