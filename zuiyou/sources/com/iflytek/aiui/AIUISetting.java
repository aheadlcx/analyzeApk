package com.iflytek.aiui;

import android.os.Environment;
import com.iflytek.alsa.jni.AlsaJni;
import com.iflytek.cloud.Setting;
import com.iflytek.cloud.thirdparty.aj;
import com.iflytek.cloud.thirdparty.as;

public final class AIUISetting {
    private static String a = (Environment.getExternalStorageDirectory() + "/AIUI/");
    private static String b = (a + "audio/raw/");

    private AIUISetting() {
    }

    public static String getAIUIPath() {
        return a;
    }

    public static String getRawAudioPath() {
        return b;
    }

    public static boolean getSaveDataLog() {
        return aj.c();
    }

    public static void setDataLogPath(String str) {
        aj.a(str);
    }

    public static void setRawAudioPath(String str) {
        b = str;
    }

    public static void setSaveDataLog(boolean z) {
        setSaveDataLog(z, -1, 0, 0);
    }

    public static void setSaveDataLog(boolean z, long j, long j2, int i) {
        aj.a(z, j, j2, i);
    }

    public static void setShowLog(boolean z) {
        Setting.setShowLog(z);
        if (!Version.isMobileVersion()) {
            as.a(z);
            AlsaJni.showJniLog(z);
        }
    }
}
