package qsbk.app.widget;

import qsbk.app.widget.recyclerview.EndlessRecyclerOnScrollListener;

class df extends EndlessRecyclerOnScrollListener {
    final /* synthetic */ PtrLayout a;

    df(PtrLayout ptrLayout) {
        this.a = ptrLayout;
    }

    public void onLoadMore() {
        this.a.loadMore();
    }
}
