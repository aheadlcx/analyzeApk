package cn.xiaochuankeji.tieba.ui.hollow.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.R;
import com.izuiyou.a.a.b;

public class CardView extends LinearLayout {
    private float a;
    private float b;
    private float c;
    private int d;
    private int e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float[] k;
    private int l;
    private int m;
    private Paint n;
    private Path o;

    public CardView(Context context) {
        super(context);
        a(context, null);
    }

    public CardView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public CardView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        b(context, attributeSet);
        a();
        b();
        c();
    }

    private void b(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CardView);
        this.a = obtainStyledAttributes.getDimension(14, 0.0f);
        this.b = obtainStyledAttributes.getDimension(15, 0.0f);
        this.c = obtainStyledAttributes.getDimension(16, 0.0f);
        this.d = obtainStyledAttributes.getColor(13, 1143219236);
        this.e = obtainStyledAttributes.getColor(17, 0);
        this.f = obtainStyledAttributes.getDimension(18, 0.0f);
        this.g = obtainStyledAttributes.getDimension(19, 0.0f);
        this.h = obtainStyledAttributes.getDimension(20, 0.0f);
        this.i = obtainStyledAttributes.getDimension(21, 0.0f);
        this.j = obtainStyledAttributes.getDimension(22, 0.0f);
    }

    private void a() {
        setLayerType(1, null);
        setBackgroundColor(0);
    }

    private void b() {
        this.k = new float[8];
        this.k[0] = this.g == 0.0f ? this.f : this.g;
        this.k[1] = this.g == 0.0f ? this.f : this.g;
        this.k[2] = this.h == 0.0f ? this.f : this.h;
        this.k[3] = this.h == 0.0f ? this.f : this.h;
        this.k[4] = this.i == 0.0f ? this.f : this.i;
        this.k[5] = this.i == 0.0f ? this.f : this.i;
        this.k[6] = this.j == 0.0f ? this.f : this.j;
        this.k[7] = this.j == 0.0f ? this.f : this.j;
    }

    private void c() {
        this.n = new Paint(1);
        this.n.setColor(this.e);
        this.n.setShadowLayer(this.a, this.b, this.c, this.d);
        this.o = new Path();
    }

    protected void onMeasure(int i, int i2) {
        b.c("measure");
        this.l = MeasureSpec.getSize(i2);
        this.m = MeasureSpec.getSize(i);
        d();
        super.onMeasure(i, i2);
    }

    private void d() {
        this.o.addRoundRect(new RectF(this.a, this.a, ((float) this.m) - this.a, ((float) this.l) - this.a), this.k, Direction.CW);
    }

    protected void onDraw(Canvas canvas) {
        a(canvas);
        super.onDraw(canvas);
    }

    private void a(Canvas canvas) {
        b.c("draw");
        canvas.drawPath(this.o, this.n);
        canvas.clipPath(this.o);
        canvas.drawPath(this.o, this.n);
    }
}
