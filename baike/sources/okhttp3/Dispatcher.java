package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.n.a;

public final class Dispatcher {
    private int a = 64;
    private int b = 5;
    @Nullable
    private Runnable c;
    @Nullable
    private ExecutorService d;
    private final Deque<a> e = new ArrayDeque();
    private final Deque<a> f = new ArrayDeque();
    private final Deque<n> g = new ArrayDeque();

    public Dispatcher(ExecutorService executorService) {
        this.d = executorService;
    }

    public synchronized ExecutorService executorService() {
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.d;
    }

    public synchronized void setMaxRequests(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.a = i;
        a();
    }

    public synchronized int getMaxRequests() {
        return this.a;
    }

    public synchronized void setMaxRequestsPerHost(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.b = i;
        a();
    }

    public synchronized int getMaxRequestsPerHost() {
        return this.b;
    }

    public synchronized void setIdleCallback(@Nullable Runnable runnable) {
        this.c = runnable;
    }

    synchronized void a(a aVar) {
        if (this.f.size() >= this.a || c(aVar) >= this.b) {
            this.e.add(aVar);
        } else {
            this.f.add(aVar);
            executorService().execute(aVar);
        }
    }

    public synchronized void cancelAll() {
        for (a b : this.e) {
            b.b().cancel();
        }
        for (a b2 : this.f) {
            b2.b().cancel();
        }
        for (n cancel : this.g) {
            cancel.cancel();
        }
    }

    private void a() {
        if (this.f.size() < this.a && !this.e.isEmpty()) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (c(aVar) < this.b) {
                    it.remove();
                    this.f.add(aVar);
                    executorService().execute(aVar);
                }
                if (this.f.size() >= this.a) {
                    return;
                }
            }
        }
    }

    private int c(a aVar) {
        int i = 0;
        for (a a : this.f) {
            int i2;
            if (a.a().equals(aVar.a())) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    synchronized void a(n nVar) {
        this.g.add(nVar);
    }

    void b(a aVar) {
        a(this.f, aVar, true);
    }

    void b(n nVar) {
        a(this.g, nVar, false);
    }

    private <T> void a(Deque<T> deque, T t, boolean z) {
        synchronized (this) {
            if (deque.remove(t)) {
                if (z) {
                    a();
                }
                int runningCallsCount = runningCallsCount();
                Runnable runnable = this.c;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (runningCallsCount == 0 && runnable != null) {
            runnable.run();
        }
    }

    public synchronized List<Call> queuedCalls() {
        List arrayList;
        arrayList = new ArrayList();
        for (a b : this.e) {
            arrayList.add(b.b());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<Call> runningCalls() {
        List arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.g);
        for (a b : this.f) {
            arrayList.add(b.b());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int queuedCallsCount() {
        return this.e.size();
    }

    public synchronized int runningCallsCount() {
        return this.f.size() + this.g.size();
    }
}
