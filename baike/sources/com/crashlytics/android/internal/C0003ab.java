package com.crashlytics.android.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.umeng.commonsdk.proguard.g;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.crashlytics.android.internal.ab */
public final class C0003ab {
    public static final Comparator<File> a = new bq();
    private static Boolean b = null;
    private static final char[] c = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long d = -1;
    private static Boolean e = null;

    public static SharedPreferences a() {
        return v.a().getContext().getSharedPreferences("com.crashlytics.prefs", 0);
    }

    private static String a(File file, String str) {
        Closeable bufferedReader;
        Throwable e;
        Throwable th;
        String str2 = null;
        if (file.exists()) {
            try {
                String[] split;
                bufferedReader = new BufferedReader(new FileReader(file), 1024);
                while (true) {
                    try {
                        CharSequence readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        split = Pattern.compile("\\s*:\\s*").split(readLine, 2);
                        if (split.length > 1 && split[0].equals(str)) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
                str2 = split[1];
                C0003ab.a(bufferedReader, "Failed to close system file reader.");
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                try {
                    v.a().b().a(Crashlytics.TAG, "Error parsing " + file, e);
                    C0003ab.a(bufferedReader, "Failed to close system file reader.");
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    C0003ab.a(bufferedReader, "Failed to close system file reader.");
                    throw th;
                }
            } catch (Throwable e4) {
                bufferedReader = null;
                th = e4;
                C0003ab.a(bufferedReader, "Failed to close system file reader.");
                throw th;
            }
        }
        return str2;
    }

    public static int b() {
        return br.a().ordinal();
    }

    public static synchronized long c() {
        long a;
        synchronized (C0003ab.class) {
            if (d == -1) {
                Object a2 = C0003ab.a(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(a2)) {
                    String toUpperCase = a2.toUpperCase(Locale.US);
                    try {
                        if (toUpperCase.endsWith("KB")) {
                            a = C0003ab.a(toUpperCase, "KB", 1024);
                        } else if (toUpperCase.endsWith("MB")) {
                            a = C0003ab.a(toUpperCase, "MB", 1048576);
                        } else if (toUpperCase.endsWith("GB")) {
                            a = C0003ab.a(toUpperCase, "GB", 1073741824);
                        } else {
                            v.a().b().a(Crashlytics.TAG, "Unexpected meminfo format while computing RAM: " + toUpperCase);
                            a = 0;
                        }
                    } catch (Throwable e) {
                        v.a().b().a(Crashlytics.TAG, "Unexpected meminfo format while computing RAM: " + toUpperCase, e);
                    }
                    d = a;
                }
                a = 0;
                d = a;
            }
            a = d;
        }
        return a;
    }

    private static long a(String str, String str2, int i) {
        return Long.parseLong(str.split(str2)[0].trim()) * ((long) i);
    }

    public static RunningAppProcessInfo a(String str, Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(str)) {
                    return runningAppProcessInfo;
                }
            }
        }
        return null;
    }

    public static String a(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }

    public static String a(String str) {
        return C0003ab.a(str.getBytes(), "SHA-1");
    }

    private static String b(InputStream inputStream) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return C0003ab.a(instance.digest());
                }
                instance.update(bArr, 0, read);
            }
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return C0003ab.a(instance.digest());
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Could not create hashing algorithm: " + str + ", returning empty string.", e);
            return "";
        }
    }

    public static String a(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null) {
                arrayList.add(str.replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : arrayList) {
            stringBuilder.append(append);
        }
        String append2 = stringBuilder.toString();
        return append2.length() > 0 ? C0003ab.a(append2) : null;
    }

    public static long a(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static long b(String str) {
        StatFs statFs = new StatFs(str);
        long blockSize = (long) statFs.getBlockSize();
        return (((long) statFs.getBlockCount()) * blockSize) - (((long) statFs.getAvailableBlocks()) * blockSize);
    }

    public static float b(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
    }

    public static boolean c(Context context) {
        if (C0003ab.d()) {
            return false;
        }
        return ((SensorManager) context.getSystemService(g.aa)).getDefaultSensor(8) != null;
    }

    public static void c(String str) {
        if (C0003ab.e(v.a().getContext())) {
            v.a().b().a(Crashlytics.TAG, str);
        }
    }

    public static void d(String str) {
        if (C0003ab.e(v.a().getContext())) {
            v.a().b().d(Crashlytics.TAG, str);
        }
    }

    public static void a(int i, String str) {
        if (C0003ab.e(v.a().getContext())) {
            v.a().b().a(4, Crashlytics.TAG, str);
        }
    }

    public static boolean d(Context context) {
        boolean z = false;
        if (e == null) {
            if (!C0003ab.a(context, "com.crashlytics.SilenceCrashlyticsLogCat", false)) {
                z = true;
            }
            e = Boolean.valueOf(z);
        }
        return e.booleanValue();
    }

    public static boolean e(Context context) {
        if (b == null) {
            b = Boolean.valueOf(C0003ab.a(context, "com.crashlytics.Trace", false));
        }
        return b.booleanValue();
    }

    public static boolean a(Context context, String str, boolean z) {
        if (context == null) {
            return z;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return z;
        }
        int a = C0003ab.a(context, str, "bool");
        if (a > 0) {
            return resources.getBoolean(a);
        }
        int a2 = C0003ab.a(context, str, "string");
        if (a2 > 0) {
            return Boolean.parseBoolean(context.getString(a2));
        }
        return z;
    }

    public static int a(Context context, String str, String str2) {
        Resources resources = context.getResources();
        int i = context.getApplicationContext().getApplicationInfo().icon;
        return resources.getIdentifier(str, str2, i > 0 ? context.getResources().getResourcePackageName(i) : context.getPackageName());
    }

    public static boolean d() {
        return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT) || Secure.getString(v.a().getContext().getContentResolver(), "android_id") == null;
    }

    public static boolean e() {
        boolean d = C0003ab.d();
        String str = Build.TAGS;
        if ((!d && str != null && str.contains("test-keys")) || new File("/system/app/Superuser.apk").exists()) {
            return true;
        }
        File file = new File("/system/xbin/su");
        if (d || !file.exists()) {
            return false;
        }
        return true;
    }

    public static int f() {
        int i;
        Object obj = null;
        if (C0003ab.d()) {
            i = 1;
        } else {
            i = 0;
        }
        if (C0003ab.e()) {
            i |= 2;
        }
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            obj = 1;
        }
        if (obj != null) {
            return i | 4;
        }
        return i;
    }

    public static int a(boolean z) {
        float b = C0003ab.b(v.a().getContext());
        if (!z) {
            return 1;
        }
        if (z && ((double) b) >= 99.0d) {
            return 3;
        }
        if (!z || ((double) b) >= 99.0d) {
            return 0;
        }
        return 2;
    }

    public static Cipher b(int i, String str) throws InvalidKeyException {
        if (str.length() < 32) {
            throw new InvalidKeyException("Key must be at least 32 bytes.");
        }
        Key secretKeySpec = new SecretKeySpec(str.getBytes(), 0, 32, "AES/ECB/PKCS7Padding");
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
            instance.init(1, secretKeySpec);
            return instance;
        } catch (Throwable e) {
            v.a().b().a(Crashlytics.TAG, "Could not create Cipher for AES/ECB/PKCS7Padding - should never happen.", e);
            throw new RuntimeException(e);
        }
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length << 1)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i << 1] = c[i2 >>> 4];
            cArr[(i << 1) + 1] = c[i2 & 15];
        }
        return new String(cArr);
    }

    public static boolean f(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String a(Context context, String str) {
        int a = C0003ab.a(context, str, "string");
        if (a > 0) {
            return context.getString(a);
        }
        return "";
    }

    public static void a(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, str, e);
            }
        }
    }

    public static void a(Flushable flushable, String str) {
        if (flushable != null) {
            try {
                flushable.flush();
            } catch (Throwable e) {
                v.a().b().a(Crashlytics.TAG, str, e);
            }
        }
    }

    public static boolean e(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("value must be zero or greater");
        }
        return String.format(Locale.US, "%1$10s", new Object[]{Integer.valueOf(i)}).replace(TokenParser.SP, '0');
    }

    public static void a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static String b(int i) {
        switch (i) {
            case 2:
                return "V";
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
            default:
                return "?";
        }
    }

    public static String g(Context context) {
        Throwable e;
        Throwable th;
        String str = null;
        Closeable openRawResource;
        try {
            openRawResource = context.getResources().openRawResource(C0003ab.h(context));
            try {
                String b = C0003ab.b((InputStream) openRawResource);
                if (!C0003ab.e(b)) {
                    str = b;
                }
                C0003ab.a(openRawResource, "Failed to close icon input stream.");
            } catch (Exception e2) {
                e = e2;
                try {
                    v.a().b().a(Crashlytics.TAG, "Could not calculate hash for app icon.", e);
                    C0003ab.a(openRawResource, "Failed to close icon input stream.");
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    C0003ab.a(openRawResource, "Failed to close icon input stream.");
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            openRawResource = null;
            v.a().b().a(Crashlytics.TAG, "Could not calculate hash for app icon.", e);
            C0003ab.a(openRawResource, "Failed to close icon input stream.");
            return str;
        } catch (Throwable e4) {
            openRawResource = null;
            th = e4;
            C0003ab.a(openRawResource, "Failed to close icon input stream.");
            throw th;
        }
        return str;
    }

    public static int h(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String i(Context context) {
        int a = C0003ab.a(context, "com.crashlytics.android.build_id", "string");
        if (a == 0) {
            return null;
        }
        String string = context.getResources().getString(a);
        v.a().b().a(Crashlytics.TAG, "Build ID is: " + string);
        return string;
    }
}
