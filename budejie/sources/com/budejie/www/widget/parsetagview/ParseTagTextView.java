package com.budejie.www.widget.parsetagview;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

public class ParseTagTextView extends TextView {
    private final String a = "ParseTagTextView";
    private Context b;

    public ParseTagTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
    }

    public ParseTagTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    public void setTextSpannable(String str) {
        setText(b.a(this.b, str, true));
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void a(Context context, TextView textView, String str) {
        if (textView != null) {
            textView.setText(b.a(context, str, true));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
