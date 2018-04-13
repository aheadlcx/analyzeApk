package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class h implements OnClickListener {
    final /* synthetic */ BillManagerActivity a;

    h(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onClick(View view) {
        BillManagerActivity.b(this.a, view);
    }
}
