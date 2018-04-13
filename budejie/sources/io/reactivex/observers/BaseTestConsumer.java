package io.reactivex.observers;

import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.VolatileSizeArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class BaseTestConsumer<T, U extends BaseTestConsumer<T, U>> implements Disposable {
    protected boolean checkSubscriptionOnce;
    protected long completions;
    protected final CountDownLatch done = new CountDownLatch(1);
    protected final List<Throwable> errors = new VolatileSizeArrayList();
    protected int establishedFusionMode;
    protected int initialFusionMode;
    protected Thread lastThread;
    protected CharSequence tag;
    protected boolean timeout;
    protected final List<T> values = new VolatileSizeArrayList();

    public enum TestWaitStrategy implements Runnable {
        SPIN {
            public void run() {
            }
        },
        YIELD {
            public void run() {
                Thread.yield();
            }
        },
        SLEEP_1MS {
            public void run() {
                TestWaitStrategy.sleep(1);
            }
        },
        SLEEP_10MS {
            public void run() {
                TestWaitStrategy.sleep(10);
            }
        },
        SLEEP_100MS {
            public void run() {
                TestWaitStrategy.sleep(100);
            }
        },
        SLEEP_1000MS {
            public void run() {
                TestWaitStrategy.sleep(1000);
            }
        };

        public abstract void run();

        static void sleep(int i) {
            try {
                Thread.sleep((long) i);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public abstract U assertNotSubscribed();

    public abstract U assertSubscribed();

    public final Thread lastThread() {
        return this.lastThread;
    }

    public final List<T> values() {
        return this.values;
    }

    public final List<Throwable> errors() {
        return this.errors;
    }

    public final long completions() {
        return this.completions;
    }

    public final boolean isTerminated() {
        return this.done.getCount() == 0;
    }

    public final int valueCount() {
        return this.values.size();
    }

    public final int errorCount() {
        return this.errors.size();
    }

    protected final AssertionError fail(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length() + 64);
        stringBuilder.append(str);
        stringBuilder.append(" (").append("latch = ").append(this.done.getCount()).append(", ").append("values = ").append(this.values.size()).append(", ").append("errors = ").append(this.errors.size()).append(", ").append("completions = ").append(this.completions);
        if (this.timeout) {
            stringBuilder.append(", timeout!");
        }
        if (isDisposed()) {
            stringBuilder.append(", disposed!");
        }
        CharSequence charSequence = this.tag;
        if (charSequence != null) {
            stringBuilder.append(", tag = ").append(charSequence);
        }
        stringBuilder.append(')');
        AssertionError assertionError = new AssertionError(stringBuilder.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                assertionError.initCause((Throwable) this.errors.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.errors));
            }
        }
        return assertionError;
    }

    public final U await() throws InterruptedException {
        if (this.done.getCount() != 0) {
            this.done.await();
        }
        return this;
    }

    public final boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean z;
        boolean z2 = true;
        if (this.done.getCount() == 0 || this.done.await(j, timeUnit)) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            z2 = false;
        }
        this.timeout = z2;
        return z;
    }

    public final U assertComplete() {
        long j = this.completions;
        if (j == 0) {
            throw fail("Not completed");
        } else if (j <= 1) {
            return this;
        } else {
            throw fail("Multiple completions: " + j);
        }
    }

    public final U assertNotComplete() {
        long j = this.completions;
        if (j == 1) {
            throw fail("Completed!");
        } else if (j <= 1) {
            return this;
        } else {
            throw fail("Multiple completions: " + j);
        }
    }

    public final U assertNoErrors() {
        if (this.errors.size() == 0) {
            return this;
        }
        throw fail("Error(s) present: " + this.errors);
    }

    public final U assertError(Throwable th) {
        return assertError(Functions.equalsWith(th));
    }

    public final U assertError(Class<? extends Throwable> cls) {
        return assertError(Functions.isInstanceOf(cls));
    }

    public final U assertError(Predicate<Throwable> predicate) {
        int size = this.errors.size();
        if (size == 0) {
            throw fail("No errors");
        }
        Object obj;
        for (Throwable test : this.errors) {
            try {
                if (predicate.test(test)) {
                    obj = 1;
                    break;
                }
            } catch (Throwable test2) {
                throw ExceptionHelper.wrapOrThrow(test2);
            }
        }
        obj = null;
        if (obj == null) {
            throw fail("Error not present");
        } else if (size == 1) {
            return this;
        } else {
            throw fail("Error present but other errors as well");
        }
    }

    public final U assertValue(T t) {
        if (this.values.size() != 1) {
            throw fail("Expected: " + valueAndClass(t) + ", Actual: " + this.values);
        }
        Object obj = this.values.get(0);
        if (ObjectHelper.equals(t, obj)) {
            return this;
        }
        throw fail("Expected: " + valueAndClass(t) + ", Actual: " + valueAndClass(obj));
    }

    public final U assertNever(T t) {
        int size = this.values.size();
        for (int i = 0; i < size; i++) {
            if (ObjectHelper.equals(this.values.get(i), t)) {
                throw fail("Value at position " + i + " is equal to " + valueAndClass(t) + "; Expected them to be different");
            }
        }
        return this;
    }

    public final U assertValue(Predicate<T> predicate) {
        assertValueAt(0, predicate);
        if (this.values.size() <= 1) {
            return this;
        }
        throw fail("Value present but other values as well");
    }

    public final U assertNever(Predicate<? super T> predicate) {
        int size = this.values.size();
        int i = 0;
        while (i < size) {
            try {
                if (predicate.test(this.values.get(i))) {
                    throw fail("Value at position " + i + " matches predicate " + predicate.toString() + ", which was not expected.");
                }
                i++;
            } catch (Throwable e) {
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
        return this;
    }

    public final U assertValueAt(int i, Predicate<T> predicate) {
        if (this.values.size() == 0) {
            throw fail("No values");
        } else if (i >= this.values.size()) {
            throw fail("Invalid index: " + i);
        } else {
            Object obj = null;
            try {
                if (predicate.test(this.values.get(i))) {
                    obj = 1;
                }
                if (obj != null) {
                    return this;
                }
                throw fail("Value not present");
            } catch (Throwable e) {
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }
    }

    public static String valueAndClass(Object obj) {
        if (obj != null) {
            return obj + " (class: " + obj.getClass().getSimpleName() + ")";
        }
        return "null";
    }

    public final U assertValueCount(int i) {
        int size = this.values.size();
        if (size == i) {
            return this;
        }
        throw fail("Value counts differ; Expected: " + i + ", Actual: " + size);
    }

    public final U assertNoValues() {
        return assertValueCount(0);
    }

    public final U assertValues(T... tArr) {
        int size = this.values.size();
        if (size != tArr.length) {
            throw fail("Value count differs; Expected: " + tArr.length + " " + Arrays.toString(tArr) + ", Actual: " + size + " " + this.values);
        }
        int i = 0;
        while (i < size) {
            Object obj = this.values.get(i);
            Object obj2 = tArr[i];
            if (ObjectHelper.equals(obj2, obj)) {
                i++;
            } else {
                throw fail("Values at position " + i + " differ; Expected: " + valueAndClass(obj2) + ", Actual: " + valueAndClass(obj));
            }
        }
        return this;
    }

    public final U assertValueSet(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            assertNoValues();
        } else {
            for (Object next : this.values) {
                if (!collection.contains(next)) {
                    throw fail("Value not in the expected collection: " + valueAndClass(next));
                }
            }
        }
        return this;
    }

    public final U assertValueSequence(Iterable<? extends T> iterable) {
        int i = 0;
        Iterator it = this.values.iterator();
        Iterator it2 = iterable.iterator();
        while (true) {
            boolean hasNext = it2.hasNext();
            boolean hasNext2 = it.hasNext();
            if (hasNext && hasNext2) {
                Object next = it2.next();
                Object next2 = it.next();
                if (ObjectHelper.equals(next2, next)) {
                    i++;
                } else {
                    throw fail("Values at position " + i + " differ; Expected: " + valueAndClass(next2) + ", Actual: " + valueAndClass(next));
                }
            } else if (hasNext) {
                throw fail("More values received than expected (" + i + ")");
            } else if (hasNext2) {
                return this;
            } else {
                throw fail("Fever values received than expected (" + i + ")");
            }
        }
        if (hasNext) {
            throw fail("More values received than expected (" + i + ")");
        } else if (hasNext2) {
            return this;
        } else {
            throw fail("Fever values received than expected (" + i + ")");
        }
    }

    public final U assertTerminated() {
        if (this.done.getCount() != 0) {
            throw fail("Subscriber still running!");
        }
        long j = this.completions;
        if (j > 1) {
            throw fail("Terminated with multiple completions: " + j);
        }
        int size = this.errors.size();
        if (size > 1) {
            throw fail("Terminated with multiple errors: " + size);
        } else if (j == 0 || size == 0) {
            return this;
        } else {
            throw fail("Terminated with multiple completions and errors: " + j);
        }
    }

    public final U assertNotTerminated() {
        if (this.done.getCount() != 0) {
            return this;
        }
        throw fail("Subscriber terminated!");
    }

    public final boolean awaitTerminalEvent() {
        try {
            await();
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public final boolean awaitTerminalEvent(long j, TimeUnit timeUnit) {
        try {
            return await(j, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public final U assertErrorMessage(String str) {
        int size = this.errors.size();
        if (size == 0) {
            throw fail("No errors");
        } else if (size == 1) {
            String message = ((Throwable) this.errors.get(0)).getMessage();
            if (ObjectHelper.equals(str, message)) {
                return this;
            }
            throw fail("Error message differs; Expected: " + str + ", Actual: " + message);
        } else {
            throw fail("Multiple errors");
        }
    }

    public final List<List<Object>> getEvents() {
        List<List<Object>> arrayList = new ArrayList();
        arrayList.add(values());
        arrayList.add(errors());
        List arrayList2 = new ArrayList();
        for (long j = 0; j < this.completions; j++) {
            arrayList2.add(Notification.createOnComplete());
        }
        arrayList.add(arrayList2);
        return arrayList;
    }

    public final U assertResult(T... tArr) {
        return assertSubscribed().assertValues(tArr).assertNoErrors().assertComplete();
    }

    public final U assertFailure(Class<? extends Throwable> cls, T... tArr) {
        return assertSubscribed().assertValues(tArr).assertError((Class) cls).assertNotComplete();
    }

    public final U assertFailure(Predicate<Throwable> predicate, T... tArr) {
        return assertSubscribed().assertValues(tArr).assertError((Predicate) predicate).assertNotComplete();
    }

    public final U assertFailureAndMessage(Class<? extends Throwable> cls, String str, T... tArr) {
        return assertSubscribed().assertValues(tArr).assertError((Class) cls).assertErrorMessage(str).assertNotComplete();
    }

    public final U awaitDone(long j, TimeUnit timeUnit) {
        try {
            if (!this.done.await(j, timeUnit)) {
                this.timeout = true;
                dispose();
            }
            return this;
        } catch (Throwable e) {
            dispose();
            throw ExceptionHelper.wrapOrThrow(e);
        }
    }

    public final U assertEmpty() {
        return assertSubscribed().assertNoValues().assertNoErrors().assertNotComplete();
    }

    public final U withTag(CharSequence charSequence) {
        this.tag = charSequence;
        return this;
    }

    public final U awaitCount(int i) {
        return awaitCount(i, TestWaitStrategy.SLEEP_10MS, 5000);
    }

    public final U awaitCount(int i, Runnable runnable) {
        return awaitCount(i, runnable, 5000);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final U awaitCount(int r8, java.lang.Runnable r9, long r10) {
        /*
        r7 = this;
        r4 = 0;
        r0 = java.lang.System.currentTimeMillis();
    L_0x0006:
        r2 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0017;
    L_0x000a:
        r2 = java.lang.System.currentTimeMillis();
        r2 = r2 - r0;
        r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r2 < 0) goto L_0x0017;
    L_0x0013:
        r0 = 1;
        r7.timeout = r0;
    L_0x0016:
        return r7;
    L_0x0017:
        r2 = r7.done;
        r2 = r2.getCount();
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 == 0) goto L_0x0016;
    L_0x0021:
        r2 = r7.values;
        r2 = r2.size();
        if (r2 >= r8) goto L_0x0016;
    L_0x0029:
        r9.run();
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.observers.BaseTestConsumer.awaitCount(int, java.lang.Runnable, long):U");
    }

    public final boolean isTimeout() {
        return this.timeout;
    }

    public final U clearTimeout() {
        this.timeout = false;
        return this;
    }

    public final U assertTimeout() {
        if (this.timeout) {
            return this;
        }
        throw fail("No timeout?!");
    }

    public final U assertNoTimeout() {
        if (!this.timeout) {
            return this;
        }
        throw fail("Timeout?!");
    }
}
