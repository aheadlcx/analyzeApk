package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.b.g;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.i;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends Dialog {
    private String a;
    private b b;
    private IUiListener c;
    private Handler d;
    private FrameLayout e;
    private LinearLayout f;
    private FrameLayout g;
    private ProgressBar h;
    private String i;
    private com.tencent.open.c.c j;
    private Context k;
    private com.tencent.open.web.security.b l;
    private boolean m = false;
    private int n;
    private String o;
    private String p;
    private long q = 0;
    private long r = 30000;
    private HashMap<String, Runnable> s;

    private class a extends WebViewClient {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            f.a("openSDK_LOG.AuthDialog", "-->Redirect URL: " + str);
            if (str.startsWith("auth://browser")) {
                JSONObject c = i.c(str);
                this.a.m = this.a.e();
                if (!this.a.m) {
                    if (c.optString("fail_cb", null) != null) {
                        this.a.a(c.optString("fail_cb"), "");
                    } else if (c.optInt("fall_to_wv") == 1) {
                        a.a(this.a, this.a.a.indexOf("?") > -1 ? "&" : "?");
                        a.a(this.a, (Object) "browser_error=1");
                        this.a.j.loadUrl(this.a.a);
                    } else {
                        String optString = c.optString("redir", null);
                        if (optString != null) {
                            this.a.j.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str.startsWith("auth://tauth.qq.com/")) {
                this.a.b.onComplete(i.c(str));
                this.a.dismiss();
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                this.a.b.onCancel();
                this.a.dismiss();
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                this.a.dismiss();
                return true;
            } else if (str.startsWith(Constants.DOWNLOAD_URI)) {
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring(Constants.DOWNLOAD_URI.length()))));
                    intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                    this.a.k.startActivity(intent);
                } catch (Throwable e) {
                    f.b("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", e);
                }
                return true;
            } else if (str.startsWith("auth://progress")) {
                try {
                    r0 = Uri.parse(str).getPathSegments();
                    if (r0.isEmpty()) {
                        return true;
                    }
                    int intValue = Integer.valueOf((String) r0.get(0)).intValue();
                    if (intValue == 0) {
                        this.a.g.setVisibility(8);
                        this.a.j.setVisibility(0);
                    } else if (intValue == 1) {
                        this.a.g.setVisibility(0);
                    }
                    return true;
                } catch (Exception e2) {
                    return true;
                }
            } else if (str.startsWith("auth://onLoginSubmit")) {
                try {
                    r0 = Uri.parse(str).getPathSegments();
                    if (!r0.isEmpty()) {
                        this.a.p = (String) r0.get(0);
                    }
                } catch (Exception e3) {
                }
                return true;
            } else if (this.a.l.a(this.a.j, str)) {
                return true;
            } else {
                f.c("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
                return false;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            f.c("openSDK_LOG.AuthDialog", "-->onReceivedError, errorCode: " + i + " | description: " + str);
            if (!i.b(this.a.k)) {
                this.a.b.onError(new UiError(9001, "当前网络不可用，请稍后重试！", str2));
                this.a.dismiss();
            } else if (this.a.o.startsWith("http://qzs.qq.com/open/mobile/login/qzsjump.html?")) {
                this.a.b.onError(new UiError(i, str, str2));
                this.a.dismiss();
            } else {
                long elapsedRealtime = SystemClock.elapsedRealtime() - this.a.q;
                if (this.a.n >= 1 || elapsedRealtime >= this.a.r) {
                    this.a.j.loadUrl(this.a.a());
                    return;
                }
                this.a.n = this.a.n + 1;
                this.a.d.postDelayed(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.j.loadUrl(this.a.a.o);
                    }
                }, 500);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            f.a("openSDK_LOG.AuthDialog", "-->onPageStarted, url: " + str);
            super.onPageStarted(webView, str, bitmap);
            this.a.g.setVisibility(0);
            this.a.q = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty(this.a.o)) {
                this.a.d.removeCallbacks((Runnable) this.a.s.remove(this.a.o));
            }
            this.a.o = str;
            Runnable dVar = new d(this.a, this.a.o);
            this.a.s.put(str, dVar);
            this.a.d.postDelayed(dVar, 120000);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            f.a("openSDK_LOG.AuthDialog", "-->onPageFinished, url: " + str);
            this.a.g.setVisibility(8);
            if (this.a.j != null) {
                this.a.j.setVisibility(0);
            }
            if (!TextUtils.isEmpty(str)) {
                this.a.d.removeCallbacks((Runnable) this.a.s.remove(str));
            }
        }

        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
            this.a.b.onError(new UiError(sslError.getPrimaryError(), "请求不合法，请检查手机安全设置，如系统时间、代理等。", "ssl error"));
            this.a.dismiss();
        }
    }

    private class b implements IUiListener {
        String a;
        String b;
        final /* synthetic */ a c;
        private String d;
        private IUiListener e;

        public b(a aVar, String str, String str2, String str3, IUiListener iUiListener) {
            this.c = aVar;
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.e = iUiListener;
        }

        private void a(String str) {
            try {
                onComplete(i.d(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.a, false);
            if (this.e != null) {
                this.e.onComplete(jSONObject);
                this.e = null;
            }
        }

        public void onError(UiError uiError) {
            String str = uiError.errorMessage != null ? uiError.errorMessage + this.a : this.a;
            g.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, str, false);
            this.c.a(str);
            if (this.e != null) {
                this.e.onError(uiError);
                this.e = null;
            }
        }

        public void onCancel() {
            if (this.e != null) {
                this.e.onCancel();
                this.e = null;
            }
        }
    }

    private class c extends Handler {
        final /* synthetic */ a a;
        private b b;

        public c(a aVar, b bVar, Looper looper) {
            this.a = aVar;
            super(looper);
            this.b = bVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.b.a((String) message.obj);
                    return;
                case 2:
                    this.b.onCancel();
                    return;
                case 3:
                    a.b(this.a.k, (String) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    class d implements Runnable {
        String a = "";
        final /* synthetic */ a b;

        public d(a aVar, String str) {
            this.b = aVar;
            this.a = str;
        }

        public void run() {
            f.a("openSDK_LOG.AuthDialog", "-->timeoutUrl: " + this.a + " | mRetryUrl: " + this.b.o);
            if (this.a.equals(this.b.o)) {
                this.b.b.onError(new UiError(9002, "请求页面超时，请稍后重试！", this.b.o));
                this.b.dismiss();
            }
        }
    }

    static /* synthetic */ String a(a aVar, Object obj) {
        String str = aVar.a + obj;
        aVar.a = str;
        return str;
    }

    static {
        try {
            Context a = com.tencent.open.utils.d.a();
            if (a == null) {
                f.c("openSDK_LOG.AuthDialog", "-->load lib fail, because context is null:" + AuthAgent.SECURE_LIB_NAME);
            } else if (new File(a.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME).exists()) {
                System.load(a.getFilesDir().toString() + "/" + AuthAgent.SECURE_LIB_NAME);
                f.c("openSDK_LOG.AuthDialog", "-->load lib success:" + AuthAgent.SECURE_LIB_NAME);
            } else {
                f.c("openSDK_LOG.AuthDialog", "-->fail, because so is not exists:" + AuthAgent.SECURE_LIB_NAME);
            }
        } catch (Throwable e) {
            f.b("openSDK_LOG.AuthDialog", "-->load lib error:" + AuthAgent.SECURE_LIB_NAME, e);
        }
    }

    public a(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.k = context;
        this.a = str2;
        this.b = new b(this, str, str2, qQToken.getAppId(), iUiListener);
        this.d = new c(this, this.b, context.getMainLooper());
        this.c = iUiListener;
        this.i = str;
        this.l = new com.tencent.open.web.security.b();
        getWindow().setSoftInputMode(32);
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        b();
        d();
        this.s = new HashMap();
    }

    public void onBackPressed() {
        if (!this.m) {
            this.b.onCancel();
        }
        super.onBackPressed();
    }

    protected void onStop() {
        super.onStop();
    }

    private String a(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        if (!TextUtils.isEmpty(this.p) && this.p.length() >= 4) {
            stringBuilder.append("_u_").append(this.p.substring(this.p.length() - 4));
        }
        return stringBuilder.toString();
    }

    private String a() {
        String str = "http://qzs.qq.com/open/mobile/login/qzsjump.html?" + this.a.substring(this.a.indexOf("?") + 1);
        f.c("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: http://qzs.qq.com/open/mobile/login/qzsjump.html?");
        return str;
    }

    private void b() {
        c();
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.j = new com.tencent.open.c.c(this.k);
        if (VERSION.SDK_INT >= 11) {
            this.j.setLayerType(1, null);
        }
        this.j.setLayoutParams(layoutParams);
        this.e = new FrameLayout(this.k);
        layoutParams.gravity = 17;
        this.e.setLayoutParams(layoutParams);
        this.e.addView(this.j);
        this.e.addView(this.g);
        setContentView(this.e);
    }

    private void c() {
        LayoutParams layoutParams;
        this.h = new ProgressBar(this.k);
        this.h.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.f = new LinearLayout(this.k);
        View view = null;
        if (this.i.equals("action_login")) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            view = new TextView(this.k);
            if (Locale.getDefault().getLanguage().equals("zh")) {
                view.setText("登录中...");
            } else {
                view.setText("Logging in...");
            }
            view.setTextColor(Color.rgb(255, 255, 255));
            view.setTextSize(18.0f);
            view.setLayoutParams(layoutParams);
        }
        layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f.setLayoutParams(layoutParams);
        this.f.addView(this.h);
        if (view != null) {
            this.f.addView(view);
        }
        this.g = new FrameLayout(this.k);
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.leftMargin = 80;
        layoutParams2.rightMargin = 80;
        layoutParams2.topMargin = 40;
        layoutParams2.bottomMargin = 40;
        layoutParams2.gravity = 17;
        this.g.setLayoutParams(layoutParams2);
        this.g.setBackgroundResource(17301504);
        this.g.addView(this.f);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        this.j.setWebViewClient(new a());
        this.j.setWebChromeClient(new WebChromeClient());
        this.j.clearFormData();
        this.j.clearSslPreferences();
        this.j.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.j.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        WebSettings settings = this.j.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        f.a("openSDK_LOG.AuthDialog", "-->mUrl : " + this.a);
        this.o = this.a;
        this.j.loadUrl(this.a);
        this.j.setVisibility(4);
        this.j.getSettings().setSavePassword(false);
        this.l.a(new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                try {
                    JniInterface.clearAllPWD();
                } catch (Exception e) {
                }
            }
        });
    }

    private boolean e() {
        b a = b.a();
        String c = a.c();
        com.tencent.connect.auth.b.a aVar = new com.tencent.connect.auth.b.a();
        aVar.a = this.c;
        aVar.b = this;
        aVar.c = c;
        String a2 = a.a(aVar);
        String substring = this.a.substring(0, this.a.indexOf("?"));
        Bundle b = i.b(this.a);
        b.putString("token_key", c);
        b.putString("serial", a2);
        b.putString("browser", "1");
        this.a = substring + "?" + HttpUtils.encodeUrl(b);
        return i.a(this.k, this.a);
    }

    private static void b(Context context, String str) {
        try {
            JSONObject d = i.d(str);
            int i = d.getInt("type");
            Toast.makeText(context.getApplicationContext(), d.getString("msg"), i).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        this.j.loadUrl("javascript:" + str + "(" + str2 + ");void(" + System.currentTimeMillis() + ");");
    }

    public void dismiss() {
        this.s.clear();
        this.d.removeCallbacksAndMessages(null);
        if (isShowing()) {
            super.dismiss();
        }
        if (this.j != null) {
            this.j.destroy();
            this.j = null;
        }
    }
}
