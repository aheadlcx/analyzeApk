package io.reactivex.disposables;

import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.OpenHashSet;
import java.util.ArrayList;
import java.util.List;

public final class CompositeDisposable implements Disposable, DisposableContainer {
    volatile boolean disposed;
    OpenHashSet<Disposable> resources;

    public CompositeDisposable(@NonNull Disposable... disposableArr) {
        ObjectHelper.requireNonNull(disposableArr, "resources is null");
        this.resources = new OpenHashSet(disposableArr.length + 1);
        for (Object obj : disposableArr) {
            ObjectHelper.requireNonNull(obj, "Disposable item is null");
            this.resources.add(obj);
        }
    }

    public CompositeDisposable(@NonNull Iterable<? extends Disposable> iterable) {
        ObjectHelper.requireNonNull(iterable, "resources is null");
        this.resources = new OpenHashSet();
        for (Disposable disposable : iterable) {
            ObjectHelper.requireNonNull(disposable, "Disposable item is null");
            this.resources.add(disposable);
        }
    }

    public void dispose() {
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                    return;
                }
                this.disposed = true;
                OpenHashSet openHashSet = this.resources;
                this.resources = null;
                dispose(openHashSet);
            }
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean add(@NonNull Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "d is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    OpenHashSet openHashSet = this.resources;
                    if (openHashSet == null) {
                        openHashSet = new OpenHashSet();
                        this.resources = openHashSet;
                    }
                    openHashSet.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    public boolean addAll(@NonNull Disposable... disposableArr) {
        int length;
        boolean z = false;
        ObjectHelper.requireNonNull(disposableArr, "ds is null");
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                } else {
                    OpenHashSet openHashSet = this.resources;
                    if (openHashSet == null) {
                        openHashSet = new OpenHashSet(disposableArr.length + 1);
                        this.resources = openHashSet;
                    }
                    length = disposableArr.length;
                    int i;
                    while (i < length) {
                        Object obj = disposableArr[i];
                        ObjectHelper.requireNonNull(obj, "d is null");
                        openHashSet.add(obj);
                        i++;
                    }
                    z = true;
                }
            }
            return z;
        }
        for (Disposable dispose : disposableArr) {
            dispose.dispose();
        }
        return z;
    }

    public boolean remove(@NonNull Disposable disposable) {
        if (!delete(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean delete(@io.reactivex.annotations.NonNull io.reactivex.disposables.Disposable r3) {
        /*
        r2 = this;
        r0 = 0;
        r1 = "Disposable item is null";
        io.reactivex.internal.functions.ObjectHelper.requireNonNull(r3, r1);
        r1 = r2.disposed;
        if (r1 == 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        monitor-enter(r2);
        r1 = r2.disposed;	 Catch:{ all -> 0x0012 }
        if (r1 == 0) goto L_0x0015;
    L_0x0010:
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        goto L_0x000a;
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        throw r0;
    L_0x0015:
        r1 = r2.resources;	 Catch:{ all -> 0x0012 }
        if (r1 == 0) goto L_0x001f;
    L_0x0019:
        r1 = r1.remove(r3);	 Catch:{ all -> 0x0012 }
        if (r1 != 0) goto L_0x0021;
    L_0x001f:
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        goto L_0x000a;
    L_0x0021:
        monitor-exit(r2);	 Catch:{ all -> 0x0012 }
        r0 = 1;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.disposables.CompositeDisposable.delete(io.reactivex.disposables.Disposable):boolean");
    }

    public void clear() {
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                    return;
                }
                OpenHashSet openHashSet = this.resources;
                this.resources = null;
                dispose(openHashSet);
            }
        }
    }

    public int size() {
        int i = 0;
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                } else {
                    OpenHashSet openHashSet = this.resources;
                    if (openHashSet != null) {
                        i = openHashSet.size();
                    }
                }
            }
        }
        return i;
    }

    void dispose(OpenHashSet<Disposable> openHashSet) {
        if (openHashSet != null) {
            Iterable iterable = null;
            for (Object obj : openHashSet.keys()) {
                if (obj instanceof Disposable) {
                    try {
                        ((Disposable) obj).dispose();
                    } catch (Throwable th) {
                        List arrayList;
                        Throwable th2 = th;
                        Exceptions.throwIfFatal(th2);
                        if (iterable == null) {
                            arrayList = new ArrayList();
                        } else {
                            Iterable iterable2 = iterable;
                        }
                        arrayList.add(th2);
                        Object obj2 = arrayList;
                    }
                }
            }
            if (iterable == null) {
                return;
            }
            if (iterable.size() == 1) {
                throw ExceptionHelper.wrapOrThrow((Throwable) iterable.get(0));
            }
            throw new CompositeException(iterable);
        }
    }
}
