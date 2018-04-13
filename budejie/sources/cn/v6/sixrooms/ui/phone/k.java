package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.BillPullToRefreshView;
import cn.v6.sixrooms.widgets.phone.BillPullToRefreshView.OnFooterRefreshListener;

final class k implements OnFooterRefreshListener {
    final /* synthetic */ BillManagerActivity a;

    k(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onFooterRefresh(BillPullToRefreshView billPullToRefreshView) {
        if (BillManagerActivity.l(this.a)) {
            this.a.u = true;
            this.a.r = this.a.r + 1;
            this.a.a(this.a.p);
            return;
        }
        this.a.o.onComplete();
    }
}
