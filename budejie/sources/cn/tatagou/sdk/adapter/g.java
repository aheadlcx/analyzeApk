package cn.tatagou.sdk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import java.util.List;

public class g extends a<String> {

    class a {
        public TextView a;
        final /* synthetic */ g b;

        a(g gVar) {
            this.b = gVar;
        }
    }

    public g(Context context, List<String> list) {
        super(context, (List) list);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.ttg_searchhistory_item, viewGroup, false);
            aVar2.a = (TextView) view.findViewById(R.id.ttg_tv_searchcontent);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a.setText((CharSequence) this.c.get(i));
        return view;
    }
}
