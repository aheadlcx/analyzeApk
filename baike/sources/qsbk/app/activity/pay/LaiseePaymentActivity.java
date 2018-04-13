package qsbk.app.activity.pay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.http.HttpPayTask;
import qsbk.app.http.HttpTask;
import qsbk.app.model.Laisee;
import qsbk.app.model.Payment;
import qsbk.app.pay.ui.PayConstants;
import qsbk.app.utils.ToastUtil;
import qsbk.app.utils.Util;
import qsbk.app.widget.BlackProgressDialog;
import qsbk.app.widget.VirtualKeyboardView;
import qsbk.app.widget.VirtualKeyboardView.Key;

public class LaiseePaymentActivity extends BaseActivity {
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
    private static final String s = LaiseePaymentActivity.class.getName();
    private boolean A;
    private EncryptHttpTask B;
    private EncryptHttpTask C;
    private View D;
    private Handler E = new aj(this);
    private BroadcastReceiver F = new ay(this);
    int a;
    HashMap<String, Object> b;
    LinkedList<Key> c = new LinkedList();
    View d;
    View e;
    View f;
    View g;
    TextView h;
    TextView j;
    TextView k;
    ImageView l;
    TextView m;
    Button n;
    View o;
    VirtualKeyboardView p;
    ImageView[] q = new ImageView[6];
    BlackProgressDialog r;
    private Laisee t;
    private boolean u;
    private IWXAPI v;
    private String w;
    private String x;
    private Payment y = Payment.PAY_WALLET;
    private BigDecimal z;

    interface a {
        void onError(String str);

        void onLaiseeIdGet();
    }

    public static void launch(Activity activity, Laisee laisee, BigDecimal bigDecimal, HashMap<String, Object> hashMap, int i, int i2) {
        Intent intent = new Intent(activity, LaiseePaymentActivity.class);
        intent.putExtra("laisee", laisee);
        intent.putExtra("balance", bigDecimal);
        intent.putExtra("postParams", hashMap);
        intent.putExtra("type", i);
        activity.startActivityForResult(intent, i2);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        registerReceiver(this.F, new IntentFilter(Constants.ACTION_LAISEE_PAY_DONE));
    }

    protected int a() {
        return R.layout.activity_laisee_payment;
    }

    protected void a(Bundle bundle) {
        d();
        e();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.E.removeCallbacksAndMessages(null);
        if (this.F != null) {
            unregisterReceiver(this.F);
        }
        if (this.B != null) {
            this.B.cancel(true);
        }
        if (this.C != null) {
            this.C.cancel(true);
        }
    }

    private void a(int i, String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str4)) {
            Map hashMap = new HashMap();
            hashMap.put("status", i == 0 ? "processing" : "cancel");
            hashMap.put("charge_id", this.w);
            HttpPayTask httpPayTask = new HttpPayTask(null, String.format(Constants.LAISEE_ORDER_STATUS_SUBMIT, new Object[]{str2}), null);
            httpPayTask.setMapParams(hashMap);
            httpPayTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
        if (i == 0) {
            if (this.a != 3) {
                newLaisee(this.t, str3, str4, this.w);
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("laisee", this.t);
            setResult(-1, intent);
            finish();
        } else if (i == 2) {
            ToastUtil.Short(str);
            finish();
        } else if (i == 1) {
            k();
            ToastUtil.Short(str);
        }
    }

    protected void d() {
        this.r = new BlackProgressDialog(this);
        this.d = findViewById(R.id.content);
        this.e = findViewById(R.id.desc_window);
        this.f = findViewById(R.id.close);
        this.f.setOnClickListener(new az(this));
        this.h = (TextView) findViewById(R.id.title);
        this.j = (TextView) findViewById(R.id.pay_for);
        this.k = (TextView) findViewById(R.id.amount);
        this.g = findViewById(R.id.payment_way);
        this.l = (ImageView) findViewById(R.id.payment_logo);
        this.m = (TextView) findViewById(R.id.payment_desc);
        this.D = findViewById(R.id.other_payment);
        this.n = (Button) findViewById(R.id.btn_pay);
        this.o = findViewById(R.id.pwd_input);
        this.p = (VirtualKeyboardView) findViewById(R.id.keyboard);
        this.p.setOnKeypressListener(new ba(this));
        this.p.getLayoutBack().setOnClickListener(new bb(this));
        this.o.setOnClickListener(new bc(this));
        this.p.getViewTreeObserver().addOnGlobalLayoutListener(new bd(this));
        this.q[0] = (ImageView) findViewById(R.id.pwd_1);
        this.q[1] = (ImageView) findViewById(R.id.pwd_2);
        this.q[2] = (ImageView) findViewById(R.id.pwd_3);
        this.q[3] = (ImageView) findViewById(R.id.pwd_4);
        this.q[4] = (ImageView) findViewById(R.id.pwd_5);
        this.q[5] = (ImageView) findViewById(R.id.pwd_6);
    }

    private void a(String str) {
        this.d.setVisibility(4);
        a(new be(this, str));
        this.c.clear();
        j();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && intent != null) {
            Payment payment = (Payment) intent.getSerializableExtra("payment");
            if (payment != null) {
                this.y = payment;
            }
        }
        g();
    }

    private void g() {
        this.d.setVisibility(0);
        if (this.y == null) {
            finish();
            return;
        }
        this.p.setVisibility(8);
        this.o.setVisibility(8);
        this.n.setVisibility(8);
        switch (this.y.mId) {
            case 1:
                this.D.setVisibility(8);
                this.o.setVisibility(0);
                this.p.setVisibility(0);
                this.m.setText(this.y.mPaymentDesc + "（¥" + this.z.setScale(2, 3).toString() + "）");
                break;
            case 2:
            case 3:
                this.n.setVisibility(0);
                this.m.setText(this.y.mPaymentDesc);
                this.D.setVisibility(0);
                break;
        }
        this.l.setImageResource(this.y.mPaymentIcon);
        this.n.setOnClickListener(new bf(this));
        this.g.setOnClickListener(new ak(this));
    }

    protected void e() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.t = (Laisee) intent.getSerializableExtra("laisee");
        if (this.t == null) {
            finish();
            return;
        }
        this.z = (BigDecimal) intent.getSerializableExtra("balance");
        this.b = (HashMap) intent.getSerializableExtra("postParams");
        this.a = intent.getIntExtra("type", -1);
        if (this.a < 0) {
            finish();
            return;
        }
        this.k.setText("￥" + Util.formatMoney(this.t.totalMoney));
        if (this.a == 3) {
            this.j.setText("充值金额");
            this.h.setText("钱袋充值");
            this.y = Payment.PAY_ALIPAY;
        } else {
            this.j.setText("糗百红包");
            this.h.setText("红包支付");
            if (this.z.setScale(2, 3).doubleValue() >= this.t.totalMoney) {
                this.A = true;
            }
            this.y = this.A ? Payment.PAY_WALLET : Payment.PAY_ALIPAY;
        }
        g();
    }

    private void a(Payment payment) {
        this.r.show();
        a(new al(this, payment));
    }

    private void b(Payment payment) {
        int parseDouble;
        this.r.show();
        Object obj = Constants.LAISEE_CHARGE;
        Object hashMap = new HashMap();
        if (payment.mId == 2) {
            hashMap.put("pay_way", "weixin");
        } else if (payment.mId == 3) {
            hashMap.put("pay_way", "alipay");
        }
        try {
            parseDouble = (int) (Double.parseDouble((String) this.b.get(PayPWDUniversalActivity.MONEY)) * 100.0d);
        } catch (Exception e) {
            parseDouble = 0;
        }
        hashMap.put(PayPWDUniversalActivity.MONEY, Integer.valueOf(parseDouble));
        if (!TextUtils.isEmpty(obj)) {
            this.B = new EncryptHttpTask(null, obj, new am(this, payment));
            this.B.setMapParams(hashMap);
            this.B.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void h() {
        new HttpTask(null, String.format(Constants.LAISEE_ORDER_WEXIN_INFO, new Object[]{QsbkApp.currentUser.userId, this.t.totalMoney + "", this.t.id}), new ap(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private boolean b(String str) {
        if (this.u) {
            return true;
        }
        PayConstants.WECHAT_APP_ID = str;
        this.v = WXAPIFactory.createWXAPI(this, str);
        this.v.registerApp(str);
        if (this.v.isWXAppInstalled()) {
            this.u = true;
            return true;
        }
        ToastUtil.Short("您还没有安装微信客户端");
        return false;
    }

    private void i() {
        new HttpTask(null, String.format(Constants.LAISEE_ORDER_ALI_INFO, new Object[]{QsbkApp.currentUser.userId, this.t.totalMoney + "", this.t.id}), new aq(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        String substring;
        String[] split = str.split(com.alipay.sdk.sys.a.b);
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

    private void j() {
        if (this.q.length == 6) {
            for (int i = 0; i < this.q.length; i++) {
                if (i < this.c.size()) {
                    this.q[i].setVisibility(0);
                } else {
                    this.q[i].setVisibility(4);
                }
            }
        }
    }

    public void newLaisee(Laisee laisee, String str, String str2, String str3) {
        this.r.show();
        Map hashMap = new HashMap();
        hashMap.put("id", laisee.id);
        hashMap.put("secret", laisee.secret);
        hashMap.put("pay_way", str);
        hashMap.put("pay_password", str2);
        hashMap.put("order_id", str3);
        this.C = new EncryptHttpTask(null, Constants.LAISEE_NEW, new au(this, laisee, str, str2, str3));
        this.C.setMapParams(hashMap);
        this.C.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void k() {
        if (this.r != null && this.r.isShowing() && !isFinishing()) {
            this.r.dismiss();
        }
    }

    private void a(a aVar) {
        Object obj = this.a == 0 ? Constants.LAISEE_SEND_P2P_ID : (this.a == 2 || this.a == 1) ? Constants.LAISEE_SEND_TRIBE_ID : this.a == 3 ? Constants.LAISEE_CHARGE : null;
        if (!TextUtils.isEmpty(obj)) {
            this.B = new EncryptHttpTask(null, obj, new ax(this, aVar));
            this.B.setMapParams(this.b);
            this.B.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
