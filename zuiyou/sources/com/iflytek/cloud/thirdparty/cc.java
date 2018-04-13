package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.LinkedHashMap;

public class cc {
    public static LinkedHashMap<String, String> a = new LinkedHashMap();
    private static String b = "=";
    private static String c = ":";
    private static String d = VoiceWakeuperAidl.PARAMS_SEPARATE;
    private static String e = "=========================================================\r\n";
    private static boolean f = false;

    public static synchronized void a(String str, String str2) {
        synchronized (cc.class) {
            if (f) {
                cb.d("appendInfo:" + str + "," + str2);
                a.put(str, (!TextUtils.isEmpty(str2) ? str2 + c : "") + System.currentTimeMillis());
            }
        }
    }
}
