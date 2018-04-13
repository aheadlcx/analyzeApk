package com.ixintui.smartlink.a;

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
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.ali.auth.third.login.LoginConstants;
import com.ixintui.plugin.IPushReceiver;
import com.ixintui.plugin.IPushSdkApi;
import com.ixintui.pushsdk.SdkConstants;
import com.ixintui.smartlink.ParamInfo;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.http.entity.mime.MIME;
import org.json.JSONException;
import org.json.JSONObject;

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

    private static SharedPreferences e(Context context) {
        if (context != null) {
            return context.getSharedPreferences("com.ixintui.data.version", 0);
        }
        throw new InvalidParameterException();
    }

    public static Object a(Context context, Intent intent) {
        Class a = com.xiaomi.a.a.a(context);
        return a(a, a(a, "a", new Class[]{Context.class}, new Object[]{context}), "a", new Class[]{Intent.class}, new Object[]{intent});
    }

    public static ParamInfo a(Uri uri) {
        StringBuffer stringBuffer = new StringBuffer();
        Map hashMap = new HashMap();
        Object query = uri.getQuery();
        if (TextUtils.isEmpty(query)) {
            return null;
        }
        String str;
        String[] split = query.split("&");
        for (String str2 : split) {
            String[] split2 = str2.split(LoginConstants.EQUAL);
            hashMap.put(split2[0], split2.length > 1 ? split2[1] : "");
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        String str3 = (String) hashMap.get("p");
        String str4 = (String) hashMap.get("ci");
        str2 = (String) hashMap.get("si");
        String str5 = (String) hashMap.get("c");
        String str6 = (String) hashMap.get("pl");
        String str7 = (String) hashMap.get("lk");
        ParamInfo paramInfo = new ParamInfo();
        if (TextUtils.isEmpty(str3)) {
            stringBuffer.append("no parameters\n");
        } else {
            try {
                paramInfo.setParam(new JSONObject(str3));
            } catch (JSONException e) {
                stringBuffer.append("parameter is not JSON\n");
            }
        }
        if (TextUtils.isEmpty(str4)) {
            stringBuffer.append("client Id is null\n");
        } else {
            paramInfo.setClientId(str4);
        }
        if (TextUtils.isEmpty(str2)) {
            stringBuffer.append("send id is null\n");
        } else {
            paramInfo.setSendId(str2);
        }
        if (TextUtils.isEmpty(str5)) {
            stringBuffer.append("channel is null\n");
        } else {
            paramInfo.setChannel(str5);
        }
        if (TextUtils.isEmpty(str6)) {
            stringBuffer.append("platform is null\n");
        } else {
            paramInfo.setPlatform(str6);
        }
        if (TextUtils.isEmpty(str7)) {
            stringBuffer.append("link key is null\n");
        } else {
            paramInfo.setLinkKey(str7);
        }
        if (!TextUtils.isEmpty(stringBuffer.toString())) {
            paramInfo.setError(stringBuffer.toString());
        }
        return paramInfo;
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            URLConnection uRLConnection = (HttpURLConnection) new URL(str).openConnection();
            uRLConnection.setRequestMethod("POST");
            uRLConnection.setDoInput(true);
            uRLConnection.setDoOutput(true);
            uRLConnection.setUseCaches(false);
            uRLConnection.setRequestProperty(MIME.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=utf-8");
            uRLConnection.setRequestProperty("Content-Length", str2.length());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(uRLConnection.getOutputStream());
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            int responseCode = uRLConnection.getResponseCode();
            if (responseCode == 200) {
                return a(uRLConnection);
            }
            new StringBuilder("post response code: ").append(responseCode);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(Context context) {
        String str = null;
        try {
            str = e(context).getString("version_file", null);
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
            str = e(context).getString("sign", null);
        } catch (Exception e) {
            a(e);
        }
        return str;
    }

    public static void b(String str, String str2) {
        String str3 = "Process id: " + Process.myPid() + " Thread id: " + Thread.currentThread().getId() + " ";
        new StringBuilder().append(str3).append(str2);
        if (a()) {
            d(str, str3 + str2);
        }
    }

    private static boolean g(Context context) {
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

    public static Object a(Class cls, Object obj, String str, Class[] clsArr, Object[] objArr) {
        Object obj2 = null;
        try {
            Method method = cls.getMethod(str, clsArr);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            obj2 = method.invoke(obj, objArr);
        } catch (Exception e) {
            a(e);
        } catch (Exception e2) {
            a(e2);
        } catch (Exception e22) {
            a(e22);
        } catch (Exception e222) {
            a(e222);
        }
        return obj2;
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

    private static boolean h(Context context) {
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

    public static Object a(Class cls, String str, Class[] clsArr, Object[] objArr) {
        return a(cls, null, str, clsArr, objArr);
    }

    private static String a(URLConnection uRLConnection) {
        String str = null;
        try {
            str = a(uRLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
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

    private static String a(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                stringBuilder.append(new String(bArr, 0, read));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static IPushReceiver c(Context context) {
        return (IPushReceiver) a(context, "com.ixintui.push.PushReceiverImpl");
    }

    private static boolean a(String str, String str2, PackageManager packageManager) {
        Intent intent = new Intent(str);
        if (str2 != null) {
            intent.setPackage(str2);
        }
        return !packageManager.queryBroadcastReceivers(intent, 64).isEmpty();
    }

    private static synchronized void d(String str, String str2) {
        synchronized (a.class) {
            a("ixintui/log.txt", str, str2);
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

    private static synchronized void a(String str, String str2, String str3) {
        synchronized (a.class) {
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    OutputStream a = a(str);
                    if (a != null) {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(a));
                        bufferedWriter.write(c() + " " + str2 + " \t" + str3 + SocketUtil.CRLF);
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

    public static Object a(Object obj, String str, Class[] clsArr, Object[] objArr) {
        Object obj2 = null;
        if (obj != null) {
            try {
                obj2 = obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
            } catch (Exception e) {
                a(e);
            }
        }
        return obj2;
    }

    private static boolean i(Context context) {
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
                } else if (((IPushSdkApi) loadClass.newInstance()).isWrapperCompatible(context, 18700)) {
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

    private static boolean j(Context context) {
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

    private static boolean k(Context context) {
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

    private static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            String a = a(Base64.decode(str2, 0));
            String b = b(new FileInputStream(new File(str)));
            if (a == null || !a.equalsIgnoreCase(b)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean l(Context context) {
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

    private static ClassLoader f(Context context) {
        if (a == null) {
            String a = a(context);
            ClassLoader classLoader = null;
            if (c(a, b(context))) {
                ClassLoader b = b(context, a);
                if (b == null) {
                    try {
                        e(context).edit().remove("sign").remove("version_file").remove("version_code").commit();
                        classLoader = b;
                    } catch (Exception e) {
                        a(e);
                    }
                }
                classLoader = b;
            }
            if (classLoader == null) {
                b("loadclass", "sign not correct");
                File fileStreamPath = context.getFileStreamPath("ixintui_plugin.jar");
                String absolutePath = (fileStreamPath.exists() && a(context, fileStreamPath)) ? fileStreamPath.getAbsolutePath() : b(context, fileStreamPath);
                classLoader = b(context, absolutePath);
            }
            a = classLoader;
        }
        return a;
    }

    public static String d(Context context) {
        if (!g(context)) {
            return "com.ixintui.push.PushService manifest setting error, please refer to ixintui sample or document";
        }
        if (!h(context)) {
            return "com.ixintui.push.MediateService manifest setting error, please refer to ixintui sample or ducoment";
        }
        if (!i(context)) {
            return "com.ixintui.push.Receiver manifest setting error, please refer to ixintui sample or document";
        }
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Object obj = (a(SdkConstants.MESSAGE_ACTION, packageName, packageManager) && a(SdkConstants.RESULT_ACTION, packageName, packageManager) && a(SdkConstants.NOTIFICATION_CLICK_ACTION, packageName, packageManager)) ? 1 : null;
        if (obj == null) {
            return "You should define client receiver to receive ixintui broadcast, please refer to ixintui sample or document";
        }
        if (!j(context)) {
            return "com.ixintui.push.Provider manifest setting error, please check authority or other setting";
        }
        if (!k(context)) {
            return "com.ixintui.push.PushActivity manifest setting error, please refer to ixintui sample or document";
        }
        if (l(context)) {
            return "ixintui push sdk integration ok";
        }
        return "asset file access error or missing layout files, please check your project";
    }

    private static Class c(Context context, String str) {
        Class cls = null;
        ClassLoader f = f(context);
        if (f == null) {
            b("loadClass", "failed to load plugin!");
        } else {
            try {
                cls = f.loadClass(str);
            } catch (Exception e) {
                a(e);
            }
        }
        return cls;
    }

    private static String b(InputStream inputStream) {
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
            String b = b(new FileInputStream(file));
            String b2 = b(context.getAssets().open("ixintui_plugin/ixintui_plugin.jar"));
            if (b2 == null || !b2.equalsIgnoreCase(b)) {
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
