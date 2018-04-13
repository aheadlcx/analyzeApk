package qsbk.app.widget.imageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;
import qsbk.app.exception.CrashHandler;

public class TouchImageView extends ImageView {
    private static final String DEBUG = "DEBUG";
    private static final float SUPER_MAX_MULTIPLIER = 1.25f;
    private static final float SUPER_MIN_MULTIPLIER = 0.75f;
    private Context context;
    private h delayedZoomVariables;
    private OnDoubleTapListener doubleTapListener = null;
    private c fling;
    private boolean imageRenderedAtLeastOnce;
    private boolean isLongImage;
    private boolean isLongImageFirstDraw;
    private float[] m;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleDetector;
    private ScaleType mScaleType;
    private float matchViewHeight;
    private float matchViewWidth;
    private Matrix matrix;
    private float maxScale;
    private float minScale;
    private float normalizedScale;
    private boolean onDrawReady;
    private float prevMatchViewHeight;
    private float prevMatchViewWidth;
    private Matrix prevMatrix;
    private int prevViewHeight;
    private int prevViewWidth;
    private g state;
    private float superMaxScale;
    private float superMinScale;
    private OnTouchImageViewListener touchImageViewListener = null;
    private OnTouchLongImageViewListener touchLongImageViewListener = null;
    private OnTouchListener userTouchListener = null;
    private int viewHeight;
    private int viewWidth;

    public interface OnTouchLongImageViewListener {
        void onMove(View view, MotionEvent motionEvent);
    }

    public interface OnTouchImageViewListener {
        void onMove();
    }

    @TargetApi(9)
    private class a {
        Scroller a;
        OverScroller b;
        boolean c;
        final /* synthetic */ TouchImageView d;

        public a(TouchImageView touchImageView, Context context) {
            this.d = touchImageView;
            if (VERSION.SDK_INT < 9) {
                this.c = true;
                this.a = new Scroller(context);
                return;
            }
            this.c = false;
            this.b = new OverScroller(context);
        }

        public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.c) {
                this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.b.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void forceFinished(boolean z) {
            if (this.c) {
                this.a.forceFinished(z);
            } else {
                this.b.forceFinished(z);
            }
        }

        public boolean isFinished() {
            if (this.c) {
                return this.a.isFinished();
            }
            return this.b.isFinished();
        }

        public boolean computeScrollOffset() {
            if (this.c) {
                return this.a.computeScrollOffset();
            }
            this.b.computeScrollOffset();
            return this.b.computeScrollOffset();
        }

        public int getCurrX() {
            if (this.c) {
                return this.a.getCurrX();
            }
            return this.b.getCurrX();
        }

        public int getCurrY() {
            if (this.c) {
                return this.a.getCurrY();
            }
            return this.b.getCurrY();
        }
    }

    private class b implements Runnable {
        final /* synthetic */ TouchImageView a;
        private long b;
        private float c;
        private float d;
        private float e;
        private float f;
        private boolean g;
        private AccelerateDecelerateInterpolator h = new AccelerateDecelerateInterpolator();
        private PointF i;
        private PointF j;

        b(TouchImageView touchImageView, float f, float f2, float f3, boolean z) {
            this.a = touchImageView;
            touchImageView.setState(g.ANIMATE_ZOOM);
            this.b = System.currentTimeMillis();
            this.c = touchImageView.normalizedScale;
            this.d = f;
            this.g = z;
            PointF access$2400 = touchImageView.transformCoordTouchToBitmap(f2, f3, false);
            this.e = access$2400.x;
            this.f = access$2400.y;
            this.i = touchImageView.transformCoordBitmapToTouch(this.e, this.f);
            this.j = new PointF((float) (touchImageView.viewWidth / 2), (float) (touchImageView.viewHeight / 2));
        }

        public void run() {
            float a = a();
            this.a.scaleImage(b(a), this.e, this.f, this.g);
            a(a);
            this.a.fixScaleTrans();
            this.a.setImageMatrix(this.a.matrix);
            if (this.a.touchImageViewListener != null) {
                this.a.touchImageViewListener.onMove();
            }
            if (a < 1.0f) {
                this.a.compatPostOnAnimation(this);
            } else {
                this.a.setState(g.NONE);
            }
        }

        private void a(float f) {
            float f2 = this.i.x + ((this.j.x - this.i.x) * f);
            float f3 = this.i.y + ((this.j.y - this.i.y) * f);
            PointF access$2500 = this.a.transformCoordBitmapToTouch(this.e, this.f);
            this.a.matrix.postTranslate(f2 - access$2500.x, f3 - access$2500.y);
        }

        private float a() {
            return this.h.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.b)) / 500.0f));
        }

        private double b(float f) {
            return ((double) (this.c + ((this.d - this.c) * f))) / ((double) this.a.normalizedScale);
        }
    }

    private class c implements Runnable {
        a a;
        int b;
        int c;
        final /* synthetic */ TouchImageView d;

        c(TouchImageView touchImageView, int i, int i2) {
            int access$1300;
            int i3;
            int access$1600;
            int i4;
            this.d = touchImageView;
            touchImageView.setState(g.FLING);
            this.a = new a(touchImageView, touchImageView.context);
            touchImageView.matrix.getValues(touchImageView.m);
            int i5 = (int) touchImageView.m[2];
            int i6 = (int) touchImageView.m[5];
            if (touchImageView.getImageWidth() > ((float) touchImageView.viewWidth)) {
                access$1300 = touchImageView.viewWidth - ((int) touchImageView.getImageWidth());
                i3 = 0;
            } else {
                i3 = i5;
                access$1300 = i5;
            }
            if (touchImageView.getImageHeight() > ((float) touchImageView.viewHeight)) {
                access$1600 = touchImageView.viewHeight - ((int) touchImageView.getImageHeight());
                i4 = 0;
            } else {
                i4 = i6;
                access$1600 = i6;
            }
            this.a.fling(i5, i6, i, i2, access$1300, i3, access$1600, i4);
            this.b = i5;
            this.c = i6;
        }

        public void cancelFling() {
            if (this.a != null) {
                this.d.setState(g.NONE);
                this.a.forceFinished(true);
            }
        }

        public void run() {
            if (this.d.touchImageViewListener != null) {
                this.d.touchImageViewListener.onMove();
            }
            if (this.a.isFinished()) {
                this.a = null;
            } else if (this.a.computeScrollOffset()) {
                int currX = this.a.getCurrX();
                int currY = this.a.getCurrY();
                int i = currX - this.b;
                int i2 = currY - this.c;
                this.b = currX;
                this.c = currY;
                this.d.matrix.postTranslate((float) i, (float) i2);
                this.d.fixTrans();
                this.d.setImageMatrix(this.d.matrix);
                this.d.compatPostOnAnimation(this);
            }
        }
    }

    private class d extends SimpleOnGestureListener {
        final /* synthetic */ TouchImageView a;

        private d(TouchImageView touchImageView) {
            this.a = touchImageView;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.a.doubleTapListener != null) {
                return this.a.doubleTapListener.onSingleTapConfirmed(motionEvent);
            }
            return this.a.performClick();
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.a.performLongClick();
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.fling != null) {
                this.a.fling.cancelFling();
            }
            this.a.fling = new c(this.a, (int) f, (int) f2);
            this.a.compatPostOnAnimation(this.a.fling);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap;
            if (this.a.doubleTapListener != null) {
                onDoubleTap = this.a.doubleTapListener.onDoubleTap(motionEvent);
            } else {
                onDoubleTap = false;
            }
            if (this.a.state != g.NONE) {
                return onDoubleTap;
            }
            this.a.compatPostOnAnimation(new b(this.a, this.a.normalizedScale == this.a.minScale ? this.a.maxScale : this.a.minScale, motionEvent.getX(), motionEvent.getY(), false));
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (this.a.doubleTapListener != null) {
                return this.a.doubleTapListener.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }

    private class e implements OnTouchListener {
        final /* synthetic */ TouchImageView a;
        private PointF b;

        private e(TouchImageView touchImageView) {
            this.a = touchImageView;
            this.b = new PointF();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.mScaleDetector.onTouchEvent(motionEvent);
            this.a.mGestureDetector.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (this.a.state == g.NONE || this.a.state == g.DRAG || this.a.state == g.FLING) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.b.set(pointF);
                        if (this.a.fling != null) {
                            this.a.fling.cancelFling();
                        }
                        this.a.setState(g.DRAG);
                        break;
                    case 1:
                    case 6:
                        this.a.setState(g.NONE);
                        break;
                    case 2:
                        if (this.a.state == g.DRAG) {
                            float f = pointF.y - this.b.y;
                            this.a.matrix.postTranslate(this.a.getFixDragTrans(pointF.x - this.b.x, (float) this.a.viewWidth, this.a.getImageWidth()), this.a.getFixDragTrans(f, (float) this.a.viewHeight, this.a.getImageHeight()));
                            this.a.fixTrans();
                            this.b.set(pointF.x, pointF.y);
                            break;
                        }
                        break;
                }
            }
            this.a.setImageMatrix(this.a.matrix);
            if (this.a.userTouchListener != null) {
                this.a.userTouchListener.onTouch(view, motionEvent);
            }
            if (this.a.touchImageViewListener != null) {
                this.a.touchImageViewListener.onMove();
            }
            if (this.a.touchLongImageViewListener != null) {
                this.a.touchLongImageViewListener.onMove(view, motionEvent);
            }
            return true;
        }
    }

    private class f extends SimpleOnScaleGestureListener {
        final /* synthetic */ TouchImageView a;

        private f(TouchImageView touchImageView) {
            this.a = touchImageView;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            this.a.setState(g.ZOOM);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.a.scaleImage((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (this.a.touchImageViewListener != null) {
                this.a.touchImageViewListener.onMove();
            }
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            this.a.setState(g.NONE);
            boolean z = false;
            float access$700 = this.a.normalizedScale;
            if (this.a.normalizedScale > this.a.maxScale) {
                access$700 = this.a.maxScale;
                z = true;
            } else if (this.a.normalizedScale < this.a.minScale) {
                access$700 = this.a.minScale;
                z = true;
            }
            if (z) {
                this.a.compatPostOnAnimation(new b(this.a, access$700, (float) (this.a.viewWidth / 2), (float) (this.a.viewHeight / 2), true));
            }
        }
    }

    private enum g {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    private class h {
        final /* synthetic */ TouchImageView a;
        public float focusX;
        public float focusY;
        public float scale;
        public ScaleType scaleType;

        public h(TouchImageView touchImageView, float f, float f2, float f3, ScaleType scaleType) {
            this.a = touchImageView;
            this.scale = f;
            this.focusX = f2;
            this.focusY = f3;
            this.scaleType = scaleType;
        }
    }

    public TouchImageView(Context context) {
        super(context);
        sharedConstructing(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        sharedConstructing(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        sharedConstructing(context);
    }

    private void sharedConstructing(Context context) {
        super.setClickable(true);
        this.context = context;
        this.mScaleDetector = new ScaleGestureDetector(context, new f());
        this.mGestureDetector = new GestureDetector(context, new d());
        this.matrix = new Matrix();
        this.prevMatrix = new Matrix();
        this.m = new float[9];
        this.normalizedScale = 1.0f;
        if (this.mScaleType == null) {
            this.mScaleType = ScaleType.FIT_CENTER;
        }
        this.minScale = 1.0f;
        this.maxScale = 2.0f;
        this.superMinScale = 0.75f * this.minScale;
        this.superMaxScale = SUPER_MAX_MULTIPLIER * this.maxScale;
        setImageMatrix(this.matrix);
        setScaleType(ScaleType.MATRIX);
        setState(g.NONE);
        this.onDrawReady = false;
        super.setOnTouchListener(new e());
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.userTouchListener = onTouchListener;
    }

    public void setTouchLongImageViewListener(OnTouchLongImageViewListener onTouchLongImageViewListener) {
        this.touchLongImageViewListener = onTouchLongImageViewListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
        this.touchImageViewListener = onTouchImageViewListener;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.doubleTapListener = onDoubleTapListener;
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            super.setImageBitmap(bitmap);
            savePreviousImageValues();
            fitImageToView();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        savePreviousImageValues();
        fitImageToView();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        savePreviousImageValues();
        fitImageToView();
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.mScaleType = scaleType;
            if (this.onDrawReady) {
                setZoom(this);
            }
        }
    }

    public boolean isZoomed() {
        return this.normalizedScale != 1.0f;
    }

    public RectF getZoomedRect() {
        if (this.mScaleType == ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap(0.0f, 0.0f, true);
        PointF transformCoordTouchToBitmap2 = transformCoordTouchToBitmap((float) this.viewWidth, (float) this.viewHeight, true);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        return new RectF(transformCoordTouchToBitmap.x / intrinsicWidth, transformCoordTouchToBitmap.y / intrinsicHeight, transformCoordTouchToBitmap2.x / intrinsicWidth, transformCoordTouchToBitmap2.y / intrinsicHeight);
    }

    private void savePreviousImageValues() {
        if (this.matrix != null && this.viewHeight != 0 && this.viewWidth != 0) {
            this.matrix.getValues(this.m);
            this.prevMatrix.setValues(this.m);
            this.prevMatchViewHeight = this.matchViewHeight;
            this.prevMatchViewWidth = this.matchViewWidth;
            this.prevViewHeight = this.viewHeight;
            this.prevViewWidth = this.viewWidth;
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.normalizedScale);
        bundle.putFloat("matchViewHeight", this.matchViewHeight);
        bundle.putFloat("matchViewWidth", this.matchViewWidth);
        bundle.putInt("viewWidth", this.viewWidth);
        bundle.putInt("viewHeight", this.viewHeight);
        this.matrix.getValues(this.m);
        bundle.putFloatArray("matrix", this.m);
        bundle.putBoolean("imageRendered", this.imageRenderedAtLeastOnce);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.normalizedScale = bundle.getFloat("saveScale");
            this.m = bundle.getFloatArray("matrix");
            this.prevMatrix.setValues(this.m);
            this.prevMatchViewHeight = bundle.getFloat("matchViewHeight");
            this.prevMatchViewWidth = bundle.getFloat("matchViewWidth");
            this.prevViewHeight = bundle.getInt("viewHeight");
            this.prevViewWidth = bundle.getInt("viewWidth");
            this.imageRenderedAtLeastOnce = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onDraw(Canvas canvas) {
        this.onDrawReady = true;
        this.imageRenderedAtLeastOnce = true;
        if (this.delayedZoomVariables != null) {
            setZoom(this.delayedZoomVariables.scale, this.delayedZoomVariables.focusX, this.delayedZoomVariables.focusY, this.delayedZoomVariables.scaleType);
            this.delayedZoomVariables = null;
        }
        try {
            super.onDraw(canvas);
        } catch (Throwable th) {
            CrashHandler.getInstance().logException(th);
            System.gc();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        savePreviousImageValues();
    }

    public float getMaxZoom() {
        return this.maxScale;
    }

    public void setMaxZoom(float f) {
        this.maxScale = f;
        this.superMaxScale = SUPER_MAX_MULTIPLIER * this.maxScale;
    }

    public float getMinZoom() {
        return this.minScale;
    }

    public void setMinZoom(float f) {
        this.minScale = f;
        this.superMinScale = 0.75f * this.minScale;
    }

    public float getCurrentZoom() {
        return this.normalizedScale;
    }

    public void resetZoom() {
        this.normalizedScale = 1.0f;
        fitImageToView();
    }

    public void setZoom(float f) {
        setZoom(f, 0.5f, 0.5f);
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.mScaleType);
    }

    public void setZoom(float f, float f2, float f3, ScaleType scaleType) {
        if (this.onDrawReady) {
            if (scaleType != this.mScaleType) {
                setScaleType(scaleType);
            }
            resetZoom();
            scaleImage((double) f, (float) (this.viewWidth / 2), (float) (this.viewHeight / 2), true);
            this.matrix.getValues(this.m);
            this.m[2] = -((getImageWidth() * f2) - (((float) this.viewWidth) * 0.5f));
            this.m[5] = -((getImageHeight() * f3) - (((float) this.viewHeight) * 0.5f));
            this.matrix.setValues(this.m);
            fixTrans();
            setImageMatrix(this.matrix);
            return;
        }
        this.delayedZoomVariables = new h(this, f, f2, f3, scaleType);
    }

    public void setZoom(TouchImageView touchImageView) {
        PointF scrollPosition = touchImageView.getScrollPosition();
        setZoom(touchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF transformCoordTouchToBitmap = transformCoordTouchToBitmap((float) (this.viewWidth / 2), (float) (this.viewHeight / 2), true);
        transformCoordTouchToBitmap.x /= (float) intrinsicWidth;
        transformCoordTouchToBitmap.y /= (float) intrinsicHeight;
        return transformCoordTouchToBitmap;
    }

    public void setScrollPosition(float f, float f2) {
        setZoom(this.normalizedScale, f, f2);
    }

    private void fixTrans() {
        this.matrix.getValues(this.m);
        float f = this.m[2];
        float f2 = this.m[5];
        f = getFixTrans(f, (float) this.viewWidth, getImageWidth());
        f2 = getFixTrans(f2, (float) this.viewHeight, getImageHeight());
        if (f != 0.0f || f2 != 0.0f) {
            this.matrix.postTranslate(f, f2);
        }
    }

    private void fixScaleTrans() {
        fixTrans();
        this.matrix.getValues(this.m);
        if (getImageWidth() < ((float) this.viewWidth)) {
            this.m[2] = (((float) this.viewWidth) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.viewHeight)) {
            this.m[5] = (((float) this.viewHeight) - getImageHeight()) / 2.0f;
        }
        this.matrix.setValues(this.m);
    }

    private float getFixTrans(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    private float getFixDragTrans(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getImageWidth() {
        return this.matchViewWidth * this.normalizedScale;
    }

    private float getImageHeight() {
        return this.matchViewHeight * this.normalizedScale;
    }

    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.viewWidth = setViewSize(mode, size, intrinsicWidth);
        this.viewHeight = setViewSize(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.viewWidth, this.viewHeight);
        if (intrinsicHeight > (intrinsicWidth << 1)) {
            this.isLongImage = true;
            this.isLongImageFirstDraw = true;
        } else {
            this.isLongImage = false;
        }
        fitImageToView();
    }

    private void fitImageToView() {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0 && this.matrix != null && this.prevMatrix != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float f = ((float) this.viewWidth) / ((float) intrinsicWidth);
            float f2 = ((float) this.viewHeight) / ((float) intrinsicHeight);
            switch (d.a[this.mScaleType.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    f = 1.0f;
                    break;
                case 2:
                    f2 = Math.max(f, f2);
                    f = f2;
                    break;
                case 3:
                    f2 = Math.min(1.0f, Math.min(f, f2));
                    f = f2;
                    break;
                case 4:
                    f2 = Math.min(f, f2);
                    f = f2;
                    break;
                case 5:
                    break;
                default:
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
            }
            float f3 = ((float) this.viewWidth) - (((float) intrinsicWidth) * f);
            float f4 = ((float) this.viewHeight) - (((float) intrinsicHeight) * f2);
            this.matchViewWidth = ((float) this.viewWidth) - f3;
            this.matchViewHeight = ((float) this.viewHeight) - f4;
            if (isZoomed() || this.imageRenderedAtLeastOnce) {
                if (this.prevMatchViewWidth == 0.0f || this.prevMatchViewHeight == 0.0f) {
                    savePreviousImageValues();
                }
                this.prevMatrix.getValues(this.m);
                this.m[0] = (this.matchViewWidth / ((float) intrinsicWidth)) * this.normalizedScale;
                this.m[4] = (this.matchViewHeight / ((float) intrinsicHeight)) * this.normalizedScale;
                float f5 = this.m[2];
                float f6 = this.m[5];
                translateMatrixAfterRotate(2, f5, this.normalizedScale * this.prevMatchViewWidth, getImageWidth(), this.prevViewWidth, this.viewWidth, intrinsicWidth);
                translateMatrixAfterRotate(5, f6, this.prevMatchViewHeight * this.normalizedScale, getImageHeight(), this.prevViewHeight, this.viewHeight, intrinsicHeight);
                this.matrix.setValues(this.m);
            } else {
                this.matrix.setScale(f, f2);
                if (this.isLongImage && this.isLongImageFirstDraw) {
                    this.isLongImageFirstDraw = false;
                    this.matrix.postTranslate(f3 / 2.0f, 0.0f);
                } else {
                    this.matrix.postTranslate(f3 / 2.0f, f4 / 2.0f);
                }
                this.normalizedScale = 1.0f;
            }
            fixTrans();
            setImageMatrix(this.matrix);
        }
    }

    private int setViewSize(int i, int i2, int i3) {
        switch (i) {
            case Integer.MIN_VALUE:
                return Math.min(i3, i2);
            case 0:
                return i3;
            default:
                return i2;
        }
    }

    private void translateMatrixAfterRotate(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        if (f3 < ((float) i3)) {
            this.m[i] = (((float) i3) - (((float) i4) * this.m[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.m[i] = -((f3 - ((float) i3)) * 0.5f);
        } else {
            this.m[i] = -((((Math.abs(f) + (((float) i2) * 0.5f)) / f2) * f3) - (((float) i3) * 0.5f));
        }
    }

    private void setState(g gVar) {
        this.state = gVar;
    }

    public boolean canScrollHorizontallyFroyo(int i) {
        return canScrollHorizontally(i);
    }

    public boolean canScrollHorizontally(int i) {
        this.matrix.getValues(this.m);
        float f = this.m[2];
        if (getImageWidth() < ((float) this.viewWidth)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if ((Math.abs(f) + ((float) this.viewWidth)) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    private void scaleImage(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.superMinScale;
            f4 = this.superMaxScale;
        } else {
            f3 = this.minScale;
            f4 = this.maxScale;
        }
        float f5 = this.normalizedScale;
        this.normalizedScale = (float) (((double) this.normalizedScale) * d);
        if (this.normalizedScale > f4) {
            this.normalizedScale = f4;
            d = (double) (f4 / f5);
        } else if (this.normalizedScale < f3) {
            this.normalizedScale = f3;
            d = (double) (f3 / f5);
        }
        this.matrix.postScale((float) d, (float) d, f, f2);
        fixScaleTrans();
    }

    private PointF transformCoordTouchToBitmap(float f, float f2, boolean z) {
        this.matrix.getValues(this.m);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f3 = this.m[2];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        f3 = ((f2 - this.m[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            f3 = Math.min(Math.max(f3, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, f3);
    }

    private PointF transformCoordBitmapToTouch(float f, float f2) {
        float f3 = 1.0f;
        this.matrix.getValues(this.m);
        Drawable drawable = getDrawable();
        float intrinsicWidth = drawable != null ? (float) drawable.getIntrinsicWidth() : 1.0f;
        if (drawable != null) {
            f3 = (float) drawable.getIntrinsicHeight();
        }
        return new PointF(((f / intrinsicWidth) * getImageWidth()) + this.m[2], ((f2 / f3) * getImageHeight()) + this.m[5]);
    }

    @TargetApi(16)
    private void compatPostOnAnimation(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }

    private void printMatrixInfo() {
        float[] fArr = new float[9];
        this.matrix.getValues(fArr);
        Log.d(DEBUG, "Scale: " + fArr[0] + " TransX: " + fArr[2] + " TransY: " + fArr[5]);
    }
}
