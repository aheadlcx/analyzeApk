package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class b {
    public static boolean a = true;
    public static List<a> b = new ArrayList();
    public static boolean c;
    private static ae d;
    private static boolean e;

    private static boolean a(a aVar) {
        List list = aVar.t;
        aVar.getClass();
        Object obj;
        if ("".equals("")) {
            obj = "bugly";
        } else {
            aVar.getClass();
            obj = "";
        }
        if (list == null || !list.contains(r0)) {
            return false;
        }
        return true;
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
            a(context, null);
        }
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (e) {
                an.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(an.b, "[init] context of init() is null, check it.");
            } else {
                a a = a.a(context);
                if (a(a)) {
                    a = false;
                } else {
                    String f = a.f();
                    if (f == null) {
                        Log.e(an.b, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                    } else {
                        a(context, f, a.z, buglyStrategy);
                    }
                }
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (e) {
                an.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(an.b, "[init] context is null, check it.");
            } else if (str == null) {
                Log.e(an.b, "init arg 'crashReportAppID' should not be null!");
            } else {
                e = true;
                if (z) {
                    c = true;
                    an.c = true;
                    an.d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    an.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    an.d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    an.d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    an.d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    an.d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                    an.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    an.b("[init] Open debug mode of Bugly.", new Object[0]);
                }
                an.a("[init] Bugly version: v%s", new Object[]{"2.6.5"});
                an.a(" crash report start initializing...", new Object[0]);
                an.b("[init] Bugly start initializing...", new Object[0]);
                an.a("[init] Bugly complete version: v%s", new Object[]{"2.6.5(1.3.4)"});
                Context a = ap.a(context);
                a a2 = a.a(a);
                a2.t();
                ao.a(a);
                d = ae.a(a, b);
                ak.a(a);
                com.tencent.bugly.crashreport.common.strategy.a a3 = com.tencent.bugly.crashreport.common.strategy.a.a(a, b);
                ac a4 = ac.a(a);
                if (a(a2)) {
                    a = false;
                } else {
                    a2.a(str);
                    an.a("[param] Set APP ID:%s", new Object[]{str});
                    a(buglyStrategy, a2);
                    com.tencent.bugly.crashreport.biz.b.a(a, buglyStrategy);
                    for (int i = 0; i < b.size(); i++) {
                        try {
                            if (a4.a(((a) b.get(i)).id)) {
                                ((a) b.get(i)).init(a, z, buglyStrategy);
                            }
                        } catch (Throwable th) {
                            if (!an.a(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                    a3.a(buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0);
                    an.b("[init] Bugly initialization finished.", new Object[0]);
                }
            }
        }
    }

    private static void a(BuglyStrategy buglyStrategy, a aVar) {
        if (buglyStrategy != null) {
            String substring;
            String appVersion = buglyStrategy.getAppVersion();
            if (!TextUtils.isEmpty(appVersion)) {
                if (appVersion.length() > 100) {
                    substring = appVersion.substring(0, 100);
                    an.d("appVersion %s length is over limit %d substring to %s", new Object[]{appVersion, Integer.valueOf(100), substring});
                } else {
                    substring = appVersion;
                }
                aVar.o = substring;
                an.a("[param] Set App version: %s", new Object[]{buglyStrategy.getAppVersion()});
            }
            try {
                if (buglyStrategy.isReplaceOldChannel()) {
                    appVersion = buglyStrategy.getAppChannel();
                    if (!TextUtils.isEmpty(appVersion)) {
                        String str;
                        if (appVersion.length() > 100) {
                            an.d("appChannel %s length is over limit %d substring to %s", new Object[]{appVersion, Integer.valueOf(100), appVersion.substring(0, 100)});
                            str = substring;
                        } else {
                            str = appVersion;
                        }
                        d.a(556, "app_channel", str.getBytes(), null, false);
                        aVar.q = str;
                    }
                } else {
                    Map a = d.a(556, null, true);
                    if (a != null) {
                        byte[] bArr = (byte[]) a.get("app_channel");
                        if (bArr != null) {
                            aVar.q = new String(bArr);
                        }
                    }
                }
                an.a("[param] Set App channel: %s", new Object[]{aVar.q});
            } catch (Exception e) {
                if (c) {
                    e.printStackTrace();
                }
            }
            appVersion = buglyStrategy.getAppPackageName();
            if (!TextUtils.isEmpty(appVersion)) {
                if (appVersion.length() > 100) {
                    substring = appVersion.substring(0, 100);
                    an.d("appPackageName %s length is over limit %d substring to %s", new Object[]{appVersion, Integer.valueOf(100), substring});
                } else {
                    substring = appVersion;
                }
                aVar.d = substring;
                an.a("[param] Set App package: %s", new Object[]{buglyStrategy.getAppPackageName()});
            }
            appVersion = buglyStrategy.getDeviceID();
            if (appVersion != null) {
                if (appVersion.length() > 100) {
                    substring = appVersion.substring(0, 100);
                    an.d("deviceId %s length is over limit %d substring to %s", new Object[]{appVersion, Integer.valueOf(100), substring});
                } else {
                    substring = appVersion;
                }
                aVar.c(substring);
                an.a("s[param] Set device ID: %s", new Object[]{substring});
            }
            aVar.g = buglyStrategy.isUploadProcess();
            ao.a = buglyStrategy.isBuglyLogUpload();
        }
    }

    public static synchronized void a(a aVar) {
        synchronized (b.class) {
            if (!b.contains(aVar)) {
                b.add(aVar);
            }
        }
    }
}
