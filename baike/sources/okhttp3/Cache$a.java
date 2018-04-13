package okhttp3;

import java.io.IOException;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.DiskLruCache$Editor;
import okio.Sink;

final class Cache$a implements CacheRequest {
    boolean a;
    final /* synthetic */ Cache b;
    private final DiskLruCache$Editor c;
    private Sink d;
    private Sink e;

    Cache$a(Cache cache, DiskLruCache$Editor diskLruCache$Editor) {
        this.b = cache;
        this.c = diskLruCache$Editor;
        this.d = diskLruCache$Editor.newSink(1);
        this.e = new d(this, this.d, cache, diskLruCache$Editor);
    }

    public void abort() {
        synchronized (this.b) {
            if (this.a) {
                return;
            }
            this.a = true;
            Cache cache = this.b;
            cache.d++;
            Util.closeQuietly(this.d);
            try {
                this.c.abort();
            } catch (IOException e) {
            }
        }
    }

    public Sink body() {
        return this.e;
    }
}
