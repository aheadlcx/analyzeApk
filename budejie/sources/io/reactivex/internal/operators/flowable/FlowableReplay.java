package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.subscribers.SubscriberResourceWrapper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableReplay<T> extends ConnectableFlowable<T> implements Disposable, HasUpstreamPublisher<T> {
    static final Callable DEFAULT_UNBOUNDED_FACTORY = new DefaultUnboundedFactory();
    final Callable<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<FlowableReplay$ReplaySubscriber<T>> current;
    final b<T> onSubscribe;
    final Flowable<T> source;

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerSubscription<T> innerSubscription);
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        BoundedReplayBuffer() {
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

        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncate();
        }

        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r11) {
            /*
            r10 = this;
            monitor-enter(r11);
            r0 = r11.emitting;	 Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x000a;
        L_0x0005:
            r0 = 1;
            r11.missed = r0;	 Catch:{ all -> 0x0079 }
            monitor-exit(r11);	 Catch:{ all -> 0x0079 }
        L_0x0009:
            return;
        L_0x000a:
            r0 = 1;
            r11.emitting = r0;	 Catch:{ all -> 0x0079 }
            monitor-exit(r11);	 Catch:{ all -> 0x0079 }
        L_0x000e:
            r0 = r11.isDisposed();
            if (r0 != 0) goto L_0x0009;
        L_0x0014:
            r4 = r11.get();
            r0 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
            if (r0 != 0) goto L_0x007c;
        L_0x0021:
            r0 = 1;
            r1 = r0;
        L_0x0023:
            r2 = 0;
            r0 = r11.index();
            r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r0;
            if (r0 != 0) goto L_0x003a;
        L_0x002d:
            r0 = r10.getHead();
            r11.index = r0;
            r6 = r11.totalRequested;
            r8 = r0.index;
            io.reactivex.internal.util.BackpressureHelper.add(r6, r8);
        L_0x003a:
            r6 = r4;
            r4 = r2;
            r2 = r0;
        L_0x003d:
            r8 = 0;
            r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r0 == 0) goto L_0x0090;
        L_0x0043:
            r0 = r2.get();
            r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r0;
            if (r0 == 0) goto L_0x0090;
        L_0x004b:
            r2 = r0.value;
            r2 = r10.leaveTransform(r2);
            r3 = r11.child;	 Catch:{ Throwable -> 0x005d }
            r3 = io.reactivex.internal.util.NotificationLite.accept(r2, r3);	 Catch:{ Throwable -> 0x005d }
            if (r3 == 0) goto L_0x007f;
        L_0x0059:
            r0 = 0;
            r11.index = r0;	 Catch:{ Throwable -> 0x005d }
            goto L_0x0009;
        L_0x005d:
            r0 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r0);
            r1 = 0;
            r11.index = r1;
            r11.dispose();
            r1 = io.reactivex.internal.util.NotificationLite.isError(r2);
            if (r1 != 0) goto L_0x0009;
        L_0x006d:
            r1 = io.reactivex.internal.util.NotificationLite.isComplete(r2);
            if (r1 != 0) goto L_0x0009;
        L_0x0073:
            r1 = r11.child;
            r1.onError(r0);
            goto L_0x0009;
        L_0x0079:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0079 }
            throw r0;
        L_0x007c:
            r0 = 0;
            r1 = r0;
            goto L_0x0023;
        L_0x007f:
            r2 = 1;
            r2 = r2 + r4;
            r4 = 1;
            r4 = r6 - r4;
            r6 = r11.isDisposed();
            if (r6 != 0) goto L_0x0009;
        L_0x008c:
            r6 = r4;
            r4 = r2;
            r2 = r0;
            goto L_0x003d;
        L_0x0090:
            r6 = 0;
            r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r0 == 0) goto L_0x009d;
        L_0x0096:
            r11.index = r2;
            if (r1 != 0) goto L_0x009d;
        L_0x009a:
            r11.produced(r4);
        L_0x009d:
            monitor-enter(r11);
            r0 = r11.missed;	 Catch:{ all -> 0x00a8 }
            if (r0 != 0) goto L_0x00ab;
        L_0x00a2:
            r0 = 0;
            r11.emitting = r0;	 Catch:{ all -> 0x00a8 }
            monitor-exit(r11);	 Catch:{ all -> 0x00a8 }
            goto L_0x0009;
        L_0x00a8:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x00a8 }
            throw r0;
        L_0x00ab:
            r0 = 0;
            r11.missed = r0;	 Catch:{ all -> 0x00a8 }
            monitor-exit(r11);	 Catch:{ all -> 0x00a8 }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
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
            Node head = getHead();
            while (true) {
                head = (Node) head.get();
                if (head != null) {
                    Object leaveTransform = leaveTransform(head.value);
                    if (!NotificationLite.isComplete(leaveTransform) && !NotificationLite.isError(leaveTransform)) {
                        collection.add(NotificationLite.getValue(leaveTransform));
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        Node getHead() {
            return (Node) get();
        }
    }

    static final class ConnectableFlowableReplay<T> extends ConnectableFlowable<T> {
        private final ConnectableFlowable<T> co;
        private final Flowable<T> observable;

        ConnectableFlowableReplay(ConnectableFlowable<T> connectableFlowable, Flowable<T> flowable) {
            this.co = connectableFlowable;
            this.observable = flowable;
        }

        public void connect(Consumer<? super Disposable> consumer) {
            this.co.connect(consumer);
        }

        protected void subscribeActual(c<? super T> cVar) {
            this.observable.subscribe(cVar);
        }
    }

    static final class DefaultUnboundedFactory implements Callable<Object> {
        DefaultUnboundedFactory() {
        }

        public Object call() {
            return new FlowableReplay$UnboundedReplayBuffer(16);
        }
    }

    static final class InnerSubscription<T> extends AtomicLong implements Disposable, d {
        static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final c<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final FlowableReplay$ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        InnerSubscription(FlowableReplay$ReplaySubscriber<T> flowableReplay$ReplaySubscriber, c<? super T> cVar) {
            this.parent = flowableReplay$ReplaySubscriber;
            this.child = cVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                long j2;
                do {
                    j2 = get();
                    if (j2 != CANCELLED) {
                        if (j2 >= 0 && j == 0) {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, BackpressureHelper.addCap(j2, j)));
                BackpressureHelper.add(this.totalRequested, j);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long j) {
            return BackpressureHelper.producedCancel(this, j);
        }

        public boolean isDisposed() {
            return get() == CANCELLED;
        }

        public void cancel() {
            dispose();
        }

        public void dispose() {
            if (getAndSet(CANCELLED) != CANCELLED) {
                this.parent.remove(this);
                this.parent.manageRequests();
            }
        }

        <U> U index() {
            return this.index;
        }
    }

    static final class MultiCastPublisher<R, U> implements b<R> {
        private final Callable<? extends ConnectableFlowable<U>> connectableFactory;
        private final Function<? super Flowable<U>, ? extends b<R>> selector;

        final class DisposableConsumer implements Consumer<Disposable> {
            private final SubscriberResourceWrapper<R> srw;

            DisposableConsumer(SubscriberResourceWrapper<R> subscriberResourceWrapper) {
                this.srw = subscriberResourceWrapper;
            }

            public void accept(Disposable disposable) {
                this.srw.setResource(disposable);
            }
        }

        MultiCastPublisher(Callable<? extends ConnectableFlowable<U>> callable, Function<? super Flowable<U>, ? extends b<R>> function) {
            this.connectableFactory = callable;
            this.selector = function;
        }

        public void subscribe(c<? super R> cVar) {
            try {
                ConnectableFlowable connectableFlowable = (ConnectableFlowable) ObjectHelper.requireNonNull(this.connectableFactory.call(), "The connectableFactory returned null");
                try {
                    b bVar = (b) ObjectHelper.requireNonNull(this.selector.apply(connectableFlowable), "The selector returned a null Publisher");
                    Object subscriberResourceWrapper = new SubscriberResourceWrapper(cVar);
                    bVar.subscribe(subscriberResourceWrapper);
                    connectableFlowable.connect(new DisposableConsumer(subscriberResourceWrapper));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    EmptySubscription.error(th, cVar);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                EmptySubscription.error(th2, cVar);
            }
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        Node(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    static final class ReplayBufferTask<T> implements Callable<ReplayBuffer<T>> {
        private final int bufferSize;

        ReplayBufferTask(int i) {
            this.bufferSize = i;
        }

        public ReplayBuffer<T> call() {
            return new FlowableReplay$SizeBoundReplayBuffer(this.bufferSize);
        }
    }

    static final class ReplayPublisher<T> implements b<T> {
        private final Callable<? extends ReplayBuffer<T>> bufferFactory;
        private final AtomicReference<FlowableReplay$ReplaySubscriber<T>> curr;

        ReplayPublisher(AtomicReference<FlowableReplay$ReplaySubscriber<T>> atomicReference, Callable<? extends ReplayBuffer<T>> callable) {
            this.curr = atomicReference;
            this.bufferFactory = callable;
        }

        public void subscribe(c<? super T> cVar) {
            FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber;
            FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber2;
            do {
                flowableReplay$ReplaySubscriber2 = (FlowableReplay$ReplaySubscriber) this.curr.get();
                if (flowableReplay$ReplaySubscriber2 != null) {
                    break;
                }
                try {
                    flowableReplay$ReplaySubscriber = new FlowableReplay$ReplaySubscriber((ReplayBuffer) this.bufferFactory.call());
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(th);
                }
            } while (!this.curr.compareAndSet(null, flowableReplay$ReplaySubscriber));
            flowableReplay$ReplaySubscriber2 = flowableReplay$ReplaySubscriber;
            InnerSubscription innerSubscription = new InnerSubscription(flowableReplay$ReplaySubscriber2, cVar);
            cVar.onSubscribe(innerSubscription);
            flowableReplay$ReplaySubscriber2.add(innerSubscription);
            if (innerSubscription.isDisposed()) {
                flowableReplay$ReplaySubscriber2.remove(innerSubscription);
                return;
            }
            flowableReplay$ReplaySubscriber2.manageRequests();
            flowableReplay$ReplaySubscriber2.buffer.replay(innerSubscription);
        }
    }

    public static <U, R> Flowable<R> multicastSelector(Callable<? extends ConnectableFlowable<U>> callable, Function<? super Flowable<U>, ? extends b<R>> function) {
        return Flowable.unsafeCreate(new MultiCastPublisher(callable, function));
    }

    public static <T> ConnectableFlowable<T> observeOn(ConnectableFlowable<T> connectableFlowable, Scheduler scheduler) {
        return RxJavaPlugins.onAssembly(new ConnectableFlowableReplay(connectableFlowable, connectableFlowable.observeOn(scheduler)));
    }

    public static <T> ConnectableFlowable<T> createFrom(Flowable<? extends T> flowable) {
        return create((Flowable) flowable, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, int i) {
        if (i == Integer.MAX_VALUE) {
            return createFrom(flowable);
        }
        return create((Flowable) flowable, new ReplayBufferTask(i));
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        return create(flowable, j, timeUnit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return create((Flowable) flowable, new FlowableReplay$ScheduledReplayBufferTask(i, j, timeUnit, scheduler));
    }

    static <T> ConnectableFlowable<T> create(Flowable<T> flowable, Callable<? extends ReplayBuffer<T>> callable) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.onAssembly(new FlowableReplay(new ReplayPublisher(atomicReference, callable), flowable, atomicReference, callable));
    }

    private FlowableReplay(b<T> bVar, Flowable<T> flowable, AtomicReference<FlowableReplay$ReplaySubscriber<T>> atomicReference, Callable<? extends ReplayBuffer<T>> callable) {
        this.onSubscribe = bVar;
        this.source = flowable;
        this.current = atomicReference;
        this.bufferFactory = callable;
    }

    public b<T> source() {
        return this.source;
    }

    protected void subscribeActual(c<? super T> cVar) {
        this.onSubscribe.subscribe(cVar);
    }

    public void dispose() {
        this.current.lazySet(null);
    }

    public boolean isDisposed() {
        Disposable disposable = (Disposable) this.current.get();
        return disposable == null || disposable.isDisposed();
    }

    public void connect(Consumer<? super Disposable> consumer) {
        Object obj;
        FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber;
        boolean z;
        do {
            obj = (FlowableReplay$ReplaySubscriber) this.current.get();
            if (obj != null && !obj.isDisposed()) {
                break;
            }
            try {
                flowableReplay$ReplaySubscriber = new FlowableReplay$ReplaySubscriber((ReplayBuffer) this.bufferFactory.call());
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(th);
            }
        } while (!this.current.compareAndSet(obj, flowableReplay$ReplaySubscriber));
        obj = flowableReplay$ReplaySubscriber;
        if (obj.shouldConnect.get() || !obj.shouldConnect.compareAndSet(false, true)) {
            z = false;
        } else {
            z = true;
        }
        try {
            consumer.accept(obj);
            if (z) {
                this.source.subscribe(obj);
            }
        } catch (Throwable th2) {
            if (z) {
                obj.shouldConnect.compareAndSet(true, false);
            }
            Exceptions.throwIfFatal(th2);
            wrapOrThrow = ExceptionHelper.wrapOrThrow(th2);
        }
    }
}
