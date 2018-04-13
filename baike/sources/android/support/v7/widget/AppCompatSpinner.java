package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.ContextThemeWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final int[] a = new int[]{16843505};
    private final p b;
    private final Context c;
    private ForwardingListener d;
    private SpinnerAdapter e;
    private final boolean f;
    private b g;
    private int h;
    private final Rect i;

    private static class a implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public a(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Theme theme) {
            this.a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) spinnerAdapter;
            }
            if (theme == null) {
                return;
            }
            if (VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            } else if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                ThemedSpinnerAdapter themedSpinnerAdapter2 = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter2.getDropDownViewTheme() == null) {
                    themedSpinnerAdapter2.setDropDownViewTheme(theme);
                }
            }
        }

        public int getCount() {
            return this.a == null ? 0 : this.a.getCount();
        }

        public Object getItem(int i) {
            return this.a == null ? null : this.a.getItem(i);
        }

        public long getItemId(int i) {
            return this.a == null ? -1 : this.a.getItemId(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            if (this.a == null) {
                return null;
            }
            return this.a.getDropDownView(i, view, viewGroup);
        }

        public boolean hasStableIds() {
            return this.a != null && this.a.hasStableIds();
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.b;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }
    }

    private class b extends ListPopupWindow {
        ListAdapter a;
        final /* synthetic */ AppCompatSpinner b;
        private CharSequence h;
        private final Rect i = new Rect();

        public b(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attributeSet, int i) {
            this.b = appCompatSpinner;
            super(context, attributeSet, i);
            setAnchorView(appCompatSpinner);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new u(this, appCompatSpinner));
        }

        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.a = listAdapter;
        }

        public CharSequence getHintText() {
            return this.h;
        }

        public void setPromptText(CharSequence charSequence) {
            this.h = charSequence;
        }

        void a() {
            int i;
            int i2;
            Drawable background = getBackground();
            if (background != null) {
                background.getPadding(this.b.i);
                if (ViewUtils.isLayoutRtl(this.b)) {
                    i = this.b.i.right;
                } else {
                    i = -this.b.i.left;
                }
                i2 = i;
            } else {
                Rect b = this.b.i;
                this.b.i.right = 0;
                b.left = 0;
                i2 = 0;
            }
            int paddingLeft = this.b.getPaddingLeft();
            int paddingRight = this.b.getPaddingRight();
            int width = this.b.getWidth();
            if (this.b.h == -2) {
                int a = this.b.a((SpinnerAdapter) this.a, getBackground());
                i = (this.b.getContext().getResources().getDisplayMetrics().widthPixels - this.b.i.left) - this.b.i.right;
                if (a <= i) {
                    i = a;
                }
                setContentWidth(Math.max(i, (width - paddingLeft) - paddingRight));
            } else if (this.b.h == -1) {
                setContentWidth((width - paddingLeft) - paddingRight);
            } else {
                setContentWidth(this.b.h);
            }
            if (ViewUtils.isLayoutRtl(this.b)) {
                i = ((width - paddingRight) - getWidth()) + i2;
            } else {
                i = i2 + paddingLeft;
            }
            setHorizontalOffset(i);
        }

        public void show() {
            boolean isShowing = isShowing();
            a();
            setInputMethodMode(2);
            super.show();
            getListView().setChoiceMode(1);
            setSelection(this.b.getSelectedItemPosition());
            if (!isShowing) {
                ViewTreeObserver viewTreeObserver = this.b.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    OnGlobalLayoutListener vVar = new v(this);
                    viewTreeObserver.addOnGlobalLayoutListener(vVar);
                    setOnDismissListener(new w(this, vVar));
                }
            }
        }

        boolean a(View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.i);
        }
    }

    public AppCompatSpinner(Context context) {
        this(context, null);
    }

    public AppCompatSpinner(Context context, int i) {
        this(context, null, R.attr.spinnerStyle, i);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, null);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2, Theme theme) {
        TypedArray obtainStyledAttributes;
        Throwable e;
        CharSequence[] textArray;
        SpinnerAdapter arrayAdapter;
        super(context, attributeSet, i);
        this.i = new Rect();
        TintTypedArray obtainStyledAttributes2 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.Spinner, i, 0);
        this.b = new p(this);
        if (theme != null) {
            this.c = new ContextThemeWrapper(context, theme);
        } else {
            int resourceId = obtainStyledAttributes2.getResourceId(R.styleable.Spinner_popupTheme, 0);
            if (resourceId != 0) {
                this.c = new ContextThemeWrapper(context, resourceId);
            } else {
                this.c = VERSION.SDK_INT < 23 ? context : null;
            }
        }
        if (this.c != null) {
            b bVar;
            TintTypedArray obtainStyledAttributes3;
            if (i2 == -1) {
                try {
                    obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a, i, 0);
                    try {
                        if (obtainStyledAttributes.hasValue(0)) {
                            i2 = obtainStyledAttributes.getInt(0, 0);
                        }
                        if (obtainStyledAttributes != null) {
                            obtainStyledAttributes.recycle();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                            if (obtainStyledAttributes != null) {
                                obtainStyledAttributes.recycle();
                            }
                            if (i2 == 1) {
                                bVar = new b(this, this.c, attributeSet, i);
                                obtainStyledAttributes3 = TintTypedArray.obtainStyledAttributes(this.c, attributeSet, R.styleable.Spinner, i, 0);
                                this.h = obtainStyledAttributes3.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
                                bVar.setBackgroundDrawable(obtainStyledAttributes3.getDrawable(R.styleable.Spinner_android_popupBackground));
                                bVar.setPromptText(obtainStyledAttributes2.getString(R.styleable.Spinner_android_prompt));
                                obtainStyledAttributes3.recycle();
                                this.g = bVar;
                                this.d = new t(this, this, bVar);
                            }
                            textArray = obtainStyledAttributes2.getTextArray(R.styleable.Spinner_android_entries);
                            if (textArray != null) {
                                arrayAdapter = new ArrayAdapter(context, 17367048, textArray);
                                arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                                setAdapter(arrayAdapter);
                            }
                            obtainStyledAttributes2.recycle();
                            this.f = true;
                            if (this.e != null) {
                                setAdapter(this.e);
                                this.e = null;
                            }
                            this.b.a(attributeSet, i);
                        } catch (Throwable th) {
                            e = th;
                            if (obtainStyledAttributes != null) {
                                obtainStyledAttributes.recycle();
                            }
                            throw e;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    obtainStyledAttributes = null;
                    Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                    if (obtainStyledAttributes != null) {
                        obtainStyledAttributes.recycle();
                    }
                    if (i2 == 1) {
                        bVar = new b(this, this.c, attributeSet, i);
                        obtainStyledAttributes3 = TintTypedArray.obtainStyledAttributes(this.c, attributeSet, R.styleable.Spinner, i, 0);
                        this.h = obtainStyledAttributes3.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
                        bVar.setBackgroundDrawable(obtainStyledAttributes3.getDrawable(R.styleable.Spinner_android_popupBackground));
                        bVar.setPromptText(obtainStyledAttributes2.getString(R.styleable.Spinner_android_prompt));
                        obtainStyledAttributes3.recycle();
                        this.g = bVar;
                        this.d = new t(this, this, bVar);
                    }
                    textArray = obtainStyledAttributes2.getTextArray(R.styleable.Spinner_android_entries);
                    if (textArray != null) {
                        arrayAdapter = new ArrayAdapter(context, 17367048, textArray);
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        setAdapter(arrayAdapter);
                    }
                    obtainStyledAttributes2.recycle();
                    this.f = true;
                    if (this.e != null) {
                        setAdapter(this.e);
                        this.e = null;
                    }
                    this.b.a(attributeSet, i);
                } catch (Throwable th2) {
                    e = th2;
                    obtainStyledAttributes = null;
                    if (obtainStyledAttributes != null) {
                        obtainStyledAttributes.recycle();
                    }
                    throw e;
                }
            }
            if (i2 == 1) {
                bVar = new b(this, this.c, attributeSet, i);
                obtainStyledAttributes3 = TintTypedArray.obtainStyledAttributes(this.c, attributeSet, R.styleable.Spinner, i, 0);
                this.h = obtainStyledAttributes3.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
                bVar.setBackgroundDrawable(obtainStyledAttributes3.getDrawable(R.styleable.Spinner_android_popupBackground));
                bVar.setPromptText(obtainStyledAttributes2.getString(R.styleable.Spinner_android_prompt));
                obtainStyledAttributes3.recycle();
                this.g = bVar;
                this.d = new t(this, this, bVar);
            }
        }
        textArray = obtainStyledAttributes2.getTextArray(R.styleable.Spinner_android_entries);
        if (textArray != null) {
            arrayAdapter = new ArrayAdapter(context, 17367048, textArray);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            setAdapter(arrayAdapter);
        }
        obtainStyledAttributes2.recycle();
        this.f = true;
        if (this.e != null) {
            setAdapter(this.e);
            this.e = null;
        }
        this.b.a(attributeSet, i);
    }

    public Context getPopupContext() {
        if (this.g != null) {
            return this.c;
        }
        if (VERSION.SDK_INT >= 23) {
            return super.getPopupContext();
        }
        return null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        if (this.g != null) {
            this.g.setBackgroundDrawable(drawable);
        } else if (VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int i) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i));
    }

    public Drawable getPopupBackground() {
        if (this.g != null) {
            return this.g.getBackground();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    public void setDropDownVerticalOffset(int i) {
        if (this.g != null) {
            this.g.setVerticalOffset(i);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.g != null) {
            return this.g.getVerticalOffset();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    public void setDropDownHorizontalOffset(int i) {
        if (this.g != null) {
            this.g.setHorizontalOffset(i);
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.g != null) {
            return this.g.getHorizontalOffset();
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    public void setDropDownWidth(int i) {
        if (this.g != null) {
            this.h = i;
        } else if (VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i);
        }
    }

    public int getDropDownWidth() {
        if (this.g != null) {
            return this.h;
        }
        if (VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.f) {
            super.setAdapter(spinnerAdapter);
            if (this.g != null) {
                this.g.setAdapter(new a(spinnerAdapter, (this.c == null ? getContext() : this.c).getTheme()));
                return;
            }
            return;
        }
        this.e = spinnerAdapter;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.d == null || !this.d.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.g != null && MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.g == null) {
            return super.performClick();
        }
        if (!this.g.isShowing()) {
            this.g.show();
        }
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        if (this.g != null) {
            this.g.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public CharSequence getPrompt() {
        return this.g != null ? this.g.getHintText() : super.getPrompt();
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.b != null) {
            this.b.a(drawable);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.b != null) {
            this.b.a(colorStateList);
        }
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        return this.b != null ? this.b.a() : null;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable Mode mode) {
        if (this.b != null) {
            this.b.a(mode);
        }
    }

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public Mode getSupportBackgroundTintMode() {
        return this.b != null ? this.b.b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.b != null) {
            this.b.c();
        }
    }

    int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int i = 0;
        max = 0;
        while (max2 < min) {
            View view2;
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != max) {
                view2 = null;
            } else {
                itemViewType = max;
                view2 = view;
            }
            view = spinnerAdapter.getView(max2, view2, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view.getMeasuredWidth());
            max2++;
            max = itemViewType;
        }
        if (drawable == null) {
            return i;
        }
        drawable.getPadding(this.i);
        return (this.i.left + this.i.right) + i;
    }
}
