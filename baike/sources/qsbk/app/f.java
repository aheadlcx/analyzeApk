package qsbk.app;

import android.support.v4.util.LruCache;

final class f extends LruCache<String, String> {
    f(int i) {
        super(i);
    }

    protected int a(String str, String str2) {
        return str2.getBytes().length;
    }
}
