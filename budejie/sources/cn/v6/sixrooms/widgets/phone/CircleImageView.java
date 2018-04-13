package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import cn.v6.sixrooms.R$styleable;

public class CircleImageView extends ImageView {
    private static final ScaleType a = ScaleType.CENTER_CROP;
    private static final Config b = Config.ARGB_8888;
    private final RectF c;
    private final RectF d;
    private final Matrix e;
    private final Paint f;
    private final Paint g;
    private int h;
    private int i;
    private Bitmap j;
    private BitmapShader k;
    private int l;
    private int m;
    private float n;
    private float o;
    private boolean p;
    private boolean q;

    public CircleImageView(Context context) {
        super(context);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = -16777216;
        this.i = 0;
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new RectF();
        this.d = new RectF();
        this.e = new Matrix();
        this.f = new Paint();
        this.g = new Paint();
        this.h = -16777216;
        this.i = 0;
        super.setScaleType(a);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CircleImageView, i, 0);
        this.i = obtainStyledAttributes.getDimensionPixelSize(R$styleable.CircleImageView_border_width, 0);
        this.h = obtainStyledAttributes.getColor(R$styleable.CircleImageView_border_color, -16777216);
        obtainStyledAttributes.recycle();
        this.p = true;
        if (this.q) {
            a();
            this.q = false;
        }
    }

    public ScaleType getScaleType() {
        return a;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.o, this.g);
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.n, this.f);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
    }

    public int getBorderColor() {
        return this.h;
    }

    public void setBorderColor(int i) {
        if (i != this.h) {
            this.h = i;
            this.g.setColor(this.h);
            invalidate();
        }
    }

    public int getBorderWidth() {
        return this.i;
    }

    public void setBorderWidth(int i) {
        if (i != this.i) {
            this.i = i;
            a();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.j = bitmap;
        a();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.j = a(drawable);
        a();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        this.j = a(getDrawable());
        a();
    }

    private static Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap;
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(1, 1, b);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), b);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void a() {
        float f = 0.0f;
        if (!this.p) {
            this.q = true;
        } else if (this.j != null) {
            float height;
            float width;
            this.k = new BitmapShader(this.j, TileMode.CLAMP, TileMode.CLAMP);
            this.f.setAntiAlias(true);
            this.f.setShader(this.k);
            this.g.setAntiAlias(true);
            this.g.setColor(this.h);
            this.g.setStrokeWidth((float) this.i);
            this.m = this.j.getHeight();
            this.l = this.j.getWidth();
            this.d.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.o = Math.min((this.d.height() - ((float) this.i)) / 2.0f, (this.d.width() - ((float) this.i)) / 2.0f);
            this.c.set((float) this.i, (float) this.i, this.d.width() - ((float) this.i), this.d.height() - ((float) this.i));
            this.n = Math.min(this.c.height() / 2.0f, this.c.width() / 2.0f);
            this.e.set(null);
            if (((float) this.l) * this.c.height() > this.c.width() * ((float) this.m)) {
                height = this.c.height() / ((float) this.m);
                width = (this.c.width() - (((float) this.l) * height)) * 0.5f;
            } else {
                height = this.c.width() / ((float) this.l);
                width = 0.0f;
                f = (this.c.height() - (((float) this.m) * height)) * 0.5f;
            }
            this.e.setScale(height, height);
            this.e.postTranslate((float) (((int) (width + 0.5f)) + this.i), (float) (((int) (f + 0.5f)) + this.i));
            this.k.setLocalMatrix(this.e);
            invalidate();
        }
    }
}
