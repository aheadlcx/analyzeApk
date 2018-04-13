package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import qsbk.app.api.BigCoverHelper;

public class MobclickAgent {

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_DUM_NORMAL(BigCoverHelper.REQCODE_CAREMA),
        E_DUM_GAME(161);
        
        private int a;

        private EScenarioType(int i) {
            this.a = i;
        }

        public int toValue() {
            return this.a;
        }
    }

    public static void setLocation(double d, double d2) {
        b.a().a(d, d2);
    }

    public static void setLatencyWindow(long j) {
    }

    public static void enableEncrypt(boolean z) {
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        b.a().a(z);
    }

    public static void setSecret(Context context, String str) {
        b.a().c(context, str);
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) {
        b.a().a(context, eScenarioType);
    }

    public static void setSessionContinueMillis(long j) {
        b.a().a(j);
    }

    public static b getAgent() {
        return b.a();
    }

    public static void setCheckDevice(boolean z) {
    }

    public static void setOpenGLContext(GL10 gl10) {
        b.a().a(gl10);
    }

    public static void openActivityDurationTrack(boolean z) {
        b.a().b(z);
    }

    public static void onPageStart(String str) {
        if (TextUtils.isEmpty(str)) {
            MLog.e("pageName is null or empty");
        } else {
            b.a().a(str);
        }
    }

    public static void onPageEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            MLog.e("pageName is null or empty");
        } else {
            b.a().b(str);
        }
    }

    public static void setDebugMode(boolean z) {
    }

    public static void onPause(Context context) {
        b.a().b(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onResume");
        } else {
            b.a().a(context);
        }
    }

    public static void reportError(Context context, String str) {
        b.a().a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        b.a().a(context, th);
    }

    public static void onEvent(Context context, String str) {
        b.a().a(context, str, null, -1, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            MLog.w("label is null or empty");
        } else {
            b.a().a(context, str, str2, -1, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            MLog.e("input map is null");
            return;
        }
        b.a().a(context, str, new HashMap(map), -1);
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        Map hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        b.a().a(context, str, hashMap, -1);
    }

    public static void onKillProcess(Context context) {
        b.a().c(context);
    }

    public static void onDeepLinkReceived(Context context, String str) {
        b.a().b(context, str);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            MLog.w("uid is null");
        } else if (str2.length() > 64) {
            MLog.w("uid is Illegal(length bigger then  legitimate length).");
        } else if (TextUtils.isEmpty(str)) {
            b.a().a("_adhoc", str2);
        } else if (str.length() > 32) {
            MLog.w("provider is Illegal(length bigger then  legitimate length).");
        } else {
            b.a().a(str, str2);
        }
    }

    public static void onProfileSignOff() {
        b.a().g();
    }
}
