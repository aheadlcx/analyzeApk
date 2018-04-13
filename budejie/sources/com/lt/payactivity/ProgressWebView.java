package com.lt.payactivity;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.ProgressBar;

public class ProgressWebView extends WebView {
    private ProgressBar a;

    public ProgressWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ProgressBar(context, null, 16842872);
        this.a.setLayoutParams(new LayoutParams(-1, 3, 0, 0));
        addView(this.a);
        setWebChromeClient(new ProgressWebView$a(this));
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.x = i;
        layoutParams.y = i2;
        this.a.setLayoutParams(layoutParams);
        super.onScrollChanged(i, i2, i3, i4);
    }
}
