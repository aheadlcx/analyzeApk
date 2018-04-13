package cz.msebera.android.httpclient.pool;

import cz.msebera.android.httpclient.concurrent.FutureCallback;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;

class b extends e<E> {
    final /* synthetic */ Object a;
    final /* synthetic */ Object b;
    final /* synthetic */ AbstractConnPool c;

    b(AbstractConnPool abstractConnPool, Lock lock, FutureCallback futureCallback, Object obj, Object obj2) {
        this.c = abstractConnPool;
        this.a = obj;
        this.b = obj2;
        super(lock, futureCallback);
    }

    public E getPoolEntry(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, IOException {
        PoolEntry a = this.c.a(this.a, this.b, j, timeUnit, this);
        this.c.b(a);
        return a;
    }
}
