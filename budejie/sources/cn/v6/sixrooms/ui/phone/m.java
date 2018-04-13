package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class m implements OnItemClickListener {
    final /* synthetic */ BillManagerActivity a;

    m(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.f != null && this.a.f.isShowing()) {
            this.a.f.dismiss();
        }
        if (this.a.j != null) {
            this.a.j.setPosition(i);
            this.a.j.notifyDataSetChanged();
        }
        BillManagerActivity.s(this.a);
        this.a.setTitleText((String) this.a.h.get(i));
        this.a.a(i);
        this.a.p = i;
    }
}
