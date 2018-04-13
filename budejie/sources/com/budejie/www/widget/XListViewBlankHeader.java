package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.budejie.www.R;

public class XListViewBlankHeader extends LinearLayout {
    private LinearLayout a;
    private View b;

    public XListViewBlankHeader(Context context) {
        super(context);
        a(context);
    }

    public XListViewBlankHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.a = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_blank_header, null);
        this.b = this.a.findViewById(R.id.black_header_view);
        addView(this.a, layoutParams);
    }

    public void a() {
        if (this.b != null) {
            this.b.setVisibility(0);
        }
    }
}
