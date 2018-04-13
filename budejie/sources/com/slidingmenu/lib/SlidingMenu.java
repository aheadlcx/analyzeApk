package com.slidingmenu.lib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R$styleable;
import cn.v6.sixrooms.widgets.phone.ExpressionKeyboard;

public class SlidingMenu extends RelativeLayout {
    private boolean a;
    private CustomViewAbove b;
    private CustomViewBehind c;
    private SlidingMenu$e d;
    private SlidingMenu$b e;
    private Handler f;

    public interface f {
        void onOpened();
    }

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Activity activity, int i) {
        this((Context) activity, null);
        a(activity, i);
    }

    public SlidingMenu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        this.f = new Handler();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.c = new CustomViewBehind(context);
        addView(this.c, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.b = new CustomViewAbove(context);
        addView(this.b, layoutParams);
        this.b.setCustomViewBehind(this.c);
        this.c.setCustomViewAbove(this.b);
        this.b.setOnPageChangeListener(new c(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SlidingMenu);
        setMode(obtainStyledAttributes.getInt(R$styleable.SlidingMenu_mode, 0));
        int resourceId = obtainStyledAttributes.getResourceId(R$styleable.SlidingMenu_viewAbove, -1);
        if (resourceId != -1) {
            setContent(resourceId);
        } else {
            setContent(new FrameLayout(context));
        }
        resourceId = obtainStyledAttributes.getResourceId(R$styleable.SlidingMenu_viewBehind, -1);
        if (resourceId != -1) {
            setMenu(resourceId);
        } else {
            setMenu(new FrameLayout(context));
        }
        setTouchModeAbove(obtainStyledAttributes.getInt(R$styleable.SlidingMenu_touchModeAbove, 0));
        setTouchModeBehind(obtainStyledAttributes.getInt(R$styleable.SlidingMenu_touchModeBehind, 0));
        resourceId = (int) obtainStyledAttributes.getDimension(R$styleable.SlidingMenu_behindOffset, -1.0f);
        int dimension = (int) obtainStyledAttributes.getDimension(R$styleable.SlidingMenu_behindWidth, -1.0f);
        if (resourceId == -1 || dimension == -1) {
            if (resourceId != -1) {
                setBehindOffset(resourceId);
            } else if (dimension != -1) {
                setBehindWidth(dimension);
            } else {
                setBehindOffset(0);
            }
            setBehindScrollScale(obtainStyledAttributes.getFloat(R$styleable.SlidingMenu_behindScrollScale, 0.33f));
            resourceId = obtainStyledAttributes.getResourceId(R$styleable.SlidingMenu_shadowDrawable, -1);
            if (resourceId != -1) {
                setShadowDrawable(resourceId);
            }
            setShadowWidth((int) obtainStyledAttributes.getDimension(R$styleable.SlidingMenu_shadowWidth, 0.0f));
            setFadeEnabled(obtainStyledAttributes.getBoolean(R$styleable.SlidingMenu_fadeEnabled, true));
            setFadeDegree(obtainStyledAttributes.getFloat(R$styleable.SlidingMenu_fadeDegree, 0.33f));
            setSelectorEnabled(obtainStyledAttributes.getBoolean(R$styleable.SlidingMenu_selectorEnabled, false));
            resourceId = obtainStyledAttributes.getResourceId(R$styleable.SlidingMenu_selectorDrawable, -1);
            if (resourceId != -1) {
                setSelectorDrawable(resourceId);
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException("Cannot set both behindOffset and behindWidth for a SlidingMenu");
    }

    public void a(Activity activity, int i) {
        a(activity, i, false);
    }

    public void a(Activity activity, int i, boolean z) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("slideStyle must be either SLIDING_WINDOW or SLIDING_CONTENT");
        } else if (getParent() != null) {
            throw new IllegalStateException("This SlidingMenu appears to already be attached");
        } else {
            TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842836});
            int resourceId = obtainStyledAttributes.getResourceId(0, 0);
            obtainStyledAttributes.recycle();
            ViewGroup viewGroup;
            View view;
            switch (i) {
                case 0:
                    this.a = false;
                    viewGroup = (ViewGroup) activity.getWindow().getDecorView();
                    view = (ViewGroup) viewGroup.getChildAt(0);
                    view.setBackgroundResource(resourceId);
                    viewGroup.removeView(view);
                    viewGroup.addView(this);
                    setContent(view);
                    return;
                case 1:
                    this.a = z;
                    viewGroup = (ViewGroup) activity.findViewById(16908290);
                    view = viewGroup.getChildAt(0);
                    viewGroup.removeView(view);
                    viewGroup.addView(this);
                    setContent(view);
                    if (view.getBackground() == null) {
                        view.setBackgroundResource(resourceId);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void setContent(int i) {
        setContent(LayoutInflater.from(getContext()).inflate(i, null));
    }

    public void setContent(View view) {
        this.b.setContent(view);
        c();
    }

    public View getContent() {
        return this.b.getContent();
    }

    public void setMenu(int i) {
        setMenu(LayoutInflater.from(getContext()).inflate(i, null));
    }

    public void setMenu(View view) {
        this.c.setContent(view);
    }

    public View getMenu() {
        return this.c.getContent();
    }

    public void setSecondaryMenu(int i) {
        setSecondaryMenu(LayoutInflater.from(getContext()).inflate(i, null));
    }

    public void setSecondaryMenu(View view) {
        this.c.setSecondaryContent(view);
    }

    public View getSecondaryMenu() {
        return this.c.getSecondaryContent();
    }

    public void setSlidingEnabled(boolean z) {
        this.b.setSlidingEnabled(z);
    }

    public void setMode(int i) {
        if (i == 0 || i == 1 || i == 2) {
            this.c.setMode(i);
            return;
        }
        throw new IllegalStateException("SlidingMenu mode must be LEFT, RIGHT, or LEFT_RIGHT");
    }

    public int getMode() {
        return this.c.getMode();
    }

    public void setStatic(boolean z) {
        if (z) {
            setSlidingEnabled(false);
            this.b.setCustomViewBehind(null);
            this.b.setCurrentItem(1);
            return;
        }
        this.b.setCurrentItem(1);
        this.b.setCustomViewBehind(this.c);
        setSlidingEnabled(true);
    }

    public void a() {
        a(true);
    }

    public void a(boolean z) {
        this.b.a(0, z);
    }

    public void b() {
        b(true);
    }

    public void b(boolean z) {
        this.b.a(2, z);
    }

    public void c() {
        c(true);
    }

    public void c(boolean z) {
        this.b.a(1, z);
    }

    public void d() {
        d(true);
    }

    public void d(boolean z) {
        if (e()) {
            c(z);
        } else {
            a(z);
        }
    }

    public boolean e() {
        return this.b.getCurrentItem() == 0 || this.b.getCurrentItem() == 2;
    }

    public boolean f() {
        return this.b.getCurrentItem() == 2;
    }

    public int getBehindOffset() {
        return ((RelativeLayout.LayoutParams) this.c.getLayoutParams()).rightMargin;
    }

    public void setBehindOffset(int i) {
        this.c.setWidthOffset(i);
    }

    public void setBehindOffsetRes(int i) {
        setBehindOffset((int) getContext().getResources().getDimension(i));
    }

    public void setAboveOffset(int i) {
        this.b.setAboveOffset(i);
    }

    public void setAboveOffsetRes(int i) {
        setAboveOffset((int) getContext().getResources().getDimension(i));
    }

    public void setBehindWidth(int i) {
        int i2;
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        try {
            Class[] clsArr = new Class[]{Point.class};
            Point point = new Point();
            Display.class.getMethod("getSize", clsArr).invoke(defaultDisplay, new Object[]{point});
            i2 = point.x;
        } catch (Exception e) {
            i2 = defaultDisplay.getWidth();
        }
        setBehindOffset(i2 - i);
    }

    public void setBehindWidthRes(int i) {
        setBehindWidth((int) getContext().getResources().getDimension(i));
    }

    public float getBehindScrollScale() {
        return this.c.getScrollScale();
    }

    public void setBehindScrollScale(float f) {
        if (f >= 0.0f || f <= 1.0f) {
            this.c.setScrollScale(f);
            return;
        }
        throw new IllegalStateException("ScrollScale must be between 0 and 1");
    }

    public void setBehindCanvasTransformer(SlidingMenu$a slidingMenu$a) {
        this.c.setCanvasTransformer(slidingMenu$a);
    }

    public int getTouchModeAbove() {
        return this.b.getTouchMode();
    }

    public void setTouchModeAbove(int i) {
        if (i == 1 || i == 0 || i == 2) {
            this.b.setTouchMode(i);
            return;
        }
        throw new IllegalStateException("TouchMode must be set to eitherTOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
    }

    public void setExpressionPage(ExpressionKeyboard expressionKeyboard) {
        this.b.setExpressionPage(expressionKeyboard);
    }

    public void setTouchModeBehind(int i) {
        if (i == 1 || i == 0 || i == 2) {
            this.c.setTouchMode(i);
            return;
        }
        throw new IllegalStateException("TouchMode must be set to eitherTOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
    }

    public void setShadowDrawable(int i) {
        setShadowDrawable(getContext().getResources().getDrawable(i));
    }

    public void setShadowDrawable(Drawable drawable) {
        this.c.setShadowDrawable(drawable);
    }

    public void setSecondaryShadowDrawable(int i) {
        setSecondaryShadowDrawable(getContext().getResources().getDrawable(i));
    }

    public void setSecondaryShadowDrawable(Drawable drawable) {
        this.c.setSecondaryShadowDrawable(drawable);
    }

    public void setShadowWidthRes(int i) {
        setShadowWidth((int) getResources().getDimension(i));
    }

    public void setShadowWidth(int i) {
        this.c.setShadowWidth(i);
    }

    public void setFadeEnabled(boolean z) {
        this.c.setFadeEnabled(z);
    }

    public void setFadeDegree(float f) {
        this.c.setFadeDegree(f);
    }

    public void setSelectorEnabled(boolean z) {
        this.c.setSelectorEnabled(true);
    }

    public void setSelectedView(View view) {
        this.c.setSelectedView(view);
    }

    public void setSelectorDrawable(int i) {
        this.c.setSelectorBitmap(BitmapFactory.decodeResource(getResources(), i));
    }

    public void setSelectorBitmap(Bitmap bitmap) {
        this.c.setSelectorBitmap(bitmap);
    }

    public void setOnOpenListener(SlidingMenu$e slidingMenu$e) {
        this.d = slidingMenu$e;
    }

    public void setOnCloseListener(SlidingMenu$b slidingMenu$b) {
        this.e = slidingMenu$b;
    }

    public void setOnOpenedListener(f fVar) {
        this.b.setOnOpenedListener(fVar);
    }

    public void setOnMoveddListener(SlidingMenu$d slidingMenu$d) {
        this.b.setOnMovedListener(slidingMenu$d);
    }

    public void setOnClosedListener(SlidingMenu$c slidingMenu$c) {
        this.b.setOnClosedListener(slidingMenu$c);
    }

    protected Parcelable onSaveInstanceState() {
        return new SlidingMenu$SavedState(super.onSaveInstanceState(), this.b.getCurrentItem());
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SlidingMenu$SavedState slidingMenu$SavedState = (SlidingMenu$SavedState) parcelable;
        super.onRestoreInstanceState(slidingMenu$SavedState.getSuperState());
        this.b.setCurrentItem(slidingMenu$SavedState.a());
    }

    protected boolean fitSystemWindows(Rect rect) {
        int i = rect.left;
        int i2 = rect.right;
        int i3 = rect.top;
        int i4 = rect.bottom;
        if (!this.a) {
            Log.v("SlidingMenu", "setting padding!");
            setPadding(i, i3, i2, i4);
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public void a(float f) {
        int i = 0;
        if (VERSION.SDK_INT >= 11) {
            int i2;
            if (f <= 0.0f || f >= 1.0f) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i2 != 0) {
                i = 2;
            }
            if (i != getContent().getLayerType()) {
                this.f.post(new d(this, i));
            }
        }
    }
}
