package com.tencent.bugly.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.b;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.af;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrashReport {
    private static Context a;

    public interface WebViewInterface {
        void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str);

        CharSequence getContentDescription();

        String getUrl();

        void loadUrl(String str);

        void setJavaScriptEnabled(boolean z);
    }

    public static class CrashHandleCallback extends BuglyStrategy$a {
    }

    public static class UserStrategy extends BuglyStrategy {
        CrashHandleCallback a;

        public UserStrategy(Context context) {
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.a = crashHandleCallback;
        }
    }

    public static void enableBugly(boolean z) {
        b.a = z;
    }

    public static void initCrashReport(Context context) {
        a = context;
        b.a(CrashModule.getInstance());
        b.a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        a = context;
        b.a(CrashModule.getInstance());
        b.a(context, (BuglyStrategy) userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        initCrashReport(context, str, z, null);
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context != null) {
            a = context;
            b.a(CrashModule.getInstance());
            b.a(context, str, z, userStrategy);
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context != null) {
            return a.a(context).c();
        }
        an.d("Please call with context.", new Object[0]);
        return "unknown";
    }

    public static void testJavaCrash() {
        if (!b.a) {
            Log.w(an.b, "Can not test Java crash because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            a b = a.b();
            if (b != null) {
                b.b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void testNativeCrash() {
        if (!b.a) {
            Log.w(an.b, "Can not test native crash because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            an.a("start to create a native crash for test!", new Object[0]);
            c.a().k();
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void testANRCrash() {
        if (!b.a) {
            Log.w(an.b, "Can not test ANR crash because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            an.a("start to create a anr crash for test!", new Object[0]);
            c.a().l();
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread());
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!b.a) {
            Log.w(an.b, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (th == null) {
            an.d("throwable is null, just return", new Object[0]);
        } else {
            Thread currentThread;
            if (thread == null) {
                currentThread = Thread.currentThread();
            } else {
                currentThread = thread;
            }
            c.a().a(currentThread, th, false, null, null, z);
        }
    }

    public static void closeNativeReport() {
        if (!b.a) {
            Log.w(an.b, "Can not close native report because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            c.a().g();
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void startCrashReport() {
        if (!b.a) {
            Log.w(an.b, "Can not start crash report because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            c.a().c();
        } else {
            Log.w(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void closeCrashReport() {
        if (!b.a) {
            Log.w(an.b, "Can not close crash report because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            c.a().d();
        } else {
            Log.w(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void closeBugly() {
        if (!b.a) {
            Log.w(an.b, "Can not close bugly because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (a != null) {
            BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
            if (instance != null) {
                instance.unregister(a);
            }
            closeCrashReport();
            com.tencent.bugly.crashreport.biz.b.a(a);
            am a = am.a();
            if (a != null) {
                a.b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!b.a) {
            Log.w(an.b, "Can not set tag caught because bugly is disable.");
        } else if (context == null) {
            Log.e(an.b, "setTag args context should not be null");
        } else {
            if (i <= 0) {
                an.d("setTag args tagId should > 0", new Object[0]);
            }
            a.a(context).a(i);
            an.b("[param] set user scene tag: %d", new Object[]{Integer.valueOf(i)});
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!b.a) {
            Log.w(an.b, "Can not get user scene tag because bugly is disable.");
            return -1;
        } else if (context != null) {
            return a.a(context).H();
        } else {
            Log.e(an.b, "getUserSceneTagId args context should not be null");
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        if (!b.a) {
            Log.w(an.b, "Can not get user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(an.b, "getUserDataValue args context should not be null");
            return "unknown";
        } else if (ap.a(str)) {
            return null;
        } else {
            return a.a(context).g(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!b.a) {
            Log.w(an.b, "Can not put user data because bugly is disable.");
        } else if (context == null) {
            Log.w(an.b, "putUserData args context should not be null");
        } else if (str == null) {
            "" + str;
            an.d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            "" + str2;
            an.d("putUserData args value should not be null", new Object[0]);
        } else if (str.matches("[a-zA-Z[0-9]]+")) {
            if (str2.length() > 200) {
                an.d("user data value length over limit %d, it will be cutted!", new Object[]{Integer.valueOf(200)});
                str2 = str2.substring(0, 200);
            }
            a a = a.a(context);
            NativeCrashHandler instance;
            if (a.E().contains(str)) {
                instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                a.a(context).b(str, str2);
                an.c("replace KV %s %s", new Object[]{str, str2});
            } else if (a.D() >= 10) {
                an.d("user data size is over limit %d, it will be cutted!", new Object[]{Integer.valueOf(10)});
            } else {
                if (str.length() > 50) {
                    an.d("user data key length over limit %d , will drop this new key %s", new Object[]{Integer.valueOf(50), str});
                    str = str.substring(0, 50);
                }
                instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                a.a(context).b(str, str2);
                an.b("[param] set user data: %s - %s", new Object[]{str, str2});
            }
        } else {
            an.d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
        }
    }

    public static String removeUserData(Context context, String str) {
        if (!b.a) {
            Log.w(an.b, "Can not remove user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(an.b, "removeUserData args context should not be null");
            return "unknown";
        } else if (ap.a(str)) {
            return null;
        } else {
            an.b("[param] remove user data: %s", new Object[]{str});
            return a.a(context).f(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!b.a) {
            Log.w(an.b, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        } else if (context != null) {
            return a.a(context).E();
        } else {
            Log.e(an.b, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        if (!b.a) {
            Log.w(an.b, "Can not get size of user data because bugly is disable.");
            return -1;
        } else if (context != null) {
            return a.a(context).D();
        } else {
            Log.e(an.b, "getUserDatasSize args context should not be null");
            return -1;
        }
    }

    public static String getAppID() {
        if (!b.a) {
            Log.w(an.b, "Can not get App ID because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return a.a(a).f();
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static void setUserId(String str) {
        if (!b.a) {
            Log.w(an.b, "Can not set user ID because bugly is disable.");
        } else if (CrashModule.hasInitialized()) {
            setUserId(a, str);
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        }
    }

    public static void setUserId(Context context, String str) {
        if (!b.a) {
            Log.w(an.b, "Can not set user ID because bugly is disable.");
        } else if (context == null) {
            Log.e(an.b, "Context should not be null when bugly has not been initialed!");
        } else if (str == null) {
            an.d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                an.d("userId %s length is over limit %d substring to %s", new Object[]{str, Integer.valueOf(100), str.substring(0, 100)});
                str = r0;
            }
            if (!str.equals(a.a(context).g())) {
                a.a(context).b(str);
                an.b("[user] set userId : %s", new Object[]{str});
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.setNativeUserId(str);
                }
                if (CrashModule.hasInitialized()) {
                    com.tencent.bugly.crashreport.biz.b.a();
                }
            }
        }
    }

    public static String getUserId() {
        if (!b.a) {
            Log.w(an.b, "Can not get user ID because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return a.a(a).g();
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static String getAppVer() {
        if (!b.a) {
            Log.w(an.b, "Can not get app version because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return a.a(a).o;
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static String getAppChannel() {
        if (!b.a) {
            Log.w(an.b, "Can not get App channel because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return a.a(a).q;
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static void setContext(Context context) {
        a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!b.a) {
            Log.w(an.b, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        } else if (CrashModule.hasInitialized()) {
            return c.a().b();
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!b.a) {
            Log.w(an.b, "Can not put SDK extra data because bugly is disable.");
        } else if (context != null && !ap.a(str) && !ap.a(str2)) {
            a.a(context).a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!b.a) {
            Log.w(an.b, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (CrashModule.hasInitialized()) {
            return a.a(a).F;
        } else {
            Log.e(an.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!b.a) {
            Log.w(an.b, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (context != null) {
            return a.a(context).F;
        } else {
            an.d("Context should not be null.", new Object[0]);
            return null;
        }
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null && !ap.a(str) && !ap.a(str2)) {
            String replace = str.replace("[a-zA-Z[0-9]]+", "");
            if (replace.length() > 100) {
                Log.w(an.b, String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{Integer.valueOf(50)}));
                replace = replace.substring(0, 50);
            }
            if (str2.length() > 500) {
                Log.w(an.b, String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{Integer.valueOf(200)}));
                str2 = str2.substring(0, 200);
            }
            a.a(context).c(replace, str2);
            an.b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!b.a) {
            Log.w(an.b, "Can not set 'isAppForeground' because bugly is disable.");
        } else if (context == null) {
            an.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                an.c("App is in foreground.", new Object[0]);
            } else {
                an.c("App is in background.", new Object[0]);
            }
            a.a(context).a(z);
        }
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!b.a) {
            Log.w(an.b, "Can not set 'isDevelopmentDevice' because bugly is disable.");
        } else if (context == null) {
            an.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                an.c("This is a development device.", new Object[0]);
            } else {
                an.c("This is not a development device.", new Object[0]);
            }
            a.a(context).D = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (b.a) {
            com.tencent.bugly.crashreport.biz.b.a(j);
        } else {
            Log.w(an.b, "Can not set 'SessionIntervalMills' because bugly is disable.");
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!b.a) {
            Log.w(an.b, "Can not set App version because bugly is disable.");
        } else if (context == null) {
            Log.w(an.b, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(an.b, "App version is null, will not set");
        } else {
            a.a(context).o = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppVersion(str);
            }
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!b.a) {
            Log.w(an.b, "Can not set App channel because Bugly is disable.");
        } else if (context == null) {
            Log.w(an.b, "setAppChannel args context should not be null");
        } else if (str == null) {
            Log.w(an.b, "App channel is null, will not set");
        } else {
            a.a(context).q = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppChannel(str);
            }
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!b.a) {
            Log.w(an.b, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(an.b, "setAppPackage args context should not be null");
        } else if (str == null) {
            Log.w(an.b, "App package is null, will not set");
        } else {
            a.a(context).d = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppPackage(str);
            }
        }
    }

    public static void setCrashFilter(String str) {
        if (b.a) {
            Log.w(an.b, "Set crash stack filter: " + str);
            c.m = str;
            return;
        }
        Log.w(an.b, "Can not set App package because bugly is disable.");
    }

    public static void setCrashRegularFilter(String str) {
        if (b.a) {
            Log.w(an.b, "Set crash stack filter: " + str);
            c.n = str;
            return;
        }
        Log.w(an.b, "Can not set App package because bugly is disable.");
    }

    public static void setBuglyDbName(String str) {
        if (b.a) {
            Log.i(an.b, "Set Bugly DB name: " + str);
            af.a = str;
            return;
        }
        Log.w(an.b, "Can not set DB name because bugly is disable.");
    }

    public static void setAuditEnable(Context context, boolean z) {
        if (!b.a) {
            Log.w(an.b, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(an.b, "setAppPackage args context should not be null");
        } else {
            Log.i(an.b, "Set audit enable: " + z);
            a.a(context).G = z;
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView != null) {
            return setJavascriptMonitor(new WebViewInterface() {
                public String getUrl() {
                    return webView.getUrl();
                }

                public void setJavaScriptEnabled(boolean z) {
                    WebSettings settings = webView.getSettings();
                    if (!settings.getJavaScriptEnabled()) {
                        settings.setJavaScriptEnabled(true);
                    }
                }

                public void loadUrl(String str) {
                    webView.loadUrl(str);
                }

                public void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                    webView.addJavascriptInterface(h5JavaScriptInterface, str);
                }

                public CharSequence getContentDescription() {
                    return webView.getContentDescription();
                }
            }, z, z2);
        }
        Log.w(an.b, "WebView is null.");
        return false;
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z) {
        return setJavascriptMonitor(webViewInterface, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z, boolean z2) {
        if (webViewInterface == null) {
            Log.w(an.b, "WebViewInterface is null.");
            return false;
        } else if (CrashModule.hasInitialized()) {
            an.a("Set Javascript exception monitor of webview.", new Object[0]);
            if (b.a) {
                an.c("URL of webview is %s", new Object[]{webViewInterface.getUrl()});
                if (z2 || VERSION.SDK_INT >= 19) {
                    an.a("Enable the javascript needed by webview monitor.", new Object[0]);
                    webViewInterface.setJavaScriptEnabled(true);
                    H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webViewInterface);
                    if (instance != null) {
                        an.a("Add a secure javascript interface to the webview.", new Object[0]);
                        webViewInterface.addJavascriptInterface(instance, "exceptionUploader");
                    }
                    if (z) {
                        an.a("Inject bugly.js(v%s) to the webview.", new Object[]{com.tencent.bugly.crashreport.crash.h5.b.b()});
                        String a = com.tencent.bugly.crashreport.crash.h5.b.a();
                        if (a == null) {
                            an.e("Failed to inject Bugly.js.", new Object[]{com.tencent.bugly.crashreport.crash.h5.b.b()});
                            return false;
                        }
                        webViewInterface.loadUrl("javascript:" + a);
                    }
                    return true;
                }
                an.e("This interface is only available for Android 4.4 or later.", new Object[0]);
                return false;
            }
            Log.w(an.b, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        } else {
            an.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
    }
}
