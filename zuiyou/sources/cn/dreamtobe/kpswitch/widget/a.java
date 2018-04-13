package cn.dreamtobe.kpswitch.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import c.a.i.g;
import cn.dreamtobe.kpswitch.a.c;

public class a extends g {
    private c a;

    public a(Context context) {
        super(context);
        a();
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = new c(this);
    }

    protected void onMeasure(int i, int i2) {
        this.a.a(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
        super.onMeasure(i, i2);
    }
}
