package com.bdj.picture.edit.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class TabHorizontalScrollView extends HorizontalScrollView {
    private View a;
    private ImageView b;
    private ImageView c;
    private int d = 0;
    private Activity e;

    public TabHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TabHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TabHorizontalScrollView(Context context) {
        super(context);
    }

    public void a(View view, ImageView imageView, ImageView imageView2, Activity activity) {
        this.e = activity;
        this.a = view;
        this.b = imageView;
        this.c = imageView2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.e.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.d = displayMetrics.widthPixels;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        Log.i("MainActivity", "view width:" + this.a.getWidth() + "window width:" + this.d + ",l:" + i + ",oldl:" + i3);
        if (!this.e.isFinishing() && this.a != null && this.b != null && this.c != null) {
            if (this.a.getWidth() < this.d) {
                this.b.setVisibility(4);
                this.c.setVisibility(4);
            } else if (i == 0) {
                this.b.setVisibility(4);
                this.c.setVisibility(0);
            } else if (this.a.getWidth() - i == this.d) {
                this.b.setVisibility(0);
                this.c.setVisibility(4);
            } else {
                this.b.setVisibility(0);
                this.c.setVisibility(0);
            }
        }
    }
}
