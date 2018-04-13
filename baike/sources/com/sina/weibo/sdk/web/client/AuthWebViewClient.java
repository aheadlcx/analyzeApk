package com.sina.weibo.sdk.web.client;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.web.WebViewRequestCallback;
import com.sina.weibo.sdk.web.WeiboCallbackManager;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;
import com.umeng.analytics.pro.b;

public class AuthWebViewClient extends BaseWebViewClient {
    private Context c;
    private boolean d = false;

    public AuthWebViewClient(WebViewRequestCallback webViewRequestCallback, Context context, BaseWebViewRequestParam baseWebViewRequestParam) {
        super(webViewRequestCallback, baseWebViewRequestParam);
        this.c = context;
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b != null) {
            this.b.shouldOverrideUrlLoadingCallBack(webView, str);
        }
        return a(str);
    }

    private boolean a(String str) {
        if (str.startsWith("sms:")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", ""));
            intent.setType("vnd.android-dir/mms-sms");
            this.c.startActivity(intent);
            return true;
        } else if (!str.startsWith(WeiboSdkWebActivity.BROWSER_CLOSE_SCHEME)) {
            return false;
        } else {
            if (this.a.getBaseData() == null || TextUtils.isEmpty(this.a.getBaseData().getCallback())) {
                return true;
            }
            String callback = this.a.getBaseData().getCallback();
            WeiboCallbackManager instance = WeiboCallbackManager.getInstance();
            if (instance.getWeiboAuthListener(callback) != null) {
                instance.getWeiboAuthListener(callback).cancel();
            }
            instance.removeWeiboAuthListener(callback);
            return true;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.b != null) {
            this.b.onPageStartedCallBack(webView, str, bitmap);
        }
        if (!str.startsWith(this.a.getBaseData().getAuthInfo().getRedirectUrl()) || this.d) {
            super.onPageStarted(webView, str, bitmap);
            return;
        }
        this.d = true;
        b(str);
        webView.stopLoading();
        if (this.b != null) {
            this.b.closePage();
        }
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.b != null) {
            this.b.onPageFinishedCallBack(webView, str);
        }
    }

    @RequiresApi(api = 23)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (this.b != null) {
            this.b.onReceivedErrorCallBack(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.b != null) {
            this.b.onReceivedErrorCallBack(webView, i, str, str2);
        }
    }

    private void b(String str) {
        Bundle parseUrl = Utility.parseUrl(str);
        String string = parseUrl.getString(b.J);
        String string2 = parseUrl.getString("error_code");
        String string3 = parseUrl.getString("error_description");
        WbAuthListener wbAuthListener = null;
        if (!(this.a.getBaseData() == null || TextUtils.isEmpty(this.a.getBaseData().getCallback()))) {
            String callback = this.a.getBaseData().getCallback();
            WeiboCallbackManager instance = WeiboCallbackManager.getInstance();
            wbAuthListener = instance.getWeiboAuthListener(callback);
            instance.removeWeiboAuthListener(callback);
        }
        if (string == null && string2 == null) {
            if (wbAuthListener != null) {
                Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(parseUrl);
                AccessTokenKeeper.writeAccessToken(this.c, parseAccessToken);
                wbAuthListener.onSuccess(parseAccessToken);
            }
        } else if (wbAuthListener != null) {
            wbAuthListener.onFailure(new WbConnectErrorMessage(string2, string3));
        }
    }

    public void closeWeb() {
        super.closeWeb();
        if (this.a.getBaseData() != null && !TextUtils.isEmpty(this.a.getBaseData().getCallback())) {
            String callback = this.a.getBaseData().getCallback();
            WeiboCallbackManager instance = WeiboCallbackManager.getInstance();
            if (instance.getWeiboAuthListener(callback) != null) {
                instance.getWeiboAuthListener(callback).cancel();
            }
            instance.removeWeiboAuthListener(callback);
        }
    }

    public boolean onBackKeyDown() {
        closeWeb();
        if (this.b != null) {
            this.b.closePage();
        }
        return true;
    }
}
