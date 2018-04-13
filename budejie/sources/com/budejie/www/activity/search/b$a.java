package com.budejie.www.activity.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.plate.bean.PlateBean;

class b$a extends BaseAdapter {
    final /* synthetic */ b a;

    private class a {
        TextView a;
        TextView b;
        final /* synthetic */ b$a c;

        private a(b$a b_a) {
            this.c = b_a;
        }
    }

    private b$a(b bVar) {
        this.a = bVar;
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public int getCount() {
        return com.budejie.www.goddubbing.c.a.a(b.a(this.a)) ? 0 : b.a(this.a).size();
    }

    public PlateBean a(int i) {
        return (PlateBean) b.a(this.a).get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = LayoutInflater.from(this.a.getActivity()).inflate(R.layout.search_label_result_layout, null);
            aVar.a = (TextView) view.findViewById(R.id.LabelNameTextView);
            aVar.b = (TextView) view.findViewById(R.id.LabelPostCountTextView);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        PlateBean plateBean = (PlateBean) b.a(this.a).get(i);
        aVar.a.setText(plateBean.theme_name);
        aVar.b.setText(plateBean.post_num + "个帖子");
        return view;
    }
}
