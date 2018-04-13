package com.ali.auth.third.core.service.impl;

import android.text.TextUtils;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.cookies.CookieManagerWrapper;
import com.ali.auth.third.core.model.HistoryAccount;
import com.ali.auth.third.core.model.InternalSession;
import com.ali.auth.third.core.model.LoginDataModel;
import com.ali.auth.third.core.model.LoginReturnData;
import com.ali.auth.third.core.model.ResultCode;
import com.ali.auth.third.core.model.Session;
import com.ali.auth.third.core.model.User;
import com.ali.auth.third.core.registry.ServiceRegistry;
import com.ali.auth.third.core.service.CredentialService;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.JSONUtils;
import com.ali.auth.third.core.util.ReflectionUtils;
import com.ali.auth.third.core.util.SystemUtils;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.net.URLDecoder;
import java.util.ArrayList;
import org.json.JSONObject;

public class CredentialManager implements CredentialService {
    public static final CredentialManager INSTANCE = new CredentialManager();
    private static final String TAG = CredentialManager.class.getSimpleName();
    private volatile InternalSession internalSession;
    public String internalSessionStoreKey = "internal_session";

    public CredentialManager() {
        preInit();
    }

    private void preInit() {
        if (KernelContext.storageService == null) {
            registerStorage(KernelContext.serviceRegistry);
        }
        String value = KernelContext.storageService.getValue("loginEnvironmentIndex", true);
        String valueOf = String.valueOf(KernelContext.getEnvironment().ordinal());
        if (value == null || value.equals(valueOf)) {
            Object value2 = KernelContext.storageService.getValue(this.internalSessionStoreKey, true);
            if (TextUtils.isEmpty(value2)) {
                value2 = KernelContext.storageService.getValue(this.internalSessionStoreKey, true);
            }
            if (TextUtils.isEmpty(value2)) {
                this.internalSession = new InternalSession();
                this.internalSession.user = new User();
                return;
            }
            this.internalSession = createInternalSession(value2);
            return;
        }
        KernelContext.storageService.putValue("loginEnvironmentIndex", valueOf, true);
        KernelContext.storageService.removeValue(this.internalSessionStoreKey, true);
        this.internalSession = new InternalSession();
        this.internalSession.user = new User();
    }

    public boolean isSessionValid() {
        if (this.internalSession == null || this.internalSession.loginTime == 0 || this.internalSession.expireIn == 0 || System.currentTimeMillis() / 1000 >= this.internalSession.expireIn) {
            return false;
        }
        return true;
    }

    public InternalSession createInternalSession(String str) {
        InternalSession internalSession = new InternalSession();
        try {
            JSONObject jSONObject = new JSONObject(str);
            internalSession.sid = JSONUtils.optString(jSONObject, "sid");
            internalSession.expireIn = (long) JSONUtils.optInteger(jSONObject, "expireIn").intValue();
            User user = new User();
            JSONObject optJSONObject = jSONObject.optJSONObject("user");
            if (optJSONObject != null) {
                user.avatarUrl = optJSONObject.optString("avatarUrl");
                user.userId = optJSONObject.optString(UserTrackerConstants.USERID);
                user.nick = optJSONObject.optString("nick");
                user.openId = optJSONObject.optString("openId");
                user.openSid = optJSONObject.optString("openSid");
                user.deviceTokenKey = optJSONObject.optString("deviceTokenKey");
                user.deviceTokenSalt = optJSONObject.optString("deviceTokenSalt");
                if (!(TextUtils.isEmpty(internalSession.sid) || TextUtils.isEmpty(user.userId))) {
                    ((RpcService) KernelContext.getService(RpcService.class)).registerSessionInfo(internalSession.sid, user.userId);
                }
            }
            internalSession.user = user;
            internalSession.loginTime = JSONUtils.optLong(jSONObject, "loginTime").longValue();
            internalSession.mobile = JSONUtils.optString(jSONObject, "mobile");
            internalSession.loginId = JSONUtils.optString(jSONObject, "loginId");
            internalSession.autoLoginToken = JSONUtils.optString(jSONObject, "autoLoginToken");
            internalSession.otherInfo = JSONUtils.toMap(jSONObject.optJSONObject("otherInfo"));
        } catch (Throwable e) {
            SDKLogger.e(TAG, e.getMessage(), e);
        }
        return internalSession;
    }

    public InternalSession getInternalSession() {
        return this.internalSession;
    }

    private void refreshInternalSession(InternalSession internalSession) {
        this.internalSession = internalSession;
        KernelContext.storageService.putValue(this.internalSessionStoreKey, SystemUtils.toInternalSessionJSON(internalSession), true);
    }

    public void refreshWhenLogin(LoginReturnData loginReturnData) {
        if (loginReturnData != null && !TextUtils.isEmpty(loginReturnData.data)) {
            InternalSession internalSession = new InternalSession();
            LoginDataModel loginDataModel = (LoginDataModel) JSONUtils.toPOJO(new JSONObject(loginReturnData.data), LoginDataModel.class);
            internalSession.externalCookies = loginDataModel.externalCookies;
            User user = new User();
            user.userId = loginDataModel.userId;
            if (loginDataModel.nick != null) {
                try {
                    user.nick = URLDecoder.decode(loginDataModel.nick, "UTF-8");
                } catch (Throwable e) {
                    SDKLogger.e(TAG, e.getMessage(), e);
                }
            }
            try {
                String[] strArr;
                user.openId = loginDataModel.openId;
                user.openSid = loginDataModel.openSid;
                user.avatarUrl = loginDataModel.headPicLink;
                if (loginReturnData.deviceToken != null) {
                    user.deviceTokenSalt = loginReturnData.deviceToken.salt;
                    user.deviceTokenKey = loginReturnData.deviceToken.key;
                }
                if (!(TextUtils.isEmpty(user.deviceTokenKey) || TextUtils.isEmpty(user.deviceTokenSalt))) {
                    ((StorageService) KernelContext.getService(StorageService.class)).putLoginHistory(new HistoryAccount(loginDataModel.userId, user.deviceTokenKey, loginDataModel.nick, loginDataModel.phone, loginDataModel.email), user.deviceTokenSalt);
                }
                internalSession.user = user;
                internalSession.loginTime = loginDataModel.loginTime;
                internalSession.sid = loginDataModel.sid;
                internalSession.expireIn = adjustSessionExpireTime(loginDataModel.expires, loginDataModel.loginTime);
                internalSession.mobile = loginDataModel.phone;
                internalSession.loginId = loginReturnData.showLoginId;
                internalSession.autoLoginToken = loginDataModel.autoLoginToken;
                internalSession.otherInfo = loginDataModel.extendAttribute;
                ((RpcService) KernelContext.getService(RpcService.class)).registerSessionInfo(loginDataModel.sid, loginDataModel.userId);
                try {
                    Object obj = loginDataModel.extendAttribute.get("ssoDomainList");
                    if (obj != null && (obj instanceof ArrayList)) {
                        strArr = (String[]) ((ArrayList) obj).toArray(new String[0]);
                        CookieManagerWrapper.INSTANCE.injectCookie(loginDataModel.cookies, strArr);
                        SDKLogger.e("session", "session = " + internalSession.toString());
                        refreshInternalSession(internalSession);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                strArr = null;
                CookieManagerWrapper.INSTANCE.injectCookie(loginDataModel.cookies, strArr);
            } catch (Exception e3) {
            }
            SDKLogger.e("session", "session = " + internalSession.toString());
            refreshInternalSession(internalSession);
        }
    }

    public long adjustSessionExpireTime(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis <= j2) {
            return j;
        }
        if (j > 0) {
            return j + (currentTimeMillis - j2);
        }
        return currentTimeMillis + TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
    }

    public Session getSession() {
        Session session = new Session();
        String str = (this.internalSession == null || this.internalSession.user == null) ? "" : this.internalSession.user.nick;
        session.nick = str;
        str = (this.internalSession == null || this.internalSession.user == null) ? "" : this.internalSession.user.avatarUrl;
        session.avatarUrl = str;
        str = (this.internalSession == null || this.internalSession.user == null) ? "" : this.internalSession.user.openId;
        session.openId = str;
        str = (this.internalSession == null || this.internalSession.user == null) ? "" : this.internalSession.user.openSid;
        session.openSid = str;
        return session;
    }

    public ResultCode logout() {
        CookieManagerWrapper.INSTANCE.clearCookies();
        InternalSession internalSession = new InternalSession();
        internalSession.user = new User();
        refreshInternalSession(internalSession);
        return ResultCode.SUCCESS;
    }

    private void registerStorage(ServiceRegistry serviceRegistry) {
        Object obj = 1;
        try {
            Class.forName("com.ali.auth.third.securityguard.SecurityGuardWrapper");
            try {
                KernelContext.isMini = false;
                KernelContext.sdkVersion = KernelContext.SDK_VERSION_STD;
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            obj = null;
        }
        if (obj != null) {
            try {
                obj = getServiceInstance("com.ali.auth.third.securityguard.SecurityGuardWrapper", null, null);
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
                return;
            }
        }
        obj = getServiceInstance("com.ali.auth.third.core.storage.CommonStorageServiceImpl", null, null);
        serviceRegistry.registerService(new Class[]{StorageService.class}, obj, null);
        KernelContext.storageService = (StorageService) serviceRegistry.getService(StorageService.class, null);
    }

    private Object getServiceInstance(String str, String[] strArr, Object[] objArr) {
        try {
            return ReflectionUtils.newInstance(str, strArr, objArr);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
            return null;
        }
    }
}
