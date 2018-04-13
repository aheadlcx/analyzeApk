package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.OnScrollListener;

class an extends OnScrollListener {
    final /* synthetic */ al a;

    an(al alVar) {
        this.a = alVar;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        this.a.a(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
    }
}
