package cn.v6.sixrooms.pay.ui.activity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.net.NetworkState;
import cn.v6.sixrooms.pay.adapter.PaySelectAdapter;
import cn.v6.sixrooms.pay.bean.H5WeixinPay;
import cn.v6.sixrooms.pay.bean.WrapPaySelect.CommodityInfo;
import cn.v6.sixrooms.pay.bean.WrapPaySelect.CommodityItem;
import cn.v6.sixrooms.pay.engine.OrderStatusEngine;
import cn.v6.sixrooms.pay.engine.PayInfoEngine;
import cn.v6.sixrooms.pay.persenter.H5WeixinPayPresenter;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeixinPayActivity extends SlidingActivity implements OnClickListener {
    private Resources a;
    private TextView b;
    private TextView c;
    private RelativeLayout d;
    private PopupWindow e;
    private PaySelectAdapter f;
    private PayInfoEngine g;
    private TextView h;
    private CommodityItem i;
    private OrderStatusEngine j;
    private String k;
    private String l = RoomActivity.VIDEOTYPE_UNKNOWN;
    private UserInfoEngine m;
    private RelativeLayout n;
    private int o;
    private H5WeixinPayPresenter p;
    private WebView q;
    private RelativeLayout r;
    private TextView s;
    private int t = 0;
    private boolean u = false;
    private CommodityInfo v;
    private Handler w = new br(this);

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.phone_activity_alipay);
        this.o = getWindowManager().getDefaultDisplay().getWidth();
        this.a = getResources();
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.phone_room_behind);
        slidingMenu.setShadowWidth(20);
        slidingMenu.setShadowDrawable(R.drawable.shadowleft);
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setMode(0);
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setOnOpenedListener(new bn(this));
        this.b = (TextView) findViewById(R.id.tv_account);
        this.c = (TextView) findViewById(R.id.tv_coin6);
        this.d = (RelativeLayout) findViewById(R.id.rl_alipay_info);
        this.h = (TextView) findViewById(R.id.tv_alipay_text);
        this.n = (RelativeLayout) findViewById(R.id.mRechargeView);
        this.r = (RelativeLayout) findViewById(R.id.rl_progressBar);
        this.s = (TextView) findViewById(R.id.tv_loadingHint);
        this.q = (WebView) findViewById(R.id.weixin_h5);
        WebSettings settings = this.q.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        this.q.setWebViewClient(new bp(this));
        this.p = new H5WeixinPayPresenter();
        this.p.setCallBack(new bm(this));
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), null, getResources().getDrawable(R.drawable.titlebar_next_selector), getResources().getString(R.string.str_pay_weixin), new bf(this), new bl(this));
        a();
        this.g = new PayInfoEngine(new bq(this));
        this.g.getPayInfo(SaveUserInfoUtils.getEncpass(this), GlobleValue.getUserBean().getId());
        this.d.setOnClickListener(this);
        Animation translateAnimation = new TranslateAnimation((float) this.o, 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.n.startAnimation(translateAnimation);
    }

    protected void onResume() {
        super.onResume();
        b();
        if (this.u) {
            this.u = false;
            new DialogUtils(this).createConfirmDialog(12, "充值提示", "请确认微信中是否完成支付", "取消", "支付成功", new bo(this)).show();
        }
    }

    protected void onPause() {
        super.onPause();
    }

    private void a() {
        String alias = GlobleValue.getUserBean().getAlias();
        String str = this.a.getString(R.string.str_recharge_account) + alias;
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getColor(R.color.red_pay_text)), str.indexOf(alias), str.length(), 33);
        this.b.setText(spannableStringBuilder);
        alias = GlobleValue.getUserBean().getCoin6();
        str = this.a.getString(R.string.str_recharge_coin6) + alias + this.a.getString(R.string.user_coin6);
        spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.a.getColor(R.color.red_pay_text)), str.indexOf(alias), str.length(), 33);
        this.c.setText(spannableStringBuilder);
    }

    private void a(String str) {
        if (this.k != this.l) {
            this.s.setText(str);
            this.r.setVisibility(0);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.rl_alipay_info) {
            if (this.e == null && this.v != null && this.v.getPayrate().size() > 0) {
                View inflate = View.inflate(this, R.layout.phone_pay_select_list, null);
                this.e = new PopupWindow(inflate, this.d.getWidth(), -2, true);
                this.e.setBackgroundDrawable(new ColorDrawable(0));
                ListView listView = (ListView) inflate.findViewById(R.id.mListView);
                List payrate = this.v.getPayrate();
                this.f = new PaySelectAdapter(this, payrate);
                listView.setAdapter(this.f);
                listView.setOnItemClickListener(new bs(this, payrate));
            }
            if (this.e != null) {
                this.e.showAsDropDown(this.d, 0, -this.d.getHeight());
            }
        }
    }

    protected void showErrorDialog() {
        new DialogUtils(this).createConfirmDialogs(25, "提示", "充值失败", "确定", new bj(this)).show();
    }

    protected void showSucessDialog() {
        new DialogUtils(this).createConfirmDialogs(23, "提示", "充值成功", "确定", new bk(this)).show();
    }

    protected void loadUserAgent(WebSettings webSettings, String str) {
        if (str.contains("v.6.cn")) {
            webSettings.setUserAgentString(SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        } else {
            webSettings.setUserAgentString(webSettings.getUserAgentString());
        }
    }

    private void b() {
        this.r.setVisibility(8);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.r.isShown()) {
            return super.onKeyUp(i, keyEvent);
        }
        b();
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.w != null) {
            this.w.removeCallbacksAndMessages(null);
        }
    }

    static /* synthetic */ void a(WeixinPayActivity weixinPayActivity) {
        String id = GlobleValue.getUserBean().getId();
        String encpass = SaveUserInfoUtils.getEncpass(weixinPayActivity);
        if (weixinPayActivity.i != null) {
            weixinPayActivity.p.getH5WeixinPayInfo(weixinPayActivity.i.getCoin6(), weixinPayActivity.i.getMoney(), encpass, id);
            weixinPayActivity.a("生成订单中");
        }
    }

    static /* synthetic */ void a(WeixinPayActivity weixinPayActivity, H5WeixinPay h5WeixinPay) {
        weixinPayActivity.loadUserAgent(weixinPayActivity.q.getSettings(), h5WeixinPay.getLink());
        if (NetworkState.checkNet(weixinPayActivity)) {
            Map hashMap = new HashMap();
            hashMap.put("Referer", "http://m.v.6.cn/pay/wechat-h5?&");
            weixinPayActivity.q.loadUrl(h5WeixinPay.getLink(), hashMap);
            return;
        }
        weixinPayActivity.showToast(weixinPayActivity.a.getString(R.string.tip_network_error_str));
    }

    static /* synthetic */ void d(WeixinPayActivity weixinPayActivity) {
        if (weixinPayActivity.j == null) {
            weixinPayActivity.j = new OrderStatusEngine(new bh(weixinPayActivity));
        }
        String id = GlobleValue.getUserBean().getId();
        weixinPayActivity.j.orderStatusV2(weixinPayActivity.k, SaveUserInfoUtils.getEncpass(weixinPayActivity), id);
    }

    static /* synthetic */ void o(WeixinPayActivity weixinPayActivity) {
        LogUtils.i("WeixinPayActivity", "开始请求用户信息");
        if (weixinPayActivity.m == null) {
            weixinPayActivity.m = new UserInfoEngine(new bg(weixinPayActivity));
        }
        weixinPayActivity.m.getUserInfo(SaveUserInfoUtils.getEncpass(weixinPayActivity), "");
    }

    static /* synthetic */ void t(WeixinPayActivity weixinPayActivity) {
        if (!weixinPayActivity.isFinishing()) {
            LogUtils.i("WeixinPayActivity", "显示充值成功对话框");
            new DialogUtils(weixinPayActivity).createDiaglog(weixinPayActivity.a.getString(R.string.str_pay_delayed)).show();
        }
    }
}
