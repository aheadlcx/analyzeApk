package com.microquation.linkedme.android.a;

import android.annotation.TargetApi;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import cn.v6.sixrooms.constants.CommonInts;
import cn.v6.sixrooms.constants.CommonStrs;
import com.ali.auth.third.core.model.SystemMessageConstants;
import com.alipay.sdk.util.h;
import com.microquation.linkedme.android.b.k;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.util.c.c;
import com.microquation.linkedme.android.util.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
    private LocationManager a;
    private c b;
    private c c;
    private b d;
    private ArrayList<Location> e;
    private ArrayList<Location> f;
    private boolean g;
    private boolean h;
    private Handler i;
    private boolean j;
    private boolean k;
    private final ScheduledExecutorService l;
    private ScheduledFuture<?> m;

    private static class a {
        private static final d a = new d();
    }

    @TargetApi(9)
    private d() {
        this.g = false;
        this.h = false;
        this.k = false;
        this.l = Executors.newSingleThreadScheduledExecutor();
        this.d = new b();
        b a = b.a(com.microquation.linkedme.android.a.a().i());
        this.d.b(TimeUnit.SECONDS.toMillis((long) a.P()));
        this.d.d(TimeUnit.SECONDS.toMillis((long) a.N()));
        this.d.a((float) a.O());
        this.d.c(TimeUnit.SECONDS.toMillis((long) a.Q()));
        this.d.a(TimeUnit.MINUTES.toMillis((long) a.R()));
        this.j = a.U();
        this.i = new Handler(this, Looper.getMainLooper()) {
            final /* synthetic */ d a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 10000:
                        this.a.a(this.a.d.c(), this.a.d.d(), false);
                        this.a.b(2 * this.a.d.c(), this.a.d.d(), true);
                        return;
                    case 10001:
                        this.a.d();
                        return;
                    case CommonInts.GT_ERROR /*10002*/:
                        this.a.b(this.a.d.c(), this.a.d.d(), true);
                        return;
                    case SystemMessageConstants.USER_CANCEL_CODE /*10003*/:
                        this.a.a(this.a.d.c(), this.a.d.d(), false);
                        this.a.e();
                        return;
                    case SystemMessageConstants.TAOBAO_CANCEL_CODE /*10004*/:
                        if (this.a.a != null) {
                            this.a.e();
                            this.a.d();
                            b.a("停止LC！");
                            return;
                        }
                        return;
                    case SystemMessageConstants.TAOBAO_ERROR_CODE /*10005*/:
                        if (this.a.a != null) {
                            this.a.e();
                            this.a.d();
                            this.a.a = null;
                            b.a("停止All！");
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public static d a() {
        return a.a;
    }

    private String a(ArrayList<Location> arrayList) {
        Iterator it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + b((Location) it.next()) + h.b;
        }
        return str.length() > 0 ? str.substring(0, str.length() - 1) : str;
    }

    private void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            b a = b.a(com.microquation.linkedme.android.a.a().i());
            if (a.Z() && e.a(com.microquation.linkedme.android.a.a().i())) {
                b.a("通过lc上传LC数据：" + str);
                JSONObject jSONObject = new JSONObject();
                try {
                    if (TextUtils.isEmpty(str)) {
                        jSONObject.putOpt(c.SI_DATA.a(), com.microquation.linkedme.android.util.a.a(str2, "linkedme2017nble"));
                    } else {
                        jSONObject.putOpt(c.LC_DATA.a(), com.microquation.linkedme.android.util.a.a(str, "linkedme2017nble"));
                    }
                    Object A = com.microquation.linkedme.android.a.a().h().A();
                    if (!TextUtils.isEmpty(A)) {
                        jSONObject.putOpt(com.microquation.linkedme.android.util.c.a.LKME_WF_INFO.a(), com.microquation.linkedme.android.util.a.a(A, "linkedme2017nble"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                com.microquation.linkedme.android.a.a().a(k.a(jSONObject, com.microquation.linkedme.android.a.a().i()));
            } else if (TextUtils.isEmpty(str)) {
                b.a("存储SI数据：" + str2);
                a.x(str2);
            } else {
                b.a("存储LC数据：" + str);
                a.a(str, h());
            }
        }
    }

    private String b(Location location) {
        return location != null ? location.getLongitude() + "," + location.getLatitude() + "," + location.getTime() : "";
    }

    private String c(boolean z) {
        String str = "";
        if (this.j && this.b != null) {
            this.e = this.b.b();
            if ((this.e == null || this.e.isEmpty()) && !z) {
                b.a("精确LC无法获取到数据，增加通过粗略LC获取数据");
                Message obtain = Message.obtain(this.i);
                obtain.what = 10000;
                this.i.sendMessage(obtain);
                this.h = true;
            }
        }
        if (this.c != null) {
            this.f = this.c.b();
        }
        if (this.j && this.e != null && !this.e.isEmpty()) {
            b.a("上传精确LC数据，关闭粗略LC获取数据");
            if (!z) {
                Message obtain2 = Message.obtain(this.i);
                obtain2.what = 10001;
                this.i.sendMessage(obtain2);
                if (this.h) {
                    obtain2 = Message.obtain(this.i);
                    obtain2.what = CommonInts.GT_ERROR;
                    this.i.sendMessage(obtain2);
                    this.h = false;
                }
            }
            str = a(this.e);
        } else if (!(this.f == null || this.f.isEmpty())) {
            str = a(this.f);
            b.a("上传粗略LC数据");
        }
        b.a("需要上传的数据为：" + str);
        return str;
    }

    private void i() {
        new Thread(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    String z = com.microquation.linkedme.android.a.a().h().z();
                    b.a("stationInfo == " + z);
                    if (TextUtils.isEmpty(z)) {
                        this.a.g();
                    } else {
                        this.a.a(null, z);
                    }
                } catch (Exception e) {
                    if (b.a()) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void a(Location location) {
        if (location != null && !h()) {
            a(b(location), null);
            b.a("1LC成功，上传数据并关闭LC！");
            f();
        }
    }

    public void a(boolean z) {
        this.k = z;
    }

    public boolean a(long j, float f, boolean z) {
        boolean z2 = false;
        if (this.c == null || z) {
            if (this.a.isProviderEnabled("network") && e.a(com.microquation.linkedme.android.a.a().i())) {
                if (z) {
                    d();
                }
                this.c = new c(a());
                if (this.a != null) {
                    StringBuilder append = new StringBuilder().append("粗略LC是否主线程===");
                    if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
                        z2 = true;
                    }
                    b.a(append.append(z2).toString());
                    this.a.requestLocationUpdates("network", j, f, this.c);
                }
                b.a("开启了粗略LC");
                return true;
            }
            b.a("无法粗略LC");
            if (!h()) {
                f();
                i();
            }
        }
        return false;
    }

    @MainThread
    public void b() {
        try {
            if (this.a == null) {
                this.a = (LocationManager) com.microquation.linkedme.android.a.a().i().getSystemService(CommonStrs.TYPE_LOCATION);
                Object obj = (this.j && g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.ACCESS_FINE_LOCATION") && this.a.isProviderEnabled("gps")) ? 1 : null;
                Object obj2 = (g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.ACCESS_COARSE_LOCATION") && this.a.isProviderEnabled("network")) ? 1 : null;
                if (obj2 == null) {
                    b.a("无权限获取LC信息，通过station获取。");
                } else {
                    this.k = true;
                }
                if (this.k) {
                    Location c = c();
                    if (c != null && e.a(c)) {
                        String b = b(c);
                        b.a("lastKnowLC 不为空，值为：" + b);
                        a(b, null);
                    }
                    b.a("开始LC！");
                    b a = b.a(com.microquation.linkedme.android.a.a().i());
                    a.S();
                    b(a.M());
                    if (obj != null) {
                        if (!(b(this.d.c(), this.d.d(), false) || a(this.d.c(), this.d.d(), false))) {
                            b.a("无法通过精确及粗略LC，LC失败！");
                            this.k = false;
                            f();
                        }
                    } else if (!a(this.d.c(), this.d.d(), false)) {
                        b.a("无法通过粗略LC，LC失败！");
                        this.k = false;
                        f();
                    }
                }
                b.a("start timer");
                if (h()) {
                    long a2 = this.d.a();
                    if (!this.k) {
                        a2 = 5;
                    }
                    this.m = this.l.scheduleAtFixedRate(new Runnable(this) {
                        final /* synthetic */ d a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            b.a("ononon...");
                            if (this.a.k) {
                                this.a.a(this.a.c(false), null);
                                return;
                            }
                            b.a("start station");
                            this.a.i();
                        }
                    }, a2, this.d.b(), TimeUnit.MILLISECONDS);
                } else if (!this.k) {
                    b.a("1start station");
                    i();
                } else if (obj != null) {
                    this.m = this.l.scheduleAtFixedRate(new Runnable(this) {
                        final /* synthetic */ d a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.b != null && this.a.b.a() == null) {
                                b.a("精确LC无法获取到数据，改用通过粗略LC获取数据，同时移除精确LC获取数据");
                                Message obtain = Message.obtain(this.a.i);
                                obtain.what = SystemMessageConstants.USER_CANCEL_CODE;
                                this.a.i.sendMessage(obtain);
                            }
                        }
                    }, this.d.a(), this.d.b(), TimeUnit.MILLISECONDS);
                }
            }
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
        }
    }

    public void b(boolean z) {
        this.g = z;
    }

    public boolean b(long j, float f, boolean z) {
        boolean z2 = false;
        if (this.b == null || z) {
            if (this.a.isProviderEnabled("gps")) {
                if (z) {
                    e();
                }
                this.b = new c(a());
                if (this.a != null) {
                    StringBuilder append = new StringBuilder().append("精确LC是否主线程===");
                    if (Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId()) {
                        z2 = true;
                    }
                    b.a(append.append(z2).toString());
                    this.a.requestLocationUpdates("gps", j, f, this.b);
                }
                b.a("开启了精确LC");
                return true;
            }
            b.a("精确LC开关未打开");
        }
        return false;
    }

    @MainThread
    public Location c() {
        if (!g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.ACCESS_FINE_LOCATION") && !g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.ACCESS_COARSE_LOCATION")) {
            return null;
        }
        if (this.a == null) {
            this.a = (LocationManager) com.microquation.linkedme.android.a.a().i().getSystemService(CommonStrs.TYPE_LOCATION);
        }
        Location lastKnownLocation = this.a.getLastKnownLocation("gps");
        Location lastKnownLocation2 = this.a.getLastKnownLocation("network");
        return (lastKnownLocation == null || lastKnownLocation2 == null) ? lastKnownLocation == null ? lastKnownLocation2 : lastKnownLocation : !e.a(lastKnownLocation, lastKnownLocation2) ? lastKnownLocation2 : lastKnownLocation;
    }

    public void d() {
        b.a("LM is null ?" + (this.a == null));
        try {
            if (this.a != null && this.c != null) {
                this.a.removeUpdates(this.c);
                this.c = null;
                b.a("移除了粗略LC");
            }
        } catch (Exception e) {
        }
    }

    public void e() {
        try {
            if (this.a != null && this.b != null) {
                this.a.removeUpdates(this.b);
                this.b = null;
                b.a("移除了精确LC");
            }
        } catch (Exception e) {
        }
    }

    @MainThread
    public void f() {
        if (this.k && this.m != null) {
            this.m.cancel(true);
        }
        if (h() && this.k) {
            String c = c(true);
            if (!TextUtils.isEmpty(c)) {
                a(c, null);
            }
        }
        Message obtain = Message.obtain(this.i);
        obtain.what = SystemMessageConstants.TAOBAO_CANCEL_CODE;
        this.i.sendMessage(obtain);
    }

    @MainThread
    public void g() {
        if (this.m != null) {
            this.m.cancel(true);
        }
        if (h() && this.k) {
            String c = c(true);
            if (!TextUtils.isEmpty(c)) {
                a(c, null);
            }
        }
        Message obtain = Message.obtain(this.i);
        obtain.what = SystemMessageConstants.TAOBAO_ERROR_CODE;
        this.i.sendMessage(obtain);
    }

    public boolean h() {
        return this.g;
    }
}
