package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAppInfo;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.constant.WBConstants.Response;
import com.sina.weibo.sdk.constant.WBConstants.SDK;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.param.ShareWebViewRequestParam;

public class WbShareHandler {
    private boolean a = false;
    private Activity b;

    public WbShareHandler(Activity activity) {
        this.b = activity;
    }

    public boolean registerApp() {
        a(this.b, WBConstants.ACTION_WEIBO_REGISTER, WbSdk.getAuthInfo().getAppKey(), null, null);
        this.a = true;
        return true;
    }

    private void a(Context context, String str, String str2, String str3, Bundle bundle) {
        Intent intent = new Intent(str);
        String packageName = context.getPackageName();
        intent.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent.putExtra(Base.APP_PKG, packageName);
        intent.putExtra(Base.APP_KEY, str2);
        intent.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent.putExtra(WBConstants.SIGN, MD5.hexdigest(Utility.getSign(context, packageName)));
        if (!TextUtils.isEmpty(str3)) {
            intent.setPackage(str3);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.sendBroadcast(intent, WBConstants.ACTION_WEIBO_SDK_PERMISSION);
    }

    public void shareMessage(WeiboMultiMessage weiboMultiMessage, boolean z) {
        if (!this.a) {
            throw new RuntimeException("please call WbShareHandler.registerApp(),before use share function");
        } else if (!a() && z) {
        } else {
            if (z || a()) {
                WbAppActivator.getInstance(this.b, WbSdk.getAuthInfo().getAppKey()).activateApp();
                a(weiboMultiMessage);
                return;
            }
            b(weiboMultiMessage);
        }
    }

    private void a(WeiboMultiMessage weiboMultiMessage) {
        Bundle bundle = new Bundle();
        bundle.putInt(WBConstants.COMMAND_TYPE_KEY, 1);
        bundle.putString(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        bundle.putLong(WBConstants.SHARE_CALLBACK_ID, 0);
        bundle.putAll(weiboMultiMessage.toBundle(bundle));
        Intent intent = new Intent();
        intent.setClass(this.b, WbShareTransActivity.class);
        intent.putExtra(WBConstants.SHARE_START_PACKAGE, WeiboAppManager.getInstance(this.b).getWbAppInfo().getPackageName());
        intent.putExtra(WBConstants.SHARE_START_ACTION, WBConstants.ACTIVITY_WEIBO);
        intent.putExtra(WBConstants.SHARE_START_FLAG, 0);
        intent.putExtra(WBConstants.SHARE_START_ACTIVITY, this.b.getClass().getName());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            this.b.startActivity(intent);
        } catch (Exception e) {
        }
    }

    private void b(WeiboMultiMessage weiboMultiMessage) {
        WbAppActivator.getInstance(this.b, WbSdk.getAuthInfo().getAppKey()).activateApp();
        Intent intent = new Intent(this.b, WbShareTransActivity.class);
        String packageName = this.b.getPackageName();
        ShareWebViewRequestParam shareWebViewRequestParam = new ShareWebViewRequestParam(WbSdk.getAuthInfo(), WebRequestType.SHARE, "", 1, "微博分享", null, this.b);
        shareWebViewRequestParam.setContext(this.b);
        shareWebViewRequestParam.setHashKey("");
        shareWebViewRequestParam.setPackageName(packageName);
        Oauth2AccessToken readAccessToken = AccessTokenKeeper.readAccessToken(this.b);
        if (!(readAccessToken == null || TextUtils.isEmpty(readAccessToken.getToken()))) {
            shareWebViewRequestParam.setToken(readAccessToken.getToken());
        }
        shareWebViewRequestParam.setMultiMessage(weiboMultiMessage);
        Bundle bundle = new Bundle();
        shareWebViewRequestParam.fillBundle(bundle);
        intent.putExtras(bundle);
        intent.putExtra(WBConstants.SHARE_START_FLAG, 0);
        intent.putExtra(WBConstants.SHARE_START_ACTIVITY, this.b.getClass().getName());
        intent.putExtra(WBConstants.SHARE_START_ACTION, WBConstants.ACTIVITY_WEIBO);
        intent.putExtra(WBConstants.SHARE_START_GOTO_ACTIVITY, "com.sina.weibo.sdk.web.WeiboSdkWebActivity");
        this.b.startActivity(intent);
    }

    private boolean a() {
        WbAppInfo wbAppInfo = WeiboAppManager.getInstance(this.b).getWbAppInfo();
        return wbAppInfo != null && wbAppInfo.isLegal();
    }

    public void doResultIntent(Intent intent, WbShareCallback wbShareCallback) {
        if (wbShareCallback != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                switch (extras.getInt(Response.ERRCODE)) {
                    case 0:
                        wbShareCallback.onWbShareSuccess();
                        return;
                    case 1:
                        wbShareCallback.onWbShareCancel();
                        return;
                    case 2:
                        wbShareCallback.onWbShareFail();
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
