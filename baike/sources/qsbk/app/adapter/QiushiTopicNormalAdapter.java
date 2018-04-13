package qsbk.app.adapter;

import android.app.Activity;
import android.widget.ListView;
import java.util.ArrayList;
import qsbk.app.model.QiushiTopicTab;

public class QiushiTopicNormalAdapter extends ArticleAdapter {
    private static final String f = QiushiTopicNormalAdapter.class.getSimpleName();
    QiushiTopicTab e = new QiushiTopicTab();

    public interface OnTabSelectListener {
        void onTabSelect(int i);
    }

    public QiushiTopicNormalAdapter(ArrayList<Object> arrayList, Activity activity, ListView listView, OnTabSelectListener onTabSelectListener) {
        super(activity, listView, arrayList, "", "QiushiTopicNormalActivity");
        this.c = onTabSelectListener;
    }

    public void setDefaultArticles(ArrayList<Object> arrayList) {
        this.m = arrayList;
        if (!(this.m == null || this.m.size() <= 0 || this.m.contains(this.e))) {
            this.m.add(0, this.e);
        }
        notifyDataSetChanged();
    }

    public void setNewArticles(ArrayList<Object> arrayList) {
        this.m = arrayList;
        if (!(this.m == null || this.m.size() <= 0 || this.m.contains(this.e))) {
            this.m.add(0, this.e);
        }
        notifyDataSetChanged();
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
