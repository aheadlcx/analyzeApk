package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.ae;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.ah;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.ay;
import com.tencent.bugly.proguard.ba;
import com.tencent.bugly.proguard.bb;
import com.tencent.bugly.proguard.bc;
import com.tencent.bugly.proguard.bd;
import com.tencent.bugly.proguard.be;
import com.tencent.bugly.proguard.m;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class b {
    public static int a = 0;
    protected final Context b;
    protected final ak c;
    protected final ae d;
    protected final a e;
    protected f f;
    protected BuglyStrategy$a g;

    public b(int i, Context context, ak akVar, ae aeVar, a aVar, BuglyStrategy$a buglyStrategy$a, f fVar) {
        a = i;
        this.b = context;
        this.c = akVar;
        this.d = aeVar;
        this.e = aVar;
        this.g = buglyStrategy$a;
        this.f = fVar;
    }

    protected List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        List<a> arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b <= currentTimeMillis - com.umeng.analytics.a.i) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    protected CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3 = null;
        List arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            List b = b(arrayList);
            if (b != null && b.size() > 0) {
                Collections.sort(b);
                int i = 0;
                while (i < b.size()) {
                    crashDetailBean2 = (CrashDetailBean) b.get(i);
                    if (i != 0) {
                        if (crashDetailBean2.s == null) {
                            crashDetailBean2 = crashDetailBean3;
                        } else {
                            String[] split = crashDetailBean2.s.split("\n");
                            if (split == null) {
                                crashDetailBean2 = crashDetailBean3;
                            } else {
                                for (String str : split) {
                                    if (!crashDetailBean3.s.contains("" + str)) {
                                        crashDetailBean3.t++;
                                        crashDetailBean3.s += str + "\n";
                                    }
                                }
                                crashDetailBean2 = crashDetailBean3;
                            }
                        }
                    }
                    i++;
                    crashDetailBean3 = crashDetailBean2;
                }
                crashDetailBean2 = crashDetailBean3;
                if (crashDetailBean2 != null) {
                    crashDetailBean.j = true;
                    crashDetailBean.t = 0;
                    crashDetailBean.s = "";
                    crashDetailBean3 = crashDetailBean;
                } else {
                    crashDetailBean3 = crashDetailBean2;
                }
                for (a aVar2 : list) {
                    if (!(aVar2.e || aVar2.d || crashDetailBean3.s.contains("" + aVar2.b))) {
                        crashDetailBean3.t++;
                        crashDetailBean3.s += aVar2.b + "\n";
                    }
                }
                if (crashDetailBean3.r == crashDetailBean.r && !crashDetailBean3.s.contains("" + crashDetailBean.r)) {
                    crashDetailBean3.t++;
                    crashDetailBean3.s += crashDetailBean.r + "\n";
                    return crashDetailBean3;
                }
            }
        }
        crashDetailBean2 = null;
        if (crashDetailBean2 != null) {
            crashDetailBean3 = crashDetailBean2;
        } else {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean3 = crashDetailBean;
        }
        for (a aVar22 : list) {
            crashDetailBean3.t++;
            crashDetailBean3.s += aVar22.b + "\n";
        }
        return crashDetailBean3.r == crashDetailBean.r ? crashDetailBean3 : crashDetailBean3;
    }

    public boolean a(CrashDetailBean crashDetailBean) {
        return a(crashDetailBean, -123456789);
    }

    public boolean a(CrashDetailBean crashDetailBean, int i) {
        if (crashDetailBean == null) {
            return true;
        }
        if (!(c.m == null || c.m.isEmpty())) {
            an.c("Crash filter for crash stack is: %s", new Object[]{c.m});
            if (crashDetailBean.q.contains(c.m)) {
                an.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (!(c.n == null || c.n.isEmpty())) {
            an.c("Crash regular filter for crash stack is: %s", new Object[]{c.n});
            if (Pattern.compile(c.n).matcher(crashDetailBean.q).find()) {
                an.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        boolean z = crashDetailBean.b == 1;
        String str = crashDetailBean.n;
        String str2 = crashDetailBean.p;
        String str3 = crashDetailBean.q;
        long j = crashDetailBean.r;
        String str4 = crashDetailBean.m;
        String str5 = crashDetailBean.e;
        String str6 = crashDetailBean.c;
        if (this.f != null) {
            if (!this.f.a(z, str, str2, str3, i, j, str4, str5, str6, crashDetailBean.z)) {
                an.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.b != 2) {
            ag agVar = new ag();
            agVar.b = 1;
            agVar.c = crashDetailBean.z;
            agVar.d = crashDetailBean.A;
            agVar.e = crashDetailBean.r;
            this.d.b(1);
            this.d.a(agVar);
            an.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            an.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> b = b();
        List list = null;
        if (b != null && b.size() > 0) {
            List arrayList = new ArrayList(10);
            List<a> arrayList2 = new ArrayList(10);
            arrayList.addAll(a((List) b));
            b.removeAll(arrayList);
            if (!com.tencent.bugly.b.c && c.d) {
                Object obj = null;
                for (a aVar : b) {
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            obj = 1;
                        }
                        arrayList2.add(aVar);
                    }
                    obj = obj;
                }
                if (obj != null || arrayList2.size() >= c.c) {
                    an.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a = a((List) arrayList2, crashDetailBean);
                    for (a aVar2 : arrayList2) {
                        if (aVar2.a != a.a) {
                            arrayList.add(aVar2);
                        }
                    }
                    e(a);
                    c(arrayList);
                    an.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            list = arrayList;
        }
        e(crashDetailBean);
        if (!(list == null || list.isEmpty())) {
            c(list);
        }
        an.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public List<CrashDetailBean> a() {
        StrategyBean c = a.a().c();
        if (c == null) {
            an.d("have not synced remote!", new Object[0]);
            return null;
        } else if (c.g) {
            long currentTimeMillis = System.currentTimeMillis();
            long b = ap.b();
            List b2 = b();
            if (b2 == null || b2.size() <= 0) {
                return null;
            }
            List arrayList = new ArrayList();
            Iterator it = b2.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.b < b - c.g) {
                    it.remove();
                    arrayList.add(aVar);
                } else if (aVar.d) {
                    if (aVar.b >= currentTimeMillis - com.umeng.analytics.a.i) {
                        it.remove();
                    } else if (!aVar.e) {
                        it.remove();
                        arrayList.add(aVar);
                    }
                } else if (((long) aVar.f) >= 3 && aVar.b < currentTimeMillis - com.umeng.analytics.a.i) {
                    it.remove();
                    arrayList.add(aVar);
                }
            }
            if (arrayList.size() > 0) {
                c(arrayList);
            }
            List arrayList2 = new ArrayList();
            List<CrashDetailBean> b3 = b(b2);
            if (b3 != null && b3.size() > 0) {
                String str = com.tencent.bugly.crashreport.common.info.a.b().o;
                Iterator it2 = b3.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
                    if (!str.equals(crashDetailBean.f)) {
                        it2.remove();
                        arrayList2.add(crashDetailBean);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                d(arrayList2);
            }
            return b3;
        } else {
            an.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            an.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
    }

    public void b(CrashDetailBean crashDetailBean) {
        boolean z = true;
        if (this.f != null) {
            f fVar = this.f;
            if (crashDetailBean.b != 1) {
                z = false;
            }
            fVar.b(z);
        }
    }

    public void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        boolean z2 = false;
        if (c.l) {
            an.a("try to upload right now", new Object[0]);
            List arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            if (crashDetailBean.b == 7) {
                z2 = true;
            }
            a(arrayList, j, z, z2, z);
        }
    }

    public void a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        if (!com.tencent.bugly.crashreport.common.info.a.a(this.b).g || this.c == null) {
            return;
        }
        if (z3 || this.c.b(c.a)) {
            StrategyBean c = this.e.c();
            if (!c.g) {
                an.d("remote report is disable!", new Object[0]);
                an.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            } else if (list != null && list.size() != 0) {
                try {
                    String str = this.c.b ? c.s : c.t;
                    String str2 = this.c.b ? StrategyBean.c : StrategyBean.a;
                    int i = this.c.b ? 830 : 630;
                    m a = a(this.b, (List) list, com.tencent.bugly.crashreport.common.info.a.b());
                    if (a == null) {
                        an.d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] a2 = ah.a(a);
                    if (a2 == null) {
                        an.d("send encode fail!", new Object[0]);
                        return;
                    }
                    bd a3 = ah.a(this.b, i, a2);
                    if (a3 == null) {
                        an.d("request package is null.", new Object[0]);
                        return;
                    }
                    aj anonymousClass1 = new aj(this) {
                        final /* synthetic */ b b;

                        public void a(int i) {
                        }

                        public void a(int i, be beVar, long j, long j2, boolean z, String str) {
                            this.b.a(z, list);
                        }
                    };
                    if (z) {
                        this.c.a(a, a3, str, str2, anonymousClass1, j, z2);
                    } else {
                        this.c.a(a, a3, str, str2, anonymousClass1, false);
                    }
                } catch (Throwable th) {
                    an.e("req cr error %s", new Object[]{th.toString()});
                    if (!an.b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            an.c("up finish update state %b", new Object[]{Boolean.valueOf(z)});
            for (CrashDetailBean crashDetailBean : list) {
                an.c("pre uid:%s uc:%d re:%b me:%b", new Object[]{crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j)});
                crashDetailBean.l++;
                crashDetailBean.d = z;
                an.c("set uid:%s uc:%d re:%b me:%b", new Object[]{crashDetailBean.c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.d), Boolean.valueOf(crashDetailBean.j)});
            }
            for (CrashDetailBean crashDetailBean2 : list) {
                c.a().a(crashDetailBean2);
            }
            an.c("update state size %d", new Object[]{Integer.valueOf(list.size())});
        }
        if (!z) {
            an.b("[crash] upload fail.", new Object[0]);
        }
    }

    public void c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            if (this.g != null || this.f != null) {
                try {
                    int i;
                    String b;
                    Map hashMap;
                    an.a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
                    switch (crashDetailBean.b) {
                        case 0:
                            i = 0;
                            break;
                        case 1:
                            i = 2;
                            break;
                        case 2:
                            i = 1;
                            break;
                        case 3:
                            i = 4;
                            break;
                        case 4:
                            i = 3;
                            break;
                        case 5:
                            i = 5;
                            break;
                        case 6:
                            i = 6;
                            break;
                        case 7:
                            i = 7;
                            break;
                        default:
                            return;
                    }
                    boolean z = crashDetailBean.b == 1;
                    String str = crashDetailBean.n;
                    String str2 = crashDetailBean.p;
                    String str3 = crashDetailBean.q;
                    long j = crashDetailBean.r;
                    if (this.f != null) {
                        this.f.a(z);
                        b = this.f.b(z, str, str2, str3, -1234567890, j);
                        if (b != null) {
                            hashMap = new HashMap(1);
                            hashMap.put("userData", b);
                        } else {
                            hashMap = null;
                        }
                    } else {
                        hashMap = this.g != null ? this.g.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q) : null;
                    }
                    if (hashMap != null && hashMap.size() > 0) {
                        crashDetailBean.O = new LinkedHashMap(hashMap.size());
                        for (Entry entry : hashMap.entrySet()) {
                            if (!ap.a((String) entry.getKey())) {
                                String str4;
                                String str5 = (String) entry.getKey();
                                if (str5.length() > 100) {
                                    str5 = str5.substring(0, 100);
                                    an.d("setted key length is over limit %d substring to %s", new Object[]{Integer.valueOf(100), str5});
                                }
                                b = str5;
                                if (ap.a((String) entry.getValue()) || ((String) entry.getValue()).length() <= BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH) {
                                    str4 = "" + ((String) entry.getValue());
                                } else {
                                    str4 = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                                    an.d("setted %s value length is over limit %d substring", new Object[]{b, Integer.valueOf(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH)});
                                }
                                crashDetailBean.O.put(b, str4);
                                an.a("add setted key %s value size:%d", new Object[]{b, Integer.valueOf(str4.length())});
                            }
                        }
                    }
                    an.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    byte[] bArr = null;
                    if (this.f != null) {
                        bArr = this.f.a(z, str, str2, str3, -1234567890, j);
                    } else if (this.g != null) {
                        bArr = this.g.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
                    }
                    crashDetailBean.T = bArr;
                    if (crashDetailBean.T != null) {
                        if (crashDetailBean.T.length > BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH) {
                            an.d("extra bytes size %d is over limit %d will drop over part", new Object[]{Integer.valueOf(crashDetailBean.T.length), Integer.valueOf(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH)});
                        }
                        an.a("add extra bytes %d ", new Object[]{Integer.valueOf(crashDetailBean.T.length)});
                    }
                } catch (Throwable th) {
                    an.d("crash handle callback somthing wrong! %s", new Object[]{th.getClass().getName()});
                    if (!an.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    protected ContentValues d(CrashDetailBean crashDetailBean) {
        int i = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            int i2;
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            String str = "_up";
            if (crashDetailBean.d) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            contentValues.put(str, Integer.valueOf(i2));
            String str2 = "_me";
            if (!crashDetailBean.j) {
                i = 0;
            }
            contentValues.put(str2, Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", ap.a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    protected CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) ap.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public void e(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            ContentValues d = d(crashDetailBean);
            if (d != null) {
                long a = ae.a().a("t_cr", d, null, true);
                if (a >= 0) {
                    an.c("insert %s success!", new Object[]{"t_cr"});
                    crashDetailBean.a = a;
                }
            }
            if (c.i) {
                f(crashDetailBean);
            }
        }
    }

    public List<CrashDetailBean> b(List<a> list) {
        Cursor a;
        Throwable th;
        Cursor cursor;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (a aVar : list) {
            stringBuilder.append(" or ").append("_id").append(" = ").append(aVar.a);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.length() > 0) {
            stringBuilder2 = stringBuilder2.substring(" or ".length());
        }
        stringBuilder.setLength(0);
        try {
            a = ae.a().a("t_cr", null, stringBuilder2, null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                List<CrashDetailBean> arrayList = new ArrayList();
                while (a.moveToNext()) {
                    CrashDetailBean a2 = a(a);
                    if (a2 != null) {
                        arrayList.add(a2);
                    } else {
                        try {
                            stringBuilder.append(" or ").append("_id").append(" = ").append(a.getLong(a.getColumnIndex("_id")));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                String stringBuilder3 = stringBuilder.toString();
                if (stringBuilder3.length() > 0) {
                    int a3 = ae.a().a("t_cr", stringBuilder3.substring(" or ".length()), null, null, true);
                    an.d("deleted %s illegle data %d", new Object[]{"t_cr", Integer.valueOf(a3)});
                }
                if (a != null) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th22) {
                th = th22;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    protected a b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            boolean z2;
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            if (cursor.getInt(cursor.getColumnIndex("_up")) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            aVar.d = z2;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (an.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public List<a> b() {
        Throwable th;
        Cursor cursor = null;
        List<a> arrayList = new ArrayList();
        Cursor a;
        try {
            a = ae.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (a == null) {
                if (a != null) {
                    a.close();
                }
                return null;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (a.moveToNext()) {
                    a b = b(a);
                    if (b != null) {
                        arrayList.add(b);
                    } else {
                        try {
                            stringBuilder.append(" or ").append("_id").append(" = ").append(a.getLong(a.getColumnIndex("_id")));
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                }
                String stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() > 0) {
                    int a2 = ae.a().a("t_cr", stringBuilder2.substring(" or ".length()), null, null, true);
                    an.d("deleted %s illegle data %d", new Object[]{"t_cr", Integer.valueOf(a2)});
                }
                if (a != null) {
                    a.close();
                }
                return arrayList;
            } catch (Throwable th22) {
                th = th22;
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    public void c(List<a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (a aVar : list) {
                stringBuilder.append(" or ").append("_id").append(" = ").append(aVar.a);
            }
            String stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.length() > 0) {
                stringBuilder2 = stringBuilder2.substring(" or ".length());
            }
            stringBuilder.setLength(0);
            try {
                int a = ae.a().a("t_cr", stringBuilder2, null, null, true);
                an.c("deleted %s data %d", new Object[]{"t_cr", Integer.valueOf(a)});
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        stringBuilder.append(" or ").append("_id").append(" = ").append(crashDetailBean.a);
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        stringBuilder2 = stringBuilder2.substring(" or ".length());
                    }
                    stringBuilder.setLength(0);
                    int a = ae.a().a("t_cr", stringBuilder2, null, null, true);
                    an.c("deleted %s data %d", new Object[]{"t_cr", Integer.valueOf(a)});
                }
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static bb a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        boolean z = true;
        if (context == null || crashDetailBean == null || aVar == null) {
            an.d("enExp args == null", new Object[0]);
            return null;
        }
        ba a;
        bb bbVar = new bb();
        switch (crashDetailBean.b) {
            case 0:
                bbVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                bbVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case 2:
                bbVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case 3:
                bbVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                bbVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                bbVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case 6:
                bbVar.a = crashDetailBean.j ? "206" : "106";
                break;
            case 7:
                bbVar.a = crashDetailBean.j ? "208" : "108";
                break;
            default:
                an.e("crash type error! %d", new Object[]{Integer.valueOf(crashDetailBean.b)});
                break;
        }
        bbVar.b = crashDetailBean.r;
        bbVar.c = crashDetailBean.n;
        bbVar.d = crashDetailBean.o;
        bbVar.e = crashDetailBean.p;
        bbVar.g = crashDetailBean.q;
        bbVar.h = crashDetailBean.y;
        bbVar.i = crashDetailBean.c;
        bbVar.j = null;
        bbVar.l = crashDetailBean.m;
        bbVar.m = crashDetailBean.e;
        bbVar.f = crashDetailBean.A;
        bbVar.t = com.tencent.bugly.crashreport.common.info.a.b().i();
        bbVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            bbVar.o = new ArrayList();
            for (Entry entry : crashDetailBean.i.entrySet()) {
                ay ayVar = new ay();
                ayVar.a = ((PlugInBean) entry.getValue()).a;
                ayVar.c = ((PlugInBean) entry.getValue()).c;
                ayVar.e = ((PlugInBean) entry.getValue()).b;
                ayVar.b = aVar.r();
                bbVar.o.add(ayVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            bbVar.p = new ArrayList();
            for (Entry entry2 : crashDetailBean.h.entrySet()) {
                ayVar = new ay();
                ayVar.a = ((PlugInBean) entry2.getValue()).a;
                ayVar.c = ((PlugInBean) entry2.getValue()).c;
                ayVar.e = ((PlugInBean) entry2.getValue()).b;
                bbVar.p.add(ayVar);
            }
        }
        if (crashDetailBean.j) {
            int size;
            bbVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (bbVar.q == null) {
                    bbVar.q = new ArrayList();
                }
                try {
                    bbVar.q.add(new ba((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    bbVar.q = null;
                }
            }
            String str = "crashcount:%d sz:%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(bbVar.k);
            if (bbVar.q != null) {
                size = bbVar.q.size();
            } else {
                size = 0;
            }
            objArr[1] = Integer.valueOf(size);
            an.c(str, objArr);
        }
        if (crashDetailBean.w != null) {
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            try {
                bbVar.q.add(new ba((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                bbVar.q = null;
            }
        }
        if (!ap.a(crashDetailBean.U)) {
            Object baVar;
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            try {
                baVar = new ba((byte) 1, "crashInfos.txt", crashDetailBean.U.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e22) {
                e22.printStackTrace();
                baVar = null;
            }
            if (baVar != null) {
                an.c("attach crash infos", new Object[0]);
                bbVar.q.add(baVar);
            }
        }
        if (crashDetailBean.V != null) {
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            a = a("backupRecord.zip", context, crashDetailBean.V);
            if (a != null) {
                an.c("attach backup record", new Object[0]);
                bbVar.q.add(a);
            }
        }
        if (crashDetailBean.x != null && crashDetailBean.x.length > 0) {
            a = new ba((byte) 2, "buglylog.zip", crashDetailBean.x);
            if (a != null) {
                an.c("attach user log", new Object[0]);
                if (bbVar.q == null) {
                    bbVar.q = new ArrayList();
                }
                bbVar.q.add(a);
            }
        }
        if (crashDetailBean.b == 3) {
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            if (crashDetailBean.O != null && crashDetailBean.O.containsKey("BUGLY_CR_01")) {
                try {
                    bbVar.q.add(new ba((byte) 1, "anrMessage.txt", ((String) crashDetailBean.O.get("BUGLY_CR_01")).getBytes("utf-8")));
                    an.c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e222) {
                    e222.printStackTrace();
                    bbVar.q = null;
                }
                crashDetailBean.O.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null) {
                a = a("trace.zip", context, crashDetailBean.v);
                if (a != null) {
                    an.c("attach traces", new Object[0]);
                    bbVar.q.add(a);
                }
            }
        }
        if (crashDetailBean.b == 1) {
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            if (crashDetailBean.v != null) {
                a = a("tomb.zip", context, crashDetailBean.v);
                if (a != null) {
                    an.c("attach tombs", new Object[0]);
                    bbVar.q.add(a);
                }
            }
        }
        if (!(aVar.H == null || aVar.H.isEmpty())) {
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : aVar.H) {
                stringBuilder.append(append);
            }
            try {
                bbVar.q.add(new ba((byte) 1, "martianlog.txt", stringBuilder.toString().getBytes("utf-8")));
                an.c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e2222) {
                e2222.printStackTrace();
            }
        }
        if (crashDetailBean.T != null && crashDetailBean.T.length > 0) {
            if (bbVar.q == null) {
                bbVar.q = new ArrayList();
            }
            bbVar.q.add(new ba((byte) 1, "userExtraByteData", crashDetailBean.T));
            an.c("attach extraData", new Object[0]);
        }
        bbVar.r = new HashMap();
        bbVar.r.put("A9", "" + crashDetailBean.B);
        bbVar.r.put("A11", "" + crashDetailBean.C);
        bbVar.r.put("A10", "" + crashDetailBean.D);
        bbVar.r.put("A23", "" + crashDetailBean.f);
        bbVar.r.put("A7", "" + aVar.k);
        bbVar.r.put("A6", "" + aVar.s());
        bbVar.r.put("A5", "" + aVar.r());
        bbVar.r.put("A22", "" + aVar.h());
        bbVar.r.put("A2", "" + crashDetailBean.F);
        bbVar.r.put("A1", "" + crashDetailBean.E);
        bbVar.r.put("A24", "" + aVar.m);
        bbVar.r.put("A17", "" + crashDetailBean.G);
        bbVar.r.put("A3", "" + aVar.k());
        bbVar.r.put("A16", "" + aVar.m());
        bbVar.r.put("A25", "" + aVar.n());
        bbVar.r.put("A14", "" + aVar.l());
        bbVar.r.put("A15", "" + aVar.w());
        bbVar.r.put("A13", "" + aVar.x());
        bbVar.r.put("A34", "" + crashDetailBean.z);
        if (aVar.C != null) {
            bbVar.r.put("productIdentify", "" + aVar.C);
        }
        try {
            bbVar.r.put("A26", "" + URLEncoder.encode(crashDetailBean.H, "utf-8"));
        } catch (UnsupportedEncodingException e22222) {
            e22222.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            bbVar.r.put("A27", "" + crashDetailBean.K);
            bbVar.r.put("A28", "" + crashDetailBean.J);
            bbVar.r.put("A29", "" + crashDetailBean.k);
        }
        bbVar.r.put("A30", "" + crashDetailBean.L);
        bbVar.r.put("A18", "" + crashDetailBean.M);
        bbVar.r.put("A36", "" + (!crashDetailBean.N));
        bbVar.r.put("F02", "" + aVar.v);
        bbVar.r.put("F03", "" + aVar.w);
        bbVar.r.put("F04", "" + aVar.e());
        bbVar.r.put("F05", "" + aVar.x);
        bbVar.r.put("F06", "" + aVar.u);
        bbVar.r.put("F08", "" + aVar.A);
        bbVar.r.put("F09", "" + aVar.B);
        bbVar.r.put("F10", "" + aVar.y);
        if (crashDetailBean.P >= 0) {
            bbVar.r.put("C01", "" + crashDetailBean.P);
        }
        if (crashDetailBean.Q >= 0) {
            bbVar.r.put("C02", "" + crashDetailBean.Q);
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Entry entry22 : crashDetailBean.R.entrySet()) {
                bbVar.r.put("C03_" + ((String) entry22.getKey()), entry22.getValue());
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            for (Entry entry222 : crashDetailBean.S.entrySet()) {
                bbVar.r.put("C04_" + ((String) entry222.getKey()), entry222.getValue());
            }
        }
        bbVar.s = null;
        if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
            bbVar.s = crashDetailBean.O;
            an.a("setted message size %d", new Object[]{Integer.valueOf(bbVar.s.size())});
        }
        String append2 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d";
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.e();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        if (crashDetailBean.b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(bbVar.r.size());
        an.c(append2, objArr2);
        return bbVar;
    }

    public static bc a(Context context, List<CrashDetailBean> list, com.tencent.bugly.crashreport.common.info.a aVar) {
        if (context == null || list == null || list.size() == 0 || aVar == null) {
            an.d("enEXPPkg args == null!", new Object[0]);
            return null;
        }
        bc bcVar = new bc();
        bcVar.a = new ArrayList();
        for (CrashDetailBean a : list) {
            bcVar.a.add(a(context, a, aVar));
        }
        return bcVar;
    }

    public static ba a(String str, Context context, String str2) {
        Throwable e;
        Throwable th;
        if (str2 == null || context == null) {
            an.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        an.c("zip %s", new Object[]{str2});
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (ap.a(file, file2, 5000)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                        byteArrayOutputStream.flush();
                    }
                    an.c("read bytes :%d", new Object[]{Integer.valueOf(byteArrayOutputStream.toByteArray().length)});
                    ba baVar = new ba((byte) 2, file2.getName(), bArr);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e2) {
                            if (!an.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    if (file2 != null && file2.exists()) {
                        an.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    return baVar;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!an.a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                if (!an.a(th3)) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                        if (file2 == null && file2.exists()) {
                            an.c("del tmp", new Object[0]);
                            file2.delete();
                            return null;
                        }
                    } catch (Throwable th4) {
                        e2 = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th32) {
                                if (!an.a(th32)) {
                                    th32.printStackTrace();
                                }
                            }
                        }
                        if (file2 != null && file2.exists()) {
                            an.c("del tmp", new Object[0]);
                            file2.delete();
                        }
                        throw e2;
                    }
                }
            } catch (Throwable th322) {
                fileInputStream = null;
                e2 = th322;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                an.c("del tmp", new Object[0]);
                file2.delete();
                throw e2;
            }
        }
        an.d("zip fail!", new Object[0]);
        return null;
    }

    private boolean f(CrashDetailBean crashDetailBean) {
        try {
            an.c("save eup logs", new Object[0]);
            com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
            String f = b.f();
            String str = b.o;
            String str2 = crashDetailBean.z;
            r8 = new Object[9];
            b.getClass();
            r8[2] = "2.6.5";
            r8[3] = str2;
            r8[4] = ap.a(new Date(crashDetailBean.r));
            r8[5] = crashDetailBean.n;
            r8[6] = crashDetailBean.o;
            r8[7] = crashDetailBean.q;
            r8[8] = crashDetailBean.c;
            f = String.format(Locale.US, "#--------\npackage:%s\nversion:%s\nsdk:%s\nprocess:%s\ndate:%s\ntype:%s\nmessage:%s\nstack:\n%s\neupID:%s\n", r8);
            String str3 = null;
            if (c.j != null) {
                File file = new File(c.j);
                if (file.isFile()) {
                    file = file.getParentFile();
                }
                str3 = file.getAbsolutePath();
            } else if (Environment.getExternalStorageState().equals("mounted")) {
                str3 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tencent/" + this.b.getPackageName();
            }
            ap.a(this.b, str3 + "/euplog.txt", f, c.k);
            return true;
        } catch (Throwable th) {
            an.d("rqdp{  save error} %s", new Object[]{th.toString()});
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public static void a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
        if (b != null) {
            an.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            an.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            an.e("# PKG NAME: %s", new Object[]{b.d});
            an.e("# APP VER: %s", new Object[]{b.o});
            an.e("# LAUNCH TIME: %s", new Object[]{ap.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a))});
            an.e("# CRASH TYPE: %s", new Object[]{str});
            an.e("# CRASH TIME: %s", new Object[]{str2});
            an.e("# CRASH PROCESS: %s", new Object[]{str3});
            if (thread != null) {
                an.e("# CRASH THREAD: %s", new Object[]{thread.getName()});
            }
            if (crashDetailBean != null) {
                an.e("# REPORT ID: %s", new Object[]{crashDetailBean.c});
                String str5 = "# CRASH DEVICE: %s %s";
                Object[] objArr = new Object[2];
                objArr[0] = b.l;
                objArr[1] = b.x().booleanValue() ? "ROOTED" : "UNROOT";
                an.e(str5, objArr);
                an.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", new Object[]{Long.valueOf(crashDetailBean.B), Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D)});
                an.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", new Object[]{Long.valueOf(crashDetailBean.E), Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G)});
                if (!ap.a(crashDetailBean.K)) {
                    an.e("# EXCEPTION FIRED BY %s %s", new Object[]{crashDetailBean.K, crashDetailBean.J});
                } else if (crashDetailBean.b == 3) {
                    String str6;
                    str5 = "# EXCEPTION ANR MESSAGE:\n %s";
                    objArr = new Object[1];
                    if (crashDetailBean.O == null) {
                        str6 = "null";
                    } else {
                        str6 = "" + ((String) crashDetailBean.O.get("BUGLY_CR_01"));
                    }
                    objArr[0] = str6;
                    an.e(str5, objArr);
                }
            }
            if (!ap.a(str4)) {
                an.e("# CRASH STACK: ", new Object[0]);
                an.e(str4, new Object[0]);
            }
            an.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
