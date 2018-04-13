package cn.v6.sixrooms.pay.persenter;

import cn.v6.sixrooms.pay.bean.H5WeixinPay;
import cn.v6.sixrooms.pay.engine.H5WeixinPayEngine.CallBack;

final class a implements CallBack {
    final /* synthetic */ H5WeixinPayPresenter a;

    a(H5WeixinPayPresenter h5WeixinPayPresenter) {
        this.a = h5WeixinPayPresenter;
    }

    public final void handleResutl(H5WeixinPay h5WeixinPay, String str) {
        if (H5WeixinPayPresenter.a(this.a) != null) {
            H5WeixinPayPresenter.a(this.a).handleResutl(h5WeixinPay, str);
        }
    }

    public final void error(int i) {
        if (H5WeixinPayPresenter.a(this.a) != null) {
            H5WeixinPayPresenter.a(this.a).error(i);
        }
    }

    public final void handleError(String str, String str2) {
        if (H5WeixinPayPresenter.a(this.a) != null) {
            H5WeixinPayPresenter.a(this.a).handleError(str, str2);
        }
    }
}
