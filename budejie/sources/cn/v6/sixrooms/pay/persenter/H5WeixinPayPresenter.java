package cn.v6.sixrooms.pay.persenter;

import cn.v6.sixrooms.pay.bean.H5WeixinPay;
import cn.v6.sixrooms.pay.engine.H5WeixinPayEngine;

public class H5WeixinPayPresenter {
    private H5WeixinPayPresenterCallBack a;
    public H5WeixinPayEngine engine;

    public interface H5WeixinPayPresenterCallBack {
        void error(int i);

        void handleError(String str, String str2);

        void handleResutl(H5WeixinPay h5WeixinPay, String str);
    }

    public H5WeixinPayPresenterCallBack getCallBack() {
        return this.a;
    }

    public void setCallBack(H5WeixinPayPresenterCallBack h5WeixinPayPresenterCallBack) {
        this.a = h5WeixinPayPresenterCallBack;
    }

    public void getH5WeixinPayInfo(String str, String str2, String str3, String str4) {
        if (this.engine == null) {
            this.engine = new H5WeixinPayEngine(new a(this));
        }
        this.engine.getWeixinH5PayUri(str, str2, str3, str4);
    }
}
