package android.support.design.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.Space;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.sdk.util.h;

public class TextInputLayout extends LinearLayout {
    private boolean A;
    private Drawable B;
    private Drawable C;
    private ColorStateList D;
    private boolean E;
    private Mode F;
    private boolean G;
    private ColorStateList H;
    private ColorStateList I;
    private boolean J;
    private boolean K;
    private ValueAnimator L;
    private boolean M;
    private boolean N;
    private boolean O;
    EditText a;
    TextView b;
    boolean c;
    final ad d;
    private final FrameLayout e;
    private CharSequence f;
    private boolean g;
    private CharSequence h;
    private Paint i;
    private final Rect j;
    private LinearLayout k;
    private int l;
    private Typeface m;
    private boolean n;
    private int o;
    private boolean p;
    private CharSequence q;
    private TextView r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private Drawable x;
    private CharSequence y;
    private CheckableImageButton z;

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new bi();
        CharSequence a;
        boolean b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.b = parcel.readInt() == 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.a, parcel, i);
            parcel.writeInt(this.b ? 1 : 0);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.a + h.d;
        }
    }

    private class a extends AccessibilityDelegateCompat {
        final /* synthetic */ TextInputLayout a;

        a(TextInputLayout textInputLayout) {
            this.a = textInputLayout;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(TextInputLayout.class.getSimpleName());
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            CharSequence i = this.a.d.i();
            if (!TextUtils.isEmpty(i)) {
                accessibilityEvent.getText().add(i);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
            CharSequence i = this.a.d.i();
            if (!TextUtils.isEmpty(i)) {
                accessibilityNodeInfoCompat.setText(i);
            }
            if (this.a.a != null) {
                accessibilityNodeInfoCompat.setLabelFor(this.a.a);
            }
            i = this.a.b != null ? this.a.b.getText() : null;
            if (!TextUtils.isEmpty(i)) {
                accessibilityNodeInfoCompat.setContentInvalid(true);
                accessibilityNodeInfoCompat.setError(i);
            }
        }
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.j = new Rect();
        this.d = new ad(this);
        bj.a(context);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.e = new FrameLayout(context);
        this.e.setAddStatesFromChildren(true);
        addView(this.e);
        this.d.a(a.b);
        this.d.b(new AccelerateInterpolator());
        this.d.b(8388659);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.TextInputLayout, i, R.style.Widget_Design_TextInputLayout);
        this.g = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
        setHint(obtainStyledAttributes.getText(R.styleable.TextInputLayout_android_hint));
        this.K = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        if (obtainStyledAttributes.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
            this.I = colorStateList;
            this.H = colorStateList;
        }
        if (obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.o = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(obtainStyledAttributes.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.t = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.u = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.w = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false);
        this.x = obtainStyledAttributes.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable);
        this.y = obtainStyledAttributes.getText(R.styleable.TextInputLayout_passwordToggleContentDescription);
        if (obtainStyledAttributes.hasValue(R.styleable.TextInputLayout_passwordToggleTint)) {
            this.E = true;
            this.D = obtainStyledAttributes.getColorStateList(R.styleable.TextInputLayout_passwordToggleTint);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode)) {
            this.G = true;
            this.F = bm.a(obtainStyledAttributes.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
        }
        obtainStyledAttributes.recycle();
        setErrorEnabled(z);
        setCounterEnabled(z2);
        h();
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setAccessibilityDelegate(this, new a(this));
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (view instanceof EditText) {
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.e.addView(view, layoutParams2);
            this.e.setLayoutParams(layoutParams);
            a();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public void setTypeface(@Nullable Typeface typeface) {
        if ((this.m != null && !this.m.equals(typeface)) || (this.m == null && typeface != null)) {
            this.m = typeface;
            this.d.c(typeface);
            if (this.r != null) {
                this.r.setTypeface(typeface);
            }
            if (this.b != null) {
                this.b.setTypeface(typeface);
            }
        }
    }

    @NonNull
    public Typeface getTypeface() {
        return this.m;
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        if (this.f == null || this.a == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        CharSequence hint = this.a.getHint();
        this.a.setHint(this.f);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i);
        } finally {
            this.a.setHint(hint);
        }
    }

    private void setEditText(EditText editText) {
        if (this.a != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (!(editText instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.a = editText;
        if (!f()) {
            this.d.c(this.a.getTypeface());
        }
        this.d.a(this.a.getTextSize());
        int gravity = this.a.getGravity();
        this.d.b((gravity & -113) | 48);
        this.d.a(gravity);
        this.a.addTextChangedListener(new bd(this));
        if (this.H == null) {
            this.H = this.a.getHintTextColors();
        }
        if (this.g && TextUtils.isEmpty(this.h)) {
            this.f = this.a.getHint();
            setHint(this.f);
            this.a.setHint(null);
        }
        if (this.r != null) {
            a(this.a.getText().length());
        }
        if (this.k != null) {
            b();
        }
        e();
        a(false, true);
    }

    private void a() {
        int i;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.e.getLayoutParams();
        if (this.g) {
            if (this.i == null) {
                this.i = new Paint();
            }
            this.i.setTypeface(this.d.d());
            this.i.setTextSize(this.d.h());
            i = (int) (-this.i.ascent());
        } else {
            i = 0;
        }
        if (i != layoutParams.topMargin) {
            layoutParams.topMargin = i;
            this.e.requestLayout();
        }
    }

    void a(boolean z) {
        a(z, false);
    }

    void a(boolean z, boolean z2) {
        Object obj = 1;
        boolean isEnabled = isEnabled();
        Object obj2 = (this.a == null || TextUtils.isEmpty(this.a.getText())) ? null : 1;
        boolean a = a(getDrawableState(), 16842908);
        if (TextUtils.isEmpty(getError())) {
            obj = null;
        }
        if (this.H != null) {
            this.d.b(this.H);
        }
        if (isEnabled && this.v && this.r != null) {
            this.d.a(this.r.getTextColors());
        } else if (isEnabled && a && this.I != null) {
            this.d.a(this.I);
        } else if (this.H != null) {
            this.d.a(this.H);
        }
        if (obj2 != null || (isEnabled() && (a || r1 != null))) {
            if (z2 || this.J) {
                c(z);
            }
        } else if (z2 || !this.J) {
            d(z);
        }
    }

    @Nullable
    public EditText getEditText() {
        return this.a;
    }

    public void setHint(@Nullable CharSequence charSequence) {
        if (this.g) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        this.h = charSequence;
        this.d.a(charSequence);
    }

    @Nullable
    public CharSequence getHint() {
        return this.g ? this.h : null;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.g) {
            this.g = z;
            CharSequence hint = this.a.getHint();
            if (!this.g) {
                if (!TextUtils.isEmpty(this.h) && TextUtils.isEmpty(hint)) {
                    this.a.setHint(this.h);
                }
                setHintInternal(null);
            } else if (!TextUtils.isEmpty(hint)) {
                if (TextUtils.isEmpty(this.h)) {
                    setHint(hint);
                }
                this.a.setHint(null);
            }
            if (this.a != null) {
                a();
            }
        }
    }

    public boolean isHintEnabled() {
        return this.g;
    }

    public void setHintTextAppearance(@StyleRes int i) {
        this.d.c(i);
        this.I = this.d.j();
        if (this.a != null) {
            a(false);
            a();
        }
    }

    private void a(TextView textView, int i) {
        if (this.k == null) {
            this.k = new LinearLayout(getContext());
            this.k.setOrientation(0);
            addView(this.k, -1, -2);
            this.k.addView(new Space(getContext()), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.a != null) {
                b();
            }
        }
        this.k.setVisibility(0);
        this.k.addView(textView, i);
        this.l++;
    }

    private void b() {
        ViewCompat.setPaddingRelative(this.k, ViewCompat.getPaddingStart(this.a), 0, ViewCompat.getPaddingEnd(this.a), this.a.getPaddingBottom());
    }

    private void a(TextView textView) {
        if (this.k != null) {
            this.k.removeView(textView);
            int i = this.l - 1;
            this.l = i;
            if (i == 0) {
                this.k.setVisibility(8);
            }
        }
    }

    public void setErrorEnabled(boolean z) {
        if (this.n != z) {
            if (this.b != null) {
                this.b.animate().cancel();
            }
            if (z) {
                int i;
                this.b = new AppCompatTextView(getContext());
                this.b.setId(R.id.textinput_error);
                if (this.m != null) {
                    this.b.setTypeface(this.m);
                }
                try {
                    TextViewCompat.setTextAppearance(this.b, this.o);
                    if (VERSION.SDK_INT < 23 || this.b.getTextColors().getDefaultColor() != -65281) {
                        boolean z2 = false;
                    } else {
                        i = 1;
                    }
                } catch (Exception e) {
                    i = 1;
                }
                if (i != 0) {
                    TextViewCompat.setTextAppearance(this.b, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
                    this.b.setTextColor(ContextCompat.getColor(getContext(), android.support.v7.appcompat.R.color.error_color_material));
                }
                this.b.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.b, 1);
                a(this.b, 0);
            } else {
                this.p = false;
                c();
                a(this.b);
                this.b = null;
            }
            this.n = z;
        }
    }

    public void setErrorTextAppearance(@StyleRes int i) {
        this.o = i;
        if (this.b != null) {
            TextViewCompat.setTextAppearance(this.b, i);
        }
    }

    public boolean isErrorEnabled() {
        return this.n;
    }

    public void setError(@Nullable CharSequence charSequence) {
        boolean z = ViewCompat.isLaidOut(this) && isEnabled() && (this.b == null || !TextUtils.equals(this.b.getText(), charSequence));
        a(charSequence, z);
    }

    private void a(@Nullable CharSequence charSequence, boolean z) {
        boolean z2 = true;
        this.q = charSequence;
        if (!this.n) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            z2 = false;
        }
        this.p = z2;
        this.b.animate().cancel();
        if (this.p) {
            this.b.setText(charSequence);
            this.b.setVisibility(0);
            if (z) {
                if (this.b.getAlpha() == 1.0f) {
                    this.b.setAlpha(0.0f);
                }
                this.b.animate().alpha(1.0f).setDuration(200).setInterpolator(a.d).setListener(new be(this)).start();
            } else {
                this.b.setAlpha(1.0f);
            }
        } else if (this.b.getVisibility() == 0) {
            if (z) {
                this.b.animate().alpha(0.0f).setDuration(200).setInterpolator(a.c).setListener(new bf(this, charSequence)).start();
            } else {
                this.b.setText(charSequence);
                this.b.setVisibility(4);
            }
        }
        c();
        a(z);
    }

    public void setCounterEnabled(boolean z) {
        if (this.c != z) {
            if (z) {
                this.r = new AppCompatTextView(getContext());
                this.r.setId(R.id.textinput_counter);
                if (this.m != null) {
                    this.r.setTypeface(this.m);
                }
                this.r.setMaxLines(1);
                try {
                    TextViewCompat.setTextAppearance(this.r, this.t);
                } catch (Exception e) {
                    TextViewCompat.setTextAppearance(this.r, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
                    this.r.setTextColor(ContextCompat.getColor(getContext(), android.support.v7.appcompat.R.color.error_color_material));
                }
                a(this.r, -1);
                if (this.a == null) {
                    a(0);
                } else {
                    a(this.a.getText().length());
                }
            } else {
                a(this.r);
                this.r = null;
            }
            this.c = z;
        }
    }

    public boolean isCounterEnabled() {
        return this.c;
    }

    public void setCounterMaxLength(int i) {
        if (this.s != i) {
            if (i > 0) {
                this.s = i;
            } else {
                this.s = -1;
            }
            if (this.c) {
                a(this.a == null ? 0 : this.a.getText().length());
            }
        }
    }

    public void setEnabled(boolean z) {
        a((ViewGroup) this, z);
        super.setEnabled(z);
    }

    private static void a(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, z);
            }
        }
    }

    public int getCounterMaxLength() {
        return this.s;
    }

    void a(int i) {
        boolean z = this.v;
        if (this.s == -1) {
            this.r.setText(String.valueOf(i));
            this.v = false;
        } else {
            this.v = i > this.s;
            if (z != this.v) {
                TextViewCompat.setTextAppearance(this.r, this.v ? this.u : this.t);
            }
            this.r.setText(getContext().getString(R.string.character_counter_pattern, new Object[]{Integer.valueOf(i), Integer.valueOf(this.s)}));
        }
        if (this.a != null && z != this.v) {
            a(false);
            c();
        }
    }

    private void c() {
        if (this.a != null) {
            Drawable background = this.a.getBackground();
            if (background != null) {
                d();
                if (DrawableUtils.canSafelyMutateDrawable(background)) {
                    background = background.mutate();
                }
                if (this.p && this.b != null) {
                    background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.b.getCurrentTextColor(), Mode.SRC_IN));
                } else if (!this.v || this.r == null) {
                    DrawableCompat.clearColorFilter(background);
                    this.a.refreshDrawableState();
                } else {
                    background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.r.getCurrentTextColor(), Mode.SRC_IN));
                }
            }
        }
    }

    private void d() {
        int i = VERSION.SDK_INT;
        if (i == 21 || i == 22) {
            Drawable background = this.a.getBackground();
            if (background != null && !this.M) {
                Drawable newDrawable = background.getConstantState().newDrawable();
                if (background instanceof DrawableContainer) {
                    this.M = aj.a((DrawableContainer) background, newDrawable.getConstantState());
                }
                if (!this.M) {
                    ViewCompat.setBackground(this.a, newDrawable);
                    this.M = true;
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (this.p) {
            savedState.a = getError();
        }
        savedState.b = this.A;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setError(savedState.a);
            if (savedState.b) {
                b(true);
            }
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.O = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.O = false;
    }

    @Nullable
    public CharSequence getError() {
        return this.n ? this.q : null;
    }

    public boolean isHintAnimationEnabled() {
        return this.K;
    }

    public void setHintAnimationEnabled(boolean z) {
        this.K = z;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.g) {
            this.d.draw(canvas);
        }
    }

    protected void onMeasure(int i, int i2) {
        e();
        super.onMeasure(i, i2);
    }

    private void e() {
        if (this.a != null) {
            Drawable[] compoundDrawablesRelative;
            if (g()) {
                if (this.z == null) {
                    this.z = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_password_icon, this.e, false);
                    this.z.setImageDrawable(this.x);
                    this.z.setContentDescription(this.y);
                    this.e.addView(this.z);
                    this.z.setOnClickListener(new bg(this));
                }
                if (this.a != null && ViewCompat.getMinimumHeight(this.a) <= 0) {
                    this.a.setMinimumHeight(ViewCompat.getMinimumHeight(this.z));
                }
                this.z.setVisibility(0);
                this.z.setChecked(this.A);
                if (this.B == null) {
                    this.B = new ColorDrawable();
                }
                this.B.setBounds(0, 0, this.z.getMeasuredWidth(), 1);
                compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.a);
                if (compoundDrawablesRelative[2] != this.B) {
                    this.C = compoundDrawablesRelative[2];
                }
                TextViewCompat.setCompoundDrawablesRelative(this.a, compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.B, compoundDrawablesRelative[3]);
                this.z.setPadding(this.a.getPaddingLeft(), this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                return;
            }
            if (this.z != null && this.z.getVisibility() == 0) {
                this.z.setVisibility(8);
            }
            if (this.B != null) {
                compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.a);
                if (compoundDrawablesRelative[2] == this.B) {
                    TextViewCompat.setCompoundDrawablesRelative(this.a, compoundDrawablesRelative[0], compoundDrawablesRelative[1], this.C, compoundDrawablesRelative[3]);
                    this.B = null;
                }
            }
        }
    }

    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i) {
        setPasswordVisibilityToggleDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.x = drawable;
        if (this.z != null) {
            this.z.setImageDrawable(drawable);
        }
    }

    public void setPasswordVisibilityToggleContentDescription(@StringRes int i) {
        setPasswordVisibilityToggleContentDescription(i != 0 ? getResources().getText(i) : null);
    }

    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.y = charSequence;
        if (this.z != null) {
            this.z.setContentDescription(charSequence);
        }
    }

    @Nullable
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.x;
    }

    @Nullable
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.y;
    }

    public boolean isPasswordVisibilityToggleEnabled() {
        return this.w;
    }

    public void setPasswordVisibilityToggleEnabled(boolean z) {
        if (this.w != z) {
            this.w = z;
            if (!(z || !this.A || this.a == null)) {
                this.a.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            this.A = false;
            e();
        }
    }

    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.D = colorStateList;
        this.E = true;
        h();
    }

    public void setPasswordVisibilityToggleTintMode(@Nullable Mode mode) {
        this.F = mode;
        this.G = true;
        h();
    }

    private void b(boolean z) {
        if (this.w) {
            int selectionEnd = this.a.getSelectionEnd();
            if (f()) {
                this.a.setTransformationMethod(null);
                this.A = true;
            } else {
                this.a.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.A = false;
            }
            this.z.setChecked(this.A);
            if (z) {
                this.z.jumpDrawablesToCurrentState();
            }
            this.a.setSelection(selectionEnd);
        }
    }

    private boolean f() {
        return this.a != null && (this.a.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private boolean g() {
        return this.w && (f() || this.A);
    }

    private void h() {
        if (this.x == null) {
            return;
        }
        if (this.E || this.G) {
            this.x = DrawableCompat.wrap(this.x).mutate();
            if (this.E) {
                DrawableCompat.setTintList(this.x, this.D);
            }
            if (this.G) {
                DrawableCompat.setTintMode(this.x, this.F);
            }
            if (this.z != null && this.z.getDrawable() != this.x) {
                this.z.setImageDrawable(this.x);
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.g && this.a != null) {
            Rect rect = this.j;
            bk.b(this, this.a, rect);
            int compoundPaddingLeft = rect.left + this.a.getCompoundPaddingLeft();
            int compoundPaddingRight = rect.right - this.a.getCompoundPaddingRight();
            this.d.a(compoundPaddingLeft, rect.top + this.a.getCompoundPaddingTop(), compoundPaddingRight, rect.bottom - this.a.getCompoundPaddingBottom());
            this.d.b(compoundPaddingLeft, getPaddingTop(), compoundPaddingRight, (i4 - i2) - getPaddingBottom());
            this.d.recalculate();
        }
    }

    private void c(boolean z) {
        if (this.L != null && this.L.isRunning()) {
            this.L.cancel();
        }
        if (z && this.K) {
            a(1.0f);
        } else {
            this.d.b(1.0f);
        }
        this.J = false;
    }

    protected void drawableStateChanged() {
        boolean z = true;
        if (!this.N) {
            int a;
            this.N = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            if (!(ViewCompat.isLaidOut(this) && isEnabled())) {
                z = false;
            }
            a(z);
            c();
            if (this.d != null) {
                a = this.d.a(drawableState) | 0;
            } else {
                a = 0;
            }
            if (a != 0) {
                invalidate();
            }
            this.N = false;
        }
    }

    private void d(boolean z) {
        if (this.L != null && this.L.isRunning()) {
            this.L.cancel();
        }
        if (z && this.K) {
            a(0.0f);
        } else {
            this.d.b(0.0f);
        }
        this.J = true;
    }

    @VisibleForTesting
    void a(float f) {
        if (this.d.g() != f) {
            if (this.L == null) {
                this.L = new ValueAnimator();
                this.L.setInterpolator(a.a);
                this.L.setDuration(200);
                this.L.addUpdateListener(new bh(this));
            }
            this.L.setFloatValues(new float[]{this.d.g(), f});
            this.L.start();
        }
    }

    private static boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
