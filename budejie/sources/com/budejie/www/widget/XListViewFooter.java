package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;

public class XListViewFooter extends LinearLayout {
    private Context a;
    private View b;
    private View c;
    private View d;
    private TextView e;
    private int f = 0;

    public XListViewFooter(Context context) {
        super(context);
        a(context);
    }

    public XListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public int getState() {
        return this.f;
    }

    public void setState(int i) {
        this.f = i;
        this.e.setVisibility(4);
        this.d.setVisibility(4);
        this.e.setVisibility(4);
        if (i == 1) {
            this.e.setVisibility(0);
            this.e.setText(R.string.xlistview_footer_hint_ready);
        } else if (i == 2) {
            this.e.setVisibility(0);
            this.d.setVisibility(0);
            this.e.setText(R.string.xlistview_header_hint_loading);
        } else {
            this.e.setVisibility(0);
            this.e.setText(R.string.xlistview_footer_hint_normal);
        }
    }

    public void setBottomMargin(int i) {
        if (i >= 0) {
            LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
            layoutParams.bottomMargin = i;
            this.b.setLayoutParams(layoutParams);
        }
    }

    public int getBottomMargin() {
        return ((LayoutParams) this.b.getLayoutParams()).bottomMargin;
    }

    public void a() {
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        layoutParams.height = 0;
        this.b.setLayoutParams(layoutParams);
    }

    public void b() {
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        layoutParams.height = -2;
        this.b.setLayoutParams(layoutParams);
    }

    private void a(Context context) {
        this.a = context;
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.a).inflate(R.layout.xlistview_footer, null);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.b = linearLayout.findViewById(R.id.xlistview_footer_content);
        this.c = linearLayout.findViewById(R.id.footer_content_layout);
        this.d = linearLayout.findViewById(R.id.xlistview_footer_progressbar);
        this.e = (TextView) linearLayout.findViewById(R.id.xlistview_footer_hint_textview);
    }

    public View getContentView() {
        return this.b;
    }

    public View getContentChildView() {
        return this.c;
    }

    public View getProgressBar() {
        return this.d;
    }

    public TextView getHintView() {
        return this.e;
    }
}
