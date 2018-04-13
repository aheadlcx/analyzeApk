package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class bi implements Runnable {
    final /* synthetic */ bh a;

    bi(bh bhVar) {
        this.a = bhVar;
    }

    public final void run() {
        String id = GlobleValue.getUserBean().getId();
        WeixinPayActivity.p(this.a.a).orderStatusV2(WeixinPayActivity.f(this.a.a), SaveUserInfoUtils.getEncpass(this.a.a), id);
        WeixinPayActivity.q(this.a.a);
    }
}
