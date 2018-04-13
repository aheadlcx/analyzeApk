package com.ixintui.pushsdk.a;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.ixintui.plugin.IPushSdkApi;
import com.ixintui.pushsdk.SdkConstants;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Locale;
import javax.crypto.Cipher;

public class a {
    private static ClassLoader a;

    private static PublicKey b() {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDbMQJogEyynCkiQJ2EOH7v8mPL\nskclL0XonCy0rwdi+B5OuNTBO6vO4xaHMw2bmTET+lVvZ21BZB4DlCd7scVMRQQ4\n2CfE8BjqmSrBj9uV5q3jJbyAiLJdCcEmNtbfCbVgbXTl+nJvuZBP7Z6yPFKc7/RJ\nYEZ4/mpqJBduUHjkWQIDAQAB\n", 0)));
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    private static SharedPreferences d(Context context) {
        if (context != null) {
            return context.getSharedPreferences("com.ixintui.data.version", 0);
        }
        throw new InvalidParameterException();
    }

    public static String a(Context context) {
        String str = null;
        try {
            str = d(context).getString("version_file", null);
        } catch (Exception e) {
            a(e);
        }
        return str;
    }

    public static synchronized boolean a() {
        boolean z = false;
        synchronized (a.class) {
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    z = new File(Environment.getExternalStorageDirectory(), "ixintui/log_flag.txt").exists();
                }
            } catch (Exception e) {
            }
        }
        return z;
    }

    public static String b(Context context) {
        String str = null;
        try {
            str = d(context).getString(Config.SIGN, null);
        } catch (Exception e) {
            a(e);
        }
        return str;
    }

    private static boolean f(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(packageName, "com.ixintui.push.PushService"), 128);
            if (serviceInfo.exported && !serviceInfo.processName.equals(packageName)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint({"GetInstance"})
    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, b());
            return new String(instance.doFinal(bArr), "UTF-8");
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    public static void a(Exception exception) {
        if (a()) {
            try {
                OutputStream a = a("ixintui/log.txt");
                if (a != null) {
                    PrintStream printStream = new PrintStream(a);
                    exception.printStackTrace(printStream);
                    printStream.close();
                }
            } catch (Throwable th) {
                th.getMessage();
            }
        }
    }

    private static boolean g(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(packageName, "com.ixintui.push.MediateService"), 128);
            if (serviceInfo.exported && serviceInfo.processName.equals(packageName)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static FileOutputStream a(String str) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return new FileOutputStream(file, true);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Object a(Context context, String str) {
        Object obj = null;
        try {
            Class c = c(context, str);
            if (c != null) {
                obj = c.newInstance();
            }
        } catch (Exception e) {
            a(e);
        }
        return obj;
    }

    private static boolean a(String str, String str2, PackageManager packageManager) {
        Intent intent = new Intent(str);
        if (str2 != null) {
            intent.setPackage(str2);
        }
        return !packageManager.queryBroadcastReceivers(intent, 64).isEmpty();
    }

    private static synchronized void b(String str, String str2) {
        synchronized (a.class) {
            a("ixintui/log.txt", str, str2);
        }
    }

    public static Object a(Object obj, String str, Class[] clsArr, Object[] objArr) {
        Object obj2 = null;
        if (obj != null) {
            try {
                obj2 = obj.getClass().getMethod(str, null).invoke(obj, null);
            } catch (Exception e) {
                a(e);
            }
        }
        return obj2;
    }

    private static synchronized void a(String str, String str2, String str3) {
        synchronized (a.class) {
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    OutputStream a = a(str);
                    if (a != null) {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(a));
                        bufferedWriter.write(c() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2 + " \t" + str3 + "\r\n");
                        bufferedWriter.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean a(String str, String str2, String str3, String str4, PackageManager packageManager) {
        Intent intent = new Intent(str);
        if (str2 != null) {
            intent.setData(Uri.parse(str2 + "://"));
        }
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 64)) {
            if (resolveInfo.activityInfo.packageName.equals(str3) && resolveInfo.activityInfo.name.equals(str4)) {
                return true;
            }
        }
        return false;
    }

    private static ClassLoader b(Context context, String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        try {
            ClassLoader classLoader;
            ClassLoader dexClassLoader = new DexClassLoader(str, context.getCacheDir().getAbsolutePath(), null, context.getClassLoader());
            if (a(dexClassLoader)) {
                Class loadClass = dexClassLoader.loadClass("com.ixintui.pushsdk.PushSdkApiImpl");
                if (loadClass == null) {
                    classLoader = null;
                } else if (((IPushSdkApi) loadClass.newInstance()).isWrapperCompatible(context, 17009)) {
                    classLoader = dexClassLoader;
                } else {
                    classLoader = null;
                }
            } else {
                classLoader = null;
            }
            return classLoader;
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    private static boolean h(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        String str = "com.ixintui.push.Receiver";
        try {
            ActivityInfo receiverInfo = packageManager.getReceiverInfo(new ComponentName(packageName, str), 128);
            if (receiverInfo.exported && receiverInfo.processName.equals(packageName) && a("com.ixintui.action.BROADCAST", packageName, packageManager) && a("android.intent.action.PACKAGE_REMOVED", "package", packageName, str, packageManager) && a("android.intent.action.BOOT_COMPLETED", null, packageName, str, packageManager) && a("android.net.conn.CONNECTIVITY_CHANGE", null, packageName, str, packageManager)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static String c() {
        try {
            Calendar instance = Calendar.getInstance();
            return String.format(Locale.getDefault(), "%d-%02d-%02d %02d:%02d:%02d.%03d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13)), Integer.valueOf(instance.get(14))});
        } catch (Exception e) {
            return "unknown time";
        }
    }

    private static boolean a(ClassLoader classLoader) {
        try {
            if (classLoader.loadClass("com.ixintui.pushsdk.PushSdkApiImpl") == null || classLoader.loadClass("com.ixintui.push.PushServiceImpl") == null || classLoader.loadClass("com.ixintui.push.PushReceiverImpl") == null || classLoader.loadClass("com.ixintui.push.PushProviderImpl") == null || classLoader.loadClass("com.ixintui.push.PushActivityImpl") == null || classLoader.loadClass("com.ixintui.push.MediateServiceImpl") == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a(e);
            return false;
        }
    }

    private static boolean i(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(packageName + ".ixintui.push.provider", 0);
            if (!resolveContentProvider.exported && resolveContentProvider.multiprocess && resolveContentProvider.authority.equals(packageName + ".ixintui.push.provider")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean j(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context.getPackageName(), "com.ixintui.push.PushActivity");
        try {
            if (packageManager.getActivityInfo(componentName, 128).theme != 16973840) {
                return false;
            }
            Intent intent = new Intent("com.ixintui.push.PushActivity");
            intent.setComponent(componentName);
            if (packageManager.resolveActivity(intent, 65536).activityInfo.name.equals("com.ixintui.push.PushActivity")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            String a = a(Base64.decode(str2, 0));
            String a2 = a(new FileInputStream(new File(str)));
            if (a == null || !a.equalsIgnoreCase(a2)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static ClassLoader e(Context context) {
        if (a == null) {
            String a = a(context);
            ClassLoader classLoader = null;
            if (a(a, b(context))) {
                ClassLoader b = b(context, a);
                if (b == null) {
                    try {
                        d(context).edit().remove(Config.SIGN).remove("version_file").remove("version_code").commit();
                        classLoader = b;
                    } catch (Exception e) {
                        a(e);
                    }
                }
                classLoader = b;
            }
            if (classLoader == null) {
                File fileStreamPath = context.getFileStreamPath("ixintui_plugin.jar");
                String absolutePath = (fileStreamPath.exists() && a(context, fileStreamPath)) ? fileStreamPath.getAbsolutePath() : b(context, fileStreamPath);
                classLoader = b(context, absolutePath);
            }
            a = classLoader;
        }
        return a;
    }

    private static boolean k(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("ixintui_plugin/libixintui");
        } catch (Exception e) {
        }
        if (inputStream == null) {
            return false;
        }
        try {
            if (context.getResources().getIdentifier("ixintui_push_download", "layout", context.getPackageName()) == 0 || context.getResources().getIdentifier("ixintui_custom_notification", "layout", context.getPackageName()) == 0) {
                return false;
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private static Class c(Context context, String str) {
        Class cls = null;
        ClassLoader e = e(context);
        if (e == null) {
            String str2 = "loadClass";
            String str3 = "failed to load plugin!";
            String str4 = "Process id: " + Process.myPid() + " Thread id: " + Thread.currentThread().getId() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            new StringBuilder().append(str4).append(str3);
            if (a()) {
                b(str2, str4 + str3);
            }
        } else {
            try {
                cls = e.loadClass(str);
            } catch (Exception e2) {
                a(e2);
            }
        }
        return cls;
    }

    public static String c(Context context) {
        if (!f(context)) {
            return "com.ixintui.push.PushService manifest setting error, please refer to ixintui sample or document";
        }
        if (!g(context)) {
            return "com.ixintui.push.MediateService manifest setting error, please refer to ixintui sample or ducoment";
        }
        if (!h(context)) {
            return "com.ixintui.push.Receiver manifest setting error, please refer to ixintui sample or document";
        }
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Object obj = (a(SdkConstants.MESSAGE_ACTION, packageName, packageManager) && a(SdkConstants.RESULT_ACTION, packageName, packageManager) && a(SdkConstants.NOTIFICATION_CLICK_ACTION, packageName, packageManager)) ? 1 : null;
        if (obj == null) {
            return "You should define client receiver to receive ixintui broadcast, please refer to ixintui sample or document";
        }
        if (!i(context)) {
            return "com.ixintui.push.Provider manifest setting error, please check authority or other setting";
        }
        if (!j(context)) {
            return "com.ixintui.push.PushActivity manifest setting error, please refer to ixintui sample or document";
        }
        if (k(context)) {
            return "ixintui push sdk integration ok";
        }
        return "asset file access error or missing layout files, please check your project";
    }

    private static String a(InputStream inputStream) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, instance);
            do {
            } while (digestInputStream.read(new byte[8192], 0, 8192) != -1);
            digestInputStream.close();
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    private static boolean a(Context context, File file) {
        try {
            String a = a(new FileInputStream(file));
            String a2 = a(context.getAssets().open("ixintui_plugin/ixintui_plugin.jar"));
            if (a2 == null || !a2.equalsIgnoreCase(a)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            a(e);
            return false;
        }
    }

    private static String b(Context context, File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(context.getAssets().open("ixintui_plugin/ixintui_plugin.jar"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read > 0) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    return file.getAbsolutePath();
                }
            }
        } catch (Exception e) {
            a(e);
            return null;
        }
    }
}
