package qsbk.app.image;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.os.Environment;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import qsbk.app.utils.HttpsConnectionUtil;

public class ImagePipelineConfigUtils {
    public static final String IMAGE_PIPELINE_CACHE_DIR = "image_cache";
    private static final int a = ((int) Runtime.getRuntime().maxMemory());
    private static final int b = (a / 8);
    private static MyMemoryTrimmableRegistry c;

    public static class MyMemoryTrimmableRegistry implements MemoryTrimmableRegistry {
        private final Set<MemoryTrimmable> a = Collections.newSetFromMap(new WeakHashMap());

        public void registerMemoryTrimmable(MemoryTrimmable memoryTrimmable) {
            this.a.add(memoryTrimmable);
        }

        public void unregisterMemoryTrimmable(MemoryTrimmable memoryTrimmable) {
            this.a.remove(memoryTrimmable);
        }

        void a(int i) {
            for (MemoryTrimmable memoryTrimmable : this.a) {
                if (memoryTrimmable != null) {
                    memoryTrimmable.trim(MemoryTrimType.OnCloseToDalvikHeapLimit);
                }
            }
        }
    }

    public static ImagePipelineConfig getDefaultImagePipelineConfig(Context context) {
        d dVar = new d(new MemoryCacheParams(b, Integer.MAX_VALUE, b, Integer.MAX_VALUE, Integer.MAX_VALUE));
        DiskCacheConfig build = DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile()).setBaseDirectoryPath(context.getExternalCacheDir()).setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR).setMaxCacheSize(104857600).setMaxCacheSizeOnLowDiskSpace(62914560).setMaxCacheSizeOnVeryLowDiskSpace(20971520).setDiskTrimmableRegistry(NoOpDiskTrimmableRegistry.getInstance()).build();
        Context applicationContext = context.getApplicationContext();
        e eVar = new e(applicationContext);
        OkHttpClient build2 = new Builder().dns(new OkHttpDns()).sslSocketFactory(HttpsConnectionUtil.getSslSocketFactory(), HttpsConnectionUtil.getX509TrustManager()).hostnameVerifier(HttpsConnectionUtil.getHostnameVerifier()).build();
        Set hashSet = new HashSet(1);
        hashSet.add(eVar);
        ImagePipelineConfig.Builder resizeAndRotateEnabledForNetwork = ImagePipelineConfig.newBuilder(context).setBitmapsConfig(Config.ARGB_8888).setNetworkFetcher(new OkHttpNetworkFetcher(build2)).setBitmapMemoryCacheParamsSupplier(new PostLollipopBitmapMemoryCacheParamsSupplier((ActivityManager) applicationContext.getSystemService("activity"))).setMainDiskCacheConfig(build).setRequestListeners(hashSet).setResizeAndRotateEnabledForNetwork(true);
        if (VERSION.SDK_INT != 19) {
            resizeAndRotateEnabledForNetwork.setDownsampleEnabled(true);
        }
        c = new MyMemoryTrimmableRegistry();
        c.registerMemoryTrimmable(new f());
        resizeAndRotateEnabledForNetwork.setMemoryTrimmableRegistry(c);
        return resizeAndRotateEnabledForNetwork.build();
    }

    public static void onTrimMemory(int i) {
        if (c != null) {
            c.a(i);
        }
    }
}
