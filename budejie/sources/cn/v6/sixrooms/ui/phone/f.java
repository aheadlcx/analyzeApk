package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class f implements OnClickListener {
    final /* synthetic */ BillManagerActivity a;

    f(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
