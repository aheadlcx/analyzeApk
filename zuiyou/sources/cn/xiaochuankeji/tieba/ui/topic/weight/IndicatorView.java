package cn.xiaochuankeji.tieba.ui.topic.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class IndicatorView extends LinearLayout {
    private static final int a = e.a(6.0f);
    private int b;
    private int c;
    private int d;

    public IndicatorView(Context context) {
        super(context);
        a();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public IndicatorView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        setOrientation(0);
    }

    private View a(boolean z) {
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        layoutParams.setMargins(z ? 0 : e.a(9.0f), 0, 0, 0);
        View aVar = new a(getContext());
        aVar.setLayoutParams(layoutParams);
        aVar.setCircleRadius((float) (a / 2));
        aVar.setCircleColor(this.c);
        return aVar;
    }

    public void a(int i, int i2, int i3) {
        removeAllViews();
        if (i > 1) {
            this.b = i3;
            this.c = i2;
            for (int i4 = 0; i4 < i; i4++) {
                boolean z;
                if (i4 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                addView(a(z), i4);
            }
            a(0);
        }
    }

    public void a(int i) {
        if (getChildAt(this.d) != null && getChildAt(i) != null) {
            ((a) getChildAt(this.d)).setCircleColor(this.c);
            ((a) getChildAt(i)).setCircleColor(this.b);
            this.d = i;
        }
    }
}
