package qsbk.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class TopicScrollView extends ViewGroup {
    private int a;
    private int b;
    private Paint c;

    public TopicScrollView(Context context) {
        super(context);
        a();
    }

    public TopicScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public TopicScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.c = new Paint();
        this.c.setColor(419430400);
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        measureChildren(i, MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), Integer.MIN_VALUE));
        int i4 = 0;
        for (int i5 = 1; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            i4 = Math.max(i4, childAt.getMeasuredWidth());
            i3 = Math.max(i3, childAt.getMeasuredHeight());
        }
        this.b = i3;
        setMeasuredDimension(resolveSize(i4, i), resolveSize(i3, i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 1; i5 < getChildCount(); i5++) {
            getChildAt(i5).layout(0, 0, i3 - i, this.b);
        }
        offsetContent(this.a);
    }

    public void offsetContent(int i) {
        this.a = i;
        getChildAt(0).layout(0, 0, getWidth(), this.b + i);
        for (int i2 = 1; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            childAt.offsetTopAndBottom(i - childAt.getTop());
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild = super.drawChild(canvas, view, j);
        if (view == getChildAt(0)) {
            canvas.drawRect((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) view.getBottom(), this.c);
        }
        return drawChild;
    }
}
