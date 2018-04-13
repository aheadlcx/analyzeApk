package com.ali.auth.third.core.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.exception.SecRuntimeException;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.model.HistoryAccount;
import com.ali.auth.third.core.service.StorageService;
import com.ali.auth.third.core.storage.aes.AESCrypt;
import com.ali.auth.third.core.storage.aes.MD5;
import java.util.List;
import java.util.TreeMap;

public class CommonStorageServiceImpl implements StorageService {
    private Context context = KernelContext.getApplicationContext();
    private SharedPreferences sp = this.context.getSharedPreferences(Constants.TB_SESSION, 0);
    private String umid;

    public String getValue(String str, boolean z) {
        String string = this.sp.getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return string;
        }
        return symDecrypt(string, MD5.getMD5(KernelContext.timestamp + ""));
    }

    @SuppressLint({"NewApi"})
    public void putValue(String str, String str2, boolean z) {
        String symEncrypt = symEncrypt(str2, MD5.getMD5(KernelContext.timestamp + ""));
        if (VERSION.SDK_INT >= 9) {
            this.sp.edit().putString(str, symEncrypt).apply();
        } else {
            this.sp.edit().putString(str, symEncrypt).commit();
        }
    }

    public void removeValue(String str, boolean z) {
        this.sp.edit().remove(str);
    }

    public String symEncrypt(String str, String str2) {
        try {
            return AESCrypt.encrypt(str2, str);
        } catch (Throwable e) {
            throw new SecRuntimeException(-1, e);
        }
    }

    public String symDecrypt(String str, String str2) {
        try {
            return AESCrypt.decrypt(str2, str);
        } catch (Throwable e) {
            throw new SecRuntimeException(-2, e);
        }
    }

    public byte[] getByteArray(String str) {
        return new byte[0];
    }

    public void savePublicKey(byte[] bArr) {
    }

    public String getUmid() {
        return this.umid;
    }

    public void setUmid(String str) {
        this.umid = str;
    }

    public String getAppKey() {
        String str = null;
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get("com.alibaba.app.appkey");
                if (obj != null) {
                    str = obj.toString();
                }
            }
        } catch (Exception e) {
        }
        return str;
    }

    public void putLoginHistory(HistoryAccount historyAccount, String str) {
    }

    public List<HistoryAccount> getHistoryAccounts() {
        return null;
    }

    public HistoryAccount findHistoryAccount(String str) {
        return null;
    }

    public void removeSafeToken(String str) {
    }

    public HistoryAccount matchHistoryAccount(String str) {
        return null;
    }

    public String signMap(String str, TreeMap<String, String> treeMap) {
        return null;
    }
}
