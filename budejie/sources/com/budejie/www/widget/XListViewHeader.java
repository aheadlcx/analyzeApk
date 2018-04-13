package com.budejie.www.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.budejie.www.R;

public class XListViewHeader extends LinearLayout {
    private LinearLayout a;
    private ImageView b;
    private ProgressBar c;
    private TextView d;
    private int e = 0;
    private final int f = 180;

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
        this.a = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
        addView(this.a, layoutParams);
        setGravity(80);
        this.b = (ImageView) findViewById(R.id.xlistview_header_arrow);
        this.d = (TextView) findViewById(R.id.xlistview_header_hint_textview);
        this.c = (ProgressBar) findViewById(R.id.xlistview_header_progressbar);
    }

    public void setState(int i) {
        if (i != this.e) {
            switch (i) {
                case 0:
                    this.d.setText(R.string.xlistview_header_hint_normal);
                    this.b.setBackgroundResource(R.drawable.list_view_pull);
                    break;
                case 1:
                    if (this.e != 1) {
                        this.b.setBackgroundResource(R.drawable.list_view_release);
                        this.d.setText(R.string.xlistview_header_hint_ready);
                        break;
                    }
                    break;
                case 2:
                    this.b.setBackgroundResource(R.anim.list_refreshing_anim);
                    ((AnimationDrawable) this.b.getBackground()).start();
                    this.d.setText(R.string.xlistview_header_hint_loading);
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
