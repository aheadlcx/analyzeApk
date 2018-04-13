package cn.v6.sixrooms.surfaceanim.util;

import android.graphics.Bitmap;
import android.util.LruCache;

final class b extends LruCache<String, Bitmap> {
    final /* synthetic */ AnimSceneResManager a;

    b(AnimSceneResManager animSceneResManager, int i) {
        this.a = animSceneResManager;
        super(i);
    }

    protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
        Bitmap bitmap = (Bitmap) obj2;
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
