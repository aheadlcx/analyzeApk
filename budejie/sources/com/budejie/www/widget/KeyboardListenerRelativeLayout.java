package com.budejie.www.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.e.b;

public class KeyboardListenerRelativeLayout extends RelativeLayout {
    private a a;
    private int b = b.a(BudejieApplication.g, 100.0f);

    public interface a {
        void a(boolean z);
    }

    public KeyboardListenerRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && Math.abs(i2 - i4) >= this.b) {
            boolean z = i2 - i4 <= 0;
            if (this.a != null) {
                this.a.a(z);
            }
        }
    }

    public void setOnKeyboardChangeListener(a aVar) {
        this.a = aVar;
    }
}
