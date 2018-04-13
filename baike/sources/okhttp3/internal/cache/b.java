package okhttp3.internal.cache;

import java.io.IOException;
import okio.Okio;

class b implements Runnable {
    final /* synthetic */ DiskLruCache a;

    b(DiskLruCache diskLruCache) {
        this.a = diskLruCache;
    }

    public void run() {
        int i = 1;
        synchronized (this.a) {
            if (this.a.i) {
                i = 0;
            }
            if ((i | this.a.j) != 0) {
                return;
            }
            try {
                this.a.c();
            } catch (IOException e) {
                this.a.k = true;
            }
            try {
                if (this.a.b()) {
                    this.a.a();
                    this.a.g = 0;
                }
            } catch (IOException e2) {
                this.a.l = true;
                this.a.e = Okio.buffer(Okio.blackhole());
            }
        }
    }
}
