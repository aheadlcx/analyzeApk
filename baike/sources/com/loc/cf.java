package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

@SuppressLint({"NewApi"})
public final class cf {
    static ArrayList<ce> b = new ArrayList();
    static long d = 0;
    static CellLocation e;
    private static ArrayList<ce> k = new ArrayList();
    int a = 0;
    TelephonyManager c = null;
    String f = null;
    boolean g = false;
    StringBuilder h = null;
    private Context i;
    private String j = null;
    private int l = -113;
    private cd m = null;
    private Object n;
    private int o = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cf(android.content.Context r5) {
        /*
        r4 = this;
        r3 = 0;
        r2 = 0;
        r4.<init>();
        r4.a = r3;
        r4.j = r2;
        r0 = -113; // 0xffffffffffffff8f float:NaN double:NaN;
        r4.l = r0;
        r4.c = r2;
        r4.m = r2;
        r4.o = r3;
        r4.f = r2;
        r4.g = r3;
        r4.h = r2;
        r4.i = r5;
        r0 = r4.c;
        if (r0 != 0) goto L_0x002b;
    L_0x001f:
        r0 = r4.i;
        r1 = "phone";
        r0 = com.loc.de.a(r0, r1);
        r0 = (android.telephony.TelephonyManager) r0;
        r4.c = r0;
    L_0x002b:
        r0 = r4.c;
        if (r0 == 0) goto L_0x0052;
    L_0x002f:
        r0 = r4.c;	 Catch:{ SecurityException -> 0x005a, Throwable -> 0x0062 }
        r0 = r0.getCellLocation();	 Catch:{ SecurityException -> 0x005a, Throwable -> 0x0062 }
        r1 = r4.i;	 Catch:{ SecurityException -> 0x005a, Throwable -> 0x0062 }
        r0 = r4.b(r0);	 Catch:{ SecurityException -> 0x005a, Throwable -> 0x0062 }
        r4.a = r0;	 Catch:{ SecurityException -> 0x005a, Throwable -> 0x0062 }
    L_0x003d:
        r0 = r4.p();	 Catch:{ Throwable -> 0x007a }
        r4.o = r0;	 Catch:{ Throwable -> 0x007a }
        r0 = r4.o;	 Catch:{ Throwable -> 0x007a }
        switch(r0) {
            case 1: goto L_0x006f;
            case 2: goto L_0x007c;
            default: goto L_0x0048;
        };	 Catch:{ Throwable -> 0x007a }
    L_0x0048:
        r0 = r4.i;	 Catch:{ Throwable -> 0x007a }
        r1 = "phone2";
        r0 = com.loc.de.a(r0, r1);	 Catch:{ Throwable -> 0x007a }
        r4.n = r0;	 Catch:{ Throwable -> 0x007a }
    L_0x0052:
        r0 = new com.loc.cd;
        r0.<init>();
        r4.m = r0;
        return;
    L_0x005a:
        r0 = move-exception;
        r0 = r0.getMessage();
        r4.f = r0;
        goto L_0x003d;
    L_0x0062:
        r0 = move-exception;
        r4.f = r2;
        r1 = "CgiManager";
        r2 = "CgiManager";
        com.loc.cw.a(r0, r1, r2);
        r4.a = r3;
        goto L_0x003d;
    L_0x006f:
        r0 = r4.i;	 Catch:{ Throwable -> 0x007a }
        r1 = "phone_msim";
        r0 = com.loc.de.a(r0, r1);	 Catch:{ Throwable -> 0x007a }
        r4.n = r0;	 Catch:{ Throwable -> 0x007a }
        goto L_0x0052;
    L_0x007a:
        r0 = move-exception;
        goto L_0x0052;
    L_0x007c:
        r0 = r4.i;	 Catch:{ Throwable -> 0x007a }
        r1 = "phone2";
        r0 = com.loc.de.a(r0, r1);	 Catch:{ Throwable -> 0x007a }
        r4.n = r0;	 Catch:{ Throwable -> 0x007a }
        goto L_0x0052;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cf.<init>(android.content.Context):void");
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object a = cz.a(obj, str, objArr);
            CellLocation cellLocation = a != null ? (CellLocation) a : null;
            if (a(cellLocation)) {
                return cellLocation;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private CellLocation a(List<CellInfo> list) {
        ce a;
        if (list == null || list.isEmpty()) {
            return null;
        }
        CellLocation cellLocation;
        CellLocation cellLocation2;
        ArrayList arrayList = k;
        cd cdVar = this.m;
        ce ceVar = null;
        int size = list.size();
        if (size != 0) {
            if (arrayList != null) {
                arrayList.clear();
            }
            for (int i = 0; i < size; i++) {
                CellInfo cellInfo = (CellInfo) list.get(i);
                if (cellInfo != null) {
                    try {
                        boolean isRegistered = cellInfo.isRegistered();
                        if (cellInfo instanceof CellInfoCdma) {
                            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                            CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                            Object obj = (cellIdentity == null || cellIdentity.getSystemId() <= 0 || cellIdentity.getNetworkId() < 0 || cellIdentity.getBasestationId() < 0) ? null : 1;
                            if (obj != null) {
                                ceVar = a(cellInfoCdma, isRegistered);
                                ceVar.l = (short) ((int) Math.min(65535, cdVar.a(ceVar)));
                                arrayList.add(ceVar);
                            }
                        } else if (cellInfo instanceof CellInfoGsm) {
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                            CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                            r2 = (cellIdentity2 != null && b(cellIdentity2.getLac()) && c(cellIdentity2.getCid())) ? 1 : null;
                            if (r2 != null) {
                                CellIdentityGsm cellIdentity3 = cellInfoGsm.getCellIdentity();
                                ceVar = a(1, isRegistered, cellIdentity3.getMcc(), cellIdentity3.getMnc(), cellIdentity3.getLac(), cellIdentity3.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
                                ceVar.l = (short) ((int) Math.min(65535, cdVar.a(ceVar)));
                                arrayList.add(ceVar);
                            }
                        } else if (cellInfo instanceof CellInfoWcdma) {
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                            r2 = (cellIdentity4 != null && b(cellIdentity4.getLac()) && c(cellIdentity4.getCid())) ? 1 : null;
                            if (r2 != null) {
                                CellIdentityWcdma cellIdentity5 = cellInfoWcdma.getCellIdentity();
                                ceVar = a(4, isRegistered, cellIdentity5.getMcc(), cellIdentity5.getMnc(), cellIdentity5.getLac(), cellIdentity5.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
                                ceVar.l = (short) ((int) Math.min(65535, cdVar.a(ceVar)));
                                arrayList.add(ceVar);
                            }
                        } else {
                            if (cellInfo instanceof CellInfoLte) {
                                CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                                CellIdentityLte cellIdentity6 = cellInfoLte.getCellIdentity();
                                r2 = (cellIdentity6 != null && b(cellIdentity6.getTac()) && c(cellIdentity6.getCi())) ? 1 : null;
                                if (r2 != null) {
                                    CellIdentityLte cellIdentity7 = cellInfoLte.getCellIdentity();
                                    a = a(3, isRegistered, cellIdentity7.getMcc(), cellIdentity7.getMnc(), cellIdentity7.getTac(), cellIdentity7.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
                                    try {
                                        a.l = (short) ((int) Math.min(65535, cdVar.a(a)));
                                        arrayList.add(a);
                                    } catch (Throwable th) {
                                        ceVar = a;
                                    }
                                }
                            } else {
                                a = ceVar;
                            }
                            ceVar = a;
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            cellLocation = null;
            cellLocation2 = null;
        } else {
            this.a |= 4;
            cdVar.a(arrayList);
            ce ceVar2 = (ce) arrayList.get(arrayList.size() - 1);
            if (ceVar2 == null || ceVar2.k != 2) {
                cellLocation2 = new GsmCellLocation();
                cellLocation2.setLacAndCid(ceVar.c, ceVar.d);
                cellLocation = null;
            } else {
                cellLocation2 = new CdmaCellLocation();
                cellLocation2.setCellLocationData(ceVar2.i, ceVar2.e, ceVar2.f, ceVar2.g, ceVar2.h);
                cellLocation = cellLocation2;
                cellLocation2 = null;
            }
        }
        return cellLocation != null ? cellLocation : cellLocation2;
    }

    private static ce a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        ce ceVar = new ce(i, z);
        ceVar.a = i2;
        ceVar.b = i3;
        ceVar.c = i4;
        ceVar.d = i5;
        ceVar.j = i6;
        return ceVar;
    }

    @SuppressLint({"NewApi"})
    private ce a(CellInfoCdma cellInfoCdma, boolean z) {
        int parseInt;
        int parseInt2;
        ce a;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] a2 = de.a(this.c);
        try {
            parseInt = Integer.parseInt(a2[0]);
            try {
                parseInt2 = Integer.parseInt(a2[1]);
            } catch (Throwable th) {
                parseInt2 = 0;
                a = a(2, z, parseInt, parseInt2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                a.g = cellIdentity.getSystemId();
                a.h = cellIdentity.getNetworkId();
                a.i = cellIdentity.getBasestationId();
                a.e = cellIdentity.getLatitude();
                a.f = cellIdentity.getLongitude();
                return a;
            }
        } catch (Throwable th2) {
            parseInt = 0;
            parseInt2 = 0;
            a = a(2, z, parseInt, parseInt2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
            a.g = cellIdentity.getSystemId();
            a.h = cellIdentity.getNetworkId();
            a.i = cellIdentity.getBasestationId();
            a.e = cellIdentity.getLatitude();
            a.f = cellIdentity.getLongitude();
            return a;
        }
        a = a(2, z, parseInt, parseInt2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        a.g = cellIdentity.getSystemId();
        a.h = cellIdentity.getNetworkId();
        a.i = cellIdentity.getBasestationId();
        a.e = cellIdentity.getLatitude();
        a.f = cellIdentity.getLongitude();
        return a;
    }

    private static ce a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            ce ceVar = new ce(1, false);
            ceVar.a = Integer.parseInt(strArr[0]);
            ceVar.b = Integer.parseInt(strArr[1]);
            ceVar.c = cz.b(neighboringCellInfo, "getLac", new Object[0]);
            ceVar.d = neighboringCellInfo.getCid();
            ceVar.j = de.a(neighboringCellInfo.getRssi());
            return ceVar;
        } catch (Throwable th) {
            cw.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    public static ArrayList<ce> a() {
        return b;
    }

    private void a(CellLocation cellLocation, String[] strArr, boolean z) {
        if (cellLocation != null && this.c != null) {
            b.clear();
            if (a(cellLocation)) {
                this.a = 1;
                ArrayList arrayList = b;
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                ce ceVar = new ce(1, true);
                ceVar.a = Integer.parseInt(strArr[0]);
                ceVar.b = Integer.parseInt(strArr[1]);
                ceVar.c = gsmCellLocation.getLac();
                ceVar.d = gsmCellLocation.getCid();
                ceVar.j = this.l;
                arrayList.add(ceVar);
                if (!z) {
                    List<NeighboringCellInfo> neighboringCellInfo = this.c.getNeighboringCellInfo();
                    if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                            if (neighboringCellInfo2 != null && a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid())) {
                                ce a = a(neighboringCellInfo2, strArr);
                                if (!(a == null || b.contains(a))) {
                                    b.add(a);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean a(int i) {
        return i > 0 && i <= 15;
    }

    private static boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > SupportMenu.USER_MASK || i2 == -1 || i2 == 0 || i2 == SupportMenu.USER_MASK || i2 >= 268435455) ? false : true;
    }

    private boolean a(CellLocation cellLocation) {
        if (cellLocation == null) {
            return false;
        }
        boolean z = true;
        Context context = this.i;
        switch (b(cellLocation)) {
            case 1:
                try {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    z = a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
                    break;
                } catch (Throwable th) {
                    cw.a(th, "CgiManager", "cgiUseful Cgi.iGsmT");
                    break;
                }
            case 2:
                try {
                    if (cz.b(cellLocation, "getSystemId", new Object[0]) <= 0 || cz.b(cellLocation, "getNetworkId", new Object[0]) < 0 || cz.b(cellLocation, "getBaseStationId", new Object[0]) < 0) {
                        z = false;
                        break;
                    }
                } catch (Throwable th2) {
                    cw.a(th2, "CgiManager", "cgiUseful Cgi.iCdmaT");
                    break;
                }
        }
        if (!z) {
            this.a = 0;
        }
        return z;
    }

    private int b(CellLocation cellLocation) {
        if (this.g || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            cw.a(th, "Utils", "getCellLocT");
            return 0;
        }
    }

    public static ArrayList<ce> b() {
        return k;
    }

    private static boolean b(int i) {
        return (i == -1 || i == 0 || i > SupportMenu.USER_MASK) ? false : true;
    }

    private static boolean c(int i) {
        return (i == -1 || i == 0 || i == SupportMenu.USER_MASK || i >= 268435455) ? false : true;
    }

    private CellLocation k() {
        if (this.c != null) {
            try {
                CellLocation cellLocation = this.c.getCellLocation();
                this.f = null;
                if (a(cellLocation)) {
                    e = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e) {
                this.f = e.getMessage();
            } catch (Throwable th) {
                this.f = null;
                cw.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    private void l() {
        this.f = null;
        e = null;
        this.a = 0;
        b.clear();
    }

    @SuppressLint({"NewApi"})
    private CellLocation m() {
        CellLocation cellLocation = null;
        Object obj = this.c;
        if (obj == null) {
            return cellLocation;
        }
        CellLocation k = k();
        if (a(k)) {
            return k;
        }
        if (de.c() >= 18) {
            try {
                cellLocation = a(obj.getAllCellInfo());
            } catch (SecurityException e) {
                this.f = e.getMessage();
            }
        }
        if (cellLocation != null) {
            return cellLocation;
        }
        cellLocation = a(obj, "getCellLocationExt", Integer.valueOf(1));
        if (cellLocation != null) {
            return cellLocation;
        }
        cellLocation = a(obj, "getCellLocationGemini", Integer.valueOf(1));
        return cellLocation != null ? cellLocation : cellLocation;
    }

    private CellLocation n() {
        CellLocation cellLocation = null;
        Object obj = this.n;
        if (obj != null) {
            try {
                Class o = o();
                if (o.isInstance(obj)) {
                    obj = o.cast(obj);
                    String str = "getCellLocation";
                    cellLocation = a(obj, str, new Object[0]);
                    if (cellLocation == null) {
                        cellLocation = a(obj, str, Integer.valueOf(1));
                        if (cellLocation == null) {
                            cellLocation = a(obj, "getCellLocationGemini", Integer.valueOf(1));
                            if (cellLocation == null) {
                                cellLocation = a(obj, "getAllCellInfo", Integer.valueOf(1));
                                if (cellLocation != null) {
                                }
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "CgiManager", "getSim2Cgi");
            }
        }
        return cellLocation;
    }

    private Class<?> o() {
        String str;
        Class<?> cls = null;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (this.o) {
            case 0:
                str = "android.telephony.TelephonyManager";
                break;
            case 1:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case 2:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = cls;
                break;
        }
        try {
            cls = systemClassLoader.loadClass(str);
        } catch (Throwable th) {
            cw.a(th, "CgiManager", "getSim2TmClass");
        }
        return cls;
    }

    private int p() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.o = 1;
        } catch (Throwable th) {
        }
        if (this.o == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.o = 2;
            } catch (Throwable th2) {
            }
        }
        return this.o;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r10) {
        /*
        r9 = this;
        r8 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r1 = 1;
        r2 = 0;
        r0 = r9.i;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = com.loc.de.a(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r9.g = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r9.g;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 == 0) goto L_0x006c;
    L_0x0011:
        r0 = r2;
    L_0x0012:
        if (r0 == 0) goto L_0x0064;
    L_0x0014:
        r0 = r9.g;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 != 0) goto L_0x0032;
    L_0x0018:
        r0 = r9.c;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r0 = r9.m();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r3 = r9.a(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r3 != 0) goto L_0x002a;
    L_0x0026:
        r0 = r9.n();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x002a:
        r3 = r9.a(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r3 == 0) goto L_0x007b;
    L_0x0030:
        e = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x0032:
        r0 = e;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r9.a(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 != 0) goto L_0x0087;
    L_0x003a:
        r0 = b;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0.clear();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = k;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0.clear();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x0044:
        r0 = r9.c;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 == 0) goto L_0x005e;
    L_0x0048:
        r0 = r9.c;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r0.getNetworkOperator();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r9.j = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r9.j;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 != 0) goto L_0x005e;
    L_0x0058:
        r0 = r9.a;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r0 | 8;
        r9.a = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x005e:
        r0 = com.loc.de.b();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        d = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x0064:
        r0 = r9.g;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 == 0) goto L_0x017e;
    L_0x0068:
        r9.l();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x006b:
        return;
    L_0x006c:
        r4 = com.loc.de.b();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r6 = d;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r4 = r4 - r6;
        r6 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x019f;
    L_0x0079:
        r0 = r2;
        goto L_0x0012;
    L_0x007b:
        r0 = 0;
        e = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        goto L_0x0032;
    L_0x007f:
        r0 = move-exception;
        r0 = r0.getMessage();
        r9.f = r0;
        goto L_0x006b;
    L_0x0087:
        r0 = r9.c;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r3 = com.loc.de.a(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = e;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r4 = r9.i;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r9.b(r0);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        switch(r0) {
            case 1: goto L_0x0099;
            case 2: goto L_0x00a8;
            default: goto L_0x0098;
        };	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x0098:
        goto L_0x0044;
    L_0x0099:
        r0 = e;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r9.a(r0, r3, r10);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        goto L_0x0044;
    L_0x009f:
        r0 = move-exception;
        r1 = "CgiManager";
        r2 = "refresh";
        com.loc.cw.a(r0, r1, r2);
        goto L_0x006b;
    L_0x00a8:
        r4 = e;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r4 == 0) goto L_0x0044;
    L_0x00ac:
        r0 = b;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0.clear();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = com.loc.de.c();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r5 = 5;
        if (r0 < r5) goto L_0x0044;
    L_0x00b8:
        r0 = r9.n;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r0 == 0) goto L_0x00e4;
    L_0x00bc:
        r0 = r4.getClass();	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        r5 = "mGsmCellLoc";
        r0 = r0.getDeclaredField(r5);	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        r5 = r0.isAccessible();	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        if (r5 != 0) goto L_0x00d0;
    L_0x00cc:
        r5 = 1;
        r0.setAccessible(r5);	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
    L_0x00d0:
        r0 = r0.get(r4);	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        r0 = (android.telephony.gsm.GsmCellLocation) r0;	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        if (r0 == 0) goto L_0x017b;
    L_0x00d8:
        r5 = r9.a(r0);	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        if (r5 == 0) goto L_0x017b;
    L_0x00de:
        r9.a(r0, r3, r10);	 Catch:{ Throwable -> 0x017a, SecurityException -> 0x007f }
        r0 = r1;
    L_0x00e2:
        if (r0 != 0) goto L_0x0044;
    L_0x00e4:
        r0 = r9.a(r4);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r0 == 0) goto L_0x0044;
    L_0x00ea:
        r0 = 2;
        r9.a = r0;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0 = new com.loc.ce;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = 2;
        r2 = 1;
        r0.<init>(r1, r2);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = 0;
        r1 = r3[r1];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.a = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = 1;
        r1 = r3[r1];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.b = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = "getSystemId";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = com.loc.cz.b(r4, r1, r2);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.g = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = "getNetworkId";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = com.loc.cz.b(r4, r1, r2);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.h = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = "getBaseStationId";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = com.loc.cz.b(r4, r1, r2);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.i = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = r9.l;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.j = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = "getBaseStationLatitude";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = com.loc.cz.b(r4, r1, r2);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.e = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = "getBaseStationLongitude";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = com.loc.cz.b(r4, r1, r2);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r0.f = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = r0.e;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 < 0) goto L_0x015b;
    L_0x0145:
        r1 = r0.f;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 < 0) goto L_0x015b;
    L_0x0149:
        r1 = r0.e;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 == r8) goto L_0x015b;
    L_0x014d:
        r1 = r0.f;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 == r8) goto L_0x015b;
    L_0x0151:
        r1 = r0.e;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r2 = r0.f;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 != r2) goto L_0x0161;
    L_0x0157:
        r1 = r0.e;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 <= 0) goto L_0x0161;
    L_0x015b:
        r1 = 0;
        r0.e = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = 0;
        r0.f = r1;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
    L_0x0161:
        r1 = b;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1 = r1.contains(r0);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        if (r1 != 0) goto L_0x0044;
    L_0x0169:
        r1 = b;	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        r1.add(r0);	 Catch:{ Throwable -> 0x0170, SecurityException -> 0x007f }
        goto L_0x0044;
    L_0x0170:
        r0 = move-exception;
        r1 = "CgiManager";
        r2 = "hdlCdmaLocChange";
        com.loc.cw.a(r0, r1, r2);	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        goto L_0x0044;
    L_0x017a:
        r0 = move-exception;
    L_0x017b:
        r0 = r2;
        goto L_0x00e2;
    L_0x017e:
        r0 = r9.a;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        switch(r0) {
            case 1: goto L_0x0185;
            case 2: goto L_0x0192;
            default: goto L_0x0183;
        };	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
    L_0x0183:
        goto L_0x006b;
    L_0x0185:
        r0 = b;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r0.isEmpty();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 == 0) goto L_0x006b;
    L_0x018d:
        r0 = 0;
        r9.a = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        goto L_0x006b;
    L_0x0192:
        r0 = b;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        r0 = r0.isEmpty();	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        if (r0 == 0) goto L_0x006b;
    L_0x019a:
        r0 = 0;
        r9.a = r0;	 Catch:{ SecurityException -> 0x007f, Throwable -> 0x009f }
        goto L_0x006b;
    L_0x019f:
        r0 = r1;
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cf.a(boolean):void");
    }

    public final ce c() {
        if (this.g) {
            return null;
        }
        ArrayList arrayList = b;
        return arrayList.size() > 0 ? (ce) arrayList.get(0) : null;
    }

    public final int d() {
        return this.a;
    }

    public final int e() {
        return this.a & 3;
    }

    public final TelephonyManager f() {
        return this.c;
    }

    public final void g() {
        this.m.a();
        this.l = -113;
        this.c = null;
        this.n = null;
    }

    public final String h() {
        return this.f;
    }

    public final String i() {
        return this.j;
    }

    public final String j() {
        if (this.g) {
            l();
        }
        if (this.h == null) {
            this.h = new StringBuilder();
        } else {
            this.h.delete(0, this.h.length());
        }
        switch (this.a & 3) {
            case 1:
                for (int i = 1; i < b.size(); i++) {
                    this.h.append(MqttTopic.MULTI_LEVEL_WILDCARD).append(((ce) b.get(i)).b);
                    this.h.append("|").append(((ce) b.get(i)).c);
                    this.h.append("|").append(((ce) b.get(i)).d);
                }
                break;
        }
        if (this.h.length() > 0) {
            this.h.deleteCharAt(0);
        }
        return this.h.toString();
    }
}
