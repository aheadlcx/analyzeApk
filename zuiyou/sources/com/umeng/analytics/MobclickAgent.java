package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.umeng.a.g;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.analytics.social.UMSocialService;
import com.umeng.analytics.social.d;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

public class MobclickAgent {
    private static final String a = "input map is null";
    private static final b b = new b();

    public static class UMAnalyticsConfig {
        public String mAppkey;
        public String mChannelId;
        public Context mContext;
        public boolean mIsCrashEnable;
        public MobclickAgent$EScenarioType mType;

        private UMAnalyticsConfig() {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = MobclickAgent$EScenarioType.E_UM_NORMAL;
            this.mContext = null;
        }

        public UMAnalyticsConfig(Context context, String str, String str2) {
            this(context, str, str2, null, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, MobclickAgent$EScenarioType mobclickAgent$EScenarioType) {
            this(context, str, str2, mobclickAgent$EScenarioType, true);
        }

        public UMAnalyticsConfig(Context context, String str, String str2, MobclickAgent$EScenarioType mobclickAgent$EScenarioType, boolean z) {
            this.mAppkey = null;
            this.mChannelId = null;
            this.mIsCrashEnable = true;
            this.mType = MobclickAgent$EScenarioType.E_UM_NORMAL;
            this.mContext = null;
            this.mContext = context;
            this.mAppkey = str;
            this.mChannelId = str2;
            this.mIsCrashEnable = z;
            if (mobclickAgent$EScenarioType != null) {
                this.mType = mobclickAgent$EScenarioType;
                return;
            }
            switch (AnalyticsConfig.getVerticalType(context)) {
                case 0:
                    this.mType = MobclickAgent$EScenarioType.E_UM_NORMAL;
                    return;
                case 1:
                    this.mType = MobclickAgent$EScenarioType.E_UM_GAME;
                    return;
                case Opcodes.SHL_INT_LIT8 /*224*/:
                    this.mType = MobclickAgent$EScenarioType.E_UM_ANALYTICS_OEM;
                    return;
                case Opcodes.SHR_INT_LIT8 /*225*/:
                    this.mType = MobclickAgent$EScenarioType.E_UM_GAME_OEM;
                    return;
                default:
                    return;
            }
        }
    }

    public static void startWithConfigure(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig != null) {
            b.a(uMAnalyticsConfig);
        }
    }

    public static void setLocation(double d, double d2) {
        b.a(d, d2);
    }

    public static void setLatencyWindow(long j) {
        b.a(j);
    }

    public static void enableEncrypt(boolean z) {
        b.e(z);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        b.a(z);
    }

    public static void setSecret(Context context, String str) {
        b.b(context, str);
    }

    public static void setScenarioType(Context context, MobclickAgent$EScenarioType mobclickAgent$EScenarioType) {
        b.a(context, mobclickAgent$EScenarioType);
    }

    public static void setSessionContinueMillis(long j) {
        b.b(j);
    }

    public static b getAgent() {
        return b;
    }

    public static void setCheckDevice(boolean z) {
        b.c(z);
    }

    public static void setOpenGLContext(GL10 gl10) {
        b.a(gl10);
    }

    public static void openActivityDurationTrack(boolean z) {
        b.b(z);
    }

    public static void onPageStart(String str) {
        if (TextUtils.isEmpty(str)) {
            g.d("pageName is null or empty");
        } else {
            b.a(str);
        }
    }

    public static void onPageEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            g.d("pageName is null or empty");
        } else {
            b.b(str);
        }
    }

    public static void setDebugMode(boolean z) {
        b.d(z);
    }

    public static void onPause(Context context) {
        b.b(context);
    }

    public static void onResume(Context context) {
        if (context == null) {
            g.d("unexpected null context in onResume");
        } else {
            b.a(context);
        }
    }

    public static void reportError(Context context, String str) {
        b.a(context, str);
    }

    public static void reportError(Context context, Throwable th) {
        b.a(context, th);
    }

    public static void onEvent(Context context, List<String> list, int i, String str) {
        b.a(context, list, i, str);
    }

    public static void onEvent(Context context, String str) {
        b.a(context, str, null, -1, 1);
    }

    public static void onEvent(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            g.b("label is null or empty");
        } else {
            b.a(context, str, str2, -1, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        if (map == null) {
            g.d(a);
            return;
        }
        b.a(context, str, new HashMap(map), -1);
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) {
        Map hashMap;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("__ct__", Integer.valueOf(i));
        b.a(context, str, hashMap, -1);
    }

    public static void onSocialEvent(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            g.d("context is null in onShareEvent");
            return;
        }
        d.d = "3";
        UMSocialService.share(context, str, uMPlatformDataArr);
    }

    public static void onSocialEvent(Context context, UMPlatformData... uMPlatformDataArr) {
        if (context == null) {
            g.d("context is null in onShareEvent");
            return;
        }
        d.d = "3";
        UMSocialService.share(context, uMPlatformDataArr);
    }

    public static void onKillProcess(Context context) {
        b.d(context);
    }

    public static void onProfileSignIn(String str) {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            g.c("uid is null");
        } else if (str2.length() > 64) {
            g.c("uid is Illegal(length bigger then  legitimate length).");
        } else if (TextUtils.isEmpty(str)) {
            b.a("_adhoc", str2);
        } else if (str.length() > 32) {
            g.c("provider is Illegal(length bigger then  legitimate length).");
        } else {
            b.a(str, str2);
        }
    }

    public static void onProfileSignOff() {
        b.b();
    }
}
