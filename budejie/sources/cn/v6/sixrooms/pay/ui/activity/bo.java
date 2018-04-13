package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.utils.DialogUtils.DialogListener;

final class bo implements DialogListener {
    final /* synthetic */ WeixinPayActivity a;

    bo(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void positive(int i) {
        WeixinPayActivity.d(this.a);
    }

    public final void negative(int i) {
    }
}
