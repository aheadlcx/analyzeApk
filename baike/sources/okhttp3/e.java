package okhttp3;

import java.io.IOException;
import okhttp3.Cache.b;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okio.ForwardingSource;
import okio.Source;

class e extends ForwardingSource {
    final /* synthetic */ Snapshot a;
    final /* synthetic */ b b;

    e(b bVar, Source source, Snapshot snapshot) {
        this.b = bVar;
        this.a = snapshot;
        super(source);
    }

    public void close() throws IOException {
        this.a.close();
        super.close();
    }
}
