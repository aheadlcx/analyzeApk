package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import qsbk.app.R;

public class TopicViewPager extends ViewPager {
    private int a = 2300;
    private boolean b = false;
    private Runnable c = new i(this);

    public TopicViewPager(Context context) {
        super(context);
        a(null, 0);
    }

    public TopicViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TopicViewPager, i, 0);
        this.a = obtainStyledAttributes.getInteger(0, 2300);
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec((MeasureSpec.getSize(i) * 5) / 9, 1073741824));
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        a(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.b = true;
                removeCallbacks(this.c);
                return;
            case 1:
            case 3:
                this.b = false;
                postDelayed(this.c, (long) this.a);
                return;
            default:
                return;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(this.c, (long) this.a);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.c);
    }

    public void setCurrentItemInternal(int i, boolean z, boolean z2) {
        super.setCurrentItemInternal(i, z, z2, z ? 1 : 0);
    }
}
