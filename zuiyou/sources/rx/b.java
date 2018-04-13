package rx;

import rx.b.g;
import rx.e.c;
import rx.g.e;

public class b {
    static final b a = new b(new a() {
        public /* synthetic */ void call(Object obj) {
            a((c) obj);
        }

        public void a(c cVar) {
            cVar.a(e.a());
            cVar.a();
        }
    }, false);
    static final b b = new b(new a() {
        public /* synthetic */ void call(Object obj) {
            a((c) obj);
        }

        public void a(c cVar) {
            cVar.a(e.a());
        }
    }, false);
    private final a c;

    public interface a extends rx.b.b<c> {
    }

    public interface b extends g<c, c> {
    }

    public static b a(a aVar) {
        NullPointerException e;
        a((Object) aVar);
        try {
            return new b(aVar);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a(th);
            e2 = a(th);
        }
    }

    public static b a(final d<?> dVar) {
        a((Object) dVar);
        return a(new a() {
            public /* synthetic */ void call(Object obj) {
                a((c) obj);
            }

            public void a(final c cVar) {
                k anonymousClass1 = new j<Object>(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void onCompleted() {
                        cVar.a();
                    }

                    public void onError(Throwable th) {
                        cVar.a(th);
                    }

                    public void onNext(Object obj) {
                    }
                };
                cVar.a(anonymousClass1);
                dVar.a(anonymousClass1);
            }
        });
    }

    static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static NullPointerException a(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    protected b(a aVar) {
        this.c = c.a(aVar);
    }

    protected b(a aVar, boolean z) {
        if (z) {
            aVar = c.a(aVar);
        }
        this.c = aVar;
    }
}
