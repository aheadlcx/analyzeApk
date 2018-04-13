package antistatic.spinnerwheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import antistatic.spinnerwheel.WheelScroller.ScrollingListener;
import qsbk.app.R;

public class WheelVerticalView extends AbstractWheelView {
    private static int z = -1;
    private final String A;
    private int B;
    protected int y;

    public WheelVerticalView(Context context) {
        this(context, null);
    }

    public WheelVerticalView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.abstractWheelViewStyle);
    }

    public WheelVerticalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        StringBuilder append = new StringBuilder().append(WheelVerticalView.class.getName()).append(" #");
        int i2 = z + 1;
        z = i2;
        this.A = append.append(i2).toString();
        this.B = 0;
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.WheelVerticalView, i, 0);
        this.y = obtainStyledAttributes.getDimensionPixelSize(0, 2);
        obtainStyledAttributes.recycle();
    }

    public void setSelectorPaintCoeff(float f) {
        Shader linearGradient;
        int measuredHeight = getMeasuredHeight();
        int itemDimension = getItemDimension();
        float f2 = (1.0f - (((float) itemDimension) / ((float) measuredHeight))) / 2.0f;
        float f3 = (1.0f + (((float) itemDimension) / ((float) measuredHeight))) / 2.0f;
        float f4 = ((float) this.m) * (1.0f - f);
        float f5 = (255.0f * f) + f4;
        int round;
        if (this.b == 2) {
            itemDimension = Math.round(f5) << 24;
            round = Math.round(f4) << 24;
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, (float) measuredHeight, new int[]{round, itemDimension, -16777216, -16777216, itemDimension, round}, new float[]{0.0f, f2, f2, f3, f3, 1.0f}, TileMode.CLAMP);
        } else {
            float f6 = (1.0f - (((float) (itemDimension * 3)) / ((float) measuredHeight))) / 2.0f;
            float f7 = ((((float) (itemDimension * 3)) / ((float) measuredHeight)) + 1.0f) / 2.0f;
            float f8 = ((255.0f * f6) / f2) * f;
            int round2 = Math.round(f5) << 24;
            round = Math.round(f4 + f8) << 24;
            int round3 = Math.round(f8) << 24;
            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, (float) measuredHeight, new int[]{0, round3, round, round2, -16777216, -16777216, round2, round, round3, 0}, new float[]{0.0f, f6, f6, f2, f2, f3, f3, f7, f7, 1.0f}, TileMode.CLAMP);
        }
        this.s.setShader(linearGradient);
        invalidate();
    }

    protected WheelScroller a(ScrollingListener scrollingListener) {
        return new WheelVerticalScroller(getContext(), scrollingListener);
    }

    protected float a(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    protected int getBaseDimension() {
        return getHeight();
    }

    protected int getItemDimension() {
        if (this.B != 0) {
            return this.B;
        }
        if (this.h == null || this.h.getChildAt(0) == null) {
            return getBaseDimension() / this.b;
        }
        this.B = this.h.getChildAt(0).getMeasuredHeight();
        return this.B;
    }

    protected void e() {
        if (this.h == null) {
            this.h = new LinearLayout(getContext());
            this.h.setOrientation(1);
        }
    }

    protected void f() {
        this.h.layout(0, 0, getMeasuredWidth() - (this.q * 2), getMeasuredHeight());
    }

    protected void j() {
        this.h.setLayoutParams(new LayoutParams(-2, -2));
        this.h.measure(MeasureSpec.makeMeasureSpec(getWidth() - (this.q * 2), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        i();
        size = c(size, mode);
        if (mode2 != 1073741824) {
            mode = Math.max(getItemDimension() * (this.b - (this.p / 100)), getSuggestedMinimumHeight());
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(mode, size2) : mode;
        }
        setMeasuredDimension(size, size2);
    }

    private int c(int i, int i2) {
        this.h.setLayoutParams(new LayoutParams(-2, -2));
        this.h.measure(MeasureSpec.makeMeasureSpec(i, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.h.getMeasuredWidth();
        if (i2 != 1073741824) {
            measuredWidth = Math.max(measuredWidth + (this.q * 2), getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= measuredWidth) {
                i = measuredWidth;
            }
        }
        this.h.measure(MeasureSpec.makeMeasureSpec(i - (this.q * 2), 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    protected void a(Canvas canvas) {
        canvas.save();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int itemDimension = getItemDimension();
        this.w.eraseColor(0);
        Canvas canvas2 = new Canvas(this.w);
        Canvas canvas3 = new Canvas(this.w);
        canvas2.translate((float) this.q, (float) ((-(((this.a - this.i) * itemDimension) + ((itemDimension - getHeight()) / 2))) + this.g));
        this.h.draw(canvas2);
        this.x.eraseColor(0);
        Canvas canvas4 = new Canvas(this.x);
        if (this.r != null) {
            int height = ((getHeight() - itemDimension) - this.y) / 2;
            int i = this.y + height;
            this.r.setBounds(0, height, measuredWidth, i);
            this.r.draw(canvas4);
            this.r.setBounds(0, height + itemDimension, measuredWidth, itemDimension + i);
            this.r.draw(canvas4);
        }
        canvas3.drawRect(0.0f, 0.0f, (float) measuredWidth, (float) measuredHeight, this.s);
        canvas4.drawRect(0.0f, 0.0f, (float) measuredWidth, (float) measuredHeight, this.t);
        canvas.drawBitmap(this.w, 0.0f, 0.0f, null);
        canvas.drawBitmap(this.x, 0.0f, 0.0f, null);
        canvas.restore();
    }
}
