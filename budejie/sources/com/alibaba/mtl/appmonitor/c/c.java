package com.alibaba.mtl.appmonitor.c;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class c<T extends b> {
    private static AtomicLong c = new AtomicLong(0);
    private static AtomicLong d = new AtomicLong(0);
    private ConcurrentLinkedQueue<T> a = new ConcurrentLinkedQueue();
    /* renamed from: a */
    private AtomicLong f28a = new AtomicLong(0);
    private Integer b = null;
    /* renamed from: b */
    private Set<Integer> f29b = new HashSet();
    /* renamed from: b */
    private AtomicLong f30b = new AtomicLong(0);
    private final int m = 20;

    public T a() {
        c.getAndIncrement();
        this.f28a.getAndIncrement();
        b bVar = (b) this.a.poll();
        if (bVar != null) {
            this.f29b.remove(Integer.valueOf(System.identityHashCode(bVar)));
            this.f30b.getAndIncrement();
            d.getAndIncrement();
        }
        return bVar;
    }

    public void a(T t) {
        t.clean();
        if (this.a.size() < 20) {
            synchronized (this.f29b) {
                int identityHashCode = System.identityHashCode(t);
                if (!this.f29b.contains(Integer.valueOf(identityHashCode))) {
                    this.f29b.add(Integer.valueOf(identityHashCode));
                    this.a.offer(t);
                }
            }
        }
    }
}
