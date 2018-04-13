package cn.tatagou.sdk.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import cn.tatagou.sdk.pojo.TtgTitleBar;

public class TtgFitView extends View {
    public TtgFitView(Context context) {
        super(context);
    }

    public TtgFitView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TtgFitView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public TtgFitView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (TtgTitleBar.getInstance().getStatusBarBgColor() == -1 || VERSION.SDK_INT < 19) {
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(i, b.getStatusBarHeight(getContext()));
        }
    }
}
