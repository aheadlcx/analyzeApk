package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.budejie.www.R;

public class CircleLoadLayout extends RelativeLayout {
    private CircleLoadView a;

    public CircleLoadLayout(Context context) {
        this(context, null);
    }

    public CircleLoadLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleLoadLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.circle_load_view, null);
        this.a = (CircleLoadView) inflate.findViewById(R.id.circleLoadView);
        addView(inflate);
    }

    public void a() {
        if (this.a != null) {
            this.a.a();
        }
        setVisibility(0);
    }

    public void b() {
        if (this.a != null) {
            this.a.b();
        }
        setVisibility(8);
    }
}
