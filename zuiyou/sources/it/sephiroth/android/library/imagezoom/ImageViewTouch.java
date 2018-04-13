package it.sephiroth.android.library.imagezoom;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewConfiguration;

public class ImageViewTouch extends ImageViewTouchBase {
    protected ScaleGestureDetector a;
    protected GestureDetector b;
    protected int c;
    protected float d;
    protected int e;
    protected OnGestureListener f;
    protected OnScaleGestureListener g;
    protected boolean h;
    protected boolean i;
    protected boolean j;
    private b y;
    private c z;

    public class a extends SimpleOnGestureListener {
        final /* synthetic */ ImageViewTouch a;

        public a(ImageViewTouch imageViewTouch) {
            this.a = imageViewTouch;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.a.z != null) {
                this.a.z.a();
            }
            return this.a.a(motionEvent);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            Log.i("ImageViewTouchBase", "onDoubleTap. double tap enabled? " + this.a.h);
            if (this.a.h) {
                this.a.q = true;
                this.a.a(Math.min(this.a.getMaxScale(), Math.max(this.a.a(this.a.getScale(), this.a.getMaxScale()), this.a.getMinScale())), motionEvent.getX(), motionEvent.getY(), 200.0f);
                this.a.invalidate();
            }
            if (this.a.y != null) {
                this.a.y.a();
            }
            return super.onDoubleTap(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.a.isLongClickable() && !this.a.a.isInProgress()) {
                this.a.setPressed(true);
                this.a.performLongClick();
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.j && motionEvent != null && motionEvent2 != null && motionEvent.getPointerCount() <= 1 && motionEvent2.getPointerCount() <= 1 && !this.a.a.isInProgress()) {
                return this.a.a(motionEvent, motionEvent2, f, f2);
            }
            return false;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.j && motionEvent.getPointerCount() <= 1 && motionEvent2.getPointerCount() <= 1 && !this.a.a.isInProgress() && this.a.getScale() != 1.0f) {
                return this.a.b(motionEvent, motionEvent2, f, f2);
            }
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return this.a.d(motionEvent);
        }

        public boolean onDown(MotionEvent motionEvent) {
            return this.a.b(motionEvent);
        }
    }

    public interface b {
        void a();
    }

    public interface c {
        void a();
    }

    public class d extends SimpleOnScaleGestureListener {
        protected boolean a = false;
        final /* synthetic */ ImageViewTouch b;

        public d(ImageViewTouch imageViewTouch) {
            this.b = imageViewTouch;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float currentSpan = scaleGestureDetector.getCurrentSpan() - scaleGestureDetector.getPreviousSpan();
            float scale = this.b.getScale() * scaleGestureDetector.getScaleFactor();
            if (this.b.i) {
                if (this.a && currentSpan != 0.0f) {
                    this.b.q = true;
                    this.b.b(Math.min(this.b.getMaxScale(), Math.max(scale, this.b.getMinScale() - 0.1f)), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                    this.b.e = 1;
                    this.b.invalidate();
                } else if (!this.a) {
                    this.a = true;
                }
            }
            return true;
        }
    }

    public ImageViewTouch(Context context) {
        super(context);
        this.h = true;
        this.i = true;
        this.j = true;
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageViewTouch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = true;
        this.i = true;
        this.j = true;
    }

    protected void a(Context context, AttributeSet attributeSet, int i) {
        super.a(context, attributeSet, i);
        this.c = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.f = getGestureListener();
        this.g = getScaleListener();
        this.a = new ScaleGestureDetector(getContext(), this.g);
        this.b = new GestureDetector(getContext(), this.f, null, true);
        this.e = 1;
    }

    public void setDoubleTapListener(b bVar) {
        this.y = bVar;
    }

    public void setSingleTapListener(c cVar) {
        this.z = cVar;
    }

    public void setDoubleTapEnabled(boolean z) {
        this.h = z;
    }

    public void setScaleEnabled(boolean z) {
        this.i = z;
    }

    public void setScrollEnabled(boolean z) {
        this.j = z;
    }

    public boolean getDoubleTapEnabled() {
        return this.h;
    }

    protected OnGestureListener getGestureListener() {
        return new a(this);
    }

    protected OnScaleGestureListener getScaleListener() {
        return new d(this);
    }

    protected void a(Drawable drawable, Matrix matrix, float f, float f2) {
        super.a(drawable, matrix, f, f2);
        this.d = getMaxScale() / 3.0f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.a.onTouchEvent(motionEvent);
        if (!this.a.isInProgress()) {
            this.b.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction() & 255) {
            case 1:
                return c(motionEvent);
            default:
                return true;
        }
    }

    protected void a(float f) {
        if (f < getMinScale()) {
            c(getMinScale(), 50.0f);
        }
    }

    protected float a(float f, float f2) {
        if (this.e != 1) {
            this.e = 1;
            return 1.0f;
        } else if ((this.d * 2.0f) + f <= f2) {
            return f + this.d;
        } else {
            this.e = -1;
            return f2;
        }
    }

    public boolean a(MotionEvent motionEvent) {
        return true;
    }

    public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (getScale() == 1.0f) {
            return false;
        }
        this.q = true;
        d(-f, -f2);
        invalidate();
        return true;
    }

    public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = motionEvent2.getX() - motionEvent.getX();
        float y = motionEvent2.getY() - motionEvent.getY();
        if (Math.abs(f) <= 800.0f && Math.abs(f2) <= 800.0f) {
            return false;
        }
        this.q = true;
        a(x / 2.0f, y / 2.0f, 300.0d);
        invalidate();
        return true;
    }

    public boolean b(MotionEvent motionEvent) {
        return true;
    }

    public boolean c(MotionEvent motionEvent) {
        if (getScale() < getMinScale()) {
            c(getMinScale(), 50.0f);
        }
        return true;
    }

    public boolean d(MotionEvent motionEvent) {
        return true;
    }

    public boolean a(int i) {
        RectF bitmapRect = getBitmapRect();
        a(bitmapRect, this.x);
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        if (bitmapRect == null) {
            return false;
        }
        if (bitmapRect.right < ((float) rect.right) || i >= 0) {
            if (((double) Math.abs(bitmapRect.left - this.x.left)) <= 1.0d) {
                return false;
            }
            return true;
        } else if (Math.abs(bitmapRect.right - ((float) rect.right)) <= 1.0f) {
            return false;
        } else {
            return true;
        }
    }
}
