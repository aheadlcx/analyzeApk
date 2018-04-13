package com.umeng.commonsdk.framework;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType;
import com.umeng.commonsdk.statistics.common.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Arrays;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.model.CustomButton;

public class b {
    private static Object a = new Object();
    private static String b = "envelope";
    private static String c = null;
    private static Object d = new Object();

    public static String a(Context context) {
        String str = "";
        try {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
                    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == myPid) {
                            return runningAppProcessInfo.processName;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(context.getApplicationContext(), th);
        }
        return str;
    }

    private static boolean a(Context context, String str) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        if (VERSION.SDK_INT >= 23) {
            try {
                boolean z2;
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                return z2;
            } catch (Throwable th) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, th);
                return false;
            }
        }
        try {
            if (applicationContext.getPackageManager().checkPermission(str, applicationContext.getPackageName()) != 0) {
                z = false;
            }
            return z;
        } catch (Throwable th2) {
            com.umeng.commonsdk.proguard.b.a(applicationContext, th2);
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnectedOrConnecting();
                    }
                }
            }
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(context.getApplicationContext(), th);
        }
        return false;
    }

    public static int c(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            File file = new File(h(context));
            synchronized (d) {
                if (file.isDirectory()) {
                    String[] list = file.list();
                    if (list != null) {
                        int length = list.length;
                        return length;
                    }
                }
            }
        } catch (Throwable th) {
            com.umeng.commonsdk.proguard.b.a(context, th);
        }
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void d(android.content.Context r5) {
        /*
        r3 = 100;
        r0 = new java.io.File;
        r1 = h(r5);
        r0.<init>(r1);
        r1 = d;
        monitor-enter(r1);
        r2 = r0.listFiles();	 Catch:{ all -> 0x005c }
        if (r2 == 0) goto L_0x0017;
    L_0x0014:
        r0 = r2.length;	 Catch:{ all -> 0x005c }
        if (r0 > r3) goto L_0x0019;
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
    L_0x0018:
        return;
    L_0x0019:
        r0 = new com.umeng.commonsdk.framework.d;	 Catch:{ all -> 0x005c }
        r0.<init>(r5);	 Catch:{ all -> 0x005c }
        java.util.Arrays.sort(r2, r0);	 Catch:{ all -> 0x005c }
        r0 = r2.length;	 Catch:{ all -> 0x005c }
        if (r0 <= r3) goto L_0x005a;
    L_0x0024:
        r0 = "--->>> biger than 10";
        com.umeng.commonsdk.statistics.common.e.b(r0);	 Catch:{ Throwable -> 0x0056 }
        r0 = 0;
    L_0x002a:
        r3 = r2.length;	 Catch:{ Throwable -> 0x0056 }
        r3 = r3 + -100;
        if (r0 >= r3) goto L_0x005a;
    L_0x002f:
        r3 = r2[r0];	 Catch:{ Throwable -> 0x0056 }
        r3 = r3.delete();	 Catch:{ Throwable -> 0x0056 }
        if (r3 != 0) goto L_0x0053;
    L_0x0037:
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0056 }
        r3.<init>();	 Catch:{ Throwable -> 0x0056 }
        r4 = "--->>> remove [";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0056 }
        r3 = r3.append(r0);	 Catch:{ Throwable -> 0x0056 }
        r4 = "] file fail.";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x0056 }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x0056 }
        com.umeng.commonsdk.statistics.common.e.b(r3);	 Catch:{ Throwable -> 0x0056 }
    L_0x0053:
        r0 = r0 + 1;
        goto L_0x002a;
    L_0x0056:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r5, r0);	 Catch:{ all -> 0x005c }
    L_0x005a:
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
        goto L_0x0018;
    L_0x005c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.b.d(android.content.Context):void");
    }

    private static String c(String str) {
        Context a = c.a();
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf(95);
        try {
            return str.substring(indexOf + 1, str.indexOf(95, indexOf + 1));
        } catch (Throwable e) {
            com.umeng.commonsdk.proguard.b.a(a, e);
            return "";
        }
    }

    public static File e(Context context) {
        File file = null;
        if (context != null) {
            File file2 = new File(h(context));
            synchronized (d) {
                File[] listFiles = file2.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                } else {
                    Arrays.sort(listFiles, new f(context));
                    file = listFiles[0];
                }
            }
        }
        return file;
    }

    public static void f(Context context) {
        if (context != null) {
            try {
                String g = g(context);
                if (!TextUtils.isEmpty(g) && !g.equals(b)) {
                    File file = new File(context.getFilesDir().getAbsolutePath() + "/." + g);
                    if (file.exists()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null && listFiles.length != 0) {
                            try {
                                String h = h(context);
                                for (int i = 0; i < listFiles.length; i++) {
                                    listFiles[i].renameTo(new File(h + MqttTopic.TOPIC_LEVEL_SEPARATOR + listFiles[i].getName()));
                                }
                                if (file.isDirectory()) {
                                    file.delete();
                                }
                            } catch (Throwable th) {
                                com.umeng.commonsdk.proguard.b.a(context, th);
                            }
                        } else if (file.isDirectory()) {
                            file.delete();
                        }
                    }
                }
            } catch (Throwable th2) {
                com.umeng.commonsdk.proguard.b.a(context, th2);
            }
        }
    }

    public static String g(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                e.b("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                return b;
            } else if (runningAppProcesses.size() == 0) {
                return b;
            } else {
                try {
                    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == Process.myPid()) {
                            String replace = runningAppProcessInfo.processName.replace(':', '_');
                            e.b("--->>> getEnvelopeDir: use current process name as envelope directory.");
                            return replace;
                        }
                    }
                } catch (Throwable th) {
                    com.umeng.commonsdk.proguard.b.a(context, th);
                }
            }
        }
        return b;
    }

    public static String h(Context context) {
        String str;
        synchronized (d) {
            try {
                if (c == null) {
                    c = context.getFilesDir().getAbsolutePath() + "/." + b;
                }
                File file = new File(c);
                if (!(file.exists() || file.mkdir())) {
                    e.b("--->>> Create Envelope Directory failed!!!");
                }
            } catch (Throwable th) {
                com.umeng.commonsdk.proguard.b.a(context, th);
            }
            str = c;
        }
        return str;
    }

    public static long i(Context context) {
        long j;
        synchronized (a) {
            j = PreferenceWrapper.getDefault(context).getLong("last_successful_build_time", 0);
        }
        return j;
    }

    private static void j(Context context) {
        synchronized (a) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong("last_successful_build_time", System.currentTimeMillis()).commit();
        }
    }

    public static int a(Context context, String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        Throwable th;
        Throwable e;
        int i = 101;
        if (bArr != null) {
            File file = new File(h(context) + MqttTopic.TOPIC_LEVEL_SEPARATOR + str);
            synchronized (d) {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(bArr);
                        fileOutputStream.close();
                        FileOutputStream fileOutputStream2 = null;
                        if (null != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Throwable th2) {
                                com.umeng.commonsdk.proguard.b.a(context, th2);
                            }
                        }
                        if (a.a(context).a(str)) {
                            j(context);
                        }
                        i = 0;
                    } catch (IOException e2) {
                        e = e2;
                        try {
                            com.umeng.commonsdk.proguard.b.a(context, e);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable e3) {
                                    com.umeng.commonsdk.proguard.b.a(context, e3);
                                }
                            }
                            return i;
                        } catch (Throwable th3) {
                            th2 = th3;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Throwable e32) {
                                    com.umeng.commonsdk.proguard.b.a(context, e32);
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (IOException e4) {
                    e32 = e4;
                    fileOutputStream = null;
                    com.umeng.commonsdk.proguard.b.a(context, e32);
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return i;
                } catch (Throwable th4) {
                    th2 = th4;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th2;
                }
            }
        }
        return i;
    }

    public static boolean a(File file) {
        boolean delete;
        Context a = c.a();
        synchronized (d) {
            if (file != null) {
                try {
                    if (file.exists()) {
                        delete = file.delete();
                    }
                } catch (Throwable th) {
                    com.umeng.commonsdk.proguard.b.a(a, th);
                }
            }
            delete = true;
        }
        return delete;
    }

    public static byte[] a(String str) throws IOException {
        byte[] bArr;
        Throwable th;
        Throwable th2;
        Context a = c.a();
        FileChannel fileChannel = null;
        synchronized (d) {
            try {
                FileChannel channel = new RandomAccessFile(str, CustomButton.POSITION_RIGHT).getChannel();
                try {
                    MappedByteBuffer load = channel.map(MapMode.READ_ONLY, 0, channel.size()).load();
                    System.out.println(load.isLoaded());
                    bArr = new byte[((int) channel.size())];
                    if (load.remaining() > 0) {
                        load.get(bArr, 0, load.remaining());
                    }
                    try {
                        channel.close();
                    } catch (Throwable th3) {
                        com.umeng.commonsdk.proguard.b.a(a, th3);
                    }
                } catch (Throwable e) {
                    th2 = e;
                    fileChannel = channel;
                    th3 = th2;
                    try {
                        com.umeng.commonsdk.proguard.b.a(a, th3);
                        throw th3;
                    } catch (Throwable th4) {
                        th3 = th4;
                        try {
                            fileChannel.close();
                        } catch (Throwable e2) {
                            com.umeng.commonsdk.proguard.b.a(a, e2);
                        }
                        throw th3;
                    }
                } catch (Throwable e22) {
                    th2 = e22;
                    fileChannel = channel;
                    th3 = th2;
                    fileChannel.close();
                    throw th3;
                }
            } catch (IOException e3) {
                th3 = e3;
                com.umeng.commonsdk.proguard.b.a(a, th3);
                throw th3;
            }
        }
        return bArr;
    }

    public static boolean a(Context context, UMBusinessType uMBusinessType) {
        String str = "a";
        if (uMBusinessType == UMBusinessType.U_DPLUS) {
            str = "d";
        } else if (uMBusinessType == UMBusinessType.U_INTERNAL) {
            str = "i";
        }
        synchronized (d) {
            try {
                File[] listFiles = new File(h(context)).listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    return false;
                }
                for (File name : listFiles) {
                    if (name.getName().startsWith(str)) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                com.umeng.commonsdk.proguard.b.a(context, th);
                return false;
            }
        }
    }
}
