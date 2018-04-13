package okhttp3;

import java.io.IOException;
import okhttp3.internal.cache.DiskLruCache$Editor;
import okio.ForwardingSink;
import okio.Sink;

class d extends ForwardingSink {
    final /* synthetic */ Cache a;
    final /* synthetic */ DiskLruCache$Editor b;
    final /* synthetic */ Cache$a c;

    d(Cache$a cache$a, Sink sink, Cache cache, DiskLruCache$Editor diskLruCache$Editor) {
        this.c = cache$a;
        this.a = cache;
        this.b = diskLruCache$Editor;
        super(sink);
    }

    public void close() throws IOException {
        synchronized (this.c.b) {
            if (this.c.a) {
                return;
            }
            this.c.a = true;
            Cache cache = this.c.b;
            cache.c++;
            super.close();
            this.b.commit();
        }
    }
}
