package com.loc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import qsbk.app.cafe.plugin.QbSignPlugin;

@SuppressLint({"NewApi"})
public class a implements GeoFenceManagerBase {
    db a = null;
    Context b = null;
    PendingIntent c = null;
    String d = null;
    GeoFenceListener e = null;
    volatile int f = 1;
    ArrayList<GeoFence> g = new ArrayList();
    c h = null;
    Object i = new Object();
    a j = null;
    b k = null;
    volatile boolean l = false;
    volatile boolean m = false;
    b n = null;
    c o = null;
    AMapLocationClient p = null;
    volatile AMapLocation q = null;
    long r = 0;
    AMapLocationClientOption s = null;
    int t = 0;
    AMapLocationListener u = new av(this);
    Hashtable<PendingIntent, ArrayList<GeoFence>> v = new Hashtable();

    class a extends Handler {
        final /* synthetic */ a a;

        public a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        this.a.b(message.getData());
                        return;
                    case 1:
                        this.a.c(message.getData());
                        return;
                    case 2:
                        this.a.e(message.getData());
                        return;
                    case 3:
                        this.a.d(message.getData());
                        return;
                    case 4:
                        this.a.f(message.getData());
                        return;
                    case 5:
                        this.a.c();
                        return;
                    case 6:
                        this.a.a(this.a.q);
                        return;
                    case 7:
                        this.a.b();
                        return;
                    case 8:
                        this.a.h(message.getData());
                        return;
                    case 9:
                        this.a.a(message.getData());
                        return;
                    case 10:
                        this.a.a();
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
            }
        }
    }

    static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }
    }

    class c extends Handler {
        final /* synthetic */ a a;

        public c(a aVar) {
            this.a = aVar;
        }

        public c(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1000:
                    this.a.g(data);
                    return;
                case 1001:
                    try {
                        this.a.a((GeoFence) data.getParcelable("geoFence"));
                        return;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return;
                    }
                case 1002:
                    try {
                        this.a.b(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public a(Context context) {
        try {
            this.b = context.getApplicationContext();
            d();
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManger", "<init>");
        }
    }

    static float a(DPoint dPoint, List<DPoint> list) {
        if (dPoint == null || list == null || list.isEmpty()) {
            return Float.MAX_VALUE;
        }
        float f = Float.MAX_VALUE;
        for (DPoint a : list) {
            f = Math.min(f, de.a(dPoint, a));
        }
        return f;
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            for (GeoFence b : list) {
                b(b);
            }
            return 0;
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static Bundle a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        String str3 = GeoFence.BUNDLE_KEY_FENCEID;
        if (str == null) {
            str = "";
        }
        bundle.putString(str3, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    private GeoFence a(Bundle bundle, boolean z) {
        Object parcelableArrayList;
        PendingIntent pendingIntent;
        Object obj;
        long j;
        String str;
        Object obj2;
        float f = 1000.0f;
        GeoFence geoFence = new GeoFence();
        List arrayList = new ArrayList();
        DPoint dPoint = new DPoint();
        if (z) {
            geoFence.setType(1);
            parcelableArrayList = bundle.getParcelableArrayList("points");
            if (parcelableArrayList != null) {
                dPoint = b((List) parcelableArrayList);
            }
            geoFence.setMaxDis2Center(b(dPoint, (List) parcelableArrayList));
            geoFence.setMinDis2Center(a(dPoint, (List) parcelableArrayList));
        } else {
            geoFence.setType(0);
            dPoint = (DPoint) bundle.getParcelable("point");
            if (dPoint != null) {
                arrayList.add(dPoint);
            }
            float f2 = bundle.getFloat("radius", 1000.0f);
            if (f2 > 0.0f) {
                f = f2;
            }
            geoFence.setRadius(f);
            geoFence.setMinDis2Center(f);
            geoFence.setMaxDis2Center(f);
            List list = arrayList;
        }
        geoFence.setActivatesAction(this.f);
        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
        List arrayList2 = new ArrayList();
        arrayList2.add(parcelableArrayList);
        geoFence.setPointList(arrayList2);
        geoFence.setCenter(dPoint);
        try {
            String string = bundle.getString(GeoFence.BUNDLE_KEY_FENCEID);
            try {
                long j2 = bundle.getLong("expiration", -1);
                long j3;
                try {
                    j3 = j2;
                    pendingIntent = (PendingIntent) bundle.getParcelable("pIntent");
                    obj = string;
                    j = j3;
                } catch (Throwable th) {
                    j3 = j2;
                    str = string;
                    j = j3;
                    obj = obj2;
                    pendingIntent = null;
                    if (TextUtils.isEmpty(obj)) {
                        geoFence.setFenceId(c.a());
                    } else {
                        geoFence.setFenceId(obj);
                    }
                    geoFence.setPendingIntentAction(this.d);
                    geoFence.setExpiration(j);
                    if (pendingIntent == null) {
                        geoFence.setPendingIntent(this.c);
                    } else {
                        geoFence.setPendingIntent(pendingIntent);
                    }
                    if (this.a != null) {
                        this.a.a(this.b, 2);
                    }
                    return geoFence;
                }
            } catch (Throwable th2) {
                str = string;
                j = -1;
                obj = obj2;
                pendingIntent = null;
                if (TextUtils.isEmpty(obj)) {
                    geoFence.setFenceId(c.a());
                } else {
                    geoFence.setFenceId(obj);
                }
                geoFence.setPendingIntentAction(this.d);
                geoFence.setExpiration(j);
                if (pendingIntent == null) {
                    geoFence.setPendingIntent(pendingIntent);
                } else {
                    geoFence.setPendingIntent(this.c);
                }
                if (this.a != null) {
                    this.a.a(this.b, 2);
                }
                return geoFence;
            }
        } catch (Throwable th3) {
            j = -1;
            obj2 = null;
            obj = obj2;
            pendingIntent = null;
            if (TextUtils.isEmpty(obj)) {
                geoFence.setFenceId(obj);
            } else {
                geoFence.setFenceId(c.a());
            }
            geoFence.setPendingIntentAction(this.d);
            geoFence.setExpiration(j);
            if (pendingIntent == null) {
                geoFence.setPendingIntent(this.c);
            } else {
                geoFence.setPendingIntent(pendingIntent);
            }
            if (this.a != null) {
                this.a.a(this.b, 2);
            }
            return geoFence;
        }
        if (TextUtils.isEmpty(obj)) {
            geoFence.setFenceId(c.a());
        } else {
            geoFence.setFenceId(obj);
        }
        geoFence.setPendingIntentAction(this.d);
        geoFence.setExpiration(j);
        if (pendingIntent == null) {
            geoFence.setPendingIntent(pendingIntent);
        } else {
            geoFence.setPendingIntent(this.c);
        }
        if (this.a != null) {
            this.a.a(this.b, 2);
        }
        return geoFence;
    }

    static void a(String str, int i, String str2, String... strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("===========================================\n");
        stringBuffer.append("              " + str + "                ").append("\n");
        stringBuffer.append("-------------------------------------------\n");
        stringBuffer.append("errorCode:" + i).append("\n");
        stringBuffer.append("错误信息:" + str2).append("\n");
        if (strArr != null && strArr.length > 0) {
            for (String append : strArr) {
                stringBuffer.append(append).append("\n");
            }
        }
        stringBuffer.append("===========================================\n");
        Log.i("fenceErrLog", stringBuffer.toString());
    }

    private static boolean a(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.abs(((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2))) < 1.0E-9d && (d - d3) * (d - d5) <= 0.0d && (d2 - d4) * (d2 - d6) <= 0.0d;
    }

    private static boolean a(GeoFence geoFence, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z = true;
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                boolean z2 = false;
                cw.a(th2, "Utils", "remindStatus");
                return z2;
            }
        }
        if ((i & 2) == 2 && geoFence.getStatus() == 2) {
            z = true;
        }
        return ((i & 4) == 4 && geoFence.getStatus() == 3) ? true : z;
    }

    private static boolean a(AMapLocation aMapLocation, GeoFence geoFence) {
        boolean z = false;
        try {
            if (!de.a(aMapLocation) || geoFence == null || geoFence.getPointList() == null || geoFence.getPointList().isEmpty()) {
                return false;
            }
            Object obj;
            switch (geoFence.getType()) {
                case 0:
                case 2:
                    DPoint center = geoFence.getCenter();
                    obj = null;
                    if (de.a(new double[]{center.getLatitude(), center.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()}) <= geoFence.getRadius()) {
                        obj = 1;
                    }
                    return obj != null;
                case 1:
                case 3:
                    for (List list : geoFence.getPointList()) {
                        int i = 0;
                        double longitude = aMapLocation.getLongitude();
                        double latitude = aMapLocation.getLatitude();
                        double latitude2 = aMapLocation.getLatitude();
                        if (list.size() < 3) {
                            obj = null;
                        } else {
                            if (!((DPoint) list.get(0)).equals(list.get(list.size() - 1))) {
                                list.add(list.get(0));
                            }
                            int i2 = 0;
                            while (i2 < list.size() - 1) {
                                double longitude2 = ((DPoint) list.get(i2)).getLongitude();
                                double latitude3 = ((DPoint) list.get(i2)).getLatitude();
                                double longitude3 = ((DPoint) list.get(i2 + 1)).getLongitude();
                                double latitude4 = ((DPoint) list.get(i2 + 1)).getLatitude();
                                if (a(longitude, latitude, longitude2, latitude3, longitude3, latitude4)) {
                                    obj = 1;
                                } else {
                                    int i3;
                                    if (Math.abs(latitude4 - latitude3) >= 1.0E-9d) {
                                        if (a(longitude2, latitude3, longitude, latitude, 180.0d, latitude2)) {
                                            if (latitude3 > latitude4) {
                                                i3 = i + 1;
                                                i = i3;
                                                i2++;
                                            }
                                        } else if (!a(longitude3, latitude4, longitude, latitude, 180.0d, latitude2)) {
                                            Object obj2;
                                            double d = ((longitude3 - longitude2) * (latitude2 - latitude)) - ((latitude4 - latitude3) * (180.0d - longitude));
                                            if (d != 0.0d) {
                                                double d2 = (((latitude3 - latitude) * (180.0d - longitude)) - ((longitude2 - longitude) * (latitude2 - latitude))) / d;
                                                longitude2 = (((longitude3 - longitude2) * (latitude3 - latitude)) - ((longitude2 - longitude) * (latitude4 - latitude3))) / d;
                                                if (d2 >= 0.0d && d2 <= 1.0d && longitude2 >= 0.0d && longitude2 <= 1.0d) {
                                                    obj2 = 1;
                                                    if (obj2 != null) {
                                                        i3 = i + 1;
                                                        i = i3;
                                                        i2++;
                                                    }
                                                }
                                            }
                                            obj2 = null;
                                            if (obj2 != null) {
                                                i3 = i + 1;
                                                i = i3;
                                                i2++;
                                            }
                                        } else if (latitude4 > latitude3) {
                                            i3 = i + 1;
                                            i = i3;
                                            i2++;
                                        }
                                    }
                                    i3 = i;
                                    i = i3;
                                    i2++;
                                }
                            }
                            obj = i % 2 != 0 ? 1 : null;
                        }
                        z = obj != null ? true : z;
                    }
                    return z;
                default:
                    return false;
            }
        } catch (Throwable th) {
            cw.a(th, "Utils", "isInGeoFence");
            return false;
        }
    }

    static float b(DPoint dPoint, List<DPoint> list) {
        if (dPoint == null || list == null || list.isEmpty()) {
            return Float.MIN_VALUE;
        }
        float f = Float.MIN_VALUE;
        for (DPoint a : list) {
            f = Math.max(f, de.a(dPoint, a));
        }
        return f;
    }

    private int b(GeoFence geoFence) {
        try {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            if (this.g.contains(geoFence)) {
                return 17;
            }
            this.g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static DPoint b(List<DPoint> list) {
        DPoint dPoint;
        double d = 0.0d;
        DPoint dPoint2 = new DPoint();
        if (list != null) {
            try {
                double d2 = 0.0d;
                for (DPoint dPoint3 : list) {
                    d2 += dPoint3.getLatitude();
                    d += dPoint3.getLongitude();
                }
                dPoint3 = new DPoint(de.c(d2 / ((double) list.size())), de.c(d / ((double) list.size())));
            } catch (Throwable th) {
                cw.a(th, "GeoFenceUtil", "getPolygonCenter");
                return dPoint2;
            }
        }
        dPoint3 = dPoint2;
        return dPoint3;
    }

    private static boolean b(AMapLocation aMapLocation, GeoFence geoFence) {
        Throwable th;
        boolean z = true;
        boolean z2 = false;
        try {
            if (a(aMapLocation, geoFence)) {
                if (geoFence.getEnterTime() == -1) {
                    if (geoFence.getStatus() != 1) {
                        geoFence.setEnterTime(de.b());
                        geoFence.setStatus(1);
                        return true;
                    }
                } else if (geoFence.getStatus() != 3 && de.b() - geoFence.getEnterTime() > 600000) {
                    geoFence.setStatus(3);
                    return true;
                }
            } else if (geoFence.getStatus() != 2) {
                try {
                    geoFence.setStatus(2);
                    geoFence.setEnterTime(-1);
                    z2 = true;
                } catch (Throwable th2) {
                    th = th2;
                    cw.a(th, "Utils", "isFenceStatusChanged");
                    return z;
                }
            }
            return z2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            z = false;
            th = th4;
            cw.a(th, "Utils", "isFenceStatusChanged");
            return z;
        }
    }

    private static int c(int i) {
        switch (i) {
            case 1:
            case 4:
            case 5:
            case 7:
            case 16:
            case 17:
                break;
            case 10000:
                i = 0;
                break;
            case 10001:
            case QbSignPlugin.REQUEST_BIND_PHONE /*10002*/:
            case 10007:
            case 10008:
            case 10009:
            case 10012:
            case 10013:
                i = 7;
                break;
            case 10003:
            case 10004:
            case 10005:
            case 10006:
            case 10010:
            case 10011:
            case 10014:
            case 10015:
            case 10016:
            case 10017:
                i = 4;
                break;
            case 20000:
            case 20001:
            case 20002:
                i = 1;
                break;
            case 20003:
                i = 8;
                break;
            default:
                i = 8;
                break;
        }
        if (i != 0) {
            a("添加围栏失败", i, "searchErrCode is " + i, new String[0]);
        }
        return i;
    }

    private void c(GeoFence geoFence) {
        PendingIntent pendingIntent = geoFence.getPendingIntent();
        Object arrayList = new ArrayList();
        if (this.v.isEmpty()) {
            arrayList.add(geoFence);
            this.g.add(geoFence);
        } else {
            arrayList = (ArrayList) this.v.get(pendingIntent);
            if (arrayList == null) {
                arrayList = new ArrayList();
            } else {
                GeoFence geoFence2 = null;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    GeoFence geoFence3 = (GeoFence) it.next();
                    if (!geoFence3.getFenceId().equals(geoFence.getFenceId())) {
                        geoFence3 = geoFence2;
                    }
                    geoFence2 = geoFence3;
                }
                if (geoFence2 != null) {
                    arrayList.remove(geoFence2);
                    this.g.remove(geoFence2);
                }
            }
            arrayList.add(geoFence);
            this.g.add(geoFence);
        }
        this.v.put(pendingIntent, arrayList);
    }

    private void d() {
        if (!this.m) {
            try {
                if (Looper.myLooper() == null) {
                    this.h = new c(this, this.b.getMainLooper());
                } else {
                    this.h = new c(this);
                }
            } catch (Throwable th) {
                cw.a(th, "GeoFenceManger", "init 1");
            }
            try {
                this.k = new b("fenceActionThread");
                this.k.setPriority(5);
                this.k.start();
                this.j = new a(this, this.k.getLooper());
            } catch (Throwable th2) {
                cw.a(th2, "GeoFenceManger", "init 2");
            }
            try {
                Context context = this.b;
                this.n = new b();
                this.o = new c();
                this.s = new AMapLocationClientOption();
                this.p = new AMapLocationClient(this.b);
                this.s.setLocationCacheEnable(false);
                this.p.setLocationListener(this.u);
                if (this.a == null) {
                    this.a = new db();
                }
            } catch (Throwable th22) {
                cw.a(th22, "GeoFenceManger", "init 3");
            }
            this.m = true;
            try {
                if (this.d != null && this.c == null) {
                    createPendingIntent(this.d);
                }
            } catch (Throwable th222) {
                cw.a(th222, "GeoFenceManger", "init 4");
            }
        }
    }

    private static boolean d(GeoFence geoFence) {
        return geoFence.getExpiration() != -1 && geoFence.getExpiration() <= de.b();
    }

    private boolean e() {
        return this.q != null && de.a(this.q) && de.b() - this.r < 10000;
    }

    final void a() {
        try {
            if (this.m) {
                try {
                    synchronized (this.i) {
                        if (this.j != null) {
                            this.j.removeCallbacksAndMessages(null);
                        }
                        this.j = null;
                    }
                } catch (Throwable th) {
                    cw.a(th, "GeoFenceManager", "destroyActionHandler");
                }
                if (this.p != null) {
                    this.p.stopLocation();
                    this.p.onDestroy();
                }
                this.p = null;
                if (this.k != null) {
                    if (VERSION.SDK_INT >= 18) {
                        this.k.quitSafely();
                    } else {
                        this.k.quit();
                    }
                }
                this.k = null;
                if (this.g != null) {
                    this.g.clear();
                    this.g = null;
                }
                this.n = null;
                if (this.c != null) {
                    this.c.cancel();
                }
                this.c = null;
                if (this.a != null) {
                    this.a.b(this.b);
                }
                this.m = false;
            }
        } catch (Throwable th2) {
        }
    }

    final void a(int i) {
        try {
            synchronized (this.i) {
                if (this.j != null) {
                    this.j.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle) {
        try {
            if (this.h != null) {
                Message obtainMessage = this.h.obtainMessage();
                obtainMessage.what = i;
                obtainMessage.setData(bundle);
                this.h.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.i) {
                if (this.j != null) {
                    Message obtainMessage = this.j.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.j.sendMessageDelayed(obtainMessage, j);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    public final void a(PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            try {
                if (this.v.containsKey(pendingIntent)) {
                    ArrayList arrayList = (ArrayList) this.v.get(pendingIntent);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        arrayList.remove((GeoFence) it.next());
                    }
                    this.v.remove(pendingIntent);
                }
            } catch (Throwable th) {
                cw.a(th, "AMapLocationManager", "doRemoveGeoFenceAlert2");
            }
        }
    }

    public final void a(PendingIntent pendingIntent, String str) {
        if (pendingIntent != null) {
            try {
                if (this.v.containsKey(pendingIntent) && !TextUtils.isEmpty(str) && !this.v.isEmpty()) {
                    Iterator it = ((ArrayList) this.v.get(pendingIntent)).iterator();
                    while (it != null && it.hasNext()) {
                        GeoFence geoFence = (GeoFence) it.next();
                        if (str.equals(geoFence.getFenceId()) || d(geoFence)) {
                            it.remove();
                            this.g.remove(geoFence);
                        }
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "GeoFenceManager", "doRemoveGeoFenceAlert");
            }
        }
    }

    final void a(Bundle bundle) {
        int i;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                cw.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        i = 1;
        if (this.f != i) {
            if (!(this.g == null || this.g.isEmpty())) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence geoFence = (GeoFence) it.next();
                    geoFence.setStatus(0);
                    geoFence.setEnterTime(-1);
                }
            }
            if (this.j != null) {
                if (e()) {
                    a(6, null, 0);
                } else {
                    a(7);
                    a(7, null, 1000);
                }
            }
        }
        this.f = i;
    }

    final void a(GeoFence geoFence) {
        try {
            if (this.b == null) {
                return;
            }
            if (this.c != null || geoFence.getPendingIntent() != null) {
                Intent intent = new Intent();
                intent.putExtras(a(geoFence, geoFence.getFenceId(), geoFence.getCustomId(), geoFence.getStatus(), 0));
                if (this.d != null) {
                    intent.setAction(this.d);
                }
                intent.setPackage(k.c(this.b));
                if (geoFence.getPendingIntent() != null) {
                    geoFence.getPendingIntent().send(this.b, 0, intent);
                } else {
                    this.c.send(this.b, 0, intent);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "resultTriggerGeoFence");
        }
    }

    final void a(AMapLocation aMapLocation) {
        try {
            if (this.g != null && !this.g.isEmpty() && aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence geoFence = (GeoFence) it.next();
                    if (!d(geoFence) && b(aMapLocation, geoFence) && a(geoFence, this.f)) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("geoFence", geoFence);
                        a(1001, bundle);
                    }
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    public void addDistrictGeoFence(String str, String str2) {
        try {
            d();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    public void addKeywordGeoFence(String str, String str2, String str3, int i, String str4) {
        int i2 = 25;
        try {
            d();
            int i3 = i <= 0 ? 10 : i;
            if (i3 <= 25) {
                i2 = i3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString("poiType", str2);
            bundle.putString("city", str3);
            bundle.putInt("size", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    public void addNearbyGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        int i2 = 25;
        try {
            d();
            if (f <= 0.0f || f > 50000.0f) {
                f = 3000.0f;
            }
            int i3 = i <= 0 ? 10 : i;
            if (i3 <= 25) {
                i2 = i3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f);
            bundle.putInt("size", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    public void addPolygonGeoFence(List<DPoint> list, String str) {
        try {
            d();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("points", new ArrayList(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    public void addRoundGeoFence(DPoint dPoint, float f, String str, String str2, long j, PendingIntent pendingIntent) {
        try {
            d();
            Bundle bundle = new Bundle();
            bundle.putParcelable("point", dPoint);
            bundle.putFloat("radius", f);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str2);
            bundle.putLong("expiration", j);
            bundle.putParcelable("pIntent", pendingIntent);
            a(0, bundle, 0);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    final void b() {
        try {
            if (this.p != null) {
                try {
                    if (this.l) {
                        a(8);
                    }
                    if (this.p != null) {
                        this.p.stopLocation();
                    }
                    this.l = false;
                } catch (Throwable th) {
                }
                this.s.setOnceLocation(true);
                this.p.setLocationOption(this.s);
                this.p.startLocation();
            }
        } catch (Throwable th2) {
            cw.a(th2, "GeoFenceManager", "doStartOnceLocation");
        }
    }

    final void b(int i) {
        try {
            if (this.b != null && this.c != null) {
                Intent intent = new Intent();
                intent.putExtras(a(null, null, null, 4, i));
                this.c.send(this.b, 0, intent);
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    final void b(Bundle bundle) {
        try {
            String string;
            int i;
            Bundle bundle2;
            ArrayList arrayList = new ArrayList();
            String str = "";
            if (!(bundle == null || bundle.isEmpty())) {
                DPoint dPoint = (DPoint) bundle.getParcelable("point");
                CharSequence string2 = bundle.getString(GeoFence.BUNDLE_KEY_FENCEID);
                string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint == null) {
                    str = string;
                } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                    a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                    i = 1;
                    bundle2 = new Bundle();
                    bundle2.putInt("errorCode", i);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    a(1000, bundle2);
                } else {
                    GeoFence a = a(bundle, false);
                    if (TextUtils.isEmpty(string2)) {
                        i = b(a);
                    } else {
                        c(a);
                        i = 0;
                    }
                    if (i == 0) {
                        arrayList.add(a);
                    }
                    bundle2 = new Bundle();
                    bundle2.putInt("errorCode", i);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    a(1000, bundle2);
                }
            }
            string = str;
            i = 1;
            bundle2 = new Bundle();
            bundle2.putInt("errorCode", i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
            a(1000, bundle2);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doAddGeoFence_round");
        }
    }

    final void c() {
        try {
            if (de.a(this.q)) {
                AMapLocation aMapLocation = this.q;
                List<GeoFence> list = this.g;
                float f = Float.MAX_VALUE;
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0 && list != null && !list.isEmpty()) {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    float f2 = Float.MAX_VALUE;
                    for (GeoFence geoFence : list) {
                        float a = de.a(dPoint, geoFence.getCenter());
                        if (a > geoFence.getMinDis2Center() && a < geoFence.getMaxDis2Center()) {
                            f = 0.0f;
                            break;
                        }
                        if (a > geoFence.getMaxDis2Center()) {
                            f2 = Math.min(f2, a - geoFence.getMaxDis2Center());
                        }
                        f2 = a < geoFence.getMinDis2Center() ? Math.min(f2, geoFence.getMinDis2Center() - a) : f2;
                    }
                    f = f2;
                }
                if (f < 1000.0f) {
                    a(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong(g.az, 2000);
                    a(8, bundle, 1000);
                } else if (f < 5000.0f) {
                    a(7);
                    a(7, null, 10000);
                } else {
                    a(7);
                    a(7, null, (long) (((f - 4000.0f) / 100.0f) * 1000.0f));
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    final void c(Bundle bundle) {
        int i = 1;
        try {
            ArrayList arrayList = new ArrayList();
            String str = "";
            if (!(bundle == null || bundle.isEmpty())) {
                List parcelableArrayList = bundle.getParcelableArrayList("points");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (parcelableArrayList != null && parcelableArrayList.size() > 2) {
                    GeoFence a = a(bundle, true);
                    i = b(a);
                    if (i == 0) {
                        arrayList.add(a);
                    }
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle2.putInt("errorCode", i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle2);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doAddGeoFence_polygon");
        }
    }

    public PendingIntent createPendingIntent(String str) {
        try {
            d();
            Intent intent = new Intent();
            intent.setPackage(k.c(this.b));
            intent.setAction(str);
            this.c = PendingIntent.getBroadcast(this.b, 0, intent, 0);
            this.d = str;
            if (!(this.g == null || this.g.isEmpty())) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence geoFence = (GeoFence) it.next();
                    geoFence.setPendingIntent(this.c);
                    geoFence.setPendingIntentAction(this.d);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "createPendingIntent");
        }
        return this.c;
    }

    final void d(Bundle bundle) {
        int i = 1;
        try {
            Bundle bundle2;
            String str = "";
            ArrayList arrayList = new ArrayList();
            if (!(bundle == null || bundle.isEmpty())) {
                Object string = bundle.getString("keyword");
                String string2 = bundle.getString("poiType");
                DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                float f = bundle.getFloat("aroundRadius", 3000.0f);
                int i2 = bundle.getInt("size", 10);
                String string3 = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint == null || TextUtils.isEmpty(string)) {
                    str = string3;
                } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                    a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                    str = string3;
                } else {
                    str = this.n.a(this.b, "http://restapi.amap.com/v3/place/around?", string, string2, String.valueOf(i2), String.valueOf(de.c(dPoint.getLatitude())), String.valueOf(de.c(dPoint.getLongitude())), String.valueOf(Float.valueOf(f).intValue()));
                    if (str != null) {
                        List arrayList2 = new ArrayList();
                        bundle2 = new Bundle();
                        bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string3);
                        bundle2.putString("pendingIntentAction", this.d);
                        bundle2.putLong("expiration", -1);
                        bundle2.putInt("activatesAction", this.f);
                        bundle2.putFloat("geoRadius", 200.0f);
                        c cVar = this.o;
                        int b = c.b(str, arrayList2, bundle2);
                        if (b != 10000) {
                            b = c(b);
                        } else if (arrayList2.isEmpty()) {
                            i = 16;
                            str = string3;
                        } else {
                            b = a(arrayList2);
                            if (b == 0) {
                                arrayList.addAll(arrayList2);
                                i = b;
                                str = string3;
                            }
                        }
                        i = b;
                        str = string3;
                    } else {
                        i = 4;
                        str = string3;
                    }
                }
            }
            bundle2 = new Bundle();
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle2.putInt("errorCode", i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle2);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doAddGeoFence_nearby");
        }
    }

    final void e(Bundle bundle) {
        try {
            int i;
            String str = "";
            ArrayList arrayList = new ArrayList();
            if (bundle == null || bundle.isEmpty()) {
                i = 1;
            } else {
                CharSequence string = bundle.getString("keyword");
                CharSequence string2 = bundle.getString("poiType");
                String string3 = bundle.getString("city");
                int i2 = bundle.getInt("size", 10);
                String string4 = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                Bundle bundle2 = new Bundle();
                bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string4);
                bundle2.putString("pendingIntentAction", this.d);
                bundle2.putLong("expiration", -1);
                bundle2.putInt("activatesAction", this.f);
                bundle2.putFloat("geoRadius", 1000.0f);
                str = this.n.a(this.b, "http://restapi.amap.com/v3/place/text?", string, string2, string3, String.valueOf(i2));
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    str = string4;
                    i = 1;
                } else if (str != null) {
                    List arrayList2 = new ArrayList();
                    c cVar = this.o;
                    int a = c.a(str, arrayList2, bundle2);
                    if (a != 10000) {
                        a = c(a);
                    } else if (arrayList2.isEmpty()) {
                        i = 16;
                        str = string4;
                    } else {
                        a = a(arrayList2);
                        if (a == 0) {
                            arrayList.addAll(arrayList2);
                            i = a;
                            str = string4;
                        }
                    }
                    i = a;
                    str = string4;
                } else {
                    i = 4;
                    str = string4;
                }
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle3.putInt("errorCode", i);
            bundle3.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle3);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doAddGeoFence_Keyword");
        }
    }

    final void f(Bundle bundle) {
        try {
            String string;
            int c;
            Bundle bundle2;
            ArrayList arrayList = new ArrayList();
            String str = "";
            if (!(bundle == null || bundle.isEmpty())) {
                str = bundle.getString("keyword");
                string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                String a = this.n.a(this.b, "http://restapi.amap.com/v3/config/district?", str);
                if (TextUtils.isEmpty(str)) {
                    str = string;
                } else {
                    if (a != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                        bundle3.putString("pendingIntentAction", this.d);
                        bundle3.putLong("expiration", -1);
                        bundle3.putInt("activatesAction", this.f);
                        List arrayList2 = new ArrayList();
                        c = this.o.c(a, arrayList2, bundle3);
                        if (c != 10000) {
                            c = c(c);
                        } else if (arrayList2.isEmpty()) {
                            c = 16;
                        } else {
                            c = a(arrayList2);
                            if (c == 0) {
                                arrayList.addAll(arrayList2);
                            }
                        }
                    } else {
                        c = 4;
                    }
                    bundle2 = new Bundle();
                    bundle2.putInt("errorCode", c);
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                    a(1000, bundle2);
                }
            }
            String str2 = str;
            c = 1;
            string = str2;
            bundle2 = new Bundle();
            bundle2.putInt("errorCode", c);
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string);
            bundle2.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle2);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doAddGeoFence_district");
        }
    }

    final void g(Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    int i = bundle.getInt("errorCode");
                    List parcelableArrayList = bundle.getParcelableArrayList("resultList");
                    String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                    if (string == null) {
                        string = "";
                    }
                    if (this.e != null) {
                        this.e.onGeoFenceCreateFinished(parcelableArrayList, i, string);
                    }
                    if (i == 0 && this.j != null) {
                        if (e()) {
                            new Bundle().putParcelable("loc", this.q);
                            a(6, null, 0);
                            a(5, bundle, 0);
                            return;
                        }
                        a(7);
                        a(7, null, 1000);
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
            }
        }
    }

    public List<GeoFence> getAllGeoFence() {
        try {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            return (ArrayList) this.g.clone();
        } catch (Throwable th) {
            return new ArrayList();
        }
    }

    final void h(Bundle bundle) {
        try {
            if (this.p != null) {
                long j = 2000;
                if (!(bundle == null || bundle.isEmpty())) {
                    j = bundle.getLong(g.az);
                }
                this.s.setOnceLocation(false);
                this.s.setInterval(j);
                this.p.setLocationOption(this.s);
                if (!this.l) {
                    this.p.stopLocation();
                    this.p.startLocation();
                    this.l = true;
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "doStartContinueLocation");
        }
    }

    public void removeGeoFence() {
        a(10, null, 0);
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        boolean z = false;
        try {
            if (this.g != null) {
                d();
                z = this.g.remove(geoFence);
                if (z && this.g.size() == 0) {
                    a(10, null, 0);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
        }
        return z;
    }

    public void setActivateAction(int i) {
        try {
            d();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            a(9, bundle, 0);
        } catch (Throwable th) {
            cw.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.e = geoFenceListener;
        } catch (Throwable th) {
        }
    }
}
