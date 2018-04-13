package com.budejie.www.activity.view;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import com.budejie.www.adapter.e;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.an;
import com.budejie.www.util.ap;
import com.budejie.www.widget.xrecyclerview.XRecyclerView;
import com.budejie.www.widget.xrecyclerview.b;
import java.util.ArrayList;
import java.util.List;

public class StaggeredRecyclerView extends XRecyclerView {
    private Context a;
    private e b;
    private List<ListItemObject> c;
    private StaggeredGridLayoutManager d;

    public StaggeredRecyclerView(Context context) {
        this(context, null);
    }

    public StaggeredRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StaggeredRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        e();
    }

    private void e() {
        if (this.d == null) {
            f();
        }
    }

    private void f() {
        this.d = new StaggeredManager(2, 1);
        this.d.setGapStrategy(0);
        setLayoutManager(this.d);
        addItemDecoration(new b(an.b(this.a, 4.6f), an.b(this.a, 10.0f)));
    }

    private void g() {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.b = new e(this.a, this.c);
        setAdapter(this.b);
    }

    public void setStaggeredManager(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.d = staggeredGridLayoutManager;
    }

    public void setData(List<ListItemObject> list) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.clear();
        ap.a(list);
        this.c.addAll(list);
        if (this.b == null) {
            g();
        } else {
            this.b.notifyDataSetChanged();
        }
    }

    public void a(List<ListItemObject> list) {
        ap.a(list);
        this.b.a(list);
    }

    public void a() {
        if (this.b == null) {
            g();
        }
        this.b.a(getHeaderViewCount());
    }
}
