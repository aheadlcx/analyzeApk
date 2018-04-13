package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.PaySelectBean;

final class ba implements OnItemClickListener {
    final /* synthetic */ PayCardActivity a;

    ba(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ((LinearLayout) view.findViewById(R.id.ll_item)).performClick();
        if (this.a.g.getId() == ((PaySelectBean) this.a.j.get(i)).getId()) {
            this.a.f.dismiss();
            return;
        }
        TextView f;
        CharSequence msg;
        this.a.g = (PaySelectBean) this.a.j.get(i);
        if (this.a.g.getId() == 0) {
            if (!(this.a.k == null || this.a.k.get(0) == null)) {
                f = this.a.t;
                msg = ((PaySelectBean) this.a.k.get(0)).getMsg();
            }
            this.a.h.setText(this.a.g.getContent());
            if (this.a.r != null) {
                if (this.a.g.getId() != 0) {
                    this.a.q.clear();
                    this.a.q.addAll(this.a.k);
                    this.a.r.notifyDataSetChanged();
                } else if (this.a.g.getId() == 1) {
                    this.a.q.clear();
                    this.a.q.addAll(this.a.l);
                    this.a.r.notifyDataSetChanged();
                }
                this.a.m = (PaySelectBean) this.a.q.get(0);
                this.a.o.setText(this.a.m.getContent());
            }
            this.a.f.dismiss();
        }
        if (!(this.a.l == null || this.a.l.get(0) == null)) {
            msg = ((PaySelectBean) this.a.l.get(0)).getMsg();
            f = this.a.t;
            if (msg == null) {
                msg = "";
            }
        }
        this.a.h.setText(this.a.g.getContent());
        if (this.a.r != null) {
            if (this.a.g.getId() != 0) {
                this.a.q.clear();
                this.a.q.addAll(this.a.k);
                this.a.r.notifyDataSetChanged();
            } else if (this.a.g.getId() == 1) {
                this.a.q.clear();
                this.a.q.addAll(this.a.l);
                this.a.r.notifyDataSetChanged();
            }
            this.a.m = (PaySelectBean) this.a.q.get(0);
            this.a.o.setText(this.a.m.getContent());
        }
        this.a.f.dismiss();
        f.setText(msg);
        this.a.h.setText(this.a.g.getContent());
        if (this.a.r != null) {
            if (this.a.g.getId() != 0) {
                this.a.q.clear();
                this.a.q.addAll(this.a.k);
                this.a.r.notifyDataSetChanged();
            } else if (this.a.g.getId() == 1) {
                this.a.q.clear();
                this.a.q.addAll(this.a.l);
                this.a.r.notifyDataSetChanged();
            }
            this.a.m = (PaySelectBean) this.a.q.get(0);
            this.a.o.setText(this.a.m.getContent());
        }
        this.a.f.dismiss();
    }
}
