package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

final class n implements OnItemClickListener {
    final /* synthetic */ BillManagerActivity a;

    n(BillManagerActivity billManagerActivity) {
        this.a = billManagerActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.g != null && this.a.g.isShowing()) {
            this.a.g.dismiss();
        }
        BillManagerActivity.s(this.a);
        if (this.a.i != null) {
            try {
                this.a.q = String.valueOf(new SimpleDateFormat("yyyy.MM.dd").parse((String) this.a.i.get(i)).getTime() / 1000);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.a.a(this.a.p);
        }
        if (this.a.k != null) {
            this.a.k.setPosition(i);
            this.a.k.notifyDataSetChanged();
        }
    }
}
