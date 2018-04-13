package okhttp3.internal.cache;

import java.io.IOException;
import okio.Sink;

class e extends f {
    final /* synthetic */ DiskLruCache$Editor a;

    e(DiskLruCache$Editor diskLruCache$Editor, Sink sink) {
        this.a = diskLruCache$Editor;
        super(sink);
    }

    protected void a(IOException iOException) {
        synchronized (this.a.c) {
            this.a.a();
        }
    }
}
