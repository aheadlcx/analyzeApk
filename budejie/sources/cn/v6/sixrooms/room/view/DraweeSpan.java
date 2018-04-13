package cn.v6.sixrooms.room.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DeferredReleaser.Releasable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class DraweeSpan extends DynamicDrawableSpan implements Releasable {
    private final DeferredReleaser a;
    private final ForwardingDrawable b;
    private CloseableReference<CloseableImage> c;
    private DataSource<CloseableReference<CloseableImage>> d;
    private boolean e;
    private Drawable f;
    private Drawable g;
    private DraweeTextView h;
    private String i;
    private Point j;
    private Rect k;
    private boolean l;
    private boolean m;

    public static class Builder {
        String a;
        int b;
        int c;
        int d;
        Drawable e;
        boolean f;
        Rect g;

        public Builder(String str) {
            this(str, false);
        }

        public Builder(String str, boolean z) {
            this.b = 100;
            this.c = 100;
            this.d = 0;
            this.g = new Rect();
            this.a = str;
            if (str == null) {
                throw new NullPointerException("Attempt to create a DraweeSpan with null uri string!");
            } else if (z) {
                this.d = 1;
            }
        }

        public Builder setLayout(int i, int i2) {
            this.b = i;
            this.c = i2;
            return this;
        }

        public Builder setMargin(int i) {
            this.g.set(i, i, i, 0);
            return this;
        }

        public Builder setMargin(int i, int i2, int i3) {
            this.g.set(i, i2, i3, 0);
            return this;
        }

        public Builder setPlaceHolderImage(Drawable drawable) {
            this.e = drawable;
            return this;
        }

        public Builder setShowAnimaImmediately(boolean z) {
            this.f = z;
            return this;
        }

        public DraweeSpan build() {
            if (this.e == null) {
                this.e = new ColorDrawable(0);
                this.e.setBounds(0, 0, this.b, this.c);
            }
            DraweeSpan draweeSpan = new DraweeSpan(this.a, this.d, this.e, this.f);
            draweeSpan.j.set(this.b, this.c);
            draweeSpan.k.set(this.g.left, this.g.top, this.g.right, 0);
            draweeSpan.layout();
            return draweeSpan;
        }
    }

    static /* synthetic */ void a(DraweeSpan draweeSpan, String str, DataSource dataSource, CloseableReference closeableReference, boolean z) {
        if (draweeSpan.getId().equals(str) && dataSource == draweeSpan.d && draweeSpan.e) {
            try {
                Drawable drawable;
                CloseableImage closeableImage = (CloseableImage) closeableReference.get();
                if (closeableImage instanceof CloseableStaticBitmap) {
                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
                    Drawable createBitmapDrawable = draweeSpan.createBitmapDrawable(closeableStaticBitmap.getUnderlyingBitmap());
                    if (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) {
                        drawable = createBitmapDrawable;
                    } else {
                        drawable = new OrientedDrawable(createBitmapDrawable, closeableStaticBitmap.getRotationAngle());
                    }
                } else {
                    if (closeableImage instanceof CloseableAnimatedImage) {
                        CloseableReference decodedFrame;
                        if (draweeSpan.m) {
                            DrawableFactory animatedDrawableFactory = ImagePipelineFactory.getInstance().getAnimatedDrawableFactory(draweeSpan.h.getContext());
                            if (animatedDrawableFactory != null) {
                                drawable = animatedDrawableFactory.createDrawable(closeableImage);
                            }
                        }
                        AnimatedImageResult imageResult = ((CloseableAnimatedImage) closeableImage).getImageResult();
                        int frameForPreview = imageResult.getFrameForPreview();
                        if (frameForPreview >= 0) {
                            decodedFrame = imageResult.getDecodedFrame(frameForPreview);
                        } else {
                            decodedFrame = null;
                        }
                        if (decodedFrame == null) {
                            decodedFrame = imageResult.getPreviewBitmap();
                        }
                        if (!(decodedFrame == null || decodedFrame.get() == null)) {
                            drawable = draweeSpan.createBitmapDrawable((Bitmap) decodedFrame.get());
                        }
                    }
                    throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
                }
                CloseableReference closeableReference2 = draweeSpan.c;
                Drawable drawable2 = draweeSpan.f;
                draweeSpan.c = closeableReference;
                if (z) {
                    try {
                        draweeSpan.d = null;
                        draweeSpan.setImage(drawable);
                    } catch (Throwable th) {
                        if (!(drawable2 == null || drawable2 == drawable)) {
                            b(drawable2);
                        }
                        if (!(closeableReference2 == null || closeableReference2 == closeableReference)) {
                            CloseableReference.closeSafely(closeableReference2);
                        }
                    }
                }
                if (!(drawable2 == null || drawable2 == drawable)) {
                    b(drawable2);
                }
                if (closeableReference2 != null && closeableReference2 != closeableReference) {
                    CloseableReference.closeSafely(closeableReference2);
                    return;
                }
                return;
            } catch (Throwable e) {
                CloseableReference.closeSafely(closeableReference);
                draweeSpan.a(str, dataSource, e, z);
                return;
            }
        }
        CloseableReference.closeSafely(closeableReference);
        dataSource.close();
    }

    private DraweeSpan(String str, int i, Drawable drawable, boolean z) {
        super(i);
        this.j = new Point();
        this.k = new Rect();
        this.m = false;
        this.i = str;
        this.m = z;
        this.a = DeferredReleaser.getInstance();
        this.g = drawable;
        this.b = new ForwardingDrawable(this.g);
    }

    protected void layout() {
        this.b.setBounds(0, 0, this.j.x, this.j.y);
    }

    public Drawable getDrawable() {
        return this.b;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        if (fontMetricsInt != null) {
            FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i3 = fontMetricsInt2.bottom - fontMetricsInt2.top;
            int i4 = bounds.bottom - bounds.top;
            int i5 = (i4 / 2) - (i3 / 4);
            i3 = (i3 / 4) + (i4 / 2);
            fontMetricsInt.ascent = -i3;
            fontMetricsInt.top = -i3;
            fontMetricsInt.bottom = i5;
            fontMetricsInt.descent = i5;
        }
        return bounds.right;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(f, (float) ((((i5 - i3) - drawable.getBounds().bottom) / 2) + i3));
        drawable.draw(canvas);
        canvas.restore();
    }

    public void setImage(Drawable drawable) {
        if (this.f != drawable) {
            b(this.f);
            a(drawable);
            if (drawable instanceof AnimatedDrawable2) {
                ((AnimatedDrawable2) drawable).start();
            }
            this.f = drawable;
        }
    }

    private void a(Drawable drawable) {
        if (drawable != null) {
            this.b.setDrawable(drawable);
        }
    }

    public void reset() {
        a(this.g);
    }

    public void onAttach(@NonNull DraweeTextView draweeTextView) {
        this.l = true;
        if (this.h != draweeTextView) {
            this.b.setCallback(null);
            if (this.h != null) {
                throw new IllegalStateException("has been attached to view:" + this.h);
            }
            this.h = draweeTextView;
            a(this.f);
            this.b.setCallback(this.h);
        }
        this.a.cancelDeferredRelease(this);
        if (this.e) {
            if (this.m && (this.f instanceof AnimatedDrawable2)) {
                ((AnimatedDrawable2) this.f).start();
            }
        } else if (!TextUtils.isEmpty(getImageUri())) {
            this.e = true;
            String id = getId();
            this.d = fetchDecodedImage();
            this.d.subscribe(new b(this, id), UiThreadImmediateExecutorService.getInstance());
        }
    }

    @VisibleForTesting
    protected DataSource<CloseableReference<CloseableImage>> fetchDecodedImage() {
        ImagePipelineFactory instance;
        try {
            instance = ImagePipelineFactory.getInstance();
        } catch (NullPointerException e) {
            ImagePipelineFactory.initialize(this.h.getContext().getApplicationContext());
            instance = ImagePipelineFactory.getInstance();
        }
        return instance.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(getImageUri())).setImageDecodeOptions(ImageDecodeOptions.newBuilder().setDecodePreviewFrame(true).build()).build(), null);
    }

    @NonNull
    public String getImageUri() {
        return this.i;
    }

    protected String getId() {
        return String.valueOf(getImageUri().hashCode());
    }

    private void a(String str, DataSource<CloseableReference<CloseableImage>> dataSource, Throwable th, boolean z) {
        if (FLog.isLoggable(5)) {
            FLog.w(DraweeSpan.class, str + " load failure", th);
        }
        if (!getId().equals(str) || dataSource != this.d || !this.e) {
            dataSource.close();
        } else if (z) {
            this.d = null;
            a(this.f);
        }
    }

    protected BitmapDrawable createBitmapDrawable(Bitmap bitmap) {
        if (this.h != null) {
            return new BitmapDrawable(this.h.getContext().getResources(), bitmap);
        }
        return new BitmapDrawable(null, bitmap);
    }

    public void onDetach() {
        if (this.l) {
            if (this.m && (this.f instanceof AnimatedDrawable2)) {
                ((AnimatedDrawable2) this.f).stop();
            }
            this.b.setCallback(null);
            this.h = null;
            reset();
            this.a.scheduleDeferredRelease(this);
        }
    }

    public void release() {
        this.e = false;
        this.l = false;
        this.h = null;
        if (this.d != null) {
            this.d.close();
            this.d = null;
        }
        if (this.f != null) {
            b(this.f);
        }
        this.f = null;
        if (this.c != null) {
            CloseableReference.closeSafely(this.c);
            this.c = null;
        }
    }

    private static void b(@Nullable Drawable drawable) {
        if ((drawable instanceof AnimatedDrawable2) && ((AnimatedDrawable2) drawable).isRunning()) {
            ((AnimatedDrawable2) drawable).stop();
        }
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches) drawable).dropCaches();
        }
    }
}
