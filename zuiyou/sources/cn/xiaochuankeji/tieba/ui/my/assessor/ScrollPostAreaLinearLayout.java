package cn.xiaochuankeji.tieba.ui.my.assessor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import cn.htjyb.c.a;

public class ScrollPostAreaLinearLayout extends LinearLayout {
    private Scroller a;
    private int b;

    public ScrollPostAreaLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Scroller(context, new LinearInterpolator());
        this.b = a.d(context);
    }

    public void a() {
        this.a.startScroll(0 - this.b, 0, this.b, 0, 200);
        invalidate();
    }

    public boolean b() {
        return this.a.isFinished();
    }

    public void computeScroll() {
        if (this.a.computeScrollOffset()) {
            scrollTo(this.a.getCurrX(), this.a.getCurrY());
            postInvalidate();
        }
    }
}
