package cn.v6.sixrooms.surfaceanim.util;

import android.app.ActivityManager;
import android.os.Build.VERSION;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.memory.DefaultFlexByteArrayPoolParams;

class FrescoUtil$b implements Supplier<MemoryCacheParams> {
    private ActivityManager a;

    public final /* synthetic */ Object get() {
        if (VERSION.SDK_INT >= 21) {
            return new MemoryCacheParams(a(), 50, 20971520, 200, 1048576);
        }
        return new MemoryCacheParams(a(), 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    FrescoUtil$b(ActivityManager activityManager) {
        this.a = activityManager;
    }

    private int a() {
        int min = Math.min(this.a.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return DefaultFlexByteArrayPoolParams.DEFAULT_MAX_BYTE_ARRAY_SIZE;
        }
        if (min < 67108864) {
            return 6291456;
        }
        return min / 6;
    }
}
