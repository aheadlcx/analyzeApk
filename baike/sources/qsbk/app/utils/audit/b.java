package qsbk.app.utils.audit;

import android.support.v4.util.LruCache;

class b extends LruCache<String, byte[]> {
    final /* synthetic */ SimpleImageLoader a;

    b(SimpleImageLoader simpleImageLoader, int i) {
        this.a = simpleImageLoader;
        super(i);
    }

    protected int a(String str, byte[] bArr) {
        return bArr.length;
    }
}
