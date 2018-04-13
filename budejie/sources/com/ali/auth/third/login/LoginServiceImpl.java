package com.ali.auth.third.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.ali.auth.third.core.WebViewProxy;
import com.ali.auth.third.core.broadcast.LoginAction;
import com.ali.auth.third.core.callback.LoginCallback;
import com.ali.auth.third.core.callback.RefreshCookieCallback;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.cookies.CookieManagerWrapper;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.model.Session;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.ali.auth.third.core.service.UserTrackerService;
import com.ali.auth.third.core.service.impl.CredentialManager;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.ResourceUtils;
import com.ali.auth.third.login.callback.LogoutCallback;
import com.ali.auth.third.login.context.LoginContext;
import com.ali.auth.third.login.task.LoginByReTokenTask;
import com.ali.auth.third.login.task.LogoutTask;
import com.ali.auth.third.login.util.LoginStatus;
import com.ali.auth.third.ui.LoginActivity;
import com.ali.auth.third.ui.context.CallbackContext;
import com.alipay.sdk.app.statistic.c;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginServiceImpl implements LoginService {
    private static final String LOG_TAG = "LoginServiceImpl";
    private transient Pattern[] mLoginPatterns;
    private transient Pattern[] mLogoutPatterns;

    class RefreshTask extends AsyncTask<Object, Void, Void> {
        RefreshCookieCallback callback;

        RefreshTask(RefreshCookieCallback refreshCookieCallback) {
            this.callback = refreshCookieCallback;
        }

        protected Void doInBackground(Object... objArr) {
            CookieManagerWrapper.INSTANCE.refreshCookie();
            return null;
        }

        protected void onPostExecute(Void voidR) {
            this.callback.onSuccess();
        }
    }

    public boolean isLoginUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length;
        int i;
        if (this.mLoginPatterns == null && !TextUtils.isEmpty(ConfigManager.LOGIN_URLS)) {
            String[] split = ConfigManager.LOGIN_URLS.split("[,]");
            this.mLoginPatterns = new Pattern[split.length];
            length = this.mLoginPatterns.length;
            for (i = 0; i < length; i++) {
                this.mLoginPatterns[i] = Pattern.compile(split[i]);
            }
        }
        for (Pattern matcher : this.mLoginPatterns) {
            if (matcher.matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    public boolean isLogoutUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length;
        int i;
        if (this.mLogoutPatterns == null && !TextUtils.isEmpty(ConfigManager.LOGOUT_URLS)) {
            String[] split = ConfigManager.LOGOUT_URLS.split("[,]");
            this.mLogoutPatterns = new Pattern[split.length];
            length = this.mLogoutPatterns.length;
            for (i = 0; i < length; i++) {
                this.mLogoutPatterns[i] = Pattern.compile(split[i]);
            }
        }
        for (Pattern matcher : this.mLogoutPatterns) {
            if (matcher.matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    public void logout(Activity activity, LogoutCallback logoutCallback) {
        ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send("logout".toUpperCase(), null);
        new LogoutTask(activity, logoutCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void auth(Activity activity, LoginCallback loginCallback) {
        SDKLogger.d("login", "auth start");
        auth(loginCallback);
    }

    public void auth(final LoginCallback loginCallback) {
        if (KernelContext.checkServiceValid()) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("withNoActivity", Constants.SERVICE_SCOPE_FLAG_VALUE);
                ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(c.d.toUpperCase(), hashMap);
            } catch (Exception e) {
            }
            if (!LoginStatus.compareAndSetLogining(false, true)) {
                return;
            }
            if (!CommonUtils.isNetworkAvailable()) {
                SDKLogger.d("login", "auth network not available");
                loginCallback.onFailure(SystemMessageConstants.NET_WORK_ERROR, ResourceUtils.getString("com_taobao_tae_sdk_network_not_available_message"));
                LoginStatus.resetLoginFlag();
                return;
            } else if (TextUtils.isEmpty(CredentialManager.INSTANCE.getInternalSession().autoLoginToken) || CredentialManager.INSTANCE.getInternalSession().user == null || TextUtils.isEmpty(CredentialManager.INSTANCE.getInternalSession().user.userId)) {
                goLogin(loginCallback);
                return;
            } else {
                SDKLogger.d("login", "auth auto login");
                new LoginByReTokenTask(null, new LoginCallback() {
                    public void onSuccess(Session session) {
                        SDKLogger.d("login", "auth auto login success");
                        if (loginCallback != null) {
                            loginCallback.onSuccess(LoginServiceImpl.this.getSession());
                        }
                        if (CallbackContext.mGlobalLoginCallback != null) {
                            CallbackContext.mGlobalLoginCallback.onSuccess(LoginServiceImpl.this.getSession());
                        }
                        CommonUtils.sendBroadcast(LoginAction.NOTIFY_LOGIN_SUCCESS);
                    }

                    public void onFailure(int i, String str) {
                        SDKLogger.d("login", "auth auto login success");
                        LoginServiceImpl.this.goLogin(loginCallback);
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                return;
            }
        }
        SDKLogger.d("login", "auth static field is null");
        if (loginCallback != null) {
            loginCallback.onFailure(SystemMessageConstants.NPE_ERROR, "NullPointException");
        }
    }

    public void logout(LogoutCallback logoutCallback) {
        try {
            Map hashMap = new HashMap();
            hashMap.put("withNoActivity", Constants.SERVICE_SCOPE_FLAG_VALUE);
            ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send("logout".toUpperCase(), hashMap);
        } catch (Exception e) {
        }
        logout(null, logoutCallback);
    }

    public void goLogin(LoginCallback loginCallback) {
        SDKLogger.d("login", "auth goLogin");
        CallbackContext.loginCallback = loginCallback;
        Intent intent = new Intent();
        intent.setClass(KernelContext.getApplicationContext(), LoginActivity.class);
        intent.setFlags(268435456);
        KernelContext.getApplicationContext().startActivity(intent);
    }

    private void showLogin(final Activity activity, final LoginCallback loginCallback) {
        CommonUtils.startInitWaitTask(activity, loginCallback, new Runnable() {
            public void run() {
                try {
                    CallbackContext.loginCallback = loginCallback;
                    CallbackContext.setActivity(activity);
                    LoginComponent.INSTANCE.showLogin(activity);
                } catch (Throwable th) {
                    LoginStatus.resetLoginFlag();
                }
            }
        }, "");
    }

    public Session getSession() {
        return LoginContext.credentialService.getSession();
    }

    public boolean checkSessionValid() {
        return LoginContext.credentialService.isSessionValid();
    }

    public void refreshCookie(RefreshCookieCallback refreshCookieCallback) {
        new RefreshTask(refreshCookieCallback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        CallbackContext.mGlobalLoginCallback = loginCallback;
    }

    public void setWebViewProxy(WebViewProxy webViewProxy) {
        KernelContext.mWebViewProxy = webViewProxy;
    }
}
