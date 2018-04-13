package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.d.b;
import com.spriteapp.booklibrary.d.c;
import com.spriteapp.booklibrary.d.d;
import com.spriteapp.booklibrary.enumeration.LoginStateEnum;
import com.spriteapp.booklibrary.enumeration.UpdaterPayEnum;
import de.greenrobot.event.EventBus;

public class AppUtil {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static String getUserAgent() {
        String str = "HuaxiReader/1.0 (" + Build.MODEL + ") android/" + VERSION.RELEASE;
        return isHeaderLegal(str) ? str : "";
    }

    public static boolean isNetAvailable(Context context) {
        if (NetworkUtil.isAvailable(context)) {
            return true;
        }
        ToastUtil.showSingleToast(f.please_check_network_info);
        return false;
    }

    public static String getHeaderSnValue() {
        String deviceId = DeviceUtil.getDeviceId(mContext);
        if (StringUtil.isEmpty(deviceId)) {
            deviceId = DeviceUtil.getMacAddress(mContext);
        }
        return MD5Util.encryptMD5(deviceId);
    }

    public static boolean isLogin() {
        return !StringUtil.isEmpty(SharedPreferencesUtil.getInstance().getString("hua_xi_token"));
    }

    public static String getToken() {
        return SharedPreferencesUtil.getInstance().getString("hua_xi_token");
    }

    public static void loginOut() {
        clearToken();
        clearBookInfo();
        HuaXiSDK.getInstance().setRegisterModelNull();
        clearShare();
        HuaXiSDK.mLoginState = LoginStateEnum.UN_LOGIN;
        EventBus.getDefault().post(UpdaterPayEnum.UPDATE_LOGIN_OUT);
    }

    private static void clearShare() {
        SharedPreferencesUtil.getInstance().removeAll();
    }

    public static void clearToken() {
        SharedPreferencesUtil.getInstance().putString("hua_xi_token", "");
    }

    public static void clearBookInfo() {
        b bVar = new b(mContext);
        d dVar = new d(mContext);
        c cVar = new c(mContext);
        bVar.f();
        dVar.c();
        cVar.c();
    }

    public static boolean isHeaderLegal(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if ((charAt <= '\u001f' && charAt != '\t') || charAt >= '') {
                return false;
            }
        }
        return true;
    }
}
