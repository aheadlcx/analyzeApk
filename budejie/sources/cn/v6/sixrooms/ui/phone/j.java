package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.BillPullToRefreshView;
import cn.v6.sixrooms.widgets.phone.BillPullToRefreshView.OnHeaderRefreshListener;

final class j implements OnHeaderRefreshListener {
    final /* synthetic */ BillManagerActivity a;

    j(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onHeaderRefresh(BillPullToRefreshView billPullToRefreshView) {
        this.a.r = 1;
        this.a.a(this.a.p);
    }
}
