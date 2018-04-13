package qsbk.app.widget.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import qsbk.app.R;

public class GroupMonthActiveView extends View {
    private int[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Paint g;

    public GroupMonthActiveView(Context context) {
        super(context);
        a(null, 0);
    }

    public GroupMonthActiveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public GroupMonthActiveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.GroupMonthActiveView, i, 0);
        float f = getResources().getDisplayMetrics().density;
        this.b = obtainStyledAttributes.getDimensionPixelOffset(2, (int) (6.0f * f));
        this.c = obtainStyledAttributes.getDimensionPixelSize(1, (int) (f * 13.0f));
        this.d = obtainStyledAttributes.getInt(0, 16);
        setActivesWithString(obtainStyledAttributes.getString(3));
        obtainStyledAttributes.recycle();
        this.g = new Paint();
    }

    public void setActivesWithString(String str) {
        if (TextUtils.isEmpty(str)) {
            this.a = null;
        } else {
            String[] split = str.split("\\s*,\\s*");
            try {
                int[] iArr = new int[split.length];
                for (int i = 0; i < iArr.length; i++) {
                    iArr[i] = Integer.valueOf(split[i]).intValue();
                }
                this.a = iArr;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                this.a = null;
            }
        }
        requestLayout();
    }

    public void setActives(int[] iArr) {
        this.a = iArr;
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        if (size == 0) {
            size = 1048576;
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int i3 = size - paddingRight;
        size = this.a == null ? 0 : this.a.length;
        i3 = Math.min(this.d, (i3 + this.b) / (this.c + this.b));
        int max = Math.max(0, ((size - 1) / i3) + 1);
        if (max > 1) {
            size = i3;
        }
        setMeasuredDimension(resolveSize(Math.max(((size * (this.c + this.b)) - this.b) + paddingRight, getSuggestedMinimumWidth()), i), resolveSize(Math.max(((max * (this.c + this.b)) - this.b) + paddingBottom, getSuggestedMinimumHeight()), i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int length = this.a == null ? 0 : this.a.length;
        int min = Math.min(this.d, ((((i3 - i) - getPaddingLeft()) - getPaddingRight()) + this.b) / (this.c + this.b));
        this.e = Math.max(0, ((length - 1) / min) + 1);
        if (this.e > 1) {
            length = min;
        }
        this.f = length;
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        int i;
        if (this.a == null) {
            i = 0;
        } else {
            i = this.a.length;
        }
        int i2 = this.c + this.b;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i3 = 0; i3 < i; i3++) {
            float f = (float) (((i3 % this.f) * i2) + paddingLeft);
            float f2 = (float) (((i3 / this.f) * i2) + paddingTop);
            float f3 = f + ((float) this.c);
            float f4 = f2 + ((float) this.c);
            int i4 = this.a[i3];
            if (i4 <= 0) {
                this.g.setColor(-35210);
                this.g.setStyle(Style.STROKE);
                float f5 = ((float) this.c) * 0.1f;
                float f6 = f5 / 2.0f;
                f += f6;
                f2 += f6;
                f3 -= f6;
                f4 -= f6;
                this.g.setStrokeWidth(f5);
            } else if (i4 < 10) {
                this.g.setColor(-10795);
                this.g.setStyle(Style.FILL);
            } else if (i4 < 50) {
                this.g.setColor(-32383);
                this.g.setStyle(Style.FILL);
            } else {
                this.g.setColor(-39065);
                this.g.setStyle(Style.FILL);
            }
            canvas.drawRect(f, f2, f3, f4, this.g);
        }
    }
}
