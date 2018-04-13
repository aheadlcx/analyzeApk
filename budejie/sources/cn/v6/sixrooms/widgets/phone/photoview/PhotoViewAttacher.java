package cn.v6.sixrooms.widgets.phone.photoview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import cn.v6.sixrooms.widgets.phone.photoview.VersionedGestureDetector.OnGestureListener;
import java.lang.ref.WeakReference;

public class PhotoViewAttacher implements OnDoubleTapListener, OnTouchListener, OnGlobalLayoutListener, IPhotoView, OnGestureListener {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    static final boolean a = Log.isLoggable("PhotoViewAttacher", 3);
    private float b = 1.0f;
    private float c = 1.75f;
    private float d = 3.0f;
    private boolean e = true;
    private WeakReference<ImageView> f;
    private ViewTreeObserver g;
    private GestureDetector h;
    private VersionedGestureDetector i;
    private final Matrix j = new Matrix();
    private final Matrix k = new Matrix();
    private final Matrix l = new Matrix();
    private final RectF m = new RectF();
    private final float[] n = new float[9];
    private OnMatrixChangedListener o;
    private OnPhotoTapListener p;
    private OnViewTapListener q;
    private OnLongClickListener r;
    private int s;
    private int t;
    private int u;
    private int v;
    private b w;
    private int x = 2;
    private boolean y;
    private ScaleType z = ScaleType.FIT_CENTER;

    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    public interface OnPhotoTapListener {
        void onPhotoTap(View view, float f, float f2);
    }

    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    private class a implements Runnable {
        final /* synthetic */ PhotoViewAttacher a;
        private final float b;
        private final float c;
        private final float d;
        private final float e;

        public a(PhotoViewAttacher photoViewAttacher, float f, float f2, float f3, float f4) {
            this.a = photoViewAttacher;
            this.d = f2;
            this.b = f3;
            this.c = f4;
            if (f < f2) {
                this.e = 1.07f;
            } else {
                this.e = 0.93f;
            }
        }

        public final void run() {
            View imageView = this.a.getImageView();
            if (imageView != null) {
                this.a.l.postScale(this.e, this.e, this.b, this.c);
                this.a.a();
                float scale = this.a.getScale();
                if ((this.e <= 1.0f || scale >= this.d) && (this.e >= 1.0f || this.d >= scale)) {
                    float f = this.d / scale;
                    this.a.l.postScale(f, f, this.b, this.c);
                    this.a.a();
                    return;
                }
                Compat.postOnAnimation(imageView, this);
            }
        }
    }

    private class b implements Runnable {
        final /* synthetic */ PhotoViewAttacher a;
        private final ScrollerProxy b;
        private int c;
        private int d;

        public b(PhotoViewAttacher photoViewAttacher, Context context) {
            this.a = photoViewAttacher;
            this.b = ScrollerProxy.getScroller(context);
        }

        public final void a() {
            if (PhotoViewAttacher.a) {
                Log.d("PhotoViewAttacher", "Cancel Fling");
            }
            this.b.forceFinished(true);
        }

        public final void a(int i, int i2, int i3, int i4) {
            RectF displayRect = this.a.getDisplayRect();
            if (displayRect != null) {
                int round;
                int i5;
                int round2;
                int i6;
                int round3 = Math.round(-displayRect.left);
                if (((float) i) < displayRect.width()) {
                    round = Math.round(displayRect.width() - ((float) i));
                    i5 = 0;
                } else {
                    i5 = round3;
                    round = round3;
                }
                int round4 = Math.round(-displayRect.top);
                if (((float) i2) < displayRect.height()) {
                    round2 = Math.round(displayRect.height() - ((float) i2));
                    i6 = 0;
                } else {
                    i6 = round4;
                    round2 = round4;
                }
                this.c = round3;
                this.d = round4;
                if (PhotoViewAttacher.a) {
                    Log.d("PhotoViewAttacher", "fling. StartX:" + round3 + " StartY:" + round4 + " MaxX:" + round + " MaxY:" + round2);
                }
                if (round3 != round || round4 != round2) {
                    this.b.fling(round3, round4, i3, i4, i5, round, i6, round2, 0, 0);
                }
            }
        }

        public final void run() {
            View imageView = this.a.getImageView();
            if (imageView != null && this.b.computeScrollOffset()) {
                int currX = this.b.getCurrX();
                int currY = this.b.getCurrY();
                if (PhotoViewAttacher.a) {
                    Log.d("PhotoViewAttacher", "fling run(). CurrentX:" + this.c + " CurrentY:" + this.d + " NewX:" + currX + " NewY:" + currY);
                }
                this.a.l.postTranslate((float) (this.c - currX), (float) (this.d - currY));
                this.a.b(this.a.getDisplayMatrix());
                this.c = currX;
                this.d = currY;
                Compat.postOnAnimation(imageView, this);
            }
        }
    }

    private static void a(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom should be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom should be less than MaxZoom");
        }
    }

    private static boolean a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static void b(ImageView imageView) {
        if (imageView != null && !(imageView instanceof PhotoView)) {
            imageView.setScaleType(ScaleType.MATRIX);
        }
    }

    public PhotoViewAttacher(ImageView imageView) {
        this.f = new WeakReference(imageView);
        imageView.setOnTouchListener(this);
        this.g = imageView.getViewTreeObserver();
        this.g.addOnGlobalLayoutListener(this);
        b(imageView);
        if (!imageView.isInEditMode()) {
            this.i = VersionedGestureDetector.newInstance(imageView.getContext(), this);
            this.h = new GestureDetector(imageView.getContext(), new a(this));
            this.h.setOnDoubleTapListener(this);
            setZoomable(true);
        }
    }

    public final boolean canZoom() {
        return this.y;
    }

    public final void cleanup() {
        if (!(this.f == null || this.f.get() == null)) {
            ((ImageView) this.f.get()).getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        this.g = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.f = null;
    }

    public final RectF getDisplayRect() {
        b();
        return a(getDisplayMatrix());
    }

    public final ImageView getImageView() {
        ImageView imageView = null;
        if (this.f != null) {
            imageView = (ImageView) this.f.get();
        }
        if (imageView == null) {
            cleanup();
        }
        return imageView;
    }

    public float getMinScale() {
        return this.b;
    }

    public float getMidScale() {
        return this.c;
    }

    public float getMaxScale() {
        return this.d;
    }

    public final float getScale() {
        this.l.getValues(this.n);
        return this.n[0];
    }

    public final ScaleType getScaleType() {
        return this.z;
    }

    public final boolean onDoubleTap(MotionEvent motionEvent) {
        try {
            float scale = getScale();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (scale < this.c) {
                zoomTo(this.c, x, y);
            } else if (scale < this.c || scale >= this.d) {
                zoomTo(this.b, x, y);
            } else {
                zoomTo(this.d, x, y);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return true;
    }

    public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public final void onDrag(float f, float f2) {
        if (a) {
            Log.d("PhotoViewAttacher", String.format("onDrag: dx: %.2f. dy: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
        }
        ImageView imageView = getImageView();
        if (imageView != null && a(imageView)) {
            this.l.postTranslate(f, f2);
            a();
            if (this.e && !this.i.isScaling()) {
                if (this.x == 2 || ((this.x == 0 && f >= 1.0f) || (this.x == 1 && f <= -1.0f))) {
                    imageView.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
    }

    public final void onFling(float f, float f2, float f3, float f4) {
        if (a) {
            Log.d("PhotoViewAttacher", "onFling. sX: " + f + " sY: " + f2 + " Vx: " + f3 + " Vy: " + f4);
        }
        ImageView imageView = getImageView();
        if (a(imageView)) {
            this.w = new b(this, imageView.getContext());
            this.w.a(imageView.getWidth(), imageView.getHeight(), (int) f3, (int) f4);
            imageView.post(this.w);
        }
    }

    public final void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView != null && this.y) {
            int top = imageView.getTop();
            int right = imageView.getRight();
            int bottom = imageView.getBottom();
            int left = imageView.getLeft();
            if (top != this.s || bottom != this.u || left != this.v || right != this.t) {
                a(imageView.getDrawable());
                this.s = top;
                this.t = right;
                this.u = bottom;
                this.v = left;
            }
        }
    }

    public final void onScale(float f, float f2, float f3) {
        if (a) {
            Log.d("PhotoViewAttacher", String.format("onScale: scale: %.2f. fX: %.2f. fY: %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3)}));
        }
        if (!a(getImageView())) {
            return;
        }
        if (getScale() < this.d || f < 1.0f) {
            this.l.postScale(f, f, f2, f3);
            a();
        }
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        View imageView = getImageView();
        if (imageView != null) {
            if (this.p != null) {
                RectF displayRect = getDisplayRect();
                if (displayRect != null) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    if (displayRect.contains(x, y)) {
                        this.p.onPhotoTap(imageView, (x - displayRect.left) / displayRect.width(), (y - displayRect.top) / displayRect.height());
                        return true;
                    }
                }
            }
            if (this.q != null) {
                this.q.onViewTap(imageView, motionEvent.getX(), motionEvent.getY());
            }
        }
        return false;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = false;
        if (!this.y) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                view.getParent().requestDisallowInterceptTouchEvent(true);
                if (this.w != null) {
                    this.w.a();
                    this.w = null;
                    break;
                }
                break;
            case 1:
            case 3:
                if (getScale() < this.b) {
                    RectF displayRect = getDisplayRect();
                    if (displayRect != null) {
                        view.post(new a(this, getScale(), this.b, displayRect.centerX(), displayRect.centerY()));
                        z = true;
                        break;
                    }
                }
                break;
        }
        if (this.h != null && this.h.onTouchEvent(motionEvent)) {
            z = true;
        }
        if (this.i == null || !this.i.onTouchEvent(motionEvent)) {
            return z;
        }
        return true;
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.e = z;
    }

    public void setMinScale(float f) {
        a(f, this.c, this.d);
        this.b = f;
    }

    public void setMidScale(float f) {
        a(this.b, f, this.d);
        this.c = f;
    }

    public void setMaxScale(float f) {
        a(this.b, this.c, f);
        this.d = f;
    }

    public final void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.r = onLongClickListener;
    }

    public final void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.o = onMatrixChangedListener;
    }

    public final void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.p = onPhotoTapListener;
    }

    public final void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.q = onViewTapListener;
    }

    public final void setZoomable(boolean z) {
        this.y = z;
        update();
    }

    public final void update() {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (this.y) {
            b(imageView);
            a(imageView.getDrawable());
            return;
        }
        c();
    }

    public final void zoomTo(float f, float f2, float f3) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            imageView.post(new a(this, getScale(), f, f2, f3));
        }
    }

    protected Matrix getDisplayMatrix() {
        this.k.set(this.j);
        this.k.postConcat(this.l);
        return this.k;
    }

    private void a() {
        b();
        b(getDisplayMatrix());
    }

    private void b() {
        float f = 0.0f;
        ImageView imageView = getImageView();
        if (imageView != null) {
            RectF a = a(getDisplayMatrix());
            if (a != null) {
                float height = a.height();
                float width = a.width();
                int height2 = imageView.getHeight();
                if (height <= ((float) height2)) {
                    switch (b.a[this.z.ordinal()]) {
                        case 2:
                            height = -a.top;
                            break;
                        case 3:
                            height = (((float) height2) - height) - a.top;
                            break;
                        default:
                            height = ((((float) height2) - height) / 2.0f) - a.top;
                            break;
                    }
                } else if (a.top > 0.0f) {
                    height = -a.top;
                } else if (a.bottom < ((float) height2)) {
                    height = ((float) height2) - a.bottom;
                } else {
                    height = 0.0f;
                }
                int width2 = imageView.getWidth();
                if (width <= ((float) width2)) {
                    switch (b.a[this.z.ordinal()]) {
                        case 2:
                            f = -a.left;
                            break;
                        case 3:
                            f = (((float) width2) - width) - a.left;
                            break;
                        default:
                            f = ((((float) width2) - width) / 2.0f) - a.left;
                            break;
                    }
                    this.x = 2;
                } else if (a.left > 0.0f) {
                    this.x = 0;
                    f = -a.left;
                } else if (a.right < ((float) width2)) {
                    f = ((float) width2) - a.right;
                    this.x = 1;
                } else {
                    this.x = -1;
                }
                this.l.postTranslate(f, height);
            }
        }
    }

    private RectF a(Matrix matrix) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                this.m.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
                matrix.mapRect(this.m);
                return this.m;
            }
        }
        return null;
    }

    private void c() {
        this.l.reset();
        b(getDisplayMatrix());
        b();
    }

    private void b(Matrix matrix) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            ImageView imageView2 = getImageView();
            if (imageView2 == null || (imageView2 instanceof PhotoView) || imageView2.getScaleType() == ScaleType.MATRIX) {
                imageView.setImageMatrix(matrix);
                if (this.o != null) {
                    RectF a = a(matrix);
                    if (a != null) {
                        this.o.onMatrixChanged(a);
                        return;
                    }
                    return;
                }
                return;
            }
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private void a(Drawable drawable) {
        ImageView imageView = getImageView();
        if (imageView != null && drawable != null) {
            float width = (float) imageView.getWidth();
            float height = (float) imageView.getHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.j.reset();
            float f = width / ((float) intrinsicWidth);
            float f2 = height / ((float) intrinsicHeight);
            if (this.z != ScaleType.CENTER) {
                if (this.z != ScaleType.CENTER_CROP) {
                    if (this.z != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
                        switch (b.a[this.z.ordinal()]) {
                            case 2:
                                this.j.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 3:
                                this.j.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 4:
                                this.j.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 5:
                                this.j.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                            default:
                                break;
                        }
                    }
                    f = Math.min(1.0f, Math.min(f, f2));
                    this.j.postScale(f, f);
                    this.j.postTranslate((width - (((float) intrinsicWidth) * f)) / 2.0f, (height - (((float) intrinsicHeight) * f)) / 2.0f);
                } else {
                    f = Math.max(f, f2);
                    this.j.postScale(f, f);
                    this.j.postTranslate((width - (((float) intrinsicWidth) * f)) / 2.0f, (height - (((float) intrinsicHeight) * f)) / 2.0f);
                }
            } else {
                this.j.postTranslate((width - ((float) intrinsicWidth)) / 2.0f, (height - ((float) intrinsicHeight)) / 2.0f);
            }
            c();
        }
    }

    public final void setScaleType(ScaleType scaleType) {
        if (scaleType != null) {
            switch (b.a[scaleType.ordinal()]) {
                case 1:
                    throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
                default:
                    Object obj = 1;
                    break;
            }
        }
        obj = null;
        if (obj != null && scaleType != this.z) {
            this.z = scaleType;
            update();
        }
    }
}
