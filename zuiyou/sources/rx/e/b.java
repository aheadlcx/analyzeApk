package rx.e;

import rx.exceptions.a;

public abstract class b {
    @Deprecated
    public void a(Throwable th) {
    }

    public final String a(Object obj) {
        try {
            return b(obj);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return obj.getClass().getName() + ".errorRendering";
        } catch (Throwable th) {
            a.b(th);
            return obj.getClass().getName() + ".errorRendering";
        }
    }

    protected String b(Object obj) throws InterruptedException {
        return null;
    }
}
