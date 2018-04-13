package okhttp3.internal.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import okhttp3.internal.cache.DiskLruCache.Snapshot;

class d implements Iterator<Snapshot> {
    final Iterator<DiskLruCache$a> a = new ArrayList(this.d.f.values()).iterator();
    Snapshot b;
    Snapshot c;
    final /* synthetic */ DiskLruCache d;

    d(DiskLruCache diskLruCache) {
        this.d = diskLruCache;
    }

    public boolean hasNext() {
        if (this.b != null) {
            return true;
        }
        synchronized (this.d) {
            if (this.d.j) {
                return false;
            }
            while (this.a.hasNext()) {
                Snapshot a = ((DiskLruCache$a) this.a.next()).a();
                if (a != null) {
                    this.b = a;
                    return true;
                }
            }
            return false;
        }
    }

    public Snapshot next() {
        if (hasNext()) {
            this.c = this.b;
            this.b = null;
            return this.c;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.c == null) {
            throw new IllegalStateException("remove() before next()");
        }
        try {
            this.d.remove(Snapshot.a(this.c));
        } catch (IOException e) {
        } finally {
            this.c = null;
        }
    }
}
