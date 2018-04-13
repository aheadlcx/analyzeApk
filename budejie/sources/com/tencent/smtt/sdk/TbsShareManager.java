package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import com.tencent.smtt.utils.k;
import com.umeng.analytics.social.d;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.commons.httpclient.HttpState;

public class TbsShareManager {
    private static Context a;
    private static boolean b;
    private static String c = null;
    private static int d = 0;
    private static String e = null;
    private static boolean f = false;
    private static boolean g = false;
    private static String h = null;
    private static boolean i = false;
    private static boolean j = false;

    static int a(Context context, boolean z) {
        b(context, z);
        return d;
    }

    static Context a(Context context, String str) {
        Context context2 = null;
        try {
            context2 = context.createPackageContext(str, 2);
        } catch (NameNotFoundException e) {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return context2;
    }

    static void a(Context context) {
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            a(context, tbsLinuxToolsJni, aj.a().h(context));
            tbsLinuxToolsJni.a(aj.a().i(context).getAbsolutePath(), "755");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            tbsLinuxToolsJni.a(file.getAbsolutePath(), "755");
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    if (file2.getAbsolutePath().indexOf(".so") > 0) {
                        tbsLinuxToolsJni.a(file2.getAbsolutePath(), "755");
                    } else {
                        tbsLinuxToolsJni.a(file2.getAbsolutePath(), "644");
                    }
                } else if (file2.isDirectory()) {
                    a(context, tbsLinuxToolsJni, file2);
                } else {
                    TbsLog.e("TbsShareManager", "unknown file type.", true);
                }
            }
        }
    }

    private static File b(Context context, String str) {
        File i = aj.a().i(context);
        if (i == null) {
            return null;
        }
        File file = new File(i, str);
        if (file != null && file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String b(Context context) {
        h(context);
        return c;
    }

    static boolean b(Context context, boolean z) {
        if (g(context)) {
            return true;
        }
        if (z) {
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        }
        return false;
    }

    static int c(Context context) {
        return a(context, true);
    }

    private static void c(Context context, boolean z) {
        Throwable th;
        OutputStream outputStream;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream2 = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        BufferedInputStream bufferedInputStream3;
        try {
            File b = b(context, "core_info");
            if (b == null) {
                if (null != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception e) {
                    }
                }
                if (null != null) {
                    try {
                        bufferedOutputStream2.close();
                        return;
                    } catch (Exception e2) {
                        return;
                    }
                }
                return;
            }
            bufferedInputStream3 = new BufferedInputStream(new FileInputStream(b));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream3);
                properties.setProperty("core_disabled", String.valueOf(false));
                if (z) {
                    String absolutePath = aj.a().h(context).getAbsolutePath();
                    String packageName = context.getApplicationContext().getPackageName();
                    int b2 = b.b(context);
                    properties.setProperty("core_packagename", packageName);
                    properties.setProperty("core_path", absolutePath);
                    properties.setProperty("app_version", String.valueOf(b2));
                }
                OutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(b));
                try {
                    properties.store(bufferedOutputStream3, null);
                    if (bufferedInputStream3 != null) {
                        try {
                            bufferedInputStream3.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (bufferedOutputStream3 != null) {
                        bufferedOutputStream3.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = bufferedOutputStream3;
                    if (bufferedInputStream3 != null) {
                        bufferedInputStream3.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedInputStream3 != null) {
                    bufferedInputStream3.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream3 = null;
            if (bufferedInputStream3 != null) {
                bufferedInputStream3.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw th;
        }
    }

    static Context d(Context context) {
        h(context);
        if (e == null) {
            return null;
        }
        Context a = a(context, e);
        return !aj.a().c(a) ? null : a;
    }

    static synchronized String e(Context context) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        Throwable th2;
        String str = null;
        synchronized (TbsShareManager.class) {
            BufferedInputStream bufferedInputStream2 = null;
            try {
                File b = b(context, "core_info");
                if (b == null) {
                    if (null != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e) {
                        }
                    }
                } else {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(b));
                    try {
                        Properties properties = new Properties();
                        properties.load(bufferedInputStream);
                        String property = properties.getProperty("core_packagename", "");
                        if (!"".equals(property)) {
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e2) {
                                }
                            }
                            str = property;
                        } else if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            th.printStackTrace();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            return str;
                        } catch (Throwable th4) {
                            th2 = th4;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e3) {
                                }
                            }
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th5) {
                bufferedInputStream = null;
                th2 = th5;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th2;
            }
        }
        return str;
    }

    static synchronized int f(Context context) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        int i = 0;
        synchronized (TbsShareManager.class) {
            BufferedInputStream bufferedInputStream2 = null;
            try {
                File b = b(context, "core_info");
                if (b == null) {
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(b));
                    try {
                        Properties properties = new Properties();
                        properties.load(bufferedInputStream);
                        String property = properties.getProperty("core_version", "");
                        if ("".equals(property)) {
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            i = Math.max(Integer.parseInt(property), 0);
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            th.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            i = -2;
                            return i;
                        } catch (Throwable th3) {
                            th = th3;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e222) {
                                    e222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        }
        return i;
    }

    public static int findCoreForThirdPartyApp(Context context) {
        l(context);
        TbsLog.i("TbsShareManager", "core_info mAvailableCoreVersion is " + d + " mAvailableCorePath is " + c + " mSrcPackageName is " + e);
        if (!(i(context) || j(context))) {
            d = 0;
            c = null;
            e = null;
            TbsLog.i("TbsShareManager", "core_info error checkCoreInfo is false and checkCoreInOthers is false ");
        }
        if (d > 0 && (QbSdk.a(context, d) || f)) {
            d = 0;
            c = null;
            e = null;
            TbsLog.i("TbsShareManager", "core_info error QbSdk.isX5Disabled ");
        }
        return d;
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        if (context == null || aj.a().a(context, null)) {
            return false;
        }
        int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO);
        if (sharedTbsCoreVersion <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, aj.a().h(a(context, TbsConfig.APP_DEMO)).getAbsolutePath(), "1");
        return true;
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        try {
            if (isThirdPartyApp(context)) {
                File i = aj.a().i(context);
                if (i != null) {
                    if (z) {
                        File file = new File(i, "core_info");
                        if (file != null && file.exists()) {
                            return;
                        }
                    }
                    for (String str : getCoreProviderAppList()) {
                        int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str);
                        if (sharedTbsCoreVersion > 0) {
                            c = aj.a().b(context, a(context, str)).getAbsolutePath();
                            e = str;
                            d = sharedTbsCoreVersion;
                            if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                writeProperties(context, Integer.toString(d), e, c, Integer.toString(b.b(context)));
                                return;
                            }
                            d = 0;
                            c = null;
                            e = null;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    static boolean g(Context context) {
        try {
            if (d == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (d == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, null, new Object[0]);
                return false;
            } else if (d != 0 && getSharedTbsCoreVersion(context, e) == d) {
                return true;
            } else {
                if (j(context)) {
                    return true;
                }
                TbsCoreLoadStat.getInstance().a(context, 418, new Throwable("mAvailableCoreVersion=" + d + "; mSrcPackageName=" + e + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, e) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
                c = null;
                d = 0;
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, null, new Object[0]);
                QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
                return false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, null, new Object[0]);
            return false;
        }
    }

    public static boolean getCoreDisabled() {
        return f;
    }

    public static boolean getCoreFormOwn() {
        return i;
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, "com.tencent.mm", "com.tencent.mobileqq", "com.qzone"};
    }

    public static long getHostCoreVersions(Context context) {
        long j = 0;
        for (String str : getCoreProviderAppList()) {
            if (str.equalsIgnoreCase("com.tencent.mm")) {
                j += ((long) getSharedTbsCoreVersion(context, str)) * 10000000000L;
            } else if (str.equalsIgnoreCase("com.tencent.mobileqq")) {
                j += ((long) getSharedTbsCoreVersion(context, str)) * 100000;
            } else if (str.equalsIgnoreCase("com.qzone")) {
                j += (long) getSharedTbsCoreVersion(context, str);
            }
        }
        return j;
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context a = a(context, str);
        return a != null ? aj.a().d(a) : 0;
    }

    static boolean h(Context context) {
        return b(context, true);
    }

    private static boolean i(Context context) {
        return e != null && d == getSharedTbsCoreVersion(context, e);
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
            if (a != null && a.equals(context.getApplicationContext())) {
                return b;
            }
            a = context.getApplicationContext();
            String packageName = a.getPackageName();
            for (Object equals : getCoreProviderAppList()) {
                if (packageName.equals(equals)) {
                    b = false;
                    return false;
                }
            }
            b = true;
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean j(Context context) {
        for (String str : getCoreProviderAppList()) {
            if (d > 0 && d == getSharedTbsCoreVersion(context, str)) {
                Context a = a(context, str);
                if (aj.a().c(context)) {
                    c = aj.a().b(context, a).getAbsolutePath();
                    e = str;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean k(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), "", "", Integer.toString(0));
        return true;
    }

    private static synchronized void l(Context context) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        synchronized (TbsShareManager.class) {
            if (!j) {
                BufferedInputStream bufferedInputStream2 = null;
                try {
                    File b = b(context, "core_info");
                    if (b != null) {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(b));
                        try {
                            Properties properties = new Properties();
                            properties.load(bufferedInputStream);
                            String property = properties.getProperty("core_version", "");
                            if (!"".equals(property)) {
                                d = Math.max(Integer.parseInt(property), 0);
                            }
                            property = properties.getProperty("core_packagename", "");
                            if (!"".equals(property)) {
                                e = property;
                            }
                            if (!(e == null || a == null)) {
                                if (e.equals(a.getPackageName())) {
                                    i = true;
                                } else {
                                    i = false;
                                }
                            }
                            property = properties.getProperty("core_path", "");
                            if (!"".equals(property)) {
                                c = property;
                            }
                            property = properties.getProperty("app_version", "");
                            if (!"".equals(property)) {
                                h = property;
                            }
                            f = Boolean.parseBoolean(properties.getProperty("core_disabled", HttpState.PREEMPTIVE_DEFAULT));
                            j = true;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                th.printStackTrace();
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                    } else if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            }
        }
    }

    public static synchronized void writeCoreInfoForThirdPartyApp(Context context, int i, boolean z) {
        synchronized (TbsShareManager.class) {
            if (i == 0) {
                k(context);
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-401);
            } else {
                int f = f(context);
                if (f < 0) {
                    TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-402);
                } else if (i == f) {
                    c(context, z);
                    TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-403);
                } else if (i < f) {
                    k(context);
                    TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(d.s);
                } else {
                    String[] coreProviderAppList = getCoreProviderAppList();
                    if (z) {
                        coreProviderAppList = new String[]{context.getApplicationContext().getPackageName()};
                    }
                    for (String str : r1) {
                        if (i == getSharedTbsCoreVersion(context, str)) {
                            Context a = a(context, str);
                            String absolutePath = aj.a().h(a).getAbsolutePath();
                            int b = b.b(context);
                            if (aj.a().c(a)) {
                                if (!str.equals(context.getApplicationContext().getPackageName())) {
                                    TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + i);
                                    try {
                                        k.b(aj.a().h(context));
                                        TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
                                    } catch (Throwable th) {
                                        th.printStackTrace();
                                    }
                                }
                                writeProperties(context, Integer.toString(i), str, absolutePath, Integer.toString(b));
                                try {
                                    File b2 = b(context, "core_info");
                                    if (!(g || b2 == null)) {
                                        TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(a);
                                        tbsLinuxToolsJni.a(b2.getAbsolutePath(), "644");
                                        tbsLinuxToolsJni.a(aj.a().i(context).getAbsolutePath(), "755");
                                        g = true;
                                    }
                                } catch (Throwable th2) {
                                    th2.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void writeProperties(Context context, String str, String str2, String str3, String str4) {
        Exception e;
        BufferedInputStream bufferedInputStream;
        OutputStream bufferedOutputStream;
        Throwable th;
        OutputStream outputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        int i = 0;
        BufferedInputStream bufferedInputStream2 = null;
        BufferedOutputStream bufferedOutputStream3 = null;
        try {
            File b = b(context, "core_info");
            if (b == null) {
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-405);
                if (null != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (null != null) {
                    try {
                        bufferedOutputStream3.close();
                        return;
                    } catch (Exception e3) {
                        e2 = e3;
                    }
                } else {
                    return;
                }
            }
            Properties properties;
            bufferedInputStream = new BufferedInputStream(new FileInputStream(b));
            try {
                properties = new Properties();
                properties.load(bufferedInputStream);
                try {
                    i = Integer.parseInt(str);
                } catch (Exception e4) {
                }
                if (i != 0) {
                    properties.setProperty("core_version", str);
                    properties.setProperty("core_disabled", String.valueOf(false));
                    properties.setProperty("core_packagename", str2);
                    properties.setProperty("core_path", str3);
                    properties.setProperty("app_version", str4);
                } else {
                    properties.setProperty("core_disabled", String.valueOf(true));
                }
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(b));
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw th;
            }
            try {
                properties.store(bufferedOutputStream, null);
                j = false;
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-406);
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (Exception e5) {
                        e22 = e5;
                        e22.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = bufferedOutputStream;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
            throw th;
        }
    }
}
