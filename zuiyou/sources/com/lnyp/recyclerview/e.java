package com.lnyp.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;

public class e {
    public static void a(RecyclerView recyclerView, View view) {
        Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && (adapter instanceof a)) {
            a aVar = (a) adapter;
            if (aVar.c() == 0) {
                aVar.a(view);
            }
        }
    }
}
