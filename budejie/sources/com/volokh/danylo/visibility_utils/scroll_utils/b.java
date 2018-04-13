package com.volokh.danylo.visibility_utils.scroll_utils;

import android.view.View;
import android.widget.ListView;

public class b implements a {
    private final ListView a;

    public b(ListView listView) {
        this.a = listView;
    }

    public View a(int i) {
        return this.a.getChildAt(i);
    }

    public int a(View view) {
        return this.a.indexOfChild(view);
    }

    public int a() {
        return this.a.getChildCount();
    }

    public int b() {
        return this.a.getLastVisiblePosition();
    }

    public int c() {
        return this.a.getFirstVisiblePosition();
    }
}
