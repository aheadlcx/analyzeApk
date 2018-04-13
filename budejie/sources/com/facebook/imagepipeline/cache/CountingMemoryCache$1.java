package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory$BitmapCreationObserver;

class CountingMemoryCache$1 implements PlatformBitmapFactory$BitmapCreationObserver {
    final /* synthetic */ CountingMemoryCache this$0;

    CountingMemoryCache$1(CountingMemoryCache countingMemoryCache) {
        this.this$0 = countingMemoryCache;
    }

    public void onBitmapCreated(Bitmap bitmap, Object obj) {
        this.this$0.mOtherEntries.put(bitmap, obj);
    }
}
