package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class QueueDrainHelper {
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;

    private QueueDrainHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, U> void drainMaxLoop(io.reactivex.internal.fuseable.SimplePlainQueue<T> r9, org.a.c<? super U> r10, boolean r11, io.reactivex.disposables.Disposable r12, io.reactivex.internal.util.QueueDrain<T, U> r13) {
        /*
        r7 = 1;
        r6 = r7;
    L_0x0002:
        r0 = r13.done();
        r8 = r9.poll();
        if (r8 != 0) goto L_0x001d;
    L_0x000c:
        r1 = r7;
    L_0x000d:
        r2 = r10;
        r3 = r11;
        r4 = r9;
        r5 = r13;
        r0 = checkTerminated(r0, r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x001f;
    L_0x0017:
        if (r12 == 0) goto L_0x001c;
    L_0x0019:
        r12.dispose();
    L_0x001c:
        return;
    L_0x001d:
        r1 = 0;
        goto L_0x000d;
    L_0x001f:
        if (r1 == 0) goto L_0x002a;
    L_0x0021:
        r0 = -r6;
        r0 = r13.leave(r0);
        if (r0 == 0) goto L_0x001c;
    L_0x0028:
        r6 = r0;
        goto L_0x0002;
    L_0x002a:
        r0 = r13.requested();
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0049;
    L_0x0034:
        r2 = r13.accept(r10, r8);
        if (r2 == 0) goto L_0x0002;
    L_0x003a:
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x0002;
    L_0x0043:
        r0 = 1;
        r13.produced(r0);
        goto L_0x0002;
    L_0x0049:
        r9.clear();
        if (r12 == 0) goto L_0x0051;
    L_0x004e:
        r12.dispose();
    L_0x0051:
        r0 = new io.reactivex.exceptions.MissingBackpressureException;
        r1 = "Could not emit value due to lack of requests.";
        r0.<init>(r1);
        r10.onError(r0);
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(io.reactivex.internal.fuseable.SimplePlainQueue, org.a.c, boolean, io.reactivex.disposables.Disposable, io.reactivex.internal.util.QueueDrain):void");
    }

    public static <T, U> boolean checkTerminated(boolean z, boolean z2, c<?> cVar, boolean z3, SimpleQueue<?> simpleQueue, QueueDrain<T, U> queueDrain) {
        if (queueDrain.cancelled()) {
            simpleQueue.clear();
            return true;
        }
        if (z) {
            Throwable error;
            if (!z3) {
                error = queueDrain.error();
                if (error != null) {
                    simpleQueue.clear();
                    cVar.onError(error);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            } else if (z2) {
                error = queueDrain.error();
                if (error != null) {
                    cVar.onError(error);
                    return true;
                }
                cVar.onComplete();
                return true;
            }
        }
        return false;
    }

    public static <T, U> void drainLoop(SimplePlainQueue<T> simplePlainQueue, Observer<? super U> observer, boolean z, Disposable disposable, ObservableQueueDrain<T, U> observableQueueDrain) {
        int i = 1;
        while (!checkTerminated(observableQueueDrain.done(), simplePlainQueue.isEmpty(), observer, z, simplePlainQueue, disposable, observableQueueDrain)) {
            while (true) {
                boolean done = observableQueueDrain.done();
                Object poll = simplePlainQueue.poll();
                boolean z2 = poll == null;
                if (!checkTerminated(done, z2, observer, z, simplePlainQueue, disposable, observableQueueDrain)) {
                    if (z2) {
                        break;
                    }
                    observableQueueDrain.accept(observer, poll);
                } else {
                    return;
                }
            }
            int leave = observableQueueDrain.leave(-i);
            if (leave != 0) {
                i = leave;
            } else {
                return;
            }
        }
    }

    public static <T, U> boolean checkTerminated(boolean z, boolean z2, Observer<?> observer, boolean z3, SimpleQueue<?> simpleQueue, Disposable disposable, ObservableQueueDrain<T, U> observableQueueDrain) {
        if (observableQueueDrain.cancelled()) {
            simpleQueue.clear();
            disposable.dispose();
            return true;
        }
        if (z) {
            Throwable error;
            if (!z3) {
                error = observableQueueDrain.error();
                if (error != null) {
                    simpleQueue.clear();
                    disposable.dispose();
                    observer.onError(error);
                    return true;
                } else if (z2) {
                    disposable.dispose();
                    observer.onComplete();
                    return true;
                }
            } else if (z2) {
                disposable.dispose();
                error = observableQueueDrain.error();
                if (error != null) {
                    observer.onError(error);
                    return true;
                }
                observer.onComplete();
                return true;
            }
        }
        return false;
    }

    public static <T> SimpleQueue<T> createQueue(int i) {
        if (i < 0) {
            return new SpscLinkedArrayQueue(-i);
        }
        return new SpscArrayQueue(i);
    }

    public static void request(d dVar, int i) {
        dVar.request(i < 0 ? Long.MAX_VALUE : (long) i);
    }

    public static <T> boolean postCompleteRequest(long j, c<? super T> cVar, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, BackpressureHelper.addCap(Long.MAX_VALUE & j2, j) | (j2 & COMPLETED_MASK)));
        if (j2 != COMPLETED_MASK) {
            return false;
        }
        postCompleteDrain(j | COMPLETED_MASK, cVar, queue, atomicLong, booleanSupplier);
        return true;
    }

    static boolean isCancelled(BooleanSupplier booleanSupplier) {
        try {
            return booleanSupplier.getAsBoolean();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            return true;
        }
    }

    static <T> boolean postCompleteDrain(long j, c<? super T> cVar, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        long j2 = j & COMPLETED_MASK;
        while (true) {
            if (j2 != j) {
                if (isCancelled(booleanSupplier)) {
                    return true;
                }
                Object poll = queue.poll();
                if (poll == null) {
                    cVar.onComplete();
                    return true;
                }
                cVar.onNext(poll);
                j2++;
            } else if (isCancelled(booleanSupplier)) {
                return true;
            } else {
                if (queue.isEmpty()) {
                    cVar.onComplete();
                    return true;
                }
                j = atomicLong.get();
                if (j == j2) {
                    j = atomicLong.addAndGet(-(j2 & Long.MAX_VALUE));
                    if ((j & Long.MAX_VALUE) == 0) {
                        return false;
                    }
                    j2 = j & COMPLETED_MASK;
                } else {
                    continue;
                }
            }
        }
    }

    public static <T> void postComplete(c<? super T> cVar, Queue<T> queue, AtomicLong atomicLong, BooleanSupplier booleanSupplier) {
        if (queue.isEmpty()) {
            cVar.onComplete();
        } else if (!postCompleteDrain(atomicLong.get(), cVar, queue, atomicLong, booleanSupplier)) {
            long j;
            long j2;
            do {
                j = atomicLong.get();
                if ((j & COMPLETED_MASK) == 0) {
                    j2 = j | COMPLETED_MASK;
                } else {
                    return;
                }
            } while (!atomicLong.compareAndSet(j, j2));
            if (j != 0) {
                postCompleteDrain(j2, cVar, queue, atomicLong, booleanSupplier);
            }
        }
    }
}
