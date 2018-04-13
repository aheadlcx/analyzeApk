package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import com.loc.be.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

public abstract class ad {
    static final List a = Collections.synchronizedList(new ArrayList());
    private s b;
    private int c;
    private bf d;
    private be e;

    class a implements bf {
        final /* synthetic */ ad a;
        private an b;

        a(ad adVar, an anVar) {
            this.a = adVar;
            this.b = anVar;
        }

        public final void a(String str) {
            try {
                this.b.b(str, x.a(this.a.b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    protected ad(int i) {
        this.c = i;
    }

    private static be a(Context context, String str) {
        be beVar = null;
        try {
            File file = new File(x.a(context, str));
            if (file.exists() || file.mkdirs()) {
                beVar = be.a(file, 20480);
            }
        } catch (Throwable e) {
            w.a(e, "LogProcessor", "initDiskLru");
        } catch (Throwable e2) {
            w.a(e2, "LogProcessor", "initDiskLru");
        }
        return beVar;
    }

    private bf a(an anVar) {
        try {
            if (this.d == null) {
                this.d = new a(this, anVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.d;
    }

    private static String a(Throwable th) {
        String str = null;
        try {
            str = t.a(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return str;
    }

    public static List a() {
        return a;
    }

    private static void a(an anVar, String str, String str2, int i) {
        ao b = x.b(i);
        b.a(0);
        b.b(str);
        b.a(str2);
        anVar.a(b);
    }

    private void a(List<? extends ao> list, an anVar) {
        if (list != null && list.size() > 0) {
            for (ao aoVar : list) {
                if (c(aoVar.b())) {
                    anVar.a(aoVar.b(), aoVar.getClass());
                } else {
                    aoVar.a(2);
                    anVar.b(aoVar);
                }
            }
        }
    }

    private boolean a(Context context, String str, String str2, String str3, an anVar) {
        be a;
        Throwable th;
        OutputStream a2;
        IOException e;
        b bVar;
        Object obj;
        OutputStream outputStream;
        Throwable th2;
        OutputStream outputStream2;
        Object obj2;
        b bVar2 = null;
        OutputStream outputStream3 = null;
        be beVar = null;
        b bVar3 = null;
        try {
            File file = new File(x.a(context, str2));
            if (file.exists() || file.mkdirs()) {
                a = be.a(file, 20480);
                try {
                    a.a(a(anVar));
                    b a3 = a.a(str);
                    if (a3 != null) {
                        if (bVar2 != null) {
                            try {
                                outputStream3.close();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                        if (a3 != null) {
                            try {
                                a3.close();
                            } catch (Throwable th32) {
                                th32.printStackTrace();
                            }
                        }
                        if (a == null || a.c()) {
                            return false;
                        }
                        try {
                            a.close();
                            return false;
                        } catch (Throwable th4) {
                            th32 = th4;
                            th32.printStackTrace();
                            return false;
                        }
                    }
                    byte[] a4;
                    com.loc.be.a b;
                    try {
                        a4 = t.a(str3);
                        b = a.b(str);
                        a2 = b.a();
                    } catch (IOException e2) {
                        e = e2;
                        bVar = a3;
                        beVar = a;
                        obj = bVar2;
                        bVar2 = bVar;
                        try {
                            e.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th322) {
                                    th322.printStackTrace();
                                }
                            }
                            if (bVar2 != null) {
                                try {
                                    bVar2.close();
                                } catch (Throwable th3222) {
                                    th3222.printStackTrace();
                                }
                            }
                            return beVar == null ? false : false;
                        } catch (Throwable th5) {
                            th2 = th5;
                            be beVar2 = beVar;
                            outputStream2 = outputStream;
                            a = beVar2;
                            if (outputStream2 != null) {
                                try {
                                    outputStream2.close();
                                } catch (Throwable th32222) {
                                    th32222.printStackTrace();
                                }
                            }
                            if (bVar2 != null) {
                                try {
                                    bVar2.close();
                                } catch (Throwable th322222) {
                                    th322222.printStackTrace();
                                }
                            }
                            try {
                                a.close();
                            } catch (Throwable th3222222) {
                                th3222222.printStackTrace();
                            }
                            throw th2;
                        }
                    } catch (Throwable th6) {
                        th2 = th6;
                        bVar = a3;
                        obj2 = bVar2;
                        bVar2 = bVar;
                        if (outputStream2 != null) {
                            outputStream2.close();
                        }
                        if (bVar2 != null) {
                            bVar2.close();
                        }
                        a.close();
                        throw th2;
                    }
                    try {
                        a2.write(a4);
                        b.b();
                        a.d();
                        if (a2 != null) {
                            try {
                                a2.close();
                            } catch (Throwable th22) {
                                th22.printStackTrace();
                            }
                        }
                        if (a3 != null) {
                            try {
                                a3.close();
                            } catch (Throwable th222) {
                                th222.printStackTrace();
                            }
                        }
                        if (!(a == null || a.c())) {
                            try {
                                a.close();
                            } catch (Throwable th2222) {
                                th2222.printStackTrace();
                            }
                        }
                        return true;
                    } catch (IOException e3) {
                        e = e3;
                        bVar = a3;
                        beVar = a;
                        outputStream = a2;
                        bVar2 = bVar;
                        e.printStackTrace();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (bVar2 != null) {
                            bVar2.close();
                        }
                        if (beVar == null) {
                        }
                    } catch (Throwable th7) {
                        th2222 = th7;
                        bVar = a3;
                        outputStream2 = a2;
                        bVar2 = bVar;
                        if (outputStream2 != null) {
                            outputStream2.close();
                        }
                        if (bVar2 != null) {
                            bVar2.close();
                        }
                        a.close();
                        throw th2222;
                    }
                } catch (IOException e4) {
                    e = e4;
                    beVar = a;
                    obj = bVar2;
                    e.printStackTrace();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (bVar2 != null) {
                        bVar2.close();
                    }
                    if (beVar == null) {
                    }
                } catch (Throwable th8) {
                    th2222 = th8;
                    obj2 = bVar2;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (bVar2 != null) {
                        bVar2.close();
                    }
                    a.close();
                    throw th2222;
                }
            }
            if (bVar2 != null) {
                try {
                    outputStream3.close();
                } catch (Throwable th32222222) {
                    th32222222.printStackTrace();
                }
            }
            if (bVar2 != null) {
                try {
                    bVar3.close();
                } catch (Throwable th322222222) {
                    th322222222.printStackTrace();
                }
            }
            if (bVar2 == null || bVar2.c()) {
                return false;
            }
            try {
                beVar.close();
                return false;
            } catch (Throwable th9) {
                th322222222 = th9;
            }
        } catch (IOException e5) {
            e = e5;
            beVar = bVar2;
            outputStream = bVar2;
            e.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
            if (bVar2 != null) {
                bVar2.close();
            }
            if (beVar == null && !beVar.c()) {
                try {
                    beVar.close();
                    return false;
                } catch (Throwable th10) {
                    th322222222 = th10;
                    th322222222.printStackTrace();
                    return false;
                }
            }
        } catch (Throwable th11) {
            th2222 = th11;
            a = bVar2;
            outputStream2 = bVar2;
            if (outputStream2 != null) {
                outputStream2.close();
            }
            if (bVar2 != null) {
                bVar2.close();
            }
            a.close();
            throw th2222;
        }
    }

    public static boolean a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String str2 : strArr) {
                str = str.trim();
                if (str.startsWith("at ") && str.contains(str2 + ".") && str.endsWith(")")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private static String b(String str) {
        String str2 = null;
        try {
            str2 = m.c(t.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str2;
    }

    private static boolean b(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String trim : str.split("\n")) {
                if (a(strArr, trim.trim())) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private boolean c(String str) {
        boolean z = false;
        if (this.e != null) {
            try {
                z = this.e.c(str);
            } catch (Throwable th) {
                w.a(th, "LogUpdateProcessor", "deleteLogData-" + str);
            }
        }
        return z;
    }

    private static int d(String str) {
        int i = 0;
        bn yVar = new y(t.c(t.a(str)));
        try {
            bi.a();
            byte[] a = bi.a(yVar);
            if (a == null) {
                return 0;
            }
            try {
                JSONObject jSONObject = new JSONObject(t.a(a));
                String str2 = "code";
                return jSONObject.has(str2) ? jSONObject.getInt(str2) : 0;
            } catch (Throwable e) {
                w.a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (Throwable e2) {
            if (e2.e() != 27) {
                i = 1;
            }
            w.a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    private static List<s> d(Context context) {
        List<s> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<s> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new ap(context, false).a();
                    try {
                    } catch (Throwable th22) {
                        th = th22;
                        list = a;
                        th3 = th;
                        try {
                            throw th3;
                        } catch (Throwable th32) {
                            th = th32;
                            a = list;
                            th22 = th;
                            th22.printStackTrace();
                            return a;
                        }
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                    throw th32;
                }
            }
        } catch (Throwable th322) {
            th = th322;
            a = null;
            th22 = th;
            th22.printStackTrace();
            return a;
        }
    }

    private static String e(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(k.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(n.q(context)).append("\",\"pkg\":\"").append(k.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(k.b(context)).append("\",\"appversion\":\"").append(k.d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            w.a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    private String e(String str) {
        Throwable e;
        String str2;
        String str3;
        Throwable th;
        Object obj;
        String str4 = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream a;
        try {
            if (this.e == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e2) {
                        w.a(e2, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        w.a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            b a2 = this.e.a(str);
            if (a2 == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        w.a(e22, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        w.a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            a = a2.a();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = a.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    str4 = t.a(byteArrayOutputStream.toByteArray());
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e5) {
                            w.a(e5, "LogProcessor", "readLog1");
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e6) {
                            e5 = e6;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            w.a(e5, str2, str3);
                            return str4;
                        }
                    }
                } catch (Throwable th2) {
                    e5 = th2;
                    try {
                        w.a(e5, "LogProcessor", "readLog");
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e52) {
                                w.a(e52, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e7) {
                                e52 = e7;
                                str2 = "LogProcessor";
                                str3 = "readLog2";
                                w.a(e52, str2, str3);
                                return str4;
                            }
                        }
                        return str4;
                    } catch (Throwable th3) {
                        th = th3;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e522) {
                                w.a(e522, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable e5222) {
                                w.a(e5222, "LogProcessor", "readLog2");
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable e52222) {
                obj = str4;
                th = e52222;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
            return str4;
        } catch (Throwable e522222) {
            byteArrayOutputStream = str4;
            a = str4;
            th = e522222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    private static String f(Context context) {
        String str = null;
        try {
            String e = e(context);
            if (!"".equals(e)) {
                str = m.b(t.a(e));
            }
        } catch (Throwable th) {
            w.a(th, "LogProcessor", "getPublicInfo");
        }
        return str;
    }

    protected String a(String str) {
        return p.c(str);
    }

    protected abstract String a(List<s> list);

    final void a(Context context, Throwable th, String str, String str2) {
        List<s> d = d(context);
        if (d != null && d.size() != 0) {
            String a = a(th);
            if (a != null && !"".equals(a)) {
                for (s sVar : d) {
                    if (b(sVar.f(), a)) {
                        a(sVar, context, th, a.replaceAll("\n", "<br/>"), str, str2);
                        return;
                    }
                }
                if (a.contains("com.amap.api.col")) {
                    try {
                        a(t.a(), context, th, a, str, str2);
                    } catch (j e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected final void a(s sVar) {
        this.b = sVar;
    }

    final void a(s sVar, Context context, String str, String str2, String str3, String str4) {
        this.b = sVar;
        String a = bt.a();
        String a2 = bt.a(context, sVar);
        k.a(context);
        if (str != null && !"".equals(str)) {
            int i = this.c;
            StringBuilder stringBuilder = new StringBuilder();
            if (str3 != null) {
                stringBuilder.append("class:").append(str3);
            }
            if (str4 != null) {
                stringBuilder.append(" method:").append(str4).append("$<br/>");
            }
            stringBuilder.append(str2);
            String a3 = a(str2);
            a = bt.a(a2, a, i, str, stringBuilder.toString());
            if (a != null && !"".equals(a)) {
                String b = b(a);
                String c = x.c(this.c);
                synchronized (Looper.getMainLooper()) {
                    an anVar = new an(context);
                    a(context, a3, c, b, anVar);
                    a(anVar, sVar.a(), a3, i);
                }
            }
        }
    }

    final void a(s sVar, Context context, Throwable th, String str, String str2, String str3) {
        a(sVar, context, th.toString(), str, str2, str3);
    }

    protected abstract boolean a(Context context);

    protected final int b() {
        return this.c;
    }

    final void b(Context context) {
        List d = d(context);
        if (d != null && d.size() != 0) {
            String a = a(d);
            if (a != null && !"".equals(a)) {
                String a2 = bt.a();
                String a3 = bt.a(context, this.b);
                k.a(context);
                int i = this.c;
                a2 = bt.a(a3, a2, i, "ANR", a);
                if (a2 != null && !"".equals(a2)) {
                    a3 = a(a);
                    String b = b(a2);
                    String c = x.c(this.c);
                    synchronized (Looper.getMainLooper()) {
                        an anVar = new an(context);
                        a(context, a3, c, b, anVar);
                        a(anVar, this.b.a(), a3, i);
                    }
                }
            }
        }
    }

    final void c() {
        try {
            if (this.e != null && !this.e.c()) {
                this.e.close();
            }
        } catch (Throwable e) {
            w.a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable e2) {
            w.a(e2, "LogProcessor", "closeDiskLru");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void c(android.content.Context r12) {
        /*
        r11 = this;
        r2 = 1;
        r3 = 0;
        r0 = r11.a(r12);	 Catch:{ Throwable -> 0x0045 }
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        r4 = android.os.Looper.getMainLooper();	 Catch:{ Throwable -> 0x0045 }
        monitor-enter(r4);	 Catch:{ Throwable -> 0x0045 }
        r0 = r11.c;	 Catch:{ Throwable -> 0x004e }
        r0 = com.loc.x.c(r0);	 Catch:{ Throwable -> 0x004e }
        r0 = a(r12, r0);	 Catch:{ Throwable -> 0x004e }
        r11.e = r0;	 Catch:{ Throwable -> 0x004e }
    L_0x001a:
        r5 = new com.loc.an;	 Catch:{ all -> 0x0042 }
        r5.<init>(r12);	 Catch:{ all -> 0x0042 }
        r0 = r11.c;	 Catch:{ all -> 0x0042 }
        r1 = 2;
        r0 = com.loc.x.a(r0);	 Catch:{ Throwable -> 0x0057 }
        r0 = r5.a(r1, r0);	 Catch:{ Throwable -> 0x0057 }
        r11.a(r0, r5);	 Catch:{ Throwable -> 0x0057 }
    L_0x002d:
        r0 = 0;
        r1 = r11.c;	 Catch:{ all -> 0x0042 }
        r1 = com.loc.x.a(r1);	 Catch:{ all -> 0x0042 }
        r6 = r5.a(r0, r1);	 Catch:{ all -> 0x0042 }
        if (r6 == 0) goto L_0x0040;
    L_0x003a:
        r0 = r6.size();	 Catch:{ all -> 0x0042 }
        if (r0 != 0) goto L_0x0060;
    L_0x0040:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        goto L_0x0008;
    L_0x0042:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ Throwable -> 0x0045 }
        throw r0;	 Catch:{ Throwable -> 0x0045 }
    L_0x0045:
        r0 = move-exception;
        r1 = "LogProcessor";
        r2 = "processUpdateLog";
        com.loc.w.a(r0, r1, r2);
        goto L_0x0008;
    L_0x004e:
        r0 = move-exception;
        r1 = "LogProcessor";
        r5 = "LogUpDateProcessor";
        com.loc.w.a(r0, r1, r5);	 Catch:{ all -> 0x0042 }
        goto L_0x001a;
    L_0x0057:
        r0 = move-exception;
        r1 = "LogProcessor";
        r6 = "processDeleteFail";
        com.loc.w.a(r0, r1, r6);	 Catch:{ all -> 0x0042 }
        goto L_0x002d;
    L_0x0060:
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0042 }
        r7.<init>();	 Catch:{ all -> 0x0042 }
        r0 = "{\"pinfo\":\"";
        r0 = r7.append(r0);	 Catch:{ all -> 0x0042 }
        r1 = f(r12);	 Catch:{ all -> 0x0042 }
        r0 = r0.append(r1);	 Catch:{ all -> 0x0042 }
        r1 = "\",\"els\":[";
        r0.append(r1);	 Catch:{ all -> 0x0042 }
        r8 = r6.iterator();	 Catch:{ all -> 0x0042 }
        r1 = r2;
    L_0x007d:
        r0 = r8.hasNext();	 Catch:{ all -> 0x0042 }
        if (r0 == 0) goto L_0x00d1;
    L_0x0083:
        r0 = r8.next();	 Catch:{ all -> 0x0042 }
        r0 = (com.loc.ao) r0;	 Catch:{ all -> 0x0042 }
        r9 = r0.b();	 Catch:{ all -> 0x0042 }
        r9 = r11.e(r9);	 Catch:{ all -> 0x0042 }
        if (r9 == 0) goto L_0x00f1;
    L_0x0093:
        r10 = "";
        r10 = r10.equals(r9);	 Catch:{ all -> 0x0042 }
        if (r10 != 0) goto L_0x00f1;
    L_0x009b:
        r10 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0042 }
        r10.<init>();	 Catch:{ all -> 0x0042 }
        r9 = r10.append(r9);	 Catch:{ all -> 0x0042 }
        r10 = "||";
        r9 = r9.append(r10);	 Catch:{ all -> 0x0042 }
        r0 = r0.c();	 Catch:{ all -> 0x0042 }
        r0 = r9.append(r0);	 Catch:{ all -> 0x0042 }
        r9 = r0.toString();	 Catch:{ all -> 0x0042 }
        if (r1 == 0) goto L_0x00ca;
    L_0x00b8:
        r0 = r3;
    L_0x00b9:
        r1 = "{\"log\":\"";
        r1 = r7.append(r1);	 Catch:{ all -> 0x0042 }
        r1 = r1.append(r9);	 Catch:{ all -> 0x0042 }
        r9 = "\"}";
        r1.append(r9);	 Catch:{ all -> 0x0042 }
    L_0x00c8:
        r1 = r0;
        goto L_0x007d;
    L_0x00ca:
        r0 = ",";
        r7.append(r0);	 Catch:{ all -> 0x0042 }
        r0 = r1;
        goto L_0x00b9;
    L_0x00d1:
        if (r1 == 0) goto L_0x00d9;
    L_0x00d3:
        r0 = 0;
    L_0x00d4:
        if (r0 != 0) goto L_0x00e3;
    L_0x00d6:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        goto L_0x0008;
    L_0x00d9:
        r0 = "]}";
        r7.append(r0);	 Catch:{ all -> 0x0042 }
        r0 = r7.toString();	 Catch:{ all -> 0x0042 }
        goto L_0x00d4;
    L_0x00e3:
        r0 = d(r0);	 Catch:{ all -> 0x0042 }
        if (r0 != r2) goto L_0x00ee;
    L_0x00e9:
        r0 = r11.c;	 Catch:{ all -> 0x0042 }
        r11.a(r6, r5);	 Catch:{ all -> 0x0042 }
    L_0x00ee:
        monitor-exit(r4);	 Catch:{ all -> 0x0042 }
        goto L_0x0008;
    L_0x00f1:
        r0 = r1;
        goto L_0x00c8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ad.c(android.content.Context):void");
    }
}
