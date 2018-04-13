package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.budejie.www.R;
import com.budejie.www.util.an;

public class NavigationBar extends RelativeLayout {
    private LinearLayout a;
    private ViewGroup b;
    private ViewGroup c;
    private ViewGroup d;

    public NavigationBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (ViewGroup) findViewById(R.id.fl_navi_left);
        this.c = (ViewGroup) findViewById(R.id.fl_navi_mid);
        this.d = (ViewGroup) findViewById(R.id.fl_navi_right);
        this.a = (LinearLayout) findViewById(R.id.fl_navi_mask);
    }

    public LinearLayout getMaskLayout() {
        return this.a;
    }

    public void setMiddleView(View view) {
        this.c.removeAllViews();
        this.c.addView(view);
    }

    public View getMiddleView() {
        return this.c.getChildCount() > 0 ? this.c.getChildAt(0) : null;
    }

    public void setLeftView(View view) {
        for (int i = 0; i < this.b.getChildCount(); i++) {
            this.b.getChildAt(i).clearAnimation();
            this.b.removeViewAt(i);
        }
        this.b.addView(view);
    }

    public void setLeftViewTwo(View view) {
        this.b.addView(view, 1);
    }

    public void a(View view) {
        this.b.removeView(view);
    }

    public View getLeftView() {
        return this.b.getChildCount() > 0 ? this.b.getChildAt(0) : null;
    }

    public void setRightView(View view) {
        for (int i = 0; i < this.d.getChildCount(); i++) {
            this.d.getChildAt(i).clearAnimation();
            this.d.removeViewAt(i);
        }
        if (view != null) {
            this.d.addView(view);
        }
    }

    public void setRightViewTwo(View view) {
        if (view != null) {
            this.d.addView(view, 1);
        }
    }

    public void setRightFrameLayoutHeight(int i) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, i);
        layoutParams.addRule(15);
        layoutParams.addRule(11);
        layoutParams.rightMargin = an.a(getContext(), 10);
        this.d.setLayoutParams(layoutParams);
    }

    public View getRightView() {
        return this.d.getChildCount() > 0 ? this.d.getChildAt(0) : null;
    }
}
