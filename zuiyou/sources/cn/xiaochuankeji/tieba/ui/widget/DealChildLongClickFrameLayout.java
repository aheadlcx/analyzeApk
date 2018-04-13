package cn.xiaochuankeji.tieba.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import c.a.i.b.a;
import c.a.i.u;

public class DealChildLongClickFrameLayout extends FrameLayout implements u {
    private a a;
    private OnLongClickListener b;

    public DealChildLongClickFrameLayout(Context context) {
        super(context);
        a(null, 0);
    }

    public DealChildLongClickFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet, 0);
    }

    public DealChildLongClickFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet, i);
    }

    private void a(AttributeSet attributeSet, int i) {
        this.a = new a(this);
        this.a.a(attributeSet, i);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.b = onLongClickListener;
        super.setOnLongClickListener(onLongClickListener);
    }

    public boolean showContextMenuForChild(View view) {
        if (this.b != null) {
            this.b.onLongClick(this);
        }
        return true;
    }

    public void d() {
        if (this.a != null) {
            this.a.a();
        }
    }
}
