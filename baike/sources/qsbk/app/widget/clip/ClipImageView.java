package qsbk.app.widget.clip;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ClipImageView extends ImageView implements OnTouchListener, OnGlobalLayoutListener {
    public static final float DEFAULT_MAX_SCALE = 4.0f;
    public static final float DEFAULT_MID_SCALE = 2.0f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    private final Matrix a;
    private final Matrix b;
    private final Matrix c;
    private final RectF d;
    private final float[] e;
    private float f;
    private float g;
    private float h;
    private b i;
    private boolean j;
    private int k;
    private int l;

    private class a implements Runnable {
        final /* synthetic */ ClipImageView a;
        private final float b;
        private final float c;
        private final float d;
        private final float e;

        public a(ClipImageView clipImageView, float f, float f2, float f3, float f4) {
            this.a = clipImageView;
            this.d = f2;
            this.b = f3;
            this.c = f4;
            if (f < f2) {
                this.e = 1.07f;
            } else {
                this.e = 0.93f;
            }
        }

        public void run() {
            this.a.c.postScale(this.e, this.e, this.b, this.c);
            this.a.b();
            float scale = this.a.getScale();
            if ((this.e <= 1.0f || scale >= this.d) && (this.e >= 1.0f || this.d >= scale)) {
                scale = this.d / scale;
                this.a.c.postScale(scale, scale, this.b, this.c);
                this.a.b();
                return;
            }
            this.a.a(this.a, this);
        }
    }

    private class b extends SimpleOnGestureListener implements OnScaleGestureListener {
        final /* synthetic */ ClipImageView a;
        private final ScaleGestureDetector b;
        private final GestureDetector c;
        private final float d;
        private VelocityTracker e;
        private boolean f;
        private float g;
        private float h;
        private float i;

        public b(ClipImageView clipImageView, Context context) {
            this.a = clipImageView;
            this.b = new ScaleGestureDetector(context, this);
            this.c = new GestureDetector(context, this);
            this.c.setOnDoubleTapListener(this);
            this.d = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scale = this.a.getScale();
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (this.a.getDrawable() != null && ((scale < this.a.h && scaleFactor > 1.0f) || (scale > this.a.f && scaleFactor < 1.0f))) {
                if (scaleFactor * scale < this.a.f) {
                    scaleFactor = this.a.f / scale;
                }
                if (scaleFactor * scale > this.a.h) {
                    scaleFactor = this.a.h / scale;
                }
                this.a.c.postScale(scaleFactor, scaleFactor, (float) (this.a.getWidth() / 2), (float) (this.a.getHeight() / 2));
                this.a.b();
            }
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z = false;
            if (!this.c.onTouchEvent(motionEvent)) {
                this.b.onTouchEvent(motionEvent);
                int pointerCount = motionEvent.getPointerCount();
                float f = 0.0f;
                float f2 = 0.0f;
                for (int i = 0; i < pointerCount; i++) {
                    f2 += motionEvent.getX(i);
                    f += motionEvent.getY(i);
                }
                float f3 = f2 / ((float) pointerCount);
                f /= (float) pointerCount;
                if (((float) pointerCount) != this.i) {
                    this.f = false;
                    if (this.e != null) {
                        this.e.clear();
                    }
                    this.g = f3;
                    this.h = f;
                }
                this.i = (float) pointerCount;
                switch (motionEvent.getAction()) {
                    case 0:
                        if (this.e == null) {
                            this.e = VelocityTracker.obtain();
                        } else {
                            this.e.clear();
                        }
                        this.e.addMovement(motionEvent);
                        this.g = f3;
                        this.h = f;
                        this.f = false;
                        break;
                    case 1:
                    case 3:
                        this.i = 0.0f;
                        if (this.e != null) {
                            this.e.recycle();
                            this.e = null;
                            break;
                        }
                        break;
                    case 2:
                        float f4 = f3 - this.g;
                        f2 = f - this.h;
                        if (!this.f) {
                            if (Math.sqrt((double) ((f4 * f4) + (f2 * f2))) >= ((double) this.d)) {
                                z = true;
                            }
                            this.f = z;
                        }
                        if (this.f) {
                            if (this.a.getDrawable() != null) {
                                this.a.c.postTranslate(f4, f2);
                                this.a.b();
                            }
                            this.g = f3;
                            this.h = f;
                            if (this.e != null) {
                                this.e.addMovement(motionEvent);
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            try {
                float scale = this.a.getScale();
                float width = (float) (this.a.getWidth() / 2);
                float height = (float) (this.a.getHeight() / 2);
                if (scale < this.a.g) {
                    this.a.post(new a(this.a, scale, this.a.g, width, height));
                } else if (scale < this.a.g || scale >= this.a.h) {
                    this.a.post(new a(this.a, scale, this.a.f, width, height));
                } else {
                    this.a.post(new a(this.a, scale, this.a.h, width, height));
                }
            } catch (Exception e) {
            }
            return true;
        }
    }

    public ClipImageView(Context context) {
        this(context, null);
    }

    public ClipImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClipImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Matrix();
        this.b = new Matrix();
        this.c = new Matrix();
        this.d = new RectF();
        this.e = new float[9];
        this.f = 1.0f;
        this.g = 2.0f;
        this.h = 4.0f;
        super.setScaleType(ScaleType.MATRIX);
        setOnTouchListener(this);
        this.i = new b(this, context);
    }

    private void a() {
        super.setScaleType(ScaleType.MATRIX);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            int width = getWidth();
            int height = getHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.k = height;
            this.l = (this.k / 8) * 5;
            if (this.l > width) {
                this.l = width;
            }
            float f = 1.0f;
            if (intrinsicWidth <= this.l) {
                f = ((float) this.l) / ((float) intrinsicWidth);
            }
            if (intrinsicHeight < this.k && ((float) this.k) / ((float) intrinsicHeight) > r0) {
                f = ((float) this.k) / ((float) intrinsicHeight);
            }
            this.a.reset();
            this.a.postScale(f, f);
            this.a.postTranslate((((float) width) - (((float) intrinsicWidth) * f)) / 2.0f, (((float) height) - (f * ((float) intrinsicHeight))) / 2.0f);
            d();
            this.j = true;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.i.onTouchEvent(motionEvent);
    }

    @TargetApi(16)
    private void a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            view.postOnAnimation(runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    public final float getScale() {
        this.c.getValues(this.e);
        return this.e[0];
    }

    public void onGlobalLayout() {
        if (!this.j) {
            a();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    private void b() {
        c();
        setImageMatrix(getDisplayMatrix());
    }

    private void c() {
        float f = 0.0f;
        RectF a = a(getDisplayMatrix());
        if (a != null) {
            float f2;
            float width = (float) getWidth();
            float height = (float) getHeight();
            if (a.top > (height - ((float) this.k)) / 2.0f) {
                f2 = ((height - ((float) this.k)) / 2.0f) - a.top;
            } else {
                f2 = 0.0f;
            }
            if (a.bottom < (((float) this.k) + height) / 2.0f) {
                f2 = ((((float) this.k) + height) / 2.0f) - a.bottom;
            }
            if (a.left > (width - ((float) this.l)) / 2.0f) {
                f = ((width - ((float) this.l)) / 2.0f) - a.left;
            }
            if (a.right < (((float) this.l) + width) / 2.0f) {
                f = ((((float) this.l) + width) / 2.0f) - a.right;
            }
            this.c.postTranslate(f, f2);
        }
    }

    private RectF a(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        this.d.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.d);
        return this.d;
    }

    private void d() {
        if (this.c != null) {
            this.c.reset();
            setImageMatrix(getDisplayMatrix());
        }
    }

    protected Matrix getDisplayMatrix() {
        this.b.set(this.a);
        this.b.postConcat(this.c);
        return this.b;
    }

    public Bitmap clip() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        draw(new Canvas(createBitmap));
        int width = getWidth();
        this.k = getHeight();
        this.l = (this.k / 8) * 5;
        if (this.l > width) {
            this.l = width;
        }
        return Bitmap.createBitmap(createBitmap, (getWidth() - this.l) / 2, (getHeight() - this.k) / 2, this.l, this.k);
    }
}
