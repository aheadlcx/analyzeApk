package cn.tatagou.sdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.Coupon;
import cn.tatagou.sdk.pojo.H5Params;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.util.a;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.d;
import cn.tatagou.sdk.util.l;
import cn.tatagou.sdk.util.m;
import cn.tatagou.sdk.util.p;
import cn.tatagou.sdk.util.q;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.IconTextView;
import cn.tatagou.sdk.view.TtgWebView;
import cn.tatagou.sdk.view.b;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyCartsPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;

public class TaobaoH5Activity extends Activity implements OnClickListener {
    private static final String b = TaobaoH5Activity.class.getSimpleName();
    public boolean a;
    private WebView c;
    private TextView d;
    private WebViewClient e;
    private WebChromeClient f;
    private String g;
    private LinearLayout h;
    private TextView i;
    private TextView j;
    private WebView k;
    private LinearLayout l;
    private TextView m;
    private boolean n = false;
    private H5Params o;
    private String p;
    private int q;
    private boolean r = false;
    private LinearLayout s;
    private String t;
    private String u;
    private c v = new c(this) {
        final /* synthetic */ TaobaoH5Activity a;

        {
            this.a = r1;
        }

        public void setTbLogin(int i) {
            super.setTbLogin(i);
            if (i == 1) {
                this.a.a = true;
                this.a.c();
                this.a.a(true);
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ttg_taobaoh5_activity);
        this.c = (WebView) findViewById(R.id.ttg_taobao_webview);
        this.o = (H5Params) getIntent().getParcelableExtra("params");
        if (this.o != null) {
            this.p = this.o.getTypeParams();
            Log.d(b, "mParams onCreate: " + this.p);
            this.q = this.o.getType();
        }
        k();
        e();
        d();
        c();
        f();
    }

    private void b() {
        if (this.o != null) {
            String str = p.isEmpty(this.o.getFinalPrices()) ? "￥0" : "￥" + this.o.getFinalPrices();
            Coupon coupon = this.o.getCoupon();
            if ("拍立减".equals(this.o.getCouponType()) && coupon == null) {
                b(str);
                this.r = true;
            } else if (coupon != null && !p.isEmpty(coupon.getCouponUrl())) {
                this.r = true;
                a(str, coupon.getCouponUrl());
            }
        }
    }

    private void c() {
        if (p.isNetworkOpen(this)) {
            this.c.setVisibility(0);
            if (this.q == 6) {
                if (this.c != null) {
                    if (this.f != null) {
                        this.c.setWebChromeClient(this.f);
                    }
                    if (this.e != null) {
                        this.c.setWebViewClient(this.e);
                    }
                    this.c.loadUrl(this.p);
                }
                if (this.h != null) {
                    this.h.setVisibility(8);
                }
            } else {
                a(this.c, this.q);
            }
            b();
            return;
        }
        h();
        i();
        this.c.setVisibility(8);
    }

    private void d() {
        this.e = new WebViewClient(this) {
            final /* synthetic */ TaobaoH5Activity a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                super.shouldOverrideUrlLoading(webView, str);
                Log.d(TaobaoH5Activity.b, "shouldOverrideUrlLoading: " + str);
                this.a.a(str);
                if (p.isEmpty(str) || !str.contains("ttg://")) {
                    return false;
                }
                m.openTtgUrl(this.a, str, TtgConfig.getInstance().getPid());
                return true;
            }

            public void onPageFinished(WebView webView, String str) {
                Log.d(TaobaoH5Activity.b, "onPageFinished: " + str);
                this.a.a(str);
                boolean checkTaobaoLogin = a.checkTaobaoLogin();
                if (!this.a.a && checkTaobaoLogin) {
                    this.a.a = true;
                    this.a.a(false);
                    IUpdateViewManager.getInstance().notifyIUpdateView(TtgInterface.TB_AUTHORIZE, a.getTaoBaoUserInfo());
                }
                String jsPatch = Config.getInstance().getJsPatch();
                if (!(p.isEmpty(jsPatch) || this.a.c == null)) {
                    if (VERSION.SDK_INT >= 19) {
                        this.a.c.evaluateJavascript(jsPatch, new TaobaoH5Activity$1$1(this));
                    } else {
                        this.a.c.loadUrl("javascript:" + jsPatch);
                    }
                }
                super.onPageFinished(webView, str);
            }
        };
    }

    private void a(String str) {
        if (!(p.isEmpty(str) || str.contains("coupon"))) {
            this.t = p.isEmpty(this.t) ? str : this.t;
            this.u = str;
        }
        if (!p.isEmpty(str) && !p.isEmpty(this.p) && this.r) {
            if ((str.startsWith("https://h5.m.taobao.com/awp/core/detail.htm") || str.startsWith("https://detail.m.tmall.com/item.htm")) && str.contains(this.p)) {
                if (this.s != null) {
                    this.s.setVisibility(0);
                }
            } else if (!str.contains("coupon") && this.s != null) {
                this.s.setVisibility(8);
            }
        }
    }

    private void e() {
        this.f = new WebChromeClient(this) {
            final /* synthetic */ TaobaoH5Activity a;

            {
                this.a = r1;
            }

            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                if (this.a.d != null && this.a.o != null && p.isEmpty(this.a.o.getTitle())) {
                    this.a.d.setText(str);
                }
            }
        };
    }

    private void f() {
        g();
        this.a = a.checkTaobaoLogin();
        b.onSetStatusBarColor(this);
        h();
        findViewById(R.id.ttg_tv_backup).setOnClickListener(this);
        findViewById(R.id.ttg_tv_close).setOnClickListener(this);
        this.s = (LinearLayout) findViewById(R.id.ttg_h5_bottom);
    }

    private void g() {
        cn.tatagou.sdk.view.c.setTitleHeight(this, (RelativeLayout) findViewById(R.id.rl_title_bg), true);
        this.d = (TextView) findViewById(R.id.ttg_tv_title);
        this.d.setTextSize((float) TtgTitleBar.getInstance().getTitleSize());
        this.d.setTextColor(TtgTitleBar.getInstance().getTitleColor());
        if (TtgTitleBar.getInstance().getTitleFont() != null) {
            this.d.setTypeface(TtgTitleBar.getInstance().getTitleFont());
        }
        if (this.o != null) {
            this.d.setText(this.o.getTitle());
        }
        TextView textView = (TextView) findViewById(R.id.ttg_tv_backup);
        textView.setTextSize((float) TtgTitleBar.getInstance().getBackIconSize());
        textView.setPadding(p.dip2px(this, (float) TtgTitleBar.getInstance().getBackIconLeftPadding()), 0, 0, 0);
        if (!p.isEmpty(TtgTitleBar.getInstance().getBackIcon())) {
            textView.setText(TtgTitleBar.getInstance().getBackIcon());
        }
    }

    private void a(String str, String str2) {
        if (this.l == null) {
            this.l = (LinearLayout) findViewById(R.id.ttg_ly_coupon);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ttg_rl_coupon);
            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.ttg_ly_bc_bottom);
            this.m = (TextView) findViewById(R.id.ttg_tv_coupon_status);
            relativeLayout2.setVisibility(0);
            ((TextView) findViewById(R.id.ttg_tv_prices)).setText(str);
            this.l.setOnClickListener(this);
            relativeLayout2.setOnClickListener(this);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, p.dip2px(this, 260.0f));
            this.k = new TtgWebView(this);
            this.k.setLayoutParams(layoutParams);
            relativeLayout.addView(this.k);
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            View iconTextView = new IconTextView(this);
            iconTextView.setText(R.string.ttg_icon_close);
            iconTextView.setTextSize(25.0f);
            iconTextView.setTextColor(SupportMenu.CATEGORY_MASK);
            iconTextView.setLayoutParams(layoutParams);
            iconTextView.setGravity(5);
            relativeLayout.addView(iconTextView);
            iconTextView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TaobaoH5Activity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b(false);
                }
            });
        }
        this.a = a.checkTaobaoLogin();
        if (this.a) {
            a(this.k, 7, str2);
            b(true);
            return;
        }
        ((TextView) findViewById(R.id.ttg_tv_receive_coupon)).setText(R.string.ttg_login_get_coupon);
        b(false);
    }

    private void b(String str) {
        ((RelativeLayout) findViewById(R.id.ttg_ly_lessen_prices)).setVisibility(0);
        ((TextView) findViewById(R.id.ttg_tv_lessen_prices)).setText(str);
    }

    private void a(WebView webView, int i) {
        if (webView != null) {
            a(webView, i, this.p);
            webView.setVisibility(0);
            if (this.h != null) {
                this.h.setVisibility(8);
            }
        }
    }

    private void h() {
        if (this.h == null) {
            this.h = (LinearLayout) findViewById(R.id.ttg_ly_fail_layout);
            this.j = (TextView) findViewById(R.id.ttg_tv_first_title);
            this.i = (TextView) findViewById(R.id.ttg_tv_second_title);
            TextView textView = (TextView) findViewById(R.id.ttg_tv_try_again);
            q.onResetShapeThemeColor(textView, 0, 0, TtgConfig.getInstance().getThemeColor());
            this.h.setOnClickListener(this);
            textView.setOnClickListener(this);
        }
    }

    private void i() {
        if (this.h != null) {
            this.h.setVisibility(0);
            this.j.setText(R.string.ttg_load_fail);
            this.i.setText(R.string.ttg_net_bad);
        }
    }

    private void j() {
        if (!a.checkTaobaoLogin()) {
            Object detailType = (this.o == null || p.isEmpty(this.o.getDetailType())) ? null : this.o.getDetailType();
            detailType = ("N".equals(Config.getInstance().getAuthFirst()) || p.isEmpty(Config.getInstance().getAuthFirst())) ? null : (("Tmall".equals(Config.getInstance().getAuthFirst()) && "TMALL".equals(detailType)) || (("Taobao".equals(Config.getInstance().getAuthFirst()) && "TAOBAO".equals(detailType)) || "All".equals(Config.getInstance().getAuthFirst()))) ? 1 : null;
            if (detailType != null) {
                l.showToastCenter(this, getResources().getString(R.string.ttg_auth_hint));
                a.showLogin(this, this.v);
            }
        }
    }

    private void a(WebView webView, int i, String str) {
        AlibcBasePage alibcDetailPage;
        OpenType openType = OpenType.H5;
        if (i == 5 || i == 8) {
            j();
            alibcDetailPage = new AlibcDetailPage(str);
        } else {
            alibcDetailPage = i == 2 ? new AlibcMyCartsPage() : i == 3 ? new AlibcMyOrdersPage(0, true) : i == 7 ? new AlibcPage(str) : null;
        }
        AlibcTrade.show(this, webView, this.e, this.f, alibcDetailPage, new AlibcShowParams(openType, false), null, new HashMap(), new AlibcTradeCallback(this) {
            final /* synthetic */ TaobaoH5Activity a;

            {
                this.a = r1;
            }

            public void onTradeSuccess(TradeResult tradeResult) {
                Log.d(TaobaoH5Activity.b, " AlibcTrade onTradeSuccess: tradeResult");
            }

            public void onFailure(int i, String str) {
                Log.d(TaobaoH5Activity.b, "AlibcTrade onFailure: tradeResult:" + i + ",s:" + str);
            }
        });
    }

    private void k() {
        String str = TtgSDK.sSource;
        if (this.q == 8) {
            str = String.format("%s_APP", new Object[]{str});
        }
        try {
            this.g = d.a.valueOf(str).getValue();
        } catch (Throwable e) {
            Log.e(b, "mPid: " + e.getMessage(), e);
        }
        if (p.isEmpty(this.g) || TtgConfig.getInstance().getPid() == 0) {
            this.g = "mm_117613736_0_0";
        } else {
            this.g = this.g.concat(String.valueOf(TtgConfig.getInstance().getPid()));
        }
        Log.d("TTG", "TTG MAMA Pid::: " + this.g);
        AlibcTradeSDK.setTaokeParams(new AlibcTaokeParams(this.g, null, null));
        str = l();
        if (str != null) {
            AlibcTradeSDK.setISVCode(str);
        }
    }

    private String l() {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("V", Integer.valueOf(2));
            jSONObject.put("TID", p.phoneImei(this));
            jSONObject.put("AID", p.getAndroidID(this));
            Object appDeviceId = Config.getInstance().getAppDeviceId();
            if (!p.isEmpty(appDeviceId)) {
                appDeviceId = appDeviceId.replace("\"", "_").replace("'", "_");
            }
            jSONObject.put("SID", appDeviceId);
            jSONObject.put("SDK", (Object) "2.4.4.6");
            String toJSONString = jSONObject.toJSONString();
            str = toJSONString.replace("\"", "'").substring(1, toJSONString.length() - 1);
        } catch (Throwable e) {
            Log.e(b, "getIsVCode: " + e.getMessage(), e);
        }
        return str;
    }

    public void onBackPressed() {
        if (p.isEmpty(this.t) || this.t.equals(this.u)) {
            m();
            super.onBackPressed();
        } else if (this.c == null || !this.c.canGoBack()) {
            m();
            super.onBackPressed();
        } else {
            this.c.goBack();
        }
    }

    private void m() {
        IUpdateViewManager.getInstance().notifyIUpdateView("searchHideSoftInput", Boolean.valueOf(true));
        if (this.o != null) {
            if ("home".equals(this.o.getBack())) {
                TtgInterface.openTtgMain(this, "ttg://home", TtgConfig.getInstance().getPid());
            }
            if ("notify".equals(this.o.getNotify())) {
                IUpdateViewManager.getInstance().notifyIUpdateView(TtgConfigKey.KEY_TTGH5CLOSE, Boolean.valueOf(true));
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ttg_tv_backup) {
            if (this.c == null || !this.c.canGoBack()) {
                m();
                finish();
                return;
            }
            this.c.goBack();
        } else if (id == R.id.ttg_tv_close) {
            m();
            finish();
        } else if (id == R.id.ttg_tv_try_again) {
            c();
        } else if (id == R.id.ttg_ly_coupon) {
            this.l.setEnabled(false);
            b(false);
        } else if (id != R.id.ttg_ly_bc_bottom) {
        } else {
            if (this.a) {
                b(this.n);
            } else {
                a.showLogin(this, this.v);
            }
        }
    }

    private void a(boolean z) {
        if (this.o != null && this.o.getCoupon() != null && !p.isEmpty(this.o.getCoupon().getCouponUrl()) && this.k != null) {
            ((TextView) findViewById(R.id.ttg_tv_receive_coupon)).setText(R.string.ttg_get_coupon_now);
            a(this.k, 7, this.o.getCoupon().getCouponUrl());
            if (z) {
                b(true);
            }
        }
    }

    private void b(boolean z) {
        boolean z2 = true;
        if (this.l != null) {
            if (z) {
                this.l.setEnabled(true);
                this.l.setVisibility(0);
                this.l.startAnimation(AnimationUtils.loadAnimation(this, R.anim.out));
                if (this.m != null) {
                    this.m.setText(R.string.ttg_icon_coupon_hide);
                }
            } else {
                this.l.setVisibility(8);
                this.l.startAnimation(AnimationUtils.loadAnimation(this, R.anim.in));
                if (this.m != null) {
                    this.m.setText(R.string.ttg_icon_coupon_show);
                }
            }
            if (z) {
                z2 = false;
            }
            this.n = z2;
        }
    }

    protected void onResume() {
        if (this.c != null) {
            this.c.onResume();
            this.c.resumeTimers();
            if (this.r) {
                a(this.c.getUrl());
            }
        }
        super.onResume();
        Log.d(b, "mWebViewonResume: " + this.c.getUrl());
    }

    protected void onPause() {
        if (this.c != null) {
            this.c.onPause();
        }
        super.onPause();
    }

    protected void onDestroy() {
        AlibcTradeSDK.destory();
        if (this.e != null) {
            this.e = null;
        }
        if (this.f != null) {
            this.f = null;
        }
        a(this.k);
        a(this.c);
        super.onDestroy();
    }

    private void a(WebView webView) {
        if (webView != null) {
            ViewGroup viewGroup = (ViewGroup) webView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(webView);
            }
            webView.removeAllViews();
            webView.destroy();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        CallbackContext.onActivityResult(i, i2, intent);
    }
}
