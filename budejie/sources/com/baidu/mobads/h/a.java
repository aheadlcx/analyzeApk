package com.baidu.mobads.h;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
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
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class a {
    private static Method d = null;
    private static Method e = null;
    private static Method f = null;
    private static Class<?> g = null;
    private static char[] n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.".toCharArray();
    private Context a = null;
    private TelephonyManager b = null;
    private a c = new a();
    private WifiManager h = null;
    private b i = null;
    private long j = 0;
    private String k = null;
    private int l = 0;
    private String m = null;

    private class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public char e;
        final /* synthetic */ a f;

        private a(a aVar) {
            this.f = aVar;
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = '\u0000';
        }

        private boolean b() {
            return this.a > -1 && this.b > 0;
        }

        public String a() {
            if (!b()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append(this.e);
            stringBuffer.append(IXAdRequestInfo.HEIGHT);
            if (this.c != 460) {
                stringBuffer.append(this.c);
            }
            stringBuffer.append(String.format(Locale.CHINA, "h%xh%xh%x", new Object[]{Integer.valueOf(this.d), Integer.valueOf(this.a), Integer.valueOf(this.b)}));
            return stringBuffer.toString();
        }
    }

    protected class b {
        public List<ScanResult> a = null;
        final /* synthetic */ a b;
        private long c = 0;

        public b(a aVar, List<ScanResult> list) {
            this.b = aVar;
            this.a = list;
            this.c = System.currentTimeMillis();
            b();
        }

        public int a() {
            if (this.a == null) {
                return 0;
            }
            return this.a.size();
        }

        public String a(int i) {
            if (a() < 1) {
                return null;
            }
            Object obj;
            Object obj2;
            String str;
            boolean a = this.b.c();
            if (a) {
                i--;
                obj = null;
            } else {
                int i2 = 1;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this.a.size();
            int i3 = 0;
            int i4 = 0;
            Object obj3 = 1;
            Object obj4 = obj;
            while (i3 < size) {
                if (((ScanResult) this.a.get(i3)).level == 0) {
                    i2 = i4;
                    obj2 = obj3;
                    obj3 = obj4;
                } else {
                    String str2 = ((ScanResult) this.a.get(i3)).BSSID;
                    i2 = ((ScanResult) this.a.get(i3)).level;
                    str2 = str2.replace(":", "");
                    if (this.b.k == null || !str2.equals(this.b.k)) {
                        if (i4 < i) {
                            stringBuffer.append(IXAdRequestInfo.HEIGHT);
                            stringBuffer.append(str2);
                            stringBuffer.append("m");
                            stringBuffer.append(StrictMath.abs(i2));
                            i2 = i4 + 1;
                            obj2 = null;
                        } else {
                            i2 = i4;
                            obj2 = obj3;
                        }
                        if (i2 > i && obj4 != null) {
                            break;
                        }
                        obj3 = obj4;
                    } else {
                        this.b.l = StrictMath.abs(i2);
                        i2 = i4;
                        obj2 = obj3;
                        int i5 = 1;
                    }
                }
                i3++;
                obj4 = obj3;
                obj3 = obj2;
                i4 = i2;
            }
            obj2 = obj3;
            if (a) {
                str = IXAdRequestInfo.HEIGHT + this.b.k + "km" + this.b.l;
            } else {
                str = null;
            }
            if (obj2 == null) {
                return str + stringBuffer.toString();
            }
            return str;
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

        private boolean c() {
            long currentTimeMillis = System.currentTimeMillis() - this.c;
            if (currentTimeMillis < 0 || currentTimeMillis > 500) {
                return true;
            }
            return false;
        }
    }

    public a(Context context) {
        String str;
        this.a = context.getApplicationContext();
        String packageName = this.a.getPackageName();
        try {
            this.b = (TelephonyManager) this.a.getSystemService("phone");
            str = (String) XAdSDKFoundationFacade.getInstance().getCommonUtils().a(this.b, XAdSDKFoundationFacade.getInstance().getBase64().decodeStr("uvNYwANvpyP-iyfb"), new Object[0]);
        } catch (Exception e) {
            str = null;
        }
        this.m = "&" + packageName + "&" + str;
        this.h = (WifiManager) this.a.getSystemService(IXAdSystemUtils.NT_WIFI);
    }

    public String a() {
        try {
            return a(10);
        } catch (Exception e) {
            return null;
        }
    }

    private String a(int i) {
        String a;
        String a2;
        if (i < 3) {
            i = 3;
        }
        try {
            a b = b();
            if (b == null || !b.b()) {
                a(this.b.getCellLocation());
            } else {
                this.c = b;
            }
            a = this.c.a();
        } catch (Exception e) {
            a = null;
        }
        if (a == null) {
            a = "Z";
        }
        try {
            if (this.i == null || this.i.c()) {
                this.i = new b(this, this.h.getScanResults());
            }
            a2 = this.i.a(i);
        } catch (Exception e2) {
            a2 = null;
        }
        if (a2 != null) {
            a = a + a2;
        }
        if (a.equals("Z")) {
            return null;
        }
        return a(a + "t" + System.currentTimeMillis() + this.m);
    }

    private void a(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation != null && this.b != null) {
            a aVar = new a();
            String networkOperator = this.b.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.c.c;
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
                        i = this.c.d;
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
                aVar.e = 'w';
                if (g == null) {
                    try {
                        g = Class.forName("android.telephony.cdma.CdmaCellLocation");
                        d = g.getMethod("getBaseStationId", new Class[0]);
                        e = g.getMethod("getNetworkId", new Class[0]);
                        f = g.getMethod("getSystemId", new Class[0]);
                    } catch (Exception e2) {
                        g = null;
                        return;
                    }
                }
                if (g != null && g.isInstance(cellLocation)) {
                    try {
                        i = ((Integer) f.invoke(cellLocation, new Object[0])).intValue();
                        if (i < 0) {
                            i = this.c.d;
                        }
                        aVar.d = i;
                        aVar.b = ((Integer) d.invoke(cellLocation, new Object[0])).intValue();
                        aVar.a = ((Integer) e.invoke(cellLocation, new Object[0])).intValue();
                    } catch (Exception e3) {
                        return;
                    }
                }
            }
            if (aVar.b()) {
                this.c = aVar;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.baidu.mobads.h.a.a b() {
        /*
        r5 = this;
        r1 = 0;
        r0 = android.os.Build.VERSION.SDK_INT;
        r0 = java.lang.Integer.valueOf(r0);
        r0 = r0.intValue();
        r2 = 17;
        if (r0 >= r2) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r0 = r5.b;	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        r0 = r0.getAllCellInfo();	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        if (r0 == 0) goto L_0x000f;
    L_0x0018:
        r2 = r0.size();	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        if (r2 <= 0) goto L_0x000f;
    L_0x001e:
        r3 = r0.iterator();	 Catch:{ Exception -> 0x0048, NoSuchMethodError -> 0x0046 }
        r2 = r1;
    L_0x0023:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        if (r0 == 0) goto L_0x0050;
    L_0x0029:
        r0 = r3.next();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        r0 = (android.telephony.CellInfo) r0;	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        r4 = r0.isRegistered();	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        if (r4 == 0) goto L_0x0023;
    L_0x0035:
        r0 = r5.a(r0);	 Catch:{ Exception -> 0x004a, NoSuchMethodError -> 0x0046 }
        if (r0 != 0) goto L_0x003d;
    L_0x003b:
        r2 = r0;
        goto L_0x0023;
    L_0x003d:
        r2 = r0.b();	 Catch:{ Exception -> 0x004d, NoSuchMethodError -> 0x0046 }
        if (r2 != 0) goto L_0x0044;
    L_0x0043:
        r0 = r1;
    L_0x0044:
        r1 = r0;
        goto L_0x000f;
    L_0x0046:
        r0 = move-exception;
        goto L_0x000f;
    L_0x0048:
        r0 = move-exception;
        goto L_0x000f;
    L_0x004a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x000f;
    L_0x004d:
        r1 = move-exception;
        r1 = r0;
        goto L_0x000f;
    L_0x0050:
        r1 = r2;
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.h.a.b():com.baidu.mobads.h.a$a");
    }

    private a a(CellInfo cellInfo) {
        int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
        if (intValue < 17) {
            return null;
        }
        a aVar = new a();
        Object obj = null;
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            aVar.c = b(cellIdentity.getMcc());
            aVar.d = b(cellIdentity.getMnc());
            aVar.a = b(cellIdentity.getLac());
            aVar.b = b(cellIdentity.getCid());
            aVar.e = 'g';
            obj = 1;
        } else if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            aVar.d = b(cellIdentity2.getSystemId());
            aVar.a = b(cellIdentity2.getNetworkId());
            aVar.b = b(cellIdentity2.getBasestationId());
            aVar.e = 'w';
            r0 = 1;
        } else if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            aVar.c = b(cellIdentity3.getMcc());
            aVar.d = b(cellIdentity3.getMnc());
            aVar.a = b(cellIdentity3.getTac());
            aVar.b = b(cellIdentity3.getCi());
            aVar.e = 'g';
            r0 = 1;
        }
        if (intValue >= 18 && r0 == null) {
            try {
                if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    aVar.c = b(cellIdentity4.getMcc());
                    aVar.d = b(cellIdentity4.getMnc());
                    aVar.a = b(cellIdentity4.getLac());
                    aVar.b = b(cellIdentity4.getCid());
                    aVar.e = 'g';
                }
            } catch (Exception e) {
            }
        }
        return aVar;
    }

    private int b(int i) {
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
    }

    private boolean c() {
        String str = null;
        this.k = null;
        this.l = 0;
        WifiInfo connectionInfo = this.h.getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        try {
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                str = bssid.replace(":", "");
            }
            if (str.length() != 12) {
                return false;
            }
            this.k = new String(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(255);
        byte nextInt2 = (byte) new Random().nextInt(255);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = nextInt;
        i2 = i + 1;
        bArr[i] = nextInt2;
        return a(bArr);
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            Object obj;
            Object obj2;
            int i3 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i3 |= bArr[i2 + 1] & 255;
                obj = 1;
            } else {
                obj = null;
            }
            i3 <<= 8;
            if (i2 + 2 < bArr.length) {
                i3 |= bArr[i2 + 2] & 255;
                obj2 = 1;
            } else {
                obj2 = null;
            }
            cArr[i + 3] = n[obj2 != null ? 63 - (i3 & 63) : 64];
            int i4 = i3 >> 6;
            int i5 = i + 2;
            char[] cArr2 = n;
            if (obj != null) {
                i3 = 63 - (i4 & 63);
            } else {
                i3 = 64;
            }
            cArr[i5] = cArr2[i3];
            i3 = i4 >> 6;
            cArr[i + 1] = n[63 - (i3 & 63)];
            cArr[i + 0] = n[63 - ((i3 >> 6) & 63)];
            i2 += 3;
            i += 4;
        }
        return new String(cArr);
    }
}
