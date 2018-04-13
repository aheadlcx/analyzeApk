package com.bdj.picture.edit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

public class CalculateDistanceScrollView extends ScrollView {
    private int a;

    public CalculateDistanceScrollView(Context context) {
        super(context);
    }

    public CalculateDistanceScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CalculateDistanceScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        Log.d("CalculateDistanceScrollView", "l,t,oldl,oldt=" + i + "," + i2 + "," + i3 + "," + i4);
        this.a = i2;
    }

    public int getScrollDistance() {
        return this.a;
    }
}
