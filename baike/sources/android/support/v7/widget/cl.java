package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.OnScrollListener;

class cl extends OnScrollListener {
    boolean a = false;
    final /* synthetic */ SnapHelper b;

    cl(SnapHelper snapHelper) {
        this.b = snapHelper;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        super.onScrollStateChanged(recyclerView, i);
        if (i == 0 && this.a) {
            this.a = false;
            this.b.a();
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (i != 0 || i2 != 0) {
            this.a = true;
        }
    }
}
