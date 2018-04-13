package qsbk.app.live.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.live.R;

public class SendContinueButton extends View {
    Path a;
    private float b;
    private float c;
    private int d;
    private int e;
    private Paint f;
    private Paint g;
    private float h;
    private int i;
    private Handler j;
    private Runnable k;
    private int l;
    private int[] m;
    public int mHeight;
    public int mStep;
    public int mWidth;
    private Bitmap n;
    private Canvas o;
    private Bitmap p;

    public SendContinueButton(Context context) {
        this(context, null);
    }

    public SendContinueButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SendContinueButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = Color.rgb(253, 170, 46);
        this.e = Color.rgb(253, 219, 46);
        this.h = 1.0f;
        this.i = WindowUtils.dp2Px(4);
        this.j = new Handler();
        this.a = new Path();
        this.mStep = WindowUtils.dp2Px(2);
        this.f = new Paint();
        this.f.setColor(this.d);
        this.f.setStyle(Style.FILL);
        this.f.setAntiAlias(true);
        this.f.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        this.g = new Paint();
        this.g.setColor(this.e);
        this.g.setStrokeWidth((float) this.i);
        this.g.setAntiAlias(true);
        this.p = BitmapFactory.decodeResource(getResources(), R.drawable.live_gift_highlight);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mWidth == 0) {
            this.mWidth = getWidth();
            this.mHeight = getHeight();
            this.b = (float) (this.mWidth / 2);
            this.c = this.b - ((float) this.i);
            a();
            this.n = Bitmap.createBitmap(this.mWidth, this.mHeight, Config.ARGB_8888);
            this.o = new Canvas(this.n);
            b();
        }
        this.g.setStyle(Style.FILL);
        canvas.drawBitmap(this.n, 0.0f, 0.0f, this.g);
        this.o.drawCircle(this.b, this.b, this.c, this.g);
        canvas.drawBitmap(this.p, 0.0f, 0.0f, new Paint());
        a(this.a, this.l, this.m);
        this.o.drawPath(this.a, this.f);
        this.g.setStyle(Style.STROKE);
        this.o.drawCircle(this.b, this.b, this.b - ((float) (this.i / 2)), this.g);
    }

    private void a(Path path, int i, int[] iArr) {
        path.reset();
        path.moveTo(0.0f, (float) this.mHeight);
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            path.lineTo((float) (this.mStep * i2), ((float) iArr[(i + i2) % length]) + (((float) this.mHeight) * (1.0f - this.h)));
        }
        path.lineTo((float) this.mWidth, ((float) iArr[((i + length) - 1) % length]) + (((float) this.mHeight) * (1.0f - this.h)));
        path.lineTo((float) this.mWidth, (float) this.mHeight);
        path.close();
    }

    public void setPercent(float f) {
        this.h = f;
    }

    private void a() {
        double d = (((double) this.mStep) * 3.141592653589793d) / ((double) this.mWidth);
        int dp2Px = WindowUtils.dp2Px(6);
        if (this.m == null) {
            this.m = new int[((this.mWidth * 2) / this.mStep)];
        }
        for (int i = 0; i < this.m.length; i++) {
            this.m[i] = (int) (((double) dp2Px) * Math.sin(((double) i) * d));
        }
        this.l = 0;
    }

    private void b() {
        if (this.k == null) {
            this.k = new im(this);
        }
        this.j.removeCallbacks(this.k);
        this.j.postDelayed(this.k, 50);
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i != 0 || this.mWidth == 0) {
            this.j.removeCallbacksAndMessages(null);
        } else {
            b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j.removeCallbacksAndMessages(null);
    }
}
