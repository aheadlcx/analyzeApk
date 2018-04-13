package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import qsbk.app.R;
import qsbk.app.widget.PtrLayout;

public class LiveBannerViewPager extends ViewPager {
    private int a = 2300;
    private boolean b = false;
    private View c;
    private float d;
    private float e;
    private float f;
    private float g;
    private boolean h = true;
    private Runnable i = new e(this);

    public LiveBannerViewPager(Context context) {
        super(context);
        a(null, 0);
    }

    public LiveBannerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public void bindView(View view) {
        this.c = view;
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TopicViewPager, i, 0);
        this.a = obtainStyledAttributes.getInteger(0, 2300);
        obtainStyledAttributes.recycle();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = true;
                removeCallbacks(this.i);
                if (this.c != null && (this.c instanceof PtrLayout)) {
                    ((PtrLayout) this.c).setRefreshEnable(false);
                }
                this.d = motionEvent.getX();
                this.e = motionEvent.getY();
                break;
            case 1:
            case 3:
                this.b = false;
                if (this.c != null && (this.c instanceof PtrLayout)) {
                    ((PtrLayout) this.c).setRefreshEnable(true);
                }
                postDelayed(this.i, (long) this.a);
                break;
            case 2:
                break;
        }
        this.f = Math.abs(motionEvent.getX() - this.d);
        this.g = Math.abs(motionEvent.getY() - this.e);
        if (this.g > 10.0f && this.g > this.f * 2.0f && this.c != null && (this.c instanceof PtrLayout)) {
            ((PtrLayout) this.c).setRefreshEnable(true);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                this.b = false;
                if (this.c != null && (this.c instanceof PtrLayout)) {
                    ((PtrLayout) this.c).setRefreshEnable(true);
                }
                postDelayed(this.i, (long) this.a);
                return;
            default:
                return;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(this.i, (long) this.a);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.i);
    }

    public void setCurrentItemInternal(int i, boolean z, boolean z2) {
        super.setCurrentItemInternal(i, z, z2, z ? 1 : 0);
    }

    public void setLoopEnable(boolean z) {
        this.h = z;
    }
}
