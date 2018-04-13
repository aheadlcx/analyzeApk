package cn.xiaochuankeji.tieba.ui.widget.text;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import c.a.i.b.e;
import c.a.i.b.i;
import c.a.i.u;
import cn.xiaochuankeji.tieba.R;

public class BadgeTextView extends AppCompatTextView implements c.a.i.a.a, u {
    private int a;
    private int b;
    private int c;
    private float d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private i j;

    private class a extends OvalShape {
        final /* synthetic */ BadgeTextView a;
        private Paint b = new Paint(1);
        private Paint c = new Paint(1);
        private int d;

        public a(BadgeTextView badgeTextView, int i, int i2) {
            this.a = badgeTextView;
            this.c.setStyle(Style.STROKE);
            this.c.setStrokeWidth(badgeTextView.d);
            this.c.setColor(badgeTextView.c);
            badgeTextView.e = i;
            this.d = i2;
            if (badgeTextView.e > 0) {
                this.b.setShader(new RadialGradient((float) (this.d / 2), (float) (this.d / 2), (float) badgeTextView.e, new int[]{1426063360, 0}, null, TileMode.CLAMP));
            }
        }

        public void draw(Canvas canvas, Paint paint) {
            float width = ((float) this.a.getWidth()) / 2.0f;
            float height = ((float) this.a.getHeight()) / 2.0f;
            canvas.drawCircle(width, height, ((float) ((this.d / 2) + this.a.e)) - (this.a.d / 2.0f), this.b);
            canvas.drawCircle(width, height, ((float) ((this.d / 2) + this.a.e)) - (this.a.d / 2.0f), this.c);
            canvas.drawCircle(width, height, (float) (this.d / 2), paint);
        }
    }

    class b extends Drawable {
        final /* synthetic */ BadgeTextView a;
        private final Paint b = new Paint(1);
        private Paint c = new Paint(1);
        private RectF d;

        public Paint a() {
            return this.b;
        }

        public b(BadgeTextView badgeTextView) {
            this.a = badgeTextView;
            this.c.setStyle(Style.STROKE);
            this.c.setStrokeWidth(badgeTextView.d);
            this.c.setColor(badgeTextView.c);
        }

        public void setBounds(int i, int i2, int i3, int i4) {
            super.setBounds(i, i2, i3, i4);
            int d = this.a.f / 2;
            if (this.d == null) {
                this.d = new RectF((float) (i + d), (float) ((this.a.e + i2) + 1), (float) (i3 - d), (float) (i4 - this.a.e));
            } else {
                this.d.set((float) (i + d), (float) ((this.a.e + i2) + 1), (float) (i3 - d), (float) (i4 - this.a.e));
            }
        }

        public void draw(Canvas canvas) {
            float height = this.d.height();
            canvas.drawRoundRect(this.d, height, height, this.b);
            canvas.drawRoundRect(this.d, height, height, this.c);
        }

        public void setAlpha(int i) {
            this.b.setAlpha(i);
        }

        public void setColorFilter(ColorFilter colorFilter) {
            this.b.setColorFilter(colorFilter);
        }

        public int getOpacity() {
            return -2;
        }
    }

    public BadgeTextView(Context context) {
        this(context, null);
    }

    public BadgeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BadgeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = i.a(this);
        this.j.a(attributeSet, i);
        this.c = c.a.d.a.a.a().a(R.color.CB);
        float applyDimension = TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics());
        this.d = applyDimension;
        this.e = (int) applyDimension;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SkinBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                this.a = obtainStyledAttributes.getResourceId(0, 0);
            }
            obtainStyledAttributes.recycle();
            this.a = e.b(this.a);
            if (this.a != 0) {
                if ("color".equals(c.a.d.a.a.a().c().getResourceTypeName(this.a))) {
                    this.b = c.a.d.a.a.a().a(this.a);
                }
                a(context, attributeSet);
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        setGravity(17);
        float textSize = getTextSize();
        this.f = (int) (Math.abs(textSize - (textSize / 4.0f)) / 2.0f);
        int i = (this.e * 2) + this.f;
        setPadding(i, this.e, i, this.e);
        if (this.b == 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BadgeTextView);
            this.b = obtainStyledAttributes.getColor(0, -65280);
            obtainStyledAttributes.recycle();
        }
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        Object trim = charSequence == null ? "" : charSequence.toString().trim();
        if (this.i && !"".equals(trim)) {
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = -2;
            layoutParams.width = -2;
            setLayoutParams(layoutParams);
            this.i = false;
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a(i, i2 - this.e);
    }

    private void a(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.g = i;
            this.h = i2;
            CharSequence text = getText();
            if (text == null) {
                return;
            }
            Drawable shapeDrawable;
            if (text.length() == 1) {
                shapeDrawable = new ShapeDrawable(new a(this, this.e, Math.max(i, i2) - (this.e * 2)));
                ViewCompat.setLayerType(this, 1, shapeDrawable.getPaint());
                shapeDrawable.getPaint().setColor(this.b);
                ViewCompat.setBackground(this, shapeDrawable);
            } else if (text.length() > 1) {
                shapeDrawable = new b(this);
                shapeDrawable.a().setColor(this.b);
                ViewCompat.setLayerType(this, 1, shapeDrawable.a());
                ViewCompat.setBackground(this, shapeDrawable);
            }
        }
    }

    public void setBadgeCount(String str) {
        a(str, false);
    }

    public void a(String str, boolean z) {
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception e) {
            parseInt = -1;
        }
        if (parseInt != -1) {
            a(parseInt, z);
        }
    }

    public void setBadgeCount(int i) {
        a(i, true);
    }

    public void a(int i, boolean z) {
        if (i > 0 && i <= 99) {
            setText(String.valueOf(i));
            setVisibility(0);
        } else if (i > 99) {
            setText("99+");
            setVisibility(0);
        } else if (i <= 0) {
            setText("0");
            if (z) {
                setVisibility(8);
            } else {
                setVisibility(0);
            }
        }
    }

    public void setBadgeText(String str) {
        if (TextUtils.isEmpty(str)) {
            setVisibility(8);
            return;
        }
        setText(str);
        setVisibility(0);
    }

    public void a() {
        setHighLightMode(false);
    }

    public void setHighLightMode(boolean z) {
        this.i = true;
        LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = a(getContext(), 8.0f);
        layoutParams.height = layoutParams.width;
        if (z && (layoutParams instanceof FrameLayout.LayoutParams)) {
            ((FrameLayout.LayoutParams) layoutParams).topMargin = a(getContext(), 8.0f);
            ((FrameLayout.LayoutParams) layoutParams).rightMargin = a(getContext(), 8.0f);
        }
        setLayoutParams(layoutParams);
        Drawable shapeDrawable = new ShapeDrawable(new OvalShape());
        ViewCompat.setLayerType(this, 1, shapeDrawable.getPaint());
        shapeDrawable.getPaint().setColor(this.b);
        shapeDrawable.getPaint().setAntiAlias(true);
        ViewCompat.setBackground(this, shapeDrawable);
        setText("");
        setVisibility(0);
    }

    public void setBackgroundColor(int i) {
        this.b = i;
        a(getWidth(), getHeight());
    }

    public void setTextColorResource(@ColorRes int i) {
        if (this.j != null) {
            this.j.a(i);
        }
    }

    public void setTextHintColorResource(int i) {
        if (this.j != null) {
            this.j.c(i);
        }
    }

    public void d() {
        if (this.j != null) {
            this.j.d();
        }
        this.c = c.a.d.a.a.a().a(R.color.CB);
        this.a = e.b(this.a);
        if (this.a != 0) {
            if ("color".equals(getResources().getResourceTypeName(this.a))) {
                this.b = c.a.d.a.a.a().a(this.a);
                if (this.i) {
                    a();
                } else {
                    a(this.g, this.h);
                }
            }
        }
    }

    public static int a(Context context, float f) {
        try {
            return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
        } catch (Exception e) {
            return (int) (f + 0.5f);
        }
    }
}
