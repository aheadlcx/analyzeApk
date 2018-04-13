package com.budejie.www.adapter.g.b;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.budejie.www.R;
import com.budejie.www.activity.view.TabPageIndicator;
import com.budejie.www.type.MySquareIcon;
import java.util.List;

public class b {
    View a;
    Context b;
    List<MySquareIcon> c;
    TabPageIndicator d;
    ViewPager e;

    public b(Context context, List<MySquareIcon> list, View view) {
        this.b = context;
        this.c = list;
        this.a = view;
        a();
    }

    public void a(List<MySquareIcon> list) {
        this.c = list;
        int size = (list.size() % 10 > 0 ? 1 : 0) + (list.size() / 10);
        this.d.a(size);
        this.e.removeAllViews();
        this.e.setAdapter(new b$1(this, size));
    }

    private void a() {
        int size = (this.c.size() / 10) + (this.c.size() % 10 > 0 ? 1 : 0);
        this.e = (ViewPager) this.a.findViewById(R.id.viewpager);
        this.d = (TabPageIndicator) this.a.findViewById(R.id.tab_page_indicator);
        this.d.setPointResource(R.drawable.post_history_post_tap_page_indicator);
        this.d.a(size);
        this.e.setAdapter(new b$2(this, size));
        this.e.setOnPageChangeListener(new b$3(this));
    }
}
