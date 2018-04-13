package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class BaseGridView extends GridView {
    public BaseGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseGridView(Context context) {
        super(context);
    }

    public BaseGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
