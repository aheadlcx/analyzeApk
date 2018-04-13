package com.facebook.common.references;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public final class CloseableReference<T> implements Closeable, Cloneable {
    private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER = new ResourceReleaser<Closeable>() {
        public void release(Closeable closeable) {
            try {
                Closeables.close(closeable, true);
            } catch (IOException e) {
            }
        }
    };
    private static Class<CloseableReference> TAG = CloseableReference.class;
    @GuardedBy("this")
    private boolean mIsClosed = false;
    private final SharedReference<T> mSharedReference;

    private CloseableReference(SharedReference<T> sharedReference) {
        this.mSharedReference = (SharedReference) Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
    }

    private CloseableReference(T t, ResourceReleaser<T> resourceReleaser) {
        this.mSharedReference = new SharedReference(t, resourceReleaser);
    }

    public static <T extends Closeable> CloseableReference<T> of(@PropagatesNullable T t) {
        if (t == null) {
            return null;
        }
        return new CloseableReference(t, DEFAULT_CLOSEABLE_RELEASER);
    }

    public static <T> CloseableReference<T> of(@PropagatesNullable T t, ResourceReleaser<T> resourceReleaser) {
        if (t == null) {
            return null;
        }
        return new CloseableReference(t, resourceReleaser);
    }

    public synchronized T get() {
        Preconditions.checkState(!this.mIsClosed);
        return this.mSharedReference.get();
    }

    public synchronized CloseableReference<T> clone() {
        Preconditions.checkState(isValid());
        return new CloseableReference(this.mSharedReference);
    }

    @Nullable
    public synchronized CloseableReference<T> cloneOrNull() {
        CloseableReference<T> clone;
        if (isValid()) {
            clone = clone();
        } else {
            clone = null;
        }
        return clone;
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }

    @VisibleForTesting
    public synchronized SharedReference<T> getUnderlyingReferenceTestOnly() {
        return this.mSharedReference;
    }

    public int getValueHash() {
        return isValid() ? System.identityHashCode(this.mSharedReference.get()) : 0;
    }

    public void close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            this.mIsClosed = true;
            this.mSharedReference.deleteReference();
        }
    }

    public static boolean isValid(@Nullable CloseableReference<?> closeableReference) {
        return closeableReference != null && closeableReference.isValid();
    }

    @Nullable
    public static <T> CloseableReference<T> cloneOrNull(@Nullable CloseableReference<T> closeableReference) {
        return closeableReference != null ? closeableReference.cloneOrNull() : null;
    }

    public static <T> List<CloseableReference<T>> cloneOrNull(@PropagatesNullable Collection<CloseableReference<T>> collection) {
        if (collection == null) {
            return null;
        }
        List<CloseableReference<T>> arrayList = new ArrayList(collection.size());
        for (CloseableReference cloneOrNull : collection) {
            arrayList.add(cloneOrNull(cloneOrNull));
        }
        return arrayList;
    }

    public static void closeSafely(@Nullable CloseableReference<?> closeableReference) {
        if (closeableReference != null) {
            closeableReference.close();
        }
    }

    public static void closeSafely(@Nullable Iterable<? extends CloseableReference<?>> iterable) {
        if (iterable != null) {
            for (CloseableReference closeSafely : iterable) {
                closeSafely(closeSafely);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void finalize() throws java.lang.Throwable {
        /*
        r5 = this;
        monitor-enter(r5);	 Catch:{ all -> 0x0048 }
        r0 = r5.mIsClosed;	 Catch:{ all -> 0x0045 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        monitor-exit(r5);	 Catch:{ all -> 0x0045 }
        super.finalize();
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r5);	 Catch:{ all -> 0x0045 }
        r0 = TAG;	 Catch:{ all -> 0x0048 }
        r1 = "Finalized without closing: %x %x (type = %s)";
        r2 = 3;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0048 }
        r3 = 0;
        r4 = java.lang.System.identityHashCode(r5);	 Catch:{ all -> 0x0048 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0048 }
        r2[r3] = r4;	 Catch:{ all -> 0x0048 }
        r3 = 1;
        r4 = r5.mSharedReference;	 Catch:{ all -> 0x0048 }
        r4 = java.lang.System.identityHashCode(r4);	 Catch:{ all -> 0x0048 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x0048 }
        r2[r3] = r4;	 Catch:{ all -> 0x0048 }
        r3 = 2;
        r4 = r5.mSharedReference;	 Catch:{ all -> 0x0048 }
        r4 = r4.get();	 Catch:{ all -> 0x0048 }
        r4 = r4.getClass();	 Catch:{ all -> 0x0048 }
        r4 = r4.getSimpleName();	 Catch:{ all -> 0x0048 }
        r2[r3] = r4;	 Catch:{ all -> 0x0048 }
        com.facebook.common.logging.FLog.w(r0, r1, r2);	 Catch:{ all -> 0x0048 }
        r5.close();	 Catch:{ all -> 0x0048 }
        super.finalize();
        goto L_0x0009;
    L_0x0045:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0045 }
        throw r0;	 Catch:{ all -> 0x0048 }
    L_0x0048:
        r0 = move-exception;
        super.finalize();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.CloseableReference.finalize():void");
    }
}
