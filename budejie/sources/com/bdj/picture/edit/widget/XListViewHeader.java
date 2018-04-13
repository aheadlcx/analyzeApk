package com.bdj.picture.edit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;

public class XListViewHeader extends LinearLayout {
    private LinearLayout a;
    private ImageView b;
    private ProgressBar c;
    private TextView d;
    private int e = 0;
    private Animation f;
    private Animation g;
    private final int h = 180;

    public XListViewHeader(Context context) {
        super(context);
        a(context);
    }

    public XListViewHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        this.a = (LinearLayout) LayoutInflater.from(context).inflate(e.xlistview_header_piclib, null);
        addView(this.a, layoutParams);
        setGravity(80);
        this.b = (ImageView) findViewById(d.mgc_xlistview_header_arrow);
        this.d = (TextView) findViewById(d.mgc_xlistview_header_hint_textview);
        this.c = (ProgressBar) findViewById(d.mgc_xlistview_header_progressbar);
        this.f = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.f.setDuration(180);
        this.f.setFillAfter(true);
        this.g = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.g.setDuration(180);
        this.g.setFillAfter(true);
    }

    public void setState(int i) {
        if (i != this.e) {
            if (i == 2) {
                this.b.clearAnimation();
                this.b.setVisibility(4);
                this.c.setVisibility(0);
            } else {
                this.b.setVisibility(0);
                this.c.setVisibility(4);
            }
            switch (i) {
                case 0:
                    if (this.e == 1) {
                        this.b.startAnimation(this.g);
                    }
                    if (this.e == 2) {
                        this.b.clearAnimation();
                    }
                    this.d.setText(h.xlistview_header_hint_normal);
                    break;
                case 1:
                    if (this.e != 1) {
                        this.b.clearAnimation();
                        this.b.startAnimation(this.f);
                        this.d.setText(h.xlistview_header_hint_ready);
                        break;
                    }
                    break;
                case 2:
                    this.d.setText(h.xlistview_header_hint_loading);
                    break;
            }
            this.e = i;
        }
    }

    public void setVisiableHeight(int i) {
        if (i < 0) {
            i = 0;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.height = i;
        this.a.setLayoutParams(layoutParams);
    }

    public int getVisiableHeight() {
        return this.a.getHeight();
    }
}
