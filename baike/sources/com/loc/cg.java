package com.loc;

import android.content.Context;
import android.net.ConnectivityManager;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.amap.api.location.AMapLocationClientOption;
import com.baidu.mobstat.Config;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import qsbk.app.core.model.User;

public final class cg {
    public String a = "com.data.carrier_v4.CollectorManager";
    private Object b = null;
    private boolean c = false;
    private Timer d = null;
    private TimerTask e = null;
    private int f = 0;
    private boolean g = true;
    private cr h = null;
    private ci i = null;
    private ConnectivityManager j = null;
    private long k = 0;
    private Context l = null;
    private JSONObject m = null;

    class a implements Runnable {
        final /* synthetic */ cg a;

        a(cg cgVar) {
            this.a = cgVar;
        }

        public final void run() {
            try {
                cz.a(this.a.b, "destroy", new Object[0]);
                this.a.b = null;
            } catch (Throwable th) {
                cw.a(th, "CollectionManager", "stop3rdCM");
            }
        }
    }

    static /* synthetic */ void a(cg cgVar, int i) {
        int i2 = 674234367;
        if (cgVar.j() && cv.v()) {
            Object[] objArr;
            JSONObject jSONObject;
            Object a;
            String b;
            switch (i) {
                case 0:
                    i2 = 70254591;
                case 2:
                    if (cgVar.i == null || cgVar.i.a(cgVar.j)) {
                        i2 = 2083520511;
                    }
                case 1:
                    objArr = new Object[1];
                    jSONObject = new JSONObject();
                    jSONObject.put(Config.SESSTION_END_TIME, 1);
                    jSONObject.put("d", i2);
                    jSONObject.put(User.UNDEFINED, 1);
                    objArr[0] = jSONObject.toString();
                    cz.a(cgVar.b, "callBackWrapData", objArr);
                    a = cz.a(cgVar.b, "getReportDate", new Object[0]);
                    if (a != null) {
                        b = cgVar.h.b((byte[]) a, cgVar.l, "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&");
                        if (cgVar.j()) {
                            try {
                                cgVar.f = ((Integer) cz.a(cgVar.b, "setUploadResult", b, Integer.valueOf(i2))).intValue();
                            } catch (Throwable th) {
                                cgVar.f = 3;
                            }
                        }
                    }
                    cgVar.e();
                    if (!cgVar.j() && cgVar.k() == 0) {
                        cgVar.i();
                        return;
                    } else if (cgVar.f >= 3) {
                        cgVar.i();
                    }
                default:
                    i2 = 70254591;
            }
            try {
                objArr = new Object[1];
                jSONObject = new JSONObject();
                jSONObject.put(Config.SESSTION_END_TIME, 1);
                jSONObject.put("d", i2);
                jSONObject.put(User.UNDEFINED, 1);
                objArr[0] = jSONObject.toString();
                cz.a(cgVar.b, "callBackWrapData", objArr);
            } catch (Throwable th2) {
                cw.a(th2, "CollectionManager", "up");
                return;
            }
            try {
                a = cz.a(cgVar.b, "getReportDate", new Object[0]);
            } catch (Throwable th3) {
                a = null;
            }
            if (a != null) {
                b = cgVar.h.b((byte[]) a, cgVar.l, "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&");
                if (cgVar.j()) {
                    cgVar.f = ((Integer) cz.a(cgVar.b, "setUploadResult", b, Integer.valueOf(i2))).intValue();
                }
            }
            cgVar.e();
            if (!cgVar.j()) {
            }
            if (cgVar.f >= 3) {
                cgVar.i();
            }
        }
    }

    private boolean g() {
        boolean z = false;
        try {
            if (j()) {
                z = h();
            }
        } catch (Throwable th) {
            cw.a(th, "CollectionManager", "collStarted");
        }
        return z;
    }

    private boolean h() {
        try {
            return ((Boolean) cz.a(this.b, "isStarted", new Object[0])).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private void i() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
        if (this.d != null) {
            this.d.cancel();
            this.d.purge();
            this.d = null;
        }
    }

    private boolean j() {
        return this.b != null;
    }

    private int k() {
        try {
            return ((Integer) cz.a(this.b, "getLeftUploadNum", new Object[0])).intValue();
        } catch (Throwable th) {
            return 0;
        }
    }

    public final void a() {
        if (j()) {
            try {
                cz.a(this.b, "getColUpHist", new Object[0]);
            } catch (Throwable th) {
            }
        }
    }

    public final void a(Context context, cr crVar, ci ciVar, AMapLocationClientOption aMapLocationClientOption, ConnectivityManager connectivityManager) {
        Object obj = 1;
        if (cv.v()) {
            JSONObject a = cw.a(aMapLocationClientOption);
            try {
                if (this.b == null) {
                    this.l = context;
                    this.h = crVar;
                    this.i = ciVar;
                    this.m = a;
                    this.j = connectivityManager;
                    if (!this.c) {
                        this.k = de.b();
                        s a2 = cw.a("Collection", JsonSerializer.VERSION);
                        this.c = db.a(context, a2);
                        if (this.c) {
                            try {
                                this.b = au.a(context, a2, this.a, null, new Class[]{Context.class}, new Object[]{context});
                            } catch (Throwable th) {
                            }
                        } else {
                            this.c = true;
                        }
                    }
                }
            } catch (Throwable th2) {
                cw.a(th2, "CollectionManager", "initCollection");
            }
            if (!this.g || g() || !j()) {
                return;
            }
            if (cv.v()) {
                if (de.a(this.m, "coll")) {
                    try {
                        Object obj2;
                        if (this.m.getString("coll").equals("0")) {
                            obj2 = null;
                        } else {
                            int i = 1;
                        }
                        obj = obj2;
                    } catch (Throwable th22) {
                        cw.a(th22, "CollectionManager", "start3rdCM");
                    }
                }
                if (obj == null) {
                    b();
                    return;
                } else if (!g()) {
                    try {
                        e();
                        try {
                            cz.a(this.b, "start", new Object[0]);
                        } catch (Throwable th3) {
                        }
                        if (this.l != null) {
                            return;
                        }
                        return;
                    } catch (Throwable th222) {
                        cw.a(th222, "CollectionManager", "start3rdCM");
                        return;
                    }
                } else {
                    return;
                }
            }
            b();
        }
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void b() {
        if (j() && g()) {
            try {
                cz.a(this.b, "destroy", new Object[0]);
            } catch (Throwable th) {
                cw.a(th, "CollectionManager", "stop3rdCM");
            }
            this.b = null;
            i();
        }
    }

    public final void c() {
        if (j() && g()) {
            z.b().submit(new a(this));
            i();
        }
    }

    public final void d() {
        if (!this.g || this.i == null || !this.i.a(this.j) || !j()) {
            return;
        }
        if (cv.v()) {
            if (this.e == null) {
                this.e = new dw(this);
            }
            if (this.d == null) {
                this.d = new Timer("T-U", false);
                this.d.schedule(this.e, 2000, 2000);
                return;
            }
            return;
        }
        i();
    }

    public final void e() {
        if (!j() || !j() || k() <= 0) {
        }
    }

    public final String f() {
        try {
            if (this.b != null) {
                return (String) cz.a(this.b, "getInnerString", "version");
            }
        } catch (Throwable th) {
            cw.a(th, "CollectionManager", "getCollVer");
        }
        return null;
    }
}
