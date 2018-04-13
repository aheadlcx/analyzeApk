package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class SwipeDismissBehavior<V extends View> extends Behavior<V> {
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private boolean a;
    ViewDragHelper b;
    OnDismissListener c;
    int d = 2;
    float e = 0.5f;
    float f = 0.0f;
    float g = 0.5f;
    private float h = 0.0f;
    private boolean i;
    private final Callback j = new az(this);

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    private class a implements Runnable {
        final /* synthetic */ SwipeDismissBehavior a;
        private final View b;
        private final boolean c;

        a(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z) {
            this.a = swipeDismissBehavior;
            this.b = view;
            this.c = z;
        }

        public void run() {
            if (this.a.b != null && this.a.b.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.b, this);
            } else if (this.c && this.a.c != null) {
                this.a.c.onDismiss(this.b);
            }
        }
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.c = onDismissListener;
    }

    public void setSwipeDirection(int i) {
        this.d = i;
    }

    public void setDragDismissDistance(float f) {
        this.e = a(0.0f, f, 1.0f);
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.f = a(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.g = a(0.0f, f, 1.0f);
    }

    public void setSensitivity(float f) {
        this.h = f;
        this.i = true;
    }

    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.a;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.a = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
                z = this.a;
                break;
            case 1:
            case 3:
                this.a = false;
                break;
        }
        if (!z) {
            return false;
        }
        a(coordinatorLayout);
        return this.b.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.b == null) {
            return false;
        }
        this.b.processTouchEvent(motionEvent);
        return true;
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    private void a(ViewGroup viewGroup) {
        if (this.b == null) {
            ViewDragHelper create;
            if (this.i) {
                create = ViewDragHelper.create(viewGroup, this.h, this.j);
            } else {
                create = ViewDragHelper.create(viewGroup, this.j);
            }
            this.b = create;
        }
    }

    static float a(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int a(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public int getDragState() {
        return this.b != null ? this.b.getViewDragState() : 0;
    }

    static float b(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }
}
