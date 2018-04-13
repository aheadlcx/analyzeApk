package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import c.a.i.i;
import cn.xiaochuankeji.tieba.R;
import java.lang.ref.WeakReference;

public class e extends i {
    private Paint a;
    private Xfermode b;
    private Bitmap c;
    private WeakReference<Bitmap> d;
    private int e;
    private int f;
    private Drawable g;

    public e(Context context) {
        this(context, null);
    }

    public e(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new PorterDuffXfermode(Mode.DST_IN);
        this.e = -1;
        this.f = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageViewByXfermode);
        this.e = obtainStyledAttributes.getInt(2, -1);
        this.f = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.g = obtainStyledAttributes.getDrawable(1);
        obtainStyledAttributes.recycle();
        if (this.e != -1) {
            this.a = new Paint();
            this.a.setAntiAlias(true);
        }
    }

    public void setOverlayResource(int i) {
        this.g = getContext().getResources().getDrawable(i);
        if (this.g != null) {
            this.g.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.e != -1 && this.e == 0) {
            int min = Math.min(getMeasuredWidth(), getMeasuredHeight());
            setMeasuredDimension(min, min);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.g != null) {
            this.g.setBounds(0, 0, i, i2);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.e == -1 || this.g != null) {
            super.onDraw(canvas);
            if (this.g != null) {
                this.g.draw(canvas);
                return;
            }
            return;
        }
        Bitmap bitmap;
        if (this.d == null) {
            bitmap = null;
        } else {
            bitmap = (Bitmap) this.d.get();
        }
        if (bitmap == null || bitmap.isRecycled()) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                float max;
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                if (this.e == 1) {
                    max = Math.max((((float) getWidth()) * 1.0f) / ((float) intrinsicWidth), (((float) getHeight()) * 1.0f) / ((float) intrinsicHeight));
                } else {
                    max = (((float) getWidth()) * 1.0f) / ((float) Math.min(intrinsicWidth, intrinsicHeight));
                }
                drawable.setBounds(0, 0, (int) (((float) intrinsicWidth) * max), (int) (max * ((float) intrinsicHeight)));
                drawable.draw(canvas2);
                if (this.c == null || this.c.isRecycled()) {
                    this.c = getBitmap();
                }
                this.a.reset();
                this.a.setFilterBitmap(false);
                this.a.setXfermode(this.b);
                canvas2.drawBitmap(this.c, 0.0f, 0.0f, this.a);
                this.a.setXfermode(null);
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, null);
                this.d = new WeakReference(createBitmap);
                bitmap = createBitmap;
            }
        }
        if (bitmap != null) {
            this.a.setXfermode(null);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.a);
        }
    }

    public Bitmap getBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        if (this.e == 1) {
            canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), (float) this.f, (float) this.f, paint);
        } else {
            canvas.drawCircle((float) (getWidth() / 2), (float) (getWidth() / 2), (float) (getWidth() / 2), paint);
        }
        return createBitmap;
    }

    public void invalidate() {
        if (this.e == -1) {
            super.invalidate();
            return;
        }
        this.d = null;
        if (this.c != null) {
            this.c.recycle();
            this.c = null;
        }
        super.invalidate();
    }
}
