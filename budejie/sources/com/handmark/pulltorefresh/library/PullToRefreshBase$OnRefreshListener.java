package com.handmark.pulltorefresh.library;

import android.view.View;

public interface PullToRefreshBase$OnRefreshListener<V extends View> {
    void onRefresh(PullToRefreshBase<V> pullToRefreshBase);
}
