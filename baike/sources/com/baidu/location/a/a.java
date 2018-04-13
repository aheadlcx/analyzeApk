package com.baidu.location.a;

import android.location.Location;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.b.d;
import com.baidu.location.d.b;
import com.baidu.location.d.j;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    private static a c = null;
    public boolean a;
    boolean b;
    private ArrayList<a> d;
    private boolean e;
    private BDLocation f;
    private BDLocation g;

    private class a {
        public String a = null;
        public Messenger b = null;
        public LocationClientOption c = new LocationClientOption();
        public int d = 0;
        final /* synthetic */ a e;

        public a(a aVar, Message message) {
            boolean z = true;
            this.e = aVar;
            this.b = message.replyTo;
            this.a = message.getData().getString("packName");
            this.c.prodName = message.getData().getString("prodName");
            b.a().a(this.c.prodName, this.a);
            this.c.coorType = message.getData().getString("coorType");
            this.c.addrType = message.getData().getString("addrType");
            this.c.enableSimulateGps = message.getData().getBoolean("enableSimulateGps", false);
            boolean z2 = j.k || this.c.enableSimulateGps;
            j.k = z2;
            if (!j.f.equals("all")) {
                j.f = this.c.addrType;
            }
            this.c.openGps = message.getData().getBoolean("openGPS");
            this.c.scanSpan = message.getData().getInt("scanSpan");
            this.c.timeOut = message.getData().getInt("timeOut");
            this.c.priority = message.getData().getInt("priority");
            this.c.location_change_notify = message.getData().getBoolean("location_change_notify");
            this.c.mIsNeedDeviceDirect = message.getData().getBoolean("needDirect", false);
            this.c.isNeedAltitude = message.getData().getBoolean("isneedaltitude", false);
            z2 = j.g || message.getData().getBoolean("isneedaptag", false);
            j.g = z2;
            if (!(j.h || message.getData().getBoolean("isneedaptagd", false))) {
                z = false;
            }
            j.h = z;
            j.O = message.getData().getFloat("autoNotifyLocSensitivity", 0.5f);
            int i = message.getData().getInt("autoNotifyMaxInterval", 0);
            if (i >= j.T) {
                j.T = i;
            }
            i = message.getData().getInt("autoNotifyMinDistance", 0);
            if (i >= j.V) {
                j.V = i;
            }
            i = message.getData().getInt("autoNotifyMinTimeInterval", 0);
            if (i >= j.U) {
                j.U = i;
            }
            if (this.c.scanSpan >= 1000) {
            }
            if (this.c.mIsNeedDeviceDirect || this.c.isNeedAltitude) {
                j.a().a(this.c.mIsNeedDeviceDirect);
                j.a().b(this.c.isNeedAltitude);
                j.a().b();
            }
            aVar.b |= this.c.isNeedAltitude;
        }

        private void a(int i) {
            Message obtain = Message.obtain(null, i);
            try {
                if (this.b != null) {
                    this.b.send(obtain);
                }
                this.d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.d++;
                }
            }
        }

        private void a(int i, String str, BDLocation bDLocation) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            bundle.setClassLoader(BDLocation.class.getClassLoader());
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.b != null) {
                    this.b.send(obtain);
                }
                this.d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.d++;
                }
            }
        }

        public void a() {
            if (!this.c.location_change_notify) {
                return;
            }
            if (j.b) {
                a(54);
            } else {
                a(55);
            }
        }

        public void a(BDLocation bDLocation) {
            a(bDLocation, 21);
        }

        public void a(BDLocation bDLocation, int i) {
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (j.a().g() && (bDLocation2.getLocType() == 161 || bDLocation2.getLocType() == 66)) {
                bDLocation2.setAltitude(j.a().i());
            }
            if (i == 21) {
                a(27, "locStr", bDLocation2);
            }
            if (!(this.c.coorType == null || this.c.coorType.equals("gcj02"))) {
                double longitude = bDLocation2.getLongitude();
                double latitude = bDLocation2.getLatitude();
                if (!(longitude == Double.MIN_VALUE || latitude == Double.MIN_VALUE)) {
                    double[] coorEncrypt;
                    if ((bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals("gcj02")) || bDLocation2.getCoorType() == null) {
                        coorEncrypt = Jni.coorEncrypt(longitude, latitude, this.c.coorType);
                        bDLocation2.setLongitude(coorEncrypt[0]);
                        bDLocation2.setLatitude(coorEncrypt[1]);
                        bDLocation2.setCoorType(this.c.coorType);
                    } else if (!(bDLocation2.getCoorType() == null || !bDLocation2.getCoorType().equals("wgs84") || this.c.coorType.equals(BDLocation.BDLOCATION_GCJ02_TO_BD09LL))) {
                        coorEncrypt = Jni.coorEncrypt(longitude, latitude, "wgs842mc");
                        bDLocation2.setLongitude(coorEncrypt[0]);
                        bDLocation2.setLatitude(coorEncrypt[1]);
                        bDLocation2.setCoorType("wgs84mc");
                    }
                }
            }
            a(i, "locStr", bDLocation2);
        }
    }

    private a() {
        this.d = null;
        this.e = false;
        this.a = false;
        this.b = false;
        this.f = null;
        this.g = null;
        this.d = new ArrayList();
    }

    private a a(Messenger messenger) {
        if (this.d == null) {
            return null;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.b.equals(messenger)) {
                return aVar;
            }
        }
        return null;
    }

    public static a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private void a(a aVar) {
        if (aVar != null) {
            if (a(aVar.b) != null) {
                aVar.a(14);
                return;
            }
            this.d.add(aVar);
            aVar.a(13);
        }
    }

    private void e() {
        f();
        d();
    }

    private void f() {
        Iterator it = this.d.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.c.openGps) {
                z2 = true;
            }
            z = aVar.c.location_change_notify ? true : z;
        }
        j.a = z;
        if (this.e != z2) {
            this.e = z2;
            d.a().a(this.e);
        }
    }

    public void a(Message message) {
        if (message != null && message.replyTo != null) {
            this.a = true;
            a(new a(this, message));
            e();
        }
    }

    public void a(BDLocation bDLocation) {
        boolean z = i.h;
        if (z) {
            i.h = false;
        }
        if (j.T >= 10000 && (bDLocation.getLocType() == 61 || bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66)) {
            if (this.f != null) {
                float[] fArr = new float[1];
                Location.distanceBetween(this.f.getLatitude(), this.f.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] > ((float) j.V) || z) {
                    this.f = null;
                    this.f = new BDLocation(bDLocation);
                } else {
                    return;
                }
            }
            this.f = new BDLocation(bDLocation);
        }
        Iterator it;
        a aVar;
        if (h.a().b()) {
            it = this.d.iterator();
            while (it.hasNext()) {
                try {
                    aVar = (a) it.next();
                    aVar.a(bDLocation);
                    if (aVar.d > 4) {
                        it.remove();
                    }
                } catch (Exception e) {
                    return;
                }
            }
            return;
        }
        if (this.g == null) {
            this.g = new BDLocation();
            this.g.setLocType(505);
        }
        it = this.d.iterator();
        while (it.hasNext()) {
            try {
                aVar = (a) it.next();
                aVar.a(this.g);
                if (aVar.d > 4) {
                    it.remove();
                }
            } catch (Exception e2) {
                return;
            }
        }
    }

    public void a(String str) {
        BDLocation bDLocation = new BDLocation(str);
        Address a = i.c().a(bDLocation);
        String f = i.c().f();
        List g = i.c().g();
        if (a != null) {
            bDLocation.setAddr(a);
        }
        if (f != null) {
            bDLocation.setLocationDescribe(f);
        }
        if (g != null) {
            bDLocation.setPoiList(g);
        }
        i.c().b(bDLocation);
        a(bDLocation);
    }

    public void b() {
        this.d.clear();
        this.f = null;
        e();
    }

    public void b(Message message) {
        a a = a(message.replyTo);
        if (a != null) {
            this.d.remove(a);
        }
        j.a().c();
        e();
    }

    public String c() {
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.d.isEmpty()) {
            return "&prod=" + b.e + Config.TRACE_TODAY_VISIT_SPLIT + b.d;
        }
        a aVar = (a) this.d.get(0);
        if (aVar.c.prodName != null) {
            stringBuffer.append(aVar.c.prodName);
        }
        if (aVar.a != null) {
            stringBuffer.append(Config.TRACE_TODAY_VISIT_SPLIT);
            stringBuffer.append(aVar.a);
            stringBuffer.append("|");
        }
        String stringBuffer2 = stringBuffer.toString();
        return (stringBuffer2 == null || stringBuffer2.equals("")) ? null : "&prod=" + stringBuffer2;
    }

    public boolean c(Message message) {
        boolean z = true;
        a a = a(message.replyTo);
        if (a == null) {
            return false;
        }
        int i = a.c.scanSpan;
        a.c.scanSpan = message.getData().getInt("scanSpan", a.c.scanSpan);
        if (a.c.scanSpan < 1000) {
            j.a().c();
            this.a = false;
        } else {
            this.a = true;
        }
        if (a.c.scanSpan <= 999 || i >= 1000) {
            z = false;
        } else {
            if (a.c.mIsNeedDeviceDirect || a.c.isNeedAltitude) {
                j.a().a(a.c.mIsNeedDeviceDirect);
                j.a().b(a.c.isNeedAltitude);
                j.a().b();
            }
            this.b |= a.c.isNeedAltitude;
        }
        a.c.openGps = message.getData().getBoolean("openGPS", a.c.openGps);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = a.c;
        if (string == null || string.equals("")) {
            string = a.c.coorType;
        }
        locationClientOption.coorType = string;
        string = message.getData().getString("addrType");
        locationClientOption = a.c;
        if (string == null || string.equals("")) {
            string = a.c.addrType;
        }
        locationClientOption.addrType = string;
        if (!j.f.equals(a.c.addrType)) {
            i.c().i();
        }
        a.c.timeOut = message.getData().getInt("timeOut", a.c.timeOut);
        a.c.location_change_notify = message.getData().getBoolean("location_change_notify", a.c.location_change_notify);
        a.c.priority = message.getData().getInt("priority", a.c.priority);
        e();
        return z;
    }

    public int d(Message message) {
        if (message == null || message.replyTo == null) {
            return 1;
        }
        a a = a(message.replyTo);
        return (a == null || a.c == null) ? 1 : a.c.priority;
    }

    public void d() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
    }

    public int e(Message message) {
        if (message == null || message.replyTo == null) {
            return 1000;
        }
        a a = a(message.replyTo);
        return (a == null || a.c == null) ? 1000 : a.c.scanSpan;
    }
}
