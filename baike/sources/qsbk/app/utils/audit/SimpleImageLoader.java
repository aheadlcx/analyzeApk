package qsbk.app.utils.audit;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.util.LinkedList;
import java.util.List;
import qsbk.app.utils.DebugUtil;

public class SimpleImageLoader {
    private static final String a = SimpleImageLoader.class.getName();
    private static SimpleImageLoader b = null;
    private static boolean c = DebugUtil.DEBUG;
    private final RequestManager d = new RequestManager();
    private final LruCache<String, byte[]> e;
    private final List<String> f;
    private Handler g = new Handler(Looper.getMainLooper());
    private int h = 0;

    public interface onCallback {
        void onFailed(View view, String str, Throwable th);

        void onSuccess(View view, String str);
    }

    private SimpleImageLoader() {
        this.d.start();
        this.e = new b(this, 349525);
        this.f = new LinkedList();
    }

    public static synchronized SimpleImageLoader getInstance() {
        SimpleImageLoader simpleImageLoader;
        synchronized (SimpleImageLoader.class) {
            if (b == null) {
                b = new SimpleImageLoader();
            }
            simpleImageLoader = b;
        }
        return simpleImageLoader;
    }

    public void cancelAll() {
        this.d.cancelAll(new c(this));
    }

    public Request loadImage(ImageView imageView, ProgressBar progressBar, String str, Drawable drawable, Drawable drawable2, int i, int i2, onCallback oncallback, int[] iArr) {
        a(imageView, progressBar, drawable, 100);
        Request request = new Request(str, new d(this, imageView, progressBar, i, i2, drawable2, iArr, oncallback, str, drawable));
        this.d.add(request);
        return request;
    }

    private void a(ImageView imageView, ProgressBar progressBar, byte[] bArr, int i, int i2, Drawable drawable, int[] iArr) {
        this.g.postDelayed(new e(this, bArr, i, i2, imageView, iArr, drawable, progressBar), (long) this.h);
    }

    private void a(ImageView imageView, ProgressBar progressBar, Drawable drawable) {
        this.g.postDelayed(new f(this, progressBar, imageView, drawable), (long) this.h);
    }

    private void a(ImageView imageView, ProgressBar progressBar, Drawable drawable, int i) {
        this.g.postDelayed(new g(this, progressBar, i, imageView, drawable), (long) this.h);
    }

    private void a(ProgressBar progressBar, int i, int i2) {
        this.g.postDelayed(new h(this, progressBar, i2, (int) ((((double) i) * 0.07d) + 1.0d)), (long) this.h);
    }

    public void cancel(String str) {
        this.d.cancelAll(new i(this, str));
    }

    public void stop() {
        this.d.stop();
    }
}
