package com.ali.auth.third.login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.ali.auth.third.core.config.AuthOption;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.cookies.LoginCookieUtils;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.model.HistoryAccount;
import com.ali.auth.third.core.model.LoginReturnData;
import com.ali.auth.third.core.model.RpcRequest;
import com.ali.auth.third.core.model.RpcResponse;
import com.ali.auth.third.core.rpc.protocol.RpcException;
import com.ali.auth.third.core.service.RpcService;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.service.UserTrackerService;
import com.ali.auth.third.core.service.impl.CredentialManager;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.CommonUtils;
import com.ali.auth.third.core.util.JSONUtils;
import com.ali.auth.third.core.util.RSAKey;
import com.ali.auth.third.core.util.ResourceUtils;
import com.ali.auth.third.core.util.Rsa;
import com.ali.auth.third.core.util.SystemUtils;
import com.ali.auth.third.login.context.LoginContext;
import com.ali.auth.third.ui.LoginWebViewActivity;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.alipay.sdk.cons.c;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginComponent {
    public static final LoginComponent INSTANCE = new LoginComponent();
    private static final String OAUTH_API = "taobao.oauth.code.create";
    private static final String TAG = "login";

    private LoginComponent() {
    }

    public RpcResponse<LoginReturnData> loginByUserName(String str) {
        RpcRequest rpcRequest = new RpcRequest();
        try {
            Object optString;
            JSONObject jSONObject = new JSONObject(str);
            CharSequence optString2 = JSONUtils.optString(jSONObject, "loginid");
            if (TextUtils.isEmpty(optString2)) {
                optString = JSONUtils.optString(jSONObject, "loginId");
            } else {
                CharSequence charSequence = optString2;
            }
            rpcRequest.target = "com.taobao.mtop.mloginService.login";
            rpcRequest.version = "1.0";
            JSONObject jSONObject2 = new JSONObject();
            if (KernelContext.isMini) {
                jSONObject2.put("app_id", KernelContext.getApplicationContext().getPackageName() + "|" + SystemUtils.getApkPublicKeyDigest());
            } else {
                jSONObject2.put("utdid", ((RpcService) KernelContext.getService(RpcService.class)).getDeviceId());
            }
            jSONObject2.put("appName", KernelContext.getAppKey());
            jSONObject2.put("loginId", optString);
            jSONObject2.put("clientIp", CommonUtils.getLocalIPAddress());
            long currentTimeMillis = System.currentTimeMillis();
            if (!TextUtils.isEmpty(optString)) {
                HistoryAccount matchHistoryAccount = ((StorageService) KernelContext.getService(StorageService.class)).matchHistoryAccount(optString);
                if (matchHistoryAccount != null) {
                    Object obj = matchHistoryAccount.tokenKey;
                    if (!TextUtils.isEmpty(obj)) {
                        Object treeMap = new TreeMap();
                        addKey(treeMap, LoginConstants.KEY_APPKEY, KernelContext.getAppKey());
                        addKey(treeMap, LoginConstants.KEY_HAVANAID, matchHistoryAccount.userId);
                        addKey(treeMap, LoginConstants.KEY_TIMESTAMP, String.valueOf(currentTimeMillis));
                        addKey(treeMap, LoginConstants.KEY_APPVERSION, CommonUtils.getAndroidAppVersion());
                        addKey(treeMap, LoginConstants.KEY_SDKVERSION, KernelContext.sdkVersion);
                        optString2 = ((StorageService) KernelContext.getService(StorageService.class)).signMap(obj, treeMap);
                        if (!TextUtils.isEmpty(optString2)) {
                            jSONObject2.put("deviceTokenSign", optString2);
                            jSONObject2.put("deviceTokenKey", obj);
                            jSONObject2.put("hid", matchHistoryAccount.userId);
                        }
                    }
                }
            }
            jSONObject2.put("password", Rsa.encrypt(JSONUtils.optString(jSONObject, "password"), RSAKey.getRsaPubkey()));
            jSONObject2.put("pwdEncrypted", true);
            jSONObject2.put("appVersion", CommonUtils.getAndroidAppVersion());
            jSONObject2.put("sdkVersion", KernelContext.sdkVersion);
            jSONObject2.put("t", currentTimeMillis + "");
            jSONObject2.put("ccId", JSONUtils.optString(jSONObject, "checkCodeId"));
            jSONObject2.put("checkCode", JSONUtils.optString(jSONObject, "checkCode"));
            try {
                JSONObject keyValues = LoginCookieUtils.getKeyValues("alimm_");
                keyValues.put("miid", LoginCookieUtils.getValue("miid"));
                jSONObject2.put("ext", keyValues);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rpcRequest.addParam("loginInfo", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            if (KernelContext.isMini) {
                String optString3 = JSONUtils.optString(jSONObject, Constants.UMID);
                jSONObject3.put("umidToken", optString3);
                ((StorageService) KernelContext.getService(StorageService.class)).setUmid(optString3);
            } else {
                jSONObject3.put("umidToken", ((StorageService) KernelContext.getService(StorageService.class)).getUmid());
            }
            jSONObject3.put(Constants.UA, JSONUtils.optString(jSONObject, Constants.UA));
            rpcRequest.addParam("riskControlInfo", jSONObject3);
            rpcRequest.addParam("ext", new JSONObject());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return ((RpcService) KernelContext.getService(RpcService.class)).invoke(rpcRequest, LoginReturnData.class);
    }

    public void showLogin(Activity activity) {
        SDKLogger.d("login", "showLogin");
        if (KernelContext.authOption == AuthOption.H5ONLY) {
            showH5Login(activity);
        } else {
            new LoginComponent$1(this, SystemUtils.getApkSignNumber(), activity).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        }
    }

    public String generateTopAppLinkToken(String str) {
        ((UserTrackerService) KernelContext.getService(UserTrackerService.class)).send(UTConstants.E_GENERATE_TAOBAO_SIGN, null);
        TreeMap treeMap = new TreeMap();
        treeMap.put("appKey", KernelContext.getAppKey());
        treeMap.put("apkSign", str);
        treeMap.put(c.n, OAUTH_API);
        String params2Str = params2Str(treeMap);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.target = "com.alibaba.havana.login.applink.generateTopAppLinkToken";
        rpcRequest.version = "1.0";
        String appKey = KernelContext.getAppKey();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appName", appKey);
            jSONObject.put("t", "" + System.currentTimeMillis());
            jSONObject.put("clientIp", CommonUtils.getLocalIPAddress());
            if (KernelContext.isMini) {
                jSONObject.put("app_id", KernelContext.getApplicationContext().getPackageName() + "|" + SystemUtils.getApkPublicKeyDigest());
            }
            jSONObject.put("sdkVersion", KernelContext.sdkVersion);
            rpcRequest.addParam("baseInfo", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rpcRequest.addParam("content", params2Str);
        try {
            RpcResponse invoke = ((RpcService) KernelContext.getService(RpcService.class)).invoke(rpcRequest, String.class);
            if (invoke != null) {
                return (String) invoke.returnValue;
            }
            return null;
        } catch (RpcException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void showH5Login(Activity activity) {
        SDKLogger.d("login", "open H5 login");
        Intent intent = new Intent(activity, LoginWebViewActivity.class);
        intent.putExtra("url", ConfigManager.LOGIN_HOST);
        intent.putExtra("title", ResourceUtils.getString(activity.getApplicationContext(), "com_taobao_tae_sdk_authorize_title"));
        activity.startActivityForResult(intent, RequestCode.OPEN_H5_LOGIN);
    }

    private String params2Str(TreeMap<String, String> treeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : treeMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                stringBuilder.append(str).append(str2);
            }
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ali.auth.third.core.model.RpcResponse<com.ali.auth.third.core.model.LoginReturnData> loginByCode(java.lang.String r9) {
        /*
        r8 = this;
        r1 = 0;
        r0 = com.ali.auth.third.core.service.UserTrackerService.class;
        r0 = com.ali.auth.third.core.context.KernelContext.getService(r0);	 Catch:{ Exception -> 0x00ee }
        r0 = (com.ali.auth.third.core.service.UserTrackerService) r0;	 Catch:{ Exception -> 0x00ee }
        r2 = "TOP_TOKEN_LOGIN";
        r3 = 0;
        r0.send(r2, r3);	 Catch:{ Exception -> 0x00ee }
        r2 = new com.ali.auth.third.core.model.RpcRequest;	 Catch:{ Exception -> 0x00ee }
        r2.<init>();	 Catch:{ Exception -> 0x00ee }
        r0 = "com.taobao.mtop.mloginService.topTokenLogin";
        r2.target = r0;	 Catch:{ Exception -> 0x00ee }
        r0 = "1.0";
        r2.version = r0;	 Catch:{ Exception -> 0x00ee }
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00e9 }
        r3.<init>();	 Catch:{ JSONException -> 0x00e9 }
        r0 = com.ali.auth.third.core.context.KernelContext.isMini;	 Catch:{ JSONException -> 0x00e9 }
        if (r0 == 0) goto L_0x00d6;
    L_0x0025:
        r0 = "app_id";
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00e9 }
        r4.<init>();	 Catch:{ JSONException -> 0x00e9 }
        r5 = com.ali.auth.third.core.context.KernelContext.getApplicationContext();	 Catch:{ JSONException -> 0x00e9 }
        r5 = r5.getPackageName();	 Catch:{ JSONException -> 0x00e9 }
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x00e9 }
        r5 = "|";
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x00e9 }
        r5 = com.ali.auth.third.core.util.SystemUtils.getApkPublicKeyDigest();	 Catch:{ JSONException -> 0x00e9 }
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x00e9 }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00e9 }
    L_0x004d:
        r0 = "appName";
        r4 = com.ali.auth.third.core.context.KernelContext.getAppKey();	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "token";
        r3.put(r0, r9);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "t";
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00e9 }
        r4.<init>();	 Catch:{ JSONException -> 0x00e9 }
        r5 = "";
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x00e9 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x00e9 }
        r4 = r4.append(r6);	 Catch:{ JSONException -> 0x00e9 }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "sdkVersion";
        r4 = com.ali.auth.third.core.context.KernelContext.sdkVersion;	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "clientIp";
        r4 = com.ali.auth.third.core.util.CommonUtils.getLocalIPAddress();	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r0, r4);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "alimm_";
        r0 = com.ali.auth.third.core.cookies.LoginCookieUtils.getKeyValues(r0);	 Catch:{ Exception -> 0x00f4, JSONException -> 0x00e9 }
        r4 = "miid";
        r5 = "miid";
        r5 = com.ali.auth.third.core.cookies.LoginCookieUtils.getValue(r5);	 Catch:{ Exception -> 0x00f4, JSONException -> 0x00e9 }
        r0.put(r4, r5);	 Catch:{ Exception -> 0x00f4, JSONException -> 0x00e9 }
        r4 = "ext";
        r3.put(r4, r0);	 Catch:{ Exception -> 0x00f4, JSONException -> 0x00e9 }
    L_0x009d:
        r0 = "tokenInfo";
        r2.addParam(r0, r3);	 Catch:{ JSONException -> 0x00e9 }
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00e9 }
        r3.<init>();	 Catch:{ JSONException -> 0x00e9 }
        r4 = "umidToken";
        r0 = com.ali.auth.third.core.service.StorageService.class;
        r0 = com.ali.auth.third.core.context.KernelContext.getService(r0);	 Catch:{ JSONException -> 0x00e9 }
        r0 = (com.ali.auth.third.core.service.StorageService) r0;	 Catch:{ JSONException -> 0x00e9 }
        r0 = r0.getUmid();	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r4, r0);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "riskControlInfo";
        r2.addParam(r0, r3);	 Catch:{ JSONException -> 0x00e9 }
        r0 = "ext";
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00e9 }
        r3.<init>();	 Catch:{ JSONException -> 0x00e9 }
        r2.addParam(r0, r3);	 Catch:{ JSONException -> 0x00e9 }
    L_0x00c7:
        r0 = com.ali.auth.third.core.service.RpcService.class;
        r0 = com.ali.auth.third.core.context.KernelContext.getService(r0);	 Catch:{ Exception -> 0x00ee }
        r0 = (com.ali.auth.third.core.service.RpcService) r0;	 Catch:{ Exception -> 0x00ee }
        r3 = com.ali.auth.third.core.model.LoginReturnData.class;
        r0 = r0.invoke(r2, r3);	 Catch:{ Exception -> 0x00ee }
    L_0x00d5:
        return r0;
    L_0x00d6:
        r4 = "utdid";
        r0 = com.ali.auth.third.core.service.RpcService.class;
        r0 = com.ali.auth.third.core.context.KernelContext.getService(r0);	 Catch:{ JSONException -> 0x00e9 }
        r0 = (com.ali.auth.third.core.service.RpcService) r0;	 Catch:{ JSONException -> 0x00e9 }
        r0 = r0.getDeviceId();	 Catch:{ JSONException -> 0x00e9 }
        r3.put(r4, r0);	 Catch:{ JSONException -> 0x00e9 }
        goto L_0x004d;
    L_0x00e9:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x00ee }
        goto L_0x00c7;
    L_0x00ee:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r1;
        goto L_0x00d5;
    L_0x00f4:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ JSONException -> 0x00e9 }
        goto L_0x009d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ali.auth.third.login.LoginComponent.loginByCode(java.lang.String):com.ali.auth.third.core.model.RpcResponse<com.ali.auth.third.core.model.LoginReturnData>");
    }

    public RpcResponse<LoginReturnData> loginByIVToken(String str, String str2, String str3) {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.target = "com.taobao.mtop.mloginService.mloginTokenLogin";
        rpcRequest.version = "1.0";
        try {
            JSONObject jSONObject = new JSONObject();
            if (KernelContext.isMini) {
                jSONObject.put("app_id", KernelContext.getApplicationContext().getPackageName() + "|" + SystemUtils.getApkPublicKeyDigest());
            } else {
                jSONObject.put("utdid", ((RpcService) KernelContext.getService(RpcService.class)).getDeviceId());
            }
            jSONObject.put("appName", KernelContext.getAppKey());
            jSONObject.put(INoCaptchaComponent.token, str);
            jSONObject.put("t", "" + System.currentTimeMillis());
            jSONObject.put("scene", str2);
            jSONObject.put("sdkVersion", KernelContext.sdkVersion);
            jSONObject.put("clientIp", CommonUtils.getLocalIPAddress());
            try {
                JSONObject keyValues = LoginCookieUtils.getKeyValues("alimm_");
                keyValues.put("miid", LoginCookieUtils.getValue("miid"));
                keyValues.put("aliusersdk_h5querystring", str3.toString());
                jSONObject.put("ext", keyValues);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rpcRequest.addParam("tokenInfo", jSONObject);
            jSONObject = new JSONObject();
            jSONObject.put("umidToken", ((StorageService) KernelContext.getService(StorageService.class)).getUmid());
            rpcRequest.addParam("riskControlInfo", jSONObject);
            rpcRequest.addParam("ext", JSONUtils.toJsonObject(new HashMap()));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return LoginContext.rpcService.invoke(rpcRequest, LoginReturnData.class);
    }

    public static RpcResponse<LoginReturnData> loginByRefreshToken() {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.target = "com.taobao.mtop.mLoginUnitService.autoLogin";
        rpcRequest.version = "1.0";
        try {
            Object obj = CredentialManager.INSTANCE.getInternalSession().user.userId;
            rpcRequest.addParam(UserTrackerConstants.USERID, Long.valueOf(Long.parseLong(obj)));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appName", KernelContext.getAppKey());
            jSONObject.put(INoCaptchaComponent.token, CredentialManager.INSTANCE.getInternalSession().autoLoginToken);
            jSONObject.put("sdkVersion", KernelContext.sdkVersion);
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("t", "" + currentTimeMillis);
            jSONObject.put("clientIp", CommonUtils.getLocalIPAddress());
            if (KernelContext.isMini) {
                jSONObject.put("app_id", KernelContext.getApplicationContext().getPackageName() + "|" + SystemUtils.getApkPublicKeyDigest());
            } else {
                jSONObject.put("utdid", ((RpcService) KernelContext.getService(RpcService.class)).getDeviceId());
            }
            if (!TextUtils.isEmpty(obj)) {
                HistoryAccount findHistoryAccount = ((StorageService) KernelContext.getService(StorageService.class)).findHistoryAccount(obj);
                if (findHistoryAccount != null) {
                    Object obj2 = findHistoryAccount.tokenKey;
                    if (!TextUtils.isEmpty(obj2)) {
                        Object treeMap = new TreeMap();
                        addKey(treeMap, LoginConstants.KEY_APPKEY, KernelContext.getAppKey());
                        addKey(treeMap, LoginConstants.KEY_HAVANAID, findHistoryAccount.userId);
                        addKey(treeMap, LoginConstants.KEY_TIMESTAMP, String.valueOf(currentTimeMillis));
                        addKey(treeMap, LoginConstants.KEY_APPVERSION, CommonUtils.getAndroidAppVersion());
                        addKey(treeMap, LoginConstants.KEY_SDKVERSION, KernelContext.sdkVersion);
                        CharSequence signMap = ((StorageService) KernelContext.getService(StorageService.class)).signMap(obj2, treeMap);
                        if (!TextUtils.isEmpty(signMap)) {
                            jSONObject.put("deviceTokenSign", signMap);
                            jSONObject.put("deviceTokenKey", obj2);
                            jSONObject.put("hid", findHistoryAccount.userId);
                        }
                    }
                }
            }
            try {
                JSONObject keyValues = LoginCookieUtils.getKeyValues("alimm_");
                keyValues.put("miid", LoginCookieUtils.getValue("miid"));
                jSONObject.put("ext", keyValues);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            rpcRequest.addParam("tokenInfo", jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("umidToken", ((StorageService) KernelContext.getService(StorageService.class)).getUmid());
            rpcRequest.addParam("riskControlInfo", jSONObject2);
            rpcRequest.addParam("ext", new JSONObject());
        } catch (JSONException e22) {
            e22.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return ((RpcService) KernelContext.getService(RpcService.class)).invoke(rpcRequest, LoginReturnData.class);
    }

    public static void addKey(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    public static RpcResponse<String> logout() {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.target = "mtop.taobao.havana.mlogin.logout";
        rpcRequest.version = "1.0";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appKey", KernelContext.getAppKey());
            jSONObject.put("sid", CredentialManager.INSTANCE.getInternalSession().sid);
            jSONObject.put("ip", CommonUtils.getLocalIPAddress());
            rpcRequest.addParam(UserTrackerConstants.USERID, Long.valueOf(Long.parseLong(CredentialManager.INSTANCE.getInternalSession().user.userId)));
            rpcRequest.addParam("request", jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ((RpcService) KernelContext.getService(RpcService.class)).invoke(rpcRequest, String.class);
    }
}
