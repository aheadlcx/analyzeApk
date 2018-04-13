package qsbk.app.widget.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ImageDecodeOptionsBuilder;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.ImageUtils;
import qsbk.app.utils.Util;

public class FrescoTouchImageView extends TouchImageView {
    static ResizeOptions sResizeOptions = new ResizeOptions(Util.displaySize.x, Util.displaySize.y);
    DataSource<CloseableReference<CloseableImage>> bigDataSource;
    private String bigUrl;
    DataSource<CloseableReference<CloseableImage>> lowDataSource;
    private CloseableReference<CloseableImage> mBigRef;
    private Listener mListener;
    private int mLoadFrom;
    private CloseableReference<CloseableImage> mRef;
    private String smallUrl;

    public interface Listener {
        public static final int LOAD_FROM_NETWORK = 0;
        public static final int LOAD_FROM_OTHER = 1;

        void fail(String str);

        void success(String str, int i);
    }

    public FrescoTouchImageView(Context context) {
        super(context);
    }

    public FrescoTouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FrescoTouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    protected void onDraw(Canvas canvas) {
        if ((this.mRef != null && this.mRef.isValid()) || (this.mBigRef != null && this.mBigRef.isValid())) {
            super.onDraw(canvas);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        int i = 0;
        Drawable drawable2 = getDrawable();
        if (drawable == null || drawable2 == null || (drawable2 instanceof TransitionDrawable) || this.mLoadFrom != 0) {
            super.setImageDrawable(drawable);
            return;
        }
        Drawable transitionDrawable = new TransitionDrawable(new Drawable[]{drawable2, drawable});
        super.setImageDrawable(transitionDrawable);
        int numberOfLayers = transitionDrawable.getNumberOfLayers();
        while (i < numberOfLayers) {
            if (transitionDrawable.getId(i) <= 0) {
                transitionDrawable.setId(i, i + 1);
            }
            i++;
        }
        transitionDrawable.startTransition(300);
    }

    private void innerSetBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            try {
                Bitmap scaleBitmapIfNecessary = ImageUtils.scaleBitmapIfNecessary(bitmap, Util.displaySize.x, Util.displaySize.y, false);
                if (scaleBitmapIfNecessary == bitmap) {
                    scaleBitmapIfNecessary = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), new Matrix(), true);
                }
                setImageBitmap(scaleBitmapIfNecessary);
            } catch (Throwable th) {
                System.gc();
                if (!bitmap.isRecycled()) {
                    setImageBitmap(bitmap);
                }
            }
        }
    }

    public void loadImage(Uri uri, Uri uri2) {
        boolean z = false;
        if (uri != null || uri2 != null) {
            closeRef();
            boolean z2 = uri == null ? false : FrescoImageloader.isInMemoryCache(uri) || FrescoImageloader.getDiskCacheFile(uri) != null;
            if (uri2 != null && (FrescoImageloader.isInMemoryCache(uri2) || FrescoImageloader.getDiskCacheFile(uri2) != null)) {
                z = true;
            }
            if (uri != null) {
                this.lowDataSource = Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).setImageDecodeOptions(new ImageDecodeOptionsBuilder().setForceStaticImage(true).build()).build(), null);
                this.lowDataSource.subscribe(new a(this, z2), UiThreadImmediateExecutorService.getInstance());
            }
            if (uri2 != null) {
                this.bigDataSource = Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri2).setResizeOptions(sResizeOptions).setImageDecodeOptions(new ImageDecodeOptionsBuilder().setForceStaticImage(true).build()).build(), null);
                this.bigDataSource.subscribe(new b(this, z), UiThreadImmediateExecutorService.getInstance());
            }
        }
    }

    public void loadImage(String str, String str2) {
        if (!TextUtils.equals(this.smallUrl, str) || !TextUtils.equals(this.bigUrl, str2)) {
            this.smallUrl = str;
            this.bigUrl = str2;
            loadImage(FrescoImageloader.get(str), FrescoImageloader.get(str2));
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        closeRef();
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
        }
        super.setImageDrawable(null);
    }

    private void closeRef() {
        if (this.lowDataSource != null) {
            this.lowDataSource.close();
        }
        if (this.bigDataSource != null) {
            this.bigDataSource.close();
        }
        CloseableReference.closeSafely(this.mRef);
        this.mRef = null;
        CloseableReference.closeSafely(this.mBigRef);
        this.mBigRef = null;
    }
}
