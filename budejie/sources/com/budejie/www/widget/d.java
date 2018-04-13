package com.budejie.www.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.budejie.www.R;

public class d extends LinearLayout {
    private RelativeLayout a;
    private int b = 0;
    private int c = 0;
    private int d = 0;

    public int getMinHeight() {
        return this.d;
    }

    public int getTriggerHeight() {
        return this.c;
    }

    public RelativeLayout getContainer() {
        return this.a;
    }

    public d(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.a = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.personal_profile_head, this, false);
        this.d = this.a.getLayoutParams().height;
        this.c = this.d + com.bdj.picture.edit.util.d.a(getContext(), 80.0f);
        addView(this.a);
    }

    public int getState() {
        return this.b;
    }

    public void setState(int i) {
        if (i != this.b) {
            this.b = i;
        }
    }

    public void setVisiableHeight(int i) {
        if (i < this.d) {
            i = this.d;
        }
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.height = i;
        this.a.setLayoutParams(layoutParams);
        requestLayout();
    }

    public int getVisiableHeight() {
        return this.a.getHeight();
    }
}
