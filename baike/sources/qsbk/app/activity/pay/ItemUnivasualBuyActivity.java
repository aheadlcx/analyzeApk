package qsbk.app.activity.pay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.sys.a;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.model.PayOrder;
import qsbk.app.model.Payment;
import qsbk.app.pay.ui.PayConstants;
import qsbk.app.pay.ui.PayResult;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.Util;
import qsbk.app.widget.VirtualKeyboardView;
import qsbk.app.widget.VirtualKeyboardView.Key;

public class ItemUnivasualBuyActivity extends BaseActivity {
    public static final int PAY_CANCEL = 0;
    public static final int PAY_FAIL = 1;
    public static final String PAY_SOURCE = "univasual_buy";
    public static final int PAY_SUCCESS = -1;
    private static final String r = ItemSignCardPaymentActivity.class.getName();
    private String A;
    private boolean B;
    private BroadcastReceiver C = new ab(this);
    LinkedList<Key> a = new LinkedList();
    View b;
    View c;
    View d;
    View e;
    TextView f;
    TextView g;
    TextView h;
    ImageView j;
    TextView k;
    Button l;
    View m;
    VirtualKeyboardView n;
    ImageView[] o = new ImageView[6];
    View p;
    Handler q = new s(this);
    private boolean s;
    private IWXAPI t;
    private String u;
    private Payment v = Payment.PAY_WALLET;
    private BigDecimal w;
    private boolean x;
    private View y;
    private PayOrder z;

    public static void launch(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, ItemUnivasualBuyActivity.class);
        intent.putExtra("order_id", str);
        activity.startActivityForResult(intent, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        registerReceiver(this.C, new IntentFilter(Constants.ACTION_UNIVERSAL_PAY_DONE));
    }

    protected void a(Bundle bundle) {
        d();
        e();
    }

    private void f() {
        this.b.setVisibility(0);
        i();
        this.w = new BigDecimal(this.z.balance);
        this.h.setText("￥" + Util.formatMoney(this.z.money));
        if (this.w.setScale(2, 3).doubleValue() >= this.z.money) {
            this.x = true;
        }
        this.v = this.x ? Payment.PAY_WALLET : Payment.PAY_ALIPAY;
        this.g.setText("购买" + this.z.payDesc);
        g();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.q.removeCallbacksAndMessages(null);
        if (this.C != null) {
            unregisterReceiver(this.C);
        }
    }

    private void a(int i, String str) {
        this.B = true;
        if (i == -1) {
            this.q.postDelayed(new ac(this), 1500);
        } else if (i == 1) {
            ToastUtil.Short(str);
            setResult(1);
            finish();
        } else if (i == 0) {
            this.B = false;
            i();
            setResult(0);
            ToastUtil.Short(str);
        }
    }

    public void onBackPressed() {
        if (!this.B) {
            super.onBackPressed();
        }
    }

    protected int a() {
        return R.layout.activity_item_payment;
    }

    protected void d() {
        this.p = findViewById(R.id.progress_layout);
        this.b = findViewById(R.id.content);
        this.c = findViewById(R.id.desc_window);
        this.d = findViewById(R.id.close);
        this.d.setOnClickListener(new ad(this));
        this.f = (TextView) findViewById(R.id.title);
        this.g = (TextView) findViewById(R.id.pay_for);
        this.h = (TextView) findViewById(R.id.amount);
        this.e = findViewById(R.id.payment_way);
        this.j = (ImageView) findViewById(R.id.payment_logo);
        this.k = (TextView) findViewById(R.id.payment_desc);
        this.y = findViewById(R.id.other_payment);
        this.l = (Button) findViewById(R.id.btn_pay);
        this.m = findViewById(R.id.pwd_input);
        this.n = (VirtualKeyboardView) findViewById(R.id.keyboard);
        this.n.setOnKeypressListener(new ae(this));
        this.n.getLayoutBack().setOnClickListener(new af(this));
        this.m.setOnClickListener(new ag(this));
        this.n.getViewTreeObserver().addOnGlobalLayoutListener(new ah(this));
        this.o[0] = (ImageView) findViewById(R.id.pwd_1);
        this.o[1] = (ImageView) findViewById(R.id.pwd_2);
        this.o[2] = (ImageView) findViewById(R.id.pwd_3);
        this.o[3] = (ImageView) findViewById(R.id.pwd_4);
        this.o[4] = (ImageView) findViewById(R.id.pwd_5);
        this.o[5] = (ImageView) findViewById(R.id.pwd_6);
        this.b.setVisibility(8);
        j();
    }

    private void a(String str) {
        this.b.setVisibility(4);
        b(str);
        this.a.clear();
        h();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            Payment payment = (Payment) intent.getSerializableExtra("payment");
            if (payment != null) {
                this.v = payment;
            }
        }
        g();
    }

    public void requestOrderInfo() {
        HttpTask encryptHttpTask = new EncryptHttpTask(Constants.LAISEE_UNIVASUAL_ORDER_INFO, new ai(this));
        Map hashMap = new HashMap();
        hashMap.put("order_id", this.A);
        encryptHttpTask.setMapParams(hashMap);
        encryptHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void g() {
        this.b.setVisibility(0);
        if (this.v == null) {
            finish();
            return;
        }
        this.n.setVisibility(8);
        this.m.setVisibility(8);
        this.l.setVisibility(8);
        switch (this.v.mId) {
            case 1:
                this.y.setVisibility(8);
                this.m.setVisibility(0);
                this.n.setVisibility(0);
                this.k.setText(this.v.mPaymentDesc + "（¥" + this.w.setScale(2, 3).toString() + "）");
                break;
            case 2:
            case 3:
                this.l.setVisibility(0);
                this.k.setText(this.v.mPaymentDesc);
                this.y.setVisibility(0);
                break;
        }
        this.j.setImageResource(this.v.mPaymentIcon);
        this.l.setOnClickListener(new t(this));
        this.e.setOnClickListener(new u(this));
    }

    protected void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.A = intent.getStringExtra("order_id");
        }
        if (TextUtils.isEmpty(this.A)) {
            finish();
        } else {
            requestOrderInfo();
        }
    }

    private void a(Payment payment) {
        a(payment, null);
    }

    private void b(String str) {
        a(Payment.PAY_WALLET, str);
    }

    private void a(Payment payment, String str) {
        j();
        Object hashMap = new HashMap();
        hashMap.put("order_id", this.z.orderId);
        hashMap.put("pay_way", payment.mParamString);
        switch (payment.mId) {
            case 1:
                hashMap.put("pay_password", str);
                break;
        }
        HttpTask encryptHttpTask = new EncryptHttpTask(Constants.LAISEE_UNIVASUAL_PAY, new v(this, payment));
        encryptHttpTask.setMapParams(hashMap);
        encryptHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    void a(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString(UriUtil.LOCAL_RESOURCE_SCHEME);
        this.u = a(string, c.G);
        new Thread(new y(this, string)).start();
    }

    void b(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(UriUtil.LOCAL_RESOURCE_SCHEME);
        String string = optJSONObject.getString("msg");
        this.u = optJSONObject.getString(c.G);
        if (string != null) {
            optJSONObject = new JSONObject(string);
            BaseReq payReq = new PayReq();
            payReq.appId = optJSONObject.getString("appid");
            payReq.partnerId = optJSONObject.getString("partnerid");
            payReq.prepayId = optJSONObject.getString("prepayid");
            payReq.nonceStr = optJSONObject.getString("noncestr");
            payReq.timeStamp = optJSONObject.getString("timestamp");
            payReq.packageValue = optJSONObject.getString("package");
            payReq.sign = optJSONObject.getString(Config.SIGN);
            payReq.extData = "out_trade_no=\"" + this.u + "\"&amount=\"" + this.z.money + "\"" + "&source=" + "\"" + PAY_SOURCE + this.z.orderId + "\"";
            if (!c(payReq.appId)) {
                i();
                a(0, "您还没有安装微信客户端");
            } else if (this.t != null) {
                this.t.sendReq(payReq);
            }
        }
    }

    protected void a(Message message) {
        switch (message.what) {
            case 1:
                PayResult payResult = new PayResult((String) message.obj);
                payResult.getResult();
                CharSequence resultStatus = payResult.getResultStatus();
                message.getData().getString(c.G);
                if (TextUtils.equals(resultStatus, "9000")) {
                    a(-1, null);
                    return;
                } else if (TextUtils.equals(resultStatus, WbAuthConstants.AUTH_FAILED_NOT_INSTALL_CODE)) {
                    a(1, "支付结果确认中");
                    return;
                } else if (TextUtils.equals(resultStatus, "5000")) {
                    a(1, "重复请求订单");
                    return;
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    a(0, "您取消了支付");
                    return;
                } else if (TextUtils.equals(resultStatus, "6002")) {
                    a(1, "网络连接出错");
                    return;
                } else {
                    a(1, "支付失败");
                    return;
                }
            default:
                return;
        }
    }

    private boolean c(String str) {
        if (this.s) {
            return true;
        }
        PayConstants.WECHAT_APP_ID = str;
        this.t = WXAPIFactory.createWXAPI(this, str);
        this.t.registerApp(str);
        if (this.t.isWXAppInstalled()) {
            this.s = true;
            return true;
        }
        ToastUtil.Short("您还没有安装微信客户端");
        return false;
    }

    private String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        String substring;
        String[] split = str.split(a.b);
        int i = 0;
        while (i < split.length) {
            if (split[i] != null && split[i].startsWith(str2)) {
                substring = split[i].substring(str2.length() + 2, split[i].length() - 1);
                break;
            }
            i++;
        }
        substring = null;
        return substring;
    }

    private void h() {
        if (this.o.length == 6) {
            for (int i = 0; i < this.o.length; i++) {
                if (i < this.a.size()) {
                    this.o[i].setVisibility(0);
                } else {
                    this.o[i].setVisibility(4);
                }
            }
        }
    }

    private void i() {
        if (this.p != null && this.p.isShown() && !isFinishing()) {
            this.p.setVisibility(8);
        }
    }

    private void j() {
        this.p.setOnTouchListener(new aa(this));
        this.p.setVisibility(0);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
