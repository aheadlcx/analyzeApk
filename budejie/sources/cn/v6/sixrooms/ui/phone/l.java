package cn.v6.sixrooms.ui.phone;

import com.slidingmenu.lib.SlidingMenu.f;

final class l implements f {
    final /* synthetic */ BillManagerActivity a;

    l(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onOpened() {
        this.a.finish();
    }
}
