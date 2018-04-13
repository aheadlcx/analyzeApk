package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import com.umeng.analytics.pro.ap;
import com.umeng.analytics.pro.ar;
import com.umeng.analytics.pro.as;
import com.umeng.analytics.pro.at;
import com.umeng.analytics.pro.ay;
import com.umeng.analytics.pro.ba;
import com.umeng.analytics.pro.bd;
import com.umeng.analytics.pro.bf;
import com.umeng.analytics.pro.bt;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.bx;
import com.umeng.analytics.pro.by;
import com.umeng.analytics.pro.bz;
import com.umeng.analytics.pro.cb;
import com.umeng.analytics.pro.f;
import com.umeng.analytics.pro.m;
import com.umeng.analytics.pro.w;
import com.umeng.analytics.pro.x;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

public class b implements ay {
    private static final String j = "sp";
    private Context a = null;
    private bx b;
    private as c = new as();
    private bf d = new bf();
    private bd e = new bd();
    private at f = null;
    private ar g = null;
    private ap h = null;
    private m i = null;
    private boolean k = false;
    private boolean l = false;
    private JSONObject m = null;
    private boolean n = false;

    b() {
        this.c.a((ay) this);
    }

    private void g(Context context) {
        if (context != null) {
            try {
                if (VERSION.SDK_INT > 13 && !this.n && (context instanceof Activity)) {
                    this.h = new ap((Activity) context);
                    this.n = true;
                }
                if (!this.k) {
                    this.a = context.getApplicationContext();
                    this.f = new at(this.a);
                    this.g = ar.b(this.a);
                    this.k = true;
                    if (this.i == null) {
                        this.i = m.a(this.a);
                    }
                    if (!this.l) {
                        bz.b(new cb(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = r1;
                            }

                            public void a() {
                                this.a.i.a(new f(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void a(Object obj, boolean z) {
                                        this.a.a.l = true;
                                    }
                                });
                            }
                        });
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    void a(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.a(str);
            } catch (Exception e) {
            }
        }
    }

    void b(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.b(str);
            } catch (Exception e) {
            }
        }
    }

    public void a(bx bxVar) {
        this.b = bxVar;
    }

    public void a(Context context, int i) {
        AnalyticsConfig.a(context, i);
    }

    void a(final Context context) {
        if (context == null) {
            by.e("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.a(context.getClass().getName());
        }
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            bz.a(new cb(this) {
                final /* synthetic */ b b;

                public void a() {
                    this.b.h(context.getApplicationContext());
                }
            });
        } catch (Throwable e) {
            by.e("Exception occurred in Mobclick.onResume(). ", e);
        }
    }

    void b(final Context context) {
        if (context == null) {
            by.e("unexpected null context in onPause");
            return;
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            this.d.b(context.getClass().getName());
        }
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            bz.a(new cb(this) {
                final /* synthetic */ b b;

                public void a() {
                    this.b.i(context.getApplicationContext());
                    this.b.i.f();
                }
            });
        } catch (Throwable e) {
            if (by.a) {
                by.e("Exception occurred in Mobclick.onRause(). ", e);
            }
        }
    }

    public bd a() {
        return this.e;
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            this.f.b(str, hashMap);
        } catch (Throwable e) {
            if (by.a) {
                by.e(e);
            }
        }
    }

    void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                by.e("unexpected null context in reportError");
                return;
            }
            try {
                if (!(this.k && this.n)) {
                    g(context);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(x.aH, 2);
                jSONObject.put(x.aI, str);
                w.a(this.a).a(bd.a(), jSONObject.toString(), 2);
            } catch (Throwable e) {
                if (by.a) {
                    by.e(e);
                }
            }
        }
    }

    void a(Context context, Throwable th) {
        if (context != null && th != null) {
            try {
                a(context, bt.a(th));
            } catch (Throwable e) {
                if (by.a) {
                    by.e(e);
                }
            }
        }
    }

    private void h(Context context) {
        this.e.c(context);
        if (this.b != null) {
            this.b.a();
        }
    }

    private void i(Context context) {
        this.e.d(context);
        bf.a(context);
        ap.b(context);
        this.g.a(this.a).a(context);
        if (this.b != null) {
            this.b.b();
        }
    }

    void c(Context context) {
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            this.g.a();
        } catch (Throwable th) {
        }
    }

    public void a(Context context, List<String> list, int i, String str) {
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            this.f.a((List) list, i, str);
        } catch (Throwable e) {
            if (by.a) {
                by.e(e);
            }
        }
    }

    public void a(Context context, String str, String str2, long j, int i) {
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            this.f.a(str, str2, j, i);
        } catch (Throwable e) {
            if (by.a) {
                by.e(e);
            }
        }
    }

    void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            this.f.a(str, (Map) map, j);
        } catch (Throwable e) {
            if (by.a) {
                by.e(e);
            }
        }
    }

    public void a(Context context, String str, Map<String, Object> map) {
    }

    void d(Context context) {
        try {
            this.h.a(context);
            this.d.a();
            i(context);
            ba.a(context).edit().commit();
            this.i.d();
            bz.a();
        } catch (Exception e) {
            if (by.a) {
                e.printStackTrace();
            }
        }
    }

    public void a(Throwable th) {
        try {
            this.d.a();
            if (this.a != null) {
                if (!(th == null || this.g == null)) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(x.aH, 1);
                    jSONObject.put(x.aI, bt.a(th));
                    w.a(this.a).a(bd.a(), jSONObject.toString(), 1);
                }
                this.i.e();
                this.h.a(this.a);
                i(this.a);
                ba.a(this.a).edit().commit();
            }
            bz.a();
        } catch (Throwable e) {
            if (by.a) {
                by.e("Exception in onAppCrash", e);
            }
        }
    }

    void a(final String str, final String str2) {
        try {
            bz.a(new cb(this) {
                final /* synthetic */ b c;

                public void a() {
                    String[] a = c.a(this.c.a);
                    if (a == null || !str.equals(a[0]) || !str2.equals(a[1])) {
                        this.c.g.a(this.c.a).a(this.c.a);
                        boolean e = this.c.a().e(this.c.a);
                        ar.b(this.c.a).b();
                        if (e) {
                            this.c.a().f(this.c.a);
                        }
                        c.a(this.c.a, str, str2);
                    }
                }
            });
        } catch (Throwable e) {
            if (by.a) {
                by.e(" Excepthon  in  onProfileSignIn", e);
            }
        }
    }

    void b() {
        try {
            bz.a(new cb(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void a() {
                    String[] a = c.a(this.a.a);
                    if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                        this.a.g.a(this.a.a).a(this.a.a);
                        boolean e = this.a.a().e(this.a.a);
                        ar.b(this.a.a).b();
                        if (e) {
                            this.a.a().f(this.a.a);
                        }
                        c.b(this.a.a);
                    }
                }
            });
        } catch (Throwable e) {
            if (by.a) {
                by.e(" Excepthon  in  onProfileSignOff", e);
            }
        }
    }

    void a(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    void a(GL10 gl10) {
        String[] a = bv.a(gl10);
        if (a.length == 2) {
            AnalyticsConfig.GPU_VENDER = a[0];
            AnalyticsConfig.GPU_RENDERER = a[1];
        }
    }

    void b(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    void c(boolean z) {
        a.d = z;
    }

    void d(boolean z) {
        by.a = z;
    }

    void e(boolean z) {
        AnalyticsConfig.a(z);
    }

    void a(double d, double d2) {
        if (AnalyticsConfig.a == null) {
            AnalyticsConfig.a = new double[2];
        }
        AnalyticsConfig.a[0] = d;
        AnalyticsConfig.a[1] = d2;
    }

    void a(long j) {
        AnalyticsConfig.sLatentWindow = ((int) j) * 1000;
    }

    void a(Context context, MobclickAgent$EScenarioType mobclickAgent$EScenarioType) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        if (mobclickAgent$EScenarioType != null) {
            a(context, mobclickAgent$EScenarioType.toValue());
        }
    }

    void b(Context context, String str) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        AnalyticsConfig.b(context, str);
    }

    void a(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig.mContext != null) {
            this.a = uMAnalyticsConfig.mContext.getApplicationContext();
        }
        if (TextUtils.isEmpty(uMAnalyticsConfig.mAppkey)) {
            by.e("the appkey is null!");
            return;
        }
        AnalyticsConfig.a(uMAnalyticsConfig.mContext, uMAnalyticsConfig.mAppkey);
        if (!TextUtils.isEmpty(uMAnalyticsConfig.mChannelId)) {
            AnalyticsConfig.a(uMAnalyticsConfig.mChannelId);
        }
        AnalyticsConfig.CATCH_EXCEPTION = uMAnalyticsConfig.mIsCrashEnable;
        a(this.a, uMAnalyticsConfig.mType);
    }

    void b(long j) {
        AnalyticsConfig.kContinueSessionMillis = j;
    }

    public void a(Context context, String str, Object obj) {
    }

    public void c(Context context, String str) {
    }

    public Object d(Context context, String str) {
        return null;
    }

    public String e(Context context) {
        return null;
    }

    private JSONObject j(Context context) {
        try {
            Object string = ba.a(context).getString(j, null);
            if (!TextUtils.isEmpty(string)) {
                return new JSONObject(string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void f(Context context) {
    }

    public void a(Context context, List<String> list) {
        try {
            if (!(this.k && this.n)) {
                g(context);
            }
            this.f.a(context, (List) list);
        } catch (Throwable e) {
            by.e(e);
        }
    }
}
