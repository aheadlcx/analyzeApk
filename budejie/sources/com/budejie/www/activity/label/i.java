package com.budejie.www.activity.label;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.budejie.www.R;
import java.util.List;

public class i extends BaseAdapter {
    private Context a;
    private List<LabelBean> b;
    private LayoutInflater c;

    public i(Context context) {
        this.a = context;
        this.c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public void a(List<LabelBean> list) {
        this.b = list;
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.b != null) {
            return this.b.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        i$a i_a;
        if (view == null) {
            i_a = new i$a(this);
            view = this.c.inflate(R.layout.label_select_list_item, null);
            i_a.a = (TextView) view.findViewById(R.id.item_at_name);
            i_a.b = (ImageView) view.findViewById(R.id.iv_label);
            view.setTag(i_a);
        } else {
            i_a = (i$a) view.getTag();
        }
        LabelBean labelBean = (LabelBean) this.b.get(i);
        if (labelBean.getTheme_id() == -1) {
            i_a.b.setVisibility(0);
            i_a.a.setText(labelBean.getTheme_name());
        } else {
            i_a.b.setVisibility(0);
            i_a.a.setText(labelBean.getTheme_name());
        }
        return view;
    }
}
