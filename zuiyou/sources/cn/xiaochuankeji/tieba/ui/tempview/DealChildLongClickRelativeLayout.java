package cn.xiaochuankeji.tieba.ui.tempview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;

public class DealChildLongClickRelativeLayout extends RelativeLayout {
    private OnLongClickListener a;

    public DealChildLongClickRelativeLayout(Context context) {
        super(context);
    }

    public DealChildLongClickRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DealChildLongClickRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DealChildLongClickRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.a = onLongClickListener;
        super.setOnLongClickListener(onLongClickListener);
    }

    public boolean showContextMenuForChild(View view) {
        if (this.a != null) {
            this.a.onLongClick(this);
        }
        return true;
    }
}
