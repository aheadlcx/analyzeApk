package qsbk.app.cache;

import java.io.File;
import qsbk.app.cache.SecureFileCache.Callback;

class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Callback b;
    final /* synthetic */ SecureFileCache c;

    g(SecureFileCache secureFileCache, String str, Callback callback) {
        this.c = secureFileCache;
        this.a = str;
        this.b = callback;
    }

    public void run() {
        File c = this.c.b(this.c.c(this.a));
        this.b.onFinished(c, SecureFileCache.b(c));
    }
}
