package com.baidu.location.a;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.d.e;
import com.baidu.location.d.j;
import com.baidu.mobstat.Config;
import com.tencent.connect.common.Constants;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class b {
    private static Method e = null;
    private static Method f = null;
    private static Method g = null;
    private static Class<?> h = null;
    c a = new c(this);
    private Context b = null;
    private TelephonyManager c = null;
    private a d = new a();
    private WifiManager i = null;
    private d j = null;
    private String k = null;
    private LocationClientOption l;
    private b m;
    private String n = null;
    private String o = null;

    public interface b {
        void onReceiveLocation(BDLocation bDLocation);
    }

    private class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public char e;
        final /* synthetic */ b f;

        private a(b bVar) {
            this.f = bVar;
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = '\u0000';
        }

        private boolean c() {
            return this.a > -1 && this.b > 0;
        }

        public int a() {
            return (this.c <= 0 || !c()) ? 2 : (this.c == 460 || this.c == 454 || this.c == 455 || this.c == 466) ? 1 : 0;
        }

        public String b() {
            if (!c()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append("&nw=");
            stringBuffer.append(this.e);
            stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.a), Integer.valueOf(this.b)}));
            return stringBuffer.toString();
        }
    }

    class c extends e {
        String a;
        final /* synthetic */ b b;

        c(b bVar) {
            this.b = bVar;
            this.a = null;
            this.k = new HashMap();
        }

        public void a() {
            this.h = j.c();
            String encodeTp4 = Jni.encodeTp4(this.a);
            this.a = null;
            this.k.put("bloc", encodeTp4);
            StringBuffer stringBuffer = new StringBuffer(512);
            if (this.b.o != null) {
                stringBuffer.append(String.format(Locale.CHINA, "&ki=%s", new Object[]{this.b.o}));
            }
            if (stringBuffer.length() > 0) {
                this.k.put("ext", Jni.encode(stringBuffer.toString()));
            }
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        public void a(String str) {
            this.a = str;
            e();
        }

        public void a(boolean z) {
            if (z && this.j != null) {
                try {
                    BDLocation bDLocation;
                    try {
                        bDLocation = new BDLocation(this.j);
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(63);
                    }
                    if (bDLocation != null) {
                        if (bDLocation.getLocType() == 161) {
                            bDLocation.setCoorType(this.b.l.coorType);
                            this.b.m.onReceiveLocation(bDLocation);
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    protected class d {
        public List<ScanResult> a = null;
        final /* synthetic */ b b;
        private long c = 0;

        public d(b bVar, List<ScanResult> list) {
            this.b = bVar;
            this.a = list;
            this.c = System.currentTimeMillis();
            b();
        }

        private void b() {
            if (a() >= 1) {
                Object obj = 1;
                for (int size = this.a.size() - 1; size >= 1 && r2 != null; size--) {
                    int i = 0;
                    obj = null;
                    while (i < size) {
                        Object obj2;
                        if (((ScanResult) this.a.get(i)).level < ((ScanResult) this.a.get(i + 1)).level) {
                            ScanResult scanResult = (ScanResult) this.a.get(i + 1);
                            this.a.set(i + 1, this.a.get(i));
                            this.a.set(i, scanResult);
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i++;
                        obj = obj2;
                    }
                }
            }
        }

        public int a() {
            return this.a == null ? 0 : this.a.size();
        }

        public String a(int i) {
            if (a() < 2) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this.a.size();
            int i2 = 0;
            int i3 = 0;
            int i4 = 1;
            while (i2 < size) {
                int i5;
                if (((ScanResult) this.a.get(i2)).level == 0) {
                    i5 = i3;
                } else {
                    if (i4 != 0) {
                        stringBuffer.append("&wf=");
                        i4 = 0;
                    } else {
                        stringBuffer.append("|");
                    }
                    stringBuffer.append(((ScanResult) this.a.get(i2)).BSSID.replace(Config.TRACE_TODAY_VISIT_SPLIT, ""));
                    i5 = ((ScanResult) this.a.get(i2)).level;
                    if (i5 < 0) {
                        i5 = -i5;
                    }
                    stringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[]{Integer.valueOf(i5)}));
                    i5 = i3 + 1;
                    if (i5 > i) {
                        break;
                    }
                }
                i2++;
                i3 = i5;
            }
            return i4 != 0 ? null : stringBuffer.toString();
        }
    }

    public b(Context context, LocationClientOption locationClientOption, b bVar) {
        String deviceId;
        String a;
        this.b = context.getApplicationContext();
        this.l = locationClientOption;
        this.m = bVar;
        String packageName = this.b.getPackageName();
        try {
            this.c = (TelephonyManager) this.b.getSystemService("phone");
            deviceId = this.c.getDeviceId();
        } catch (Exception e) {
            deviceId = null;
        }
        try {
            a = com.baidu.a.a.a.b.a.a(this.b);
        } catch (Exception e2) {
            a = null;
        }
        if (a != null) {
            this.k = "&prod=" + this.l.prodName + Config.TRACE_TODAY_VISIT_SPLIT + packageName + "|&cu=" + a + "&coor=" + locationClientOption.getCoorType();
        } else {
            this.k = "&prod=" + this.l.prodName + Config.TRACE_TODAY_VISIT_SPLIT + packageName + "|&im=" + deviceId + "&coor=" + locationClientOption.getCoorType();
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&fw=");
        stringBuffer.append("7.02");
        stringBuffer.append("&lt=1");
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&resid=");
        stringBuffer.append(Constants.VIA_REPORT_TYPE_SET_AVATAR);
        if (locationClientOption.getAddrType() != null) {
        }
        if (locationClientOption.getAddrType() != null && locationClientOption.getAddrType().equals("all")) {
            this.k += "&addr=all";
        }
        if (locationClientOption.isNeedAptag || locationClientOption.isNeedAptagd) {
            this.k += "&sema=";
            if (locationClientOption.isNeedAptag) {
                this.k += "aptag|";
            }
            if (locationClientOption.isNeedAptagd) {
                this.k += "aptagd|";
            }
            this.o = h.b(this.b);
        }
        stringBuffer.append("&first=1");
        stringBuffer.append(VERSION.SDK);
        this.k += stringBuffer.toString();
        this.i = (WifiManager) this.b.getSystemService("wifi");
        a = a();
        if (!TextUtils.isEmpty(a)) {
            a = a.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        if (!(TextUtils.isEmpty(a) || a.equals(Config.DEF_MAC_ID))) {
            this.k += "&mac=" + a;
        }
        b();
    }

    private String a(int i) {
        String b;
        String a;
        if (i < 3) {
            i = 3;
        }
        try {
            a(this.c.getCellLocation());
            b = this.d.b();
        } catch (Exception e) {
            b = null;
        }
        try {
            this.j = null;
            this.j = new d(this, this.i.getScanResults());
            a = this.j.a(i);
        } catch (Exception e2) {
            a = null;
        }
        if (b == null && a == null) {
            this.n = null;
            return null;
        }
        if (a != null) {
            b = b + a;
        }
        if (b == null) {
            return null;
        }
        this.n = b + this.k;
        return b + this.k;
    }

    private void a(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation != null && this.c != null) {
            a aVar = new a();
            String networkOperator = this.c.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.d.c;
                        }
                        aVar.c = intValue;
                    }
                    String substring = networkOperator.substring(3);
                    if (substring != null) {
                        char[] toCharArray = substring.toCharArray();
                        while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                            i++;
                        }
                    }
                    i = Integer.valueOf(substring.substring(0, i)).intValue();
                    if (i < 0) {
                        i = this.d.d;
                    }
                    aVar.d = i;
                } catch (Exception e) {
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                aVar.a = ((GsmCellLocation) cellLocation).getLac();
                aVar.b = ((GsmCellLocation) cellLocation).getCid();
                aVar.e = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                aVar.e = 'c';
                if (h == null) {
                    try {
                        h = Class.forName("android.telephony.cdma.CdmaCellLocation");
                        e = h.getMethod("getBaseStationId", new Class[0]);
                        f = h.getMethod("getNetworkId", new Class[0]);
                        g = h.getMethod("getSystemId", new Class[0]);
                    } catch (Exception e2) {
                        h = null;
                        return;
                    }
                }
                if (h != null && h.isInstance(cellLocation)) {
                    try {
                        i = ((Integer) g.invoke(cellLocation, new Object[0])).intValue();
                        if (i < 0) {
                            i = this.d.d;
                        }
                        aVar.d = i;
                        aVar.b = ((Integer) e.invoke(cellLocation, new Object[0])).intValue();
                        aVar.a = ((Integer) f.invoke(cellLocation, new Object[0])).intValue();
                    } catch (Exception e3) {
                        return;
                    }
                }
            }
            if (aVar.c()) {
                this.d = aVar;
            } else {
                this.d = null;
            }
        }
    }

    public String a() {
        String str = null;
        try {
            WifiInfo connectionInfo = this.i.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getMacAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }

    public String b() {
        try {
            return a(15);
        } catch (Exception e) {
            return null;
        }
    }

    public void c() {
        if (this.n != null && this.d != null && this.d.a() == 1 && null == null) {
            this.a.a(this.n);
        }
    }
}
