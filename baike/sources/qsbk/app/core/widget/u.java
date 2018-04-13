package qsbk.app.core.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

class u extends OnScrollListener {
    final /* synthetic */ RefreshRecyclerView a;

    u(RefreshRecyclerView refreshRecyclerView) {
        this.a = refreshRecyclerView;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (!this.a.d && this.a.e && i2 > 0) {
            this.a.a();
        }
    }
}
