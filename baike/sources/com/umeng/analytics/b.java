package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.pro.g;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.i;
import com.umeng.analytics.pro.i.d;
import com.umeng.analytics.pro.j;
import com.umeng.analytics.pro.m;
import com.umeng.analytics.pro.n;
import com.umeng.analytics.pro.o;
import com.umeng.analytics.pro.p;
import com.umeng.analytics.pro.r;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements n {
    private Context a;
    private r b;
    private j c;
    private p d;
    private o e;
    private h f;
    private boolean g;
    private volatile JSONObject h;
    private volatile JSONObject i;
    private volatile JSONObject j;
    private boolean k;

    private static class a {
        private static final b a = new b();
    }

    private b() {
        this.a = null;
        this.c = new j();
        this.d = new p();
        this.e = o.a();
        this.f = null;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = false;
        this.c.a((n) this);
    }

    public static b a() {
        return a.a;
    }

    public void b() {
        try {
            if (this.a != null) {
                if (!this.g) {
                    this.g = true;
                    h(this.a);
                }
                if (VERSION.SDK_INT > 13) {
                    synchronized (this) {
                        if (!this.k && (this.a instanceof Activity)) {
                            this.f = new h((Activity) this.a);
                            this.k = true;
                        }
                    }
                } else {
                    this.k = true;
                }
                this.a = this.a.getApplicationContext();
                if (VERSION.SDK_INT > 13) {
                    UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.t, CoreProtocol.getInstance(this.a), Long.valueOf(System.currentTimeMillis()));
                }
            }
        } catch (Throwable th) {
        }
    }

    private void h(android.content.Context r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.umeng.analytics.b.h(android.content.Context):void. bs: [B:1:0x0002, B:20:0x0050]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = this;
        if (r4 != 0) goto L_0x0008;
    L_0x0002:
        r0 = "unexpected null context in getNativeSuperProperties";	 Catch:{ Throwable -> 0x0065 }
        com.umeng.commonsdk.statistics.common.MLog.e(r0);	 Catch:{ Throwable -> 0x0065 }
    L_0x0007:
        return;	 Catch:{ Throwable -> 0x0065 }
    L_0x0008:
        r0 = r3.a;	 Catch:{ Throwable -> 0x0065 }
        if (r0 != 0) goto L_0x000e;	 Catch:{ Throwable -> 0x0065 }
    L_0x000c:
        r3.a = r4;	 Catch:{ Throwable -> 0x0065 }
    L_0x000e:
        r0 = com.umeng.commonsdk.statistics.internal.PreferenceWrapper.getDefault(r4);	 Catch:{ Throwable -> 0x0065 }
        r1 = "sp";	 Catch:{ Throwable -> 0x0065 }
        r2 = 0;	 Catch:{ Throwable -> 0x0065 }
        r1 = r0.getString(r1, r2);	 Catch:{ Throwable -> 0x0065 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x0065 }
        if (r2 != 0) goto L_0x0043;
    L_0x001f:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0067 }
        r2.<init>(r1);	 Catch:{ JSONException -> 0x0067 }
        r3.h = r2;	 Catch:{ JSONException -> 0x0067 }
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0067 }
        r2.<init>(r1);	 Catch:{ JSONException -> 0x0067 }
        r3.i = r2;	 Catch:{ JSONException -> 0x0067 }
        r1 = r3.h;	 Catch:{ JSONException -> 0x0067 }
        if (r1 != 0) goto L_0x0038;	 Catch:{ JSONException -> 0x0067 }
    L_0x0031:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0067 }
        r1.<init>();	 Catch:{ JSONException -> 0x0067 }
        r3.h = r1;	 Catch:{ JSONException -> 0x0067 }
    L_0x0038:
        r1 = r3.i;	 Catch:{ JSONException -> 0x0067 }
        if (r1 != 0) goto L_0x0043;	 Catch:{ JSONException -> 0x0067 }
    L_0x003c:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0067 }
        r1.<init>();	 Catch:{ JSONException -> 0x0067 }
        r3.i = r1;	 Catch:{ JSONException -> 0x0067 }
    L_0x0043:
        r1 = "prepp";	 Catch:{ Throwable -> 0x0065 }
        r2 = 0;	 Catch:{ Throwable -> 0x0065 }
        r0 = r0.getString(r1, r2);	 Catch:{ Throwable -> 0x0065 }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x0065 }
        if (r1 != 0) goto L_0x0007;
    L_0x0050:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0063 }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0063 }
        r3.j = r1;	 Catch:{ JSONException -> 0x0063 }
        r0 = r3.j;	 Catch:{ JSONException -> 0x0063 }
        if (r0 != 0) goto L_0x0007;	 Catch:{ JSONException -> 0x0063 }
    L_0x005b:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0063 }
        r0.<init>();	 Catch:{ JSONException -> 0x0063 }
        r3.j = r0;	 Catch:{ JSONException -> 0x0063 }
        goto L_0x0007;
    L_0x0063:
        r0 = move-exception;
        goto L_0x0007;
    L_0x0065:
        r0 = move-exception;
        goto L_0x0007;
    L_0x0067:
        r1 = move-exception;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.b.h(android.content.Context):void");
    }

    public JSONObject c() {
        return this.i;
    }

    public void d() {
        this.i = null;
    }

    void a(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.a(str);
            } catch (Throwable th) {
            }
        }
    }

    void b(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                this.d.b(str);
            } catch (Throwable th) {
            }
        }
    }

    public void a(r rVar) {
        this.b = rVar;
    }

    public void a(Context context, int i) {
        if (context == null) {
            MLog.e("unexpected null context in setVerticalType");
            return;
        }
        if (this.a == null) {
            this.a = context;
        }
        if (!(this.g && this.k)) {
            b();
        }
        AnalyticsConfig.a(this.a, i);
    }

    void a(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onResume");
            return;
        }
        if (this.a == null) {
            this.a = context;
        }
        try {
            if (!(this.g && this.k)) {
                b();
            }
            if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                this.d.a(context.getClass().getName());
            }
            e();
            i(this.a);
        } catch (Throwable th) {
            MLog.e("Exception occurred in Mobclick.onResume(). ", th);
        }
    }

    private void i(Context context) {
        try {
            Class.forName("com.umeng.visual.UMVisualAgent");
        } catch (ClassNotFoundException e) {
            if (VERSION.SDK_INT > 13) {
                UMWorkDispatch.sendEvent(context, com.umeng.analytics.pro.i.a.p, CoreProtocol.getInstance(context), Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    void b(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onPause");
            return;
        }
        if (this.a == null) {
            this.a = context;
        }
        try {
            if (!(this.g && this.k)) {
                b();
            }
            if (AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                this.d.b(context.getClass().getName());
            }
            f();
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e("Exception occurred in Mobclick.onRause(). ", th);
            }
        }
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        if (context != null) {
            try {
                if (this.a == null) {
                    this.a = context;
                }
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                    return;
                }
                return;
            }
        }
        if (!(this.g && this.k)) {
            b();
        }
        m.a(this.a).a(str, (Map) hashMap);
    }

    void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                MLog.e("unexpected null context in reportError");
                return;
            }
            if (this.a == null) {
                this.a = context;
            }
            try {
                if (!(this.g && this.k)) {
                    b();
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(com.umeng.analytics.pro.b.L, 2);
                jSONObject.put(com.umeng.analytics.pro.b.M, str);
                jSONObject.put("__ii", this.e.d());
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.j, CoreProtocol.getInstance(this.a), jSONObject);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        }
    }

    void a(Context context, Throwable th) {
        if (context != null && th != null) {
            if (this.a == null) {
                this.a = context;
            }
            try {
                if (!(this.g && this.k)) {
                    b();
                }
                a(this.a, DataHelper.convertExceptionToString(th));
            } catch (Throwable e) {
                if (MLog.DEBUG) {
                    MLog.e(e);
                }
            }
        }
    }

    public void e() {
        try {
            if (this.a != null) {
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.g, CoreProtocol.getInstance(this.a), Long.valueOf(System.currentTimeMillis()));
            }
            if (this.b != null) {
                this.b.a();
            }
        } catch (Throwable th) {
        }
    }

    public void f() {
        try {
            if (this.a != null) {
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.h, CoreProtocol.getInstance(this.a), Long.valueOf(System.currentTimeMillis()));
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.d, CoreProtocol.getInstance(this.a), null);
                UMWorkDispatch.sendEvent(this.a, 4099, CoreProtocol.getInstance(this.a), null);
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.i, CoreProtocol.getInstance(this.a), null);
            }
        } catch (Throwable th) {
        }
        if (this.b != null) {
            this.b.b();
        }
    }

    public void a(Context context, String str, String str2, long j, int i) {
        if (context != null) {
            try {
                this.a = context;
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                    return;
                }
                return;
            }
        }
        if (!(this.g && this.k)) {
            b();
        }
        m.a(this.a).a(str, str2, j, i);
    }

    public void b(Context context, String str) {
        if (context != null) {
            try {
                this.a = context;
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                    return;
                }
                return;
            }
        }
        if (!(this.g && this.k)) {
            b();
        }
        if (!TextUtils.isEmpty(str)) {
            Map hashMap = new HashMap();
            hashMap.put(com.umeng.analytics.pro.b.aq, str);
            b(this.a, com.umeng.analytics.pro.b.ap, hashMap, -1);
        } else if (MLog.DEBUG) {
            MLog.e("please check your link!");
        }
    }

    private void b(Context context, String str, Map<String, Object> map, long j) {
        if (context != null) {
            try {
                this.a = context;
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                    return;
                }
                return;
            }
        }
        if (!(this.g && this.k)) {
            b();
        }
        m.a(this.a).a(str, map, j);
    }

    void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (TextUtils.isEmpty(str)) {
                MLog.e("Event id is empty, please check.");
            } else if (Arrays.asList(com.umeng.analytics.pro.b.ar).contains(str)) {
                MLog.e("Event id uses reserved keywords, please use other event name. ");
            } else if (map.isEmpty()) {
                MLog.e("Map is empty, please check.");
            } else {
                for (Entry key : map.entrySet()) {
                    if (Arrays.asList(com.umeng.analytics.pro.b.ar).contains(key.getKey())) {
                        MLog.e("Map key uses reserved keywords[_$!link], please use other key.");
                        return;
                    }
                }
                b(context, str, map, j);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public void a(Context context, String str, Map<String, Object> map) {
        if (context != null) {
            try {
                if (this.a == null) {
                    this.a = context;
                }
            } catch (Throwable th) {
                return;
            }
        }
        if (!(this.g && this.k)) {
            b();
        }
        if (TextUtils.isEmpty(str)) {
            MLog.e("the eventName is empty! please check~");
            return;
        }
        String str2 = "";
        if (this.h == null) {
            this.h = new JSONObject();
        } else {
            str2 = this.h.toString();
        }
        UMWorkDispatch.sendEvent(this.a, 8194, CoreProtocol.getInstance(this.a), new d(str, map, str2, System.currentTimeMillis()));
    }

    void c(Context context) {
        try {
            if (this.a == null && context != null) {
                this.a = context;
            }
            if (this.f != null) {
                this.f.b();
            }
            if (this.d != null) {
                this.d.a();
            }
            if (this.a != null) {
                if (this.e != null) {
                    this.e.b(this.a, Long.valueOf(System.currentTimeMillis()));
                }
                i.a(this.a).a();
                p.a(this.a);
                h.a(this.a);
                PreferenceWrapper.getDefault(this.a).edit().commit();
            }
            UMWorkDispatch.Quit();
        } catch (Throwable th) {
        }
    }

    public void a(Throwable th) {
        try {
            if (this.d != null) {
                this.d.a();
            }
            if (this.f != null) {
                this.f.b();
            }
            if (this.a != null) {
                if (this.e != null) {
                    this.e.b(this.a, Long.valueOf(System.currentTimeMillis()));
                }
                if (th != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(com.umeng.analytics.pro.b.L, 1);
                    jSONObject.put(com.umeng.analytics.pro.b.M, DataHelper.convertExceptionToString(th));
                    g.a(this.a).a(this.e.d(), jSONObject.toString(), 1);
                }
                i.a(this.a).a();
                p.a(this.a);
                h.a(this.a);
                PreferenceWrapper.getDefault(this.a).edit().commit();
            }
            UMWorkDispatch.Quit();
        } catch (Throwable e) {
            if (MLog.DEBUG) {
                MLog.e("Exception in onAppCrash", e);
            }
        }
    }

    void a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.umeng.analytics.pro.b.H, str);
            jSONObject.put("uid", str2);
            UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.e, CoreProtocol.getInstance(this.a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    void g() {
        UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.f, CoreProtocol.getInstance(this.a), null);
    }

    void a(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    void a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    void b(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    void a(double d, double d2) {
        if (AnalyticsConfig.a == null) {
            AnalyticsConfig.a = new double[2];
        }
        AnalyticsConfig.a[0] = d;
        AnalyticsConfig.a[1] = d2;
    }

    void a(Context context, EScenarioType eScenarioType) {
        if (context == null) {
            MLog.e("unexpected null context in setScenarioType");
            return;
        }
        if (this.a == null) {
            this.a = context;
        }
        if (eScenarioType != null) {
            int toValue = eScenarioType.toValue();
            if (toValue == EScenarioType.E_DUM_NORMAL.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
            } else if (toValue == EScenarioType.E_DUM_GAME.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
            } else {
                AnalyticsConfig.FLAG_DPLUS = false;
            }
            a(this.a, toValue);
        }
        if (!this.g || !this.k) {
            b();
        }
    }

    void c(Context context, String str) {
        if (context == null) {
            MLog.e("unexpected null context in setSecret");
            return;
        }
        if (this.a == null) {
            this.a = context;
        }
        if (!(this.g && this.k)) {
            b();
        }
        AnalyticsConfig.a(this.a, str);
    }

    void a(long j) {
        AnalyticsConfig.kContinueSessionMillis = j;
    }

    public synchronized void a(Context context, String str, Object obj) {
        if (context == null) {
            try {
                MLog.e("unexpected null context in registerSuperProperty");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            if (TextUtils.isEmpty(str) || obj == null) {
                MLog.e("please check key or value, must not NULL!");
            } else {
                String subStr = HelperUtils.subStr(str, 128);
                if (Arrays.asList(com.umeng.analytics.pro.b.at).contains(subStr)) {
                    MLog.e("SuperProperty  key is invalid.  ");
                } else {
                    if (obj instanceof String) {
                        obj = HelperUtils.subStr(obj.toString(), 256);
                    }
                    a(subStr, obj);
                    UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.m, CoreProtocol.getInstance(this.a), this.h.toString());
                }
            }
        }
    }

    private void a(String str, Object obj) {
        int i = 0;
        try {
            if (this.h == null) {
                this.h = new JSONObject();
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray;
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    jSONArray = new JSONArray();
                    while (i < strArr.length) {
                        jSONArray.put(strArr[i]);
                        i++;
                    }
                    this.h.put(str, jSONArray);
                } else if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    jSONArray = new JSONArray();
                    while (i < jArr.length) {
                        jSONArray.put(jArr[i]);
                        i++;
                    }
                    this.h.put(str, jSONArray);
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    jSONArray = new JSONArray();
                    while (i < iArr.length) {
                        jSONArray.put(iArr[i]);
                        i++;
                    }
                    this.h.put(str, jSONArray);
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    jSONArray = new JSONArray();
                    while (i < fArr.length) {
                        jSONArray.put((double) fArr[i]);
                        i++;
                    }
                    this.h.put(str, jSONArray);
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    jSONArray = new JSONArray();
                    while (i < dArr.length) {
                        jSONArray.put(dArr[i]);
                        i++;
                    }
                    this.h.put(str, jSONArray);
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    jSONArray = new JSONArray();
                    while (i < sArr.length) {
                        jSONArray.put(sArr[i]);
                        i++;
                    }
                    this.h.put(str, jSONArray);
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                jSONArray = new JSONArray();
                while (i < list.size()) {
                    Object obj2 = list.get(i);
                    if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                        jSONArray.put(list.get(i));
                    }
                    i++;
                }
                this.h.put(str, jSONArray);
            } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                this.h.put(str, obj);
            }
        } catch (Throwable th) {
        }
    }

    public synchronized void a(Object obj) {
        if (obj != null) {
            try {
                String str = (String) obj;
                Editor edit = PreferenceWrapper.getDefault(this.a).edit();
                if (!(edit == null || TextUtils.isEmpty(str))) {
                    edit.putString("sp", this.h.toString()).commit();
                }
            } catch (Throwable th) {
            }
        }
    }

    public synchronized void d(Context context, String str) {
        if (context == null) {
            try {
                MLog.e("unexpected null context in unregisterSuperProperty");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            String subStr = HelperUtils.subStr(str, 128);
            if (this.h == null) {
                this.h = new JSONObject();
            }
            if (this.h.has(subStr)) {
                this.h.remove(subStr);
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.o, CoreProtocol.getInstance(this.a), subStr);
            }
        }
    }

    public synchronized void h() {
        try {
            if (this.h != null) {
                Editor edit = PreferenceWrapper.getDefault(this.a).edit();
                edit.putString("sp", this.h.toString());
                edit.commit();
            } else {
                this.h = new JSONObject();
            }
        } catch (Throwable th) {
        }
    }

    public synchronized Object e(Context context, String str) {
        Object obj = null;
        synchronized (this) {
            if (context == null) {
                try {
                    MLog.e("unexpected null context in getSuperProperty");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                if (this.a == null) {
                    this.a = context;
                }
                if (this.h != null) {
                    String subStr = HelperUtils.subStr(str, 128);
                    if (this.h.has(subStr)) {
                        obj = this.h.opt(subStr);
                    }
                } else {
                    this.h = new JSONObject();
                }
            }
        }
        return obj;
    }

    public synchronized String d(Context context) {
        String str = null;
        synchronized (this) {
            if (context == null) {
                try {
                    MLog.e("unexpected null context in getSuperProperties");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                if (this.a == null) {
                    this.a = context;
                }
                if (this.h != null) {
                    str = this.h.toString();
                } else {
                    this.h = new JSONObject();
                }
            }
        }
        return str;
    }

    public synchronized JSONObject i() {
        try {
            if (this.h == null) {
                this.h = new JSONObject();
            }
        } catch (Throwable th) {
        }
        return this.h;
    }

    public synchronized void e(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in clearSuperProperties");
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            this.h = new JSONObject();
            UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.n, CoreProtocol.getInstance(this.a), null);
        }
    }

    public synchronized void j() {
        try {
            Editor edit = PreferenceWrapper.getDefault(this.a).edit();
            edit.remove("sp");
            edit.commit();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void a(Context context, List<String> list) {
        if (context == null) {
            try {
                MLog.e("unexpected null context in setFirstLaunchEvent");
            } catch (Throwable th) {
                MLog.e(th);
            }
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            m.a(this.a).a((List) list);
        }
    }

    public synchronized void a(Context context, JSONObject jSONObject) {
        if (context == null) {
            MLog.e("unexpected null context in setPreProperties");
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            if (this.j == null) {
                this.j = new JSONObject();
            }
            Iterator keys = jSONObject.keys();
            if (keys != null) {
                while (keys.hasNext()) {
                    try {
                        String obj = keys.next().toString();
                        Object obj2 = jSONObject.get(obj);
                        if (b(obj, obj2)) {
                            this.j.put(obj, obj2);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            if (this.j.length() > 0) {
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.q, CoreProtocol.getInstance(this.a), this.j.toString());
            }
        }
    }

    public synchronized void f(Context context, String str) {
        if (context == null) {
            MLog.e("unexpected null context in clearPreProperties(context, " + str + ")");
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            if (this.j == null) {
                this.j = new JSONObject();
            }
            if (this.j.has(str)) {
                this.j.remove(str);
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.r, CoreProtocol.getInstance(this.a), this.j.toString());
            }
        }
    }

    public synchronized void f(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in clearPreProperties");
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            if (this.j.length() > 0) {
                UMWorkDispatch.sendEvent(this.a, com.umeng.analytics.pro.i.a.s, CoreProtocol.getInstance(this.a), null);
            }
            this.j = new JSONObject();
        }
    }

    public synchronized JSONObject g(Context context) {
        JSONObject jSONObject;
        if (context == null) {
            MLog.e("unexpected null context in clearPreProperties");
            jSONObject = null;
        } else {
            if (this.a == null) {
                this.a = context;
            }
            if (!(this.g && this.k)) {
                b();
            }
            if (this.j == null) {
                this.j = new JSONObject();
            }
            JSONObject jSONObject2 = new JSONObject();
            if (this.j.length() > 0) {
                try {
                    jSONObject = new JSONObject(this.j.toString());
                } catch (JSONException e) {
                    jSONObject = jSONObject2;
                }
            } else {
                jSONObject = jSONObject2;
            }
        }
        return jSONObject;
    }

    public synchronized void b(Object obj) {
        try {
            Editor edit = PreferenceWrapper.getDefault(this.a).edit();
            if (obj != null) {
                String str = (String) obj;
                if (!(edit == null || TextUtils.isEmpty(str))) {
                    edit.putString("prepp", str).commit();
                }
            } else if (edit != null) {
                edit.remove("prepp").commit();
            }
        } catch (Throwable th) {
        }
    }

    private boolean b(String str, Object obj) {
        int length;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            length = str.getBytes("UTF-8").length;
            if (length > 128) {
                return false;
            }
            if (Arrays.asList(com.umeng.analytics.pro.b.au).contains(str)) {
                return false;
            }
            if (obj instanceof String) {
                if (((String) obj).getBytes("UTF-8").length <= 256) {
                    return true;
                }
                return false;
            } else if (obj instanceof Integer) {
                return true;
            } else {
                if (obj instanceof Long) {
                    return true;
                }
                if (obj instanceof Double) {
                    return true;
                }
                if (obj instanceof Float) {
                    return true;
                }
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            length = 0;
        } catch (Throwable th) {
            return false;
        }
    }
}
