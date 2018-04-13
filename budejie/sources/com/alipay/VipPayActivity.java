package com.alipay;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.constants.GiftIdStrs;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.adapter.alipay.AlibcAlipay;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alipay.sdk.app.PayTask;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.PayHistoryActivity;
import com.budejie.www.bean.UserItem;
import com.budejie.www.busevent.AliPayAction;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.type.GetOrderIdResult;
import com.budejie.www.type.GetVipStatusResult;
import com.budejie.www.type.GetWXOrderIdResult;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.z;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import de.greenrobot.event.EventBus;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import net.tsz.afinal.a.a;
import org.apache.commons.httpclient.HttpState;
import pl.droidsonroids.gif.GifDrawable;

public class VipPayActivity extends OauthWeiboBaseAct implements OnClickListener {
    public static final String PARTNER = "2088801036522405";
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALqh8kHwob4rq4O1AGuQi+5TH6cLGii0isy4Q/GXMch80C3l0wNkVyfObWjUBYIldMmQHuWk8LN+b8kuSRm7krEnmISEbhwMh931w5E+vl3hqBI0JidgGAkrIEB1FzWwYWl6SUWyyWANgsdx2wvriU/tiuiEoLu4ZaeMUFa6SxLRAgMBAAECgYAQZPt/+WBr8TAe4L6JtGfmZpzoMpx4qOtscl9dkJeJ8hTjg0XXDXBuib6or79QIHQy5JsiNywByICPXqaDQSvwh1TDlKoX+ZGIlyqexmvf07/4rarGxuDPeTM09VpRVoy/0OJ4MCSX5nNGxSOg74pY0swY77AsUIZvD5pCpjhTQQJBAPPl9iLB2UoZEwrp4QHQPI3XXQDC9CqQn157lfuy6ib0bG+ePWiyJuuieafk+5pcgeZNPgOZIALSXib+5NVlDckCQQDD5JYOLV3EMFpiGmQyUWpQwYZ7OhecLbTcOJpDWfKm52Q5CP079bFTieS86hk/YBqvHA90fChfrNRa/MRy+UDJAkA7h5l4ClB64XvgYg1lX+onxLUShoMKEJqVeRy2fojgUTBWXg0nDLSrNtDyxdUTbBjYmWnVfv5tMeOBKR25aBzhAkEAtjAqvxTCWG0AIq2Y8pN15ZWXlZP50W9Q/mBGWM2XOFOMit8pZlKjkaZ17p1qzd578tCOLzAp/JGpDtZ3ftcCwQJBAJ9QSQM/Jvy1jISMlMnwnYLiq0kdrkgeN7aXO+KkizBjQD/oyHZ4DvKaSweitzmNlGR99TrllfQzUfHa9nPNMx8=";
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    private static final int SDK_CHECK_FLAG = 2;
    private static final int SDK_PAY_FLAG = 1;
    public static final String SELLER = "2088801036522405";
    private static final int UPDATE_VIP_STATUS = 3;
    private IWXAPI api;
    private TextView center_txt;
    private m database;
    private VipPayActivity instance;
    private boolean isPaySupported;
    private Button left_btn;
    private Dialog loadDialog;
    private String mCurContent;
    private String mCurGoodId;
    private String mCurMoney;
    private String mCurTitle;
    private GetVipStatusResult mGetVipStatusResult = null;
    @SuppressLint({"ResourceAsColor"})
    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            VipPayActivity.this.mIsPaying = false;
            VipPayActivity.this.mPayTipIsShow = false;
            switch (message.what) {
                case 1:
                    PayResult payResult = new PayResult((String) message.obj);
                    payResult.getResult();
                    CharSequence resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, AlibcAlipay.PAY_SUCCESS_CODE)) {
                        Toast.makeText(VipPayActivity.this, "支付成功", 0).show();
                        VipPayActivity.this.AskVipStatus();
                        return;
                    } else if (TextUtils.equals(resultStatus, "8000")) {
                        Toast.makeText(VipPayActivity.this, "支付结果确认中", 0).show();
                        return;
                    } else {
                        Toast.makeText(VipPayActivity.this, UserTrackerConstants.EM_PAY_FAILURE, 0).show();
                        return;
                    }
                case 2:
                    Toast.makeText(VipPayActivity.this, "检查结果为：" + message.obj, 0).show();
                    return;
                case 3:
                    String str = HttpState.PREEMPTIVE_DEFAULT;
                    String str2 = "您还不是百思不得姐会员";
                    if (VipPayActivity.this.mGetVipStatusResult.getIs_vip() != null) {
                        str = VipPayActivity.this.mGetVipStatusResult.getIs_vip();
                    }
                    if (VipPayActivity.this.mGetVipStatusResult.getDays() != null) {
                        str2 = VipPayActivity.this.mGetVipStatusResult.getDays();
                    }
                    VipPayActivity.this.ChangeVipStatus(str.equals(Constants.SERVICE_SCOPE_FLAG_VALUE), str2);
                    return;
                default:
                    return;
            }
        }
    };
    private boolean mIsPaying;
    private boolean mPayTipIsShow;
    private TextView mTipTextView;
    private AsyncImageView mVipImageView;
    private GetWXOrderIdResult mWXResult = null;
    private a<String> onRequestCallBack = new a<String>() {
        public void onStart() {
            VipPayActivity.this.mIsPaying = true;
            try {
                VipPayActivity.this.loadDialog.show();
            } catch (Exception e) {
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            if (VipPayActivity.this.loadDialog.isShowing()) {
                VipPayActivity.this.loadDialog.dismiss();
            }
            VipPayActivity.this.mIsPaying = false;
            Toast.makeText(VipPayActivity.this, "支付繁忙，请稍后重试", 0).show();
        }

        public void onSuccess(String str) {
            super.onSuccess(str);
            if (VipPayActivity.this.loadDialog.isShowing()) {
                VipPayActivity.this.loadDialog.dismiss();
            }
            new AsyncTask<String, String, GetOrderIdResult>() {
                protected void onPostExecute(GetOrderIdResult getOrderIdResult) {
                    VipPayActivity.this.PayOrder();
                }

                protected GetOrderIdResult doInBackground(String... strArr) {
                    try {
                        VipPayActivity.this.result = (GetOrderIdResult) z.a(strArr[0], GetOrderIdResult.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return VipPayActivity.this.result;
                }
            }.execute(new String[]{str});
        }
    };
    private a<String> onVipStatusRequestCallBack = new a<String>() {
        public void onStart() {
            try {
                VipPayActivity.this.loadDialog.show();
            } catch (Exception e) {
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            if (VipPayActivity.this.loadDialog.isShowing()) {
                VipPayActivity.this.loadDialog.dismiss();
            }
        }

        public void onSuccess(String str) {
            super.onSuccess(str);
            if (VipPayActivity.this.loadDialog.isShowing()) {
                VipPayActivity.this.loadDialog.dismiss();
            }
            new AsyncTask<String, String, GetVipStatusResult>() {
                protected void onPostExecute(GetVipStatusResult getVipStatusResult) {
                    if (VipPayActivity.this.mGetVipStatusResult != null) {
                        Message message = new Message();
                        message.what = 3;
                        message.obj = getVipStatusResult;
                        VipPayActivity.this.mHandler.sendMessage(message);
                    }
                }

                protected GetVipStatusResult doInBackground(String... strArr) {
                    try {
                        VipPayActivity.this.mGetVipStatusResult = (GetVipStatusResult) z.a(strArr[0], GetVipStatusResult.class);
                        if (VipPayActivity.this.mGetVipStatusResult != null) {
                            ai.b(VipPayActivity.this, VipPayActivity.this.mGetVipStatusResult.getIs_vip(), VipPayActivity.this.mGetVipStatusResult.token);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return VipPayActivity.this.mGetVipStatusResult;
                }
            }.execute(new String[]{str});
        }
    };
    private a<String> onWXRequestCallBack = new a<String>() {
        public void onStart() {
            VipPayActivity.this.mIsPaying = true;
            try {
                VipPayActivity.this.loadDialog.show();
            } catch (Exception e) {
            }
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            if (VipPayActivity.this.loadDialog.isShowing()) {
                VipPayActivity.this.loadDialog.dismiss();
            }
            VipPayActivity.this.mIsPaying = false;
            Toast.makeText(VipPayActivity.this, "支付繁忙，请稍后重试", 0).show();
        }

        public void onSuccess(String str) {
            super.onSuccess(str);
            if (VipPayActivity.this.loadDialog.isShowing()) {
                VipPayActivity.this.loadDialog.dismiss();
            }
            new AsyncTask<String, String, GetWXOrderIdResult>() {
                protected void onPostExecute(GetWXOrderIdResult getWXOrderIdResult) {
                    VipPayActivity.this.WXPayOrder();
                }

                protected GetWXOrderIdResult doInBackground(String... strArr) {
                    try {
                        VipPayActivity.this.mWXResult = (GetWXOrderIdResult) z.a(strArr[0], GetWXOrderIdResult.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return VipPayActivity.this.mWXResult;
                }
            }.execute(new String[]{str});
        }
    };
    private SharedPreferences preference;
    private GetOrderIdResult result = null;
    private TextView right_btn_text;
    private String uid;
    private LinearLayout userInfoLayout;
    private UserItem userItem;
    private TextView userName;
    private AsyncImageView userProfileImg;
    private n weiboTools;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pay_main);
        this.instance = this;
        findViews();
        initData();
    }

    protected void onResume() {
        super.onResume();
        initUserInfo();
        initTitleView();
        AskVipStatus();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initData() {
        boolean z = true;
        this.mIsPaying = false;
        this.mPayTipIsShow = false;
        this.mCurTitle = "";
        this.mCurContent = "";
        this.mCurMoney = "";
        this.mCurGoodId = "";
        this.loadDialog = new Dialog(this, R.style.dialogTheme);
        this.loadDialog.setContentView(R.layout.loaddialog);
        this.loadDialog.setCanceledOnTouchOutside(true);
        this.database = new m(this);
        this.weiboTools = new n(this.instance);
        this.preference = getSharedPreferences("weiboprefer", 0);
        this.api = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290");
        this.api.registerApp("wx592fdc48acfbe290");
        EventBus.getDefault().register(this, 0);
        if (this.api.getWXAppSupportAPI() < 570425345) {
            z = false;
        }
        this.isPaySupported = z;
    }

    private void initUserInfo() {
        this.uid = this.preference.getString("id", "");
        this.userItem = this.database.e(this.uid);
        if (this.userItem == null) {
            this.userName.setText(R.string.vip_pay_not_login);
            this.mTipTextView.setText(R.string.vip_pay_not_login_tips);
            this.userInfoLayout.setOnClickListener(this);
        } else if (TextUtils.isEmpty(this.userItem.getName()) || TextUtils.isEmpty(this.userItem.getProfile())) {
            ai.a(this.instance, "", "");
        } else {
            this.userName.setText(this.userItem.getName());
            this.userProfileImg.setPostAvatarImage(this.userItem.getProfile());
        }
    }

    private void initTitleView() {
        this.left_btn = (Button) findViewById(R.id.title_left_btn);
        this.left_btn.setVisibility(0);
        this.left_btn.setOnClickListener(this);
        this.right_btn_text = (TextView) findViewById(R.id.title_right_btn);
        if (TextUtils.isEmpty(this.uid)) {
            this.right_btn_text.setVisibility(8);
        } else {
            this.right_btn_text.setVisibility(0);
            this.right_btn_text.setText(R.string.vip_pay_history);
            this.right_btn_text.setOnClickListener(this);
        }
        this.center_txt = (TextView) findViewById(R.id.title_center_txt);
        this.center_txt.setText(R.string.vip_pay_title);
    }

    private void findViews() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.mVipImageView = (AsyncImageView) findViewById(R.id.VipImageView);
        this.userInfoLayout = (LinearLayout) findViewById(R.id.user_info_layout);
        this.userProfileImg = (AsyncImageView) findViewById(R.id.user_profile_img);
        this.userName = (TextView) findViewById(R.id.UserNameTextView);
        this.mTipTextView = (TextView) findViewById(R.id.TipTextView);
    }

    public void getOrderId(Context context) {
        String b = ai.b(context);
        BudejieApplication.a.a(RequstMethod.POST, "http://d.api.budejie.com/order/create/", j.d(this, this.mCurGoodId, b, b, "1"), this.onRequestCallBack);
    }

    public void PayOrder1(View view) {
        PayDialogShow(1);
    }

    public void PayOrder3(View view) {
        PayDialogShow(3);
    }

    public void PayOrder12(View view) {
        PayDialogShow(12);
    }

    private void PayDialogShow(final int i) {
        if (an.a(this.preference)) {
            if (this.isPaySupported) {
                String[] strArr = new String[]{"支付宝支付", "微信支付"};
            } else {
                new String[1][0] = "支付宝支付";
            }
            new Builder(this.instance).setItems(new String[]{"支付宝支付", "微信支付"}, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        Toast.makeText(VipPayActivity.this.instance, "支付宝支付", 1).show();
                        switch (i) {
                            case 1:
                                VipPayActivity.this.PayOrderAskAlipay(VipPayActivity.this.getString(R.string.vip_pay_1_title), VipPayActivity.this.getString(R.string.vip_pay_1_content), com.tencent.connect.common.Constants.VIA_REPORT_TYPE_WPA_STATE, "10001");
                                return;
                            case 3:
                                VipPayActivity.this.PayOrderAskAlipay(VipPayActivity.this.getString(R.string.vip_pay_3_title), VipPayActivity.this.getString(R.string.vip_pay_3_content), "20", "10003");
                                return;
                            case 12:
                                VipPayActivity.this.PayOrderAskAlipay(VipPayActivity.this.getString(R.string.vip_pay_12_title), VipPayActivity.this.getString(R.string.vip_pay_12_content), GiftIdStrs.SMALL_FIREWORKS, "10012");
                                return;
                            default:
                                return;
                        }
                    } else if (i != 1) {
                    } else {
                        if (VipPayActivity.this.isPaySupported) {
                            Toast.makeText(VipPayActivity.this.instance, "微信支付", 1).show();
                            switch (i) {
                                case 1:
                                    VipPayActivity.this.getWXOrderId(VipPayActivity.this.instance, "10001");
                                    return;
                                case 3:
                                    VipPayActivity.this.getWXOrderId(VipPayActivity.this.instance, "10003");
                                    return;
                                case 12:
                                    VipPayActivity.this.getWXOrderId(VipPayActivity.this.instance, "10012");
                                    return;
                                default:
                                    return;
                            }
                        }
                        Toast.makeText(VipPayActivity.this.instance, "您未安装微信或当前微信版本过低，无法使用微信支付", 1).show();
                    }
                }
            }).create().show();
            return;
        }
        an.a(this.instance, 0, null, null, 0);
    }

    private void PayOrderAskAlipay(String str, String str2, String str3, String str4) {
        this.mCurTitle = str;
        this.mCurContent = str2;
        this.mCurMoney = str3;
        this.mCurGoodId = str4;
        getOrderId(this);
    }

    private void PayOrder() {
        if (this.result == null || this.result.getOrder_id() == null) {
            Toast.makeText(this, "请先获取订单号", 0).show();
            return;
        }
        String orderInfo = getOrderInfo(this.mCurTitle, this.mCurContent, this.mCurMoney, this.result.getOrder_id());
        String sign = sign(orderInfo);
        try {
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sign = orderInfo + "&sign=\"" + sign + com.alipay.sdk.sys.a.a + getSignType();
        new Thread(new Runnable() {
            public void run() {
                String pay = new PayTask(VipPayActivity.this).pay(sign, true);
                Message message = new Message();
                message.what = 1;
                message.obj = pay;
                VipPayActivity.this.mHandler.sendMessage(message);
            }
        }).start();
    }

    public String getOrderInfo(String str, String str2, String str3, String str4) {
        return (((((((((("partner=\"2088801036522405\"" + "&seller_id=\"2088801036522405\"") + "&out_trade_no=\"" + str4 + "\"") + "&subject=\"" + str + "\"") + "&body=\"" + str2 + "\"") + "&total_fee=\"" + str3 + "\"") + "&notify_url=\"http://d.api.budejie.com/pay/alipay_callback/\"") + "&service=\"mobile.securitypay.pay\"") + "&payment_type=\"1\"") + "&_input_charset=\"utf-8\"") + "&it_b_pay=\"30m\"") + "&return_url=\"m.alipay.com\"";
    }

    public String getOutTradeNo() {
        return (new SimpleDateFormat("MMddHHmmss", Locale.getDefault()).format(new Date()) + new Random().nextInt()).substring(0, 15);
    }

    public String sign(String str) {
        return SignUtils.sign(str, RSA_PRIVATE);
    }

    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    private void AskVipStatus() {
        NetWorkUtil.a(this.instance, ai.b(this.instance), this.onVipStatusRequestCallBack);
    }

    private void ChangeVipStatus(boolean z, String str) {
        if (z) {
            this.userName.setTextColor(getResources().getColor(R.color.vip_pay_name_text_color));
            this.mVipImageView.setVisibility(0);
            try {
                this.mVipImageView.setVisibility(0);
                Drawable gifDrawable = new GifDrawable(this.instance.getResources(), com.budejie.www.util.j.I);
                this.mVipImageView.setImageDrawable(gifDrawable);
                gifDrawable.start();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.mTipTextView.setText(str);
            return;
        }
        this.userName.setTextColor(getResources().getColor(R.color.vip_pay_name_normal_text_color));
        this.mVipImageView.setVisibility(8);
        this.mTipTextView.setText(R.string.vip_pay_no_vip_tips);
    }

    public void onClick(View view) {
        if (view == this.left_btn) {
            finish();
        } else if (view == this.right_btn_text) {
            startActivity(new Intent(this, PayHistoryActivity.class));
        } else if (view == this.userInfoLayout && !an.a(this.preference)) {
            an.a(this.instance, 0, null, null, 0);
        }
    }

    public void getWXOrderId(Context context, String str) {
        String b = ai.b(context);
        BudejieApplication.a.a(RequstMethod.POST, "http://d.api.budejie.com/order/create/", j.d(this, str, b, b, "5"), this.onWXRequestCallBack);
    }

    private void WXPayOrder() {
        if (this.mWXResult == null || this.mWXResult.getPrepayid() == null) {
            Toast.makeText(this, "请重新获取支付信息", 0).show();
            return;
        }
        BaseReq payReq = new PayReq();
        payReq.appId = this.mWXResult.getAppid();
        payReq.partnerId = this.mWXResult.getPartnerid();
        payReq.prepayId = this.mWXResult.getPrepayid();
        payReq.nonceStr = this.mWXResult.getNoncestr();
        payReq.timeStamp = this.mWXResult.getTimestamp();
        payReq.packageValue = "Sign=WXPay";
        payReq.sign = this.mWXResult.getSign();
        this.api.registerApp("wx592fdc48acfbe290");
        this.api.sendReq(payReq);
    }

    public void onEventMainThread(AliPayAction aliPayAction) {
        aa.b("VipPayActivity", "onEventMainThread ");
        if (aliPayAction == AliPayAction.OK) {
            aa.b("VipPayActivity", "onEventMainThread AliPayAction.OK");
            Toast.makeText(this, "支付成功", 0).show();
            AskVipStatus();
        } else if (aliPayAction == AliPayAction.CANCEL) {
            aa.b("VipPayActivity", "onEventMainThread AliPayAction.CANCEL");
            Toast.makeText(this, UserTrackerConstants.EM_PAY_FAILURE, 0).show();
        }
    }
}
