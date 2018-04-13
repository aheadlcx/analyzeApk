package qsbk.app.live.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.support.v7.widget.RecyclerView.State;

class by extends LinearLayoutManager {
    final /* synthetic */ LiveBaseActivity a;

    by(LiveBaseActivity liveBaseActivity, Context context) {
        this.a = liveBaseActivity;
        super(context);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i) {
        int i2;
        int itemCount = this.a.N.getItemCount();
        if (itemCount <= 0 || i == itemCount - 1) {
            i2 = i;
        } else {
            i2 = itemCount - 1;
        }
        if (itemCount <= 0 && r0 != 0) {
            i2 = 0;
        }
        SmoothScroller bzVar = new bz(this, this.a.L.getContext());
        bzVar.setTargetPosition(i2);
        startSmoothScroll(bzVar);
    }
}
