package com.sina.weibo.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.statistic.WBAgent;
import com.sina.weibo.sdk.utils.AidTask;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.SecurityHelper;
import com.sina.weibo.sdk.utils.UIUtils;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.WeiboCallbackManager;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.param.AuthWebViewRequestParam;
import com.umeng.analytics.pro.b;

public class BaseSsoHandler {
    protected Activity a;
    protected WbAuthListener b;
    protected final int c = 3;
    protected int d = -1;
    protected int e = 3;

    protected enum AuthType {
        ALL,
        SsoOnly,
        WebOnly
    }

    public BaseSsoHandler(Activity activity) {
        this.a = activity;
        AidTask.getInstance(this.a).aidTaskInit(WbSdk.getAuthInfo().getAppKey());
    }

    public void authorize(WbAuthListener wbAuthListener) {
        a(WbAuthConstants.REQUEST_CODE_SSO_AUTH, wbAuthListener, AuthType.ALL);
        WbAppActivator.getInstance(this.a, WbSdk.getAuthInfo().getAppKey()).activateApp();
    }

    public void authorizeClientSso(WbAuthListener wbAuthListener) {
        a(WbAuthConstants.REQUEST_CODE_SSO_AUTH, wbAuthListener, AuthType.SsoOnly);
        WbAppActivator.getInstance(this.a, WbSdk.getAuthInfo().getAppKey()).activateApp();
    }

    public void authorizeWeb(WbAuthListener wbAuthListener) {
        a(WbAuthConstants.REQUEST_CODE_SSO_AUTH, wbAuthListener, AuthType.WebOnly);
        WbAppActivator.getInstance(this.a, WbSdk.getAuthInfo().getAppKey()).activateApp();
    }

    private void a(int i, WbAuthListener wbAuthListener, AuthType authType) {
        a();
        if (wbAuthListener == null) {
            throw new RuntimeException("please set auth listener");
        }
        this.b = wbAuthListener;
        if (authType != AuthType.WebOnly) {
            Object obj = null;
            if (authType == AuthType.SsoOnly) {
                obj = 1;
            }
            if (c()) {
                a(i);
            } else if (obj != null) {
                this.b.onFailure(new WbConnectErrorMessage());
            } else {
                b();
            }
        } else if (wbAuthListener != null) {
            b();
        }
    }

    protected void a(int i) {
        try {
            WbAppInfo wbAppInfo = WeiboAppManager.getInstance(this.a).getWbAppInfo();
            Intent intent = new Intent();
            intent.setClassName(wbAppInfo.getPackageName(), wbAppInfo.getAuthActivityName());
            intent.putExtras(WbSdk.getAuthInfo().getAuthBundle());
            intent.putExtra(WBConstants.COMMAND_TYPE_KEY, 3);
            intent.putExtra(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
            intent.putExtra("aid", Utility.getAid(this.a, WbSdk.getAuthInfo().getAppKey()));
            if (SecurityHelper.validateAppSignatureForIntent(this.a, intent)) {
                a(intent, i);
                try {
                    this.a.startActivityForResult(intent, this.d);
                } catch (Exception e) {
                    if (this.b != null) {
                        this.b.onFailure(new WbConnectErrorMessage());
                    }
                    d();
                }
            }
        } catch (Exception e2) {
        }
    }

    protected void a(Intent intent, int i) {
    }

    protected void a() {
        this.d = WbAuthConstants.REQUEST_CODE_SSO_AUTH;
    }

    protected void b() {
        AuthInfo authInfo = WbSdk.getAuthInfo();
        WeiboParameters weiboParameters = new WeiboParameters(authInfo.getAppKey());
        weiboParameters.put("client_id", authInfo.getAppKey());
        weiboParameters.put("redirect_uri", authInfo.getRedirectUrl());
        weiboParameters.put("scope", authInfo.getScope());
        weiboParameters.put(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "code");
        weiboParameters.put("version", WBConstants.WEIBO_SDK_VERSION_CODE);
        weiboParameters.put("luicode", "10000360");
        Oauth2AccessToken readAccessToken = AccessTokenKeeper.readAccessToken(this.a);
        if (!(readAccessToken == null || TextUtils.isEmpty(readAccessToken.getToken()))) {
            weiboParameters.put("trans_token", readAccessToken.getToken());
            weiboParameters.put("trans_access_token", readAccessToken.getToken());
        }
        weiboParameters.put("lfid", "OP_" + authInfo.getAppKey());
        Object aid = Utility.getAid(this.a, authInfo.getAppKey());
        if (!TextUtils.isEmpty(aid)) {
            weiboParameters.put("aid", aid);
        }
        weiboParameters.put("packagename", authInfo.getPackageName());
        weiboParameters.put("key_hash", authInfo.getKeyHash());
        String str = "https://open.weibo.cn/oauth2/authorize?" + weiboParameters.encodeUrl();
        if (NetworkHelper.hasInternetPermission(this.a)) {
            String str2 = null;
            if (this.b != null) {
                WeiboCallbackManager instance = WeiboCallbackManager.getInstance();
                str2 = instance.genCallbackKey();
                instance.setWeiboAuthListener(str2, this.b);
            }
            AuthWebViewRequestParam authWebViewRequestParam = new AuthWebViewRequestParam(authInfo, WebRequestType.AUTH, str2, "微博登录", str, this.a);
            Intent intent = new Intent(this.a, WeiboSdkWebActivity.class);
            Bundle bundle = new Bundle();
            authWebViewRequestParam.fillBundle(bundle);
            intent.putExtras(bundle);
            this.a.startActivity(intent);
            return;
        }
        UIUtils.showAlert(this.a, "Error", "Application requires permission to access the Internet");
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
        if (WbAuthConstants.REQUEST_CODE_SSO_AUTH != i) {
            return;
        }
        if (i2 == -1) {
            if (SecurityHelper.checkResponseAppLegal(this.a, WeiboAppManager.getInstance(this.a).getWbAppInfo(), intent)) {
                Object safeString = Utility.safeString(intent.getStringExtra(b.J));
                Object safeString2 = Utility.safeString(intent.getStringExtra("error_type"));
                Object safeString3 = Utility.safeString(intent.getStringExtra("error_description"));
                LogUtil.d(WBAgent.TAG, "error: " + safeString + ", error_type: " + safeString2 + ", error_description: " + safeString3);
                if (TextUtils.isEmpty(safeString) && TextUtils.isEmpty(safeString2) && TextUtils.isEmpty(safeString3)) {
                    Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(intent.getExtras());
                    if (parseAccessToken != null && parseAccessToken.isSessionValid()) {
                        LogUtil.d(WBAgent.TAG, "Login Success! " + parseAccessToken.toString());
                        AccessTokenKeeper.writeAccessToken(this.a, parseAccessToken);
                        this.b.onSuccess(parseAccessToken);
                        return;
                    }
                    return;
                } else if ("access_denied".equals(safeString) || "OAuthAccessDeniedException".equals(safeString)) {
                    LogUtil.d(WBAgent.TAG, "Login canceled by user.");
                    this.b.cancel();
                    return;
                } else {
                    LogUtil.d(WBAgent.TAG, "Login failed: " + safeString);
                    this.b.onFailure(new WbConnectErrorMessage(safeString2, safeString3));
                    return;
                }
            }
            this.b.onFailure(new WbConnectErrorMessage(WbAuthConstants.AUTH_FAILED_INSTALL_APP_COUNTERFEIT_MESSAGE, WbAuthConstants.AUTH_FAILED_INSTALL_APP_COUNTERFEIT_CODE));
        } else if (i2 != 0) {
        } else {
            if (intent != null) {
                this.b.cancel();
            } else {
                this.b.cancel();
            }
        }
    }

    protected boolean c() {
        WbAppInfo wbAppInfo = WeiboAppManager.getInstance(this.a).getWbAppInfo();
        return wbAppInfo != null && wbAppInfo.isLegal();
    }

    protected void d() {
    }
}
