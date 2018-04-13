package cn.xiaochuankeji.tieba.ui.widget.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class MagicIndicator extends FrameLayout {
    private i a;

    public MagicIndicator(Context context) {
        super(context);
    }

    public MagicIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(int i, float f, int i2) {
        if (this.a != null) {
            this.a.a(i, f, i2);
        }
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void b(int i) {
        if (this.a != null) {
            this.a.b(i);
        }
    }

    public i getNavigator() {
        return this.a;
    }

    public void setNavigator(i iVar) {
        if (this.a != iVar) {
            if (this.a != null) {
                this.a.b();
            }
            this.a = iVar;
            removeAllViews();
            if (this.a instanceof View) {
                addView((View) this.a, new LayoutParams(-1, -1));
                this.a.a();
            }
        }
    }
}
