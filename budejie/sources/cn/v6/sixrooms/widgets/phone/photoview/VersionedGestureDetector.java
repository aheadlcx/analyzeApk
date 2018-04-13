package cn.v6.sixrooms.widgets.phone.photoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public abstract class VersionedGestureDetector {
    OnGestureListener a;

    public interface OnGestureListener {
        void onDrag(float f, float f2);

        void onFling(float f, float f2, float f3, float f4);

        void onScale(float f, float f2, float f3);
    }

    private static class a extends VersionedGestureDetector {
        float b;
        float c;
        final float d;
        final float e;
        private VelocityTracker f;
        private boolean g;

        public a(Context context) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.e = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.d = (float) viewConfiguration.getScaledTouchSlop();
        }

        float a(MotionEvent motionEvent) {
            return motionEvent.getX();
        }

        float b(MotionEvent motionEvent) {
            return motionEvent.getY();
        }

        public boolean isScaling() {
            return false;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z = false;
            float yVelocity;
            switch (motionEvent.getAction()) {
                case 0:
                    this.f = VelocityTracker.obtain();
                    this.f.addMovement(motionEvent);
                    this.b = a(motionEvent);
                    this.c = b(motionEvent);
                    this.g = false;
                    break;
                case 1:
                    if (this.g && this.f != null) {
                        this.b = a(motionEvent);
                        this.c = b(motionEvent);
                        this.f.addMovement(motionEvent);
                        this.f.computeCurrentVelocity(1000);
                        float xVelocity = this.f.getXVelocity();
                        yVelocity = this.f.getYVelocity();
                        if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.e) {
                            this.a.onFling(this.b, this.c, -xVelocity, -yVelocity);
                        }
                    }
                    if (this.f != null) {
                        this.f.recycle();
                        this.f = null;
                        break;
                    }
                    break;
                case 2:
                    yVelocity = a(motionEvent);
                    float b = b(motionEvent);
                    float f = yVelocity - this.b;
                    float f2 = b - this.c;
                    if (!this.g) {
                        if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.d)) {
                            z = true;
                        }
                        this.g = z;
                    }
                    if (this.g) {
                        this.a.onDrag(f, f2);
                        this.b = yVelocity;
                        this.c = b;
                        if (this.f != null) {
                            this.f.addMovement(motionEvent);
                            break;
                        }
                    }
                    break;
                case 3:
                    if (this.f != null) {
                        this.f.recycle();
                        this.f = null;
                        break;
                    }
                    break;
            }
            return true;
        }
    }

    @TargetApi(5)
    private static class b extends a {
        private int f = -1;
        private int g = 0;

        public b(Context context) {
            super(context);
        }

        final float a(MotionEvent motionEvent) {
            try {
                return motionEvent.getX(this.g);
            } catch (Exception e) {
                return motionEvent.getX();
            }
        }

        final float b(MotionEvent motionEvent) {
            try {
                return motionEvent.getY(this.g);
            } catch (Exception e) {
                return motionEvent.getY();
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            int i = 0;
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.f = motionEvent.getPointerId(0);
                    break;
                case 1:
                case 3:
                    this.f = -1;
                    break;
                case 6:
                    int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                    if (motionEvent.getPointerId(action) == this.f) {
                        if (action == 0) {
                            action = 1;
                        } else {
                            action = 0;
                        }
                        this.f = motionEvent.getPointerId(action);
                        this.b = motionEvent.getX(action);
                        this.c = motionEvent.getY(action);
                        break;
                    }
                    break;
            }
            if (this.f != -1) {
                i = this.f;
            }
            this.g = motionEvent.findPointerIndex(i);
            return super.onTouchEvent(motionEvent);
        }
    }

    @TargetApi(8)
    private static class c extends b {
        private final ScaleGestureDetector f;
        private final OnScaleGestureListener g = new c(this);

        public c(Context context) {
            super(context);
            this.f = new ScaleGestureDetector(context, this.g);
        }

        public final boolean isScaling() {
            return this.f.isInProgress();
        }

        public final boolean onTouchEvent(MotionEvent motionEvent) {
            this.f.onTouchEvent(motionEvent);
            return super.onTouchEvent(motionEvent);
        }
    }

    public abstract boolean isScaling();

    public abstract boolean onTouchEvent(MotionEvent motionEvent);

    public static VersionedGestureDetector newInstance(Context context, OnGestureListener onGestureListener) {
        VersionedGestureDetector aVar;
        int i = VERSION.SDK_INT;
        if (i < 5) {
            aVar = new a(context);
        } else if (i < 8) {
            aVar = new b(context);
        } else {
            aVar = new c(context);
        }
        aVar.a = onGestureListener;
        return aVar;
    }
}
