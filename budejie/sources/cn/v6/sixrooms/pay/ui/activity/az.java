package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.PaySelectBean;

final class az implements OnItemClickListener {
    final /* synthetic */ PayCardActivity a;

    az(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ((LinearLayout) view.findViewById(R.id.ll_item)).performClick();
        this.a.m = (PaySelectBean) this.a.q.get(i);
        this.a.o.setText(this.a.m.getContent());
        this.a.p.dismiss();
    }
}
