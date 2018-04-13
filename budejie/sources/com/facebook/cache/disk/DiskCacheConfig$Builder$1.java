package com.facebook.cache.disk;

import com.facebook.common.internal.Supplier;
import java.io.File;

class DiskCacheConfig$Builder$1 implements Supplier<File> {
    final /* synthetic */ DiskCacheConfig$Builder this$0;

    DiskCacheConfig$Builder$1(DiskCacheConfig$Builder diskCacheConfig$Builder) {
        this.this$0 = diskCacheConfig$Builder;
    }

    public File get() {
        return DiskCacheConfig$Builder.access$1000(this.this$0).getApplicationContext().getCacheDir();
    }
}
