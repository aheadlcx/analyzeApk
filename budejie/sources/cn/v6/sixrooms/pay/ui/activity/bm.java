package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.pay.bean.H5WeixinPay;
import cn.v6.sixrooms.pay.persenter.H5WeixinPayPresenter.H5WeixinPayPresenterCallBack;

final class bm implements H5WeixinPayPresenterCallBack {
    final /* synthetic */ WeixinPayActivity a;

    bm(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void handleResutl(H5WeixinPay h5WeixinPay, String str) {
        this.a.k = h5WeixinPay.getOrderid();
        this.a.b();
        WeixinPayActivity.a(this.a, h5WeixinPay);
    }

    public final void error(int i) {
        this.a.b();
        this.a.showToast(this.a.a.getString(R.string.tip_network_error_str));
    }

    public final void handleError(String str, String str2) {
        this.a.b();
        this.a.handleErrorResult(str, str2, this.a);
    }
}
