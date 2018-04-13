package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ao;
import com.tencent.bugly.proguard.ap;
import java.util.HashMap;
import java.util.Map;

public class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, byte[] bArr, Map<String, String> map, boolean z) {
        boolean m = c.a().m();
        if (m) {
            an.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.o;
        crashDetailBean.g = this.c.w();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = m ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        if (str5 == null) {
            str5 = "";
        }
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = ap.b(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.K();
        crashDetailBean.v = str8;
        String str11 = null;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        if (instance != null) {
            str11 = instance.getDumpFilePath();
        }
        String a = b.a(str11, str8);
        if (!ap.a(a)) {
            crashDetailBean.U = a;
        }
        crashDetailBean.V = b.c(str11);
        crashDetailBean.w = b.a(str9, c.e, c.h);
        crashDetailBean.J = str7;
        crashDetailBean.K = str6;
        crashDetailBean.L = str10;
        crashDetailBean.E = this.c.p();
        crashDetailBean.F = this.c.o();
        crashDetailBean.G = this.c.q();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.k();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = ap.a(this.a, c.e, c.h);
            }
            crashDetailBean.x = ao.a();
            crashDetailBean.M = this.c.a;
            crashDetailBean.N = this.c.a();
            crashDetailBean.P = this.c.H();
            crashDetailBean.Q = this.c.I();
            crashDetailBean.R = this.c.B();
            crashDetailBean.S = this.c.G();
            crashDetailBean.y = ap.a(c.f, false);
            str11 = "java:\n";
            int indexOf = crashDetailBean.q.indexOf(str11);
            if (indexOf > 0) {
                indexOf += str11.length();
                if (indexOf < crashDetailBean.q.length()) {
                    String substring = crashDetailBean.q.substring(indexOf, crashDetailBean.q.length() - 1);
                    if (substring.length() > 0 && crashDetailBean.y.containsKey(crashDetailBean.A)) {
                        str11 = (String) crashDetailBean.y.get(crashDetailBean.A);
                        int indexOf2 = str11.indexOf(substring);
                        if (indexOf2 > 0) {
                            str11 = str11.substring(indexOf2);
                            crashDetailBean.y.put(crashDetailBean.A, str11);
                            crashDetailBean.q = crashDetailBean.q.substring(0, indexOf);
                            crashDetailBean.q += str11;
                        }
                    }
                }
            }
            if (str == null) {
                crashDetailBean.z = this.c.e;
            }
            this.b.c(crashDetailBean);
        } else {
            crashDetailBean.B = -1;
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.M = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = -1;
            crashDetailBean.R = map;
            crashDetailBean.S = this.c.G();
            crashDetailBean.y = null;
            if (str == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.x = bArr;
            }
        }
        return crashDetailBean;
    }

    public void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        an.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    public void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        an.a("Native Crash Happen v2", new Object[0]);
        try {
            int i7;
            String str8;
            String str9;
            if (!this.d.b()) {
                an.e("waiting for remote sync", new Object[0]);
                i7 = 0;
                while (!this.d.b()) {
                    ap.b(500);
                    i7 += 500;
                    if (i7 >= 3000) {
                        break;
                    }
                }
            }
            String b = b.b(str3);
            String str10 = "UNKNOWN";
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + "(" + str5 + ")";
            } else {
                if (i4 > 0) {
                    str10 = AppInfo.a(this.a, i4);
                }
                if (str10.equals(String.valueOf(i4))) {
                    str8 = str5;
                    str9 = str;
                } else {
                    str10 = str10 + "(" + i4 + ")";
                    str8 = str5;
                    str9 = str;
                }
            }
            if (!this.d.b()) {
                an.d("no remote but still store!", new Object[0]);
            }
            if (this.d.c().g || !this.d.b()) {
                String str11;
                String str12;
                Map hashMap = new HashMap();
                if (strArr != null) {
                    for (String str112 : strArr) {
                        String[] split = str112.split("=");
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            an.d("bad extraMsg %s", new Object[]{str112});
                        }
                    }
                } else {
                    an.c("not found extraMsg", new Object[0]);
                }
                String str13 = (String) hashMap.get("ExceptionProcessName");
                if (str13 == null || str13.length() == 0) {
                    str112 = this.c.e;
                } else {
                    an.c("crash process name change to %s", new Object[]{str13});
                    str112 = str13;
                }
                str13 = (String) hashMap.get("ExceptionThreadName");
                if (str13 == null || str13.length() == 0) {
                    Thread currentThread = Thread.currentThread();
                    str12 = currentThread.getName() + "(" + currentThread.getId() + ")";
                } else {
                    an.c("crash thread name change to %s", new Object[]{str13});
                    for (Thread thread : Thread.getAllStackTraces().keySet()) {
                        if (thread.getName().equals(str13)) {
                            str12 = str13 + "(" + thread.getId() + ")";
                            break;
                        }
                    }
                    str12 = str13;
                }
                CrashDetailBean packageCrashDatas = packageCrashDatas(str112, str12, (j2 / 1000) + (1000 * j), str9, str2, b, str8, str10, str4, (String) hashMap.get("SysLogPath"), str7, null, null, true);
                if (packageCrashDatas == null) {
                    an.e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                b.a("NATIVE_CRASH", ap.a(), this.c.e, Thread.currentThread(), str9 + "\n" + str2 + "\n" + b, packageCrashDatas);
                if (!this.b.a(packageCrashDatas, i3)) {
                    this.b.a(packageCrashDatas, 3000, true);
                }
                this.b.b(packageCrashDatas);
                str13 = null;
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    str13 = instance.getDumpFilePath();
                }
                b.a(true, str13);
                return;
            }
            an.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            b.a("NATIVE_CRASH", ap.a(), this.c.e, Thread.currentThread(), str9 + "\n" + str2 + "\n" + b, null);
            ap.b(str4);
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
    }
}
