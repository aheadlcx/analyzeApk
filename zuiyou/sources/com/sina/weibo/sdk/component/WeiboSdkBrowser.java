package com.sina.weibo.sdk.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.c;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.ShareRequestParam.UploadPicResult;
import com.sina.weibo.sdk.component.view.LoadingBar;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class WeiboSdkBrowser extends Activity implements BrowserRequestCallBack {
    public static final String BROWSER_CLOSE_SCHEME = "sinaweibo://browser/close";
    public static final String BROWSER_WIDGET_SCHEME = "sinaweibo://browser/datatransfer";
    private static final String CANCEL_EN = "Close";
    private static final String CANCEL_ZH_CN = "关闭";
    private static final String CANCEL_ZH_TW = "关闭";
    private static final String CHANNEL_DATA_ERROR_EN = "channel_data_error";
    private static final String CHANNEL_DATA_ERROR_ZH_CN = "重新加载";
    private static final String CHANNEL_DATA_ERROR_ZH_TW = "重新載入";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_EN = "A network error occurs, please tap the button to reload";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_CN = "网络出错啦，请点击按钮重新加载";
    private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_TW = "網路出錯啦，請點擊按鈕重新載入";
    private static final String LOADINFO_EN = "Loading....";
    private static final String LOADINFO_ZH_CN = "加载中....";
    private static final String LOADINFO_ZH_TW = "載入中....";
    private static final String TAG = WeiboSdkBrowser.class.getName();
    private static final String WEIBOBROWSER_NO_TITLE_EN = "No Title";
    private static final String WEIBOBROWSER_NO_TITLE_ZH_CN = "无标题";
    private static final String WEIBOBROWSER_NO_TITLE_ZH_TW = "無標題";
    private static final /* synthetic */ a ajc$tjp_0 = null;
    private boolean isErrorPage;
    private Boolean isFromGame = Boolean.valueOf(false);
    private boolean isLoading;
    private String mHtmlTitle;
    private TextView mLeftBtn;
    private Button mLoadErrorRetryBtn;
    private LinearLayout mLoadErrorView;
    private LoadingBar mLoadingBar;
    private BrowserRequestParamBase mRequestParam;
    private String mSpecifyTitle;
    private TextView mTitleText;
    private String mUrl;
    private WebView mWebView;
    private WeiboWebViewClient mWeiboWebViewClient;

    public class AjcClosure1 extends org.aspectj.a.a.a {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            WeiboSdkBrowser.onCreate_aroundBody0((WeiboSdkBrowser) objArr2[0], (Bundle) objArr2[1], (org.aspectj.lang.a) objArr2[2]);
            return null;
        }
    }

    private class WeiboChromeClient extends WebChromeClient {
        private WeiboChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            WeiboSdkBrowser.this.mLoadingBar.drawProgress(i);
            if (i == 100) {
                WeiboSdkBrowser.this.isLoading = false;
                WeiboSdkBrowser.this.refreshAllViews();
            } else if (!WeiboSdkBrowser.this.isLoading) {
                WeiboSdkBrowser.this.isLoading = true;
                WeiboSdkBrowser.this.refreshAllViews();
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (!WeiboSdkBrowser.this.isWeiboCustomScheme(WeiboSdkBrowser.this.mUrl) && !WeiboSdkBrowser.this.isFromGame.booleanValue()) {
                WeiboSdkBrowser.this.mHtmlTitle = str;
                WeiboSdkBrowser.this.updateTitleName();
            }
        }
    }

    private static /* synthetic */ void ajc$preClinit() {
        b bVar = new b("WeiboSdkBrowser.java", WeiboSdkBrowser.class);
        ajc$tjp_0 = bVar.a("method-execution", bVar.a("4", "onCreate", "com.sina.weibo.sdk.component.WeiboSdkBrowser", "android.os.Bundle", "savedInstanceState", "", "void"), 124);
    }

    static {
        ajc$preClinit();
    }

    public static void startAuth(Context context, String str, AuthInfo authInfo, WeiboAuthListener weiboAuthListener) {
        AuthRequestParam authRequestParam = new AuthRequestParam(context);
        authRequestParam.setLauncher(BrowserLauncher.AUTH);
        authRequestParam.setUrl(str);
        authRequestParam.setAuthInfo(authInfo);
        authRequestParam.setAuthListener(weiboAuthListener);
        Intent intent = new Intent(context, WeiboSdkBrowser.class);
        intent.putExtras(authRequestParam.createRequestParamBundle());
        context.startActivity(intent);
    }

    public static void startShared(Context context, String str, AuthInfo authInfo, WeiboAuthListener weiboAuthListener) {
    }

    static final /* synthetic */ void onCreate_aroundBody0(WeiboSdkBrowser weiboSdkBrowser, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        if (weiboSdkBrowser.initDataFromIntent(weiboSdkBrowser.getIntent())) {
            weiboSdkBrowser.setContentView();
            weiboSdkBrowser.initWebView();
            if (weiboSdkBrowser.isWeiboShareRequestParam(weiboSdkBrowser.mRequestParam)) {
                weiboSdkBrowser.startShare();
                return;
            } else {
                weiboSdkBrowser.openUrl(weiboSdkBrowser.mUrl);
                return;
            }
        }
        weiboSdkBrowser.finish();
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(ajc$tjp_0, this, this, bundle);
        c.c().a(new AjcClosure1(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    private boolean initDataFromIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        this.mRequestParam = createBrowserRequestParam(extras);
        if (this.mRequestParam != null) {
            this.mUrl = this.mRequestParam.getUrl();
            this.mSpecifyTitle = this.mRequestParam.getSpecifyTitle();
        } else {
            String string = extras.getString("key_url");
            String string2 = extras.getString("key_specify_title");
            if (!TextUtils.isEmpty(string) && string.startsWith("http")) {
                this.mUrl = string;
                this.mSpecifyTitle = string2;
            }
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            return false;
        }
        LogUtil.d(TAG, "LOAD URL : " + this.mUrl);
        return true;
    }

    private void openUrl(String str) {
        this.mWebView.loadUrl(str);
    }

    private void startShare() {
        LogUtil.d(TAG, "Enter startShare()............");
        final ShareRequestParam shareRequestParam = (ShareRequestParam) this.mRequestParam;
        if (shareRequestParam.hasImage()) {
            LogUtil.d(TAG, "loadUrl hasImage............");
            new AsyncWeiboRunner(this).requestAsync(ShareRequestParam.UPLOAD_PIC_URL, shareRequestParam.buildUploadPicParam(new WeiboParameters(shareRequestParam.getAppKey())), Constants.HTTP_POST, new RequestListener() {
                public void onWeiboException(WeiboException weiboException) {
                    LogUtil.d(WeiboSdkBrowser.TAG, "post onWeiboException " + weiboException.getMessage());
                    shareRequestParam.sendSdkErrorResponse(WeiboSdkBrowser.this, weiboException.getMessage());
                    WeiboSdkBrowser.this.finish();
                }

                public void onComplete(String str) {
                    LogUtil.d(WeiboSdkBrowser.TAG, "post onComplete : " + str);
                    UploadPicResult parse = UploadPicResult.parse(str);
                    if (parse == null || parse.getCode() != 1 || TextUtils.isEmpty(parse.getPicId())) {
                        shareRequestParam.sendSdkErrorResponse(WeiboSdkBrowser.this, "upload pic faild");
                        WeiboSdkBrowser.this.finish();
                        return;
                    }
                    WeiboSdkBrowser.this.openUrl(shareRequestParam.buildUrl(parse.getPicId()));
                }
            });
            return;
        }
        openUrl(this.mUrl);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        if (isWeiboShareRequestParam(this.mRequestParam)) {
            this.mWebView.getSettings().setUserAgentString(Utility.generateUA(this));
        }
        this.mWebView.getSettings().setSavePassword(false);
        this.mWebView.setWebViewClient(this.mWeiboWebViewClient);
        this.mWebView.setWebChromeClient(new WeiboChromeClient());
        this.mWebView.requestFocus();
        this.mWebView.setScrollBarStyle(0);
        if (VERSION.SDK_INT >= 11) {
            this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        } else {
            removeJavascriptInterface(this.mWebView);
        }
    }

    private void setTopNavTitle() {
        this.mTitleText.setText(this.mSpecifyTitle);
        this.mLeftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (WeiboSdkBrowser.this.mRequestParam != null) {
                    WeiboSdkBrowser.this.mRequestParam.execRequest(WeiboSdkBrowser.this, 3);
                }
                WeiboSdkBrowser.this.finish();
            }
        });
    }

    private void updateTitleName() {
        CharSequence charSequence = "";
        if (!TextUtils.isEmpty(this.mHtmlTitle)) {
            charSequence = this.mHtmlTitle;
        } else if (!TextUtils.isEmpty(this.mSpecifyTitle)) {
            charSequence = this.mSpecifyTitle;
        }
        this.mTitleText.setText(charSequence);
    }

    private void setContentView() {
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        View linearLayout = new LinearLayout(this);
        linearLayout.setId(1);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        View initTitleBar = initTitleBar();
        View textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 2)));
        textView.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_common_shadow_top.9.png"));
        this.mLoadingBar = new LoadingBar(this);
        this.mLoadingBar.setBackgroundColor(0);
        this.mLoadingBar.drawProgress(0);
        this.mLoadingBar.setLayoutParams(new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 3)));
        linearLayout.addView(initTitleBar);
        linearLayout.addView(textView);
        linearLayout.addView(this.mLoadingBar);
        this.mWebView = new WebView(this);
        this.mWebView.setBackgroundColor(-1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, 1);
        this.mWebView.setLayoutParams(layoutParams);
        this.mLoadErrorView = new LinearLayout(this);
        this.mLoadErrorView.setVisibility(8);
        this.mLoadErrorView.setOrientation(1);
        this.mLoadErrorView.setGravity(17);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, 1);
        this.mLoadErrorView.setLayoutParams(layoutParams);
        initTitleBar = new ImageView(this);
        initTitleBar.setImageDrawable(ResourceManager.getDrawable(this, "weibosdk_empty_failed.png"));
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        int dp2px = ResourceManager.dp2px(this, 8);
        layoutParams2.bottomMargin = dp2px;
        layoutParams2.rightMargin = dp2px;
        layoutParams2.topMargin = dp2px;
        layoutParams2.leftMargin = dp2px;
        initTitleBar.setLayoutParams(layoutParams2);
        this.mLoadErrorView.addView(initTitleBar);
        initTitleBar = new TextView(this);
        initTitleBar.setGravity(1);
        initTitleBar.setTextColor(-4342339);
        initTitleBar.setTextSize(2, 14.0f);
        initTitleBar.setText(ResourceManager.getString(this, EMPTY_PROMPT_BAD_NETWORK_UI_EN, EMPTY_PROMPT_BAD_NETWORK_UI_ZH_CN, EMPTY_PROMPT_BAD_NETWORK_UI_ZH_TW));
        initTitleBar.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.mLoadErrorView.addView(initTitleBar);
        this.mLoadErrorRetryBtn = new Button(this);
        this.mLoadErrorRetryBtn.setGravity(17);
        this.mLoadErrorRetryBtn.setTextColor(-8882056);
        this.mLoadErrorRetryBtn.setTextSize(2, 16.0f);
        this.mLoadErrorRetryBtn.setText(ResourceManager.getString(this, CHANNEL_DATA_ERROR_EN, CHANNEL_DATA_ERROR_ZH_CN, CHANNEL_DATA_ERROR_ZH_TW));
        this.mLoadErrorRetryBtn.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "weibosdk_common_button_alpha.9.png", "weibosdk_common_button_alpha_highlighted.9.png"));
        layoutParams = new LinearLayout.LayoutParams(ResourceManager.dp2px(this, 142), ResourceManager.dp2px(this, 46));
        layoutParams.topMargin = ResourceManager.dp2px(this, 10);
        this.mLoadErrorRetryBtn.setLayoutParams(layoutParams);
        this.mLoadErrorRetryBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WeiboSdkBrowser.this.openUrl(WeiboSdkBrowser.this.mUrl);
                WeiboSdkBrowser.this.isErrorPage = false;
            }
        });
        this.mLoadErrorView.addView(this.mLoadErrorRetryBtn);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(this.mWebView);
        relativeLayout.addView(this.mLoadErrorView);
        setContentView(relativeLayout);
        setTopNavTitle();
    }

    private View initTitleBar() {
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LayoutParams(-1, ResourceManager.dp2px(this, 45)));
        relativeLayout.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_navigationbar_background.9.png"));
        this.mLeftBtn = new TextView(this);
        this.mLeftBtn.setClickable(true);
        this.mLeftBtn.setTextSize(2, 17.0f);
        this.mLeftBtn.setTextColor(ResourceManager.createColorStateList(-32256, 1728020992));
        this.mLeftBtn.setText(ResourceManager.getString(this, CANCEL_EN, "关闭", "关闭"));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(5);
        layoutParams.addRule(15);
        layoutParams.leftMargin = ResourceManager.dp2px(this, 10);
        layoutParams.rightMargin = ResourceManager.dp2px(this, 10);
        this.mLeftBtn.setLayoutParams(layoutParams);
        relativeLayout.addView(this.mLeftBtn);
        this.mTitleText = new TextView(this);
        this.mTitleText.setTextSize(2, 18.0f);
        this.mTitleText.setTextColor(-11382190);
        this.mTitleText.setEllipsize(TruncateAt.END);
        this.mTitleText.setSingleLine(true);
        this.mTitleText.setGravity(17);
        this.mTitleText.setMaxWidth(ResourceManager.dp2px(this, 160));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.mTitleText.setLayoutParams(layoutParams);
        relativeLayout.addView(this.mTitleText);
        return relativeLayout;
    }

    protected void refreshAllViews() {
        if (this.isLoading) {
            setViewLoading();
        } else {
            setViewNormal();
        }
    }

    private void setViewNormal() {
        updateTitleName();
        this.mLoadingBar.setVisibility(8);
    }

    private void setViewLoading() {
        this.mTitleText.setText(ResourceManager.getString(this, LOADINFO_EN, LOADINFO_ZH_CN, LOADINFO_ZH_TW));
        this.mLoadingBar.setVisibility(0);
    }

    private void handleReceivedError(WebView webView, int i, String str, String str2) {
        if (!str2.startsWith("sinaweibo")) {
            this.isErrorPage = true;
            promptError();
        }
    }

    private void promptError() {
        this.mLoadErrorView.setVisibility(0);
        this.mWebView.setVisibility(8);
    }

    private void hiddenErrorPrompt() {
        this.mLoadErrorView.setVisibility(8);
        this.mWebView.setVisibility(0);
    }

    private boolean isWeiboCustomScheme(String str) {
        if (!TextUtils.isEmpty(str) && "sinaweibo".equalsIgnoreCase(Uri.parse(str).getAuthority())) {
            return true;
        }
        return false;
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        NetworkHelper.clearCookies(this);
        super.onDestroy();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        if (this.mRequestParam != null) {
            this.mRequestParam.execRequest(this, 3);
        }
        finish();
        return true;
    }

    private BrowserRequestParamBase createBrowserRequestParam(Bundle bundle) {
        this.isFromGame = Boolean.valueOf(false);
        BrowserLauncher browserLauncher = (BrowserLauncher) bundle.getSerializable(BrowserRequestParamBase.EXTRA_KEY_LAUNCHER);
        BrowserRequestParamBase authRequestParam;
        if (browserLauncher == BrowserLauncher.AUTH) {
            authRequestParam = new AuthRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installAuthWeiboWebViewClient(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher == BrowserLauncher.SHARE) {
            authRequestParam = new ShareRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installShareWeiboWebViewClient(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher == BrowserLauncher.WIDGET) {
            authRequestParam = new WidgetRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installWidgetWeiboWebViewClient(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher != BrowserLauncher.GAME) {
            return null;
        } else {
            this.isFromGame = Boolean.valueOf(true);
            authRequestParam = new GameRequestParam(this);
            authRequestParam.setupRequestParam(bundle);
            installWeiboWebGameClient(authRequestParam);
            return authRequestParam;
        }
    }

    private boolean isWeiboShareRequestParam(BrowserRequestParamBase browserRequestParamBase) {
        return browserRequestParamBase != null && browserRequestParamBase.getLauncher() == BrowserLauncher.SHARE;
    }

    private void installAuthWeiboWebViewClient(AuthRequestParam authRequestParam) {
        this.mWeiboWebViewClient = new AuthWeiboWebViewClient(this, authRequestParam);
        this.mWeiboWebViewClient.setBrowserRequestCallBack(this);
    }

    private void installShareWeiboWebViewClient(ShareRequestParam shareRequestParam) {
        WeiboWebViewClient shareWeiboWebViewClient = new ShareWeiboWebViewClient(this, shareRequestParam);
        shareWeiboWebViewClient.setBrowserRequestCallBack(this);
        this.mWeiboWebViewClient = shareWeiboWebViewClient;
    }

    private void installWidgetWeiboWebViewClient(WidgetRequestParam widgetRequestParam) {
        WeiboWebViewClient widgetWeiboWebViewClient = new WidgetWeiboWebViewClient(this, widgetRequestParam);
        widgetWeiboWebViewClient.setBrowserRequestCallBack(this);
        this.mWeiboWebViewClient = widgetWeiboWebViewClient;
    }

    private void installWeiboWebGameClient(GameRequestParam gameRequestParam) {
        WeiboWebViewClient weiboGameClient = new WeiboGameClient(this, gameRequestParam);
        weiboGameClient.setBrowserRequestCallBack(this);
        this.mWeiboWebViewClient = weiboGameClient;
    }

    public void onPageStartedCallBack(WebView webView, String str, Bitmap bitmap) {
        LogUtil.d(TAG, "onPageStarted URL: " + str);
        this.mUrl = str;
        if (!isWeiboCustomScheme(str)) {
            this.mHtmlTitle = "";
        }
    }

    public boolean shouldOverrideUrlLoadingCallBack(WebView webView, String str) {
        LogUtil.i(TAG, "shouldOverrideUrlLoading URL: " + str);
        return false;
    }

    public void onPageFinishedCallBack(WebView webView, String str) {
        LogUtil.d(TAG, "onPageFinished URL: " + str);
        if (this.isErrorPage) {
            promptError();
            return;
        }
        this.isErrorPage = false;
        hiddenErrorPrompt();
    }

    public void onReceivedErrorCallBack(WebView webView, int i, String str, String str2) {
        LogUtil.d(TAG, "onReceivedError: errorCode = " + i + ", description = " + str + ", failingUrl = " + str2);
        handleReceivedError(webView, i, str, str2);
    }

    public void onReceivedSslErrorCallBack(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        LogUtil.d(TAG, "onReceivedSslErrorCallBack.........");
    }

    public static void closeBrowser(Activity activity, String str, String str2) {
        WeiboCallbackManager instance = WeiboCallbackManager.getInstance(activity.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            instance.removeWeiboAuthListener(str);
            activity.finish();
        }
        if (!TextUtils.isEmpty(str2)) {
            instance.removeWidgetRequestCallback(str2);
            activity.finish();
        }
    }

    public void removeJavascriptInterface(WebView webView) {
        if (VERSION.SDK_INT < 11) {
            try {
                webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[0]).invoke("searchBoxJavaBridge_", new Object[0]);
            } catch (Exception e) {
                LogUtil.e(TAG, e.toString());
            }
        }
    }
}
