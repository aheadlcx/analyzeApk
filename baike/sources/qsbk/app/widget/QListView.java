package qsbk.app.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class QListView extends ListView {
    public QListView(Context context) {
        super(context);
    }

    public QListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public QListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public QListView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    protected void layoutChildren() {
        int count = getCount();
        ListAdapter adapter = getAdapter();
        if (adapter == null || !(adapter instanceof BaseAdapter) || count == adapter.getCount()) {
            super.layoutChildren();
        } else {
            ((BaseAdapter) adapter).notifyDataSetChanged();
        }
    }
}
