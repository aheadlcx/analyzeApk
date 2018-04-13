package cn.v6.sixrooms.utils;

import com.ishumei.smantifraud.SmAntiFraud;

public class ShuMeiParameterUtils {
    public static String getParameterStr() {
        return "&deviceId=" + SmAntiFraud.getDeviceId() + "&mac=" + AppInfoUtils.getMacI() + "&imei=" + AppInfoUtils.getIMEII();
    }
}
