package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebIconDatabase;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import com.budejie.www.R$styleable;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.sdk.a.d;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.tencent.smtt.utils.v;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressLint({"NewApi"})
public class QbSdk {
    private static boolean A = false;
    private static boolean B = false;
    public static final int EXTENSION_INIT_FAILURE = -99999;
    public static final String LOGIN_TYPE_KEY_PARTNER_CALL_POS = "PosID";
    public static final String LOGIN_TYPE_KEY_PARTNER_ID = "ChannelID";
    public static final String PARAM_KEY_FEATUREID = "param_key_featureid";
    public static final String PARAM_KEY_FUNCTIONID = "param_key_functionid";
    public static final String PARAM_KEY_POSITIONID = "param_key_positionid";
    public static final int SVNVERSION = 446038;
    public static final String TID_QQNumber_Prefix = "QQ:";
    public static final int VERSION = 1;
    static boolean a = false;
    static boolean b = false;
    static boolean c = true;
    static String d;
    static boolean e = false;
    static long f = 0;
    static long g = 0;
    static Object h = new Object();
    static volatile boolean i = a;
    static TbsListener j = new j();
    static Map<String, Object> k = null;
    private static int l = 0;
    private static String m = "";
    private static Class<?> n;
    private static Object o;
    private static boolean p = false;
    private static String[] q;
    private static String r = "NULL";
    private static String s = "UNKNOWN";
    public static boolean sIsVersionPrinted = false;
    private static int t = 0;
    private static int u = R$styleable.Theme_Custom_label_subscribe_bg_gd_color;
    private static String v = null;
    private static String w = null;
    private static boolean x = true;
    private static TbsListener y = null;
    private static TbsListener z = null;

    public interface PreInitCallback {
        void onCoreInitFinished();

        void onViewInitFinished(boolean z);
    }

    static Bundle a(Context context, Bundle bundle) {
        if (a(context)) {
            Object a = v.a(o, "incrUpdate", new Class[]{Context.class, Bundle.class}, new Object[]{context, bundle});
            if (a != null) {
                return (Bundle) a;
            }
            TbsLogReport.a(context).a(216, "incrUpdate return null!");
            return null;
        }
        TbsLogReport.a(context).a(216, "initForPatch return false!");
        return null;
    }

    static Object a(Context context, String str, Bundle bundle) {
        if (!a(context)) {
            return Integer.valueOf(EXTENSION_INIT_FAILURE);
        }
        Object a = v.a(o, "miscCall", new Class[]{String.class, Bundle.class}, new Object[]{str, bundle});
        return a == null ? null : a;
    }

    static String a() {
        return m;
    }

    static synchronized void a(Context context, String str) {
        synchronized (QbSdk.class) {
            if (!a) {
                a = true;
                s = "forceSysWebViewInner: " + str;
                TbsLog.e("QbSdk", "QbSdk.SysWebViewForcedInner..." + s);
                TbsCoreLoadStat.getInstance().a(context, 401, new Throwable(s));
            }
        }
    }

    static boolean a(Context context) {
        try {
            if (n != null) {
                return true;
            }
            File h = aj.a().h(context);
            if (h == null) {
                TbsLog.e("QbSdk", "QbSdk initExtension (false) optDir == null");
                return false;
            }
            File file = new File(h, "tbs_sdk_extension_dex.jar");
            if (file.exists()) {
                n = new DexClassLoader(file.getAbsolutePath(), h.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                o = n.getConstructor(new Class[]{Context.class, Context.class}).newInstance(new Object[]{context, context});
                return true;
            }
            TbsLog.e("QbSdk", "QbSdk initExtension (false) dexFile.exists()=false", true);
            return false;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initExtension sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    static boolean a(Context context, int i) {
        return a(context, i, 20000);
    }

    static boolean a(Context context, int i, int i2) {
        if (!b(context)) {
            return true;
        }
        Object a = v.a(o, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, new Object[]{Integer.valueOf(i), Integer.valueOf(43100), Integer.valueOf(i2)});
        if (a != null) {
            return ((Boolean) a).booleanValue();
        }
        a = v.a(o, "isX5Disabled", new Class[]{Integer.TYPE, Integer.TYPE}, new Object[]{Integer.valueOf(i), Integer.valueOf(43100)});
        return a != null ? ((Boolean) a).booleanValue() : true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(Context context, boolean z) {
        TbsLog.initIfNeed(context);
        if (!sIsVersionPrinted) {
            TbsLog.i("QbSdk", "svn revision: 446038; SDK_VERSION_CODE: 43100; SDK_VERSION_NAME: 3.1.0.1034");
            sIsVersionPrinted = true;
        }
        if (a && !z) {
            TbsLog.e("QbSdk", "QbSdk init: " + s, false);
            TbsCoreLoadStat.getInstance().a(context, 414, new Throwable(s));
            return false;
        } else if (b) {
            TbsLog.e("QbSdk", "QbSdk init mIsSysWebViewForcedByOuter = true", true);
            TbsCoreLoadStat.getInstance().a(context, 402, new Throwable(r));
            return false;
        } else {
            if (!x) {
                c(context);
            }
            try {
                File h = aj.a().h(context);
                if (h == null) {
                    TbsLog.e("QbSdk", "QbSdk init (false) optDir == null");
                    TbsCoreLoadStat.getInstance().a(context, 312, new Throwable("QbSdk.init (false) TbsCoreShareDir is null"));
                    return false;
                }
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    int a;
                    if (l != 0) {
                        a = aj.a().a(true, context);
                        if (l != a) {
                            n = null;
                            o = null;
                            TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp tbsCoreInstalledVer=" + a, true);
                            TbsLog.e("QbSdk", "QbSdk init (false) not isThirdPartyApp sTbsVersion=" + l, true);
                            TbsCoreLoadStat.getInstance().a(context, 303, new Throwable("sTbsVersion: " + l + "; tbsCoreInstalledVer: " + a));
                            return false;
                        }
                    }
                    a = 0;
                    l = a;
                }
                if (n != null) {
                    return true;
                }
                File file;
                if (!TbsShareManager.isThirdPartyApp(context)) {
                    file = new File(aj.a().h(context), "tbs_sdk_extension_dex.jar");
                } else if (TbsShareManager.h(context)) {
                    file = new File(TbsShareManager.b(context), "tbs_sdk_extension_dex.jar");
                } else {
                    TbsCoreLoadStat.getInstance().a(context, 304, new Throwable("isShareTbsCoreAvailable false!"));
                    return false;
                }
                if (file.exists()) {
                    n = new DexClassLoader(file.getAbsolutePath(), h.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                    Constructor constructor = n.getConstructor(new Class[]{Context.class, Context.class});
                    if (TbsShareManager.isThirdPartyApp(context)) {
                        o = constructor.newInstance(new Object[]{context, TbsShareManager.d(context)});
                    } else {
                        o = constructor.newInstance(new Object[]{context, context});
                    }
                    v.a(o, "setClientVersion", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(1)});
                    return true;
                }
                TbsLog.e("QbSdk", "QbSdk init (false) tbs_sdk_extension_dex.jar is not exist!");
                int d = aj.a().d(context);
                if (new File(file.getParentFile(), "tbs_jars_fusion_dex.jar").exists()) {
                    if (d > 0) {
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + d));
                        return false;
                    }
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(with fusion dex)!" + d));
                    return false;
                } else if (d > 0) {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITH_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + d));
                    return false;
                } else {
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_INFO_MISS_SDKEXTENSION_JAR_WITHOUT_FUSION_DEX_WITHOUT_CORE, new Exception("tbs_sdk_extension_dex not exist(without fusion dex)!" + d));
                    return false;
                }
            } catch (Throwable th) {
                TbsLog.e("QbSdk", "QbSdk init Throwable: " + Log.getStackTraceString(th));
                TbsCoreLoadStat.getInstance().a(context, 306, th);
                return false;
            }
        }
    }

    static boolean a(Context context, boolean z, boolean z2) {
        int i = 1;
        boolean z3 = false;
        if (TbsShareManager.isThirdPartyApp(context) && !TbsShareManager.g(context)) {
            TbsCoreLoadStat.getInstance().a(context, 302);
        } else if (a(context, z)) {
            Object a = v.a(o, "canLoadX5Core", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(43100)});
            boolean a2;
            if (a == null) {
                a = v.a(o, "canLoadX5", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(a.a())});
                if (a == null) {
                    TbsCoreLoadStat.getInstance().a(context, 308);
                } else if (!((a instanceof String) && ((String) a).equalsIgnoreCase("AuthenticationFail"))) {
                    if (a instanceof Boolean) {
                        l = l.d();
                        a2 = a(context, l.d());
                        if (((Boolean) a).booleanValue() && !a2) {
                            z3 = true;
                        }
                        if (!z3) {
                            TbsLog.e(TbsListener.tag_load_error, "318");
                            TbsLog.w(TbsListener.tag_load_error, "isX5Disable:" + a2);
                            TbsLog.w(TbsListener.tag_load_error, "(Boolean) ret:" + ((Boolean) a));
                        }
                    }
                }
            } else if (!((a instanceof String) && ((String) a).equalsIgnoreCase("AuthenticationFail"))) {
                if (a instanceof Bundle) {
                    Bundle bundle = (Bundle) a;
                    if (bundle.isEmpty()) {
                        TbsCoreLoadStat.getInstance().a(context, 331, new Throwable("" + a));
                        TbsLog.e(TbsListener.tag_load_error, "empty bundle");
                    } else {
                        int i2;
                        try {
                            i2 = bundle.getInt("result_code", -1);
                        } catch (Exception e) {
                            TbsLog.e("QbSdk", "bundle.getInt(KEY_RESULT_CODE) error : " + e.toString());
                            i2 = -1;
                        }
                        a2 = i2 == 0;
                        if (TbsShareManager.isThirdPartyApp(context)) {
                            l.a(TbsShareManager.c(context));
                            m = String.valueOf(TbsShareManager.c(context));
                            if (m.length() == 5) {
                                m = "0" + m;
                            }
                            if (m.length() != 6) {
                                m = "";
                            }
                        } else {
                            if (VERSION.SDK_INT >= 12) {
                                m = bundle.getString("tbs_core_version", "0");
                            } else {
                                m = bundle.getString("tbs_core_version");
                                if (m == null) {
                                    m = "0";
                                }
                            }
                            try {
                                l = Integer.parseInt(m);
                            } catch (NumberFormatException e2) {
                                l = 0;
                            }
                            l.a(l);
                            if (l == 0) {
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sTbsVersion is 0"));
                            } else {
                                if ((l <= 0 || l > 25442) && l != 25472) {
                                    i = 0;
                                }
                                if (i != 0) {
                                    TbsLog.e(TbsDownloader.LOGTAG, "is_obsolete --> delete old core:" + l);
                                    k.b(aj.a().h(context));
                                    TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("is_obsolete --> delete old core:" + l));
                                }
                            }
                        }
                        try {
                            q = bundle.getStringArray("tbs_jarfiles");
                            if (q instanceof String[]) {
                                d = bundle.getString("tbs_librarypath");
                                a = null;
                                if (i2 != 0) {
                                    try {
                                        a = v.a(o, "getErrorCodeForLogReport", new Class[0], new Object[0]);
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                switch (i2) {
                                    case -2:
                                        if (!(a instanceof Integer)) {
                                            TbsCoreLoadStat.getInstance().a(context, 404, new Throwable("detail: " + a));
                                            break;
                                        }
                                        TbsCoreLoadStat.getInstance().a(context, ((Integer) a).intValue(), new Throwable("detail: " + a));
                                        break;
                                    case -1:
                                        if (!(a instanceof Integer)) {
                                            TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("detail: " + a));
                                            break;
                                        }
                                        TbsCoreLoadStat.getInstance().a(context, ((Integer) a).intValue(), new Throwable("detail: " + a));
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        TbsCoreLoadStat.getInstance().a(context, 415, new Throwable("detail: " + a + "errcode" + i2));
                                        break;
                                }
                                z3 = a2;
                            } else {
                                TbsCoreLoadStat.getInstance().a(context, 307, new Throwable("sJarFiles not instanceof String[]: " + q));
                            }
                        } catch (Throwable th) {
                            TbsCoreLoadStat.getInstance().a(context, 329, th);
                        }
                    }
                } else {
                    TbsCoreLoadStat.getInstance().a(context, 330, new Throwable("" + a));
                    TbsLog.e(TbsListener.tag_load_error, "ret not instance of bundle");
                }
            }
            if (!z3) {
                TbsLog.e(TbsListener.tag_load_error, "319");
            }
        } else {
            TbsLog.e("QbSdk", "QbSdk.init failure!");
        }
        return z3;
    }

    protected static String b() {
        bi b = bi.b();
        if (b != null && b.c()) {
            Object invokeStaticMethod = b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getGUID", new Class[0], new Object[0]);
            if (invokeStaticMethod != null && (invokeStaticMethod instanceof String)) {
                return (String) invokeStaticMethod;
            }
        }
        return null;
    }

    private static boolean b(Context context) {
        try {
            if (n != null) {
                return true;
            }
            File h = aj.a().h(context);
            if (h == null) {
                TbsLog.e("QbSdk", "QbSdk initForX5DisableConfig (false) optDir == null");
                return false;
            }
            File file;
            if (!TbsShareManager.isThirdPartyApp(context)) {
                file = new File(aj.a().h(context), "tbs_sdk_extension_dex.jar");
            } else if (TbsShareManager.h(context)) {
                file = new File(TbsShareManager.b(context), "tbs_sdk_extension_dex.jar");
            } else {
                TbsCoreLoadStat.getInstance().a(context, 304);
                return false;
            }
            if (file.exists()) {
                n = new DexClassLoader(file.getAbsolutePath(), h.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                Constructor constructor = n.getConstructor(new Class[]{Context.class, Context.class});
                if (TbsShareManager.isThirdPartyApp(context)) {
                    o = constructor.newInstance(new Object[]{context, TbsShareManager.d(context)});
                } else {
                    o = constructor.newInstance(new Object[]{context, context});
                }
                v.a(o, "setClientVersion", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(1)});
                return true;
            }
            TbsCoreLoadStat.getInstance().a(context, 406, new Exception("initForX5DisableConfig failure -- tbs_sdk_extension_dex.jar is not exist!"));
            return false;
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "initForX5DisableConfig sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    private static void c(Context context) {
        int i;
        Throwable th;
        Editor edit;
        File a;
        int i2 = -1;
        x = true;
        SharedPreferences sharedPreferences;
        int i3;
        int i4;
        try {
            sharedPreferences = VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0);
            try {
                i3 = sharedPreferences.getInt("tbs_preload_x5_recorder", -1);
                if (i3 >= 0) {
                    i3++;
                    if (i3 <= 4) {
                        i = i3;
                    } else {
                        return;
                    }
                }
                i = i3;
                i3 = -1;
            } catch (Throwable th2) {
                th = th2;
                i3 = -1;
                i4 = -1;
                TbsLog.e("QbSdk", "tbs_preload_x5_counter Inc exception:" + Log.getStackTraceString(th));
                i = -1;
                if (i <= 3) {
                    TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- before creation!");
                    bi.b().a(context, null);
                    TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- after creation!");
                    i2 = 0;
                    try {
                        i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
                        if (i > 0) {
                            sharedPreferences.edit().putInt("tbs_preload_x5_counter", i - 1).commit();
                        }
                    } catch (Throwable th3) {
                        TbsLog.e("QbSdk", "tbs_preload_x5_counter Dec exception:" + Log.getStackTraceString(th3));
                    }
                    TbsLog.i("QbSdk", "QbSdk -- preload_x5_check result:" + i2);
                }
                try {
                    i = sharedPreferences.getInt("tbs_preload_x5_version", -1);
                    edit = sharedPreferences.edit();
                    if (i != i4) {
                        TbsLog.e("QbSdk", "QbSdk - preload_x5_check -- reset exception core_ver:" + i4 + "; value:" + i);
                    } else {
                        k.a(aj.a().h(context), false);
                        a = ae.a(context).a();
                        if (a != null) {
                            k.a(a, false);
                        }
                        TbsLog.e("QbSdk", "QbSdk - preload_x5_check: tbs core " + i4 + " is deleted!");
                    }
                    edit.putInt("tbs_precheck_disable_version", i);
                    edit.commit();
                } catch (Throwable th32) {
                    TbsLog.e("QbSdk", "tbs_preload_x5_counter disable version exception:" + Log.getStackTraceString(th32));
                    return;
                }
            }
            try {
                i4 = aj.a().d(context);
                if (i4 > 0) {
                    if (i <= 4) {
                        try {
                            sharedPreferences.edit().putInt("tbs_preload_x5_recorder", i).commit();
                        } catch (Throwable th4) {
                            th32 = th4;
                            TbsLog.e("QbSdk", "tbs_preload_x5_counter Inc exception:" + Log.getStackTraceString(th32));
                            i = -1;
                            if (i <= 3) {
                                TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- before creation!");
                                bi.b().a(context, null);
                                TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- after creation!");
                                i2 = 0;
                                i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
                                if (i > 0) {
                                    sharedPreferences.edit().putInt("tbs_preload_x5_counter", i - 1).commit();
                                }
                                TbsLog.i("QbSdk", "QbSdk -- preload_x5_check result:" + i2);
                            }
                            i = sharedPreferences.getInt("tbs_preload_x5_version", -1);
                            edit = sharedPreferences.edit();
                            if (i != i4) {
                                TbsLog.e("QbSdk", "QbSdk - preload_x5_check -- reset exception core_ver:" + i4 + "; value:" + i);
                            } else {
                                k.a(aj.a().h(context), false);
                                a = ae.a(context).a();
                                if (a != null) {
                                    k.a(a, false);
                                }
                                TbsLog.e("QbSdk", "QbSdk - preload_x5_check: tbs core " + i4 + " is deleted!");
                            }
                            edit.putInt("tbs_precheck_disable_version", i);
                            edit.commit();
                        }
                    }
                    i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
                    if (i >= 0) {
                        i++;
                        sharedPreferences.edit().putInt("tbs_preload_x5_counter", i).commit();
                        if (i <= 3) {
                            i = sharedPreferences.getInt("tbs_preload_x5_version", -1);
                            edit = sharedPreferences.edit();
                            if (i != i4) {
                                k.a(aj.a().h(context), false);
                                a = ae.a(context).a();
                                if (a != null) {
                                    k.a(a, false);
                                }
                                TbsLog.e("QbSdk", "QbSdk - preload_x5_check: tbs core " + i4 + " is deleted!");
                            } else {
                                TbsLog.e("QbSdk", "QbSdk - preload_x5_check -- reset exception core_ver:" + i4 + "; value:" + i);
                            }
                            edit.putInt("tbs_precheck_disable_version", i);
                            edit.commit();
                        }
                        if (i3 > 0 && i3 <= 3) {
                            TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- before creation!");
                            bi.b().a(context, null);
                            TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- after creation!");
                            i2 = 0;
                        }
                        i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
                        if (i > 0) {
                            sharedPreferences.edit().putInt("tbs_preload_x5_counter", i - 1).commit();
                        }
                        TbsLog.i("QbSdk", "QbSdk -- preload_x5_check result:" + i2);
                    }
                    i = -1;
                    if (i <= 3) {
                        TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- before creation!");
                        bi.b().a(context, null);
                        TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- after creation!");
                        i2 = 0;
                        i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
                        if (i > 0) {
                            sharedPreferences.edit().putInt("tbs_preload_x5_counter", i - 1).commit();
                        }
                        TbsLog.i("QbSdk", "QbSdk -- preload_x5_check result:" + i2);
                    }
                    i = sharedPreferences.getInt("tbs_preload_x5_version", -1);
                    edit = sharedPreferences.edit();
                    if (i != i4) {
                        TbsLog.e("QbSdk", "QbSdk - preload_x5_check -- reset exception core_ver:" + i4 + "; value:" + i);
                    } else {
                        k.a(aj.a().h(context), false);
                        a = ae.a(context).a();
                        if (a != null) {
                            k.a(a, false);
                        }
                        TbsLog.e("QbSdk", "QbSdk - preload_x5_check: tbs core " + i4 + " is deleted!");
                    }
                    edit.putInt("tbs_precheck_disable_version", i);
                    edit.commit();
                }
            } catch (Throwable th5) {
                th32 = th5;
                i4 = -1;
                TbsLog.e("QbSdk", "tbs_preload_x5_counter Inc exception:" + Log.getStackTraceString(th32));
                i = -1;
                if (i <= 3) {
                    i = sharedPreferences.getInt("tbs_preload_x5_version", -1);
                    edit = sharedPreferences.edit();
                    if (i != i4) {
                        k.a(aj.a().h(context), false);
                        a = ae.a(context).a();
                        if (a != null) {
                            k.a(a, false);
                        }
                        TbsLog.e("QbSdk", "QbSdk - preload_x5_check: tbs core " + i4 + " is deleted!");
                    } else {
                        TbsLog.e("QbSdk", "QbSdk - preload_x5_check -- reset exception core_ver:" + i4 + "; value:" + i);
                    }
                    edit.putInt("tbs_precheck_disable_version", i);
                    edit.commit();
                }
                TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- before creation!");
                bi.b().a(context, null);
                TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- after creation!");
                i2 = 0;
                i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
                if (i > 0) {
                    sharedPreferences.edit().putInt("tbs_preload_x5_counter", i - 1).commit();
                }
                TbsLog.i("QbSdk", "QbSdk -- preload_x5_check result:" + i2);
            }
        } catch (Throwable th6) {
            th32 = th6;
            i3 = -1;
            sharedPreferences = null;
            i4 = -1;
            TbsLog.e("QbSdk", "tbs_preload_x5_counter Inc exception:" + Log.getStackTraceString(th32));
            i = -1;
            if (i <= 3) {
                i = sharedPreferences.getInt("tbs_preload_x5_version", -1);
                edit = sharedPreferences.edit();
                if (i != i4) {
                    k.a(aj.a().h(context), false);
                    a = ae.a(context).a();
                    if (a != null) {
                        k.a(a, false);
                    }
                    TbsLog.e("QbSdk", "QbSdk - preload_x5_check: tbs core " + i4 + " is deleted!");
                } else {
                    TbsLog.e("QbSdk", "QbSdk - preload_x5_check -- reset exception core_ver:" + i4 + "; value:" + i);
                }
                edit.putInt("tbs_precheck_disable_version", i);
                edit.commit();
            }
            TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- before creation!");
            bi.b().a(context, null);
            TbsLog.i("QbSdk", "QbSdk - preload_x5_check -- after creation!");
            i2 = 0;
            i = sharedPreferences.getInt("tbs_preload_x5_counter", -1);
            if (i > 0) {
                sharedPreferences.edit().putInt("tbs_preload_x5_counter", i - 1).commit();
            }
            TbsLog.i("QbSdk", "QbSdk -- preload_x5_check result:" + i2);
        }
    }

    public static boolean canLoadVideo(Context context) {
        Object a = v.a(o, "canLoadVideo", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(0)});
        if (a == null) {
            TbsCoreLoadStat.getInstance().a(context, 314);
        } else if (!((Boolean) a).booleanValue()) {
            TbsCoreLoadStat.getInstance().a(context, 313);
        }
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    public static boolean canLoadX5(Context context) {
        return a(context, false, false);
    }

    public static boolean canLoadX5FirstTimeThirdApp(Context context) {
        try {
            if (n == null) {
                File h = aj.a().h(context);
                if (h == null) {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) optDir == null");
                    return false;
                }
                File file = new File(TbsShareManager.b(context), "tbs_sdk_extension_dex.jar");
                if (file.exists()) {
                    n = new DexClassLoader(file.getAbsolutePath(), h.getAbsolutePath(), file.getAbsolutePath(), QbSdk.class.getClassLoader()).loadClass("com.tencent.tbs.sdk.extension.TbsSDKExtension");
                } else {
                    TbsLog.e("QbSdk", "QbSdk canLoadX5FirstTimeThirdApp (false) dexFile.exists()=false", true);
                    return false;
                }
            }
            if (o == null) {
                o = n.getConstructor(new Class[]{Context.class, Context.class}).newInstance(new Object[]{context, context});
            }
            Object a = v.a(o, "canLoadX5CoreForThirdApp", new Class[0], new Object[0]);
            return (a == null || !(a instanceof Boolean)) ? false : ((Boolean) a).booleanValue();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "canLoadX5FirstTimeThirdApp sys WebView: " + Log.getStackTraceString(th));
            return false;
        }
    }

    public static void canOpenFile(Context context, String str, ValueCallback<Boolean> valueCallback) {
        new d(context, str, valueCallback).start();
    }

    public static boolean canOpenMimeFileType(Context context, String str) {
        return !a(context, false) ? false : false;
    }

    public static boolean canOpenWebPlus(Context context) {
        BufferedInputStream bufferedInputStream;
        InputStream fileInputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream2;
        InputStream inputStream = null;
        boolean z = true;
        if (t == 0) {
            t = a.a();
        }
        TbsLog.i("QbSdk", "canOpenWebPlus - totalRAM: " + t);
        if (VERSION.SDK_INT < 7 || t < u || context == null) {
            return false;
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(aj.a().h(context), "tbs.conf")));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                String property = properties.getProperty("android_sdk_max_supported");
                String property2 = properties.getProperty("android_sdk_min_supported");
                int parseInt = Integer.parseInt(property);
                int parseInt2 = Integer.parseInt(property2);
                int parseInt3 = Integer.parseInt(VERSION.SDK);
                if (parseInt3 > parseInt || parseInt3 < parseInt2) {
                    TbsLog.i("QbSdk", "canOpenWebPlus - sdkVersion: " + parseInt3);
                    if (bufferedInputStream == null) {
                        return false;
                    }
                    try {
                        bufferedInputStream.close();
                        return false;
                    } catch (Exception e) {
                        return false;
                    }
                }
                boolean z2;
                int parseInt4 = Integer.parseInt(properties.getProperty("tbs_core_version"));
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e2) {
                    }
                }
                try {
                    fileInputStream = new FileInputStream(new File(aj.j(context), "tbs_extension.conf"));
                    try {
                        Properties properties2 = new Properties();
                        properties2.load(fileInputStream);
                        parseInt = Integer.parseInt(properties2.getProperty("tbs_local_version"));
                        parseInt2 = Integer.parseInt(properties2.getProperty(TbsConfigKey.KEY_APP_VERSIONCODE_FOR_SWITCH));
                        if (parseInt4 == 88888888 || parseInt == 88888888) {
                            z2 = false;
                        } else if (parseInt4 > parseInt) {
                            z2 = false;
                        } else if (parseInt4 == parseInt) {
                            if (parseInt2 > 0) {
                                if (parseInt2 != b.b(context)) {
                                    z2 = false;
                                }
                            }
                            z2 = Boolean.parseBoolean(properties2.getProperty("x5_disabled")) && !TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getBoolean(TbsConfigKey.KEY_SWITCH_BACKUPCORE_ENABLE, false);
                        } else {
                            z2 = false;
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e3) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
                if (z2) {
                    z = false;
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            throw th;
        }
    }

    public static boolean canUseVideoFeatrue(Context context, int i) {
        Object a = v.a(o, "canUseVideoFeatrue", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
        return (a == null || !(a instanceof Boolean)) ? false : ((Boolean) a).booleanValue();
    }

    public static void clear(Context context) {
    }

    public static void clearAllWebViewCache(Context context, boolean z) {
        try {
            WebView webView = new WebView(context);
            if (VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.clearCache(true);
            if (z) {
                CookieSyncManager.createInstance(context);
                CookieManager.getInstance().removeAllCookie();
            }
            WebViewDatabase.getInstance(context).clearUsernamePassword();
            WebViewDatabase.getInstance(context).clearHttpAuthUsernamePassword();
            WebViewDatabase.getInstance(context).clearFormData();
            WebStorage.getInstance().deleteAllData();
            WebIconDatabase.getInstance().removeAllIcons();
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "clearAllWebViewCache exception 1 -- " + Log.getStackTraceString(th));
        }
        try {
            if (new WebView(context).getWebViewClientExtension() != null) {
                bi b = bi.b();
                if (b != null && b.c()) {
                    b.d().a(context, z);
                }
            }
        } catch (Throwable th2) {
        }
    }

    public static void closeFileReader(Context context) {
        bi b = bi.b();
        b.a(context, null);
        if (b.c()) {
            b.d().o();
        }
    }

    public static boolean createMiniQBShortCut(Context context, String str, String str2, Drawable drawable) {
        if (context == null) {
            return false;
        }
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        if (isMiniQBShortCutExist(context, str, str2)) {
            return false;
        }
        bi b = bi.b();
        if (b == null || !b.c()) {
            return false;
        }
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        DexLoader b2 = b.d().b();
        TbsLog.e("QbSdk", "qbsdk createMiniQBShortCut");
        Object invokeStaticMethod = b2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createMiniQBShortCut", new Class[]{Context.class, String.class, String.class, Bitmap.class}, new Object[]{context, str, str2, bitmap});
        TbsLog.e("QbSdk", "qbsdk after createMiniQBShortCut ret: " + invokeStaticMethod);
        return invokeStaticMethod != null;
    }

    public static boolean deleteMiniQBShortCut(Context context, String str, String str2) {
        if (context == null || TbsDownloader.getOverSea(context)) {
            return false;
        }
        bi b = bi.b();
        if (b == null || !b.c()) {
            return false;
        }
        return b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "deleteMiniQBShortCut", new Class[]{Context.class, String.class, String.class}, new Object[]{context, str, str2}) != null;
    }

    public static void forceSysWebView() {
        b = true;
        r = "SysWebViewForcedByOuter: " + Log.getStackTraceString(new Throwable());
        TbsLog.e("QbSdk", "sys WebView: SysWebViewForcedByOuter");
    }

    public static long getApkFileSize(Context context) {
        return context != null ? TbsDownloadConfig.getInstance(context.getApplicationContext()).mPreferences.getLong(TbsConfigKey.KEY_TBSAPKFILESIZE, 0) : 0;
    }

    public static String[] getDexLoaderFileList(Context context, Context context2, String str) {
        int i = 0;
        if (q instanceof String[]) {
            int length = q.length;
            String[] strArr = new String[length];
            while (i < length) {
                strArr[i] = str + q[i];
                i++;
            }
            return strArr;
        }
        Object a = v.a(o, "getJarFiles", new Class[]{Context.class, Context.class, String.class}, new Object[]{context, context2, str});
        if (!(a instanceof String[])) {
            a = new String[]{""};
        }
        return (String[]) a;
    }

    public static boolean getDownloadWithoutWifi() {
        return A;
    }

    public static String getMiniQBVersion(Context context) {
        bi b = bi.b();
        b.a(context, null);
        return (b == null || !b.c()) ? null : b.d().f();
    }

    public static String getQQBuildNumber() {
        return w;
    }

    public static boolean getTBSInstalling() {
        return B;
    }

    public static String getTID() {
        return v;
    }

    public static int getTbsVersion(Context context) {
        if (TbsShareManager.isThirdPartyApp(context)) {
            return TbsShareManager.a(context, false);
        }
        int d = aj.a().d(context);
        if (d != 0 || ae.a(context).c() != 3) {
            return d;
        }
        reset(context);
        return d;
    }

    public static void initTbsSettings(Map<String, Object> map) {
        if (k == null) {
            k = map;
            return;
        }
        try {
            k.putAll(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initX5Environment(Context context, PreInitCallback preInitCallback) {
        z = new h(context, preInitCallback);
        if (TbsShareManager.isThirdPartyApp(context)) {
            aj.a().b(context, true);
        }
        TbsDownloader.needDownload(context, false, false, new i(context, preInitCallback));
    }

    public static boolean installLocalQbApk(Context context, String str, String str2, Bundle bundle) {
        l a = l.a(true);
        a.a(context, false, false, null);
        return (a == null || !a.b()) ? false : a.a().a(context, str, str2, bundle);
    }

    public static boolean intentDispatch(WebView webView, Intent intent, String str, String str2) {
        if (webView == null) {
            return false;
        }
        if (str.startsWith("mttbrowser://miniqb/ch=icon?")) {
            Context context = webView.getContext();
            int indexOf = str.indexOf("url=");
            String substring = indexOf > 0 ? str.substring(indexOf + 4) : null;
            HashMap hashMap = new HashMap();
            Object obj = "unknown";
            try {
                obj = context.getApplicationInfo().packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, obj);
            hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, "14004");
            if (d.a(context, "miniqb://home".equals(substring) ? "qb://navicard/addCard?cardId=168&cardName=168" : substring, hashMap, "QbSdk.startMiniQBToLoadUrl", null) != 0) {
                bi b = bi.b();
                if (b != null && b.c() && b.d().a(context, substring, null, str2, null) == 0) {
                    return true;
                }
                webView.loadUrl(substring);
            }
        } else {
            webView.loadUrl(str);
        }
        return false;
    }

    public static boolean isMiniQBShortCutExist(Context context, String str, String str2) {
        if (context == null) {
            return false;
        }
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        bi b = bi.b();
        if (b == null || !b.c()) {
            return false;
        }
        Object invokeStaticMethod = b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "isMiniQBShortCutExist", new Class[]{Context.class, String.class}, new Object[]{context, str});
        if (invokeStaticMethod == null) {
            return false;
        }
        return (invokeStaticMethod instanceof Boolean ? (Boolean) invokeStaticMethod : Boolean.valueOf(false)).booleanValue();
    }

    public static boolean isTbsCoreInited() {
        l a = l.a(false);
        return a != null && a.g();
    }

    public static boolean isX5DisabledSync(Context context) {
        if (ae.a(context).c() == 2) {
            int i = 1;
        } else {
            boolean z = false;
        }
        if (i != 0) {
            return false;
        }
        if (!b(context)) {
            return true;
        }
        i = aj.a().d(context);
        Object a = v.a(o, "isX5DisabledSync", new Class[]{Integer.TYPE, Integer.TYPE}, new Object[]{Integer.valueOf(i), Integer.valueOf(43100)});
        return a != null ? ((Boolean) a).booleanValue() : true;
    }

    public static synchronized void preInit(Context context) {
        synchronized (QbSdk.class) {
            preInit(context, null);
        }
    }

    public static synchronized void preInit(Context context, PreInitCallback preInitCallback) {
        synchronized (QbSdk.class) {
            TbsLog.initIfNeed(context);
            i = a;
            ai aiVar = new ai();
            aiVar.a("init_all", (byte) 1);
            if (!p) {
                Thread gVar = new g(context, aiVar, new f(Looper.getMainLooper(), preInitCallback, context, aiVar));
                gVar.setName("tbs_preinit");
                gVar.setPriority(10);
                gVar.start();
                p = true;
            }
        }
    }

    public static void reset(Context context) {
        TbsLog.e("QbSdk", "QbSdk reset!", true);
        try {
            TbsDownloader.stopDownload();
            TbsDownloader.b(context);
            k.b(context.getDir("tbs", 0));
            TbsLog.i("QbSdk", "delete downloaded apk success", true);
            aj.a.set(Integer.valueOf(0));
        } catch (Throwable th) {
            TbsLog.e("QbSdk", "QbSdk reset exception:" + Log.getStackTraceString(th));
        }
    }

    public static void setCurrentID(String str) {
        if (str != null && str.startsWith(TID_QQNumber_Prefix)) {
            String substring = str.substring(TID_QQNumber_Prefix.length());
            v = "0000000000000000".substring(substring.length()) + substring;
        }
    }

    public static void setDownloadWithoutWifi(boolean z) {
        A = z;
    }

    public static void setQQBuildNumber(String str) {
        w = str;
    }

    public static void setTBSInstallingStatus(boolean z) {
        B = z;
    }

    public static void setTbsListener(TbsListener tbsListener) {
        y = tbsListener;
    }

    public static void setTbsLogClient(TbsLogClient tbsLogClient) {
        TbsLog.setTbsLogClient(tbsLogClient);
    }

    public static int startMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        TbsCoreLoadStat.getInstance().a(context, 501);
        if (context == null) {
            return -100;
        }
        bi b = bi.b();
        b.a(context, null);
        if (!b.c()) {
            TbsCoreLoadStat.getInstance().a(context, 502);
            return -102;
        } else if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
            return -101;
        } else {
            int a = b.d().a(context, str, hashMap, null, valueCallback);
            if (a == 0) {
                TbsCoreLoadStat.getInstance().a(context, 503);
                return a;
            }
            TbsLogReport.a(context).b(504, "" + a);
            return a;
        }
    }

    public static boolean startQBForDoc(Context context, String str, int i, int i2, String str2, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationContext().getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return d.a(context, str, i2, str2, hashMap, bundle);
    }

    public static boolean startQBForVideo(Context context, String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        return d.a(context, str, hashMap);
    }

    public static boolean startQBToLoadurl(Context context, String str, int i, WebView webView) {
        HashMap hashMap = new HashMap();
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_ID, context.getApplicationInfo().processName);
        hashMap.put(LOGIN_TYPE_KEY_PARTNER_CALL_POS, Integer.toString(i));
        if (webView == null) {
            try {
                String str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
                if (str2 == "com.tencent.mm" || str2 == "com.tencent.mobileqq") {
                    bi b = bi.b();
                    if (b != null && b.c()) {
                        Object invokeStaticMethod = b.d().b().invokeStaticMethod("com.tencent.smtt.webkit.WebViewList", "getCurrentMainWebviewJustForQQandWechat", new Class[0], new Object[0]);
                        if (invokeStaticMethod != null) {
                            IX5WebViewBase iX5WebViewBase = (IX5WebViewBase) invokeStaticMethod;
                            if (iX5WebViewBase != null) {
                                webView = (WebView) iX5WebViewBase.getView().getParent();
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return d.a(context, str, hashMap, "QbSdk.startQBToLoadurl", webView) == 0;
    }

    public static boolean startQbOrMiniQBToLoadUrl(Context context, String str, HashMap<String, String> hashMap, ValueCallback<String> valueCallback) {
        if (context == null) {
            return false;
        }
        bi b = bi.b();
        b.a(context, null);
        String str2 = "QbSdk.startMiniQBToLoadUrl";
        if (hashMap != null && "5".equals(hashMap.get(LOGIN_TYPE_KEY_PARTNER_CALL_POS)) && b.c() && ((Bundle) b.d().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getAdWebViewInfoFromX5Core", new Class[0], new Object[0])) == null) {
        }
        if (d.a(context, str, hashMap, str2, null) == 0) {
            return true;
        }
        if (b.c()) {
            if (context != null && context.getApplicationInfo().packageName.equals("com.nd.android.pandahome2") && getTbsVersion(context) < 25487) {
                return false;
            }
            if (b.d().a(context, str, hashMap, null, valueCallback) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void unForceSysWebView() {
        b = false;
        TbsLog.e("QbSdk", "sys WebView: unForceSysWebView called");
    }

    public static boolean useSoftWare() {
        if (o == null) {
            return false;
        }
        Object a = v.a(o, "useSoftWare", new Class[0], new Object[0]);
        if (a == null) {
            a = v.a(o, "useSoftWare", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(a.a())});
        }
        return a == null ? false : ((Boolean) a).booleanValue();
    }
}
