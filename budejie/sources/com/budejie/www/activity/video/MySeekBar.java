package com.budejie.www.activity.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class MySeekBar extends SeekBar {
    public MySeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MySeekBar(Context context) {
        super(context);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
