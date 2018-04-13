package cn.dreamtobe.kpswitch.widget;

import android.content.Context;
import android.util.AttributeSet;
import c.a.i.g;
import cn.dreamtobe.kpswitch.a;
import cn.dreamtobe.kpswitch.b;

public class KPSwitchPanelFrameLayout extends g implements a, b {
    private cn.dreamtobe.kpswitch.a.b a;

    public KPSwitchPanelFrameLayout(Context context) {
        super(context);
        a(null);
    }

    public KPSwitchPanelFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
    }

    public KPSwitchPanelFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        this.a = new cn.dreamtobe.kpswitch.a.b(this, attributeSet);
    }

    public void setVisibility(int i) {
        if (!this.a.a(i)) {
            super.setVisibility(i);
        }
    }

    protected void onMeasure(int i, int i2) {
        int[] a = this.a.a(i, i2);
        super.onMeasure(a[0], a[1]);
    }

    public boolean a() {
        return this.a.a();
    }

    public boolean b() {
        return this.a.b();
    }

    public void c() {
        super.setVisibility(0);
    }

    public void c_() {
        this.a.c_();
    }

    public void setIgnoreRecommendHeight(boolean z) {
        this.a.b(z);
    }

    public void a(int i) {
        this.a.b(i);
    }

    public void a(boolean z) {
        this.a.a(z);
    }
}
