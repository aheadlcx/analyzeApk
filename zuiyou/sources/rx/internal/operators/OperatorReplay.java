package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.b.f;
import rx.d;
import rx.d$a;
import rx.g;
import rx.g.e;
import rx.internal.util.c;
import rx.j;
import rx.k;

public final class OperatorReplay<T> extends rx.c.a<T> implements k {
    static final f e = new f() {
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    };
    final d<? extends T> b;
    final AtomicReference<b<T>> c;
    final f<? extends a<T>> d;

    interface a<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements a<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        public BoundedReplayBuffer() {
            Node node = new Node(null, 0);
            this.tail = node;
            set(node);
        }

        final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        final void removeFirst() {
            Node node = (Node) ((Node) get()).get();
            if (node == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(node);
        }

        final void removeSome(int i) {
            Node node = (Node) get();
            while (i > 0) {
                node = (Node) node.get();
                i--;
                this.size--;
            }
            setFirst(node);
        }

        final void setFirst(Node node) {
            set(node);
        }

        Node getInitialHead() {
            return (Node) get();
        }

        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.a((Object) t));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncate();
        }

        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.a(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.a());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r14) {
            /*
            r13 = this;
            r4 = 0;
            r12 = 0;
            monitor-enter(r14);
            r0 = r14.emitting;	 Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x000d;
        L_0x0008:
            r0 = 1;
            r14.missed = r0;	 Catch:{ all -> 0x0077 }
            monitor-exit(r14);	 Catch:{ all -> 0x0077 }
        L_0x000c:
            return;
        L_0x000d:
            r0 = 1;
            r14.emitting = r0;	 Catch:{ all -> 0x0077 }
            monitor-exit(r14);	 Catch:{ all -> 0x0077 }
        L_0x0011:
            r0 = r14.isUnsubscribed();
            if (r0 != 0) goto L_0x000c;
        L_0x0017:
            r0 = r14.index();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            if (r0 != 0) goto L_0x002a;
        L_0x001f:
            r0 = r13.getInitialHead();
            r14.index = r0;
            r2 = r0.index;
            r14.addTotalRequested(r2);
        L_0x002a:
            r1 = r14.isUnsubscribed();
            if (r1 != 0) goto L_0x000c;
        L_0x0030:
            r6 = r14.child;
            if (r6 == 0) goto L_0x000c;
        L_0x0034:
            r8 = r14.get();
            r2 = r4;
            r1 = r0;
        L_0x003a:
            r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r0 == 0) goto L_0x0085;
        L_0x003e:
            r0 = r1.get();
            r0 = (rx.internal.operators.OperatorReplay.Node) r0;
            if (r0 == 0) goto L_0x0085;
        L_0x0046:
            r1 = r0.value;
            r1 = r13.leaveTransform(r1);
            r7 = rx.internal.operators.NotificationLite.a(r6, r1);	 Catch:{ Throwable -> 0x0056 }
            if (r7 == 0) goto L_0x007a;
        L_0x0052:
            r0 = 0;
            r14.index = r0;	 Catch:{ Throwable -> 0x0056 }
            goto L_0x000c;
        L_0x0056:
            r0 = move-exception;
            r14.index = r12;
            rx.exceptions.a.b(r0);
            r14.unsubscribe();
            r2 = rx.internal.operators.NotificationLite.c(r1);
            if (r2 != 0) goto L_0x000c;
        L_0x0065:
            r2 = rx.internal.operators.NotificationLite.b(r1);
            if (r2 != 0) goto L_0x000c;
        L_0x006b:
            r1 = rx.internal.operators.NotificationLite.d(r1);
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r1);
            r6.onError(r0);
            goto L_0x000c;
        L_0x0077:
            r0 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x0077 }
            throw r0;
        L_0x007a:
            r10 = 1;
            r2 = r2 + r10;
            r1 = r14.isUnsubscribed();
            if (r1 != 0) goto L_0x000c;
        L_0x0083:
            r1 = r0;
            goto L_0x003a;
        L_0x0085:
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 == 0) goto L_0x0097;
        L_0x0089:
            r14.index = r1;
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
            if (r0 == 0) goto L_0x0097;
        L_0x0094:
            r14.produced(r2);
        L_0x0097:
            monitor-enter(r14);
            r0 = r14.missed;	 Catch:{ all -> 0x00a2 }
            if (r0 != 0) goto L_0x00a5;
        L_0x009c:
            r0 = 0;
            r14.emitting = r0;	 Catch:{ all -> 0x00a2 }
            monitor-exit(r14);	 Catch:{ all -> 0x00a2 }
            goto L_0x000c;
        L_0x00a2:
            r0 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x00a2 }
            throw r0;
        L_0x00a5:
            r0 = 0;
            r14.missed = r0;	 Catch:{ all -> 0x00a2 }
            monitor-exit(r14);	 Catch:{ all -> 0x00a2 }
            goto L_0x0011;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.BoundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        Object enterTransform(Object obj) {
            return obj;
        }

        Object leaveTransform(Object obj) {
            return obj;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        final void collect(Collection<? super T> collection) {
            Node initialHead = getInitialHead();
            while (true) {
                initialHead = (Node) initialHead.get();
                if (initialHead != null) {
                    Object leaveTransform = leaveTransform(initialHead.value);
                    if (!NotificationLite.b(leaveTransform) && !NotificationLite.c(leaveTransform)) {
                        collection.add(NotificationLite.d(leaveTransform));
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        boolean hasError() {
            return this.tail.value != null && NotificationLite.c(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.b(leaveTransform(this.tail.value));
        }
    }

    static final class InnerProducer<T> extends AtomicLong implements rx.f, k {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        j<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final b<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(b<T> bVar, j<? super T> jVar) {
            this.parent = bVar;
            this.child = jVar;
        }

        public void request(long j) {
            if (j >= 0) {
                long j2;
                long j3;
                do {
                    j2 = get();
                    if (j2 == UNSUBSCRIBED) {
                        return;
                    }
                    if (j2 < 0 || j != 0) {
                        j3 = j2 + j;
                        if (j3 < 0) {
                            j3 = Long.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                addTotalRequested(j);
                this.parent.c(this);
                this.parent.a.replay(this);
            }
        }

        void addTotalRequested(long j) {
            long j2;
            long j3;
            do {
                j2 = this.totalRequested.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(j2, j3));
        }

        public long produced(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long j2;
            long j3;
            do {
                j3 = get();
                if (j3 == UNSUBSCRIBED) {
                    return UNSUBSCRIBED;
                }
                j2 = j3 - j;
                if (j2 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j3 + ")");
                }
            } while (!compareAndSet(j3, j2));
            return j2;
        }

        public boolean isUnsubscribed() {
            return get() == UNSUBSCRIBED;
        }

        public void unsubscribe() {
            if (get() != UNSUBSCRIBED && getAndSet(UNSUBSCRIBED) != UNSUBSCRIBED) {
                this.parent.b(this);
                this.parent.c(this);
                this.child = null;
            }
        }

        <U> U index() {
            return this.index;
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        public Node(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final g scheduler;

        public SizeAndTimeBoundReplayBuffer(int i, long j, g gVar) {
            this.scheduler = gVar;
            this.limit = i;
            this.maxAgeInMillis = j;
        }

        Object enterTransform(Object obj) {
            return new rx.f.b(this.scheduler.b(), obj);
        }

        Object leaveTransform(Object obj) {
            return ((rx.f.b) obj).b();
        }

        Node getInitialHead() {
            long b = this.scheduler.b() - this.maxAgeInMillis;
            Node node = (Node) get();
            Node node2 = node;
            for (Node node3 = (Node) node.get(); node3 != null; node3 = (Node) node3.get()) {
                Object obj = node3.value;
                Object leaveTransform = leaveTransform(obj);
                if (NotificationLite.b(leaveTransform) || NotificationLite.c(leaveTransform) || ((rx.f.b) obj).a() > b) {
                    break;
                }
                node2 = node3;
            }
            return node2;
        }

        void truncate() {
            long b = this.scheduler.b() - this.maxAgeInMillis;
            Node node = (Node) get();
            Node node2 = node;
            int i = 0;
            Node node3 = (Node) node.get();
            while (node3 != null) {
                if (this.size <= this.limit) {
                    if (((rx.f.b) node3.value).a() > b) {
                        break;
                    }
                    i++;
                    this.size--;
                    node2 = node3;
                    node3 = (Node) node3.get();
                } else {
                    i++;
                    this.size--;
                    node2 = node3;
                    node3 = (Node) node3.get();
                }
            }
            if (i != 0) {
                setFirst(node2);
            }
        }

        void truncateFinal() {
            long b = this.scheduler.b() - this.maxAgeInMillis;
            Node node = (Node) get();
            Node node2 = node;
            int i = 0;
            Node node3 = (Node) node.get();
            while (node3 != null && this.size > 1 && ((rx.f.b) node3.value).a() <= b) {
                i++;
                this.size--;
                node2 = node3;
                node3 = (Node) node3.get();
            }
            if (i != 0) {
                setFirst(node2);
            }
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        public SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements a<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        public UnboundedReplayBuffer(int i) {
            super(i);
        }

        public void next(T t) {
            add(NotificationLite.a((Object) t));
            this.size++;
        }

        public void error(Throwable th) {
            add(NotificationLite.a(th));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.a());
            this.size++;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(rx.internal.operators.OperatorReplay.InnerProducer<T> r13) {
            /*
            r12 = this;
            r4 = 0;
            r1 = 0;
            monitor-enter(r13);
            r0 = r13.emitting;	 Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x000d;
        L_0x0008:
            r0 = 1;
            r13.missed = r0;	 Catch:{ all -> 0x004a }
            monitor-exit(r13);	 Catch:{ all -> 0x004a }
        L_0x000c:
            return;
        L_0x000d:
            r0 = 1;
            r13.emitting = r0;	 Catch:{ all -> 0x004a }
            monitor-exit(r13);	 Catch:{ all -> 0x004a }
        L_0x0011:
            r0 = r13.isUnsubscribed();
            if (r0 != 0) goto L_0x000c;
        L_0x0017:
            r6 = r12.size;
            r0 = r13.index();
            r0 = (java.lang.Integer) r0;
            if (r0 == 0) goto L_0x004d;
        L_0x0021:
            r0 = r0.intValue();
        L_0x0025:
            r7 = r13.child;
            if (r7 == 0) goto L_0x000c;
        L_0x0029:
            r8 = r13.get();
            r2 = r4;
        L_0x002e:
            r10 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
            if (r10 == 0) goto L_0x006e;
        L_0x0032:
            if (r0 >= r6) goto L_0x006e;
        L_0x0034:
            r10 = r12.get(r0);
            r10 = rx.internal.operators.NotificationLite.a(r7, r10);	 Catch:{ Throwable -> 0x004f }
            if (r10 != 0) goto L_0x000c;
        L_0x003e:
            r10 = r13.isUnsubscribed();
            if (r10 != 0) goto L_0x000c;
        L_0x0044:
            r0 = r0 + 1;
            r10 = 1;
            r2 = r2 + r10;
            goto L_0x002e;
        L_0x004a:
            r0 = move-exception;
            monitor-exit(r13);	 Catch:{ all -> 0x004a }
            throw r0;
        L_0x004d:
            r0 = r1;
            goto L_0x0025;
        L_0x004f:
            r0 = move-exception;
            rx.exceptions.a.b(r0);
            r13.unsubscribe();
            r1 = rx.internal.operators.NotificationLite.c(r10);
            if (r1 != 0) goto L_0x000c;
        L_0x005c:
            r1 = rx.internal.operators.NotificationLite.b(r10);
            if (r1 != 0) goto L_0x000c;
        L_0x0062:
            r1 = rx.internal.operators.NotificationLite.d(r10);
            r0 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r0, r1);
            r7.onError(r0);
            goto L_0x000c;
        L_0x006e:
            r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r6 == 0) goto L_0x0084;
        L_0x0072:
            r0 = java.lang.Integer.valueOf(r0);
            r13.index = r0;
            r6 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x0084;
        L_0x0081:
            r13.produced(r2);
        L_0x0084:
            monitor-enter(r13);
            r0 = r13.missed;	 Catch:{ all -> 0x008f }
            if (r0 != 0) goto L_0x0092;
        L_0x0089:
            r0 = 0;
            r13.emitting = r0;	 Catch:{ all -> 0x008f }
            monitor-exit(r13);	 Catch:{ all -> 0x008f }
            goto L_0x000c;
        L_0x008f:
            r0 = move-exception;
            monitor-exit(r13);	 Catch:{ all -> 0x008f }
            throw r0;
        L_0x0092:
            r0 = 0;
            r13.missed = r0;	 Catch:{ all -> 0x008f }
            monitor-exit(r13);	 Catch:{ all -> 0x008f }
            goto L_0x0011;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.UnboundedReplayBuffer.replay(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }
    }

    static final class b<T> extends j<T> implements k {
        static final InnerProducer[] c = new InnerProducer[0];
        static final InnerProducer[] d = new InnerProducer[0];
        final a<T> a;
        boolean b;
        volatile boolean e;
        final c<InnerProducer<T>> f = new c();
        InnerProducer<T>[] g = c;
        volatile long h;
        long i;
        final AtomicBoolean j = new AtomicBoolean();
        boolean k;
        boolean l;
        long m;
        long n;
        volatile rx.f o;
        List<InnerProducer<T>> p;
        boolean q;

        public b(a<T> aVar) {
            this.a = aVar;
            request(0);
        }

        void a() {
            add(e.a(new rx.b.a(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void call() {
                    if (!this.a.e) {
                        synchronized (this.a.f) {
                            if (!this.a.e) {
                                this.a.f.a();
                                b bVar = this.a;
                                bVar.h++;
                                this.a.e = true;
                            }
                        }
                    }
                }
            }));
        }

        boolean a(InnerProducer<T> innerProducer) {
            if (innerProducer == null) {
                throw new NullPointerException();
            } else if (this.e) {
                return false;
            } else {
                synchronized (this.f) {
                    if (this.e) {
                        return false;
                    }
                    this.f.a((Object) innerProducer);
                    this.h++;
                    return true;
                }
            }
        }

        void b(InnerProducer<T> innerProducer) {
            if (!this.e) {
                synchronized (this.f) {
                    if (this.e) {
                        return;
                    }
                    this.f.b(innerProducer);
                    if (this.f.c()) {
                        this.g = c;
                    }
                    this.h++;
                }
            }
        }

        public void setProducer(rx.f fVar) {
            if (this.o != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.o = fVar;
            c(null);
            c();
        }

        public void onNext(T t) {
            if (!this.b) {
                this.a.next(t);
                c();
            }
        }

        public void onError(Throwable th) {
            if (!this.b) {
                this.b = true;
                try {
                    this.a.error(th);
                    c();
                } finally {
                    unsubscribe();
                }
            }
        }

        public void onCompleted() {
            if (!this.b) {
                this.b = true;
                try {
                    this.a.complete();
                    c();
                } finally {
                    unsubscribe();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void c(rx.internal.operators.OperatorReplay.InnerProducer<T> r11) {
            /*
            r10 = this;
            r6 = 0;
            r0 = r10.isUnsubscribed();
            if (r0 == 0) goto L_0x0008;
        L_0x0007:
            return;
        L_0x0008:
            monitor-enter(r10);
            r0 = r10.k;	 Catch:{ all -> 0x0022 }
            if (r0 == 0) goto L_0x0029;
        L_0x000d:
            if (r11 == 0) goto L_0x0025;
        L_0x000f:
            r0 = r10.p;	 Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x001a;
        L_0x0013:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0022 }
            r0.<init>();	 Catch:{ all -> 0x0022 }
            r10.p = r0;	 Catch:{ all -> 0x0022 }
        L_0x001a:
            r0.add(r11);	 Catch:{ all -> 0x0022 }
        L_0x001d:
            r0 = 1;
            r10.l = r0;	 Catch:{ all -> 0x0022 }
            monitor-exit(r10);	 Catch:{ all -> 0x0022 }
            goto L_0x0007;
        L_0x0022:
            r0 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x0022 }
            throw r0;
        L_0x0025:
            r0 = 1;
            r10.q = r0;	 Catch:{ all -> 0x0022 }
            goto L_0x001d;
        L_0x0029:
            r0 = 1;
            r10.k = r0;	 Catch:{ all -> 0x0022 }
            monitor-exit(r10);	 Catch:{ all -> 0x0022 }
            r4 = r10.m;
            if (r11 == 0) goto L_0x0051;
        L_0x0031:
            r0 = r11.totalRequested;
            r0 = r0.get();
            r0 = java.lang.Math.max(r4, r0);
        L_0x003b:
            r10.a(r0, r4);
        L_0x003e:
            r0 = r10.isUnsubscribed();
            if (r0 != 0) goto L_0x0007;
        L_0x0044:
            monitor-enter(r10);
            r0 = r10.l;	 Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x006b;
        L_0x0049:
            r0 = 0;
            r10.k = r0;	 Catch:{ all -> 0x004e }
            monitor-exit(r10);	 Catch:{ all -> 0x004e }
            goto L_0x0007;
        L_0x004e:
            r0 = move-exception;
            monitor-exit(r10);	 Catch:{ all -> 0x004e }
            throw r0;
        L_0x0051:
            r3 = r10.b();
            r7 = r3.length;
            r2 = r6;
            r0 = r4;
        L_0x0058:
            if (r2 >= r7) goto L_0x003b;
        L_0x005a:
            r8 = r3[r2];
            if (r8 == 0) goto L_0x0068;
        L_0x005e:
            r8 = r8.totalRequested;
            r8 = r8.get();
            r0 = java.lang.Math.max(r0, r8);
        L_0x0068:
            r2 = r2 + 1;
            goto L_0x0058;
        L_0x006b:
            r0 = 0;
            r10.l = r0;	 Catch:{ all -> 0x004e }
            r0 = r10.p;	 Catch:{ all -> 0x004e }
            r1 = 0;
            r10.p = r1;	 Catch:{ all -> 0x004e }
            r7 = r10.q;	 Catch:{ all -> 0x004e }
            r1 = 0;
            r10.q = r1;	 Catch:{ all -> 0x004e }
            monitor-exit(r10);	 Catch:{ all -> 0x004e }
            r4 = r10.m;
            if (r0 == 0) goto L_0x00ba;
        L_0x007d:
            r8 = r0.iterator();
            r2 = r4;
        L_0x0082:
            r0 = r8.hasNext();
            if (r0 == 0) goto L_0x009a;
        L_0x0088:
            r0 = r8.next();
            r0 = (rx.internal.operators.OperatorReplay.InnerProducer) r0;
            r0 = r0.totalRequested;
            r0 = r0.get();
            r0 = java.lang.Math.max(r2, r0);
            r2 = r0;
            goto L_0x0082;
        L_0x009a:
            r0 = r2;
        L_0x009b:
            if (r7 == 0) goto L_0x00b6;
        L_0x009d:
            r3 = r10.b();
            r7 = r3.length;
            r2 = r6;
        L_0x00a3:
            if (r2 >= r7) goto L_0x00b6;
        L_0x00a5:
            r8 = r3[r2];
            if (r8 == 0) goto L_0x00b3;
        L_0x00a9:
            r8 = r8.totalRequested;
            r8 = r8.get();
            r0 = java.lang.Math.max(r0, r8);
        L_0x00b3:
            r2 = r2 + 1;
            goto L_0x00a3;
        L_0x00b6:
            r10.a(r0, r4);
            goto L_0x003e;
        L_0x00ba:
            r0 = r4;
            goto L_0x009b;
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.b.c(rx.internal.operators.OperatorReplay$InnerProducer):void");
        }

        InnerProducer<T>[] b() {
            Object obj;
            synchronized (this.f) {
                Object d = this.f.d();
                int length = d.length;
                obj = new InnerProducer[length];
                System.arraycopy(d, 0, obj, 0, length);
            }
            return obj;
        }

        void a(long j, long j2) {
            long j3 = this.n;
            rx.f fVar = this.o;
            long j4 = j - j2;
            if (j4 != 0) {
                this.m = j;
                if (fVar == null) {
                    j3 += j4;
                    if (j3 < 0) {
                        j3 = Long.MAX_VALUE;
                    }
                    this.n = j3;
                } else if (j3 != 0) {
                    this.n = 0;
                    fVar.request(j3 + j4);
                } else {
                    fVar.request(j4);
                }
            } else if (j3 != 0 && fVar != null) {
                this.n = 0;
                fVar.request(j3);
            }
        }

        void c() {
            InnerProducer[] innerProducerArr = this.g;
            if (this.i != this.h) {
                synchronized (this.f) {
                    innerProducerArr = this.g;
                    Object d = this.f.d();
                    int length = d.length;
                    if (innerProducerArr.length != length) {
                        innerProducerArr = new InnerProducer[length];
                        this.g = innerProducerArr;
                    }
                    System.arraycopy(d, 0, innerProducerArr, 0, length);
                    this.i = this.h;
                }
            }
            a aVar = this.a;
            for (InnerProducer innerProducer : r0) {
                if (innerProducer != null) {
                    aVar.replay(innerProducer);
                }
            }
        }
    }

    public static <T> rx.c.a<T> b(d<? extends T> dVar) {
        return a((d) dVar, e);
    }

    public static <T> rx.c.a<T> a(d<? extends T> dVar, final int i) {
        if (i == Integer.MAX_VALUE) {
            return b(dVar);
        }
        return a((d) dVar, new f<a<T>>() {
            public /* synthetic */ Object call() {
                return a();
            }

            public a<T> a() {
                return new SizeBoundReplayBuffer(i);
            }
        });
    }

    public static <T> rx.c.a<T> a(d<? extends T> dVar, long j, TimeUnit timeUnit, g gVar) {
        return a(dVar, j, timeUnit, gVar, Integer.MAX_VALUE);
    }

    public static <T> rx.c.a<T> a(d<? extends T> dVar, long j, TimeUnit timeUnit, final g gVar, final int i) {
        final long toMillis = timeUnit.toMillis(j);
        return a((d) dVar, new f<a<T>>() {
            public /* synthetic */ Object call() {
                return a();
            }

            public a<T> a() {
                return new SizeAndTimeBoundReplayBuffer(i, toMillis, gVar);
            }
        });
    }

    static <T> rx.c.a<T> a(d<? extends T> dVar, final f<? extends a<T>> fVar) {
        final AtomicReference atomicReference = new AtomicReference();
        return new OperatorReplay(new d$a<T>() {
            public /* synthetic */ void call(Object obj) {
                a((j) obj);
            }

            public void a(j<? super T> jVar) {
                b bVar;
                b bVar2;
                do {
                    bVar = (b) atomicReference.get();
                    if (bVar != null) {
                        break;
                    }
                    bVar2 = new b((a) fVar.call());
                    bVar2.a();
                } while (!atomicReference.compareAndSet(bVar, bVar2));
                bVar = bVar2;
                rx.f innerProducer = new InnerProducer(bVar, jVar);
                bVar.a(innerProducer);
                jVar.add(innerProducer);
                bVar.a.replay(innerProducer);
                jVar.setProducer(innerProducer);
            }
        }, dVar, atomicReference, fVar);
    }

    private OperatorReplay(d$a<T> d_a, d<? extends T> dVar, AtomicReference<b<T>> atomicReference, f<? extends a<T>> fVar) {
        super(d_a);
        this.b = dVar;
        this.c = atomicReference;
        this.d = fVar;
    }

    public void unsubscribe() {
        this.c.lazySet(null);
    }

    public boolean isUnsubscribed() {
        b bVar = (b) this.c.get();
        return bVar == null || bVar.isUnsubscribed();
    }
}
