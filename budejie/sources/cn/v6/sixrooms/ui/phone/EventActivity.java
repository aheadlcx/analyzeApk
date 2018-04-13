package cn.v6.sixrooms.ui.phone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.GameParamsEngine;
import cn.v6.sixrooms.net.NetworkState;
import cn.v6.sixrooms.room.game.GameCenterBean;
import cn.v6.sixrooms.room.utils.AppSclickManager;
import cn.v6.sixrooms.room.utils.AppSclickManager.Area;
import cn.v6.sixrooms.room.utils.AppSclickManager.Mod;
import cn.v6.sixrooms.room.utils.AppSclickManager.Page;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.KeyboardListener;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.PackageInfoUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
public class EventActivity extends BaseWebviewActivity {
    public static final String BESTFAMILY_EVENT = "bestfamily";
    public static final String BOXING_EVENT_AWARD = "boxing";
    public static final String CONTRACT_EVENT = "contract";
    public static final String GOV_REPORT_EVENT = "report";
    public static final String RECHARGE_PROTOCOL_EVENT = "recharge";
    public static final String SINGWAR_EVENT = "singwar";
    private String a = "";
    private String b = "";
    private RelativeLayout c;
    private View d;
    private TextView e;
    private TextView f;
    private GameParamsEngine g = null;
    private Map<String, Integer> h = new HashMap();
    public KeyboardListener keyboardListener;
    public RelativeLayout userManagerView;

    private class a extends WebViewClient {
        final /* synthetic */ EventActivity b;

        private a(EventActivity eventActivity) {
            this.b = eventActivity;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            int intValue;
            String str2 = null;
            if (this.b.e.getVisibility() == 8) {
                this.b.e.setVisibility(0);
            }
            if (this.b.h.containsKey(str)) {
                intValue = ((Integer) this.b.h.get(str)).intValue();
            } else {
                this.b.h.put(str, Integer.valueOf(1));
                intValue = 1;
            }
            if (intValue == 0) {
                return true;
            }
            if (str.contains(".apk")) {
                String[] split = str.split("#");
                String[] split2 = split[0].split("\\?");
                String str3 = split2[0];
                String[] strArr = new String[0];
                if (split2.length > 1) {
                    strArr = split2[1].split("\\&");
                }
                String str4 = null;
                Object obj = null;
                for (String str5 : r0) {
                    if (str5.startsWith("type=")) {
                        obj = str5.replace("type=", "").trim();
                    } else if (str5.startsWith("classname=")) {
                        str4 = str5.replace("classname=", "").trim();
                    } else if (str5.startsWith("gid=")) {
                        str2 = str5.replace("gid=", "").trim();
                    }
                }
                Intent intent;
                if (!PackageInfoUtils.isAppInstalled(this.b, split[1])) {
                    intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str3));
                    this.b.startActivity(intent);
                    return true;
                } else if ("1".equals(obj)) {
                    strArr = new String[3];
                    strArr[0] = SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext());
                    UserBean loginUserBean = LoginUtils.getLoginUserBean();
                    if (loginUserBean == null || strArr[0] == null) {
                        this.b.showLoginDialog();
                        return true;
                    }
                    if (TextUtils.isEmpty(loginUserBean.getId())) {
                        strArr[1] = "";
                    } else {
                        strArr[1] = loginUserBean.getId();
                    }
                    if (strArr[1] == null) {
                        this.b.showLoginDialog();
                        return true;
                    }
                    strArr[2] = str2;
                    GameCenterBean gameCenterBean = new GameCenterBean();
                    gameCenterBean.setPackageName(split[1]);
                    gameCenterBean.setClassName(str4);
                    if (this.b.g == null) {
                        this.b.g = new GameParamsEngine(new af(this, str));
                    }
                    this.b.g.getGameParams(gameCenterBean, strArr);
                    this.b.h.put(str, Integer.valueOf(0));
                } else {
                    intent = this.b.getPackageManager().getLaunchIntentForPackage(split[1]);
                    intent.setFlags(1048576);
                    this.b.startActivity(intent);
                    return true;
                }
            }
            if (TextUtils.isEmpty(this.b.a) || TextUtils.isEmpty(this.b.b) || !str.contains(this.b.a) || !PackageInfoUtils.isAppInstalled(this.b, this.b.b)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            this.b.finish();
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        CharSequence string;
        super.onCreate(bundle);
        initSlidingMenu();
        initBundle();
        setContentView(R.layout.phone_activity_event);
        initUI();
        if (CONTRACT_EVENT.equals(this.eventUrlFrom)) {
            string = getResources().getString(R.string.contract_event);
        } else if (BOXING_EVENT_AWARD.equals(this.eventUrlFrom)) {
            string = getResources().getString(R.string.boxing_event_award_titel);
        } else if (SINGWAR_EVENT.equals(this.eventUrlFrom)) {
            string = getResources().getString(R.string.singwar_event_titel);
        } else if (GOV_REPORT_EVENT.equals(this.eventUrlFrom)) {
            string = getResources().getString(R.string.gov_report_title);
        } else if (RECHARGE_PROTOCOL_EVENT.equals(this.eventUrlFrom)) {
            string = getResources().getString(R.string.recharge_protocol_title);
        } else {
            string = getResources().getString(R.string.event_title);
        }
        this.f.setText(string);
        this.e.setOnClickListener(new aa(this));
        findViewById(R.id.titlebar_left_withdrawals).setOnClickListener(new ab(this));
    }

    private boolean a(boolean z) {
        if (this.mWebView != null) {
            if (this.mWebView.canGoBack()) {
                this.mWebView.goBack();
                return false;
            } else if (z) {
                finish();
                overridePendingTransition(R.anim.msg_verify_fragment_in, R.anim.msg_verify_fragment_out);
                return false;
            }
        }
        return true;
    }

    protected void initBundle() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.eventUrl = extras.getString("eventurl");
            String str = this.eventUrl;
            String str2 = "&protocol=";
            CharSequence charSequence = "&qqbrows";
            if (!TextUtils.isEmpty(str) && str.contains(charSequence)) {
                try {
                    String[] split = str.split("#");
                    if (split.length > 0) {
                        this.b = split[1];
                        if (str.contains(str2)) {
                            str = str.substring(str.indexOf(str2) + str2.length());
                            this.a = str.substring(0, str.indexOf("&"));
                            this.eventUrl = split[0].replace(charSequence, "").replace(str2 + this.a, "");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.eventUrlFrom = extras.getString("eventUrlFrom");
            if (extras.getBoolean(AppSclickManager.KEY, false)) {
                AppSclickManager.getInstance().send(Page.INDEX, Area.BANANER, Mod.BANANER, this.eventUrl);
            }
        }
    }

    @SuppressLint({"NewApi"})
    protected void initUI() {
        this.userManagerView = (RelativeLayout) findViewById(R.id.userManagerView);
        Animation translateAnimation = new TranslateAnimation((float) DensityUtil.getScreenWidth(), 0.0f, 0.0f, 0.0f);
        translateAnimation.setDuration(250);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        this.userManagerView.startAnimation(translateAnimation);
        this.keyboardListener = new KeyboardListener(this);
        this.keyboardListener.startTask(1000);
        this.keyboardListener.setOnListener(new ae(this));
        this.e = (TextView) findViewById(R.id.titlebar_close_withdrawals);
        this.f = (TextView) findViewById(R.id.titlebar_title);
        this.mWebView = (WebView) findViewById(R.id.event_web);
        this.c = (RelativeLayout) findViewById(R.id.rl_progressBar);
        setWebViewAttribute();
        this.mWebView.setWebViewClient(new ac(this));
        this.d = findViewById(R.id.view_networkError);
        this.d.setOnClickListener(new ad(this));
        if (NetworkState.checkNet(this)) {
            this.mWebView.setWebChromeClient(this.webChromeClient);
            this.mWebView.loadUrl(this.eventUrl);
            return;
        }
        this.d.setVisibility(0);
    }

    protected void reload() {
        if (this.mWebView != null) {
            this.mWebView.reload();
        }
    }

    public void onBackPressed() {
        if (a(false)) {
            super.onBackPressed();
            overridePendingTransition(R.anim.msg_verify_fragment_in, R.anim.msg_verify_fragment_out);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.keyboardListener != null) {
            this.keyboardListener.stopTask();
        }
    }
}
