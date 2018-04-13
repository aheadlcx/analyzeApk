package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.util.Map;

public class Bugly {
    private static boolean a;
    public static Context applicationContext = null;
    public static boolean enable = true;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (Bugly.class) {
            if (!a) {
                a = true;
                applicationContext = ap.a(context);
                if (applicationContext == null) {
                    Log.e(an.b, "init arg 'context' should not be null!");
                } else {
                    b.a(CrashModule.getInstance());
                    b.a(Beta.getInstance());
                    b.a = enable;
                    b.a(applicationContext, str, z, buglyStrategy);
                }
            }
        }
    }

    public static synchronized String getAppChannel() {
        String str = null;
        synchronized (Bugly.class) {
            a b = a.b();
            if (b != null) {
                if (TextUtils.isEmpty(b.q)) {
                    ae a = ae.a();
                    if (a == null) {
                        str = b.q;
                    } else {
                        Map a2 = a.a(556, null, true);
                        if (a2 != null) {
                            byte[] bArr = (byte[]) a2.get("app_channel");
                            if (bArr != null) {
                                str = new String(bArr);
                            }
                        }
                    }
                }
                str = b.q;
            }
        }
        return str;
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        CrashReport.setIsDevelopmentDevice(context, z);
    }

    public static void setAppChannel(Context context, String str) {
        CrashReport.setAppChannel(context, str);
    }

    public static void setUserId(Context context, String str) {
        CrashReport.setUserId(context, str);
    }

    public static void setUserTag(Context context, int i) {
        CrashReport.setUserSceneTag(context, i);
    }

    public static void putUserData(Context context, String str, String str2) {
        CrashReport.putUserData(context, str, str2);
    }
}
