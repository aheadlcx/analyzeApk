package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class g implements OnClickListener {
    final /* synthetic */ BillManagerActivity a;

    g(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onClick(View view) {
        BillManagerActivity.a(this.a, view);
    }
}
