package com.tencent.bugly.beta.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.TextView;
import com.tencent.bugly.beta.global.a;
import com.tencent.bugly.beta.global.c;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.proguard.an;

public class d implements OnPreDrawListener {
    final int a;
    final Object[] b;
    long c;
    StringBuilder d = new StringBuilder().append("loading");

    public d(int i, Object... objArr) {
        this.a = i;
        this.b = objArr;
    }

    public boolean onPreDraw() {
        try {
            int intValue;
            int measuredWidth;
            switch (this.a) {
                case 1:
                    h hVar = (h) this.b[0];
                    TextView textView = (TextView) this.b[1];
                    Bitmap bitmap = (Bitmap) this.b[2];
                    intValue = ((Integer) this.b[3]).intValue();
                    measuredWidth = textView.getMeasuredWidth();
                    int i = (int) (0.42857142857142855d * ((double) measuredWidth));
                    textView.setHeight(i);
                    if (hVar.u == null) {
                        if (intValue == 2) {
                            hVar.u = a.a(bitmap, measuredWidth, i, (float) a.a(e.E.s, 6.0f));
                        } else {
                            hVar.u = a.a(bitmap, measuredWidth, i, (float) a.a(e.E.s, 0.0f));
                        }
                        if (hVar.u != null) {
                            textView.setText("");
                            textView.setBackgroundDrawable(hVar.u);
                            textView.getViewTreeObserver().removeOnPreDrawListener(this);
                            return true;
                        }
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.c > 300) {
                        this.c = currentTimeMillis;
                        if (this.d.length() > 9) {
                            this.d = new StringBuilder().append("loading");
                        } else {
                            this.d.append(".");
                        }
                        textView.setText(this.d.toString());
                    }
                    return true;
                case 2:
                    if (((Integer) this.b[2]).intValue() > 0) {
                        int intValue2 = ((Integer) this.b[0]).intValue();
                        TextView textView2 = (TextView) this.b[1];
                        intValue = textView2.getMeasuredWidth();
                        measuredWidth = textView2.getMeasuredHeight();
                        int i2 = (int) (((float) (e.E.B.widthPixels * e.E.B.heightPixels)) * 0.4f);
                        if (!(intValue == 0 || measuredWidth == 0 || intValue * measuredWidth > i2)) {
                            this.b[2] = Integer.valueOf(0);
                            Paint paint = new Paint();
                            paint.setStyle(Style.FILL);
                            paint.setAntiAlias(true);
                            if (intValue2 == 2) {
                                i2 = 8;
                                intValue2 = 7;
                            } else {
                                intValue2 = 0;
                                i2 = 0;
                            }
                            paint.setColor(-3355444);
                            Bitmap createBitmap = Bitmap.createBitmap(intValue, measuredWidth, Config.ARGB_8888);
                            Canvas canvas = new Canvas(createBitmap);
                            RectF rectF = new RectF(0.0f, 0.0f, (float) intValue, (float) measuredWidth);
                            canvas.drawRoundRect(rectF, (float) i2, (float) i2, paint);
                            paint.setColor(-1);
                            canvas.drawRoundRect(new RectF(2.0f, 2.0f, ((float) intValue) - 2.0f, ((float) measuredWidth) - 2.0f), (float) intValue2, (float) intValue2, paint);
                            paint.setColor(-3355444);
                            Bitmap createBitmap2 = Bitmap.createBitmap(intValue, measuredWidth, Config.ARGB_8888);
                            new Canvas(createBitmap2).drawRoundRect(rectF, (float) i2, (float) i2, paint);
                            Drawable bitmapDrawable = new BitmapDrawable(createBitmap);
                            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(createBitmap2);
                            textView2.setBackgroundDrawable(bitmapDrawable);
                            textView2.setOnTouchListener(new c(1, bitmapDrawable2, bitmapDrawable));
                        }
                    }
                    return true;
                case 3:
                    ViewGroup viewGroup = (ViewGroup) this.b[0];
                    if (viewGroup.getMeasuredHeight() > a.a((Context) this.b[1], 158.0f)) {
                        LayoutParams layoutParams = viewGroup.getLayoutParams();
                        layoutParams.height = a.a((Context) this.b[1], 200.0f);
                        viewGroup.setLayoutParams(layoutParams);
                    }
                    return true;
            }
        } catch (Throwable e) {
            if (!an.b(e)) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
