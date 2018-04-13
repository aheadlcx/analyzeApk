package qsbk.app.live.debug;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

class c extends OnScrollListener {
    final /* synthetic */ LiveDebugListFragment a;

    c(LiveDebugListFragment liveDebugListFragment) {
        this.a = liveDebugListFragment;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (!this.a.e && this.a.f && i2 > 0) {
            this.a.h();
        }
    }
}
