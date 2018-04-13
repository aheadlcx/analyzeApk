package com.sina.weibo.sdk.web.client;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.constant.WBConstants.Response;
import com.sina.weibo.sdk.utils.WbUtils;
import com.sina.weibo.sdk.web.WebViewRequestCallback;
import com.sina.weibo.sdk.web.WeiboCallbackManager;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;

public class ShareWebViewClient extends BaseWebViewClient {
    private Activity c;

    public ShareWebViewClient(Activity activity, WebViewRequestCallback webViewRequestCallback, BaseWebViewRequestParam baseWebViewRequestParam) {
        super(webViewRequestCallback, baseWebViewRequestParam);
        this.c = activity;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        if (this.b != null) {
            this.b.onPageStartedCallBack(webView, str, bitmap);
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
        if (!str.startsWith(WeiboSdkWebActivity.BROWSER_CLOSE_SCHEME)) {
            return false;
        }
        Bundle parseUri = WbUtils.parseUri(str);
        if (!(this.a.getBaseData() == null || TextUtils.isEmpty(this.a.getBaseData().getCallback()))) {
            String callback = this.a.getBaseData().getCallback();
            WeiboCallbackManager instance = WeiboCallbackManager.getInstance();
            if (!(instance.getWeiboAuthListener(callback) == null || parseUri.isEmpty())) {
                instance.removeWeiboAuthListener(callback);
            }
        }
        CharSequence string = parseUri.getString("code");
        String string2 = parseUri.getString("msg");
        if (TextUtils.isEmpty(string)) {
            sendSdkCancleResponse(this.c);
        } else if ("0".equals(string)) {
            sendSdkOkResponse(this.c);
        } else {
            sendSdkErrorResponse(this.c, string2);
        }
        if (this.b != null) {
            this.b.closePage();
        }
        return true;
    }

    private void a(Activity activity, int i, String str) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            Intent intent = new Intent(WBConstants.ACTIVITY_REQ_SDK);
            String string = extras.getString("packageName");
            intent.setFlags(131072);
            intent.setPackage(string);
            intent.putExtras(extras);
            intent.putExtra(Base.APP_PKG, activity.getPackageName());
            intent.putExtra(Response.ERRCODE, i);
            intent.putExtra(Response.ERRMSG, str);
            try {
                activity.startActivityForResult(intent, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
            } catch (ActivityNotFoundException e) {
            }
        }
    }

    public void sendSdkCancleResponse(Activity activity) {
        a(activity, 1, "send cancel!!!");
    }

    public void sendSdkOkResponse(Activity activity) {
        a(activity, 0, "send ok!!!");
    }

    public void sendSdkErrorResponse(Activity activity, String str) {
        a(activity, 2, str);
    }

    public void closeWeb() {
        super.closeWeb();
        sendSdkCancleResponse(this.c);
    }

    public boolean onBackKeyDown() {
        closeWeb();
        if (this.b != null) {
            this.b.closePage();
        }
        return true;
    }
}
