package com.handmark.pulltorefresh.library;

import android.view.View;

public interface PullToRefreshBase$OnRefreshListener2<V extends View> {
    void onPullDownToRefresh(PullToRefreshBase<V> pullToRefreshBase);

    void onPullUpToRefresh(PullToRefreshBase<V> pullToRefreshBase);
}
