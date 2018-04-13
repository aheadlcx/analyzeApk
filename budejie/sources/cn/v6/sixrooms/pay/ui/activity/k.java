package cn.v6.sixrooms.pay.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.v6.sixrooms.pay.bean.PaySelectBean;

final class k implements OnItemClickListener {
    final /* synthetic */ AlipayActivity a;

    k(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.j = (PaySelectBean) this.a.f.get(i);
        this.a.i.setText(this.a.j.getContent());
        this.a.e.dismiss();
    }
}
