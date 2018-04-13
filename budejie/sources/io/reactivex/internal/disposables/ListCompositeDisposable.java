package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class ListCompositeDisposable implements Disposable, DisposableContainer {
    volatile boolean disposed;
    List<Disposable> resources;

    public ListCompositeDisposable(Disposable... disposableArr) {
        ObjectHelper.requireNonNull(disposableArr, "resources is null");
        this.resources = new LinkedList();
        for (Object obj : disposableArr) {
            ObjectHelper.requireNonNull(obj, "Disposable item is null");
            this.resources.add(obj);
        }
    }

    public ListCompositeDisposable(Iterable<? extends Disposable> iterable) {
        ObjectHelper.requireNonNull(iterable, "resources is null");
        this.resources = new LinkedList();
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
                List list = this.resources;
                this.resources = null;
                dispose(list);
            }
        }
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean add(Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "d is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!this.disposed) {
                    List list = this.resources;
                    if (list == null) {
                        list = new LinkedList();
                        this.resources = list;
                    }
                    list.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    public boolean addAll(Disposable... disposableArr) {
        int length;
        boolean z = false;
        ObjectHelper.requireNonNull(disposableArr, "ds is null");
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                } else {
                    List list = this.resources;
                    if (list == null) {
                        list = new LinkedList();
                        this.resources = list;
                    }
                    length = disposableArr.length;
                    int i;
                    while (i < length) {
                        Object obj = disposableArr[i];
                        ObjectHelper.requireNonNull(obj, "d is null");
                        list.add(obj);
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

    public boolean remove(Disposable disposable) {
        if (!delete(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean delete(io.reactivex.disposables.Disposable r3) {
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
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.disposables.ListCompositeDisposable.delete(io.reactivex.disposables.Disposable):boolean");
    }

    public void clear() {
        if (!this.disposed) {
            synchronized (this) {
                if (this.disposed) {
                    return;
                }
                List list = this.resources;
                this.resources = null;
                dispose(list);
            }
        }
    }

    void dispose(List<Disposable> list) {
        if (list != null) {
            Iterable iterable = null;
            for (Disposable dispose : list) {
                try {
                    dispose.dispose();
                } catch (Throwable th) {
                    List arrayList;
                    Exceptions.throwIfFatal(th);
                    if (iterable == null) {
                        arrayList = new ArrayList();
                    } else {
                        Iterable iterable2 = iterable;
                    }
                    arrayList.add(th);
                    iterable = arrayList;
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
