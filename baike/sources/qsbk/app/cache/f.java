package qsbk.app.cache;

import java.io.File;
import qsbk.app.cache.SecureFileCache.Callback;

class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Callback b;
    final /* synthetic */ SecureFileCache c;

    f(SecureFileCache secureFileCache, String str, Callback callback) {
        this.c = secureFileCache;
        this.a = str;
        this.b = callback;
    }

    public void run() {
        File b = this.c.a(this.c.c(this.a));
        this.b.onFinished(b, SecureFileCache.b(b));
    }
}
