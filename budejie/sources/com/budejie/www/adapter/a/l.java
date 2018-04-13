package com.budejie.www.adapter.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.label.h;
import com.budejie.www.bean.SearchHotItem;
import com.budejie.www.util.ai;
import java.util.List;

public class l extends BaseAdapter {
    private List<SearchHotItem> a;
    private Context b;
    private boolean c;

    class a {
        TextView a;
        TextView b;
        final /* synthetic */ l c;

        a(l lVar) {
            this.c = lVar;
        }
    }

    public void a(List<SearchHotItem> list) {
        this.a = list;
    }

    public l(Context context, List<SearchHotItem> list) {
        this.b = context;
        this.a = list;
        this.c = ai.a(context) == 0;
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
        a aVar;
        if (view == null) {
            view = View.inflate(this.b, R.layout.search_hot_item, null);
            a aVar2 = new a(this);
            aVar2.a = (TextView) view.findViewById(R.id.IndexTextView);
            aVar2.b = (TextView) view.findViewById(R.id.HotKeyTextView);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        if (!(this.a == null || this.a.get(i) == null)) {
            aVar.a.setTextColor(-1);
            aVar.a.setText("" + (i + 1));
            h.a(this.b, aVar.a, i, this.c);
            aVar.b.setText(((SearchHotItem) this.a.get(i)).getSearchKey());
        }
        return view;
    }
}
