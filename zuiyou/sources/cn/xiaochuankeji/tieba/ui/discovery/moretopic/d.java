package cn.xiaochuankeji.tieba.ui.discovery.moretopic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.ui.topic.o;

public class d extends BaseAdapter {
    private Context a;
    private cn.htjyb.b.a.d<Topic> b;

    public d(Context context, cn.htjyb.b.a.d<Topic> dVar) {
        this.a = context;
        this.b = dVar;
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
        oVar.a(true, false, true);
        oVar.a((Topic) this.b.itemAt(i));
        return view;
    }
}
