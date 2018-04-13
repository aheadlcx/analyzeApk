package com.bdj.picture.edit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;

public class XListViewFooter extends LinearLayout {
    private Context a;
    private View b;
    private View c;
    private TextView d;

    public XListViewFooter(Context context) {
        super(context);
        a(context);
    }

    public XListViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void setState(int i) {
        this.d.setVisibility(4);
        this.c.setVisibility(4);
        this.d.setVisibility(4);
        if (i == 1) {
            this.d.setVisibility(0);
            this.d.setText(h.xlistview_footer_hint_ready);
        } else if (i == 2) {
            this.c.setVisibility(0);
        } else if (i == 3) {
            this.d.setVisibility(0);
            this.d.setText(h.xlistview_footer_hint_no_more_data);
        } else {
            this.d.setVisibility(0);
            this.d.setText(h.xlistview_header_hint_normal);
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
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.a).inflate(e.xlistview_footer_piclib, null);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.b = linearLayout.findViewById(d.mgc_xlistview_footer_content);
        this.c = linearLayout.findViewById(d.mgc_xlistview_footer_progressbar);
        this.d = (TextView) linearLayout.findViewById(d.mgc_xlistview_footer_hint_textview);
    }
}
