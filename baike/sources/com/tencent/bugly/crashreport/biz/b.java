package com.tencent.bugly.crashreport.biz;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.List;
import qsbk.app.utils.ListViewHelper;

public class b {
    public static a a;
    private static boolean b;
    private static int c = 10;
    private static long d = ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL;
    private static long e = i.MIN_UPLOAD_INTERVAL;
    private static long f = 0;
    private static int g;
    private static long h;
    private static long i;
    private static long j = 0;
    private static ActivityLifecycleCallbacks k = null;
    private static Class<?> l = null;
    private static boolean m = true;

    static /* synthetic */ String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(z.a());
        stringBuilder.append("  ");
        stringBuilder.append(str);
        stringBuilder.append("  ");
        stringBuilder.append(str2);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    static /* synthetic */ int g() {
        int i = g;
        g = i + 1;
        return i;
    }

    private static void c(Context context, BuglyStrategy buglyStrategy) {
        boolean isEnableUserInfo;
        boolean z;
        if (buglyStrategy != null) {
            boolean recordUserInfoOnceADay = buglyStrategy.recordUserInfoOnceADay();
            isEnableUserInfo = buglyStrategy.isEnableUserInfo();
            z = recordUserInfoOnceADay;
        } else {
            isEnableUserInfo = true;
            z = false;
        }
        if (z) {
            Object obj;
            a a = a.a(context);
            List a2 = a.a(a.d);
            if (a2 != null) {
                for (int i = 0; i < a2.size(); i++) {
                    UserInfoBean userInfoBean = (UserInfoBean) a2.get(i);
                    if (userInfoBean.n.equals(a.j) && userInfoBean.b == 1) {
                        long b = z.b();
                        if (b <= 0) {
                            break;
                        } else if (userInfoBean.e >= b) {
                            if (userInfoBean.f <= 0) {
                                a aVar = a;
                                w a3 = w.a();
                                if (a3 != null) {
                                    a3.a(new e(aVar));
                                }
                            }
                            obj = null;
                            if (obj == null) {
                                isEnableUserInfo = false;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
            obj = 1;
            if (obj == null) {
                isEnableUserInfo = false;
            } else {
                return;
            }
        }
        a b2 = a.b();
        if (b2 != null) {
            Object obj2 = null;
            String str = null;
            for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    str = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    obj2 = 1;
                }
            }
            if (str == null) {
                str = "unknown";
            } else if (obj2 != null) {
                b2.a(true);
            } else {
                str = "background";
            }
            b2.p = str;
        }
        if (isEnableUserInfo) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k == null) {
                            k = new g();
                        }
                        application.registerActivityLifecycleCallbacks(k);
                    } catch (Throwable e) {
                        if (!x.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (m) {
            i = System.currentTimeMillis();
            a.a(1, false, 0);
            x.a("[session] launch app, new start", new Object[0]);
            a.a();
            w.a().a(new c(a, 21600000), 21600000);
        }
    }

    public static void a(Context context, BuglyStrategy buglyStrategy) {
        if (!b) {
            long appReportDelay;
            m = a.a(context).e;
            a = new a(context, m);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                appReportDelay = buglyStrategy.getAppReportDelay();
            } else {
                appReportDelay = 0;
            }
            if (appReportDelay <= 0) {
                c(context, buglyStrategy);
            } else {
                w.a().a(new f(context, buglyStrategy), appReportDelay);
            }
        }
    }

    public static void a(long j) {
        if (j < 0) {
            j = com.tencent.bugly.crashreport.common.strategy.a.a().c().q;
        }
        f = j;
    }

    public static void a(StrategyBean strategyBean, boolean z) {
        if (!(a == null || z)) {
            a aVar = a;
            w a = w.a();
            if (a != null) {
                a.a(new e(aVar));
            }
        }
        if (strategyBean != null) {
            if (strategyBean.q > 0) {
                e = strategyBean.q;
            }
            if (strategyBean.w > 0) {
                c = strategyBean.w;
            }
            if (strategyBean.x > 0) {
                d = strategyBean.x;
            }
        }
    }

    public static void a() {
        if (a != null) {
            a.a(2, false, 0);
        }
    }

    public static void a(Context context) {
        if (b && context != null) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k != null) {
                            application.unregisterActivityLifecycleCallbacks(k);
                        }
                    } catch (Throwable e) {
                        if (!x.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            b = false;
        }
    }
}
