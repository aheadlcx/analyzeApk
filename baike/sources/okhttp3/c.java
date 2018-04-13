package okhttp3;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okio.Okio;

class c implements Iterator<String> {
    final Iterator<Snapshot> a = this.d.b.snapshots();
    @Nullable
    String b;
    boolean c;
    final /* synthetic */ Cache d;

    c(Cache cache) throws IOException {
        this.d = cache;
    }

    public boolean hasNext() {
        if (this.b != null) {
            return true;
        }
        this.c = false;
        while (this.a.hasNext()) {
            Snapshot snapshot = (Snapshot) this.a.next();
            try {
                this.b = Okio.buffer(snapshot.getSource(0)).readUtf8LineStrict();
                snapshot.close();
                return true;
            } catch (IOException e) {
                snapshot.close();
            } catch (Throwable th) {
                snapshot.close();
                throw th;
            }
        }
        return false;
    }

    public String next() {
        if (hasNext()) {
            String str = this.b;
            this.b = null;
            this.c = true;
            return str;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.c) {
            this.a.remove();
            return;
        }
        throw new IllegalStateException("remove() before next()");
    }
}
