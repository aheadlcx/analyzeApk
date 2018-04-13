package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.htjyb.ui.a;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.ui.a.c;

public class k extends BaseAdapter implements a {
    private Context a;
    private cn.htjyb.b.a.a<? extends Topic> b;

    public k(Context context, cn.htjyb.b.a.a<? extends Topic> aVar) {
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
        c cVar;
        if (view == null) {
            c cVar2 = new c(this.a);
            view = cVar2.f_();
            view.setTag(cVar2);
            cVar = cVar2;
        } else {
            cVar = (c) view.getTag();
        }
        cVar.a((Topic) this.b.itemAt(i), false);
        return view;
    }

    public void c() {
    }
}
