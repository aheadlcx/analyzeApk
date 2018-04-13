package com.ali.auth.third.securityguard;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.ali.auth.third.core.config.ConfigManager;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.exception.SecRuntimeException;
import com.ali.auth.third.core.model.HistoryAccount;
import com.ali.auth.third.core.model.LoginHistory;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.alibaba.wireless.security.open.safetoken.ISafeTokenComponent;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecurityGuardWrapper implements StorageService {
    private static final String HISTORY_LOGIN_ACCOUNTS = "taesdk_history_acounts";
    private static final String SEED_KEY = "seed_key";
    public static final String TAG = "auth.SecurityGuardWrapper";

    private SecurityGuardManager getSecurityGuardManager() {
        try {
            return SecurityGuardManager.getInstance(KernelContext.context);
        } catch (Throwable e) {
            throw new SecRuntimeException(e.getErrorCode(), e);
        }
    }

    public String getValue(String str, boolean z) {
        if (!z) {
            return getSecurityGuardManager().getStaticDataStoreComp().getExtraData(str, ConfigManager.POSTFIX_OF_SECURITY_JPG);
        }
        try {
            return getSecurityGuardManager().getDynamicDataStoreComp().getString(str);
        } catch (SecException e) {
            return null;
        }
    }

    public void putValue(String str, String str2, boolean z) {
        try {
            getSecurityGuardManager().getDynamicDataStoreComp().putString(str, str2);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public void removeValue(String str, boolean z) {
        if (z) {
            try {
                getSecurityGuardManager().getDynamicDataStoreComp().removeString(str);
            } catch (SecException e) {
                e.printStackTrace();
            }
        }
    }

    public String symEncrypt(String str, String str2) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            if (TextUtils.isEmpty(str2)) {
                str2 = SEED_KEY;
            }
            try {
                return Base64.encodeToString(_symEncrypt(bytes, str2), 11);
            } catch (SecRuntimeException e) {
                throw e;
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException();
        }
    }

    private byte[] _symEncrypt(byte[] bArr, String str) {
        try {
            saveThreadSeedKey(str);
            return getSecurityGuardManager().getStaticKeyEncryptComp().encrypt(16, str, bArr);
        } catch (Throwable e) {
            throw new SecRuntimeException(e.getErrorCode(), e);
        }
    }

    private void saveThreadSeedKey(String str) {
    }

    public String symDecrypt(String str, String str2) {
        try {
            byte[] decode = Base64.decode(str, 8);
            if (TextUtils.isEmpty(str2)) {
                str2 = SEED_KEY;
            }
            return new String(_symDecrypt(decode, str2), "UTF-8");
        } catch (SecRuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    private byte[] _symDecrypt(byte[] bArr, String str) {
        try {
            return getSecurityGuardManager().getStaticKeyEncryptComp().decrypt(16, str, bArr);
        } catch (Throwable e) {
            throw new SecRuntimeException(e.getErrorCode(), e);
        }
    }

    public byte[] getByteArray(String str) {
        try {
            return getSecurityGuardManager().getDynamicDataStoreComp().getByteArray(str);
        } catch (SecException e) {
            return null;
        }
    }

    public void savePublicKey(byte[] bArr) {
        if (getProviderName().equals("lite")) {
            try {
                Method declaredMethod = getSecurityGuardManager().getClass().getDeclaredMethod("saveCertPublicKey", new Class[]{byte[].class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(getSecurityGuardManager(), new Object[]{bArr});
            } catch (Throwable e) {
                SDKLogger.e(TAG, "Fail to invoke the saveCertPublicKey, the error message is " + e.getMessage(), e);
            }
        }
    }

    public String getUmid() {
        try {
            return getSecurityGuardManager().getUMIDComp().getSecurityToken();
        } catch (Throwable e) {
            throw new SecRuntimeException(e.getErrorCode(), e);
        }
    }

    public void setUmid(String str) {
    }

    public String getAppKey() {
        try {
            return getSecurityGuardManager().getStaticDataStoreComp().getAppKeyByIndex(ConfigManager.getAppKeyIndex(), ConfigManager.POSTFIX_OF_SECURITY_JPG);
        } catch (Throwable e) {
            throw new SecRuntimeException(e.getErrorCode(), e);
        }
    }

    public void putLoginHistory(HistoryAccount historyAccount, String str) {
        if (saveToken(historyAccount.tokenKey, str)) {
            IDynamicDataStoreComponent dynamicDataStoreComp = getSecurityGuardManager().getDynamicDataStoreComp();
            if (dynamicDataStoreComp != null) {
                LoginHistory loginHistory;
                Object obj = null;
                try {
                    obj = dynamicDataStoreComp.getStringDDpEx(HISTORY_LOGIN_ACCOUNTS, 0);
                } catch (SecException e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(obj)) {
                    loginHistory = new LoginHistory();
                } else {
                    try {
                        loginHistory = parseObject(obj);
                    } catch (JSONException e2) {
                        LoginHistory loginHistory2 = new LoginHistory();
                        try {
                            dynamicDataStoreComp.removeStringDDpEx(HISTORY_LOGIN_ACCOUNTS, 0);
                            loginHistory = loginHistory2;
                        } catch (SecException e3) {
                            e3.printStackTrace();
                            loginHistory = loginHistory2;
                        }
                    }
                }
                if (loginHistory == null) {
                    return;
                }
                if (loginHistory.accountHistory != null) {
                    List arrayList = new ArrayList();
                    for (HistoryAccount historyAccount2 : loginHistory.accountHistory) {
                        if (TextUtils.isEmpty(historyAccount2.userId) || !historyAccount2.userId.equals(historyAccount.userId)) {
                            arrayList.add(historyAccount2);
                        } else {
                            historyAccount2.update(historyAccount);
                            historyAccount = historyAccount2;
                        }
                    }
                    arrayList.add(historyAccount);
                    for (int size = arrayList.size() - 3; size > 0; size--) {
                        removeSafeToken(((HistoryAccount) arrayList.remove(0)).tokenKey);
                    }
                    loginHistory.accountHistory = arrayList;
                    try {
                        dynamicDataStoreComp.putStringDDpEx(HISTORY_LOGIN_ACCOUNTS, toJSONString(loginHistory), 0);
                        return;
                    } catch (SecException e32) {
                        e32.printStackTrace();
                        return;
                    }
                }
                loginHistory.accountHistory = new ArrayList();
                loginHistory.accountHistory.add(historyAccount);
                try {
                    dynamicDataStoreComp.putStringDDpEx(HISTORY_LOGIN_ACCOUNTS, toJSONString(loginHistory), 0);
                } catch (SecException e322) {
                    e322.printStackTrace();
                }
            }
        }
    }

    public synchronized boolean saveToken(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            if (!(TextUtils.isEmpty(str) || getSecurityGuardManager() == null)) {
                try {
                    ISafeTokenComponent safeTokenComp = getSecurityGuardManager().getSafeTokenComp();
                    if (safeTokenComp != null) {
                        int i;
                        String[] strArr = new String[]{"", "", "", ""};
                        if (0 > strArr.length) {
                            i = 0;
                        } else {
                            i = 0;
                        }
                        z = safeTokenComp.saveToken(str, str2, strArr[i], 0);
                    }
                } catch (SecException e) {
                    e.printStackTrace();
                    Log.e("TAG", INoCaptchaComponent.errorCode + e.getErrorCode());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        return z;
    }

    public LoginHistory parseObject(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONArray jSONArray = new JSONArray(str);
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                HistoryAccount historyAccount = new HistoryAccount();
                historyAccount.userId = jSONObject.optString(UserTrackerConstants.USERID);
                historyAccount.tokenKey = jSONObject.optString("tokenKey");
                historyAccount.mobile = jSONObject.optString("mobile");
                historyAccount.nick = jSONObject.optString("nick");
                historyAccount.email = jSONObject.optString("email");
                arrayList.add(historyAccount);
            }
        }
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.accountHistory = arrayList;
        return loginHistory;
    }

    public String toJSONString(LoginHistory loginHistory) {
        if (loginHistory == null || loginHistory.accountHistory == null || loginHistory.accountHistory.size() <= 0) {
            return "";
        }
        JSONArray jSONArray = new JSONArray();
        for (HistoryAccount historyAccount : loginHistory.accountHistory) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(UserTrackerConstants.USERID, historyAccount.userId);
                jSONObject.put("tokenKey", historyAccount.tokenKey);
                jSONObject.put("nick", historyAccount.nick);
                jSONObject.put("email", historyAccount.email);
                jSONObject.put("mobile", historyAccount.mobile);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
                return "";
            }
        }
        return jSONArray.toString();
    }

    public List<HistoryAccount> getHistoryAccounts() {
        List<HistoryAccount> list = null;
        try {
            Object stringDDpEx = getSecurityGuardManager().getDynamicDataStoreComp().getStringDDpEx(HISTORY_LOGIN_ACCOUNTS, 0);
            if (!TextUtils.isEmpty(stringDDpEx)) {
                LoginHistory parseObject = parseObject(stringDDpEx);
                if (parseObject != null) {
                    list = parseObject.accountHistory;
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public HistoryAccount findHistoryAccount(String str) {
        try {
            List<HistoryAccount> historyAccounts = getHistoryAccounts();
            if (historyAccounts == null) {
                return null;
            }
            for (HistoryAccount historyAccount : historyAccounts) {
                if (historyAccount.userId != null && historyAccount.userId.equals(str)) {
                    return historyAccount;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void removeSafeToken(String str) {
        try {
            getSecurityGuardManager().getSafeTokenComp().removeToken(str);
        } catch (SecException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public HistoryAccount matchHistoryAccount(String str) {
        List<HistoryAccount> historyAccounts = getHistoryAccounts();
        if (historyAccounts != null) {
            for (HistoryAccount historyAccount : historyAccounts) {
                if (equals(str, historyAccount.nick) || equals(str, historyAccount.email)) {
                    return historyAccount;
                }
                if (equals(str, historyAccount.mobile)) {
                    return historyAccount;
                }
            }
        }
        return null;
    }

    public String signMap(String str, TreeMap<String, String> treeMap) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : treeMap.entrySet()) {
            stringBuilder.append((String) entry.getKey()).append(LoginConstants.EQUAL).append((String) entry.getValue()).append("&");
        }
        String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        Log.e("TAG", "map=" + substring);
        return signForLogin(str, substring);
    }

    private String signForLogin(String str, String str2) {
        try {
            return getSecurityGuardManager().getSafeTokenComp().signWithToken(str, str2.getBytes("UTF-8"), 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (SecException e2) {
            e2.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static boolean equals(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    private boolean isWeakSecurity() {
        return getSecurityGuardManager().getSDKVerison().contains("weak");
    }

    public String getProviderName() {
        return isWeakSecurity() ? "mini" : "full";
    }
}
