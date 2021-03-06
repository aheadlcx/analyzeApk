package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.PaySelectBean;
import cn.v6.sixrooms.pay.bean.WrapPaySelect;
import cn.v6.sixrooms.pay.engine.PayInfoEngine.CallBack;

final class i implements CallBack {
    final /* synthetic */ AlipayActivity a;

    i(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void handleResult(WrapPaySelect wrapPaySelect, String str) {
        this.a.f.clear();
        this.a.f.addAll(wrapPaySelect.getAlipayless());
        this.a.j = (PaySelectBean) this.a.f.get(0);
        this.a.i.setText(this.a.j.getContent());
    }

    public final void error(int i) {
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }
}
