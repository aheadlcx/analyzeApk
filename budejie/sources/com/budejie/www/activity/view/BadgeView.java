package com.budejie.www.activity.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;

public class BadgeView extends TextView {
    private static final int a = Color.parseColor("#CCff2d55");
    private static Animation b;
    private static Animation c;
    private Context d;
    private View e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private ShapeDrawable k;
    private int l;

    public BadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public BadgeView(Context context, View view) {
        this(context, null, 16842884, view, 0);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null, 0);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i, View view, int i2) {
        super(context, attributeSet, i);
        a(context, view, i2);
    }

    private void a(Context context, View view, int i) {
        this.d = context;
        this.e = view;
        this.l = i;
        this.f = 2;
        this.g = a(5);
        this.h = this.g;
        this.i = a;
        setTypeface(Typeface.DEFAULT_BOLD);
        int a = a(5);
        setPadding(a, a(1), a, 0);
        setTextColor(-1);
        b = new AlphaAnimation(0.0f, 1.0f);
        b.setInterpolator(new DecelerateInterpolator());
        b.setDuration(200);
        c = new AlphaAnimation(1.0f, 0.0f);
        c.setInterpolator(new AccelerateInterpolator());
        c.setDuration(200);
        this.j = false;
        if (this.e != null) {
            a(this.e);
        } else {
            a();
        }
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        ViewParent parent = view.getParent();
        View frameLayout = new FrameLayout(this.d);
        if (view instanceof TabWidget) {
            View childTabViewAt = ((TabWidget) view).getChildTabViewAt(this.l);
            this.e = childTabViewAt;
            ((ViewGroup) childTabViewAt).addView(frameLayout, new LayoutParams(-1, -1));
            setVisibility(8);
            frameLayout.addView(this);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        viewGroup.addView(frameLayout, indexOfChild, layoutParams);
        layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        view.setLayoutParams(layoutParams);
        frameLayout.addView(view);
        setVisibility(8);
        frameLayout.addView(this);
        viewGroup.invalidate();
    }

    public void a() {
        a(-2, -2, false, null);
    }

    public void a(Animation animation) {
        a(-2, -2, true, animation);
    }

    public void a(int i, int i2) {
        a(i, i2, false, null);
    }

    public void b() {
        a(false, null);
    }

    private void a(int i, int i2, boolean z, Animation animation) {
        if (getBackground() == null) {
            if (this.k == null) {
                this.k = getDefaultBackground();
            }
            setBackgroundDrawable(this.k);
        }
        c(i, i2);
        if (z) {
            startAnimation(animation);
        }
        setVisibility(0);
        this.j = true;
    }

    private void a(boolean z, Animation animation) {
        setVisibility(8);
        if (z) {
            startAnimation(animation);
        }
        this.j = false;
    }

    private ShapeDrawable getDefaultBackground() {
        int a = a(8);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{(float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a}, null, null));
        shapeDrawable.getPaint().setColor(this.i);
        return shapeDrawable;
    }

    private void c(int i, int i2) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        switch (this.f) {
            case 1:
                layoutParams.gravity = 51;
                layoutParams.setMargins(this.g, this.h, 0, 0);
                break;
            case 2:
                layoutParams.gravity = 53;
                layoutParams.setMargins(0, this.h, this.g, 0);
                break;
            case 3:
                layoutParams.gravity = 83;
                layoutParams.setMargins(this.g, 0, 0, this.h);
                break;
            case 4:
                layoutParams.gravity = 85;
                layoutParams.setMargins(0, 0, this.g, this.h);
                break;
            case 5:
                layoutParams.gravity = 17;
                layoutParams.setMargins(0, 0, 0, 0);
                break;
            case 6:
                layoutParams.gravity = 19;
                layoutParams.setMargins(this.g, 0, 0, 0);
                break;
            case 7:
                layoutParams.gravity = 21;
                layoutParams.setMargins(0, 0, this.g, 0);
                break;
        }
        setLayoutParams(layoutParams);
    }

    public View getTarget() {
        return this.e;
    }

    public boolean isShown() {
        return this.j;
    }

    public int getBadgePosition() {
        return this.f;
    }

    public void setBadgePosition(int i) {
        this.f = i;
    }

    public int getHorizontalBadgeMargin() {
        return this.g;
    }

    public int getVerticalBadgeMargin() {
        return this.h;
    }

    public void setBadgeMargin(int i) {
        this.g = i;
        this.h = i;
    }

    public void b(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public int getBadgeBackgroundColor() {
        return this.i;
    }

    public void setBadgeBackgroundColor(int i) {
        this.i = i;
        this.k = getDefaultBackground();
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }
}
