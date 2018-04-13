package com.budejie.www.widget.erroredittext;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;
import com.budejie.www.R;

public class SetErrorAbleEditText extends EditText implements j {
    private Context a;
    private k b;

    public SetErrorAbleEditText(Context context) {
        super(context);
        this.a = context;
        this.b = new k(context, this);
        a();
    }

    public SetErrorAbleEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        this.b = new k(context, this);
        a();
    }

    public SetErrorAbleEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        this.b = new k(context, this);
        a();
    }

    private void a() {
        addTextChangedListener(new SetErrorAbleEditText$1(this));
        setOnKeyListener(new SetErrorAbleEditText$2(this));
    }

    public void a(CharSequence charSequence) {
        this.b.a(charSequence, this.a.getResources().getDrawable(R.drawable.indicator_input_error));
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z) {
            if (this.b.a != null) {
                this.b.a();
            }
        } else if (this.b.a != null) {
            this.b.b();
        }
        super.onFocusChanged(z, i, rect);
    }

    protected void onDetachedFromWindow() {
        if (this.b.a != null) {
            this.b.b();
        }
        super.onDetachedFromWindow();
    }

    public String getTextContent() {
        return getText().toString();
    }
}
