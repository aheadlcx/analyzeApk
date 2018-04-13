package cn.v6.sixrooms.room.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.socket.common.SocketUtil;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class MiniGameWebview extends WebView {
    private static final String JS_OBJ = "appAndroid";
    private static final String TAG = MiniGameWebview.class.getSimpleName();
    private Dialog alertDialog;
    private String encpass = null;
    private String loginUid = null;
    private BaseFragmentActivity mActivity;
    private DialogUtils mDialogUtils;
    private String mEventUrl;
    private String mRuid;

    private class b extends WebChromeClient {
        final /* synthetic */ MiniGameWebview a;

        private b(MiniGameWebview miniGameWebview) {
            this.a = miniGameWebview;
        }

        public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            this.a.showAlert(str2);
            jsResult.confirm();
            return true;
        }
    }

    class c extends WebViewClient {
        final /* synthetic */ MiniGameWebview a;

        c(MiniGameWebview miniGameWebview) {
            this.a = miniGameWebview;
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            LogUtils.e(MiniGameWebview.TAG, "onReceivedSslError" + i + "---" + str + "---" + str2);
            super.onReceivedError(webView, i, str, str2);
        }

        public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            LogUtils.e(MiniGameWebview.TAG, "onReceivedSslError" + sslError.toString());
            sslErrorHandler.proceed();
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            LogUtils.e(MiniGameWebview.TAG, "shouldOverrideUrlLoading" + str);
            webView.loadUrl(str);
            return true;
        }

        public final void onPageFinished(WebView webView, String str) {
            LogUtils.e(MiniGameWebview.TAG, "onPageFinished" + str);
            super.onPageFinished(webView, str);
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            LogUtils.e(MiniGameWebview.TAG, "onPageStarted" + str);
            super.onPageStarted(webView, str, bitmap);
        }
    }

    public MiniGameWebview(Context context) {
        super(context);
        init();
    }

    public MiniGameWebview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MiniGameWebview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void init() {
        setBackgroundColor(0);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        addJavascriptInterface(new MiniGameWebview$a(this, getContext()), JS_OBJ);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(2);
        getSettings().setUseWideViewPort(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setWebViewClient(new c(this));
        setWebChromeClient(new b());
    }

    public void initValue(BaseFragmentActivity baseFragmentActivity, String str) {
        this.mRuid = str;
        this.mActivity = baseFragmentActivity;
    }

    public void initLoadUrl(String str) {
        this.mEventUrl = str;
        loadUserAgent(getSettings());
        initJsVariable();
        loadUrl(this.mEventUrl);
    }

    private void initJsVariable() {
        this.loginUid = GlobleValue.getUserBean() == null ? "" : GlobleValue.getUserBean().getId();
        this.encpass = SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext());
        this.encpass = TextUtils.isEmpty(this.encpass) ? null : this.encpass;
    }

    private void loadUserAgent(WebSettings webSettings) {
        if (this.mEventUrl.contains("v.6.cn")) {
            webSettings.setUserAgentString(SocketUtil.encryptContent(AppInfoUtils.getAppInfo()));
        } else {
            webSettings.setUserAgentString(webSettings.getUserAgentString());
        }
    }

    private void showAlert(String str) {
        if (this.mDialogUtils == null) {
            this.mDialogUtils = new DialogUtils(this.mActivity);
        }
        if (!this.mActivity.isFinishing()) {
            this.alertDialog = this.mDialogUtils.createDiaglog(str);
            this.alertDialog.show();
        }
    }

    public static void clearCookies() {
        CookieManager.getInstance().removeAllCookie();
    }

    public static void clearWebViewCache() {
        V6Coop.getInstance().getContext().deleteDatabase("webview.db");
        V6Coop.getInstance().getContext().deleteDatabase("webviewCache.db");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroy();
    }

    public void scrollTo(int i, int i2) {
        super.scrollTo(0, 0);
    }
}
