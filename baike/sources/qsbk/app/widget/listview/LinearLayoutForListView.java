package qsbk.app.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class LinearLayoutForListView extends LinearLayout {
    private BaseAdapter a;
    private OnClickListener b = null;

    public LinearLayoutForListView(Context context) {
        super(context);
    }

    public LinearLayoutForListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bindLinearLayout() {
        int count = this.a.getCount();
        for (int i = 0; i < count; i++) {
            View view = this.a.getView(i, null, null);
            view.setOnClickListener(this.b);
            if (i == count) {
                ((RelativeLayout) view).removeViewAt(2);
            } else {
                addView(view, getChildCount());
            }
        }
    }

    public BaseAdapter getAdapter() {
        return this.a;
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        this.a = baseAdapter;
        bindLinearLayout();
    }

    public OnClickListener getOnClickListener() {
        return this.b;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.b = onClickListener;
    }
}
