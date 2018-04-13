package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.bean.WrapPaySelect;
import cn.v6.sixrooms.pay.engine.PayInfoEngine.CallBack;

final class ay implements CallBack {
    final /* synthetic */ PayCardActivity a;

    ay(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void error(int i) {
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }

    public final void handleResult(WrapPaySelect wrapPaySelect, String str) {
        this.a.k = wrapPaySelect.getYeepayszx();
        this.a.l = wrapPaySelect.getYeepayunicom();
        this.a.m = (PaySelectBean) this.a.k.get(0);
        this.a.o.setText(this.a.m.getContent());
        this.a.t.setText(((PaySelectBean) this.a.k.get(0)).getMsg());
        this.a.q.clear();
        this.a.q.addAll(this.a.k);
    }
}
