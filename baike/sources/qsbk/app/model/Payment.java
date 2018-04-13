package qsbk.app.model;

import qsbk.app.R;

public class Payment extends QbBean {
    public static final int ALIPAY = 3;
    public static final Payment PAY_ALIPAY = new Payment("支付宝", 3, R.drawable.ic_pay_ali, "alipay");
    public static final Payment PAY_WALLET = new Payment("糗百钱袋", 1, R.drawable.ic_wallet_balance, "balance");
    public static final Payment PAY_WEXIN = new Payment("微信", 2, R.drawable.ic_pay_wechat, "weixin");
    public static final int QBWALLET = 1;
    public static final int UNKOWN = 0;
    public static final int WEIXIN = 2;
    public final int mId;
    public final String mParamString;
    public final String mPaymentDesc;
    public final int mPaymentIcon;

    public Payment(String str, int i, int i2, String str2) {
        this.mPaymentIcon = i2;
        this.mPaymentDesc = str;
        this.mId = i;
        this.mParamString = str2;
    }
}
