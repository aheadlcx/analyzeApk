package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.c;
import okhttp3.internal.connection.d;
import okhttp3.internal.connection.f;
import okhttp3.internal.connection.f.a;
import okhttp3.internal.e.e;

public final class j {
    static final /* synthetic */ boolean c = (!j.class.desiredAssertionStatus());
    private static final Executor d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), c.a("OkHttp ConnectionPool", true));
    final d a;
    boolean b;
    private final int e;
    private final long f;
    private final Runnable g;
    private final Deque<okhttp3.internal.connection.c> h;

    public j() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public j(int i, long j, TimeUnit timeUnit) {
        this.g = new j$1(this);
        this.h = new ArrayDeque();
        this.a = new d();
        this.e = i;
        this.f = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }

    @Nullable
    okhttp3.internal.connection.c a(a aVar, f fVar, ac acVar) {
        if (c || Thread.holdsLock(this)) {
            for (okhttp3.internal.connection.c cVar : this.h) {
                if (cVar.a(aVar, acVar)) {
                    fVar.a(cVar);
                    return cVar;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    @Nullable
    Socket a(a aVar, f fVar) {
        if (c || Thread.holdsLock(this)) {
            for (okhttp3.internal.connection.c cVar : this.h) {
                if (cVar.a(aVar, null) && cVar.e() && cVar != fVar.b()) {
                    return fVar.b(cVar);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    void a(okhttp3.internal.connection.c cVar) {
        if (c || Thread.holdsLock(this)) {
            if (!this.b) {
                this.b = true;
                d.execute(this.g);
            }
            this.h.add(cVar);
            return;
        }
        throw new AssertionError();
    }

    boolean b(okhttp3.internal.connection.c cVar) {
        if (!c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (cVar.a || this.e == 0) {
            this.h.remove(cVar);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    long a(long j) {
        okhttp3.internal.connection.c cVar = null;
        long j2 = Long.MIN_VALUE;
        synchronized (this) {
            int i = 0;
            int i2 = 0;
            for (okhttp3.internal.connection.c cVar2 : this.h) {
                long j3;
                if (a(cVar2, j) > 0) {
                    i2++;
                } else {
                    okhttp3.internal.connection.c cVar3;
                    int i3 = i + 1;
                    long j4 = j - cVar2.e;
                    if (j4 > j2) {
                        long j5 = j4;
                        cVar3 = cVar2;
                        j3 = j5;
                    } else {
                        cVar3 = cVar;
                        j3 = j2;
                    }
                    j2 = j3;
                    cVar = cVar3;
                    i = i3;
                }
            }
            if (j2 >= this.f || i > this.e) {
                this.h.remove(cVar);
                c.a(cVar.c());
                return 0;
            } else if (i > 0) {
                j3 = this.f - j2;
                return j3;
            } else if (i2 > 0) {
                j3 = this.f;
                return j3;
            } else {
                this.b = false;
                return -1;
            }
        }
    }

    private int a(okhttp3.internal.connection.c cVar, long j) {
        List list = cVar.d;
        int i = 0;
        while (i < list.size()) {
            Reference reference = (Reference) list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                a aVar = (a) reference;
                e.b().a("A connection to " + cVar.a().a().a() + " was leaked. Did you forget to close a response body?", aVar.a);
                list.remove(i);
                cVar.a = true;
                if (list.isEmpty()) {
                    cVar.e = j - this.f;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
