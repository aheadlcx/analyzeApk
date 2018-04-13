package qsbk.app.activity.pay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpTask;
import qsbk.app.model.Payment;
import qsbk.app.model.Product;
import qsbk.app.pay.ui.PayConstants;
import qsbk.app.pay.ui.PayResult;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.Util;
import qsbk.app.widget.BlackProgressDialog;
import qsbk.app.widget.VirtualKeyboardView;
import qsbk.app.widget.VirtualKeyboardView.Key;

public class ItemSignCardPaymentActivity extends BaseActivity {
    public static final int PAY_CANCEL = 1;
    public static final int PAY_FAIL = 2;
    public static final String PAY_SOURCE = "qiubai_wallet";
    public static final int PAY_SUCCESS = 0;
    public static final String PAY_WAY_ALIPAY = "alipay";
    public static final String PAY_WAY_POCKET = "pocket";
    public static final String PAY_WAY_WEIXIN = "weixin";
    public static final String RESULT_CHARGE_ID = "charge_id";
    public static final String RESULT_CODE = "code";
    public static final String RESULT_ERR_MSG = "err_msg";
    public static final String RESULT_OUT_TRADE_ID = "oid";
    public static final String RESULT_PAY_WAY = "pay_way";
    public static final String RESULT_PWD = "pay_pwd";
    private static final String p = ItemSignCardPaymentActivity.class.getName();
    private BroadcastReceiver A = new c(this);
    LinkedList<Key> a = new LinkedList();
    View b;
    View c;
    View d;
    View e;
    TextView f;
    TextView g;
    TextView h;
    ImageView i;
    TextView j;
    Button k;
    View l;
    VirtualKeyboardView m;
    ImageView[] n = new ImageView[6];
    BlackProgressDialog o;
    private Product q;
    private boolean r;
    private IWXAPI s;
    private String t;
    private String u;
    private Payment v = Payment.PAY_WALLET;
    private BigDecimal w;
    private boolean x;
    private View y;
    private String z;

    public static void launch(Activity activity, Product product, BigDecimal bigDecimal, String str, int i) {
        Intent intent = new Intent(activity, ItemSignCardPaymentActivity.class);
        intent.putExtra("product", product);
        intent.putExtra("request_id", str);
        intent.putExtra("balance", bigDecimal);
        activity.startActivityForResult(intent, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        registerReceiver(this.A, new IntentFilter(Constants.ACTION_LAISEE_PAY_DONE));
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.A != null) {
            unregisterReceiver(this.A);
        }
    }

    private void a(int i, String str, String str2, String str3, String str4) {
        if (i == 0) {
            setResult(-1);
            finish();
        } else if (i == 2) {
            ToastUtil.Short(str);
            finish();
        } else if (i == 1) {
            e();
            ToastUtil.Short(str);
        }
    }

    protected int getLayoutId() {
        return R.layout.activity_item_payment;
    }

    protected void initView() {
        this.o = new BlackProgressDialog(this);
        this.b = findViewById(R.id.content);
        this.c = findViewById(R.id.desc_window);
        this.d = findViewById(R.id.close);
        this.d.setOnClickListener(new i(this));
        this.f = (TextView) findViewById(R.id.title);
        this.g = (TextView) findViewById(R.id.pay_for);
        this.h = (TextView) findViewById(R.id.amount);
        this.e = findViewById(R.id.payment_way);
        this.i = (ImageView) findViewById(R.id.payment_logo);
        this.j = (TextView) findViewById(R.id.payment_desc);
        this.y = findViewById(R.id.other_payment);
        this.k = (Button) findViewById(R.id.btn_pay);
        this.l = findViewById(R.id.pwd_input);
        this.m = (VirtualKeyboardView) findViewById(R.id.keyboard);
        this.m.setOnKeypressListener(new j(this));
        this.m.getLayoutBack().setOnClickListener(new k(this));
        this.l.setOnClickListener(new l(this));
        this.m.getViewTreeObserver().addOnGlobalLayoutListener(new m(this));
        this.n[0] = (ImageView) findViewById(R.id.pwd_1);
        this.n[1] = (ImageView) findViewById(R.id.pwd_2);
        this.n[2] = (ImageView) findViewById(R.id.pwd_3);
        this.n[3] = (ImageView) findViewById(R.id.pwd_4);
        this.n[4] = (ImageView) findViewById(R.id.pwd_5);
        this.n[5] = (ImageView) findViewById(R.id.pwd_6);
    }

    private void a(String str) {
        this.b.setVisibility(4);
        a(this.q, str, this.z);
        this.a.clear();
        d();
    }

    private void a(Product product, String str, String str2) {
        HttpTask encryptHttpTask = new EncryptHttpTask(Constants.WALLET_ITEM_BUY, new n(this));
        Map hashMap = new HashMap();
        hashMap.put("card", product.id);
        hashMap.put("count", Integer.valueOf(product.count));
        hashMap.put("price", product.totalMoney);
        hashMap.put("pay_password", str);
        hashMap.put("request_id", str2);
        encryptHttpTask.setMapParams(hashMap);
        encryptHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            Payment payment = (Payment) intent.getSerializableExtra("payment");
            if (payment != null) {
                this.v = payment;
            }
        }
        a();
    }

    private void a() {
        this.b.setVisibility(0);
        if (this.v == null) {
            finish();
            return;
        }
        this.m.setVisibility(8);
        this.l.setVisibility(8);
        this.k.setVisibility(8);
        switch (this.v.mId) {
            case 1:
                this.y.setVisibility(8);
                this.l.setVisibility(0);
                this.m.setVisibility(0);
                this.j.setText(this.v.mPaymentDesc + "（¥" + this.w.setScale(2, 3).toString() + "）");
                break;
            case 2:
            case 3:
                this.k.setVisibility(0);
                this.j.setText(this.v.mPaymentDesc);
                this.y.setVisibility(0);
                break;
        }
        this.i.setImageResource(this.v.mPaymentIcon);
        this.k.setOnClickListener(new q(this));
        this.e.setOnClickListener(new r(this));
    }

    protected void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.q = (Product) intent.getSerializableExtra("product");
        this.z = intent.getStringExtra("request_id");
        if (this.q == null || TextUtils.isEmpty(this.z)) {
            finish();
            return;
        }
        this.w = (BigDecimal) intent.getSerializableExtra("balance");
        this.h.setText("￥" + Util.formatMoney(this.q.totalMoney));
        if (this.w.setScale(2, 3).doubleValue() >= this.q.totalMoney()) {
            this.x = true;
        }
        this.v = this.x ? Payment.PAY_WALLET : Payment.PAY_ALIPAY;
        this.g.setText("购买" + this.q.name);
        a();
    }

    private void a(Payment payment) {
        this.o.show();
        switch (payment.mId) {
            case 2:
                b();
                return;
            case 3:
                c();
                return;
            default:
                return;
        }
    }

    private void b() {
        HttpTask encryptHttpTask = new EncryptHttpTask(null, Constants.WALLET_ITEM_BUY_OTHER, new d(this));
        encryptHttpTask.setMapParams(b("weixin"));
        encryptHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private HashMap<String, Object> b(String str) {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("card", this.q.id);
        hashMap.put("count", Integer.valueOf(this.q.count));
        hashMap.put("price", this.q.totalMoney);
        hashMap.put("request_id", this.z);
        hashMap.put("pay_way", str);
        return hashMap;
    }

    protected void onHandleMessage(Message message) {
        super.onHandleMessage(message);
        switch (message.what) {
            case 1:
                PayResult payResult = new PayResult((String) message.obj);
                payResult.getResult();
                CharSequence resultStatus = payResult.getResultStatus();
                message.getData().getString(c.G);
                if (TextUtils.equals(resultStatus, "9000")) {
                    a(0, null, this.u, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, WbAuthConstants.AUTH_FAILED_NOT_INSTALL_CODE)) {
                    a(2, "支付结果确认中", this.u, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, "5000")) {
                    a(2, "重复请求订单", this.u, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    a(1, "您取消了支付", this.u, "alipay", null);
                    return;
                } else if (TextUtils.equals(resultStatus, "6002")) {
                    a(2, "网络连接出错", this.u, "alipay", null);
                    return;
                } else {
                    a(2, "支付失败", this.u, "alipay", null);
                    return;
                }
            default:
                return;
        }
    }

    private boolean c(String str) {
        if (this.r) {
            return this.r;
        }
        PayConstants.WECHAT_APP_ID = str;
        this.s = WXAPIFactory.createWXAPI(this, str);
        this.s.registerApp(str);
        if (this.s.isWXAppInstalled()) {
            this.r = true;
            return true;
        }
        ToastUtil.Short("您还没有安装微信客户端");
        return false;
    }

    private void c() {
        HttpTask encryptHttpTask = new EncryptHttpTask(null, Constants.WALLET_ITEM_BUY_OTHER, new e(this));
        encryptHttpTask.setMapParams(b("alipay"));
        encryptHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
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

    private void d() {
        if (this.n.length == 6) {
            for (int i = 0; i < this.n.length; i++) {
                if (i < this.a.size()) {
                    this.n[i].setVisibility(0);
                } else {
                    this.n[i].setVisibility(4);
                }
            }
        }
    }

    private void e() {
        if (this.o != null && this.o.isShowing() && !isFinishing()) {
            this.o.dismiss();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
