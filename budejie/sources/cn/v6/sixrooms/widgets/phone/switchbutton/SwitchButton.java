package cn.v6.sixrooms.widgets.phone.switchbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import cn.v6.sixrooms.R$styleable;

public class SwitchButton extends CompoundButton {
    private static boolean a = false;
    private boolean b;
    private Configuration c;
    private Rect d;
    private Rect e;
    private Rect f;
    private RectF g;
    private a h;
    private a i;
    private boolean j;
    private float k;
    private float l;
    private float m;
    private float n;
    private int o;
    private int p;
    private Paint q;
    private Rect r;
    private OnCheckedChangeListener s;

    class a implements b {
        final /* synthetic */ SwitchButton a;

        a(SwitchButton switchButton) {
            this.a = switchButton;
        }

        public final void a() {
            this.a.j = true;
        }

        public final boolean b() {
            return this.a.f.right < this.a.d.right && this.a.f.left > this.a.d.left;
        }

        public final void a(int i) {
            this.a.a(i);
            this.a.postInvalidate();
        }

        public final void c() {
            this.a.setCheckedInClass(this.a.getStatusBasedOnPos());
            this.a.j = false;
        }
    }

    @SuppressLint({"NewApi"})
    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.i = new a(this);
        this.j = false;
        this.r = null;
        this.c = Configuration.getDefault(getContext().getResources().getDisplayMetrics().density);
        this.o = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.p = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
        this.h = a.a().a(this.i);
        this.r = new Rect();
        if (a) {
            this.q = new Paint();
            this.q.setStyle(Style.STROKE);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchButton);
        this.c.setThumbMarginInPixel(obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_margin, this.c.getDefaultThumbMarginInPixel()));
        this.c.setThumbMarginInPixel(obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_marginTop, this.c.getThumbMarginTop()), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_marginBottom, this.c.getThumbMarginBottom()), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_marginLeft, this.c.getThumbMarginLeft()), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_marginRight, this.c.getThumbMarginRight()));
        this.c.setRadius((float) obtainStyledAttributes.getInt(R$styleable.SwitchButton_radius, a.f));
        this.c.setThumbWidthAndHeightInPixel(obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_width, -1), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_thumb_height, -1));
        this.c.setMeasureFactor(obtainStyledAttributes.getFloat(R$styleable.SwitchButton_measureFactor, -1.0f));
        this.c.setInsetBounds(obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_insetLeft, 0), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_insetTop, 0), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_insetRight, 0), obtainStyledAttributes.getDimensionPixelSize(R$styleable.SwitchButton_insetBottom, 0));
        this.h.a(obtainStyledAttributes.getInteger(R$styleable.SwitchButton_animationVelocity, -1));
        if (this.c != null) {
            this.c.a(a(obtainStyledAttributes, R$styleable.SwitchButton_offDrawable, R$styleable.SwitchButton_offColor, a.a));
            this.c.b(a(obtainStyledAttributes, R$styleable.SwitchButton_onDrawable, R$styleable.SwitchButton_onColor, a.b));
            Configuration configuration = this.c;
            Drawable drawable = obtainStyledAttributes.getDrawable(R$styleable.SwitchButton_thumbDrawable);
            if (drawable == null) {
                int color = obtainStyledAttributes.getColor(R$styleable.SwitchButton_thumbColor, a.c);
                int color2 = obtainStyledAttributes.getColor(R$styleable.SwitchButton_thumbPressedColor, a.d);
                drawable = new StateListDrawable();
                Drawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(this.c.getRadius());
                gradientDrawable.setColor(color);
                Drawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(this.c.getRadius());
                gradientDrawable2.setColor(color2);
                drawable.addState(View.PRESSED_ENABLED_STATE_SET, gradientDrawable2);
                drawable.addState(new int[0], gradientDrawable);
            }
            configuration.setThumbDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwitchButton(Context context) {
        this(context, null);
    }

    private Drawable a(TypedArray typedArray, int i, int i2, int i3) {
        Drawable drawable = typedArray.getDrawable(i);
        if (drawable != null) {
            return drawable;
        }
        int color = typedArray.getColor(i2, i3);
        drawable = new GradientDrawable();
        ((GradientDrawable) drawable).setCornerRadius(this.c.getRadius());
        ((GradientDrawable) drawable).setColor(color);
        return drawable;
    }

    public void setConfiguration(Configuration configuration) {
        if (this.c == null) {
            this.c = Configuration.getDefault(configuration.getDensity());
        }
        this.c.a(configuration.getOffDrawableWithFix());
        this.c.b(configuration.getOnDrawableWithFix());
        this.c.setThumbDrawable(configuration.getThumbDrawableWithFix());
        this.c.setThumbMarginInPixel(configuration.getThumbMarginTop(), configuration.getThumbMarginBottom(), configuration.getThumbMarginLeft(), configuration.getThumbMarginRight());
        this.c.setThumbWidthAndHeightInPixel(configuration.a(), configuration.b());
        this.c.setVelocity(configuration.getVelocity());
        this.c.setMeasureFactor(configuration.getMeasureFactor());
        this.h.a(this.c.getVelocity());
        requestLayout();
        a();
        setChecked(this.b);
    }

    public Configuration getConfiguration() {
        return this.c;
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int a = (int) (((((float) this.c.a()) * this.c.getMeasureFactor()) + ((float) getPaddingLeft())) + ((float) getPaddingRight()));
        int thumbMarginLeft = this.c.getThumbMarginLeft() + this.c.getThumbMarginRight();
        if (thumbMarginLeft > 0) {
            a += thumbMarginLeft;
        }
        if (mode == 1073741824) {
            a = Math.max(size, a);
        } else if (mode == Integer.MIN_VALUE) {
            a = Math.min(size, a);
        }
        mode = (this.c.getInsetBounds().left + this.c.getInsetBounds().right) + a;
        size = MeasureSpec.getMode(i2);
        thumbMarginLeft = MeasureSpec.getSize(i2);
        a = (this.c.b() + getPaddingTop()) + getPaddingBottom();
        int thumbMarginTop = this.c.getThumbMarginTop() + this.c.getThumbMarginBottom();
        if (thumbMarginTop > 0) {
            a += thumbMarginTop;
        }
        if (size == 1073741824) {
            a = Math.max(thumbMarginLeft, a);
        } else if (size == Integer.MIN_VALUE) {
            a = Math.min(thumbMarginLeft, a);
        }
        setMeasuredDimension(mode, a + (this.c.getInsetBounds().top + this.c.getInsetBounds().bottom));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a();
    }

    private void a() {
        int thumbMarginRight;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            this.e = null;
        } else {
            if (this.e == null) {
                this.e = new Rect();
            }
            this.e.set(getPaddingLeft() + (this.c.getThumbMarginLeft() > 0 ? 0 : -this.c.getThumbMarginLeft()), getPaddingTop() + (this.c.getThumbMarginTop() > 0 ? 0 : -this.c.getThumbMarginTop()), (-this.c.getShrinkX()) + ((measuredWidth - getPaddingRight()) - (this.c.getThumbMarginRight() > 0 ? 0 : -this.c.getThumbMarginRight())), ((measuredHeight - getPaddingBottom()) - (this.c.getThumbMarginBottom() > 0 ? 0 : -this.c.getThumbMarginBottom())) + (-this.c.getShrinkY()));
        }
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            this.d = null;
        } else {
            if (this.d == null) {
                this.d = new Rect();
            }
            int paddingLeft = getPaddingLeft() + (this.c.getThumbMarginLeft() > 0 ? this.c.getThumbMarginLeft() : 0);
            measuredWidth -= getPaddingRight();
            if (this.c.getThumbMarginRight() > 0) {
                thumbMarginRight = this.c.getThumbMarginRight();
            } else {
                thumbMarginRight = 0;
            }
            measuredWidth = (-this.c.getShrinkX()) + (measuredWidth - thumbMarginRight);
            int paddingTop = getPaddingTop();
            if (this.c.getThumbMarginTop() > 0) {
                thumbMarginRight = this.c.getThumbMarginTop();
            } else {
                thumbMarginRight = 0;
            }
            paddingTop += thumbMarginRight;
            measuredHeight -= getPaddingBottom();
            if (this.c.getThumbMarginBottom() > 0) {
                thumbMarginRight = this.c.getThumbMarginBottom();
            } else {
                thumbMarginRight = 0;
            }
            this.d.set(paddingLeft, paddingTop, measuredWidth, (measuredHeight - thumbMarginRight) + (-this.c.getShrinkY()));
            this.n = (float) (this.d.left + (((this.d.right - this.d.left) - this.c.a()) / 2));
        }
        thumbMarginRight = getMeasuredWidth();
        measuredWidth = getMeasuredHeight();
        if (thumbMarginRight <= 0 || measuredWidth <= 0) {
            this.f = null;
        } else {
            if (this.f == null) {
                this.f = new Rect();
            }
            thumbMarginRight = this.b ? this.d.right - this.c.a() : this.d.left;
            measuredWidth = (((this.c.getThumbMarginTop() > 0 ? 0 : -this.c.getThumbMarginTop()) / 2) + this.d.top) + 1;
            this.f.set(thumbMarginRight, measuredWidth, thumbMarginRight + this.c.a(), this.c.b() + measuredWidth);
        }
        if (this.e != null) {
            this.c.getOnDrawable().setBounds(this.e);
            this.c.getOffDrawable().setBounds(this.e);
        }
        if (this.f != null) {
            this.c.getThumbDrawable().setBounds(this.f);
        }
        if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0) {
            this.g = new RectF(0.0f, 0.0f, (float) getMeasuredWidth(), (float) getMeasuredHeight());
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.setClipChildren(false);
        }
    }

    protected void onDraw(Canvas canvas) {
        Object obj = null;
        super.onDraw(canvas);
        canvas.getClipBounds(this.r);
        if (this.r != null && this.c.needShrink()) {
            this.r.inset(this.c.getInsetX(), this.c.getInsetY());
            canvas.clipRect(this.r, Op.REPLACE);
            canvas.translate((float) this.c.getInsetBounds().left, (float) this.c.getInsetBounds().top);
        }
        if (!isEnabled()) {
            Object obj2;
            boolean z = this.c.getOnDrawable() instanceof StateListDrawable;
            boolean z2 = this.c.getOffDrawable() instanceof StateListDrawable;
            if ((this.c.getThumbDrawable() instanceof StateListDrawable) && z && z2) {
                obj2 = null;
            } else {
                obj2 = 1;
            }
            if (obj2 != null) {
                obj = 1;
            }
        }
        if (obj != null) {
            canvas.saveLayerAlpha(this.g, 127, 31);
        }
        this.c.getOffDrawable().draw(canvas);
        Drawable onDrawable = this.c.getOnDrawable();
        int i = 255;
        if (!(this.d == null || this.d.right == this.d.left)) {
            int a = (this.d.right - this.c.a()) - this.d.left;
            if (a > 0) {
                i = ((this.f.left - this.d.left) * 255) / a;
            }
        }
        onDrawable.setAlpha(i);
        this.c.getOnDrawable().draw(canvas);
        this.c.getThumbDrawable().draw(canvas);
        if (obj != null) {
            canvas.restore();
        }
        if (a) {
            this.q.setColor(Color.parseColor("#AA0000"));
            canvas.drawRect(this.e, this.q);
            this.q.setColor(Color.parseColor("#00FF00"));
            canvas.drawRect(this.d, this.q);
            this.q.setColor(Color.parseColor("#0000FF"));
            canvas.drawRect(this.f, this.q);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.j || !isEnabled()) {
            return false;
        }
        float x = motionEvent.getX() - this.k;
        float y = motionEvent.getY() - this.l;
        switch (motionEvent.getAction()) {
            case 0:
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                this.k = motionEvent.getX();
                this.l = motionEvent.getY();
                this.m = this.k;
                setPressed(true);
                break;
            case 1:
            case 3:
                setPressed(false);
                boolean statusBasedOnPos = getStatusBasedOnPos();
                float eventTime = (float) (motionEvent.getEventTime() - motionEvent.getDownTime());
                if (x < ((float) this.o) && y < ((float) this.o) && eventTime < ((float) this.p)) {
                    performClick();
                    break;
                }
                slideToChecked(statusBasedOnPos);
                break;
                break;
            case 2:
                float x2 = motionEvent.getX();
                a((int) (x2 - this.m));
                this.m = x2;
                break;
        }
        invalidate();
        return true;
    }

    private boolean getStatusBasedOnPos() {
        return ((float) this.f.left) > this.n;
    }

    public void invalidate() {
        if (this.r == null || !this.c.needShrink()) {
            super.invalidate();
        } else {
            invalidate(this.r);
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    public void setChecked(boolean z) {
        setChecked(z, true);
    }

    public void setChecked(boolean z, boolean z2) {
        if (this.f != null) {
            a(z ? getMeasuredWidth() : -getMeasuredWidth());
        }
        a(z, z2);
    }

    public boolean isChecked() {
        return this.b;
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean z) {
        boolean z2 = true;
        if (z) {
            if (this.b) {
                z2 = false;
            }
            slideToChecked(z2);
            return;
        }
        if (this.b) {
            z2 = false;
        }
        setChecked(z2);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.c != null) {
            setDrawableState(this.c.getThumbDrawable());
            setDrawableState(this.c.getOnDrawable());
            setDrawableState(this.c.getOffDrawable());
        }
    }

    private void setDrawableState(Drawable drawable) {
        if (drawable != null) {
            drawable.setState(getDrawableState());
            invalidate();
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        if (onCheckedChangeListener == null) {
            throw new IllegalArgumentException("onCheckedChangeListener can not be null");
        }
        this.s = onCheckedChangeListener;
    }

    private void setCheckedInClass(boolean z) {
        a(z, true);
    }

    private void a(boolean z, boolean z2) {
        if (this.b != z) {
            this.b = z;
            refreshDrawableState();
            if (this.s != null && z2) {
                this.s.onCheckedChanged(this, this.b);
            }
        }
    }

    public void slideToChecked(boolean z) {
        if (!this.j) {
            this.h.a(this.f.left, z ? this.d.right - this.c.a() : this.d.left);
        }
    }

    private void a(int i) {
        int i2 = this.f.left + i;
        int i3 = this.f.right + i;
        if (i2 < this.d.left) {
            i2 = this.d.left;
            i3 = this.c.a() + i2;
        }
        if (i3 > this.d.right) {
            i3 = this.d.right;
            i2 = i3 - this.c.a();
        }
        this.f.set(i2, this.f.top, i3, this.f.bottom);
        this.c.getThumbDrawable().setBounds(this.f);
    }
}
