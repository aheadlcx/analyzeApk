package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ScrollBottomScrollView extends ScrollView {
    private a a;

    public interface a {
        void a();
    }

    public ScrollBottomScrollView(Context context) {
        super(context);
    }

    public ScrollBottomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollBottomScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.a != null && getHeight() + i2 >= computeVerticalScrollRange()) {
            this.a.a();
        }
    }

    public void setScrollBottomListener(a aVar) {
        this.a = aVar;
    }
}
