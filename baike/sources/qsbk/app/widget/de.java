package qsbk.app.widget;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

class de implements OnScrollListener {
    final /* synthetic */ PtrLayout a;

    de(PtrLayout ptrLayout) {
        this.a = ptrLayout;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (PtrLayout.a(this.a) != null) {
            PtrLayout.a(this.a).onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (absListView.getChildCount() != 0 && this.a.mPtrIndicator.getCurrentPosY() == 0) {
            if (PtrLayout.b(this.a) != null) {
                View childAt = absListView.getChildAt(0);
                float height = (float) childAt.getHeight();
                if (height == 0.0f) {
                    height = 1.0f;
                }
                PtrLayout.b(this.a).onScrollOffsetChange(((float) i) - (((float) childAt.getTop()) / height), -childAt.getTop(), (int) height);
            }
            if (PtrLayout.a(this.a) != null) {
                PtrLayout.a(this.a).onScroll(absListView, i, i2, i3);
            }
            if (i3 >= 5 && i + i2 >= i3 - PtrLayout.c(this.a) && !this.a.isRefreshing() && !this.a.isLoadingMore() && this.a.isEnableLoadMore()) {
                this.a.loadMore();
            }
        }
    }
}
