package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@NotThreadSafe
abstract class f<T, C, E extends PoolEntry<T, C>> {
    private final T a;
    private final Set<E> b = new HashSet();
    private final LinkedList<E> c = new LinkedList();
    private final LinkedList<e<E>> d = new LinkedList();

    protected abstract E a(C c);

    f(T t) {
        this.a = t;
    }

    public final T getRoute() {
        return this.a;
    }

    public int getLeasedCount() {
        return this.b.size();
    }

    public int getPendingCount() {
        return this.d.size();
    }

    public int getAvailableCount() {
        return this.c.size();
    }

    public int getAllocatedCount() {
        return this.c.size() + this.b.size();
    }

    public E getFree(Object obj) {
        if (!this.c.isEmpty()) {
            Iterator it;
            PoolEntry poolEntry;
            if (obj != null) {
                it = this.c.iterator();
                while (it.hasNext()) {
                    poolEntry = (PoolEntry) it.next();
                    if (obj.equals(poolEntry.getState())) {
                        it.remove();
                        this.b.add(poolEntry);
                        return poolEntry;
                    }
                }
            }
            it = this.c.iterator();
            while (it.hasNext()) {
                poolEntry = (PoolEntry) it.next();
                if (poolEntry.getState() == null) {
                    it.remove();
                    this.b.add(poolEntry);
                    return poolEntry;
                }
            }
        }
        return null;
    }

    public E getLastUsed() {
        if (this.c.isEmpty()) {
            return null;
        }
        return (PoolEntry) this.c.getLast();
    }

    public boolean remove(E e) {
        Args.notNull(e, "Pool entry");
        if (this.c.remove(e) || this.b.remove(e)) {
            return true;
        }
        return false;
    }

    public void free(E e, boolean z) {
        Args.notNull(e, "Pool entry");
        Asserts.check(this.b.remove(e), "Entry %s has not been leased from this pool", (Object) e);
        if (z) {
            this.c.addFirst(e);
        }
    }

    public E add(C c) {
        E a = a(c);
        this.b.add(a);
        return a;
    }

    public void queue(e<E> eVar) {
        if (eVar != null) {
            this.d.add(eVar);
        }
    }

    public e<E> nextPending() {
        return (e) this.d.poll();
    }

    public void unqueue(e<E> eVar) {
        if (eVar != null) {
            this.d.remove(eVar);
        }
    }

    public void shutdown() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((e) it.next()).cancel(true);
        }
        this.d.clear();
        it = this.c.iterator();
        while (it.hasNext()) {
            ((PoolEntry) it.next()).close();
        }
        this.c.clear();
        for (PoolEntry close : this.b) {
            close.close();
        }
        this.b.clear();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[route: ");
        stringBuilder.append(this.a);
        stringBuilder.append("][leased: ");
        stringBuilder.append(this.b.size());
        stringBuilder.append("][available: ");
        stringBuilder.append(this.c.size());
        stringBuilder.append("][pending: ");
        stringBuilder.append(this.d.size());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
