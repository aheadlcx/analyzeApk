package cn.dreamtobe.kpswitch.a;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import cn.dreamtobe.kpswitch.a;
import cn.dreamtobe.kpswitch.b.e;

public class b implements a {
    private final View a;
    private boolean b = false;
    private boolean c = false;
    private final int[] d = new int[2];
    private boolean e = false;

    public b(View view, AttributeSet attributeSet) {
        this.a = view;
        if (attributeSet != null) {
            TypedArray typedArray = null;
            try {
                typedArray = view.getContext().obtainStyledAttributes(attributeSet, cn.dreamtobe.kpswitch.c.b.KPSwitchPanelLayout);
                this.c = typedArray.getBoolean(cn.dreamtobe.kpswitch.c.b.KPSwitchPanelLayout_ignore_recommend_height, false);
            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }
        }
    }

    public boolean a(int i) {
        if (i == 0) {
            this.b = false;
        }
        if (i == this.a.getVisibility()) {
            return true;
        }
        if (a() && i == 0) {
            return true;
        }
        return false;
    }

    public int[] a(int i, int i2) {
        if (this.b) {
            this.a.setVisibility(8);
            i = MeasureSpec.makeMeasureSpec(0, 1073741824);
            i2 = MeasureSpec.makeMeasureSpec(0, 1073741824);
        }
        this.d[0] = i;
        this.d[1] = i2;
        return this.d;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean a() {
        return this.e;
    }

    public boolean b() {
        return !this.b;
    }

    public void c() {
        throw new IllegalAccessError("You can't invoke handle show in handler, please instead of handling in the panel layout, maybe just need invoke super.setVisibility(View.VISIBLE)");
    }

    public void c_() {
        this.b = true;
    }

    public void b(int i) {
        if (!this.c) {
            e.a(this.a, i);
        }
    }

    public void b(boolean z) {
        this.c = z;
    }
}
