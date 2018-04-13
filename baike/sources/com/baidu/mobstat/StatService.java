package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sina.weibo.sdk.statistic.i;
import java.util.Map;

public class StatService {
    public static final int EXCEPTION_LOG = 1;
    public static final int JAVA_EXCEPTION_LOG = 16;
    private static boolean a = false;
    private static long b;

    public interface WearListener {
        boolean onSendLogData(String str);
    }

    private static boolean a(Class<?> cls, String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean z = false;
        for (int i = 2; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            if (stackTraceElement.getMethodName().equals(str)) {
                try {
                    Class cls2 = Class.forName(stackTraceElement.getClassName());
                    while (cls2.getSuperclass() != null && cls2.getSuperclass() != cls) {
                        cls2 = cls2.getSuperclass();
                    }
                    z = true;
                } catch (Throwable e) {
                    db.a(e);
                }
            }
        }
        return z;
    }

    private static String a(boolean z) {
        String str = "";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement className : stackTrace) {
            Object className2 = className.getClassName();
            if (!TextUtils.isEmpty(className2)) {
                Class cls = null;
                try {
                    cls = Class.forName(className2);
                } catch (Throwable th) {
                }
                if (cls != null && Activity.class.isAssignableFrom(cls)) {
                    if (z) {
                        return cls.getName();
                    }
                    return cls.getSimpleName();
                }
            }
        }
        return str;
    }

    public static void enableDeviceMac(Context context, boolean z) {
        CooperService.a().enableDeviceMac(context, z);
        a(context);
    }

    public static synchronized void onResume(Context context) {
        synchronized (StatService.class) {
            if (a(context, "onResume(...)")) {
                if (a(Activity.class, "onResume")) {
                    a(context);
                    bv.a().a(context);
                    ch.a().a(context, System.currentTimeMillis(), false);
                } else {
                    throw new SecurityException("onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
                }
            }
        }
    }

    @Deprecated
    public static synchronized void onResume(Fragment fragment) {
        synchronized (StatService.class) {
            if (fragment == null) {
                db.c("onResume :parame=null");
            } else if (a(Fragment.class, "onResume")) {
                Context activity = fragment.getActivity();
                if (activity == null) {
                    db.c("can not get correct fragmentActivity, fragment may not attached to activity");
                } else {
                    a(activity);
                    bv.a().a(activity);
                    ch.a().a(fragment, System.currentTimeMillis());
                }
            } else {
                throw new SecurityException("onResume(Context context)不在Fragment.onResume()中被调用||onResume(Context context)is not called in Fragment.onResume().");
            }
        }
    }

    @TargetApi(11)
    @Deprecated
    public static synchronized void onResume(android.app.Fragment fragment) {
        synchronized (StatService.class) {
            if (fragment == null) {
                db.c("onResume :parame=null");
            } else if (a(fragment.getClass(), "onResume")) {
                Context a = ch.a((Object) fragment);
                if (a == null) {
                    db.c("can not get correct context, fragment may not attached to activity");
                } else {
                    a(a);
                    bv.a().a(a);
                    ch.a().a(fragment, System.currentTimeMillis());
                }
            } else {
                throw new SecurityException("onResume(Context context)不在Fragment.onResume()中被调用||onResume(Context context)is not called in Fragment.onResume().");
            }
        }
    }

    public static synchronized void onPageStart(Context context, String str) {
        synchronized (StatService.class) {
            if (!(context == null || str == null)) {
                if (!str.equals("")) {
                    a(context);
                    bv.a().a(context);
                    ch.a().a(context, System.currentTimeMillis(), str);
                }
            }
            db.c("onPageStart :parame=null || empty");
        }
    }

    private static synchronized void a(Context context, String str, ExtraInfo extraInfo) {
        synchronized (StatService.class) {
            if (!(context == null || str == null)) {
                if (!str.equals("")) {
                    String a = a(false);
                    db.a("pageName is:" + str + "; activityName is:" + a);
                    ch.a().a(context, System.currentTimeMillis(), a, str, extraInfo);
                }
            }
            db.c("onPageEnd :parame=null || empty");
        }
    }

    public static synchronized void onPageEnd(Context context, String str) {
        synchronized (StatService.class) {
            a(context, str, null);
        }
    }

    private static synchronized void a(Context context, ExtraInfo extraInfo) {
        synchronized (StatService.class) {
            if (a(context, "onPause(...)")) {
                if (a(Activity.class, "onPause")) {
                    ch.a().a(context, System.currentTimeMillis(), false, extraInfo);
                } else {
                    throw new SecurityException("onPause(Context context)不在Activity.onPause()中被调用||onPause(Context context)is not called in Activity.onPause().");
                }
            }
        }
    }

    public static synchronized void onPause(Context context) {
        synchronized (StatService.class) {
            a(context, null);
        }
    }

    @Deprecated
    public static synchronized void onPause(Fragment fragment) {
        synchronized (StatService.class) {
            if (fragment == null) {
                db.c("onResume :parame=null");
            } else if (a(Fragment.class, "onPause")) {
                ch.a().b(fragment, System.currentTimeMillis());
            } else {
                throw new SecurityException("Fragment onPause(Context context)不在Fragment.onPause()中被调用||onPause(Context context)is not called in Fragment.onPause().");
            }
        }
    }

    @TargetApi(11)
    @Deprecated
    public static synchronized void onPause(android.app.Fragment fragment) {
        synchronized (StatService.class) {
            if (fragment == null) {
                db.c("android.app.Fragment onResume :parame=null");
            } else if (a(fragment.getClass(), "onPause")) {
                ch.a().b(fragment, System.currentTimeMillis());
            } else {
                throw new SecurityException("android.app.Fragment onPause(Context context)不在android.app.Fragment.onPause()中被调用||onPause(Context context)is not called in android.app.Fragment.onPause().");
            }
        }
    }

    public static void setOn(Context context, int i) {
        if (a(context, "setOn(...)") && !a) {
            a = true;
            if ((i & 1) != 0) {
                a(context, false);
            } else if ((i & 16) != 0) {
                a(context, true);
            }
            a(context);
        }
    }

    public static void start(Context context) {
        if (a(context, "start(...)")) {
            boolean a = dg.a(Application.class, "onCreate");
            if (a) {
                db.c("method:start() 被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            a(context);
            bv.a().a(context, a);
        }
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (a(context, "setSendLogStrategy(...)")) {
            boolean a = dg.a(Application.class, "onCreate");
            if (a) {
                db.c("method:start() 被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            a(context);
            bv.a().a(context, a);
            by.a().a(context.getApplicationContext(), sendStrategyEnum, i, z);
        }
    }

    @Deprecated
    public static void setSendLogStrategy(Context context, SendStrategyEnum sendStrategyEnum, int i) {
        setSendLogStrategy(context, sendStrategyEnum, i, false);
    }

    private static void a(Context context, boolean z) {
        if (a(context, "onError(...)")) {
            bt.a().a(context.getApplicationContext(), z);
        }
    }

    private static void a(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map) {
        if (a(context, "onEvent(...)") && str != null && !str.equals("")) {
            boolean a = dg.a(Application.class, "onCreate");
            if (a) {
                db.c("method:onEvent() 被 Application.onCreate()调用，not a good practice; 可能由于多进程反复重启等原因造成Application.onCreate() 方法多次被执行，导致启动次数高；建议埋点在统计路径触发的第一个页面中，比如APP主页面中");
            }
            a(context);
            bv.a().a(context, a);
            bm.a().a(context.getApplicationContext(), str, str2, i, System.currentTimeMillis(), extraInfo, dg.a((Map) map));
        }
    }

    public static void onEvent(Context context, String str, String str2, int i, Map<String, String> map) {
        a(context, str, str2, i, null, (Map) map);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        a(context, str, str2, i, null, null);
    }

    private static void a(Context context, String str, String str2, ExtraInfo extraInfo) {
        a(context, str, str2, 1, extraInfo, null);
    }

    public static void onEvent(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void onEventStart(Context context, String str, String str2) {
        if (a(context, "onEventStart(...)") && str != null && !str.equals("")) {
            a(context);
            bv.a().a(context);
            bm.a().a(context.getApplicationContext(), str, str2, System.currentTimeMillis());
        }
    }

    private static void a(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        if (a(context, "onEventEnd(...)") && str != null && !str.equals("")) {
            bm.a().a(context.getApplicationContext(), str, str2, System.currentTimeMillis(), extraInfo, dg.a((Map) map));
        }
    }

    public static void onEventEnd(Context context, String str, String str2, Map<String, String> map) {
        a(context.getApplicationContext(), str, str2, null, map);
    }

    public static void onEventEnd(Context context, String str, String str2) {
        a(context, str, str2, null, null);
    }

    private static void a(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map) {
        if (a(context, "onEventDuration(...)") && str != null && !str.equals("")) {
            if (j <= 0) {
                db.b("onEventDuration: duration must be greater than zero");
                return;
            }
            a(context);
            bv.a().a(context);
            bm.a().b(context.getApplicationContext(), str, str2, j, extraInfo, dg.a((Map) map));
        }
    }

    public static void onEventDuration(Context context, String str, String str2, long j, Map<String, String> map) {
        a(context, str, str2, j, null, (Map) map);
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        a(context, str, str2, j, null, null);
    }

    private static boolean a(Context context, String str) {
        if (context != null) {
            return true;
        }
        db.b(str + ":context=null");
        return false;
    }

    public static void setAppKey(String str) {
        PrefOperate.setAppKey(str);
    }

    public static String getAppKey(Context context) {
        return PrefOperate.getAppKey(context);
    }

    @Deprecated
    public static void setAppChannel(String str) {
        PrefOperate.setAppChannel(str);
    }

    public static void setAppChannel(Context context, String str, boolean z) {
        PrefOperate.setAppChannel(context, str, z);
        a(context);
    }

    public static void setLogSenderDelayed(int i) {
        by.a().a(i);
    }

    public static void setSessionTimeOut(int i) {
        if (i <= 0) {
            db.b("SessionTimeOut is between 1 and 600. Default value[30] is used");
        } else if (i <= 600) {
            ch.a().a(i);
        } else {
            db.b("SessionTimeOut is between 1 and 600. Value[600] is used");
            ch.a().a(600);
        }
    }

    public static void setDebugOn(boolean z) {
        db.a = z ? 2 : 7;
    }

    public static void setForTv(Context context, boolean z) {
        bj.a().c(context, z);
        a(context);
    }

    public static void bindJSInterface(Context context, WebView webView) {
        bindJSInterface(context, webView, null);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void bindJSInterface(Context context, WebView webView, WebViewClient webViewClient) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null.");
        } else if (webView == null) {
            throw new IllegalArgumentException("webview can't be null.");
        } else {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDefaultTextEncodingName("UTF-8");
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            webView.setWebViewClient(new bi(context, webViewClient));
            a(context);
        }
    }

    public static String getSdkVersion() {
        return CooperService.a().getMTJSDKVersion();
    }

    private static void a(Context context) {
        bf.a().a(context);
    }

    public static void onErised(Context context, String str, String str2, String str3) {
        if (bv.a().b() || !a(context, "onErised(...)")) {
            return;
        }
        if (str == null || "".equals(str)) {
            db.c("AppKey is invalid");
            return;
        }
        bv.a().a(context, false, false);
        long currentTimeMillis = System.currentTimeMillis();
        bm.a().a(context, str2, str3, 1, currentTimeMillis, 0, null, null);
        DataCore.instance().saveLogDataToSend(context, true, false);
        if (currentTimeMillis - b > i.MIN_UPLOAD_INTERVAL && de.n(context)) {
            by.a().a(context);
            b = currentTimeMillis;
        }
    }
}
