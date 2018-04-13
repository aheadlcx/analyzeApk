package qsbk.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import qsbk.app.R;

public class MagicTextView extends TextView {
    private ArrayList<Shadow> a;
    private ArrayList<Shadow> b;
    private WeakHashMap<String, Pair<Canvas, Bitmap>> c;
    private Canvas d;
    private Bitmap e;
    private Drawable f;
    private float g;
    private Integer h;
    private Join i;
    private float j;
    private int[] k;
    private boolean l = false;
    private PaintFlagsDrawFilter m = new PaintFlagsDrawFilter(0, 3);
    private PorterDuffXfermode n = new PorterDuffXfermode(Mode.SRC_ATOP);
    private PorterDuffXfermode o = new PorterDuffXfermode(Mode.DST_OUT);

    public static class Shadow {
        float a;
        float b;
        float c;
        int d;

        public Shadow(float f, float f2, float f3, int i) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = i;
        }
    }

    public MagicTextView(Context context) {
        super(context);
        init(null);
    }

    public MagicTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public MagicTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public void init(AttributeSet attributeSet) {
        this.a = new ArrayList();
        this.b = new ArrayList();
        if (this.c == null) {
            this.c = new WeakHashMap();
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MagicTextView);
            if (obtainStyledAttributes.getString(8) != null) {
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), String.format("fonts/%s.ttf", new Object[]{r0})));
            }
            if (obtainStyledAttributes.hasValue(9)) {
                Drawable drawable = obtainStyledAttributes.getDrawable(9);
                if (drawable != null) {
                    setForegroundDrawable(drawable);
                } else {
                    setTextColor(obtainStyledAttributes.getColor(9, -16777216));
                }
            }
            if (obtainStyledAttributes.hasValue(0)) {
                addInnerShadow((float) obtainStyledAttributes.getDimensionPixelSize(1, 0), (float) obtainStyledAttributes.getDimensionPixelOffset(2, 0), (float) obtainStyledAttributes.getDimensionPixelOffset(3, 0), obtainStyledAttributes.getColor(0, -16777216));
            }
            if (obtainStyledAttributes.hasValue(4)) {
                addOuterShadow((float) obtainStyledAttributes.getDimensionPixelSize(5, 0), (float) obtainStyledAttributes.getDimensionPixelOffset(6, 0), (float) obtainStyledAttributes.getDimensionPixelOffset(7, 0), obtainStyledAttributes.getColor(4, -16777216));
            }
            if (obtainStyledAttributes.hasValue(12)) {
                float dimensionPixelSize = (float) obtainStyledAttributes.getDimensionPixelSize(10, 1);
                int color = obtainStyledAttributes.getColor(12, -16777216);
                float dimensionPixelSize2 = (float) obtainStyledAttributes.getDimensionPixelSize(11, 10);
                Join join = null;
                switch (obtainStyledAttributes.getInt(13, 0)) {
                    case 0:
                        join = Join.MITER;
                        break;
                    case 1:
                        join = Join.BEVEL;
                        break;
                    case 2:
                        join = Join.ROUND;
                        break;
                }
                setStroke(dimensionPixelSize, color, join, dimensionPixelSize2);
            }
            obtainStyledAttributes.recycle();
        }
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (this.b.size() > 0 || this.f != null) {
            setLayerType(1, null);
        }
    }

    public void setStroke(float f, int i, Join join, float f2) {
        this.g = f;
        this.h = Integer.valueOf(i);
        this.i = join;
        this.j = f2;
    }

    public void setStroke(float f, int i) {
        setStroke(f, i, Join.MITER, 10.0f);
    }

    public void addOuterShadow(float f, float f2, float f3, int i) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.a.add(new Shadow(f, f2, f3, i));
    }

    public void addInnerShadow(float f, float f2, float f3, int i) {
        if (f == 0.0f) {
            f = 1.0E-4f;
        }
        this.b.add(new Shadow(f, f2, f3, i));
    }

    public void clearInnerShadows() {
        this.b.clear();
    }

    public void clearOuterShadows() {
        this.a.clear();
    }

    public void setForegroundDrawable(Drawable drawable) {
        this.f = drawable;
    }

    public Drawable getForeground() {
        return this.f == null ? this.f : new ColorDrawable(getCurrentTextColor());
    }

    public void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.m);
        super.onDraw(canvas);
        freeze();
        Drawable background = getBackground();
        Drawable[] compoundDrawables = getCompoundDrawables();
        int currentTextColor = getCurrentTextColor();
        setCompoundDrawables(null, null, null, null);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            Shadow shadow = (Shadow) it.next();
            setShadowLayer(shadow.a, shadow.b, shadow.c, shadow.d);
            super.onDraw(canvas);
        }
        setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        setTextColor(currentTextColor);
        if (this.f != null && (this.f instanceof BitmapDrawable)) {
            a();
            super.onDraw(this.d);
            ((BitmapDrawable) this.f).getPaint().setXfermode(this.n);
            this.f.setBounds(canvas.getClipBounds());
            this.f.draw(this.d);
            canvas.drawBitmap(this.e, 0.0f, 0.0f, null);
            this.d.drawColor(0, Mode.CLEAR);
        }
        if (this.h != null) {
            TextPaint paint = getPaint();
            paint.setAntiAlias(true);
            paint.setStyle(Style.STROKE);
            paint.setStrokeJoin(this.i);
            paint.setStrokeMiter(this.j);
            setTextColor(this.h.intValue());
            paint.setStrokeWidth(this.g);
            super.onDraw(canvas);
            paint.setStyle(Style.FILL);
            setTextColor(currentTextColor);
        }
        if (this.b.size() > 0) {
            a();
            TextPaint paint2 = getPaint();
            Iterator it2 = this.b.iterator();
            while (it2.hasNext()) {
                shadow = (Shadow) it2.next();
                setTextColor(shadow.d);
                super.onDraw(this.d);
                setTextColor(-16777216);
                paint2.setAntiAlias(true);
                paint2.setXfermode(this.o);
                paint2.setMaskFilter(new BlurMaskFilter(shadow.a, Blur.NORMAL));
                this.d.save();
                this.d.translate(shadow.b, shadow.c);
                super.onDraw(this.d);
                this.d.restore();
                canvas.drawBitmap(this.e, 0.0f, 0.0f, null);
                this.d.drawColor(0, Mode.CLEAR);
                paint2.setXfermode(null);
                paint2.setMaskFilter(null);
                setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            }
        }
        if (compoundDrawables != null) {
            setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
        setBackgroundDrawable(background);
        setTextColor(currentTextColor);
        unfreeze();
    }

    private void a() {
        String format = String.format("%dx%d", new Object[]{Integer.valueOf(getWidth()), Integer.valueOf(getHeight())});
        Pair pair = (Pair) this.c.get(format);
        if (pair != null) {
            this.d = (Canvas) pair.first;
            this.e = (Bitmap) pair.second;
            return;
        }
        this.d = new Canvas();
        this.e = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        this.d.setBitmap(this.e);
        this.c.put(format, new Pair(this.d, this.e));
    }

    public void freeze() {
        this.k = new int[]{getCompoundPaddingLeft(), getCompoundPaddingRight(), getCompoundPaddingTop(), getCompoundPaddingBottom()};
        this.l = true;
    }

    public void unfreeze() {
        this.l = false;
    }

    public void requestLayout() {
        if (!this.l) {
            super.requestLayout();
        }
    }

    public void postInvalidate() {
        if (!this.l) {
            super.postInvalidate();
        }
    }

    public void postInvalidate(int i, int i2, int i3, int i4) {
        if (!this.l) {
            super.postInvalidate(i, i2, i3, i4);
        }
    }

    public void invalidate() {
        if (!this.l) {
            super.invalidate();
        }
    }

    public void invalidate(Rect rect) {
        if (!this.l) {
            super.invalidate(rect);
        }
    }

    public void invalidate(int i, int i2, int i3, int i4) {
        if (!this.l) {
            super.invalidate(i, i2, i3, i4);
        }
    }

    public int getCompoundPaddingLeft() {
        return !this.l ? super.getCompoundPaddingLeft() : this.k[0];
    }

    public int getCompoundPaddingRight() {
        return !this.l ? super.getCompoundPaddingRight() : this.k[1];
    }

    public int getCompoundPaddingTop() {
        return !this.l ? super.getCompoundPaddingTop() : this.k[2];
    }

    public int getCompoundPaddingBottom() {
        return !this.l ? super.getCompoundPaddingBottom() : this.k[3];
    }
}
