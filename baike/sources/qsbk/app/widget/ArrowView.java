package qsbk.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import qsbk.app.widget.OverScrollView.LoadMoreListener;
import qsbk.app.widget.OverScrollView.OnScrollListener;

public class ArrowView extends View {
    private static final String a = ArrowView.class.getSimpleName();
    private Paint b;
    private Path c;
    private Point d = new Point();
    private Point e = new Point();
    private Point f = new Point();
    private int g = 40;
    private float h = 0.34906584f;
    private double i = (((double) (this.g / 2)) * Math.tan((double) this.h));
    private double j = this.i;
    private String k = "上滑跳过";
    private TextPaint l;
    private int m;
    private int n = 0;
    private double o = (255.0d / (this.i * 2.0d));

    private class a implements LoadMoreListener, OnScrollListener {
        final OnScrollListener a;
        final LoadMoreListener b;
        final /* synthetic */ ArrowView c;

        a(ArrowView arrowView, OnScrollListener onScrollListener, LoadMoreListener loadMoreListener) {
            this.c = arrowView;
            this.a = onScrollListener;
            this.b = loadMoreListener;
        }

        public void onScroll(OverScrollView overScrollView, int i, boolean z, boolean z2) {
            if (this.a != null) {
                this.a.onScroll(overScrollView, i, z, z2);
            }
            if (overScrollView.getScrollY() != 0 && z2) {
                this.c.setOffset(i);
            }
        }

        public void onScrollChanged(OverScrollView overScrollView, int i, int i2, int i3, int i4) {
            if (this.a != null) {
                this.a.onScrollChanged(overScrollView, i, i2, i3, i4);
            }
            if (i2 == 0) {
                this.c.b();
            }
        }

        public void onLoadMore() {
            if (this.b != null) {
                this.b.onLoadMore();
            }
            this.c.b();
        }
    }

    public ArrowView(Context context) {
        super(context);
        a();
    }

    public ArrowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ArrowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    @TargetApi(21)
    public ArrowView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    private void a() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int applyDimension = (int) TypedValue.applyDimension(1, 2.0f, displayMetrics);
        this.g = ((int) TypedValue.applyDimension(1, 8.0f, displayMetrics)) * 2;
        this.i = ((double) (this.g / 2)) * Math.tan((double) this.h);
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setColor(-3421237);
        this.b.setStyle(Style.STROKE);
        this.b.setStrokeWidth((float) applyDimension);
        this.c = new Path();
        this.l = new TextPaint();
        this.l.setColor(13355979);
        this.l.setAntiAlias(true);
        this.l.setTextSize(36.0f);
    }

    public void trackOverScrollView(OverScrollView overScrollView) {
        if (overScrollView == null) {
            Log.e(a, "trackOverScrollView: OverScrollView is null.");
        } else {
            overScrollView.setOnScrollListener(new a(this, overScrollView.getOnScrollListener(), overScrollView.getLoadMoreListener()));
        }
    }

    private void b() {
        this.i = ((double) (this.g / 2)) * Math.tan((double) this.h);
        this.j = -this.i;
        this.d.y = (int) (((double) this.e.y) + this.j);
        this.n = 0;
        invalidate();
    }

    private void setOffset(int i) {
        this.j = ((double) (((float) i) / 20.0f)) + this.j;
        double d = this.j;
        if (d > this.i) {
            d = this.i;
        } else if (d < (-this.i)) {
            d = -this.i;
        }
        this.n = ((int) ((this.j + this.i) * this.o)) * 8;
        this.n = Math.max(0, Math.min(this.n, 255));
        this.d.y = (int) (d + ((double) this.e.y));
        postInvalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = (i3 + i) / 2;
        int i6 = (i2 + i4) / 2;
        this.d.x = i5;
        Point point = this.f;
        this.e.y = i6;
        point.y = i6;
        this.e.x = i5 - (this.g / 2);
        this.f.x = (this.g / 2) + i5;
        this.m = i5 - ((int) (this.l.measureText(this.k) / 2.0f));
        b();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.c.reset();
        this.c.moveTo((float) this.e.x, (float) this.e.y);
        this.c.lineTo((float) this.d.x, (float) this.d.y);
        this.c.lineTo((float) this.f.x, (float) this.f.y);
        canvas.drawPath(this.c, this.b);
        this.l.setColor((this.l.getColor() & ViewCompat.MEASURED_SIZE_MASK) | (this.n << 24));
        canvas.drawText(this.k, (float) this.m, (float) (this.f.y + 48), this.l);
    }
}
