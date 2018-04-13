package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.htjyb.b.a.a;
import cn.xiaochuankeji.tieba.background.topic.Topic;

public class p extends BaseAdapter {
    private Context a;
    private a<? extends Topic> b;

    public p(Context context, a<? extends Topic> aVar) {
        this.a = context;
        this.b = aVar;
    }

    public int getCount() {
        return this.b.itemCount();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        o oVar;
        if (view == null) {
            o oVar2 = new o(this.a);
            view = oVar2.f_();
            view.setTag(oVar2);
            oVar = oVar2;
        } else {
            oVar = (o) view.getTag();
        }
        oVar.a(false, false, true);
        oVar.a((Topic) this.b.itemAt(i));
        return view;
    }
}
