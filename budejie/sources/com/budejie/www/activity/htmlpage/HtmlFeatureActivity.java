package com.budejie.www.activity.htmlpage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.busevent.AliPayAction;
import com.budejie.www.c.m;
import com.budejie.www.g.b;
import com.budejie.www.g.e;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.n;
import com.budejie.www.label.widget.ProgressWebView;
import com.budejie.www.label.widget.ProgressWebView.c;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.w;
import com.budejie.www.util.z;
import com.budejie.www.widget.f;
import com.facebook.common.util.UriUtil;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.RenderPriority;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.tauth.UiError;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
public class HtmlFeatureActivity extends BaseActvityWithLoadDailog {
    private static Bitmap B;
    private static String C;
    private static String D;
    private static String E;
    private static String F;
    public static int a = 0;
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;
    private static Activity v;
    private TextView A;
    private f G;
    private WebChromeClient H = new WebChromeClient(this) {
        final /* synthetic */ HtmlFeatureActivity a;

        {
            this.a = r1;
        }

        public void onReceivedTitle(WebView webView, String str) {
            Log.d("HtmlFeatureActivity", "onReceivedTitle: title=" + str);
            super.onReceivedTitle(webView, str);
            if (!TextUtils.isEmpty(str) && !str.startsWith(UriUtil.HTTP_SCHEME)) {
                this.a.z = str;
                if (this.a.z.length() > 7) {
                    this.a.z = this.a.z.substring(0, 7);
                }
                HtmlFeatureActivity.E = str;
                HtmlFeatureActivity.F = str;
                this.a.setTitle(this.a.z);
                if (!this.a.e.containsKey(HtmlFeatureActivity.D)) {
                    this.a.e.put(HtmlFeatureActivity.D, this.a.z);
                }
                this.a.A.setVisibility(0);
            }
        }
    };
    HashMap<String, String> e;
    ValueCallback<Uri> f;
    String h = "";
    public BroadcastReceiver i = new BroadcastReceiver(this) {
        final /* synthetic */ HtmlFeatureActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            Log.d("MyWebViewClient", "onReceive" + intent.getAction());
            if (this.a.m != null && intent.getAction().equals("action.send.topic.success")) {
                this.a.b();
            } else if (intent.getAction().equals("action.share.huodong.success")) {
                this.a.b(intent.getStringExtra("shareType"));
            } else if (intent.getAction().equals("action.ad.onclicked")) {
                this.a.g();
            } else if (intent.getAction().equals("action.ad.dismissed")) {
                this.a.h();
            }
        }
    };
    Handler j = new Handler(this) {
        final /* synthetic */ HtmlFeatureActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = false;
            Log.d("MyWebViewClient", "handleMessage msg.what=" + message.what);
            int i2 = message.what;
            if (i2 == 600) {
                Bundle bundle = (Bundle) message.obj;
                Object string = bundle.getString(j.c);
                final int i3 = bundle.getInt("notificationId");
                if (TextUtils.isEmpty(string)) {
                    this.a.u.a(i3, (boolean) i, (int) R.string.forwarfail);
                } else {
                    HashMap l = z.l(string);
                    this.a.u.a(i3, true, (String) l.get("msg"));
                    if ("0".equals(l.get(j.c))) {
                        this.a.b(bundle.getString("shareType"));
                    } else {
                        Toast.makeText(this.a.getApplicationContext(), R.string.forwarfail, i).show();
                    }
                }
                new Thread(this) {
                    final /* synthetic */ AnonymousClass7 b;

                    public void run() {
                        try {
                            Thread.sleep(1000);
                            this.b.a.j.sendMessage(this.b.a.j.obtainMessage(817, Integer.valueOf(i3)));
                        } catch (InterruptedException e) {
                        }
                    }
                }.start();
            } else if (i2 == 817) {
                this.a.u.a(((Integer) message.obj).intValue());
            } else if (i2 == 812) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "sina_faild");
                    return;
                }
                try {
                    i = Integer.parseInt(r0);
                } catch (NumberFormatException e) {
                }
                if (i < 0) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "sina_faild");
                    return;
                }
                r2 = z.c(r0);
                if (r2 == null || r2.isEmpty()) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "sina_faild");
                    return;
                }
                r1 = (String) r2.get("result_msg");
                if ("0".equals((String) r2.get(j.c))) {
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "sina_success");
                    this.a.s = (String) r2.get("id");
                    this.a.q.a(this.a.s, r2);
                    ai.a(HtmlFeatureActivity.v, this.a.s, Constants.SERVICE_SCOPE_FLAG_VALUE);
                    if (OauthWeiboBaseAct.mAccessToken != null) {
                        this.a.q.a(this.a.s, OauthWeiboBaseAct.mAccessToken.e());
                    }
                    this.a.r = this.a.p.a(this.a.s);
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_successed), -1);
                    this.a.o.show();
                    if (this.a.x.getHuodongBean() != null) {
                        n.a(HtmlFeatureActivity.v, this.a.x.getHuodongBean());
                        return;
                    }
                    return;
                }
                an.a(HtmlFeatureActivity.v, r1, -1).show();
            } else if (i2 == 813) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "tencent_faild");
                    return;
                }
                try {
                    i = Integer.parseInt(r0);
                } catch (NumberFormatException e2) {
                }
                if (i < 0) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "tencent_faild");
                    return;
                }
                r2 = z.c(r0);
                if (r2 == null || r2.isEmpty()) {
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "tencent_faild");
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    return;
                }
                r1 = (String) r2.get("result_msg");
                if ("0".equals((String) r2.get(j.c))) {
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "tencent_success");
                    this.a.s = (String) r2.get("id");
                    this.a.q.a(this.a.s, r2);
                    ai.a(HtmlFeatureActivity.v, this.a.s, Constants.SERVICE_SCOPE_FLAG_VALUE);
                    this.a.r = this.a.p.a(this.a.s);
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_successed), -1);
                    this.a.o.show();
                    if (this.a.x.getHuodongBean() != null) {
                        n.a(HtmlFeatureActivity.v, this.a.x.getHuodongBean());
                        return;
                    }
                    return;
                }
                an.a(HtmlFeatureActivity.v, r1, -1).show();
            } else if (i2 == 929) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "qzone_faild");
                    return;
                }
                int parseInt;
                try {
                    parseInt = Integer.parseInt(r0);
                } catch (NumberFormatException e3) {
                    parseInt = i;
                }
                if (parseInt < 0) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "qzone_faild");
                    return;
                }
                r2 = z.c(r0);
                if (r2 == null || r2.isEmpty()) {
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_failed), -1);
                    this.a.o.show();
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "qzone_faild");
                    return;
                }
                r1 = (String) r2.get("result_msg");
                if ("0".equals((String) r2.get(j.c))) {
                    MobclickAgent.onEvent(HtmlFeatureActivity.v, "weibo_bind", "qzone_success");
                    this.a.s = (String) r2.get("id");
                    this.a.q.a(this.a.s, r2);
                    ai.a(HtmlFeatureActivity.v, this.a.s, Constants.SERVICE_SCOPE_FLAG_VALUE);
                    this.a.r = this.a.p.a(this.a.s);
                    this.a.o = an.a(HtmlFeatureActivity.v, HtmlFeatureActivity.v.getString(R.string.bind_successed), -1);
                    this.a.o.show();
                    if (this.a.x.getHuodongBean() != null) {
                        n.a(HtmlFeatureActivity.v, this.a.x.getHuodongBean());
                        return;
                    }
                    return;
                }
                an.a(HtmlFeatureActivity.v, r1, -1).show();
            } else if (i2 == HtmlFeatureActivity.a) {
                if (this.a.G == null) {
                    this.a.G = new f(this.a, R.style.dialogTheme);
                }
                this.a.G.a("");
                this.a.G.show();
            } else if (i2 == HtmlFeatureActivity.b) {
                if (this.a.G != null) {
                    this.a.G.dismiss();
                }
            } else if (i2 == HtmlFeatureActivity.c) {
                if (this.a.G != null) {
                    this.a.G.dismiss();
                }
                Toast.makeText(HtmlFeatureActivity.v, R.string.save_successed, i).show();
            } else if (i2 == HtmlFeatureActivity.d) {
                if (this.a.G != null) {
                    this.a.G.dismiss();
                }
                Toast.makeText(HtmlFeatureActivity.v, R.string.save_failed, i).show();
            }
        }
    };
    private final String k = "http://www.curlend.com/";
    private boolean l = false;
    private ProgressWebView m;
    private b n;
    private Toast o;
    private n p;
    private m q;
    private HashMap<String, String> r;
    private String s;
    private SharedPreferences t;
    private com.elves.update.a u;
    private IWXAPI w;
    private c x;
    private String y;
    private String z;

    private class a implements c {
        final /* synthetic */ HtmlFeatureActivity a;

        private a(HtmlFeatureActivity htmlFeatureActivity) {
            this.a = htmlFeatureActivity;
        }

        public void a(ValueCallback<Uri> valueCallback, String str) {
            if (this.a.f == null) {
                this.a.f = valueCallback;
                a.c(this.a.h);
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                this.a.startActivityForResult(Intent.createChooser(intent, null), 3);
                this.a.h = Environment.getExternalStorageDirectory().getPath() + "/fuiou_wmp/temp";
                new File(this.a.h).mkdirs();
                this.a.h += File.separator + "compress.jpg";
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        v = this;
        this.t = getSharedPreferences("weiboprefer", 0);
        this.u = new com.elves.update.a(this);
        this.q = new m(this);
        this.p = new n(this);
        this.s = ai.b(this);
        this.r = this.p.a(this.s);
        this.n = new b(this, this.mSsoHandler, this.mTencent, this);
        this.w = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.w.registerApp("wx592fdc48acfbe290");
        setContentView(R.layout.html_featrue_layout);
        com.budejie.www.widget.a.a((Activity) this);
        q();
        p();
        C = "http://img.spriteapp.cn/ws/img/budejie_logo.png";
        D = this.y;
        o();
        IntentFilter intentFilter = new IntentFilter("action.send.topic.success");
        intentFilter.addAction("action.share.huodong.success");
        intentFilter.addAction("action.ad.onclicked");
        intentFilter.addAction("action.ad.dismissed");
        registerReceiver(this.i, intentFilter);
        EventBus.getDefault().register(this, 0);
    }

    public void a(String str, String str2, String str3, String str4, boolean z) {
        C = str2;
        D = str;
        E = str3;
        F = str4;
        o();
        if (z) {
            this.w = WXAPIFactory.createWXAPI(this, "wx560d77dcaa245c51", true);
            this.w.registerApp("wx560d77dcaa245c51");
            this.x.setApi(this.w);
        }
    }

    public void onEventMainThread(AliPayAction aliPayAction) {
        Log.d("HtmlFeatureActivity", "onEventMainThread ");
        if (aliPayAction == AliPayAction.OK) {
            Log.d("HtmlFeatureActivity", "onEventMainThread AliPayAction.OK");
            this.m.loadUrl("javascript:userpay.success()");
        } else if (aliPayAction == AliPayAction.CANCEL) {
            Log.d("HtmlFeatureActivity", "onEventMainThread AliPayAction.CANCEL");
            Toast.makeText(this, "支付取消", 0).show();
            this.m.loadUrl("javascript:userpay.cancel()");
        }
    }

    private void o() {
        new Thread(this) {
            final /* synthetic */ HtmlFeatureActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    HtmlFeatureActivity.B = an.b(this.a, HtmlFeatureActivity.C);
                } catch (Exception e) {
                }
            }
        }.start();
    }

    private void p() {
        d(R.id.navigation_bar);
        if (this.y.contains("hadimg")) {
            a((Context) this, (int) R.drawable.h5title);
        } else {
            setTitle((int) R.string.app_name);
        }
        a(new OnClickListener(this) {
            final /* synthetic */ HtmlFeatureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.m.canGoBack()) {
                    this.a.m.goBack();
                } else {
                    this.a.finish();
                }
            }
        });
        this.A = (TextView) v.getLayoutInflater().inflate(R.layout.title_bar, null).findViewById(R.id.title_right_btn);
        this.A.setText("分享");
        ((ViewGroup) this.A.getParent()).removeAllViews();
        this.A.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ HtmlFeatureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.z)) {
                    this.a.x.shareSNS("", HtmlFeatureActivity.E, HtmlFeatureActivity.C, HtmlFeatureActivity.F, HtmlFeatureActivity.D, "", "", "");
                }
            }
        });
        c().setRightView(this.A);
    }

    private void q() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.e = new HashMap();
        this.m = (ProgressWebView) findViewById(R.id.htmlWebView);
        this.m.setWebCallbackClientInterface(new a());
        this.m.setMyWebChromeClient(this.H);
        this.m.resumeTimers();
        WebSettings settings = this.m.getSettings();
        this.m.getSettings().setUserAgentString(settings.getUserAgentString() + NetWorkUtil.a());
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        this.x = new c(this, this.j, this.n, this.u, this.q, this.p, this.r, this.w);
        this.m.addJavascriptInterface(this.x, AlibcConstants.PF_ANDROID);
        this.m.setDownloadListener(new e(this));
        this.m.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ HtmlFeatureActivity b;

            {
                this.b = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Log.d("HtmlFeatureActivity", "shouldOverrideUrlLoading: aUrl=" + str);
                this.b.y = str;
                if (this.b.y.startsWith("weixin:")) {
                    try {
                        this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.b.y)));
                    } catch (Exception e) {
                        an.a(HtmlFeatureActivity.v, "微信支付仅支持微信6.0.2 及以上版本，请更新安装最新版本微信.", -1).show();
                    }
                } else if (this.b.y.startsWith("mqqapi:")) {
                    try {
                        this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.b.y)));
                    } catch (Exception e2) {
                        an.a(HtmlFeatureActivity.v, "QQ钱包仅支持手机QQ4.6.1 及以上版本，请更新安装最新版本手机QQ.", -1).show();
                    }
                } else if (this.b.y.startsWith("http:") || this.b.y.startsWith("https:")) {
                    this.b.a(this.b.y, "http://img.spriteapp.cn/ws/img/budejie_logo.png", this.b.z, this.b.z, false);
                } else {
                    try {
                        this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.b.y)));
                    } catch (Exception e3) {
                    }
                }
                return false;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                Log.d("HtmlFeatureActivity", "onPageStarted: ");
                super.onPageStarted(webView, str, bitmap);
                if (this.b.e.containsKey(str)) {
                    this.b.setTitle((CharSequence) this.b.e.get(str));
                    this.b.e.remove(str);
                }
            }

            public void onPageFinished(WebView webView, String str) {
                Log.d("HtmlFeatureActivity", "onPageFinished: ");
                try {
                    if (!webView.canGoBack() || this.b.l) {
                        if (!this.b.m.canGoBack() && this.b.l) {
                            Log.d("MyWebViewClient", "!webView.canGoBack() && closeShow");
                            this.b.d();
                            this.b.l = false;
                        }
                        super.onPageFinished(webView, str);
                    }
                    Log.d("MyWebViewClient", "webView.canGoBack() && !closeShow");
                    this.b.b(null);
                    this.b.l = true;
                    super.onPageFinished(webView, str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }
        });
        this.y = getIntent().getDataString();
        if (TextUtils.isEmpty(this.y)) {
            Toast.makeText(v, R.string.data_invalid, 0).show();
            finish();
            return;
        }
        a();
        if (!this.y.startsWith("http://d.api.budejie.com/credit/duiba/login")) {
            this.y = NetWorkUtil.b(v, this.y);
            this.m.loadUrl(this.y);
        } else if (TextUtils.isEmpty(ai.b(this))) {
            an.a((Activity) this, 1, null, null, (int) R$styleable.Theme_Custom_phone_edittext_bg);
        } else {
            this.y = NetWorkUtil.b(v, this.y);
            this.m.loadUrl(this.y);
        }
    }

    @TargetApi(21)
    public void a() {
        String str = "http://d.api.budejie.com/";
        String str2 = "http://api.budejie.com/";
        CookieSyncManager.createInstance(v);
        CookieManager instance = CookieManager.getInstance();
        if (VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(this.m, true);
        }
        instance.setAcceptCookie(true);
        Object b = NetWorkUtil.b(v);
        if (!TextUtils.isEmpty(b)) {
            String[] split = b.split(h.b);
            for (int i = 0; i < split.length; i++) {
                instance.setCookie(str, split[i]);
                instance.setCookie(str2, split[i]);
            }
        }
        CookieSyncManager.getInstance().sync();
    }

    private Uri a(Intent intent) {
        Object type;
        Uri data = intent.getData();
        String str = "";
        String str2 = "";
        int i = VERSION.SDK_INT >= 19 ? 1 : 0;
        if (UriUtil.LOCAL_FILE_SCHEME.equals(data.getScheme())) {
            str = data.getPath();
            type = intent.getType();
        } else if (i == 0 || !DocumentsContract.isDocumentUri(v, data)) {
            Cursor managedQuery = managedQuery(data, new String[]{"_data", "mime_type"}, null, null, null);
            if (managedQuery != null) {
                int columnIndexOrThrow = managedQuery.getColumnIndexOrThrow("_data");
                r2 = managedQuery.getColumnIndexOrThrow("mime_type");
                if (managedQuery.moveToFirst()) {
                    str = managedQuery.getString(columnIndexOrThrow);
                    type = managedQuery.getString(r2);
                }
            }
        } else {
            String documentId = DocumentsContract.getDocumentId(data);
            if (TextUtils.isEmpty(documentId) || documentId.split(":").length < 2) {
                return null;
            }
            String string;
            String str3 = documentId.split(":")[1];
            String[] strArr = new String[]{"_data", "mime_type"};
            Cursor query = v.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, "_id=?", new String[]{str3}, null);
            if (query != null) {
                i = query.getColumnIndex(strArr[0]);
                r2 = query.getColumnIndex(strArr[0]);
                if (query.moveToFirst()) {
                    string = query.getString(i);
                    documentId = query.getString(r2);
                } else {
                    documentId = str2;
                    string = str;
                }
                query.close();
            } else {
                documentId = str2;
                string = str;
            }
            str2 = documentId;
            str = string;
        }
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "仅支持图片文件", 0).show();
            return null;
        } else if ("image/gif".equals(type)) {
            return Uri.fromFile(new File(str));
        } else {
            File a = a.a(str, this.h);
            if (a != null) {
                return Uri.fromFile(a);
            }
            return null;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !this.m.canGoBack()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.m.goBack();
        return true;
    }

    public void onBackPressed() {
        if (this.m.canGoBack()) {
            this.m.goBack();
        } else {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        if (this.m != null) {
            this.m.pauseTimers();
        }
        super.onDestroy();
        unregisterReceiver(this.i);
        EventBus.getDefault().unregister(this);
        v = null;
    }

    public void a(String str) {
        a();
        c("javascript:loginSuccess('" + str + "')");
    }

    public void b() {
        c("javascript:sendTopicSuccess()");
    }

    public void g() {
        c("javascript:ADOnClicked()");
    }

    public void h() {
        c("javascript:ADOnDismissed()");
    }

    public void b(String str) {
        c("javascript:shareSNSSuccess('" + str + "')");
        if ("sms".equals(str)) {
            Toast.makeText(getApplicationContext(), "复制成功", 0).show();
        } else {
            Toast.makeText(getApplicationContext(), "分享成功", 0).show();
        }
    }

    public void c(String str) {
        this.m.loadUrl(str);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.t.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.s = this.t.getString("id", "");
            this.p.a((String) a.get("qzone_uid"), this.s, (String) a.get("qzone_token"), 929, this.j);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        this.o = an.a((Activity) this, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, -1);
        this.o.show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.s = this.t.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.p.a(mAccessToken, this.s, 812, this.j);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == R$styleable.Theme_Custom_phone_edittext_bg) {
            if (TextUtils.isEmpty(ai.b(this))) {
                finish();
            } else if (!(this.m == null || this.y == null)) {
                this.m.loadUrl(this.y);
            }
        }
        if (this.f == null) {
            w.a((Activity) this, false).a(i, i2, intent);
        } else if (intent == null) {
            this.f.onReceiveValue(null);
            this.f = null;
        } else {
            Object a;
            if (i == 3) {
                a = a(intent);
            } else {
                a = null;
            }
            this.f.onReceiveValue(a);
            this.f = null;
            super.onActivityResult(i, i2, intent);
            w.a((Activity) this, false).a(i, i2, intent);
        }
    }

    protected void onPause() {
        super.onPause();
        i();
        this.m.pauseTimers();
        this.m.onPause();
        if (isFinishing()) {
            this.m.loadUrl("about:blank");
            setContentView(new FrameLayout(this));
        }
    }

    protected void onResume() {
        super.onResume();
        this.m.resumeTimers();
        this.m.onResume();
        this.n = new b(this, this.mSsoHandler, this.mTencent, this);
    }

    public void i() {
        this.m.loadUrl(String.format("javascript:stopMusic()", new Object[0]));
    }
}
