package com.alibaba.sdk.android.oss.common.utils;

import com.iflytek.speech.VoiceWakeuperAidl;

public class VersionInfoUtils {
    private static String userAgent = null;
    private static String version = null;

    public static String getUserAgent() {
        if (userAgent == null) {
            userAgent = "aliyun-sdk-android/" + getVersion() + "/" + getDefaultUserAgent();
        }
        return userAgent;
    }

    public static String getVersion() {
        return "2.4.0";
    }

    public static String getDefaultUserAgent() {
        String property = System.getProperty("http.agent");
        if (OSSUtils.isEmptyString(property)) {
            property = "(" + System.getProperty("os.name") + "/" + System.getProperty("os.version") + "/" + System.getProperty("os.arch") + VoiceWakeuperAidl.PARAMS_SEPARATE + System.getProperty("java.version") + ")";
        }
        return property.replaceAll("[^\\p{ASCII}]", "?");
    }
}
