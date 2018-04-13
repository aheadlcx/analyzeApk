package com.budejie.www.widget.xrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.h.c;

public class LoadingMoreFooter extends LinearLayout {
    private String a;
    private String b;
    private String c;
    private View d;
    private View e;
    private TextView f;

    public LoadingMoreFooter(Context context) {
        super(context);
        a(context);
    }

    public LoadingMoreFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void setLoadingHint(String str) {
        this.a = str;
    }

    public void setNoMoreHint(String str) {
        this.b = str;
    }

    public void setLoadingDoneHint(String str) {
        this.c = str;
    }

    public void a(Context context) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_footer, null);
        addView(linearLayout);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.d = linearLayout.findViewById(R.id.footer_content_layout);
        this.e = linearLayout.findViewById(R.id.xlistview_footer_progressbar);
        this.f = (TextView) linearLayout.findViewById(R.id.xlistview_footer_hint_textview);
        this.a = "正在刷新...";
        this.b = "没有更多了";
        this.c = "加载完成";
    }

    public void setState(int i) {
        switch (i) {
            case 0:
                this.e.setVisibility(0);
                this.f.setText(this.a);
                setVisibility(0);
                return;
            case 1:
                this.f.setText(this.c);
                setVisibility(4);
                return;
            case 2:
                this.f.setText(this.b);
                this.e.setVisibility(8);
                setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void setRandomState(String str) {
        this.b = str;
        this.d.setBackgroundResource(c.a().b(R.attr.list_item_bg));
        this.f.setVisibility(0);
        this.f.setTextColor(c.a().c(R.attr.theme_red_color));
        this.e.setVisibility(8);
    }
}
