package cn.xiaochuankeji.tieba.d.a;

import android.widget.ListView;

public class c implements b {
    private ListView a;

    public c(ListView listView) {
        this.a = listView;
    }

    public int a() {
        return this.a.getLastVisiblePosition();
    }

    public int b() {
        return this.a.getFirstVisiblePosition();
    }
}
