package cn.dreamtobe.kpswitch.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import c.a.i.j;
import cn.dreamtobe.kpswitch.a.a;
import cn.dreamtobe.kpswitch.b;
import cn.dreamtobe.kpswitch.b.e;

public class KPSwitchFSPanelLinearLayout extends j implements b {
    private a a;

    public KPSwitchFSPanelLinearLayout(Context context) {
        super(context);
        a();
    }

    public KPSwitchFSPanelLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    @TargetApi(11)
    public KPSwitchFSPanelLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.a = new a(this);
    }

    public void a(int i) {
        e.a(this, i);
    }

    public void a(boolean z) {
        this.a.a(z);
    }
}
