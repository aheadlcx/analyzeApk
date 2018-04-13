package cn.htjyb.ui.widget.headfooterlistview;

import android.content.Context;
import android.util.AttributeSet;

public class QueryListViewInScrollView extends QueryListView {
    public QueryListViewInScrollView(Context context) {
        super(context);
    }

    public QueryListViewInScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void d() {
        super.d();
        this.a.setInScrollView(true);
    }
}
