package cn.v6.sixrooms.widgets.phone;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

final class l implements OnScrollListener {
    final /* synthetic */ FullScreenChatPage a;

    l(FullScreenChatPage fullScreenChatPage) {
        this.a = fullScreenChatPage;
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.a.l != null) {
            this.a.l.onScrollStateChanged(absListView, i);
        }
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.a.l != null) {
            this.a.l.onScroll(absListView, i, i2, i3);
        }
    }
}
