package com.budejie.www.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.j;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.htmlpage.c;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.c.d;
import com.budejie.www.c.e;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.b;
import com.budejie.www.http.i;
import com.budejie.www.http.n;
import com.budejie.www.label.widget.ProgressWebView;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.m;
import com.budejie.www.util.z;
import com.budejie.www.widget.parsetagview.ParseTagEditText;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class RichPostDetail extends BaseActvityWithLoadDailog implements OnClickListener, com.budejie.www.f.a {
    public static int a = 0;
    private ProgressDialog A;
    private b B;
    private com.budejie.www.c.b C;
    private e D;
    private c E;
    private TextWatcher F = new TextWatcher(this) {
        final /* synthetic */ RichPostDetail a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    d b;
    com.budejie.www.c.b c;
    final Handler d = new Handler(this) {
        final /* synthetic */ RichPostDetail a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            if (i2 == 4) {
                try {
                    this.a.f.setLove(this.a.f.getLove() + 1);
                } catch (Exception e) {
                }
            } else if (i2 == 5) {
                this.a.A = ProgressDialog.show(this.a, "", (String) message.obj, true, true);
            } else if (i2 == 6) {
                this.a.A.cancel();
            } else if (i2 == 7) {
                an.a(this.a, this.a.getString(R.string.already_collected), -1).show();
            } else if (i2 == 9) {
                this.a.f.setRepost(String.valueOf(Integer.parseInt(this.a.f.getRepost()) + 1));
                m.a(this.a.n, this.a.d, this.a.f);
            } else if (i2 == 91) {
                this.a.f.setRepost(String.valueOf(Integer.parseInt(this.a.f.getRepost()) + 1));
            } else if (i2 == 10) {
                an.a(this.a, this.a.getString(R.string.collect_failed), -1).show();
            } else if (i2 == 11) {
                CharSequence b = ai.b(this.a);
                if (an.j(this.a) && an.k(this.a) && !b.equals("")) {
                    an.a(this.a, false);
                    sendEmptyMessage(13);
                } else {
                    an.a(this.a, (int) R.string.collected, (int) R.drawable.collect_tip).show();
                }
                if (!TextUtils.isEmpty(b)) {
                    this.a.z = "add";
                    this.a.B.a(this.a.z, ai.b(this.a), (String) message.obj, 971);
                }
            } else if (i2 == 12) {
                an.a(this.a, (int) R.string.collect_fail, (int) R.drawable.collect_tip).show();
            } else if (i2 == 100001) {
                an.a(this.a.n, this.a.n.getString(R.string.forwardAndCollect_succeed), -1).show();
                if (!TextUtils.isEmpty(ai.b(this.a.n))) {
                    this.a.z = "add";
                    this.a.B.a(this.a.z, ai.b(this.a.n), (String) message.obj, 971);
                }
            } else if (i2 == 829) {
                r0 = (String) message.obj;
                if (this.a.C != null) {
                    this.a.C.a("collectTable", r0);
                }
                an.a(this.a, this.a.getString(R.string.delete_success), -1).show();
                this.a.z = "delete";
                this.a.B.a(this.a.z, ai.b(this.a), r0, 971);
            } else if (i2 == 13) {
                an.b(this.a, this.a.e);
            } else if (i2 == 14) {
                Map u = z.u((String) message.obj);
                if (u == null) {
                    this.a.y = an.a(this.a.n, this.a.n.getString(R.string.operate_fail), -1);
                    this.a.y.show();
                    return;
                }
                if ("1".equals((String) u.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                    this.a.y = an.a(this.a.n, this.a.getString(R.string.send_comment_success), -1);
                    this.a.y.show();
                    this.a.s.a(this.a.t, true, (int) R.string.sendsuccess);
                    if (!(this.a.f == null || this.a.f.getRichObject() == null)) {
                        RichPostDetail.a++;
                    }
                    this.a.a(true);
                    return;
                }
                this.a.y = an.a(this.a.n, (String) u.get(SocialConstants.PARAM_APP_DESC), -1);
                this.a.y.show();
            } else if (i2 == 1004) {
                HashMap k = z.k((String) message.obj);
                if (k != null) {
                    r0 = (String) k.get("result_desc");
                    if (TextUtils.isEmpty(r0)) {
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.operate_fail), -1);
                    } else {
                        this.a.y = an.a(this.a.n, r0, -1);
                    }
                } else {
                    this.a.y = an.a(this.a.n, this.a.n.getString(R.string.operate_fail), -1);
                }
                if (this.a.y != null) {
                    this.a.y.show();
                }
            } else if (i2 == 100002) {
            } else {
                if (i2 == 817) {
                    this.a.s.a(((Integer) message.obj).intValue());
                } else if (i2 == 812) {
                    r0 = (String) message.obj;
                    if (TextUtils.isEmpty(r0)) {
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "sina_faild");
                        return;
                    }
                    try {
                        i = Integer.parseInt(r0);
                    } catch (NumberFormatException e2) {
                    }
                    if (i < 0) {
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "sina_faild");
                        return;
                    }
                    r2 = z.c(r0);
                    if (r2 == null || r2.isEmpty()) {
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "sina_faild");
                        return;
                    }
                    r1 = (String) r2.get("result_msg");
                    if ("0".equals((String) r2.get(j.c))) {
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "sina_success");
                        this.a.v = (String) r2.get("id");
                        this.a.r.a(this.a.v, r2);
                        ai.a(this.a.n, this.a.v, Constants.SERVICE_SCOPE_FLAG_VALUE);
                        if (OauthWeiboBaseAct.mAccessToken != null) {
                            this.a.r.a(this.a.v, OauthWeiboBaseAct.mAccessToken.e());
                        }
                        this.a.x = this.a.q.a(this.a.v);
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_successed), -1);
                        this.a.y.show();
                        if (this.a.E.getListItemObject() != null) {
                            this.a.q.a(this.a.n, this.a.E.getListItemObject(), "sina", this.a.v, this.a.x, this.a.s, this.a.d);
                        }
                        this.a.E.setmPreference(this.a.u);
                        this.a.E.setWeiboDb(this.a.r);
                        this.a.E.setWeiboMap(this.a.x);
                        return;
                    }
                    an.a(this.a.n, r1, -1).show();
                } else if (i2 == 813) {
                    r0 = (String) message.obj;
                    if (TextUtils.isEmpty(r0)) {
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "tencent_faild");
                        return;
                    }
                    try {
                        i = Integer.parseInt(r0);
                    } catch (NumberFormatException e3) {
                    }
                    if (i < 0) {
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "tencent_faild");
                        return;
                    }
                    r2 = z.c(r0);
                    if (r2 == null || r2.isEmpty()) {
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "tencent_faild");
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_failed), -1);
                        this.a.y.show();
                        return;
                    }
                    r1 = (String) r2.get("result_msg");
                    if ("0".equals((String) r2.get(j.c))) {
                        MobclickAgent.onEvent(this.a.n, "weibo_bind", "tencent_success");
                        this.a.v = (String) r2.get("id");
                        this.a.r.a(this.a.v, r2);
                        ai.a(this.a.n, this.a.v, Constants.SERVICE_SCOPE_FLAG_VALUE);
                        this.a.x = this.a.q.a(this.a.v);
                        this.a.y = an.a(this.a.n, this.a.n.getString(R.string.bind_successed), -1);
                        this.a.y.show();
                        if (this.a.E.getListItemObject() != null) {
                            this.a.q.a(this.a.n, this.a.E.getListItemObject(), "qq", this.a.v, this.a.x, this.a.s, this.a.d);
                        }
                        this.a.E.setmPreference(this.a.u);
                        this.a.E.setWeiboDb(this.a.r);
                        this.a.E.setWeiboMap(this.a.x);
                        return;
                    }
                    an.a(this.a.n, r1, -1).show();
                } else if (i2 == 100) {
                    this.a.a();
                }
            }
        }
    };
    private ProgressWebView e;
    private ListItemObject f;
    private String h;
    private boolean i = false;
    private InputMethodManager j;
    private ImageView k;
    private TextView l;
    private ParseTagEditText m;
    private RichPostDetail n;
    private com.budejie.www.g.b o;
    private IWXAPI p;
    private n q;
    private com.budejie.www.c.m r;
    private com.elves.update.a s;
    private int t;
    private SharedPreferences u;
    private String v;
    private String w;
    private HashMap<String, String> x;
    private Toast y;
    private String z = "add";

    class a extends WebViewClient {
        final /* synthetic */ RichPostDetail b;

        a(RichPostDetail richPostDetail) {
            this.b = richPostDetail;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.d("MyWebViewClient", "shouldOverrideUrlLoading");
            webView.loadUrl(str);
            return true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.d("MyWebViewClient", "onPageStarted");
            super.onPageStarted(webView, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            Log.d("MyWebViewClient", "onPageFinished");
            try {
                if (!webView.canGoBack() || this.b.i) {
                    if (!this.b.e.canGoBack() && this.b.i) {
                        Log.d("MyWebViewClient", "!webView.canGoBack() && closeShow");
                        this.b.d();
                        this.b.i = false;
                    }
                    super.onPageFinished(webView, str);
                }
                Log.d("MyWebViewClient", "webView.canGoBack() && !closeShow");
                this.b.b(null);
                this.b.i = true;
                super.onPageFinished(webView, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rich_detail);
        this.f = (ListItemObject) getIntent().getSerializableExtra("listitem_object");
        i.a(getString(R.string.track_event_read_rich_text), com.budejie.www.http.j.a(this.f), getString(R.string.track_event_rich_text));
        if (this.f == null || this.f.getWid() == null) {
            this.h = "";
        } else {
            this.h = this.f.getWid() + "";
        }
        g();
        i();
        k();
        l();
    }

    protected void onPause() {
        super.onPause();
        b();
        this.e.pauseTimers();
        this.e.onPause();
        if (isFinishing()) {
            this.e.loadUrl("about:blank");
            setContentView(new FrameLayout(this));
        }
    }

    protected void a(final boolean z) {
        new Thread(this) {
            final /* synthetic */ RichPostDetail b;

            public void run() {
                try {
                    Thread.sleep(3000);
                    this.b.d.sendMessage(this.b.d.obtainMessage(931, Boolean.valueOf(z)));
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    private void g() {
        this.n = this;
        this.j = (InputMethodManager) getSystemService("input_method");
        this.s = new com.elves.update.a(this);
        this.q = new n(this);
        this.v = ai.b(this);
        this.o = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
        this.B = b.a(this.n, this.n);
        this.C = new com.budejie.www.c.b(this);
        this.D = new e(this);
        this.r = new com.budejie.www.c.m(this);
        this.b = new d(this);
        this.c = new com.budejie.www.c.b(this);
        this.x = this.q.a(this.v);
        this.u = getSharedPreferences("weiboprefer", 0);
        h();
    }

    private void h() {
        this.p = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.p.registerApp("wx592fdc48acfbe290");
    }

    private void i() {
        int i;
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        d(R.id.navigation_bar);
        setTitle((CharSequence) "评论");
        a(new OnClickListener(this) {
            final /* synthetic */ RichPostDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e.canGoBack()) {
                    this.a.e.goBack();
                } else {
                    this.a.finish();
                }
            }
        });
        View textView = new TextView(this.n);
        Object comment = this.f.getComment();
        if (TextUtils.isEmpty(comment)) {
            i = 0;
        } else {
            i = Integer.parseInt(comment);
        }
        a = i;
        if (i >= 10000) {
            textView.setText("评论(" + new DecimalFormat("##.0").format(((double) i) / 10000.0d) + "万)");
        } else {
            textView.setText("评论(" + i + ")");
        }
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RichPostDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setTextSize(14.0f);
        textView.setTextColor(getResources().getColor(R.color.main_red));
        textView.setBackgroundResource(com.budejie.www.util.j.bn);
        c().setRightView(textView);
        ImageView imageView = (ImageView) this.n.getLayoutInflater().inflate(R.layout.title_bar, null).findViewById(R.id.title_right_imgbtn);
        imageView.setVisibility(0);
        ((ViewGroup) imageView.getParent()).removeAllViews();
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RichPostDetail a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(view);
            }
        });
        c().setRightViewTwo(imageView);
    }

    public void a() {
        com.budejie.www.util.a.a((Activity) this, this.f, "", true);
    }

    private void j() {
        CharSequence charSequence;
        String str = "";
        if (a >= 10000) {
            charSequence = "评论(" + new DecimalFormat("##.0").format(((double) a) / 10000.0d) + "万)";
        } else {
            Object obj = "评论(" + a + ")";
        }
        ((TextView) c().getRightView()).setText(charSequence);
    }

    private void a(View view) {
        if (this.f != null) {
            view.setTag(this.f);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
            bundle.putSerializable("weiboMap", this.x);
            bundle.putSerializable("data", this.f);
            this.o.a(5, bundle, this.d, this.p, this.r, this.q, this.s, this.u, this.d).onClick(view);
        }
    }

    protected void onResume() {
        super.onResume();
        this.e.resumeTimers();
        this.e.onResume();
        j();
        this.o = new com.budejie.www.g.b(this, this.mSsoHandler, this.mTencent, this);
    }

    private void k() {
        this.m = (ParseTagEditText) findViewById(R.id.bottomEditText);
        this.m.setTextChangedListener(this.F);
        this.m.setListener(new com.budejie.www.widget.parsetagview.a(this));
        this.m.setFocusable(true);
        this.k = (ImageView) findViewById(R.id.informSomeone);
        this.k.setOnClickListener(this);
        this.l = (TextView) findViewById(R.id.sendBtn);
        this.l.setOnClickListener(this);
    }

    private void l() {
        this.e = (ProgressWebView) findViewById(R.id.wb_content);
        this.e.setWebViewClient(new a(this));
        this.e.getSettings().setJavaScriptEnabled(true);
        this.e.getSettings().setDomStorageEnabled(true);
        this.e.resumeTimers();
        this.e.getSettings().setUserAgentString(this.e.getSettings().getUserAgentString() + NetWorkUtil.a());
        if (VERSION.SDK_INT >= 17) {
            this.e.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.E = new c(this, this.d, this.o, this.s, this.r, this.q, this.x, this.p);
        this.E.setListItemObject(this.f);
        Bundle bundle = new Bundle();
        bundle.putString(HistoryOpenHelper.COLUMN_UID, ai.b(this));
        bundle.putSerializable("weiboMap", this.x);
        bundle.putSerializable("data", this.f);
        this.E.setArgs(bundle);
        this.e.addJavascriptInterface(this.E, AlibcConstants.PF_ANDROID);
        if (!TextUtils.isEmpty(this.f.getRichObject().getBody())) {
            this.e.loadDataWithBaseURL(this.f.getRichObject().getSourceUrl(), this.f.getRichObject().getBody(), "text/html", "utf-8", null);
        } else if (!TextUtils.isEmpty(this.f.getRichObject().getSourceUrl())) {
            this.e.loadUrl(this.f.getRichObject().getSourceUrl() + "&showShare=1");
        }
    }

    protected void onDestroy() {
        if (this.e != null) {
            this.e.pauseTimers();
        }
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.informSomeone:
                MobclickAgent.onEvent(this.n, "E02-A05", "评论条@人点击");
                startActivityForResult(new Intent(this, SelectorContactsActivity.class), 435);
                return;
            case R.id.sendBtn:
                this.v = this.u.getString("id", "");
                if (TextUtils.isEmpty(this.v)) {
                    an.a(this.n, 0, null, null, 0);
                    return;
                }
                m();
                this.j.hideSoftInputFromWindow(this.m.getWindowToken(), 0);
                this.m.setText("");
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 435 || i == 436) {
            String stringExtra = intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME));
            if (!TextUtils.isEmpty(stringExtra)) {
                StringBuilder append;
                if (i == 436) {
                    append = new StringBuilder("").append(stringExtra).append(" ");
                } else {
                    append = new StringBuilder("@").append(stringExtra).append(" ");
                }
                a(append);
            }
        }
    }

    private void a(StringBuilder stringBuilder) {
        int selectionStart = this.m.getSelectionStart();
        if (selectionStart < 0) {
            selectionStart = 0;
        }
        this.m.getEditableText().insert(selectionStart, stringBuilder);
    }

    private void m() {
        if (an.a((Context) this)) {
            this.w = this.m.getText().toString().trim();
            int length = this.w.length();
            Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
            int i = 2;
            if (!TextUtils.isEmpty(configParams)) {
                i = Integer.parseInt(configParams);
            }
            if (length < i) {
                this.y = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                this.y.show();
                return;
            } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                this.y = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                this.y.show();
                return;
            } else {
                this.v = this.u.getString("id", "");
                if (TextUtils.isEmpty(this.v)) {
                    an.a(this.n, 0, null, null, 0);
                    return;
                } else if (this.D.b(this.h, this.w)) {
                    this.y = an.a((Activity) this, getString(R.string.commentExist), -1);
                    this.y.show();
                    return;
                } else {
                    String str;
                    StringBuffer stringBuffer = new StringBuffer();
                    CharSequence stringBuffer2 = stringBuffer.toString();
                    if (stringBuffer2.endsWith(",")) {
                        stringBuffer2 = stringBuffer2.substring(0, stringBuffer.length() - 1);
                    }
                    if (TextUtils.isEmpty(stringBuffer2)) {
                        str = "";
                    } else {
                        CharSequence charSequence = stringBuffer2;
                    }
                    this.t = ((int) System.currentTimeMillis()) / 100;
                    this.s.a(this.t, getString(R.string.commend_sending));
                    this.q.a(this.h, this.w, str, this.v, this.d, 14, "", false, "html");
                    return;
                }
            }
        }
        Toast.makeText(this, R.string.nonet, 0).show();
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.v = this.u.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.q.a(mAccessToken, this.v, 812, this.d);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void a(int i, String str) {
    }

    public void a(int i) {
    }

    public void b() {
        this.e.loadUrl(String.format("javascript:stopMusic()", new Object[0]));
    }
}
