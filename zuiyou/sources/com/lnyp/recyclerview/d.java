package com.lnyp.recyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import com.lnyp.recyclerview.RecyclerViewLoadingFooter.State;

public class d {
    public static void a(Activity activity, RecyclerView recyclerView, int i, State state, OnClickListener onClickListener) {
        if (activity != null && !activity.isFinishing()) {
            Adapter adapter = recyclerView.getAdapter();
            if (adapter != null && (adapter instanceof a)) {
                a aVar = (a) adapter;
                if (aVar.a().getItemCount() < i) {
                    return;
                }
                if (aVar.d() > 0) {
                    RecyclerViewLoadingFooter recyclerViewLoadingFooter = (RecyclerViewLoadingFooter) aVar.b();
                    recyclerViewLoadingFooter.setState(state);
                    if (state == State.NetWorkError) {
                        recyclerViewLoadingFooter.setOnClickListener(onClickListener);
                        return;
                    }
                    return;
                }
                View recyclerViewLoadingFooter2 = new RecyclerViewLoadingFooter(activity);
                recyclerViewLoadingFooter2.setState(state);
                if (state == State.NetWorkError) {
                    recyclerViewLoadingFooter2.setOnClickListener(onClickListener);
                }
                aVar.b(recyclerViewLoadingFooter2);
            }
        }
    }
}
