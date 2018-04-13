package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class b {
    private static String a = null;

    protected static Map<String, Integer> a(String str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Integer> hashMap = new HashMap();
            for (String split : str.split(",")) {
                String[] split2 = split.split(":");
                if (split2.length != 2) {
                    an.e("error format at %s", new Object[]{split});
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            an.e("error format intStateStr %s", new Object[]{str});
            e.printStackTrace();
            return null;
        }
    }

    protected static String b(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                stringBuilder.append(str2).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    protected static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (a.a(context) == null) {
            an.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = (String) map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            an.e("no intStateStr", new Object[0]);
            return null;
        }
        Map a = a(str);
        if (a == null) {
            an.e("parse intSateMap fail", new Object[]{Integer.valueOf(map.size())});
            return null;
        }
        try {
            ((Integer) a.get("sino")).intValue();
            ((Integer) a.get("sud")).intValue();
            String str2 = (String) map.get("soVersion");
            if (str2 == null) {
                an.e("error format at version", new Object[0]);
                return null;
            }
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            str = (String) map.get("errorAddr");
            String str9 = str == null ? "unknown" : str;
            str = (String) map.get("codeMsg");
            if (str == null) {
                str3 = "unknown";
            } else {
                str3 = str;
            }
            str = (String) map.get("tombPath");
            if (str == null) {
                str4 = "unknown";
            } else {
                str4 = str;
            }
            str = (String) map.get("signalName");
            if (str == null) {
                str5 = "unknown";
            } else {
                str5 = str;
            }
            str = (String) map.get("errnoMsg");
            str = (String) map.get("stack");
            if (str == null) {
                str6 = "unknown";
            } else {
                str6 = str;
            }
            str = (String) map.get("jstack");
            if (str != null) {
                str6 = str6 + "java:\n" + str;
            }
            Integer num = (Integer) a.get("sico");
            String str10 = "UNKNOWN";
            if (num != null && num.intValue() > 0) {
                str5 = str5 + "(" + str3 + ")";
                str3 = "KERNEL";
            }
            str = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (!(str == null || str.isEmpty())) {
                bArr = ap.a(null, str, "BuglyNativeLog.txt");
            }
            str = (String) map.get("sendingProcess");
            if (str == null) {
                str7 = "unknown";
            } else {
                str7 = str;
            }
            num = (Integer) a.get("spd");
            if (num != null) {
                str7 = str7 + "(" + num + ")";
            }
            str = (String) map.get("threadName");
            if (str == null) {
                str8 = "unknown";
            } else {
                str8 = str;
            }
            num = (Integer) a.get("et");
            if (num != null) {
                str8 = str8 + "(" + num + ")";
            }
            str = (String) map.get("processName");
            if (str == null) {
                str10 = "unknown";
            } else {
                str10 = str;
            }
            num = (Integer) a.get("ep");
            if (num != null) {
                str10 = str10 + "(" + num + ")";
            }
            Map map2 = null;
            str = (String) map.get("key-value");
            if (str != null) {
                map2 = new HashMap();
                for (String split : str.split("\n")) {
                    String[] split2 = split.split("=");
                    if (split2.length == 2) {
                        map2.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str10, str8, (((long) ((Integer) a.get("etms")).intValue()) / 1000) + (((long) ((Integer) a.get("ets")).intValue()) * 1000), str5, str9, b(str6), str3, str7, str4, (String) map.get("sysLogPath"), str2, bArr, map2, false);
            if (packageCrashDatas != null) {
                str = (String) map.get(Parameters.SESSION_USER_ID);
                if (str != null) {
                    an.c("[Native record info] userId: %s", new Object[]{str});
                    packageCrashDatas.m = str;
                }
                str = (String) map.get("sysLog");
                if (str != null) {
                    packageCrashDatas.w = str;
                }
                str = (String) map.get("appVersion");
                if (str != null) {
                    an.c("[Native record info] appVersion: %s", new Object[]{str});
                    packageCrashDatas.f = str;
                }
                str = (String) map.get("isAppForeground");
                if (str != null) {
                    an.c("[Native record info] isAppForeground: %s", new Object[]{str});
                    packageCrashDatas.N = str.equalsIgnoreCase("true");
                }
                str = (String) map.get("launchTime");
                if (str != null) {
                    an.c("[Native record info] launchTime: %s", new Object[]{str});
                    packageCrashDatas.M = Long.parseLong(str);
                }
                packageCrashDatas.y = null;
                packageCrashDatas.k = true;
            }
            return packageCrashDatas;
        } catch (Throwable e) {
            if (!an.a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable e2) {
            an.e("error format", new Object[0]);
            e2.printStackTrace();
            return null;
        }
    }

    protected static String a(BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = bufferedInputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) read);
        }
    }

    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        IOException e;
        Throwable th;
        CrashDetailBean crashDetailBean = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            an.e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                BufferedInputStream bufferedInputStream;
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        String a = a(bufferedInputStream);
                        if (a == null || !a.equals("NATIVE_RQD_REPORT")) {
                            an.e("record read fail! %s", new Object[]{a});
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            Map hashMap = new HashMap();
                            Object obj = crashDetailBean;
                            while (true) {
                                String a2 = a(bufferedInputStream);
                                if (a2 == null) {
                                    break;
                                } else if (obj == null) {
                                    obj = a2;
                                } else {
                                    hashMap.put(obj, a2);
                                    obj = crashDetailBean;
                                }
                            }
                            if (obj != null) {
                                an.e("record not pair! drop! %s", new Object[]{obj});
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            } else {
                                crashDetailBean = a(context, hashMap, nativeExceptionHandler);
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e222) {
                                        e222.printStackTrace();
                                    }
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e222 = e3;
                        try {
                            e222.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            return crashDetailBean;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    e22222 = e4;
                    bufferedInputStream = crashDetailBean;
                    e22222.printStackTrace();
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    return crashDetailBean;
                } catch (Throwable th3) {
                    bufferedInputStream = crashDetailBean;
                    th = th3;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return crashDetailBean;
    }

    private static String b(String str, String str2) {
        String str3 = null;
        BufferedReader a = ap.a(str, "reg_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String readLine = a.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    String str4 = "                ";
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = a.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        if (i3 % 4 == 0) {
                            if (i3 > 0) {
                                stringBuilder.append("\n");
                            }
                            stringBuilder.append("  ");
                        } else {
                            if (readLine2.length() > 16) {
                                i2 = 28;
                            }
                            stringBuilder.append(str4.substring(0, i2 - i));
                        }
                        i = readLine2.length();
                        stringBuilder.append(readLine2);
                        i3++;
                    }
                    stringBuilder.append("\n");
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            an.a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        an.a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e22) {
                        an.a(e22);
                    }
                }
            }
        }
        return str3;
    }

    private static String c(String str, String str2) {
        String str3 = null;
        BufferedReader a = ap.a(str, "map_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String readLine = a.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    while (true) {
                        readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append("  ");
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    }
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            an.a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        an.a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e22) {
                        an.a(e22);
                    }
                }
            }
        }
        return str3;
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String b = b(str, str2);
        if (!(b == null || b.isEmpty())) {
            stringBuilder.append("Register infos:\n");
            stringBuilder.append(b);
        }
        b = c(str, str2);
        if (!(b == null || b.isEmpty())) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append("System SO infos:\n");
            stringBuilder.append(b);
        }
        return stringBuilder.toString();
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void a(boolean z, String str) {
        if (str != null) {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canWrite()) {
                file.delete();
                an.c("delete record file %s", new Object[]{file.getAbsoluteFile()});
            }
            file = new File(str, "reg_record.txt");
            if (file.exists() && file.canWrite()) {
                file.delete();
                an.c("delete record file %s", new Object[]{file.getAbsoluteFile()});
            }
            file = new File(str, "map_record.txt");
            if (file.exists() && file.canWrite()) {
                file.delete();
                an.c("delete record file %s", new Object[]{file.getAbsoluteFile()});
            }
            file = new File(str, "backup_record.txt");
            if (file.exists() && file.canWrite()) {
                file.delete();
                an.c("delete record file %s", new Object[]{file.getAbsoluteFile()});
            }
            if (a != null) {
                file = new File(a);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                    an.c("delete record file %s", new Object[]{file.getAbsoluteFile()});
                }
            }
            if (z) {
                file = new File(str);
                if (file.canRead() && file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                                file2.delete();
                                an.c("delete invalid record file %s", new Object[]{file2.getAbsoluteFile()});
                            }
                        }
                    }
                }
            }
        }
    }

    public static String a(String str, int i, String str2) {
        BufferedReader bufferedReader;
        Throwable th;
        Throwable th2;
        String str3 = null;
        if (str == null || i <= 0) {
            return str3;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return str3;
        }
        a = str;
        an.a("Read system log from native record file(length: %s bytes): %s", new Object[]{Long.valueOf(file.length()), file.getAbsolutePath()});
        if (str2 == null) {
            str3 = ap.a(new File(str));
        } else {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                while (true) {
                    try {
                        Object readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                            stringBuilder.append(readLine);
                            stringBuilder.append("\n");
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                str3 = stringBuilder.toString();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th4) {
                        an.a(th4);
                    }
                }
            } catch (Throwable th42) {
                bufferedReader = str3;
                th2 = th42;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th2;
            }
        }
        if (str3 == null || str3.length() <= i) {
            return str3;
        }
        return str3.substring(str3.length() - i);
    }
}
