package com.loc;

import android.content.Context;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.json.JSONObject;

public class bq {
    static int a = 1000;
    static boolean b = false;

    private static String a(be beVar) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        try {
            File b = beVar.b();
            if (b != null && b.exists()) {
                for (String str : b.list()) {
                    String str2;
                    if (str2.contains(".0")) {
                        str2 = t.a(bt.a(beVar, str2.split("\\.")[0], false));
                        if (obj != null) {
                            obj = null;
                        } else {
                            stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        }
                        stringBuilder.append("{\"log\":\"").append(str2).append("\"}");
                    }
                }
            }
        } catch (Throwable th) {
            w.a(th, "StatisticsManager", "getContent");
        }
        return stringBuilder.toString();
    }

    public static synchronized void a(int i, boolean z) {
        synchronized (bq.class) {
            a = i;
            b = z;
        }
    }

    public static void a(Context context) {
        Throwable th;
        be beVar = null;
        be a;
        try {
            if (c(context)) {
                a = be.a(new File(x.a(context, x.h)), (long) PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE);
                try {
                    String str;
                    Object a2 = a(a);
                    if (TextUtils.isEmpty(a2)) {
                        str = null;
                    } else {
                        String b = m.b(t.a(d(context)));
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("{\"pinfo\":\"").append(b).append("\",\"els\":[");
                        stringBuilder.append(a2);
                        stringBuilder.append("]}");
                        str = stringBuilder.toString();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        bn yVar = new y(t.c(t.a(str)), com.tencent.connect.common.Constants.VIA_SHARE_TYPE_INFO);
                        bi.a();
                        JSONObject jSONObject = new JSONObject(new String(bi.a(yVar)));
                        if (jSONObject.has("code") && jSONObject.getInt("code") == 1) {
                            if (a != null) {
                                a.e();
                            }
                            a = null;
                        }
                        if (a != null) {
                            try {
                                if (!a.c()) {
                                    try {
                                        a.close();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        th.printStackTrace();
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                th.printStackTrace();
                            }
                        }
                    } else if (a != null) {
                        try {
                            if (!a.c()) {
                                try {
                                    a.close();
                                } catch (Throwable th4) {
                                    th = th4;
                                    th.printStackTrace();
                                }
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            th.printStackTrace();
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    try {
                        w.a(th, "OfflineLocManager", "updateOfflineLocData");
                        if (a != null) {
                            try {
                                if (!a.c()) {
                                    a.close();
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                th.printStackTrace();
                            }
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        if (a != null) {
                            try {
                                if (!a.c()) {
                                    a.close();
                                }
                            } catch (Throwable th9) {
                                th9.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } else if (null != null) {
                try {
                    if (!beVar.c()) {
                        try {
                            beVar.close();
                        } catch (Throwable th10) {
                            th = th10;
                        }
                    }
                } catch (Throwable th11) {
                    th = th11;
                    th.printStackTrace();
                }
            }
        } catch (Throwable th12) {
            th = th12;
            a = null;
            if (a != null) {
                if (a.c()) {
                    a.close();
                }
            }
            throw th;
        }
    }

    public static synchronized void a(bp bpVar, Context context) {
        synchronized (bq.class) {
            z.b().submit(new dp(bpVar, context));
        }
    }

    private static long b(Context context) {
        Throwable th;
        File file = new File(x.a(context, "f.log"));
        if (!file.exists()) {
            return 0;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream2.available()];
                fileInputStream2.read(bArr);
                long parseLong = Long.parseLong(t.a(bArr));
                if (fileInputStream2 == null) {
                    return parseLong;
                }
                try {
                    fileInputStream2.close();
                    return parseLong;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return parseLong;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            w.a(th, "OfflineLocManager", "getUpdateTime");
            if (file.exists()) {
                file.delete();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return System.currentTimeMillis();
        }
    }

    private static synchronized boolean c(Context context) {
        Throwable th;
        Throwable th2;
        boolean z = false;
        synchronized (bq.class) {
            try {
                if (n.m(context) == 1 || b) {
                    if (System.currentTimeMillis() - b(context) >= 14400000) {
                        long currentTimeMillis = System.currentTimeMillis();
                        FileOutputStream fileOutputStream = null;
                        try {
                            File file = new File(x.a(context, "f.log"));
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                            try {
                                fileOutputStream2.write(t.a(String.valueOf(currentTimeMillis)));
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                    } catch (Throwable th3) {
                                        th3.printStackTrace();
                                    }
                                }
                            } catch (Throwable th4) {
                                th2 = th4;
                                fileOutputStream = fileOutputStream2;
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                throw th2;
                            }
                        } catch (Throwable th5) {
                            th3 = th5;
                            w.a(th3, "OfflineLocManager", "updateLogUpdateTime");
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            z = true;
                            return z;
                        }
                        z = true;
                    }
                }
            } catch (Throwable th22) {
                w.a(th22, "StatisticsManager", "isUpdate");
            }
        }
        return z;
    }

    private static String d(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(k.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(n.q(context)).append("\",\"mac\":\"").append(n.i(context)).append("\",\"tid\":\"").append(n.f(context)).append("\",\"umidt\":\"").append(n.a()).append("\",\"manufacture\":\"").append(Build.MANUFACTURER).append("\",\"device\":\"").append(Build.DEVICE).append("\",\"sim\":\"").append(n.r(context)).append("\",\"pkg\":\"").append(k.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appversion\":\"").append(k.d(context)).append("\",\"appname\":\"").append(k.b(context)).append("\"");
        } catch (Throwable th) {
            w.a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }
}
