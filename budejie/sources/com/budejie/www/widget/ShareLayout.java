package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class ShareLayout extends RelativeLayout {
    private Context a;
    private LayoutInflater b;

    public ShareLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShareLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        this.b = LayoutInflater.from(this.a);
        a();
    }

    private void a() {
    }
}
