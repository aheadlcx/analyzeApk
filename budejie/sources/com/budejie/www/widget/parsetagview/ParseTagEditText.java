package com.budejie.www.widget.parsetagview;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class ParseTagEditText extends EditText {
    private final String a = "ParseTagEditText";
    private Context b;
    private boolean c = false;
    private int d = 0;
    private int e = 0;
    private a f;

    interface a {
    }

    public ParseTagEditText(Context context) {
        super(context);
        this.b = context;
    }

    public ParseTagEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
    }

    public ParseTagEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = context;
    }

    public void setTextChangedListener(TextWatcher textWatcher) {
        addTextChangedListener(textWatcher);
    }

    public void setListener(a aVar) {
        this.f = aVar;
    }

    public void setTextSpannable(String str) {
    }
}
