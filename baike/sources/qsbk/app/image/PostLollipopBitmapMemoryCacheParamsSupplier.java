package qsbk.app.image;

import android.app.ActivityManager;
import android.os.Build.VERSION;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

public class PostLollipopBitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
    private ActivityManager a;

    public PostLollipopBitmapMemoryCacheParamsSupplier(ActivityManager activityManager) {
        this.a = activityManager;
    }

    public MemoryCacheParams get() {
        if (VERSION.SDK_INT < 21 || VERSION.SDK_INT > 23) {
            return new MemoryCacheParams(a(), 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        int a = a();
        int i = (a / 2097152) * 2;
        return new MemoryCacheParams(a, i, a, i, 2097152);
    }

    private int a() {
        int min = Math.min(this.a.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return 4194304;
        }
        if (min < 67108864) {
            return 6291456;
        }
        if (VERSION.SDK_INT <= 9) {
            return 8388608;
        }
        return min / 8;
    }
}
