package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class a {
    private static int a(List<String> list, String str) {
        int i = 0;
        while (list != null && i < list.size()) {
            if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase((String) list.get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static String a(String str) {
        if (str == null) {
            return str;
        }
        String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        return (split == null || split.length <= 0) ? str : split[split.length - 1];
    }

    public static List<String> a(Context context) {
        List<String> arrayList = new ArrayList();
        CharSequence b = b(context);
        if (!TextUtils.isEmpty(b)) {
            arrayList.add(b);
        }
        b = i.a("ro.product.cpu.abi", "");
        if (!TextUtils.isEmpty(b)) {
            arrayList.add(b);
        }
        b = i.a("ro.product.cpu.abi2", "");
        if (!TextUtils.isEmpty(b)) {
            arrayList.add(b);
        }
        Object a = i.a("ro.product.cpu.abilist", "");
        if (!TextUtils.isEmpty(a)) {
            String[] split = a.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            int i = 0;
            while (split != null && i < split.length) {
                if (!TextUtils.isEmpty(split[i])) {
                    arrayList.add(split[i]);
                }
                i++;
            }
        }
        arrayList.add("armeabi");
        return arrayList;
    }

    public static void a(Context context, String str, String str2) {
        Exception e;
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        if (str != null) {
            try {
                if (!str.endsWith(File.separator)) {
                    str = str + File.separator;
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                            return;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                    return;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                th2 = th3;
                zipFile = null;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw th2;
            }
        }
        if (!(str2 == null || str2.endsWith(File.separator))) {
            str2 = str2 + File.separator;
        }
        HashMap hashMap = new HashMap();
        List a = a(context);
        zipFile = new ZipFile(str);
        try {
            Enumeration entries = zipFile.entries();
            byte[] bArr = new byte[1024];
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                b.b("ze.getName() = " + zipEntry.getName());
                if (zipEntry.getName().startsWith("lib/") && !zipEntry.isDirectory()) {
                    String a2 = a(zipEntry.getName());
                    String b = b(zipEntry.getName());
                    String str3 = (String) hashMap.get(a2);
                    if (!TextUtils.isEmpty(str3)) {
                        int a3 = a(a, b);
                        if (a3 < 0) {
                            continue;
                        } else if (a3 >= a(a, str3)) {
                            continue;
                        }
                    }
                    b.b("use abi " + b);
                    hashMap.put(a2, b);
                    File file = new File(str2 + a2);
                    if (file.exists()) {
                        file.delete();
                    }
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    InputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 1024);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    com.xiaomi.channel.commonutils.file.a.a(bufferedInputStream);
                    com.xiaomi.channel.commonutils.file.a.a(fileOutputStream);
                }
            }
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (Exception e32) {
                    e32.printStackTrace();
                }
            }
        } catch (Exception e5) {
            e32 = e5;
            zipFile2 = zipFile;
        } catch (Throwable th4) {
            th2 = th4;
        }
    }

    public static String b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            Field declaredField = Class.forName("android.content.pm.ApplicationInfo").getDeclaredField("primaryCpuAbi");
            declaredField.setAccessible(true);
            return (String) declaredField.get(applicationInfo);
        } catch (Throwable th) {
            return null;
        }
    }

    private static String b(String str) {
        if (str != null) {
            String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            if (split != null && split.length > 1) {
                return split[split.length - 2];
            }
        }
        return "armeabi";
    }
}
