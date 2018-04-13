package com.budejie.www.activity.label.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class DeputyListView extends ListView {
    public DeputyListView(Context context) {
        super(context);
    }

    public DeputyListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DeputyListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
