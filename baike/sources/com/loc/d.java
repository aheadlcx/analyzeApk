package com.loc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.APSService;
import com.amap.api.location.DPoint;
import com.amap.api.location.LocationManagerBase;
import com.amap.api.location.UmidtokenInfo;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.core.model.CustomButton;

public class d implements LocationManagerBase {
    private int A = 0;
    private boolean B = true;
    private ServiceConnection C = new dx(this);
    AMapLocationClientOption a = new AMapLocationClientOption();
    public c b;
    g c = null;
    ArrayList<AMapLocationListener> d = new ArrayList();
    a e;
    boolean f = false;
    public boolean g = true;
    public boolean h = true;
    h i;
    Messenger j = null;
    Messenger k = null;
    Intent l = null;
    int m = 0;
    b n = null;
    boolean o = false;
    AMapLocationMode p = AMapLocationMode.Hight_Accuracy;
    Object q = new Object();
    db r = null;
    e s = null;
    String t = null;
    boolean u = false;
    a v = null;
    String w = null;
    private Context x;
    private boolean y = false;
    private boolean z = false;

    public class a extends Handler {
        final /* synthetic */ d a;

        public a(d dVar, Looper looper) {
            this.a = dVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1002:
                    try {
                        d.a(this.a, (AMapLocationListener) message.obj);
                        return;
                    } catch (Throwable th) {
                        cw.a(th, "AMapLocationManage$MHandlerr", "handleMessage SET_LISTENER");
                        return;
                    }
                case 1003:
                    try {
                        this.a.f();
                        return;
                    } catch (Throwable th2) {
                        cw.a(th2, "AMapLocationManager$MHandler", "handleMessage START_LOCATION");
                        return;
                    }
                case 1004:
                    try {
                        this.a.g();
                        return;
                    } catch (Throwable th22) {
                        cw.a(th22, "AMapLocationManager$MHandler", "handleMessage STOP_LOCATION");
                        return;
                    }
                case 1005:
                    try {
                        d.b(this.a, (AMapLocationListener) message.obj);
                        return;
                    } catch (Throwable th222) {
                        cw.a(th222, "AMapLocationManager$MHandler", "handleMessage REMOVE_LISTENER");
                        return;
                    }
                case 1008:
                    try {
                        d.h(this.a);
                        return;
                    } catch (Throwable th2222) {
                        cw.a(th2222, "AMapLocationManager$ActionHandler", "handleMessage START_SOCKET");
                        return;
                    }
                case 1009:
                    try {
                        d.i(this.a);
                        return;
                    } catch (Throwable th22222) {
                        cw.a(th22222, "AMapLocationManager$ActionHandler", "handleMessage STOP_SOCKET");
                        return;
                    }
                case 1011:
                    try {
                        this.a.a();
                        return;
                    } catch (Throwable th222222) {
                        cw.a(th222222, "AMapLocationManager$MHandler", "handleMessage DESTROY");
                        return;
                    }
                case 1014:
                    try {
                        this.a.i.c();
                        return;
                    } catch (Throwable th2222222) {
                        cw.a(th2222222, "AMapLocationManager$ActionHandler", "handleMessage ACTION_SAVE_LAST_LOCATION");
                        return;
                    }
                case 1015:
                    try {
                        g gVar = this.a.c;
                        gVar.c = this.a.a;
                        if (gVar.c == null) {
                            gVar.c = new AMapLocationClientOption();
                        }
                        gVar.b();
                        return;
                    } catch (Throwable th22222222) {
                        cw.a(th22222222, "AMapLocationManager$ActionHandler", "handleMessage START_GPS_LOCATION");
                        return;
                    }
                case 1016:
                    try {
                        if (this.a.c.c()) {
                            this.a.a(1016, null, 1000);
                            return;
                        } else {
                            d.f(this.a);
                            return;
                        }
                    } catch (Throwable th222222222) {
                        cw.a(th222222222, "AMapLocationManager$ActionHandler", "handleMessage START_LBS_LOCATION");
                        return;
                    }
                case 1017:
                    try {
                        this.a.c.a();
                        return;
                    } catch (Throwable th2222222222) {
                        cw.a(th2222222222, "AMapLocationManager$ActionHandler", "handleMessage STOP_GPS_LOCATION");
                        return;
                    }
                case 1018:
                    try {
                        this.a.a = (AMapLocationClientOption) message.obj;
                        if (this.a.a != null) {
                            d.g(this.a);
                            return;
                        }
                        return;
                    } catch (Throwable th22222222222) {
                        cw.a(th22222222222, "AMapLocationManager$ActionHandler", "handleMessage SET_OPTION");
                        return;
                    }
                default:
                    return;
            }
        }
    }

    static class b extends HandlerThread {
        d a = null;

        public b(String str, d dVar) {
            super(str);
            this.a = dVar;
        }

        protected final void onLooperPrepared() {
            try {
                this.a.i.a();
                this.a.k = new Messenger(this.a.b);
                this.a.a(this.a.b());
                super.onLooperPrepared();
            } catch (Throwable th) {
            }
        }
    }

    public class c extends Handler {
        final /* synthetic */ d a;

        public c(d dVar) {
            this.a = dVar;
        }

        public c(d dVar, Looper looper) {
            this.a = dVar;
            super(looper);
        }

        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (!this.a.o || cw.c()) {
                switch (message.what) {
                    case 1:
                        try {
                            d.a(this.a, message.getData());
                            return;
                        } catch (Throwable th) {
                            cw.a(th, "AMapLocationManager$ActionHandler", "handleMessage RESULT_LBS_LOCATIONSUCCESS");
                            return;
                        }
                    case 2:
                        break;
                    case 3:
                        return;
                    case 5:
                        try {
                            this.a.a(10, message.getData());
                            return;
                        } catch (Throwable th2) {
                            cw.a(th2, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONCHANGE");
                            return;
                        }
                    case 6:
                        try {
                            Bundle data = message.getData();
                            if (this.a.c != null) {
                                g gVar = this.a.c;
                                if (data != null) {
                                    data.setClassLoader(AMapLocation.class.getClassLoader());
                                    gVar.h = data.getInt("lMaxGeoDis");
                                    gVar.i = data.getInt("lMinGeoDis");
                                    AMapLocation aMapLocation = (AMapLocation) data.getParcelable("loc");
                                    if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                        gVar.n = aMapLocation;
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th22) {
                            cw.a(th22, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_GEO_SUCCESS");
                            return;
                        }
                    case 7:
                        try {
                            this.a.B = message.getData().getBoolean("ngpsAble");
                            return;
                        } catch (Throwable th222) {
                            cw.a(th222, "AMapLocationManager$ActionHandler", "handleMessage RESULT_NGPS_ABLE");
                            return;
                        }
                    case 8:
                        db.a(null, 2141);
                        break;
                    case 100:
                        try {
                            d.a(this.a);
                            return;
                        } catch (Throwable th2222) {
                            cw.a(th2222, "AMapLocationManager$ActionHandler", "handleMessage RESULT_FASTSKY");
                            return;
                        }
                    default:
                        return;
                }
                try {
                    d.a(this.a, message);
                } catch (Throwable th22222) {
                    cw.a(th22222, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONSUCCESS");
                }
            }
        }
    }

    public d(Context context, Intent intent) {
        this.x = context;
        this.l = intent;
        if (cw.c()) {
            try {
                dc.a(this.x, cw.b());
            } catch (Throwable th) {
            }
        }
        try {
            if (Looper.myLooper() == null) {
                this.b = new c(this, this.x.getMainLooper());
            } else {
                this.b = new c(this);
            }
        } catch (Throwable th2) {
            cw.a(th2, "AMapLocationManager", "init 1");
        }
        try {
            this.i = new h(this.x);
        } catch (Throwable th22) {
            cw.a(th22, "AMapLocationManager", "init 5");
        }
        this.n = new b("amapLocManagerThread", this);
        this.n.setPriority(5);
        this.n.start();
        this.v = a(this.n.getLooper());
        try {
            this.c = new g(this.x, this.b);
        } catch (Throwable th222) {
            cw.a(th222, "AMapLocationManager", "init 3");
        }
        if (this.r == null) {
            this.r = new db();
        }
    }

    private AMapLocationServer a(bu buVar) {
        if (this.a.isLocationCacheEnable()) {
            try {
                return buVar.i();
            } catch (Throwable th) {
                cw.a(th, "AMapLocationManager", "doFirstCacheLoc");
            }
        }
        return null;
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.q) {
            this.v = new a(this, looper);
            aVar = this.v;
        }
        return aVar;
    }

    private void a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                cw.a(th, "AMapLocationManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = cw.d(this.x);
        }
        bundle.putString("c", this.t);
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.setData(bundle);
        obtain.replyTo = this.k;
        if (this.j != null) {
            this.j.send(obtain);
        }
    }

    private void a(int i, Object obj, long j) {
        synchronized (this.q) {
            if (this.v != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.obj = obj;
                this.v.sendMessageDelayed(obtain, j);
            }
        }
    }

    private void a(AMapLocation aMapLocation, Throwable th) {
        try {
            if (!cw.c() || aMapLocation != null) {
                if (aMapLocation == null) {
                    aMapLocation = new AMapLocation("");
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("amapLocation is null");
                }
                if (!"gps".equals(aMapLocation.getProvider())) {
                    aMapLocation.setProvider("lbs");
                }
                if (this.z) {
                    aMapLocation.setTime(aMapLocation.getTime());
                    if (this.i.a(aMapLocation, this.w)) {
                        a(1014, null, 0);
                    }
                    db.a(this.x, this.A, aMapLocation);
                    db.b(this.x, this.A, aMapLocation);
                    try {
                        if ("gps".equals(aMapLocation.getProvider()) || !this.c.c()) {
                            aMapLocation.setAltitude(de.b(aMapLocation.getAltitude()));
                            aMapLocation.setBearing(de.a(aMapLocation.getBearing()));
                            aMapLocation.setSpeed(de.a(aMapLocation.getSpeed()));
                            Iterator it = this.d.iterator();
                            while (it.hasNext()) {
                                try {
                                    ((AMapLocationListener) it.next()).onLocationChanged(aMapLocation);
                                } catch (Throwable th2) {
                                }
                            }
                        }
                    } catch (Throwable th3) {
                    }
                }
                if (!this.o || cw.c()) {
                    dc.b(this.x);
                    if (this.a.isOnceLocation()) {
                        g();
                    }
                }
            } else if (th != null) {
                dc.a(this.x, "loc", th.getMessage());
            } else {
                dc.a(this.x, "loc", "amaplocation is null");
            }
        } catch (Throwable th4) {
            cw.a(th4, "AMapLocationManager", "handlerLocation part3");
        }
    }

    static /* synthetic */ void a(d dVar) {
        Object obj = 1;
        Object obj2 = null;
        try {
            if (dVar.x.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                obj2 = 1;
            } else if (dVar.x instanceof Activity) {
                int i = 1;
                obj = null;
            } else {
                obj = null;
            }
            if (obj2 != null) {
                Builder builder = new Builder(dVar.x);
                builder.setMessage(cv.g());
                if (!("".equals(cv.h()) || cv.h() == null)) {
                    builder.setPositiveButton(cv.h(), new dy(dVar));
                }
                builder.setNegativeButton(cv.i(), new dz(dVar));
                AlertDialog create = builder.create();
                if (obj != null) {
                    create.getWindow().setType(2003);
                }
                create.setCanceledOnTouchOutside(false);
                create.show();
                return;
            }
            dVar.e();
        } catch (Throwable th) {
            dVar.e();
            cw.a(th, "AMapLocationManager", "showDialog");
        }
    }

    static /* synthetic */ void a(d dVar, Bundle bundle) {
        AMapLocation aMapLocation;
        Throwable th = null;
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                dVar.A = bundle.getInt("originalLocType", 0);
                dVar.w = bundle.getString("nb", null);
                if (!(aMapLocation == null || aMapLocation.getErrorCode() != 0 || TextUtils.isEmpty(aMapLocation.getAdCode()) || dVar.c == null)) {
                    dVar.c.n = aMapLocation;
                }
            } catch (Throwable th2) {
                cw.a(th2, "AMapLocationManager", "doLbsLocationSuccess");
                Throwable th3 = th2;
                aMapLocation = null;
                th = th3;
            }
        } else {
            aMapLocation = null;
        }
        dVar.a(aMapLocation, th);
    }

    static /* synthetic */ void a(d dVar, Message message) {
        try {
            AMapLocation aMapLocation = (AMapLocation) message.obj;
            if (aMapLocation != null) {
                dVar.A = aMapLocation.getLocationType();
            }
            if (dVar.h && dVar.j != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", cw.b(dVar.a));
                dVar.a(0, bundle);
                dVar.h = false;
            }
            dVar.a(aMapLocation, null);
            if (dVar.B) {
                dVar.a(7, null);
            }
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "doGpsLocationSuccess");
        }
    }

    static /* synthetic */ void a(d dVar, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (dVar.d == null) {
            dVar.d = new ArrayList();
        }
        if (!dVar.d.contains(aMapLocationListener)) {
            dVar.d.add(aMapLocationListener);
        }
    }

    private AMapLocationServer b(bu buVar) {
        da daVar;
        Object apikey;
        Throwable th;
        AMapLocationServer aMapLocationServer;
        String umidtoken;
        Parcelable b;
        Bundle bundle;
        AMapLocationServer aMapLocationServer2 = null;
        Object obj = 1;
        try {
            daVar = new da();
            daVar.a(de.b());
            apikey = AMapLocationClientOption.getAPIKEY();
            if (!TextUtils.isEmpty(apikey)) {
                l.a(apikey);
            }
        } catch (Throwable th2) {
            th = th2;
            aMapLocationServer = aMapLocationServer2;
            try {
                cw.a(th, "AMapLocationManager", "apsLocation");
                if (buVar != null) {
                    try {
                        buVar.e();
                    } catch (Throwable th3) {
                    }
                }
                return aMapLocationServer;
            } catch (Throwable th4) {
            }
        }
        try {
            umidtoken = UmidtokenInfo.getUmidtoken();
            if (!TextUtils.isEmpty(umidtoken)) {
                n.a(umidtoken);
            }
        } catch (Throwable th5) {
            cw.a(th5, "AMapLocationManager", "apsLocation setUmidToken");
        }
        try {
            buVar.a(this.x);
            buVar.a(this.a);
            Context context = this.x;
            buVar.h();
        } catch (Throwable th52) {
            cw.a(th52, "AMapLocationManager", "initApsBase");
        }
        aMapLocationServer = a(buVar);
        if (aMapLocationServer == null) {
            try {
                if (de.a(cw.b(this.x))) {
                    cw.a = "http://abroad.apilocate.amap.com/mobile/binary";
                }
            } catch (Throwable th6) {
                th52 = th6;
                cw.a(th52, "AMapLocationManager", "apsLocation");
                if (buVar != null) {
                    buVar.e();
                }
                return aMapLocationServer;
            }
            try {
                aMapLocationServer = buVar.a(false);
            } catch (Throwable th522) {
                cw.a(th522, "AMapLocationManager", "apsLocation:doFirstNetLocate");
            }
        } else {
            obj = null;
        }
        daVar.b(de.b());
        daVar.a(aMapLocationServer);
        if (aMapLocationServer != null) {
            umidtoken = aMapLocationServer.k();
        } else {
            apikey = aMapLocationServer2;
        }
        try {
            if (this.a.isLocationCacheEnable() && this.i != null) {
                b = this.i.b(aMapLocationServer, umidtoken);
                bundle = new Bundle();
                if (aMapLocationServer == null) {
                    bundle.putInt("originalLocType", aMapLocationServer.getLocationType());
                    bundle.putParcelable("loc", b);
                    bundle.putString("nb", aMapLocationServer.k());
                } else {
                    bundle.putInt("originalLocType", 0);
                }
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = 1;
                this.b.sendMessage(obtain);
                db.a(this.x, daVar);
                if (obj != null) {
                    try {
                        aMapLocationServer2 = buVar.a(true);
                    } catch (Throwable th5222) {
                        cw.a(th5222, "AMapLocationManager", "apsLocation:doFirstNetLocate 2");
                    }
                    if (aMapLocationServer2.getErrorCode() == 0) {
                        try {
                            buVar.a(aMapLocationServer2);
                        } catch (Throwable th52222) {
                            cw.a(th52222, "AMapLocationManager", "apsLocation:doFirstAddCache");
                        }
                    }
                }
                if (buVar != null) {
                    try {
                        buVar.e();
                    } catch (Throwable th7) {
                    }
                }
                return aMapLocationServer;
            }
        } catch (Throwable th522222) {
            cw.a(th522222, "AMapLocationManager", "fixLastLocation");
        }
        apikey = aMapLocationServer;
        try {
            bundle = new Bundle();
            if (aMapLocationServer == null) {
                bundle.putInt("originalLocType", 0);
            } else {
                bundle.putInt("originalLocType", aMapLocationServer.getLocationType());
                bundle.putParcelable("loc", b);
                bundle.putString("nb", aMapLocationServer.k());
            }
            Message obtain2 = Message.obtain();
            obtain2.setData(bundle);
            obtain2.what = 1;
            this.b.sendMessage(obtain2);
        } catch (Throwable th5222222) {
            cw.a(th5222222, "AMapLocationManager", "apsLocation:callback");
        }
        db.a(this.x, daVar);
        if (obj != null) {
            aMapLocationServer2 = buVar.a(true);
            if (aMapLocationServer2.getErrorCode() == 0) {
                buVar.a(aMapLocationServer2);
            }
        }
        if (buVar != null) {
            buVar.e();
        }
        return aMapLocationServer;
    }

    static /* synthetic */ void b(d dVar, AMapLocationListener aMapLocationListener) {
        if (!dVar.d.isEmpty() && dVar.d.contains(aMapLocationListener)) {
            dVar.d.remove(aMapLocationListener);
        }
        if (dVar.d.isEmpty()) {
            dVar.g();
        }
    }

    private void c() {
        synchronized (this.q) {
            if (this.v != null) {
                this.v.removeMessages(1016);
            }
        }
    }

    private boolean d() {
        int i = 0;
        do {
            try {
                if (this.j != null) {
                    break;
                }
                Thread.sleep(100);
                i++;
            } catch (Throwable th) {
                cw.a(th, "AMapLocationManager", "checkAPSManager");
                return false;
            }
        } while (i < 50);
        if (this.j != null) {
            return true;
        }
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        Parcelable aMapLocation = new AMapLocation("");
        aMapLocation.setErrorCode(10);
        aMapLocation.setLocationDetail("请检查配置文件是否配置服务，并且manifest中service标签是否配置在application标签内");
        bundle.putParcelable("loc", aMapLocation);
        obtain.setData(bundle);
        obtain.what = 1;
        this.b.sendMessage(obtain);
        return false;
    }

    private void e() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", cv.l()));
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            intent.setData(Uri.parse(cv.j()));
            this.x.startActivity(intent);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "callAMap part2");
        }
    }

    private void f() {
        long j = 0;
        if (this.a == null) {
            this.a = new AMapLocationClientOption();
        }
        if (!this.z) {
            this.z = true;
            switch (ea.a[this.a.getLocationMode().ordinal()]) {
                case 1:
                    a(1017, null, 0);
                    a(1016, null, 0);
                    return;
                case 2:
                    c();
                    a(1015, null, 0);
                    return;
                case 3:
                    a(1015, null, 0);
                    if (this.a.isGpsFirst() && this.a.isOnceLocation()) {
                        j = i.MIN_UPLOAD_INTERVAL;
                    }
                    a(1016, null, j);
                    return;
                default:
                    return;
            }
        }
    }

    static /* synthetic */ void f(d dVar) {
        try {
            if (dVar.g) {
                dVar.g = false;
                AMapLocationServer b = dVar.b(new bu());
                if (dVar.d()) {
                    Bundle bundle = new Bundle();
                    String str = "0";
                    if (b != null && (b.getLocationType() == 2 || b.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putBundle("optBundle", cw.b(dVar.a));
                    bundle.putString("isCacheLoc", str);
                    dVar.a(0, bundle);
                }
            } else if (dVar.d()) {
                Bundle bundle2 = new Bundle();
                bundle2.putBundle("optBundle", cw.b(dVar.a));
                if (!dVar.c.c()) {
                    dVar.a(1, bundle2);
                }
            }
            try {
                if (!dVar.a.isOnceLocation()) {
                    dVar.h();
                }
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
        }
    }

    private void g() {
        try {
            this.c.a();
            c();
            this.z = false;
            this.m = 0;
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "stopLocation");
        }
    }

    static /* synthetic */ void g(d dVar) {
        g gVar = dVar.c;
        gVar.c = dVar.a;
        if (gVar.c == null) {
            gVar.c = new AMapLocationClientOption();
        }
        if (!(gVar.c.getLocationMode() == AMapLocationMode.Device_Sensors || gVar.a == null)) {
            gVar.a.removeMessages(8);
        }
        if (dVar.z && !dVar.a.getLocationMode().equals(dVar.p)) {
            dVar.g();
            dVar.f();
        }
        dVar.p = dVar.a.getLocationMode();
        if (dVar.r != null) {
            if (dVar.a.isOnceLocation()) {
                dVar.r.a(dVar.x, 0);
            } else {
                dVar.r.a(dVar.x, 1);
            }
            dVar.r.a(dVar.x, dVar.a);
        }
    }

    private void h() {
        long j = 1000;
        if (this.a.getLocationMode() != AMapLocationMode.Device_Sensors) {
            if (this.a.getInterval() >= 1000) {
                j = this.a.getInterval();
            }
            a(1016, null, j);
        }
    }

    static /* synthetic */ void h(d dVar) {
        try {
            if (dVar.j != null) {
                dVar.m = 0;
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", cw.b(dVar.a));
                dVar.a(2, bundle);
                return;
            }
            dVar.m++;
            if (dVar.m < 10) {
                dVar.a(1008, null, 50);
            }
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "startAssistantLocationImpl");
        }
    }

    static /* synthetic */ void i(d dVar) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle("optBundle", cw.b(dVar.a));
            dVar.a(3, bundle);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "stopAssistantLocationImpl");
        }
    }

    final void a() {
        a(12, null);
        this.g = true;
        this.h = true;
        this.y = false;
        g();
        if (this.r != null) {
            this.r.b(this.x);
        }
        db.a(this.x);
        if (this.e != null) {
            this.e.removeGeoFence();
        }
        if (this.s != null) {
            this.s.d.sendEmptyMessage(11);
        } else if (this.C != null) {
            this.x.unbindService(this.C);
        }
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        this.C = null;
        synchronized (this.q) {
            if (this.v != null) {
                this.v.removeCallbacksAndMessages(null);
            }
            this.v = null;
        }
        if (this.n != null) {
            if (VERSION.SDK_INT >= 18) {
                try {
                    cz.a(this.n, HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable th) {
                    this.n.quit();
                }
            } else {
                this.n.quit();
            }
        }
        this.n = null;
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
        }
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
    }

    final void a(Intent intent) {
        try {
            this.x.bindService(intent, this.C, 1);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "startServiceImpl");
        }
    }

    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            if (this.e == null) {
                this.e = new a(this.x);
                this.e.setActivateAction(7);
            }
            this.e.addRoundGeoFence(new DPoint(d, d2), f, null, str, j, pendingIntent);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "addGeoFenceAlert");
        }
    }

    final Intent b() {
        if (this.l == null) {
            this.l = new Intent(this.x, APSService.class);
        }
        String str = "";
        try {
            str = !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : k.f(this.x);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "startServiceImpl p2");
        }
        this.l.putExtra("a", str);
        this.l.putExtra(CustomButton.POSITION_BOTTOM, k.c(this.x));
        this.l.putExtra("d", UmidtokenInfo.getUmidtoken());
        return this.l;
    }

    public AMapLocation getLastKnownLocation() {
        AMapLocation aMapLocation = null;
        try {
            if (this.i != null) {
                h hVar = this.i;
                if (hVar.b == null) {
                    hVar.b = hVar.d();
                }
                if (hVar.b != null && de.a(hVar.b.a())) {
                    aMapLocation = hVar.b.a();
                }
            }
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "getLastKnownLocation");
        }
        return aMapLocation;
    }

    public boolean isStarted() {
        return this.y;
    }

    public void onDestroy() {
        try {
            a(1011, null, 0);
            this.o = true;
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "onDestroy");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            if (this.e != null) {
                this.e.a(pendingIntent);
            }
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "removeGeoFenceAlert 2");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            if (this.e != null) {
                this.e.a(pendingIntent, str);
            }
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "removeGeoFenceAlert 1");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, aMapLocationListener, 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "setLocationListener");
        }
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            a(1018, aMapLocationClientOption.clone(), 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "setLocationOption");
        }
    }

    public void startAssistantLocation() {
        try {
            a(1008, null, 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "startAssistantLocation");
        }
    }

    public void startLocation() {
        try {
            a(1003, null, 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            a(1009, null, 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            a(1004, null, 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, aMapLocationListener, 0);
        } catch (Throwable th) {
            cw.a(th, "AMapLocationManager", "unRegisterLocationListener");
        }
    }
}
