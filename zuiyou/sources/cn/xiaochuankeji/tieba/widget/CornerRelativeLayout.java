package cn.xiaochuankeji.tieba.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import cn.xiaochuankeji.tieba.R;

public class CornerRelativeLayout extends RelativeLayout {
    private float[] a;
    private Path b;
    private Paint c;
    private boolean d;
    private int e;
    private int f;
    private Region g;
    private int h;

    public CornerRelativeLayout(Context context) {
        this(context, null);
    }

    public CornerRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CornerRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new float[8];
        this.d = false;
        this.h = 10;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CornerRelativeLayout);
        this.d = obtainStyledAttributes.getBoolean(0, false);
        this.e = obtainStyledAttributes.getColor(6, -1);
        this.f = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(2, dimensionPixelSize);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(3, dimensionPixelSize);
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(4, dimensionPixelSize);
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(5, dimensionPixelSize);
        this.a[0] = (float) dimensionPixelSize2;
        this.a[1] = (float) dimensionPixelSize2;
        this.a[2] = (float) dimensionPixelSize3;
        this.a[3] = (float) dimensionPixelSize3;
        this.a[4] = (float) dimensionPixelSize5;
        this.a[5] = (float) dimensionPixelSize5;
        this.a[6] = (float) dimensionPixelSize4;
        this.a[7] = (float) dimensionPixelSize4;
        this.b = new Path();
        this.g = new Region();
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setAntiAlias(true);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        RectF rectF = new RectF();
        rectF.left = (float) getPaddingLeft();
        rectF.top = (float) getPaddingTop();
        rectF.right = (float) (i - getPaddingRight());
        rectF.bottom = (float) (i2 - getPaddingBottom());
        this.b.reset();
        if (this.d) {
            float height = (rectF.width() >= rectF.height() ? rectF.height() : rectF.width()) / 2.0f;
            PointF pointF = new PointF((float) (i / 2), (float) (i2 / 2));
            this.b.addCircle(pointF.x, pointF.y, height, Direction.CW);
            this.b.moveTo((float) (-this.h), (float) (-this.h));
            this.b.moveTo((float) (this.h + i), (float) (this.h + i2));
        } else {
            this.b.addRoundRect(rectF, this.a, Direction.CW);
        }
        this.g.setPath(this.b, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
    }

    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), null, 31);
        super.dispatchDraw(canvas);
        if (this.f > 0) {
            this.c.setXfermode(new PorterDuffXfermode(Mode.SRC_OVER));
            this.c.setStrokeWidth((float) (this.f * 2));
            this.c.setColor(this.e);
            this.c.setStyle(Style.STROKE);
            canvas.drawPath(this.b, this.c);
        }
        this.c.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.c.setColor(-1);
        this.c.setStyle(Style.FILL);
        canvas.drawPath(this.b, this.c);
        canvas.restore();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.g.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }
}
