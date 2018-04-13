package com.handmark.pulltorefresh.library.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.LruCache;
import android.view.View;
import android.view.View.MeasureSpec;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.utils.DensityUtil;

public class SixroomRefreshPullView extends View {
    private int a;
    private int b;
    private int c;
    private Bitmap d;
    private Bitmap e;
    private float f;
    private LruCache<String, Bitmap> g;
    private int[] h = new int[]{R.drawable.down_001, R.drawable.down_001, R.drawable.down_001, R.drawable.down_001, R.drawable.down_002, R.drawable.down_002, R.drawable.down_002, R.drawable.down_003, R.drawable.down_003, R.drawable.down_004, R.drawable.down_005, R.drawable.down_006, R.drawable.down_007, R.drawable.down_008, R.drawable.down_009, R.drawable.down_010, R.drawable.down_011, R.drawable.down_012};
    private int i;
    private Paint j;
    private float k;

    public SixroomRefreshPullView(Context context) {
        super(context);
        a();
    }

    public SixroomRefreshPullView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    @TargetApi(12)
    private void a() {
        this.g = new d(this);
        this.k = DensityUtil.getScreenDensity(getContext());
        this.d = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), this.h[0]));
        this.e = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.loading_000));
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setColor(-16777216);
        this.j.setTextSize((float) DensityUtil.dip2px(getContext(), 13.0f));
    }

    @TargetApi(12)
    private Bitmap a(String str) {
        return (Bitmap) this.g.get(str);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i), (a(i) * this.e.getHeight()) / this.e.getWidth());
    }

    private int a(int i) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        if (mode == 1073741824) {
            return size;
        }
        int width = this.e.getWidth();
        return mode == Integer.MIN_VALUE ? Math.min(width, size) : width;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.b = getMeasuredWidth();
        this.c = getMeasuredHeight();
        this.a = this.c;
        this.i = this.a / this.h.length;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.d = getBitmap();
        int dip2px = DensityUtil.dip2px(getContext(), 7.0f);
        canvas.drawBitmap(this.d, (float) DensityUtil.dip2px(getContext(), 35.0f), (float) dip2px, null);
        if (((double) this.f) < 0.7d) {
            canvas.drawText("下拉可刷新...", ((float) this.b) * 0.5f, ((float) this.c) * 0.85f, this.j);
        } else {
            canvas.drawText("松手刷新...", ((float) this.b) * 0.5f, ((float) this.c) * 0.85f, this.j);
        }
    }

    @SuppressLint({"NewApi"})
    public void setCurrentProgress(float f) {
        if (f >= 0.3f && f <= 1.3f) {
            this.f = f - 0.3f;
            setAlpha(this.f);
            postInvalidate();
        }
    }

    @SuppressLint({"NewApi"})
    public void over() {
        this.f = 0.0f;
        setAlpha(this.f);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            this.g.evictAll();
        }
    }

    private Bitmap getBitmap() {
        int abs = Math.abs(((int) (this.f * ((float) this.a))) / this.i);
        if (abs >= this.h.length) {
            abs = this.h.length - 1;
        }
        Bitmap a = a(String.valueOf(abs));
        if (a == null) {
            a = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), this.h[abs]));
            String valueOf = String.valueOf(abs);
            if (a(valueOf) == null) {
                this.g.put(valueOf, a);
            }
        }
        return a;
    }
}
