package rx.internal.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.d$b;
import rx.exceptions.OnErrorNotImplementedException;

public enum InternalObservableUtils {
    ;
    
    public static final g COUNTER = null;
    static final e ERROR_EXTRACTOR = null;
    public static final rx.b.b<Throwable> ERROR_NOT_IMPLEMENTED = null;
    public static final d$b<Boolean, Object> IS_EMPTY = null;
    public static final h LONG_COUNTER = null;
    public static final f OBJECT_EQUALS = null;
    static final o RETURNS_VOID = null;
    public static final q TO_ARRAY = null;

    static final class a<T, R> implements rx.b.h<R, T, R> {
        final rx.b.c<R, ? super T> a;

        public a(rx.b.c<R, ? super T> cVar) {
            this.a = cVar;
        }

        public R a(R r, T t) {
            this.a.a(r, t);
            return r;
        }
    }

    static final class b implements rx.b.g<Object, Boolean> {
        final Object a;

        public /* synthetic */ Object call(Object obj) {
            return a(obj);
        }

        public b(Object obj) {
            this.a = obj;
        }

        public Boolean a(Object obj) {
            boolean z = obj == this.a || (obj != null && obj.equals(this.a));
            return Boolean.valueOf(z);
        }
    }

    static final class c implements rx.b.b<Throwable> {
        c() {
        }

        public /* synthetic */ void call(Object obj) {
            a((Throwable) obj);
        }

        public void a(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    }

    static final class d implements rx.b.g<Object, Boolean> {
        final Class<?> a;

        public /* synthetic */ Object call(Object obj) {
            return a(obj);
        }

        public d(Class<?> cls) {
            this.a = cls;
        }

        public Boolean a(Object obj) {
            return Boolean.valueOf(this.a.isInstance(obj));
        }
    }

    static final class e implements rx.b.g<Notification<?>, Throwable> {
        e() {
        }

        public /* synthetic */ Object call(Object obj) {
            return a((Notification) obj);
        }

        public Throwable a(Notification<?> notification) {
            return notification.a();
        }
    }

    static final class f implements rx.b.h<Object, Object, Boolean> {
        f() {
        }

        public /* synthetic */ Object a(Object obj, Object obj2) {
            return b(obj, obj2);
        }

        public Boolean b(Object obj, Object obj2) {
            boolean z = obj == obj2 || (obj != null && obj.equals(obj2));
            return Boolean.valueOf(z);
        }
    }

    static final class g implements rx.b.h<Integer, Object, Integer> {
        g() {
        }

        public Integer a(Integer num, Object obj) {
            return Integer.valueOf(num.intValue() + 1);
        }
    }

    static final class h implements rx.b.h<Long, Object, Long> {
        h() {
        }

        public Long a(Long l, Object obj) {
            return Long.valueOf(l.longValue() + 1);
        }
    }

    static final class i implements rx.b.g<rx.d<? extends Notification<?>>, rx.d<?>> {
        final rx.b.g<? super rx.d<? extends Void>, ? extends rx.d<?>> a;

        public /* synthetic */ Object call(Object obj) {
            return a((rx.d) obj);
        }

        public i(rx.b.g<? super rx.d<? extends Void>, ? extends rx.d<?>> gVar) {
            this.a = gVar;
        }

        public rx.d<?> a(rx.d<? extends Notification<?>> dVar) {
            return (rx.d) this.a.call(dVar.d(InternalObservableUtils.RETURNS_VOID));
        }
    }

    static final class j<T> implements rx.b.f<rx.c.a<T>> {
        private final rx.d<T> a;
        private final int b;

        public /* synthetic */ Object call() {
            return a();
        }

        j(rx.d<T> dVar, int i) {
            this.a = dVar;
            this.b = i;
        }

        public rx.c.a<T> a() {
            return this.a.a(this.b);
        }
    }

    static final class k<T> implements rx.b.f<rx.c.a<T>> {
        private final TimeUnit a;
        private final rx.d<T> b;
        private final long c;
        private final rx.g d;

        public /* synthetic */ Object call() {
            return a();
        }

        k(rx.d<T> dVar, long j, TimeUnit timeUnit, rx.g gVar) {
            this.a = timeUnit;
            this.b = dVar;
            this.c = j;
            this.d = gVar;
        }

        public rx.c.a<T> a() {
            return this.b.a(this.c, this.a, this.d);
        }
    }

    static final class l<T> implements rx.b.f<rx.c.a<T>> {
        private final rx.d<T> a;

        public /* synthetic */ Object call() {
            return a();
        }

        l(rx.d<T> dVar) {
            this.a = dVar;
        }

        public rx.c.a<T> a() {
            return this.a.e();
        }
    }

    static final class m<T> implements rx.b.f<rx.c.a<T>> {
        private final long a;
        private final TimeUnit b;
        private final rx.g c;
        private final int d;
        private final rx.d<T> e;

        public /* synthetic */ Object call() {
            return a();
        }

        m(rx.d<T> dVar, int i, long j, TimeUnit timeUnit, rx.g gVar) {
            this.a = j;
            this.b = timeUnit;
            this.c = gVar;
            this.d = i;
            this.e = dVar;
        }

        public rx.c.a<T> a() {
            return this.e.a(this.d, this.a, this.b, this.c);
        }
    }

    static final class n implements rx.b.g<rx.d<? extends Notification<?>>, rx.d<?>> {
        final rx.b.g<? super rx.d<? extends Throwable>, ? extends rx.d<?>> a;

        public /* synthetic */ Object call(Object obj) {
            return a((rx.d) obj);
        }

        public n(rx.b.g<? super rx.d<? extends Throwable>, ? extends rx.d<?>> gVar) {
            this.a = gVar;
        }

        public rx.d<?> a(rx.d<? extends Notification<?>> dVar) {
            return (rx.d) this.a.call(dVar.d(InternalObservableUtils.ERROR_EXTRACTOR));
        }
    }

    static final class o implements rx.b.g<Object, Void> {
        o() {
        }

        public /* synthetic */ Object call(Object obj) {
            return a(obj);
        }

        public Void a(Object obj) {
            return null;
        }
    }

    static final class p<T, R> implements rx.b.g<rx.d<T>, rx.d<R>> {
        final rx.b.g<? super rx.d<T>, ? extends rx.d<R>> a;
        final rx.g b;

        public /* synthetic */ Object call(Object obj) {
            return a((rx.d) obj);
        }

        public p(rx.b.g<? super rx.d<T>, ? extends rx.d<R>> gVar, rx.g gVar2) {
            this.a = gVar;
            this.b = gVar2;
        }

        public rx.d<R> a(rx.d<T> dVar) {
            return ((rx.d) this.a.call(dVar)).a(this.b);
        }
    }

    static final class q implements rx.b.g<List<? extends rx.d<?>>, rx.d<?>[]> {
        q() {
        }

        public /* synthetic */ Object call(Object obj) {
            return a((List) obj);
        }

        public rx.d<?>[] a(List<? extends rx.d<?>> list) {
            return (rx.d[]) list.toArray(new rx.d[list.size()]);
        }
    }

    static {
        LONG_COUNTER = new h();
        OBJECT_EQUALS = new f();
        TO_ARRAY = new q();
        RETURNS_VOID = new o();
        COUNTER = new g();
        ERROR_EXTRACTOR = new e();
        ERROR_NOT_IMPLEMENTED = new c();
        IS_EMPTY = new rx.internal.operators.g(UtilityFunctions.a(), true);
    }

    public static rx.b.g<Object, Boolean> equalsWith(Object obj) {
        return new b(obj);
    }

    public static rx.b.g<Object, Boolean> isInstanceOf(Class<?> cls) {
        return new d(cls);
    }

    public static rx.b.g<rx.d<? extends Notification<?>>, rx.d<?>> createRepeatDematerializer(rx.b.g<? super rx.d<? extends Void>, ? extends rx.d<?>> gVar) {
        return new i(gVar);
    }

    public static <T, R> rx.b.g<rx.d<T>, rx.d<R>> createReplaySelectorAndObserveOn(rx.b.g<? super rx.d<T>, ? extends rx.d<R>> gVar, rx.g gVar2) {
        return new p(gVar, gVar2);
    }

    public static rx.b.g<rx.d<? extends Notification<?>>, rx.d<?>> createRetryDematerializer(rx.b.g<? super rx.d<? extends Throwable>, ? extends rx.d<?>> gVar) {
        return new n(gVar);
    }

    public static <T> rx.b.f<rx.c.a<T>> createReplaySupplier(rx.d<T> dVar) {
        return new l(dVar);
    }

    public static <T> rx.b.f<rx.c.a<T>> createReplaySupplier(rx.d<T> dVar, int i) {
        return new j(dVar, i);
    }

    public static <T> rx.b.f<rx.c.a<T>> createReplaySupplier(rx.d<T> dVar, long j, TimeUnit timeUnit, rx.g gVar) {
        return new k(dVar, j, timeUnit, gVar);
    }

    public static <T> rx.b.f<rx.c.a<T>> createReplaySupplier(rx.d<T> dVar, int i, long j, TimeUnit timeUnit, rx.g gVar) {
        return new m(dVar, i, j, timeUnit, gVar);
    }

    public static <T, R> rx.b.h<R, T, R> createCollectorCaller(rx.b.c<R, ? super T> cVar) {
        return new a(cVar);
    }
}
