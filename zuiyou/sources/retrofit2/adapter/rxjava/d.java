package retrofit2.adapter.rxjava;

import javax.annotation.Nullable;
import retrofit2.l;

public final class d<T> {
    @Nullable
    private final l<T> a;
    @Nullable
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

    private d(@Nullable l<T> lVar, @Nullable Throwable th) {
        this.a = lVar;
        this.b = th;
    }

    public String toString() {
        if (this.b != null) {
            return "Result{isError=true, error=\"" + this.b + "\"}";
        }
        return "Result{isError=false, response=" + this.a + '}';
    }
}
