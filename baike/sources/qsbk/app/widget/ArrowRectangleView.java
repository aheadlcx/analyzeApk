package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import qsbk.app.R;

public class ArrowRectangleView extends ViewGroup {
    private int a;
    private int b;
    private int c;
    private int d;
    private boolean e;

    public ArrowRectangleView(Context context) {
        this(context, null);
    }

    public ArrowRectangleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArrowRectangleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 40;
        this.b = 20;
        this.c = -1;
        this.d = -1;
        this.e = true;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ArrowRectangleView, 0, 0);
        try {
            this.c = obtainStyledAttributes.getColor(0, -1);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setArrowOffset(int i) {
        this.d = i;
        invalidate();
    }

    public void setArrowIsAbove(boolean z) {
        this.e = z;
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        int i3 = 0;
        int i4 = 0;
        int i5 = this.d == -1 ? 0 : this.b;
        while (i3 < childCount) {
            int measuredHeight;
            View childAt = getChildAt(i3);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i, i2);
                i4 = Math.max(i4, (childAt.getMeasuredWidth() + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin);
                measuredHeight = marginLayoutParams.bottomMargin + ((i5 + childAt.getMeasuredHeight()) + marginLayoutParams.topMargin);
                i5 = i4;
            } else {
                measuredHeight = i5;
                i5 = i4;
            }
            i3++;
            i4 = i5;
            i5 = measuredHeight;
        }
        setMeasuredDimension((getPaddingLeft() + i4) + getPaddingRight(), (i5 + getPaddingTop()) + getPaddingBottom());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int childCount = getChildCount();
        if (this.d == -1) {
            i5 = 0;
        } else if (this.e) {
            i5 = this.b;
        } else {
            i5 = 0;
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            int measuredHeight = (childAt.getMeasuredHeight() * i6) + i5;
            childAt.layout(0, measuredHeight, childAt.getMeasuredWidth() + 0, childAt.getMeasuredHeight() + measuredHeight);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        setLayerType(1, null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.c);
        paint.setStyle(Style.FILL);
        if (this.d != -1) {
            Point point;
            Point point2;
            Point point3;
            Path path = new Path();
            if (this.e) {
                point = new Point(this.d, 0);
                point2 = new Point(point.x - (this.a / 2), this.b + 0);
                point3 = new Point(point.x + (this.a / 2), this.b + 0);
            } else {
                point = new Point(this.d, getMeasuredHeight());
                point2 = new Point(point.x - (this.a / 2), getMeasuredHeight() - this.b);
                point3 = new Point(point.x + (this.a / 2), getMeasuredHeight() - this.b);
            }
            path.moveTo((float) point.x, (float) point.y);
            path.lineTo((float) point2.x, (float) point2.y);
            path.lineTo((float) point3.x, (float) point3.y);
            path.close();
            canvas.drawPath(path, paint);
        }
        super.dispatchDraw(canvas);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(super.generateDefaultLayoutParams());
    }
}
