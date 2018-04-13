package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.d$b;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.f;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.a.l;
import rx.internal.util.a.y;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.e;
import rx.j;
import rx.k;

public final class OperatorMerge<T> implements d$b<T, rx.d<? extends T>> {
    final boolean a;
    final int b;

    static final class MergeProducer<T> extends AtomicLong implements f {
        private static final long serialVersionUID = -1214379189873595503L;
        final d<T> subscriber;

        public MergeProducer(d<T> dVar) {
            this.subscriber = dVar;
        }

        public void request(long j) {
            if (j > 0) {
                if (get() != Long.MAX_VALUE) {
                    a.a((AtomicLong) this, j);
                    this.subscriber.d();
                }
            } else if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long produced(int i) {
            return addAndGet((long) (-i));
        }
    }

    static final class a {
        static final OperatorMerge<Object> a = new OperatorMerge(true, Integer.MAX_VALUE);
    }

    static final class b {
        static final OperatorMerge<Object> a = new OperatorMerge(false, Integer.MAX_VALUE);
    }

    static final class c<T> extends j<T> {
        static final int f = (e.b / 4);
        final d<T> a;
        final long b;
        volatile boolean c;
        volatile e d;
        int e;

        public c(d<T> dVar, long j) {
            this.a = dVar;
            this.b = j;
        }

        public void onStart() {
            this.e = e.b;
            request((long) e.b);
        }

        public void onNext(T t) {
            this.a.a(this, (Object) t);
        }

        public void onError(Throwable th) {
            this.a.a().offer(th);
            this.c = true;
            this.a.d();
        }

        public void onCompleted() {
            this.c = true;
            this.a.d();
        }

        public void a(long j) {
            int i = this.e - ((int) j);
            if (i > f) {
                this.e = i;
                return;
            }
            this.e = e.b;
            i = e.b - i;
            if (i > 0) {
                request((long) i);
            }
        }
    }

    static final class d<T> extends j<rx.d<? extends T>> {
        static final c<?>[] p = new c[0];
        final j<? super T> a;
        final boolean b;
        final int c;
        MergeProducer<T> d;
        volatile Queue<Object> e;
        volatile rx.g.b f;
        volatile ConcurrentLinkedQueue<Throwable> g;
        volatile boolean h;
        boolean i;
        boolean j;
        final Object k = new Object();
        volatile c<?>[] l = p;
        long m;
        long n;
        int o;
        final int q;
        int r;

        public /* synthetic */ void onNext(Object obj) {
            a((rx.d) obj);
        }

        public d(j<? super T> jVar, boolean z, int i) {
            this.a = jVar;
            this.b = z;
            this.c = i;
            if (i == Integer.MAX_VALUE) {
                this.q = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.q = Math.max(1, i >> 1);
            request((long) i);
        }

        Queue<Throwable> a() {
            Queue<Throwable> queue = this.g;
            if (queue == null) {
                synchronized (this) {
                    queue = this.g;
                    if (queue == null) {
                        queue = new ConcurrentLinkedQueue();
                        this.g = queue;
                    }
                }
            }
            return queue;
        }

        rx.g.b b() {
            rx.g.b bVar = this.f;
            if (bVar == null) {
                Object obj;
                synchronized (this) {
                    rx.g.b bVar2 = this.f;
                    if (bVar2 == null) {
                        bVar2 = new rx.g.b();
                        this.f = bVar2;
                        bVar = bVar2;
                        obj = 1;
                    } else {
                        bVar = bVar2;
                        obj = null;
                    }
                }
                if (obj != null) {
                    add(bVar);
                }
            }
            return bVar;
        }

        public void a(rx.d<? extends T> dVar) {
            if (dVar != null) {
                if (dVar == rx.d.c()) {
                    c();
                } else if (dVar instanceof ScalarSynchronousObservable) {
                    a(((ScalarSynchronousObservable) dVar).h());
                } else {
                    long j = this.m;
                    this.m = 1 + j;
                    c cVar = new c(this, j);
                    a(cVar);
                    dVar.a(cVar);
                    d();
                }
            }
        }

        void c() {
            int i = this.r + 1;
            if (i == this.q) {
                this.r = 0;
                a((long) i);
                return;
            }
            this.r = i;
        }

        private void g() {
            Collection arrayList = new ArrayList(this.g);
            if (arrayList.size() == 1) {
                this.a.onError((Throwable) arrayList.get(0));
            } else {
                this.a.onError(new CompositeException(arrayList));
            }
        }

        public void onError(Throwable th) {
            a().offer(th);
            this.h = true;
            d();
        }

        public void onCompleted() {
            this.h = true;
            d();
        }

        void a(c<T> cVar) {
            b().a((k) cVar);
            synchronized (this.k) {
                Object obj = this.l;
                int length = obj.length;
                Object obj2 = new c[(length + 1)];
                System.arraycopy(obj, 0, obj2, 0, length);
                obj2[length] = cVar;
                this.l = obj2;
            }
        }

        void b(c<T> cVar) {
            int i = 0;
            e eVar = cVar.d;
            if (eVar != null) {
                eVar.b();
            }
            this.f.b(cVar);
            synchronized (this.k) {
                Object obj = this.l;
                int length = obj.length;
                while (i < length) {
                    if (cVar.equals(obj[i])) {
                        break;
                    }
                    i++;
                }
                i = -1;
                if (i < 0) {
                } else if (length == 1) {
                    this.l = p;
                } else {
                    Object obj2 = new c[(length - 1)];
                    System.arraycopy(obj, 0, obj2, 0, i);
                    System.arraycopy(obj, i + 1, obj2, i, (length - i) - 1);
                    this.l = obj2;
                }
            }
        }

        void a(c<T> cVar, T t) {
            Object obj = null;
            long j = this.d.get();
            if (j != 0) {
                synchronized (this) {
                    j = this.d.get();
                    if (!(this.i || j == 0)) {
                        this.i = true;
                        obj = 1;
                    }
                }
            }
            if (obj != null) {
                e eVar = cVar.d;
                if (eVar == null || eVar.c()) {
                    a(cVar, t, j);
                    return;
                }
                b(cVar, t);
                e();
                return;
            }
            b(cVar, t);
            d();
        }

        protected void b(c<T> cVar, T t) {
            e eVar = cVar.d;
            if (eVar == null) {
                eVar = e.a();
                cVar.add(eVar);
                cVar.d = eVar;
            }
            try {
                eVar.a(NotificationLite.a((Object) t));
            } catch (Throwable e) {
                cVar.unsubscribe();
                cVar.onError(e);
            } catch (Throwable e2) {
                if (!cVar.isUnsubscribed()) {
                    cVar.unsubscribe();
                    cVar.onError(e2);
                }
            }
        }

        protected void a(c<T> cVar, T t, long j) {
            Throwable th;
            Object obj = null;
            try {
                this.a.onNext(t);
            } catch (Throwable th2) {
                th = th2;
                obj = 1;
            }
            if (j != Long.MAX_VALUE) {
                this.d.produced(1);
            }
            cVar.a(1);
            synchronized (this) {
                if (this.j) {
                    this.j = false;
                    e();
                    return;
                }
                this.i = false;
                return;
            }
            if (obj == null) {
                synchronized (this) {
                    this.i = false;
                }
            }
            throw th;
        }

        public void a(long j) {
            request(j);
        }

        void a(T t) {
            Object obj = null;
            long j = this.d.get();
            if (j != 0) {
                synchronized (this) {
                    j = this.d.get();
                    if (!(this.i || j == 0)) {
                        this.i = true;
                        obj = 1;
                    }
                }
            }
            if (obj != null) {
                Queue queue = this.e;
                if (queue == null || queue.isEmpty()) {
                    a((Object) t, j);
                    return;
                }
                b((Object) t);
                e();
                return;
            }
            b((Object) t);
            d();
        }

        protected void b(T t) {
            Queue queue = this.e;
            if (queue == null) {
                int i = this.c;
                if (i == Integer.MAX_VALUE) {
                    queue = new rx.internal.util.atomic.c(e.b);
                } else if (!rx.internal.util.a.c.b(i)) {
                    queue = new SpscExactAtomicArrayQueue(i);
                } else if (y.a()) {
                    queue = new l(i);
                } else {
                    queue = new rx.internal.util.atomic.b(i);
                }
                this.e = queue;
            }
            if (!queue.offer(NotificationLite.a((Object) t))) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
            }
        }

        protected void a(T t, long j) {
            Throwable th;
            Object obj = null;
            try {
                this.a.onNext(t);
            } catch (Throwable th2) {
                th = th2;
                obj = 1;
            }
            if (j != Long.MAX_VALUE) {
                this.d.produced(1);
            }
            int i = this.r + 1;
            if (i == this.q) {
                this.r = 0;
                a((long) i);
            } else {
                this.r = i;
            }
            synchronized (this) {
                if (this.j) {
                    this.j = false;
                    e();
                    return;
                }
                this.i = false;
                return;
            }
            if (obj == null) {
                synchronized (this) {
                    this.i = false;
                }
            }
            throw th;
        }

        void d() {
            synchronized (this) {
                if (this.i) {
                    this.j = true;
                    return;
                }
                this.i = true;
                e();
            }
        }

        void e() {
            Object obj = null;
            j jVar = this.a;
            while (!f()) {
                Object obj2;
                Object obj3;
                int i;
                long j;
                int i2;
                boolean z;
                Queue queue;
                c[] cVarArr;
                int length;
                int i3;
                Object obj4;
                e eVar;
                Queue queue2 = this.e;
                long j2 = this.d.get();
                if (j2 == Long.MAX_VALUE) {
                    obj2 = 1;
                } else {
                    obj2 = null;
                }
                int i4 = 0;
                int i5;
                int i6;
                c cVar;
                int i7;
                int i8;
                if (queue2 != null) {
                    do {
                        i = 0;
                        obj3 = null;
                        while (j2 > 0) {
                            obj3 = queue2.poll();
                            if (!f()) {
                                if (obj3 == null) {
                                    break;
                                }
                                try {
                                    try {
                                        jVar.onNext(NotificationLite.d(obj3));
                                    } catch (Throwable th) {
                                        if (this.b) {
                                            a().offer(th);
                                        } else {
                                            rx.exceptions.a.b(th);
                                            unsubscribe();
                                            jVar.onError(th);
                                            return;
                                        }
                                    }
                                    j2--;
                                    i++;
                                    i4++;
                                } catch (Throwable th2) {
                                    if (obj == null) {
                                        synchronized (this) {
                                            this.i = false;
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                        if (i > 0) {
                            if (obj2 != null) {
                                j2 = Long.MAX_VALUE;
                            } else {
                                j2 = this.d.produced(i);
                            }
                        }
                        if (j2 != 0) {
                        }
                    } while (obj3 != null);
                    j = j2;
                    i2 = i4;
                    z = this.h;
                    queue = this.e;
                    cVarArr = this.l;
                    length = cVarArr.length;
                    if (z || !((queue == null || queue.isEmpty()) && length == 0)) {
                        if (length <= 0) {
                            long j3 = this.n;
                            i4 = this.o;
                            if (length <= i4 || cVarArr[i4].b != j3) {
                                if (length <= i4) {
                                    i4 = 0;
                                }
                                for (i3 = 0; i3 < length && cVarArr[i4].b != j3; i3++) {
                                    i4++;
                                    if (i4 == length) {
                                        i4 = 0;
                                    }
                                }
                                this.o = i4;
                                this.n = cVarArr[i4].b;
                            }
                            i5 = i4;
                            i = i2;
                            j2 = j;
                            i6 = 0;
                            obj3 = null;
                            while (i6 < length) {
                                if (!f()) {
                                    cVar = cVarArr[i5];
                                    obj4 = null;
                                    do {
                                        i7 = 0;
                                        while (j2 > 0) {
                                            if (!f()) {
                                                eVar = cVar.d;
                                                if (eVar != null) {
                                                    obj4 = eVar.d();
                                                    if (obj4 != null) {
                                                        break;
                                                    }
                                                    try {
                                                        jVar.onNext(NotificationLite.d(obj4));
                                                        i7++;
                                                        j2--;
                                                    } catch (Throwable th3) {
                                                        obj = 1;
                                                        rx.exceptions.a.b(th3);
                                                        jVar.onError(th3);
                                                        return;
                                                    } finally {
                                                        unsubscribe();
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                            return;
                                        }
                                        if (i7 > 0) {
                                            if (obj2 != null) {
                                                j2 = this.d.produced(i7);
                                            } else {
                                                j2 = Long.MAX_VALUE;
                                            }
                                            cVar.a((long) i7);
                                        }
                                        if (j2 == 0) {
                                            break;
                                        }
                                    } while (obj4 != null);
                                    z = cVar.c;
                                    e eVar2 = cVar.d;
                                    if (z && (eVar2 == null || eVar2.c())) {
                                        b(cVar);
                                        if (!f()) {
                                            i++;
                                            obj3 = 1;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (j2 == 0) {
                                        obj4 = obj3;
                                        i8 = i;
                                        break;
                                    }
                                    i4 = i5 + 1;
                                    if (i4 == length) {
                                        i4 = 0;
                                    }
                                    i6++;
                                    i5 = i4;
                                } else {
                                    return;
                                }
                            }
                            obj4 = obj3;
                            i8 = i;
                            this.o = i5;
                            this.n = cVarArr[i5].b;
                        } else {
                            obj4 = null;
                            i8 = i2;
                        }
                        if (i8 > 0) {
                            request((long) i8);
                        }
                        if (obj4 == null) {
                            synchronized (this) {
                                if (this.j) {
                                    this.i = false;
                                    return;
                                }
                                this.j = false;
                            }
                        }
                    } else {
                        Queue queue3 = this.g;
                        if (queue3 == null || queue3.isEmpty()) {
                            jVar.onCompleted();
                            return;
                        } else {
                            g();
                            return;
                        }
                    }
                }
                j = j2;
                i2 = i4;
                z = this.h;
                queue = this.e;
                cVarArr = this.l;
                length = cVarArr.length;
                if (z) {
                }
                if (length <= 0) {
                    obj4 = null;
                    i8 = i2;
                } else {
                    long j32 = this.n;
                    i4 = this.o;
                    if (length <= i4) {
                        i4 = 0;
                    }
                    for (i3 = 0; i3 < length; i3++) {
                        i4++;
                        if (i4 == length) {
                            i4 = 0;
                        }
                    }
                    this.o = i4;
                    this.n = cVarArr[i4].b;
                    i5 = i4;
                    i = i2;
                    j2 = j;
                    i6 = 0;
                    obj3 = null;
                    while (i6 < length) {
                        if (!f()) {
                            cVar = cVarArr[i5];
                            obj4 = null;
                            do {
                                i7 = 0;
                                while (j2 > 0) {
                                    if (!f()) {
                                        eVar = cVar.d;
                                        if (eVar != null) {
                                            break;
                                        }
                                        obj4 = eVar.d();
                                        if (obj4 != null) {
                                            break;
                                        }
                                        jVar.onNext(NotificationLite.d(obj4));
                                        i7++;
                                        j2--;
                                    } else {
                                        return;
                                    }
                                }
                                if (i7 > 0) {
                                    if (obj2 != null) {
                                        j2 = Long.MAX_VALUE;
                                    } else {
                                        j2 = this.d.produced(i7);
                                    }
                                    cVar.a((long) i7);
                                }
                                if (j2 == 0) {
                                    break;
                                }
                                break;
                            } while (obj4 != null);
                            z = cVar.c;
                            e eVar22 = cVar.d;
                            b(cVar);
                            if (!f()) {
                                i++;
                                obj3 = 1;
                                if (j2 == 0) {
                                    obj4 = obj3;
                                    i8 = i;
                                    break;
                                }
                                i4 = i5 + 1;
                                if (i4 == length) {
                                    i4 = 0;
                                }
                                i6++;
                                i5 = i4;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    obj4 = obj3;
                    i8 = i;
                    this.o = i5;
                    this.n = cVarArr[i5].b;
                }
                if (i8 > 0) {
                    request((long) i8);
                }
                if (obj4 == null) {
                    synchronized (this) {
                        if (this.j) {
                            this.j = false;
                        } else {
                            this.i = false;
                            return;
                        }
                    }
                }
            }
        }

        boolean f() {
            if (this.a.isUnsubscribed()) {
                return true;
            }
            Queue queue = this.g;
            if (this.b || queue == null || queue.isEmpty()) {
                return false;
            }
            try {
                g();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    public /* synthetic */ Object call(Object obj) {
        return a((j) obj);
    }

    public static <T> OperatorMerge<T> a(boolean z) {
        if (z) {
            return a.a;
        }
        return b.a;
    }

    OperatorMerge(boolean z, int i) {
        this.a = z;
        this.b = i;
    }

    public j<rx.d<? extends T>> a(j<? super T> jVar) {
        Object dVar = new d(jVar, this.a, this.b);
        f mergeProducer = new MergeProducer(dVar);
        dVar.d = mergeProducer;
        jVar.add(dVar);
        jVar.setProducer(mergeProducer);
        return dVar;
    }
}
