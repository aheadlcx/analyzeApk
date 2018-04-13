package qsbk.app.image;

import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

final class d implements Supplier<MemoryCacheParams> {
    final /* synthetic */ MemoryCacheParams a;

    d(MemoryCacheParams memoryCacheParams) {
        this.a = memoryCacheParams;
    }

    public MemoryCacheParams get() {
        return this.a;
    }
}
