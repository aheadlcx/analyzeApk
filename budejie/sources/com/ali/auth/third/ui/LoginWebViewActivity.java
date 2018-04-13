package com.ali.auth.third.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.login.LoginConstants;
import com.ali.auth.third.login.LoginService;
import com.ali.auth.third.login.bridge.LoginBridge;
import com.ali.auth.third.login.context.LoginContext;
import com.ali.auth.third.login.task.RefreshSidTask;
import com.ali.auth.third.login.util.LoginStatus;
import com.ali.auth.third.ui.webview.AuthWebView;
import com.ali.auth.third.ui.webview.BaseWebViewActivity;
import com.ali.auth.third.ui.webview.BaseWebViewClient;
import com.ali.auth.third.ui.webview.BridgeWebChromeClient;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;

public class LoginWebViewActivity extends BaseWebViewActivity {
    public static final String CALLBACK = "https://www.alipay.com/webviewbridge";
    public static final String TAG = BaseWebViewActivity.class.getSimpleName();
    public static Activity originActivity;
    public static String scene;
    public static String token;
    private LoginService mLoginService;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.authWebView.addBridgeObject(LoginContext.loginBridgeName, new LoginBridge());
        this.authWebView.addBridgeObject(LoginContext.bindBridgeName, new LoginBridge());
        this.mLoginService = (LoginService) MemberSDK.getService(LoginService.class);
        KernelContext.context = getApplicationContext();
    }

    protected WebViewClient createWebViewClient() {
        return new BaseWebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                SDKLogger.d(LoginWebViewActivity.TAG, "shouldOverrideUrlLoading url=" + str);
                Uri parse = Uri.parse(str);
                if (LoginWebViewActivity.this.mLoginService.isLoginUrl(str)) {
                    new RefreshSidTask(LoginWebViewActivity.this.authWebView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                    return true;
                } else if (LoginWebViewActivity.this.checkWebviewBridge(str)) {
                    return LoginWebViewActivity.this.overrideCallback(parse);
                } else {
                    if (webView instanceof AuthWebView) {
                        ((AuthWebView) webView).loadUrl(str);
                        return true;
                    }
                    webView.loadUrl(str);
                    return true;
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                SDKLogger.d(LoginWebViewActivity.TAG, "onPageStarted url=" + str);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                SDKLogger.d(LoginWebViewActivity.TAG, "onPageFinished url=" + str);
            }

            public void onLoadResource(WebView webView, String str) {
                super.onLoadResource(webView, str);
                SDKLogger.d(LoginWebViewActivity.TAG, "onLoadResource url=" + str);
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                SDKLogger.d(LoginWebViewActivity.TAG, "onReceivedError error=" + webResourceError.toString());
            }
        };
    }

    public boolean checkWebviewBridge(String str) {
        Uri parse = Uri.parse(str);
        if (CALLBACK.contains(parse.getAuthority() + parse.getPath())) {
            return true;
        }
        return false;
    }

    private boolean overrideCallback(Uri uri) {
        Bundle serialBundle = serialBundle(uri.getQuery());
        if (serialBundle == null) {
            serialBundle = new Bundle();
        }
        serialBundle.getString("havana_mobile_reg_otherWebView");
        CharSequence string = serialBundle.getString("action");
        serialBundle.getString("loginId");
        if (TextUtils.isEmpty(string) || "quit".equals(string)) {
            setResult(ResultCode.SUCCESS.code, getIntent().putExtra("iv_token", serialBundle.getString("havana_iv_token")));
            finish();
            return true;
        } else if ("relogin".equals(string)) {
            finish();
            return true;
        } else if ("mobile_confirm_login".equals(string)) {
            return true;
        } else {
            if ("trustLogin".equals(string)) {
                return true;
            }
            if (!"continueLogin".equals(string)) {
                return false;
            }
            serialBundle.putString("aliusersdk_h5querystring", uri.getQuery());
            serialBundle.putString(INoCaptchaComponent.token, token);
            serialBundle.putString("scene", scene);
            setResult(ResultCode.CHECK.code, getIntent().putExtras(serialBundle));
            finish();
            return true;
        }
    }

    public static Bundle serialBundle(String str) {
        Bundle bundle = null;
        if (str != null && str.length() > 0) {
            String[] split = str.split("&");
            bundle = new Bundle();
            for (String str2 : split) {
                int indexOf = str2.indexOf(LoginConstants.EQUAL);
                if (indexOf > 0 && indexOf < str2.length() - 1) {
                    bundle.putString(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                }
            }
        }
        return bundle;
    }

    protected WebChromeClient createWebChromeClient() {
        return new BridgeWebChromeClient() {
            public void onReceivedTitle(WebView webView, String str) {
                if (!LoginWebViewActivity.this.canReceiveTitle) {
                    return;
                }
                if ((str == null || !str.contains("我喜欢")) && str != null) {
                    LoginWebViewActivity.this.titleText.setText(str);
                }
            }
        };
    }

    public void onBackHistory() {
        if (this.authWebView.canGoBack() && (this.authWebView.getUrl().contains("authorization-notice") || this.authWebView.getUrl().contains("agreement"))) {
            this.authWebView.goBack();
            return;
        }
        setResult(ResultCode.USER_CANCEL.code, new Intent());
        LoginStatus.resetLoginFlag();
        finish();
    }
}
