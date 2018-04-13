package com.budejie.www.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class SplashADView extends RelativeLayout {
    public SplashADView(Context context) {
        super(context);
    }

    public SplashADView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SplashADView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
