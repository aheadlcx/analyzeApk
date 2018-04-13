package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.widget.curtain.FloatVideoLayout;

public class BottomRelativeLayout extends RelativeLayout {
    private Animation a;
    private boolean b;
    private int c = 0;
    private Animation d;
    private DisplayMetrics e;

    public BottomRelativeLayout(Context context) {
        super(context);
    }

    public BottomRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setDisplayMetrics(DisplayMetrics displayMetrics) {
        this.e = displayMetrics;
    }

    public void a() {
        if (!this.b && this.c != 0) {
            if (this.a == null) {
                this.a = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_navigation_show_anim);
                this.a.setAnimationListener(new BottomRelativeLayout$1(this));
            }
            setVisibility(0);
            this.c = 0;
            FloatVideoLayout.b(getContext(), true);
            startAnimation(this.a);
        }
    }

    public void b() {
        if (!this.b && this.c != 8) {
            if (this.d == null) {
                this.d = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_navigation_hide_anim);
                this.d.setAnimationListener(new BottomRelativeLayout$2(this));
            }
            startAnimation(this.d);
        }
    }
}
