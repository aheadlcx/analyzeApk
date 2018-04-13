package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import cn.v6.sixrooms.R$styleable;

public class StrokeTextView extends TextView {
    public static final int maxLenght = 5;
    private float a;
    private float b;
    private String c;
    private Paint d;
    private int e = 3;
    private int f = -16777216;

    public StrokeTextView(Context context) {
        super(context);
        a();
    }

    public StrokeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.StrokeTextView);
        this.f = obtainStyledAttributes.getColor(R$styleable.StrokeTextView_stroke_color, -16777216);
        this.e = obtainStyledAttributes.getDimensionPixelSize(R$styleable.StrokeTextView_stroke_width, 1);
        obtainStyledAttributes.recycle();
        a();
    }

    public StrokeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.StrokeTextView, i, 0);
        this.f = obtainStyledAttributes.getColor(R$styleable.StrokeTextView_stroke_color, -16777216);
        this.e = obtainStyledAttributes.getDimensionPixelSize(R$styleable.StrokeTextView_stroke_width, 1);
        obtainStyledAttributes.recycle();
        a();
    }

    private void a() {
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setTextSize(getTextSize());
        this.d.setColor(this.f);
        FontMetrics fontMetrics = this.d.getFontMetrics();
        this.a = fontMetrics.bottom;
        this.b = fontMetrics.bottom - fontMetrics.top;
        getPaddingLeft();
        getPaddingTop();
        getPaddingRight();
        getPaddingBottom();
    }

    protected void onDraw(Canvas canvas) {
        if (this.e > 0) {
            float paddingTop = (((float) getPaddingTop()) + this.b) - this.a;
            canvas.drawText(this.c, 0.0f, paddingTop - ((float) this.e), this.d);
            canvas.drawText(this.c, 0.0f, ((float) this.e) + paddingTop, this.d);
            canvas.drawText(this.c, (float) (this.e + 0), paddingTop, this.d);
            canvas.drawText(this.c, (float) (this.e + 0), ((float) this.e) + paddingTop, this.d);
            canvas.drawText(this.c, (float) (this.e + 0), paddingTop - ((float) this.e), this.d);
            canvas.drawText(this.c, (float) (0 - this.e), paddingTop, this.d);
            canvas.drawText(this.c, (float) (0 - this.e), ((float) this.e) + paddingTop, this.d);
            canvas.drawText(this.c, (float) (0 - this.e), paddingTop - ((float) this.e), this.d);
        }
        super.onDraw(canvas);
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        if (this.e > 0) {
            if (charSequence.length() > 5) {
                charSequence = charSequence.subSequence(0, 5) + "...";
            }
            super.setText(charSequence, bufferType);
            this.c = charSequence.toString();
            invalidate();
            return;
        }
        super.setText(charSequence, bufferType);
        this.c = charSequence.toString();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.e > 0 && this.e < 4) {
            setMeasuredDimension(getMeasuredWidth() + this.e, getMeasuredHeight());
        }
    }
}
