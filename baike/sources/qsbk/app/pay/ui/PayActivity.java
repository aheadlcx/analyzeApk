package qsbk.app.pay.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.widget.ProgressBar;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.sys.a;
import com.baidu.mobstat.Config;
import com.qiushibaike.statsdk.StatSDK;
import com.r0adkll.slidr.Slidr;
import com.tencent.mm.opensdk.constants.ConstantsAPI.Token;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import qsbk.app.core.model.Diamond;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.Constants;
import qsbk.app.core.utils.PreferenceUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.pay.R;
import qsbk.app.pay.adapter.DiamondAdapter;

public class PayActivity extends BaseActivity {
    public static String WX_STATE = "remix_pay";
    protected RecyclerView a;
    protected DiamondAdapter b;
    protected ArrayList<Diamond> c = new ArrayList();
    protected long d;
    protected LinearLayoutManager e;
    protected long f;
    protected boolean g = false;
    protected IWXAPI h;
    protected int i = 2;
    protected boolean j = false;
    protected BroadcastReceiver k = new a(this);
    protected Handler l = new d(this);
    private ProgressDialog m;
    private ProgressBar n;

    protected void a() {
        ConfigInfoUtil.instance().setUpdateConfigCallback(new e(this));
        ConfigInfoUtil.instance().deleteConfigAndUpdate();
    }

    protected void a(String str, String str2) {
        if (str != null) {
            NetRequest.getInstance().post(b(str), new f(this, str2));
        }
    }

    protected String b(String str) {
        return String.format(UrlConstants.ALI_SET_PROCESSING, new Object[]{Integer.valueOf(this.i), str});
    }

    private void a(long j) {
        NetRequest.getInstance().get(d(), new g(this));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        Slidr.attach(this, AppUtils.getEdgeSlidrConfig());
        registerReceiver(this.k, new IntentFilter(WX_STATE));
    }

    protected void initView() {
        this.a = (RecyclerView) findViewById(R.id.recyclerview);
        this.n = (ProgressBar) findViewById(R.id.progress_bar);
        setTitle(getString(R.string.pay_my_diamond_recharge));
        setUp();
    }

    protected void initData() {
        int i = PreferenceUtils.instance().getInt("payType", 1);
        this.d = AppUtils.getInstance().getUserInfoProvider().getBalance();
        this.b = a(this.d, i);
        this.a.setLayoutManager(b());
        this.a.setAdapter(this.b);
        this.a.setItemAnimator(new DefaultItemAnimator());
        this.a.setHasFixedSize(true);
        a(true);
        f();
    }

    protected LayoutManager b() {
        this.e = new LinearLayoutManager(getActivity());
        this.e.setOrientation(1);
        return this.e;
    }

    protected DiamondAdapter a(long j, int i) {
        return new DiamondAdapter(this, this.c, j, i);
    }

    protected int getLayoutId() {
        return R.layout.pay_activity;
    }

    public void do_ali_pay(float f, float f2) {
        if (AppUtils.getInstance().getUserInfoProvider().getUser() == null) {
            ToastUtil.Long("用户信息出错，请重试");
            return;
        }
        String a = a(f);
        this.m = ProgressDialog.show(this, "", AppUtils.getInstance().getAppContext().getString(R.string.pay_get_payinfo), false, true);
        NetRequest.getInstance().get(a, new h(this, f2, f, a));
    }

    protected void a(int i, String str, float f, float f2, String str2) {
        new Thread(new j(this, str, b(str, c.G), f, f2, str2, i)).start();
    }

    protected String a(float f) {
        User user = AppUtils.getInstance().getUserInfoProvider().getUser();
        String format = String.format(UrlConstants.ALI_PAY, new Object[]{Integer.valueOf(this.i), Long.valueOf(user.getPlatformId()), Float.valueOf(f)});
        if (Constants.SOURCE == 1) {
            return UrlConstants.ALI_PAY_QIUBAI;
        }
        return format;
    }

    private void a(String str, String str2, int i, String str3) {
        StatSDK.onEvent(getActivity(), "pay_failed", str, str2, UrlConstants.getPayDomain() + " - " + UrlConstants.getLiveHttpsDomain(), i + " - " + str3);
    }

    public void do_wechat_pay(float f, float f2) {
        if (AppUtils.getInstance().getUserInfoProvider().getUser() == null) {
            ToastUtil.Long("用户信息出错，请重试");
            return;
        }
        String b = b(f);
        this.m = ProgressDialog.show(this, "", AppUtils.getInstance().getAppContext().getString(R.string.pay_get_payinfo), false, true);
        NetRequest.getInstance().get(b, new m(this, f2, f, b));
    }

    protected void b(int i, String str, float f, float f2, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("msg");
            String string2 = jSONObject.getString(c.G);
            String str3 = (((long) (f + f2)) * 10) + "";
            if (string != null) {
                JSONObject jSONObject2 = new JSONObject(string);
                BaseReq payReq = new PayReq();
                payReq.appId = jSONObject2.getString("appid");
                payReq.partnerId = jSONObject2.getString("partnerid");
                payReq.prepayId = jSONObject2.getString("prepayid");
                payReq.nonceStr = jSONObject2.getString("noncestr");
                payReq.timeStamp = jSONObject2.getString("timestamp");
                payReq.packageValue = jSONObject2.getString("package");
                payReq.sign = jSONObject2.getString(Config.SIGN);
                payReq.extData = "out_trade_no=\"" + string2 + "\"&amount=\"" + str3 + "\"";
                c(payReq.appId);
                if (this.h != null) {
                    this.h.sendReq(payReq);
                }
            }
        } catch (Exception e) {
            a(Token.WX_TOKEN_PLATFORMID_VALUE, str2, i, e.getMessage());
            this.l.post(new o(this));
        }
    }

    protected String b(float f) {
        User user = AppUtils.getInstance().getUserInfoProvider().getUser();
        String format = String.format(UrlConstants.WECHAT_PAY, new Object[]{Integer.valueOf(this.i), Long.valueOf(user.getPlatformId()), Float.valueOf(f)});
        if (Constants.SOURCE == 1) {
            return UrlConstants.WECHAT_PAY_QIUBAI;
        }
        return format;
    }

    private void a(boolean z) {
        this.c.clear();
        Object e = e();
        this.c.clear();
        c();
        this.c.addAll(e);
        this.b.setHelpMsg(ConfigInfoUtil.instance().getHelpMsg());
        this.b.setHelpUrl(ConfigInfoUtil.instance().getHelpUrl() + "?package=" + getPackageName());
        this.b.notifyDataSetChanged();
        if (z) {
            ConfigInfoUtil.instance().setUpdateConfigCallback(new b(this, e));
            ConfigInfoUtil.instance().deleteConfigAndUpdate();
            this.n.setVisibility(0);
        }
    }

    protected void c() {
        if (UrlConstants.isInTestEnvironment()) {
            Diamond diamond = new Diamond();
            diamond.cd = "赠送$钻石";
            diamond.ce = 10000000;
            diamond.cn = 0;
            diamond.ct = "$钻石";
            diamond.mv = "1000000";
            diamond.pr = 0.01f;
            this.c.add(diamond);
        }
    }

    private List<Diamond> e() {
        List<Diamond> localDiamonds = ConfigInfoUtil.instance().getLocalDiamonds();
        if (localDiamonds == null) {
            return new ArrayList();
        }
        return localDiamonds;
    }

    private String b(String str, String str2) {
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

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            f();
        }
    }

    private void f() {
        NetRequest.getInstance().get(d(), new c(this), "request_balance", true);
    }

    protected String d() {
        return UrlConstants.GET_BALANCE;
    }

    private void c(String str) {
        if (!this.g && !TextUtils.isEmpty(str)) {
            PayConstants.resetWeChatAppId(str);
            this.h = WXAPIFactory.createWXAPI(this, PayConstants.WECHAT_APP_ID);
            this.h.registerApp(PayConstants.WECHAT_APP_ID);
            if (this.h.isWXAppInstalled()) {
                this.g = true;
                return;
            }
            hideSavingDialog();
            showSnackbar(getResources().getString(R.string.pay_wechat_not_installed));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.k);
        ConfigInfoUtil.instance().setUpdateConfigCallback(null);
    }

    private void d(String str) {
        a(str, "processing");
        a();
        if (this.j) {
            sendBroadcast(new Intent(Constants.FIRST_CHARGE_STATE));
        }
    }

    private void e(String str) {
        a(str, "cancel");
    }

    private boolean g() {
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                if (((Diamond) it.next()).fc == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void a(Message message) {
        a(message.getData().getLong("amount", 0));
    }

    protected void a(String str) {
        a((long) Integer.parseInt(b(str, "amount")));
    }

    public void do_qiubai_pay(float f, float f2) {
    }
}
