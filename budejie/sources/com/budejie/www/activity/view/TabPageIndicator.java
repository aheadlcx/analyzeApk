package com.budejie.www.activity.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.budejie.www.R;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.List;

public class TabPageIndicator extends LinearLayout {
    private List<View> a = new ArrayList();
    private int b = R.drawable.tap_page_indicator;
    private int c = 10;

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setVisibility(8);
    }

    public TabPageIndicator(Context context) {
        super(context);
        setVisibility(8);
    }

    @SuppressLint({"NewApi"})
    public TabPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setVisibility(8);
    }

    public void a(int i) {
        int i2 = 1;
        if (i <= 1) {
            setVisibility(8);
            return;
        }
        this.a.clear();
        removeAllViews();
        setVisibility(0);
        int a = an.a(getContext(), this.c);
        View imageView = new ImageView(getContext());
        imageView.setBackgroundResource(this.b);
        addView(imageView);
        this.a.add(imageView);
        while (i2 < i) {
            addView(new View(getContext()), new LayoutParams(a, -1));
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(this.b);
            addView(imageView);
            this.a.add(imageView);
            i2++;
        }
        setSelectIndicator(0);
    }

    public void setPointResource(int i) {
        this.b = i;
    }

    public void setmSpace(int i) {
        this.c = i;
    }

    public void setSelectIndicator(int i) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            boolean z;
            View view = (View) this.a.get(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            view.setSelected(z);
        }
    }
}
