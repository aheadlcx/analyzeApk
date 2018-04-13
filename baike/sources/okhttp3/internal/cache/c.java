package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

class c extends f {
    static final /* synthetic */ boolean a = (!DiskLruCache.class.desiredAssertionStatus());
    final /* synthetic */ DiskLruCache b;

    c(DiskLruCache diskLruCache, Sink sink) {
        this.b = diskLruCache;
        super(sink);
    }

    protected void a(IOException iOException) {
        if (a || Thread.holdsLock(this.b)) {
            this.b.h = true;
            return;
        }
        throw new AssertionError();
    }
}
