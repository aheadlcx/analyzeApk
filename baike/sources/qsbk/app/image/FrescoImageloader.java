package qsbk.app.image;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.cache.disk.DynamicDefaultDiskStorage;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.QsbkApp;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ReflectionUtils;
import qsbk.app.utils.UIHelper;

public class FrescoImageloader {
    public static AbstractScaleType SCALE_CENTER_TOP = new FrescoImageloader$ScaleTypeCenterTop();
    static ImageDecodeOptions a = new ImageDecodeOptionsBuilder().setForceStaticImage(true).build();
    private static final Map<String, Uri> b = new a(101);
    public static ImagePipelineConfig sImagePipelineConfig;

    public static final void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null.");
        }
        Context applicationContext = context.getApplicationContext();
        sImagePipelineConfig = ImagePipelineConfigUtils.getDefaultImagePipelineConfig(applicationContext);
        Fresco.initialize(applicationContext, sImagePipelineConfig);
        if (DebugUtil.DEBUG) {
            FLog.setMinimumLoggingLevel(5);
        }
    }

    public static ExecutorSupplier getExecutorSupplier() {
        return sImagePipelineConfig.getExecutorSupplier();
    }

    private static void a(ImageView imageView) {
        if (!(imageView instanceof SimpleDraweeView)) {
            throw new IllegalArgumentException("ImageView must be kind of SimpleDraweeView");
        }
    }

    public static void displayAvatar(ImageView imageView, String str) {
        displayAvatar(imageView, str, UIHelper.getDefaultAvatar());
    }

    public static void displayAvatar(ImageView imageView, String str, int i) {
        displayAvatar(imageView, str, i, true, 0);
    }

    public static void displayAvatar(ImageView imageView, String str, boolean z) {
        displayAvatar(imageView, str, UIHelper.getDefaultAvatar(), z, 0);
    }

    public static void displayAvatar(ImageView imageView, String str, int i, boolean z, int i2) {
        Uri uri;
        GenericDraweeHierarchy build;
        UIHelper.imageViewFilter(imageView);
        if (i == 0) {
            i = UIHelper.getDefaultAvatar();
        }
        Uri uri2 = Uri.EMPTY;
        if (str != null) {
            try {
                uri2 = Uri.parse(str);
            } catch (Throwable th) {
                th.printStackTrace();
                Log.e("Imageloader", "Invalid uri");
                uri = uri2;
            }
        }
        uri = uri2;
        GenericDraweeHierarchy genericDraweeHierarchy = (GenericDraweeHierarchy) ((SimpleDraweeView) imageView).getHierarchy();
        if (genericDraweeHierarchy == null) {
            build = new GenericDraweeHierarchyBuilder(imageView.getResources()).build();
        } else {
            build = genericDraweeHierarchy;
        }
        build.setPlaceholderImage(i);
        build.setFailureImage(i);
        RoundingParams roundingParams;
        if (z) {
            roundingParams = build.getRoundingParams();
            if (roundingParams == null) {
                roundingParams = RoundingParams.asCircle();
            }
            roundingParams.setRoundAsCircle(true);
            build.setRoundingParams(roundingParams);
        } else if (i2 > 0) {
            roundingParams = build.getRoundingParams();
            if (roundingParams == null) {
                roundingParams = RoundingParams.fromCornersRadius((float) i2);
            }
            roundingParams.setCornersRadius((float) i2);
            roundingParams.setRoundAsCircle(false);
            build.setRoundingParams(roundingParams);
        }
        ((SimpleDraweeView) imageView).setHierarchy(build);
        ((SimpleDraweeView) imageView).setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setImageDecodeOptions(a).build())).setOldController(((SimpleDraweeView) imageView).getController())).build());
    }

    public static void displayImage(ImageView imageView, String str) {
        a(imageView);
        displayImage(imageView, str, 0);
    }

    public static void displayImage(ImageView imageView, String str, int i) {
        a(imageView);
        displayImage(imageView, str, i, 0);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable) {
        a(imageView);
        displayImage(imageView, str, drawable, null);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, boolean z) {
        a(imageView);
        displayImage(imageView, str, drawable, null, false, 0, null, z, 0, 0);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, boolean z, int i, int i2) {
        a(imageView);
        displayImage(imageView, str, drawable, null, false, 0, null, z, i, i2);
    }

    public static void displayImage(ImageView imageView, String str, int i, int i2) {
        displayImage(imageView, str, i, i2, false);
    }

    public static void displayImage(ImageView imageView, String str, int i, int i2, boolean z) {
        Drawable drawable;
        Drawable drawable2 = null;
        a(imageView);
        if (i != 0) {
            try {
                drawable = imageView.getResources().getDrawable(i);
            } catch (NotFoundException e) {
                drawable = drawable2;
            }
        } else {
            drawable = drawable2;
        }
        if (i2 != 0) {
            try {
                drawable2 = imageView.getResources().getDrawable(i2);
            } catch (NotFoundException e2) {
            }
        }
        if (drawable2 == null) {
            drawable2 = drawable;
        }
        displayImage(imageView, str, drawable, drawable2, z);
    }

    public static void displayImage(ImageView imageView, String str, int i, int i2, boolean z, int i3) {
        displayImage(imageView, str, imageView.getResources().getDrawable(i), imageView.getResources().getDrawable(i2), z, i3);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2) {
        a(imageView);
        displayImage(imageView, str, drawable, drawable2, null);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2, Postprocessor postprocessor) {
        a(imageView);
        displayImage(imageView, str, drawable, drawable2, false, 0, postprocessor, false, 0, 0);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2, boolean z) {
        a(imageView);
        displayImage(imageView, str, drawable, drawable2, z, 0);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2, int i) {
        a(imageView);
        displayImage(imageView, str, drawable, drawable2, false, i);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2, boolean z, int i) {
        a(imageView);
        displayImage(imageView, str, drawable, drawable2, z, i, null, false, 0, 0);
    }

    public static void displayImage(ImageView imageView, String str, Drawable drawable, Drawable drawable2, boolean z, int i, Postprocessor postprocessor, boolean z2, int i2, int i3) {
        GenericDraweeHierarchy genericDraweeHierarchy;
        UIHelper.imageViewFilter(imageView);
        GenericDraweeHierarchy genericDraweeHierarchy2 = (GenericDraweeHierarchy) ((SimpleDraweeView) imageView).getHierarchy();
        if (genericDraweeHierarchy2 == null) {
            ((SimpleDraweeView) imageView).setHierarchy(new GenericDraweeHierarchyBuilder(imageView.getResources()).build());
            genericDraweeHierarchy = (GenericDraweeHierarchy) ((SimpleDraweeView) imageView).getHierarchy();
        } else {
            genericDraweeHierarchy = genericDraweeHierarchy2;
        }
        if (drawable != null) {
            genericDraweeHierarchy.setPlaceholderImage(drawable);
        }
        if (drawable2 != null) {
            genericDraweeHierarchy.setFailureImage(drawable2);
        }
        RoundingParams roundingParams;
        if (z) {
            roundingParams = genericDraweeHierarchy.getRoundingParams();
            if (roundingParams == null) {
                roundingParams = RoundingParams.asCircle();
            }
            roundingParams.setRoundAsCircle(true);
            genericDraweeHierarchy.setRoundingParams(roundingParams);
        } else if (i > 0) {
            roundingParams = genericDraweeHierarchy.getRoundingParams();
            if (roundingParams == null) {
                roundingParams = RoundingParams.fromCornersRadius((float) i);
            } else {
                roundingParams.setRoundAsCircle(false);
                roundingParams.setCornersRadius((float) i);
            }
            genericDraweeHierarchy.setRoundingParams(roundingParams);
        }
        Uri uri = null;
        if (str != null) {
            try {
                uri = get(str);
            } catch (Throwable th) {
                th.printStackTrace();
                Log.e("Imageloader", "Invalid uri");
            }
        }
        if (uri == null) {
            uri = Uri.EMPTY;
        }
        ImageRequestBuilder newBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(uri);
        if (!(i2 == 0 || i3 == 0)) {
            newBuilderWithSource.setResizeOptions(new ResizeOptions(i2, i3));
        }
        if (!z2) {
            newBuilderWithSource.setImageDecodeOptions(a);
        }
        if (postprocessor != null) {
            Log.e("Imageloader", "Has postprocessor.");
            newBuilderWithSource.setPostprocessor(postprocessor);
        }
        PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = (PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(newBuilderWithSource.build())).setOldController(((SimpleDraweeView) imageView).getController());
        if (z2) {
            pipelineDraweeControllerBuilder.setAutoPlayAnimations(true);
        }
        ((SimpleDraweeView) imageView).setController((PipelineDraweeController) pipelineDraweeControllerBuilder.build());
    }

    public static Uri get(String str) {
        Uri uri = (Uri) b.get(str);
        if (uri != null) {
            return uri;
        }
        uri = parseUri(str);
        b.put(str, uri);
        return uri;
    }

    public static Uri parseUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return Uri.EMPTY;
        }
        if (!str.startsWith("file://")) {
            return Uri.parse(str);
        }
        Uri parse = Uri.parse(str);
        File file = new File(parse.getPath());
        if (!file.exists()) {
            file = new File(parse.getEncodedPath());
        }
        return UriUtil.getUriForFile(file);
    }

    public static File getDiskCacheFile(String str) {
        try {
            return getDiskCacheFile(Uri.parse(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static File getDiskCacheFile(Uri uri) {
        if (uri == null) {
            return null;
        }
        File file;
        BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(uri), QsbkApp.mContext));
        if (resource == null || !(resource instanceof FileBinaryResource)) {
            file = null;
        } else {
            file = ((FileBinaryResource) resource).getFile();
        }
        return file;
    }

    public static File getDiskCacheFileWithReflection(String str) {
        List resourceIds = CacheKeyUtil.getResourceIds(DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(get(str)), QsbkApp.mContext));
        FileCache mainFileCache = ImagePipelineFactory.getInstance().getMainFileCache();
        if (resourceIds == null || resourceIds.isEmpty()) {
            return null;
        }
        if (mainFileCache instanceof DiskStorageCache) {
            Object obj = ReflectionUtils.get(mainFileCache, "mStorage");
            if (obj instanceof DynamicDefaultDiskStorage) {
                obj = ReflectionUtils.get(obj, "mCurrentState");
                if (obj != null) {
                    obj = ReflectionUtils.get(obj, "delegate");
                }
            }
            if (obj instanceof DefaultDiskStorage) {
                return (File) ReflectionUtils.invokeMethod(obj, ReflectionUtils.getMethod(obj, "getContentFileFor", new Class[]{String.class}), new Object[]{resourceIds.get(0)});
            }
        }
        return null;
    }

    public static boolean isInMemoryCache(String str) {
        try {
            return Fresco.getImagePipeline().isInBitmapMemoryCache(get(str));
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean isInMemoryCache(Uri uri) {
        try {
            return Fresco.getImagePipeline().isInBitmapMemoryCache(uri);
        } catch (Throwable th) {
            return false;
        }
    }

    public static void evictFromMemoryCache(List<String> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Fresco.getImagePipeline().evictFromMemoryCache(get((String) list.get(i)));
            }
        }
    }

    public static void evictFromMemoryCache2(List<Uri> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Fresco.getImagePipeline().evictFromMemoryCache((Uri) list.get(i));
            }
        }
    }

    public static String getFrescoResUrl(int i) {
        return "res://" + QsbkApp.getInstance().getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + i;
    }

    public static void onTrimMemory(int i) {
        ImagePipelineConfigUtils.onTrimMemory(i);
    }

    public static void clearAllMemoryCaches() {
        Fresco.getImagePipeline().clearMemoryCaches();
    }
}
