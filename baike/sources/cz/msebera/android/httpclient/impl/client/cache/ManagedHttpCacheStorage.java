package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.cache.HttpCacheEntry;
import cz.msebera.android.httpclient.client.cache.HttpCacheStorage;
import cz.msebera.android.httpclient.client.cache.HttpCacheUpdateCallback;
import cz.msebera.android.httpclient.util.Args;
import java.io.Closeable;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
public class ManagedHttpCacheStorage implements HttpCacheStorage, Closeable {
    private final j a;
    private final ReferenceQueue<HttpCacheEntry> b = new ReferenceQueue();
    private final Set<y> c = new HashSet();
    private final AtomicBoolean d = new AtomicBoolean(true);

    public ManagedHttpCacheStorage(CacheConfig cacheConfig) {
        this.a = new j(cacheConfig.getMaxCacheEntries());
    }

    private void a() throws IllegalStateException {
        if (!this.d.get()) {
            throw new IllegalStateException("Cache has been shut down");
        }
    }

    private void a(HttpCacheEntry httpCacheEntry) {
        if (httpCacheEntry.getResource() != null) {
            this.c.add(new y(httpCacheEntry, this.b));
        }
    }

    public void putEntry(String str, HttpCacheEntry httpCacheEntry) throws IOException {
        Args.notNull(str, "URL");
        Args.notNull(httpCacheEntry, "Cache entry");
        a();
        synchronized (this) {
            this.a.put(str, httpCacheEntry);
            a(httpCacheEntry);
        }
    }

    public HttpCacheEntry getEntry(String str) throws IOException {
        HttpCacheEntry httpCacheEntry;
        Args.notNull(str, "URL");
        a();
        synchronized (this) {
            httpCacheEntry = (HttpCacheEntry) this.a.get(str);
        }
        return httpCacheEntry;
    }

    public void removeEntry(String str) throws IOException {
        Args.notNull(str, "URL");
        a();
        synchronized (this) {
            this.a.remove(str);
        }
    }

    public void updateEntry(String str, HttpCacheUpdateCallback httpCacheUpdateCallback) throws IOException {
        Args.notNull(str, "URL");
        Args.notNull(httpCacheUpdateCallback, "Callback");
        a();
        synchronized (this) {
            HttpCacheEntry httpCacheEntry = (HttpCacheEntry) this.a.get(str);
            HttpCacheEntry update = httpCacheUpdateCallback.update(httpCacheEntry);
            this.a.put(str, update);
            if (httpCacheEntry != update) {
                a(update);
            }
        }
    }

    public void cleanResources() {
        if (this.d.get()) {
            while (true) {
                y yVar = (y) this.b.poll();
                if (yVar != null) {
                    synchronized (this) {
                        this.c.remove(yVar);
                    }
                    yVar.getResource().dispose();
                } else {
                    return;
                }
            }
        }
    }

    public void shutdown() {
        if (this.d.compareAndSet(true, false)) {
            synchronized (this) {
                this.a.clear();
                for (y resource : this.c) {
                    resource.getResource().dispose();
                }
                this.c.clear();
                do {
                } while (this.b.poll() != null);
            }
        }
    }

    public void close() {
        if (this.d.compareAndSet(true, false)) {
            synchronized (this) {
                while (true) {
                    y yVar = (y) this.b.poll();
                    if (yVar != null) {
                        this.c.remove(yVar);
                        yVar.getResource().dispose();
                    }
                }
            }
        }
    }
}
