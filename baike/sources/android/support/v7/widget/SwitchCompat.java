package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import cz.msebera.android.httpclient.message.TokenParser;

public class SwitchCompat extends CompoundButton {
    private static final int[] N = new int[]{16842912};
    private static final Property<SwitchCompat, Float> b = new cr(Float.class, "thumbPos");
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private final TextPaint H;
    private ColorStateList I;
    private Layout J;
    private Layout K;
    private TransformationMethod L;
    private final Rect M;
    ObjectAnimator a;
    private Drawable c;
    private ColorStateList d;
    private Mode e;
    private boolean f;
    private boolean g;
    private Drawable h;
    private ColorStateList i;
    private Mode j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private CharSequence q;
    private CharSequence r;
    private boolean s;
    private int t;
    private int u;
    private float v;
    private float w;
    private VelocityTracker x;
    private int y;
    private float z;

    public SwitchCompat(Context context) {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = false;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.x = VelocityTracker.obtain();
        this.M = new Rect();
        this.H = new TextPaint(1);
        Resources resources = getResources();
        this.H.density = resources.getDisplayMetrics().density;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.SwitchCompat, i, 0);
        this.c = obtainStyledAttributes.getDrawable(R.styleable.SwitchCompat_android_thumb);
        if (this.c != null) {
            this.c.setCallback(this);
        }
        this.h = obtainStyledAttributes.getDrawable(R.styleable.SwitchCompat_track);
        if (this.h != null) {
            this.h.setCallback(this);
        }
        this.q = obtainStyledAttributes.getText(R.styleable.SwitchCompat_android_textOn);
        this.r = obtainStyledAttributes.getText(R.styleable.SwitchCompat_android_textOff);
        this.s = obtainStyledAttributes.getBoolean(R.styleable.SwitchCompat_showText, true);
        this.m = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SwitchCompat_thumbTextPadding, 0);
        this.n = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SwitchCompat_switchMinWidth, 0);
        this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SwitchCompat_switchPadding, 0);
        this.p = obtainStyledAttributes.getBoolean(R.styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.SwitchCompat_thumbTint);
        if (colorStateList != null) {
            this.d = colorStateList;
            this.f = true;
        }
        Mode parseTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.e != parseTintMode) {
            this.e = parseTintMode;
            this.g = true;
        }
        if (this.f || this.g) {
            b();
        }
        colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.SwitchCompat_trackTint);
        if (colorStateList != null) {
            this.i = colorStateList;
            this.k = true;
        }
        parseTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.j != parseTintMode) {
            this.j = parseTintMode;
            this.l = true;
        }
        if (this.k || this.l) {
            a();
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.SwitchCompat_switchTextAppearance, 0);
        if (resourceId != 0) {
            setSwitchTextAppearance(context, resourceId);
        }
        obtainStyledAttributes.recycle();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.u = viewConfiguration.getScaledTouchSlop();
        this.y = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public void setSwitchTextAppearance(Context context, int i) {
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i, R.styleable.TextAppearance);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
        if (colorStateList != null) {
            this.I = colorStateList;
        } else {
            this.I = getTextColors();
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, 0);
        if (!(dimensionPixelSize == 0 || ((float) dimensionPixelSize) == this.H.getTextSize())) {
            this.H.setTextSize((float) dimensionPixelSize);
            requestLayout();
        }
        a(obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, -1), obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, -1));
        if (obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false)) {
            this.L = new AllCapsTransformationMethod(getContext());
        } else {
            this.L = null;
        }
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
            TextPaint textPaint = this.H;
            if ((style & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            TextPaint textPaint2 = this.H;
            if ((style & 2) != 0) {
                f = -0.25f;
            } else {
                f = 0.0f;
            }
            textPaint2.setTextSkewX(f);
            return;
        }
        this.H.setFakeBoldText(false);
        this.H.setTextSkewX(0.0f);
        setSwitchTypeface(typeface);
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.H.getTypeface() != null && !this.H.getTypeface().equals(typeface)) || (this.H.getTypeface() == null && typeface != null)) {
            this.H.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void setSwitchPadding(int i) {
        this.o = i;
        requestLayout();
    }

    public int getSwitchPadding() {
        return this.o;
    }

    public void setSwitchMinWidth(int i) {
        this.n = i;
        requestLayout();
    }

    public int getSwitchMinWidth() {
        return this.n;
    }

    public void setThumbTextPadding(int i) {
        this.m = i;
        requestLayout();
    }

    public int getThumbTextPadding() {
        return this.m;
    }

    public void setTrackDrawable(Drawable drawable) {
        if (this.h != null) {
            this.h.setCallback(null);
        }
        this.h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i) {
        setTrackDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public Drawable getTrackDrawable() {
        return this.h;
    }

    public void setTrackTintList(@Nullable ColorStateList colorStateList) {
        this.i = colorStateList;
        this.k = true;
        a();
    }

    @Nullable
    public ColorStateList getTrackTintList() {
        return this.i;
    }

    public void setTrackTintMode(@Nullable Mode mode) {
        this.j = mode;
        this.l = true;
        a();
    }

    @Nullable
    public Mode getTrackTintMode() {
        return this.j;
    }

    private void a() {
        if (this.h == null) {
            return;
        }
        if (this.k || this.l) {
            this.h = this.h.mutate();
            if (this.k) {
                DrawableCompat.setTintList(this.h, this.i);
            }
            if (this.l) {
                DrawableCompat.setTintMode(this.h, this.j);
            }
            if (this.h.isStateful()) {
                this.h.setState(getDrawableState());
            }
        }
    }

    public void setThumbDrawable(Drawable drawable) {
        if (this.c != null) {
            this.c.setCallback(null);
        }
        this.c = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbResource(int i) {
        setThumbDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public Drawable getThumbDrawable() {
        return this.c;
    }

    public void setThumbTintList(@Nullable ColorStateList colorStateList) {
        this.d = colorStateList;
        this.f = true;
        b();
    }

    @Nullable
    public ColorStateList getThumbTintList() {
        return this.d;
    }

    public void setThumbTintMode(@Nullable Mode mode) {
        this.e = mode;
        this.g = true;
        b();
    }

    @Nullable
    public Mode getThumbTintMode() {
        return this.e;
    }

    private void b() {
        if (this.c == null) {
            return;
        }
        if (this.f || this.g) {
            this.c = this.c.mutate();
            if (this.f) {
                DrawableCompat.setTintList(this.c, this.d);
            }
            if (this.g) {
                DrawableCompat.setTintMode(this.c, this.e);
            }
            if (this.c.isStateful()) {
                this.c.setState(getDrawableState());
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.p = z;
        invalidate();
    }

    public boolean getSplitTrack() {
        return this.p;
    }

    public CharSequence getTextOn() {
        return this.q;
    }

    public void setTextOn(CharSequence charSequence) {
        this.q = charSequence;
        requestLayout();
    }

    public CharSequence getTextOff() {
        return this.r;
    }

    public void setTextOff(CharSequence charSequence) {
        this.r = charSequence;
        requestLayout();
    }

    public void setShowText(boolean z) {
        if (this.s != z) {
            this.s = z;
            requestLayout();
        }
    }

    public boolean getShowText() {
        return this.s;
    }

    public void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int intrinsicHeight;
        int max;
        int i3 = 0;
        if (this.s) {
            if (this.J == null) {
                this.J = a(this.q);
            }
            if (this.K == null) {
                this.K = a(this.r);
            }
        }
        Rect rect = this.M;
        if (this.c != null) {
            this.c.getPadding(rect);
            intrinsicWidth = (this.c.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.c.getIntrinsicHeight();
        } else {
            intrinsicHeight = 0;
            intrinsicWidth = 0;
        }
        if (this.s) {
            max = Math.max(this.J.getWidth(), this.K.getWidth()) + (this.m * 2);
        } else {
            max = 0;
        }
        this.C = Math.max(max, intrinsicWidth);
        if (this.h != null) {
            this.h.getPadding(rect);
            i3 = this.h.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        max = rect.left;
        intrinsicWidth = rect.right;
        if (this.c != null) {
            rect = DrawableUtils.getOpticalBounds(this.c);
            max = Math.max(max, rect.left);
            intrinsicWidth = Math.max(intrinsicWidth, rect.right);
        }
        intrinsicWidth = Math.max(this.n, intrinsicWidth + (max + (this.C * 2)));
        intrinsicHeight = Math.max(i3, intrinsicHeight);
        this.A = intrinsicWidth;
        this.B = intrinsicHeight;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < intrinsicHeight) {
            setMeasuredDimension(getMeasuredWidthAndState(), intrinsicHeight);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        Object obj = isChecked() ? this.q : this.r;
        if (obj != null) {
            accessibilityEvent.getText().add(obj);
        }
    }

    private Layout a(CharSequence charSequence) {
        int ceil;
        CharSequence transformation = this.L != null ? this.L.getTransformation(charSequence, this) : charSequence;
        TextPaint textPaint = this.H;
        if (transformation != null) {
            ceil = (int) Math.ceil((double) Layout.getDesiredWidth(transformation, this.H));
        } else {
            ceil = 0;
        }
        return new StaticLayout(transformation, textPaint, ceil, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    private boolean a(float f, float f2) {
        if (this.c == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.c.getPadding(this.M);
        int i = this.E - this.u;
        thumbOffset = (thumbOffset + this.D) - this.u;
        int i2 = (((this.C + thumbOffset) + this.M.left) + this.M.right) + this.u;
        int i3 = this.G + this.u;
        if (f <= ((float) thumbOffset) || f >= ((float) i2) || f2 <= ((float) i) || f2 >= ((float) i3)) {
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.x.addMovement(motionEvent);
        float x;
        float y;
        switch (motionEvent.getActionMasked()) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                if (isEnabled() && a(x, y)) {
                    this.t = 1;
                    this.v = x;
                    this.w = y;
                    break;
                }
            case 1:
            case 3:
                if (this.t != 2) {
                    this.t = 0;
                    this.x.clear();
                    break;
                }
                b(motionEvent);
                super.onTouchEvent(motionEvent);
                return true;
            case 2:
                switch (this.t) {
                    case 0:
                        break;
                    case 1:
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        if (Math.abs(x - this.v) > ((float) this.u) || Math.abs(y - this.w) > ((float) this.u)) {
                            this.t = 2;
                            getParent().requestDisallowInterceptTouchEvent(true);
                            this.v = x;
                            this.w = y;
                            return true;
                        }
                    case 2:
                        float x2 = motionEvent.getX();
                        int thumbScrollRange = getThumbScrollRange();
                        float f = x2 - this.v;
                        x = thumbScrollRange != 0 ? f / ((float) thumbScrollRange) : f > 0.0f ? 1.0f : -1.0f;
                        if (ViewUtils.isLayoutRtl(this)) {
                            x = -x;
                        }
                        x = a(x + this.z, 0.0f, 1.0f);
                        if (x != this.z) {
                            this.v = x2;
                            setThumbPosition(x);
                        }
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
        this.t = 0;
        boolean z2 = motionEvent.getAction() == 1 && isEnabled();
        boolean isChecked = isChecked();
        if (z2) {
            this.x.computeCurrentVelocity(1000);
            float xVelocity = this.x.getXVelocity();
            if (Math.abs(xVelocity) <= ((float) this.y)) {
                z = getTargetCheckedState();
            } else if (ViewUtils.isLayoutRtl(this)) {
                if (xVelocity >= 0.0f) {
                    z = false;
                }
            } else if (xVelocity <= 0.0f) {
                z = false;
            }
        } else {
            z = isChecked;
        }
        if (z != isChecked) {
            playSoundEffect(0);
        }
        setChecked(z);
        a(motionEvent);
    }

    private void a(boolean z) {
        float f = z ? 1.0f : 0.0f;
        this.a = ObjectAnimator.ofFloat(this, b, new float[]{f});
        this.a.setDuration(250);
        if (VERSION.SDK_INT >= 18) {
            this.a.setAutoCancel(true);
        }
        this.a.start();
    }

    private void c() {
        if (this.a != null) {
            this.a.cancel();
        }
    }

    private boolean getTargetCheckedState() {
        return this.z > 0.5f;
    }

    void setThumbPosition(float f) {
        this.z = f;
        invalidate();
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean isChecked = isChecked();
        if (getWindowToken() == null || !ViewCompat.isLaidOut(this)) {
            c();
            setThumbPosition(isChecked ? 1.0f : 0.0f);
            return;
        }
        a(isChecked);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int max;
        int paddingLeft;
        int paddingTop;
        int i5 = 0;
        super.onLayout(z, i, i2, i3, i4);
        if (this.c != null) {
            Rect rect = this.M;
            if (this.h != null) {
                this.h.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect opticalBounds = DrawableUtils.getOpticalBounds(this.c);
            max = Math.max(0, opticalBounds.left - rect.left);
            i5 = Math.max(0, opticalBounds.right - rect.right);
        } else {
            max = 0;
        }
        if (ViewUtils.isLayoutRtl(this)) {
            paddingLeft = getPaddingLeft() + max;
            max = ((this.A + paddingLeft) - max) - i5;
            i5 = paddingLeft;
        } else {
            paddingLeft = (getWidth() - getPaddingRight()) - i5;
            i5 += max + (paddingLeft - this.A);
            max = paddingLeft;
        }
        switch (getGravity() & 112) {
            case 16:
                paddingTop = (((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2) - (this.B / 2);
                paddingLeft = this.B + paddingTop;
                break;
            case 80:
                paddingLeft = getHeight() - getPaddingBottom();
                paddingTop = paddingLeft - this.B;
                break;
            default:
                paddingTop = getPaddingTop();
                paddingLeft = this.B + paddingTop;
                break;
        }
        this.D = i5;
        this.E = paddingTop;
        this.G = paddingLeft;
        this.F = max;
    }

    public void draw(Canvas canvas) {
        Rect opticalBounds;
        int i;
        Rect rect = this.M;
        int i2 = this.D;
        int i3 = this.E;
        int i4 = this.F;
        int i5 = this.G;
        int thumbOffset = i2 + getThumbOffset();
        if (this.c != null) {
            opticalBounds = DrawableUtils.getOpticalBounds(this.c);
        } else {
            opticalBounds = DrawableUtils.INSETS_NONE;
        }
        if (this.h != null) {
            this.h.getPadding(rect);
            int i6 = rect.left + thumbOffset;
            if (opticalBounds != null) {
                if (opticalBounds.left > rect.left) {
                    i2 += opticalBounds.left - rect.left;
                }
                if (opticalBounds.top > rect.top) {
                    thumbOffset = (opticalBounds.top - rect.top) + i3;
                } else {
                    thumbOffset = i3;
                }
                if (opticalBounds.right > rect.right) {
                    i4 -= opticalBounds.right - rect.right;
                }
                i = opticalBounds.bottom > rect.bottom ? i5 - (opticalBounds.bottom - rect.bottom) : i5;
            } else {
                i = i5;
                thumbOffset = i3;
            }
            this.h.setBounds(i2, thumbOffset, i4, i);
            i = i6;
        } else {
            i = thumbOffset;
        }
        if (this.c != null) {
            this.c.getPadding(rect);
            i2 = i - rect.left;
            i = (i + this.C) + rect.right;
            this.c.setBounds(i2, i3, i, i5);
            Drawable background = getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, i2, i3, i, i5);
            }
        }
        super.draw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        int save;
        super.onDraw(canvas);
        Rect rect = this.M;
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i = this.E + rect.top;
        int i2 = this.G - rect.bottom;
        Drawable drawable2 = this.c;
        if (drawable != null) {
            if (!this.p || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect opticalBounds = DrawableUtils.getOpticalBounds(drawable2);
                drawable2.copyBounds(rect);
                rect.left += opticalBounds.left;
                rect.right -= opticalBounds.right;
                save = canvas.save();
                canvas.clipRect(rect, Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
        save = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.J : this.K;
        if (layout != null) {
            int i3;
            int[] drawableState = getDrawableState();
            if (this.I != null) {
                this.H.setColor(this.I.getColorForState(drawableState, 0));
            }
            this.H.drawableState = drawableState;
            if (drawable2 != null) {
                rect = drawable2.getBounds();
                i3 = rect.right + rect.left;
            } else {
                i3 = getWidth();
            }
            canvas.translate((float) ((i3 / 2) - (layout.getWidth() / 2)), (float) (((i + i2) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save);
    }

    public int getCompoundPaddingLeft() {
        if (!ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.A;
        if (TextUtils.isEmpty(getText())) {
            return compoundPaddingLeft;
        }
        return compoundPaddingLeft + this.o;
    }

    public int getCompoundPaddingRight() {
        if (ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.A;
        if (TextUtils.isEmpty(getText())) {
            return compoundPaddingRight;
        }
        return compoundPaddingRight + this.o;
    }

    private int getThumbOffset() {
        float f;
        if (ViewUtils.isLayoutRtl(this)) {
            f = 1.0f - this.z;
        } else {
            f = this.z;
        }
        return (int) ((f * ((float) getThumbScrollRange())) + 0.5f);
    }

    private int getThumbScrollRange() {
        if (this.h == null) {
            return 0;
        }
        Rect opticalBounds;
        Rect rect = this.M;
        this.h.getPadding(rect);
        if (this.c != null) {
            opticalBounds = DrawableUtils.getOpticalBounds(this.c);
        } else {
            opticalBounds = DrawableUtils.INSETS_NONE;
        }
        return ((((this.A - this.C) - rect.left) - rect.right) - opticalBounds.left) - opticalBounds.right;
    }

    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, N);
        }
        return onCreateDrawableState;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        int i = 0;
        Drawable drawable = this.c;
        if (drawable != null && drawable.isStateful()) {
            i = 0 | drawable.setState(drawableState);
        }
        drawable = this.h;
        if (drawable != null && drawable.isStateful()) {
            i |= drawable.setState(drawableState);
        }
        if (i != 0) {
            invalidate();
        }
    }

    public void drawableHotspotChanged(float f, float f2) {
        if (VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(f, f2);
        }
        if (this.c != null) {
            DrawableCompat.setHotspot(this.c, f, f2);
        }
        if (this.h != null) {
            DrawableCompat.setHotspot(this.h, f, f2);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.c || drawable == this.h;
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.c != null) {
            this.c.jumpToCurrentState();
        }
        if (this.h != null) {
            this.h.jumpToCurrentState();
        }
        if (this.a != null && this.a.isStarted()) {
            this.a.end();
            this.a = null;
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.q : this.r;
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

    private static float a(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return f > f3 ? f3 : f;
    }
}
