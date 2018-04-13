package com.budejie.www.adapter.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.bean.PayHistoryItem;
import java.util.List;

public class h extends BaseAdapter {
    private List<PayHistoryItem> a;
    private Context b;

    public void a(List<PayHistoryItem> list) {
        this.a = list;
    }

    public h(Context context) {
        this.b = context;
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        h$a h_a;
        if (view == null) {
            view = View.inflate(this.b, R.layout.pay_history_item, null);
            h_a = new h$a(this);
            h_a.a = (TextView) view.findViewById(R.id.HistoryTip1TextView);
            h_a.b = (TextView) view.findViewById(R.id.HistoryTip2TextView);
            view.setTag(h_a);
        } else {
            h_a = (h$a) view.getTag();
        }
        if (!(this.a == null || this.a.get(i) == null)) {
            h_a.a.setText(((PayHistoryItem) this.a.get(i)).getTitle());
            h_a.b.setText(((PayHistoryItem) this.a.get(i)).getEnd_time());
        }
        return view;
    }
}
