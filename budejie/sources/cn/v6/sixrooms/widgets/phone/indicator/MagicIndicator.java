package cn.v6.sixrooms.widgets.phone.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import cn.v6.sixrooms.ui.view.indicator.IPagerNavigator;

public class MagicIndicator extends FrameLayout {
    private IPagerNavigator a;

    public MagicIndicator(Context context) {
        super(context);
    }

    public MagicIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.a != null) {
            this.a.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.a != null) {
            this.a.onPageSelected(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.a != null) {
            this.a.onPageScrollStateChanged(i);
        }
    }

    public IPagerNavigator getNavigator() {
        return this.a;
    }

    public void setNavigator(IPagerNavigator iPagerNavigator) {
        if (this.a != iPagerNavigator) {
            if (this.a != null) {
                this.a.onDetachFromMagicIndicator();
            }
            this.a = iPagerNavigator;
            removeAllViews();
            if (this.a instanceof View) {
                addView((View) this.a, new LayoutParams(-1, -1));
                this.a.onAttachToMagicIndicator();
            }
        }
    }
}
