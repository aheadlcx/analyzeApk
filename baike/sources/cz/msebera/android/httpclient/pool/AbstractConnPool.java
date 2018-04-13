package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.concurrent.FutureCallback;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.Asserts;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public abstract class AbstractConnPool<T, C, E extends PoolEntry<T, C>> implements ConnPool<T, E>, ConnPoolControl<T> {
    private final Lock a = new ReentrantLock();
    private final ConnFactory<T, C> b;
    private final Map<T, f<T, C, E>> c = new HashMap();
    private final Set<E> d = new HashSet();
    private final LinkedList<E> e = new LinkedList();
    private final LinkedList<e<E>> f = new LinkedList();
    private final Map<T, Integer> g = new HashMap();
    private volatile boolean h;
    private volatile int i;
    private volatile int j;
    private volatile int k;

    protected abstract E a(T t, C c);

    public AbstractConnPool(ConnFactory<T, C> connFactory, int i, int i2) {
        this.b = (ConnFactory) Args.notNull(connFactory, "Connection factory");
        this.i = Args.positive(i, "Max per route value");
        this.j = Args.positive(i2, "Max total value");
    }

    protected void b(E e) {
    }

    protected void c(E e) {
    }

    protected void d(E e) {
    }

    protected boolean a(E e) {
        return true;
    }

    public boolean isShutdown() {
        return this.h;
    }

    public void shutdown() throws IOException {
        if (!this.h) {
            this.h = true;
            this.a.lock();
            try {
                Iterator it = this.e.iterator();
                while (it.hasNext()) {
                    ((PoolEntry) it.next()).close();
                }
                for (PoolEntry close : this.d) {
                    close.close();
                }
                for (f shutdown : this.c.values()) {
                    shutdown.shutdown();
                }
                this.c.clear();
                this.d.clear();
                this.e.clear();
            } finally {
                this.a.unlock();
            }
        }
    }

    private f<T, C, E> a(T t) {
        f<T, C, E> fVar = (f) this.c.get(t);
        if (fVar != null) {
            return fVar;
        }
        f aVar = new a(this, t, t);
        this.c.put(t, aVar);
        return aVar;
    }

    public Future<E> lease(T t, Object obj, FutureCallback<E> futureCallback) {
        Args.notNull(t, "Route");
        Asserts.check(!this.h, "Connection pool shut down");
        return new b(this, this.a, futureCallback, t, obj);
    }

    public Future<E> lease(T t, Object obj) {
        return lease(t, obj, null);
    }

    private E a(T t, Object obj, long j, TimeUnit timeUnit, e<E> eVar) throws IOException, InterruptedException, TimeoutException {
        Date date = null;
        if (j > 0) {
            date = new Date(System.currentTimeMillis() + timeUnit.toMillis(j));
        }
        this.a.lock();
        f a = a((Object) t);
        PoolEntry poolEntry = null;
        while (poolEntry == null) {
            Asserts.check(!this.h, "Connection pool shut down");
            while (true) {
                poolEntry = a.getFree(obj);
                if (poolEntry != null) {
                    if (poolEntry.isExpired(System.currentTimeMillis())) {
                        poolEntry.close();
                    } else if (this.k > 0 && poolEntry.getUpdated() + ((long) this.k) <= System.currentTimeMillis() && !a(poolEntry)) {
                        poolEntry.close();
                    }
                    if (!poolEntry.isClosed()) {
                        break;
                    }
                    this.e.remove(poolEntry);
                    a.free(poolEntry, false);
                } else {
                    break;
                }
            }
            if (poolEntry != null) {
                this.e.remove(poolEntry);
                this.d.add(poolEntry);
                d(poolEntry);
                this.a.unlock();
                return poolEntry;
            }
            int i;
            int b = b((Object) t);
            int max = Math.max(0, (a.getAllocatedCount() + 1) - b);
            if (max > 0) {
                for (i = 0; i < max; i++) {
                    PoolEntry lastUsed = a.getLastUsed();
                    if (lastUsed == null) {
                        break;
                    }
                    lastUsed.close();
                    this.e.remove(lastUsed);
                    a.remove(lastUsed);
                }
            }
            if (a.getAllocatedCount() < b) {
                i = Math.max(this.j - this.d.size(), 0);
                if (i > 0) {
                    if (this.e.size() > i - 1 && !this.e.isEmpty()) {
                        PoolEntry poolEntry2 = (PoolEntry) this.e.removeLast();
                        poolEntry2.close();
                        a(poolEntry2.getRoute()).remove(poolEntry2);
                    }
                    E add = a.add(this.b.create(t));
                    this.d.add(add);
                    this.a.unlock();
                    return add;
                }
            }
            try {
                a.queue(eVar);
                this.f.add(eVar);
                boolean await = eVar.await(date);
                a.unqueue(eVar);
                this.f.remove(eVar);
                if (!await && date != null && date.getTime() <= System.currentTimeMillis()) {
                    break;
                }
            } catch (Throwable th) {
                this.a.unlock();
            }
        }
        throw new TimeoutException("Timeout waiting for connection");
    }

    public void release(E e, boolean z) {
        this.a.lock();
        try {
            if (this.d.remove(e)) {
                f a = a(e.getRoute());
                a.free(e, z);
                if (!z || this.h) {
                    e.close();
                } else {
                    this.e.addFirst(e);
                    c(e);
                }
                e nextPending = a.nextPending();
                if (nextPending != null) {
                    this.f.remove(nextPending);
                } else {
                    nextPending = (e) this.f.poll();
                }
                if (nextPending != null) {
                    nextPending.wakeup();
                }
            }
            this.a.unlock();
        } catch (Throwable th) {
            this.a.unlock();
        }
    }

    private int b(T t) {
        Integer num = (Integer) this.g.get(t);
        if (num != null) {
            return num.intValue();
        }
        return this.i;
    }

    public void setMaxTotal(int i) {
        Args.positive(i, "Max value");
        this.a.lock();
        try {
            this.j = i;
        } finally {
            this.a.unlock();
        }
    }

    public int getMaxTotal() {
        this.a.lock();
        try {
            int i = this.j;
            return i;
        } finally {
            this.a.unlock();
        }
    }

    public void setDefaultMaxPerRoute(int i) {
        Args.positive(i, "Max per route value");
        this.a.lock();
        try {
            this.i = i;
        } finally {
            this.a.unlock();
        }
    }

    public int getDefaultMaxPerRoute() {
        this.a.lock();
        try {
            int i = this.i;
            return i;
        } finally {
            this.a.unlock();
        }
    }

    public void setMaxPerRoute(T t, int i) {
        Args.notNull(t, "Route");
        Args.positive(i, "Max per route value");
        this.a.lock();
        try {
            this.g.put(t, Integer.valueOf(i));
        } finally {
            this.a.unlock();
        }
    }

    public int getMaxPerRoute(T t) {
        Args.notNull(t, "Route");
        this.a.lock();
        try {
            int b = b((Object) t);
            return b;
        } finally {
            this.a.unlock();
        }
    }

    public PoolStats getTotalStats() {
        this.a.lock();
        try {
            PoolStats poolStats = new PoolStats(this.d.size(), this.f.size(), this.e.size(), this.j);
            return poolStats;
        } finally {
            this.a.unlock();
        }
    }

    public PoolStats getStats(T t) {
        Args.notNull(t, "Route");
        this.a.lock();
        PoolStats poolStats;
        try {
            f a = a((Object) t);
            poolStats = new PoolStats(a.getLeasedCount(), a.getPendingCount(), a.getAvailableCount(), b((Object) t));
            return poolStats;
        } finally {
            poolStats = this.a;
            poolStats.unlock();
        }
    }

    public Set<T> getRoutes() {
        this.a.lock();
        try {
            Set<T> hashSet = new HashSet(this.c.keySet());
            return hashSet;
        } finally {
            this.a.unlock();
        }
    }

    protected void a(PoolEntryCallback<T, C> poolEntryCallback) {
        this.a.lock();
        try {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                PoolEntry poolEntry = (PoolEntry) it.next();
                poolEntryCallback.process(poolEntry);
                if (poolEntry.isClosed()) {
                    a(poolEntry.getRoute()).remove(poolEntry);
                    it.remove();
                }
            }
            a();
        } finally {
            this.a.unlock();
        }
    }

    private void a() {
        Iterator it = this.c.entrySet().iterator();
        while (it.hasNext()) {
            f fVar = (f) ((Entry) it.next()).getValue();
            if (fVar.getAllocatedCount() + fVar.getPendingCount() == 0) {
                it.remove();
            }
        }
    }

    public void closeIdle(long j, TimeUnit timeUnit) {
        long j2 = 0;
        Args.notNull(timeUnit, "Time unit");
        long toMillis = timeUnit.toMillis(j);
        if (toMillis >= 0) {
            j2 = toMillis;
        }
        a(new c(this, System.currentTimeMillis() - j2));
    }

    public void closeExpired() {
        a(new d(this, System.currentTimeMillis()));
    }

    public int getValidateAfterInactivity() {
        return this.k;
    }

    public void setValidateAfterInactivity(int i) {
        this.k = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[leased: ");
        stringBuilder.append(this.d);
        stringBuilder.append("][available: ");
        stringBuilder.append(this.e);
        stringBuilder.append("][pending: ");
        stringBuilder.append(this.f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
