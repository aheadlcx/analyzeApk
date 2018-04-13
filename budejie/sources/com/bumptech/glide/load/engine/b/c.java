package com.bumptech.glide.load.engine.b;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class c {
    private final Map<com.bumptech.glide.load.b, a> a = new HashMap();
    private final b b = new b();

    private static class a {
        final Lock a;
        int b;

        private a() {
            this.a = new ReentrantLock();
        }
    }

    private static class b {
        private final Queue<a> a;

        private b() {
            this.a = new ArrayDeque();
        }

        a a() {
            synchronized (this.a) {
                a aVar = (a) this.a.poll();
            }
            if (aVar == null) {
                return new a();
            }
            return aVar;
        }

        void a(a aVar) {
            synchronized (this.a) {
                if (this.a.size() < 10) {
                    this.a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    void a(com.bumptech.glide.load.b bVar) {
        a aVar;
        synchronized (this) {
            aVar = (a) this.a.get(bVar);
            if (aVar == null) {
                aVar = this.b.a();
                this.a.put(bVar, aVar);
            }
            aVar.b++;
        }
        aVar.a.lock();
    }

    void b(com.bumptech.glide.load.b bVar) {
        a aVar;
        synchronized (this) {
            aVar = (a) this.a.get(bVar);
            if (aVar == null || aVar.b <= 0) {
                int i;
                StringBuilder append = new StringBuilder().append("Cannot release a lock that is not held, key: ").append(bVar).append(", interestedThreads: ");
                if (aVar == null) {
                    i = 0;
                } else {
                    i = aVar.b;
                }
                throw new IllegalArgumentException(append.append(i).toString());
            }
            int i2 = aVar.b - 1;
            aVar.b = i2;
            if (i2 == 0) {
                a aVar2 = (a) this.a.remove(bVar);
                if (aVar2.equals(aVar)) {
                    this.b.a(aVar2);
                } else {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVar2 + ", key: " + bVar);
                }
            }
        }
        aVar.a.unlock();
    }
}
