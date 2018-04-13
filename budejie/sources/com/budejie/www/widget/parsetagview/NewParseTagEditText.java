package com.budejie.www.widget.parsetagview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class NewParseTagEditText extends TextView {
    private Context a;

    public NewParseTagEditText(Context context) {
        super(context);
        this.a = context;
    }

    public NewParseTagEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    public NewParseTagEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
    }

    public void setTextSpannable(String str) {
    }
}
