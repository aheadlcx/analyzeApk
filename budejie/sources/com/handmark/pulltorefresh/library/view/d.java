package com.handmark.pulltorefresh.library.view;

import android.graphics.Bitmap;
import android.util.LruCache;

final class d extends LruCache<String, Bitmap> {
    final /* synthetic */ SixroomRefreshPullView a;

    d(SixroomRefreshPullView sixroomRefreshPullView) {
        this.a = sixroomRefreshPullView;
        super(2097152);
    }

    protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
        return ((Bitmap) obj2).getByteCount();
    }
}
