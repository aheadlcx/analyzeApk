package com.budejie.www.activity.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import com.budejie.www.R;

public class MICView extends View {
    Bitmap a;
    Bitmap b;
    Bitmap c;
    Matrix d;
    private int e = 0;
    private float f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;

    public MICView(Context context) {
        super(context);
        a();
    }

    public MICView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.a = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mic_normal);
        this.b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mic_wave);
        this.h = this.b.getWidth();
        this.i = this.b.getHeight();
        this.j = this.a.getWidth();
        this.k = this.a.getHeight();
        this.l = (this.j - this.h) / 2;
    }

    public void setAF(int i) {
        this.e = i;
        this.f = ((float) this.i) * 0.05f;
        this.e = (int) (this.f * ((float) this.e));
        Log.i("progress", "--->" + this.e);
        if (this.e > this.i) {
            this.e = this.i;
        } else if (this.e < 0) {
            this.e = 0;
        }
        this.g = this.i - this.e;
        this.d = new Matrix();
        this.d.preScale(1.0f, -1.0f);
        if (this.e > 0) {
            this.c = Bitmap.createBitmap(this.b, 0, 0, this.h, this.e, this.d, false);
        }
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.m = MeasureSpec.getSize(i);
        this.n = MeasureSpec.getSize(i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = (this.m / 2) - (this.j / 2);
        int i2 = (this.n / 2) - (this.k / 2);
        Paint paint = new Paint();
        canvas.drawBitmap(this.a, (float) i, (float) i2, paint);
        if (this.e > 0) {
            canvas.drawBitmap(this.c, (float) (i + this.l), (float) (i2 + this.g), paint);
        }
    }
}
