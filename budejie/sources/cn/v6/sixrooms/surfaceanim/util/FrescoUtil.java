package cn.v6.sixrooms.surfaceanim.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Environment;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.File;
import java.util.HashSet;

public class FrescoUtil {
    public static void asyncGetBitmap(String str, BaseBitmapDataSubscriber baseBitmapDataSubscriber) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setProgressiveRenderingEnabled(true).build(), null).subscribe(baseBitmapDataSubscriber, CallerThreadExecutor.getInstance());
    }

    public static File getFileFromDiskCache(String str) {
        if (str == null) {
            return null;
        }
        CacheKey encodedCacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(str), null);
        if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(encodedCacheKey)) {
            return ((FileBinaryResource) ImagePipelineFactory.getInstance().getMainFileCache().getResource(encodedCacheKey)).getFile();
        }
        if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(encodedCacheKey)) {
            return ((FileBinaryResource) ImagePipelineFactory.getInstance().getSmallImageFileCache().getResource(encodedCacheKey)).getFile();
        }
        return null;
    }

    public static void initFresco(Context context) {
        File externalCacheDir;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            externalCacheDir = context.getExternalCacheDir();
        } else {
            externalCacheDir = context.getCacheDir();
        }
        DiskCacheConfig build = DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(externalCacheDir).setBaseDirectoryName("s_image_cache").build();
        DiskCacheConfig build2 = DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(externalCacheDir).setBaseDirectoryName("image_cache").setMaxCacheSize(83886080).setMaxCacheSizeOnLowDiskSpace(52428800).setMaxCacheSizeOnVeryLowDiskSpace(10485760).build();
        MemoryTrimmableRegistry instance = NoOpMemoryTrimmableRegistry.getInstance();
        instance.registerMemoryTrimmable(new FrescoUtil$a((byte) 0));
        Fresco.initialize(context, ImagePipelineConfig.newBuilder(context).setBitmapsConfig(Config.RGB_565).setMainDiskCacheConfig(build2).setSmallImageDiskCacheConfig(build).setBitmapsConfig(Config.RGB_565).setMemoryTrimmableRegistry(instance).setBitmapMemoryCacheParamsSupplier(new FrescoUtil$b((ActivityManager) context.getSystemService("activity"))).setDownsampleEnabled(true).setResizeAndRotateEnabledForNetwork(true).setRequestListeners(new HashSet()).build());
    }
}
