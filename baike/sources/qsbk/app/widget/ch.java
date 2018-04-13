package qsbk.app.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class ch extends BaseAdapter {
    final /* synthetic */ LinearReactiveLayout a;

    ch(LinearReactiveLayout linearReactiveLayout) {
        this.a = linearReactiveLayout;
    }

    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View textView = new TextView(this.a.getContext());
        textView.setText("Item " + i);
        textView.setTextSize(16.0f);
        int i2 = (int) (10.0f * this.a.getResources().getDisplayMetrics().density);
        textView.setPadding(i2, i2, i2, i2);
        return textView;
    }
}
