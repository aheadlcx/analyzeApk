package com.budejie.www.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.budejie.www.R;

public class SelectLabelLayout extends RelativeLayout {
    private TextView a;
    private TextView b;
    private View c;

    public SelectLabelLayout(Context context) {
        this(context, null);
    }

    public SelectLabelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SelectLabelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.c = LayoutInflater.from(context).inflate(R.layout.tougao_select_label_view, null);
        addView(this.c);
        a();
    }

    private void a() {
        this.a = (TextView) this.c.findViewById(R.id.select_label_button);
        this.b = (TextView) this.c.findViewById(R.id.label_button);
        this.b.setVisibility(8);
    }

    public void setListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.a.setOnClickListener(onClickListener);
            this.b.setOnClickListener(onClickListener);
        }
    }

    public void setLabelName(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.b.setVisibility(0);
            this.a.setVisibility(8);
            this.b.setText(str);
        }
    }
}
