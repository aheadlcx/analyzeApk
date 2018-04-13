package com.budejie.www.activity.search;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.budejie.www.bean.SearchHotItem;
import com.budejie.www.goddubbing.c.a;
import com.umeng.analytics.MobclickAgent;

class c$1 implements OnItemClickListener {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MobclickAgent.onEvent(this.a.getActivity(), "E06_A18", "热门搜索点击");
        String str = "";
        if (!a.a(c.a(this.a))) {
            if (this.a.e.getHeaderViewsCount() > 0) {
                int i2 = i - 1;
                if (i2 < 0) {
                    i2 = 0;
                }
                str = ((SearchHotItem) c.a(this.a).get(i2)).getSearchKey();
            } else {
                str = ((SearchHotItem) c.a(this.a).get(i)).getSearchKey();
            }
        }
        this.a.b(str);
        if (this.a.g != null) {
            this.a.g.a(0, str, false);
        }
    }
}
