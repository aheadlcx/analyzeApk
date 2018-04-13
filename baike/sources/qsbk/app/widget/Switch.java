package qsbk.app.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import cz.msebera.android.httpclient.message.TokenParser;
import qsbk.app.R;

public class Switch extends CompoundButton {
    private static final int[] a = new int[]{16842912};
    private static final int[] b = new int[]{16842901, 16842902, 16842903, 16842904};
    private Layout A;
    private Layout B;
    private final Rect c;
    private Drawable d;
    private Drawable e;
    private int f;
    private int g;
    private int h;
    private CharSequence i;
    private CharSequence j;
    private int k;
    private int l;
    private float m;
    private float n;
    private VelocityTracker o;
    private int p;
    private float q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private TextPaint y;
    private ColorStateList z;

    public Switch(Context context) {
        this(context, null);
    }

    public Switch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchStyle);
    }

    public Switch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Rect();
        this.o = VelocityTracker.obtain();
        this.y = new TextPaint(1);
        Resources resources = getResources();
        this.y.density = resources.getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Switch, i, 0);
        this.d = obtainStyledAttributes.getDrawable(0);
        this.e = obtainStyledAttributes.getDrawable(1);
        this.i = obtainStyledAttributes.getText(2);
        this.j = obtainStyledAttributes.getText(3);
        this.f = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        this.g = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.h = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        int resourceId = obtainStyledAttributes.getResourceId(5, 0);
        if (resourceId != 0) {
            setSwitchTextAppearance(context, resourceId);
        }
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.l = viewConfiguration.getScaledTouchSlop();
        this.p = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTextAppearance(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, b);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(3);
        if (colorStateList != null) {
            this.z = colorStateList;
        } else {
            this.z = getTextColors();
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, getResources().getDimensionPixelSize(R.dimen.g_txt_middle));
        if (!(dimensionPixelSize == 0 || ((float) dimensionPixelSize) == this.y.getTextSize())) {
            this.y.setTextSize((float) dimensionPixelSize);
            requestLayout();
        }
        a(obtainStyledAttributes.getInt(1, -1), obtainStyledAttributes.getInt(2, -1));
        obtainStyledAttributes.recycle();
    }

    private void a(int i, int i2) {
        Typeface typeface = null;
        switch (i) {
            case 1:
                typeface = Typeface.SANS_SERIF;
                break;
            case 2:
                typeface = Typeface.SERIF;
                break;
            case 3:
                typeface = Typeface.MONOSPACE;
                break;
        }
        setSwitchTypeface(typeface, i2);
    }

    public void setSwitchTypeface(Typeface typeface, int i) {
        boolean z = false;
        if (i > 0) {
            Typeface defaultFromStyle;
            int style;
            float f;
            if (typeface == null) {
                defaultFromStyle = Typeface.defaultFromStyle(i);
            } else {
                defaultFromStyle = Typeface.create(typeface, i);
            }
            setSwitchTypeface(defaultFromStyle);
            if (defaultFromStyle != null) {
                style = defaultFromStyle.getStyle();
            } else {
                style = 0;
            }
            style = (style ^ -1) & i;
            TextPaint textPaint = this.y;
            if ((style & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            TextPaint textPaint2 = this.y;
            if ((style & 2) != 0) {
                f = -0.25f;
            } else {
                f = 0.0f;
            }
            textPaint2.setTextSkewX(f);
            return;
        }
        this.y.setFakeBoldText(false);
        this.y.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if (this.y.getTypeface() != typeface) {
            this.y.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public int getSwitchPadding() {
        return this.h;
    }

    public void setSwitchPadding(int i) {
        this.h = i;
        requestLayout();
    }

    public int getSwitchMinWidth() {
        return this.g;
    }

    public void setSwitchMinWidth(int i) {
        this.g = i;
        requestLayout();
    }

    public int getThumbTextPadding() {
        return this.f;
    }

    public void setThumbTextPadding(int i) {
        this.f = i;
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(getContext().getResources().getDrawable(i));
    }

    public Drawable getTrackDrawable() {
        return this.e;
    }

    public void setTrackDrawable(Drawable drawable) {
        this.e = drawable;
        requestLayout();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(getContext().getResources().getDrawable(i));
    }

    public Drawable getThumbDrawable() {
        return this.d;
    }

    public void setThumbDrawable(Drawable drawable) {
        this.d = drawable;
        requestLayout();
    }

    public CharSequence getTextOn() {
        return this.i;
    }

    public void setTextOn(CharSequence charSequence) {
        this.i = charSequence;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.j;
    }

    public void setTextOff(CharSequence charSequence) {
        this.j = charSequence;
        requestLayout();
    }

    @SuppressLint({"NewApi"})
    public void onMeasure(int i, int i2) {
        if (this.A == null) {
            this.A = a(this.i);
        }
        if (this.B == null) {
            this.B = a(this.j);
        }
        this.e.getPadding(this.c);
        int max = Math.max(this.A.getWidth(), this.B.getWidth());
        int max2 = Math.max(this.g, (((max * 2) + (this.f * 4)) + this.c.left) + this.c.right);
        int intrinsicHeight = this.e.getIntrinsicHeight();
        this.t = max + (this.f * 2);
        this.r = max2;
        this.s = intrinsicHeight;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < intrinsicHeight && VERSION.SDK_INT >= 11) {
            setMeasuredDimension(getMeasuredWidthAndState(), intrinsicHeight);
        }
    }

    @TargetApi(14)
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        Layout layout = isChecked() ? this.A : this.B;
        if (layout != null && !TextUtils.isEmpty(layout.getText())) {
            accessibilityEvent.getText().add(layout.getText());
        }
    }

    private Layout a(CharSequence charSequence) {
        return new StaticLayout(charSequence, this.y, (int) Math.ceil((double) Layout.getDesiredWidth(charSequence, this.y)), Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private boolean a(float f, float f2) {
        this.d.getPadding(this.c);
        int i = (this.u + ((int) (this.q + 0.5f))) - this.l;
        return f > ((float) i) && f < ((float) ((((this.t + i) + this.c.left) + this.c.right) + this.l)) && f2 > ((float) (this.v - this.l)) && f2 < ((float) (this.x + this.l));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.o.addMovement(motionEvent);
        float x;
        float y;
        switch (motionEvent.getActionMasked()) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                if (isEnabled() && a(x, y)) {
                    this.k = 1;
                    this.m = x;
                    this.n = y;
                    break;
                }
            case 1:
            case 3:
                if (this.k != 2) {
                    this.k = 0;
                    this.o.clear();
                    break;
                }
                b(motionEvent);
                return true;
            case 2:
                switch (this.k) {
                    case 0:
                        break;
                    case 1:
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        if (Math.abs(x - this.m) > ((float) this.l) || Math.abs(y - this.n) > ((float) this.l)) {
                            this.k = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.m = x;
                            this.n = y;
                            return true;
                        }
                    case 2:
                        x = motionEvent.getX();
                        y = Math.max(0.0f, Math.min((x - this.m) + this.q, (float) getThumbScrollRange()));
                        if (y == this.q) {
                            return true;
                        }
                        this.q = y;
                        this.m = x;
                        invalidate();
                        return true;
                    default:
                        break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }

    private void b(MotionEvent motionEvent) {
        boolean z = true;
        this.k = 0;
        boolean z2 = motionEvent.getAction() == 1 && isEnabled();
        a(motionEvent);
        if (z2) {
            this.o.computeCurrentVelocity(1000);
            float xVelocity = this.o.getXVelocity();
            if (Math.abs(xVelocity) <= ((float) this.p)) {
                z = getTargetCheckedState();
            } else if (isLayoutRtl()) {
                if (xVelocity >= 0.0f) {
                    z = false;
                }
            } else if (xVelocity <= 0.0f) {
                z = false;
            }
            a(z);
            return;
        }
        a(isChecked());
    }

    private void a(boolean z) {
        setChecked(z);
    }

    private boolean getTargetCheckedState() {
        if (isLayoutRtl()) {
            if (this.q <= ((float) (getThumbScrollRange() / 2))) {
                return true;
            }
            return false;
        } else if (this.q < ((float) (getThumbScrollRange() / 2))) {
            return false;
        } else {
            return true;
        }
    }

    private void setThumbPosition(boolean z) {
        float f = 0.0f;
        if (isLayoutRtl()) {
            if (!z) {
                f = (float) getThumbScrollRange();
            }
            this.q = f;
            return;
        }
        if (z) {
            f = (float) getThumbScrollRange();
        }
        this.q = f;
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
        setThumbPosition(isChecked());
        invalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int paddingTop;
        int i6;
        super.onLayout(z, i, i2, i3, i4);
        setThumbPosition(isChecked());
        if (isLayoutRtl()) {
            paddingLeft = getPaddingLeft();
            i5 = this.r + paddingLeft;
        } else {
            i5 = getWidth() - getPaddingRight();
            paddingLeft = i5 - this.r;
        }
        switch (getGravity() & 112) {
            case 16:
                paddingTop = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.s / 2);
                i6 = this.s + paddingTop;
                break;
            case 80:
                i6 = getHeight() - getPaddingBottom();
                paddingTop = i6 - this.s;
                break;
            default:
                paddingTop = getPaddingTop();
                i6 = this.s + paddingTop;
                break;
        }
        this.u = paddingLeft;
        this.v = paddingTop;
        this.x = i6;
        this.w = i5;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.u;
        int i2 = this.v;
        int i3 = this.w;
        int i4 = this.x;
        this.e.setBounds(i, i2, i3, i4);
        this.e.draw(canvas);
        canvas.save();
        this.e.getPadding(this.c);
        i += this.c.left;
        int i5 = this.c.top + i2;
        int i6 = i4 - this.c.bottom;
        canvas.clipRect(i, i2, i3 - this.c.right, i4);
        this.d.getPadding(this.c);
        i3 = (int) (this.q + 0.5f);
        int i7 = (i - this.c.left) + i3;
        i3 = this.c.right + ((i + i3) + this.t);
        this.d.setBounds(i7, i2, i3, i4);
        this.d.draw(canvas);
        if (this.z != null) {
            this.y.setColor(this.z.getColorForState(getDrawableState(), this.z.getDefaultColor()));
        }
        this.y.drawableState = getDrawableState();
        Layout layout = getTargetCheckedState() ? this.A : this.B;
        if (layout != null) {
            canvas.translate((float) (((i7 + i3) / 2) - (layout.getWidth() / 2)), (float) (((i5 + i6) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restore();
    }

    public int getCompoundPaddingLeft() {
        if (!isLayoutRtl()) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.r;
        if (TextUtils.isEmpty(getText())) {
            return compoundPaddingLeft;
        }
        return compoundPaddingLeft + this.h;
    }

    public boolean isLayoutRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    public int getCompoundPaddingRight() {
        if (isLayoutRtl()) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.r;
        if (TextUtils.isEmpty(getText())) {
            return compoundPaddingRight;
        }
        return compoundPaddingRight + this.h;
    }

    private int getThumbScrollRange() {
        if (this.e == null) {
            return 0;
        }
        this.e.getPadding(this.c);
        return ((this.r - this.t) - this.c.left) - this.c.right;
    }

    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, a);
        }
        return onCreateDrawableState;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        if (this.d != null) {
            this.d.setState(drawableState);
        }
        if (this.e != null) {
            this.e.setState(drawableState);
        }
        invalidate();
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.d || drawable == this.e;
    }

    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        this.d.jumpToCurrentState();
        this.e.jumpToCurrentState();
    }

    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Switch.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Switch.class.getName());
        CharSequence charSequence = isChecked() ? this.i : this.j;
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (TextUtils.isEmpty(text)) {
                accessibilityNodeInfo.setText(charSequence);
                return;
            }
            CharSequence stringBuilder = new StringBuilder();
            stringBuilder.append(text).append(TokenParser.SP).append(charSequence);
            accessibilityNodeInfo.setText(stringBuilder);
        }
    }
}
