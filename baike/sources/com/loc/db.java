package com.loc;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONObject;

public final class db {
    private static List<br> h = new ArrayList();
    private static JSONArray i = null;
    public SparseArray<Long> a = new SparseArray();
    public int b = -1;
    public long c = 0;
    String[] d = new String[]{"ol", Config.CELL_LOCATION, Config.GPS_LOCATION, "ha", "bs", "ds"};
    public int e = -1;
    public long f = -1;
    boolean g = false;
    private LinkedList<bx> j = new LinkedList();

    public static void a(Context context) {
        if (context != null) {
            try {
                if (cv.o()) {
                    if (h != null && h.size() > 0) {
                        List arrayList = new ArrayList();
                        arrayList.addAll(h);
                        bs.a(arrayList, context);
                        h.clear();
                    }
                    f(context);
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "destroy");
            }
        }
    }

    public static void a(Context context, int i, int i2, long j, long j2) {
        if (i != -1 && i2 != -1) {
            try {
                String str = "O012";
                if (context == null) {
                    return;
                }
                if (cv.o()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("param_int_first", i);
                    jSONObject.put("param_int_second", i2);
                    jSONObject.put("param_long_first", j);
                    jSONObject.put("param_long_second", j2);
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "reportServiceAliveTime");
            }
        }
    }

    public static void a(Context context, int i, AMapLocation aMapLocation) {
        Object obj = null;
        Object obj2 = 1;
        try {
            String str = "net";
            int i2 = aMapLocation.getErrorCode() == 0 ? 1 : 0;
            int i3;
            switch (i) {
                case 2:
                case 4:
                case 7:
                case 8:
                    str = "cache";
                    i3 = 1;
                    break;
                case 5:
                case 6:
                    str = "net";
                    i3 = 1;
                    break;
            }
            if (aMapLocation.getErrorCode() != 0) {
                switch (aMapLocation.getErrorCode()) {
                    case 4:
                    case 5:
                    case 6:
                    case 11:
                        str = "net";
                        break;
                }
            }
            obj2 = obj;
            if (obj2 != null) {
                a(context, "O005", i2, str);
            }
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "reportBatting");
        }
    }

    public static void a(Context context, long j) {
        if (context != null) {
            try {
                if (cv.o()) {
                    a(context, "O004", Long.valueOf(j).intValue(), null);
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    public static void a(Context context, da daVar) {
        Object obj = 1;
        Object obj2 = null;
        if (context != null) {
            try {
                if (cv.o()) {
                    int intValue;
                    AMapLocationServer c = daVar.c();
                    int intValue2 = Long.valueOf(daVar.b() - daVar.a()).intValue();
                    String str = "net";
                    if (c != null) {
                        intValue = Long.valueOf(c.j()).intValue();
                        switch (c.getLocationType()) {
                            case 1:
                                obj = null;
                                break;
                            case 2:
                            case 4:
                                str = "cache";
                                int i = 1;
                                break;
                            case 5:
                            case 6:
                                str = "net";
                                break;
                        }
                    }
                    intValue = 0;
                    if (obj != null) {
                        if (obj2 == null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("param_int_first", intValue);
                            jSONObject.put("param_int_second", intValue2);
                            a(context, "O003", jSONObject);
                        }
                        a(context, "O002", intValue2, str);
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "reportLBSLocUseTime");
            }
        }
    }

    public static void a(Context context, String str) {
        try {
            a(context, "O010", 0, str);
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "reportDex_dexFunction");
        }
    }

    public static void a(Context context, String str, int i) {
        try {
            a(context, "O009", i, str);
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "reportDex_dexLoadClass");
        }
    }

    private static void a(Context context, String str, int i, String str2) {
        if (context != null) {
            try {
                if (cv.o()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (i != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i);
                    }
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    private static void a(Context context, String str, JSONObject jSONObject) {
        if (context != null) {
            try {
                if (cv.o()) {
                    br brVar = new br(context, "loc", "3.4.0", str);
                    brVar.a(jSONObject.toString());
                    h.add(brVar);
                    if (h.size() >= 100) {
                        List arrayList = new ArrayList();
                        arrayList.addAll(h);
                        bs.a(arrayList, context);
                        h.clear();
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "applyStatistics");
            }
        }
    }

    public static void a(String str, int i) {
        String valueOf = String.valueOf(i);
        String str2 = "";
        switch (i) {
            case 2011:
                str2 = "ContextIsNull";
                break;
            case 2021:
                str2 = "OnlyMainWifi";
                break;
            case 2022:
                str2 = "OnlyOneWifiButNotMain";
                break;
            case 2031:
                str2 = "CreateApsReqException";
                break;
            case 2041:
                str2 = "ResponseResultIsNull";
                break;
            case 2051:
                str2 = "NeedLoginNetWork\t";
                break;
            case 2052:
                str2 = "MaybeIntercepted";
                break;
            case 2053:
                str2 = "DecryptResponseException";
                break;
            case 2054:
                str2 = "ParserDataException";
                break;
            case 2061:
                str2 = "ServerRetypeError";
                break;
            case 2062:
                str2 = "ServerLocFail";
                break;
            case 2081:
                str2 = "LocalLocException";
                break;
            case 2091:
                str2 = "InitException";
                break;
            case 2101:
                str2 = "BindAPSServiceException";
                break;
            case 2102:
                str2 = "AuthClientScodeFail";
                break;
            case 2111:
                str2 = "ErrorCgiInfo";
                break;
            case 2121:
                str2 = "NotLocPermission";
                break;
            case 2131:
                str2 = "NoCgiOrWifiInfo";
                break;
            case 2141:
                str2 = "NoEnoughStatellites";
                break;
            case 2151:
                str2 = "MaybeMockNetLoc";
                break;
            case 2152:
                str2 = "MaybeMockGPSLoc";
                break;
        }
        a(str, valueOf, str2);
    }

    public static void a(String str, String str2) {
        try {
            z.b(cw.b(), str2, str);
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "reportLog");
        }
    }

    public static void a(String str, String str2, String str3) {
        try {
            z.a(cw.b(), "/mobile/binary", str3, str, str2);
        } catch (Throwable th) {
        }
    }

    public static void a(String str, Throwable th) {
        try {
            if (th instanceof j) {
                z.a(cw.b(), str, (j) th);
            }
        } catch (Throwable th2) {
        }
    }

    public static boolean a(Context context, s sVar) {
        boolean z = false;
        try {
            z = au.a(context, sVar);
        } catch (Throwable th) {
        }
        return z;
    }

    public static void b(Context context, int i, AMapLocation aMapLocation) {
        int i2 = 1;
        int i3 = 0;
        switch (i) {
            case 1:
                i3 = 1;
                i2 = 0;
                break;
            case 2:
            case 4:
            case 8:
                i3 = 1;
                break;
            default:
                i2 = 0;
                break;
        }
        if (i3 != 0) {
            try {
                if (i == null) {
                    i = new JSONArray();
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lon", aMapLocation.getLongitude());
                jSONObject.put("lat", aMapLocation.getLatitude());
                jSONObject.put("type", i2);
                jSONObject.put("timestamp", de.a());
                JSONArray put = i.put(jSONObject);
                i = put;
                if (put.length() >= cv.p()) {
                    f(context);
                }
            } catch (Throwable th) {
                cw.a(th, "ReportUtil", "recordOfflineLocLog");
            }
        }
    }

    private static void f(Context context) {
        try {
            if (i != null && i.length() > 0) {
                bq.a(new bp(context, cw.b(), i.toString()), context);
                i = null;
            }
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "writeOfflineLocLog");
        }
    }

    public final void a() {
        this.g = false;
        if (this.j != null) {
            this.j.clear();
        }
    }

    public final void a(Context context, int i) {
        try {
            if (this.b != i) {
                if (!(this.b == -1 || this.b == i)) {
                    long b = de.b() - this.c;
                    this.a.append(this.b, Long.valueOf(((Long) this.a.get(this.b, Long.valueOf(0))).longValue() + b));
                }
                this.c = de.b() - dd.b(context, "pref", this.d[i], 0);
                this.b = i;
            }
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "setLocationType");
        }
    }

    public final void a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        try {
            int i;
            switch (eb.a[aMapLocationClientOption.getLocationMode().ordinal()]) {
                case 1:
                    i = 4;
                    break;
                case 2:
                    i = 5;
                    break;
                case 3:
                    i = 3;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (this.e != i) {
                if (!(this.e == -1 || this.e == i)) {
                    this.a.append(this.e, Long.valueOf((de.b() - this.f) + ((Long) this.a.get(this.e, Long.valueOf(0))).longValue()));
                }
                this.f = de.b() - dd.b(context, "pref", this.d[i], 0);
                this.e = i;
            }
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "setLocationMode");
        }
    }

    public final void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        bx bxVar;
        if (aMapLocation.equals(aMapLocation2)) {
            bxVar = new bx(aMapLocation, 0);
            if (!this.j.contains(bxVar)) {
                if (!this.g && this.j.size() >= 5) {
                    this.j.removeFirst();
                }
                this.j.add(bxVar);
            }
        } else {
            if (!this.g) {
                this.g = true;
            }
            bxVar = new bx(aMapLocation, 1);
            if (!this.j.contains(bxVar)) {
                this.j.add(bxVar);
            }
        }
        if (this.j.size() >= 10) {
            LinkedList linkedList = (LinkedList) this.j.clone();
            this.j.clear();
            this.g = false;
            if (linkedList != null && !linkedList.isEmpty()) {
                StringBuffer stringBuffer = new StringBuffer();
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(((bx) it.next()).toString());
                    stringBuffer.append(MqttTopic.MULTI_LEVEL_WILDCARD);
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                a("gpsstatistics", stringBuffer.toString());
            }
        }
    }

    public final void b(Context context) {
        try {
            long b = de.b() - this.c;
            if (this.b != -1) {
                this.a.append(this.b, Long.valueOf(((Long) this.a.get(this.b, Long.valueOf(0))).longValue() + b));
            }
            b = de.b() - this.f;
            if (this.e != -1) {
                this.a.append(this.e, Long.valueOf(((Long) this.a.get(this.e, Long.valueOf(0))).longValue() + b));
            }
            int i = 0;
            while (i < 6) {
                b = ((Long) this.a.get(i, Long.valueOf(0))).longValue();
                if (b > 0 && b > dd.b(context, "pref", this.d[i], 0)) {
                    dd.a(context, "pref", this.d[i], b);
                }
                i++;
            }
        } catch (Throwable th) {
            cw.a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    public final int c(Context context) {
        try {
            long b = dd.b(context, "pref", this.d[2], 0);
            long b2 = dd.b(context, "pref", this.d[0], 0);
            long b3 = dd.b(context, "pref", this.d[1], 0);
            if (b == 0 && b2 == 0 && b3 == 0) {
                return -1;
            }
            b2 -= b;
            b3 -= b;
            return b > b2 ? b > b3 ? 2 : 1 : b2 > b3 ? 0 : 1;
        } catch (Throwable th) {
            return -1;
        }
    }

    public final int d(Context context) {
        try {
            long b = dd.b(context, "pref", this.d[3], 0);
            long b2 = dd.b(context, "pref", this.d[4], 0);
            long b3 = dd.b(context, "pref", this.d[5], 0);
            return (b == 0 && b2 == 0 && b3 == 0) ? -1 : b > b2 ? b > b3 ? 3 : 5 : b2 > b3 ? 4 : 5;
        } catch (Throwable th) {
            return -1;
        }
    }

    public final void e(Context context) {
        int i = 0;
        while (i < this.d.length) {
            try {
                dd.a(context, "pref", this.d[i], 0);
                i++;
            } catch (Throwable th) {
                return;
            }
        }
    }
}
