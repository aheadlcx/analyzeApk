package cn.v6.sixrooms.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.utils.SmilySimplePack;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint({"HandlerLeak"})
public class SmilyEncUtils {
    private static SmilyEncUtils b;
    private static final String e = SmilyEncUtils.class.getSimpleName();
    SmilySimplePack a;
    private HashMap<String, SoftReference<Bitmap>> c = new HashMap();
    private ExecutorService d = Executors.newFixedThreadPool(20);
    private Handler f = new x(this);

    public interface ImageLoadingListener {
        void onLoadingComplete(String str, Bitmap bitmap, ImageView imageView);
    }

    private SmilyEncUtils() {
        try {
            this.a = new SmilySimplePack(V6Coop.getInstance().getContext().getAssets());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, SoftReference<Bitmap>> getImageCache() {
        return this.c;
    }

    public static synchronized SmilyEncUtils getInstance() {
        SmilyEncUtils smilyEncUtils;
        synchronized (SmilyEncUtils.class) {
            if (b == null) {
                b = new SmilyEncUtils();
            }
            smilyEncUtils = b;
        }
        return smilyEncUtils;
    }

    public Bitmap getSmilyByFileName(String str) {
        if (this.c.containsKey(str)) {
            Bitmap bitmap = (Bitmap) ((SoftReference) this.c.get(str)).get();
            if (bitmap != null) {
                return bitmap;
            }
        }
        return loadBitmap(str, 1.0f);
    }

    public Bitmap getSmilyByFileName(String str, float f) {
        return loadBitmap(str, f);
    }

    public Bitmap getSmilyByFileName(String str, float f, boolean z) {
        if (!z) {
            return getSmilyByFileName(str, f);
        }
        return loadBitmap(str, f, !z);
    }

    public void getAsyncSmilyByFileName(String str, ImageView imageView, ImageLoadingListener imageLoadingListener) {
        this.d.submit(new y(this, str, imageLoadingListener, imageView));
    }

    public void getAsyncSmilyByFileName(String str, ImageView imageView, ImageLoadingListener imageLoadingListener, float f) {
        this.d.submit(new aa(this, str, f, imageLoadingListener, imageView));
    }

    public Bitmap loadBitmap(String str, float f) {
        return loadBitmap(str, f, true);
    }

    public Bitmap loadBitmap(String str, float f, boolean z) {
        byte[] ReadFile = this.a.ReadFile(str);
        Bitmap bitmap = null;
        if (ReadFile != null) {
            bitmap = BitmapFactory.decodeByteArray(ReadFile, 0, ReadFile.length);
        }
        if (f != 1.0f) {
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
        }
        if (bitmap != null && z) {
            this.c.put(str, new SoftReference(bitmap));
        }
        return bitmap;
    }
}
