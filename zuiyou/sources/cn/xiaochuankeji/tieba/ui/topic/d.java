package cn.xiaochuankeji.tieba.ui.topic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager;

public class d extends BaseAdapter {
    private TopicHistoryRecordManager a;
    private Context b;

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public d(TopicHistoryRecordManager topicHistoryRecordManager, Context context) {
        this.a = topicHistoryRecordManager;
        this.b = context;
    }

    public int getCount() {
        return this.a.itemCount();
    }

    public Topic a(int i) {
        return this.a.itemAt(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        o oVar;
        if (view == null) {
            oVar = new o(this.b);
            view = oVar.f_();
            view.setTag(oVar);
        } else {
            oVar = (o) view.getTag();
        }
        Topic a = a(i);
        oVar.a(false, false, true);
        oVar.a(a);
        return view;
    }
}
