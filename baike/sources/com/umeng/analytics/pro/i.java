package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.common.ReportPolicy.DebugPolicy;
import com.umeng.commonsdk.statistics.common.ReportPolicy.DefconPolicy;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportAtLaunch;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportByInterval;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportDaily;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportRealtime;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportStrategy;
import com.umeng.commonsdk.statistics.common.ReportPolicy.ReportWifiOnly;
import com.umeng.commonsdk.statistics.common.ReportPolicy.SmartPolicy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.utils.ACache;

public class i {
    private static Context a = null;
    private static boolean r = false;
    private static boolean s = false;
    private ABTest b;
    private ImLatent c;
    private c d;
    private SharedPreferences e;
    private String f;
    private String g;
    private String h;
    private String i;
    private long j;
    private int k;
    private JSONArray l;
    private final int m;
    private int n;
    private int o;
    private long p;
    private final long q;

    public static class a {
        public static final int a = 4097;
        public static final int b = 4098;
        public static final int c = 4099;
        public static final int d = 4100;
        public static final int e = 4101;
        public static final int f = 4102;
        public static final int g = 4103;
        public static final int h = 4104;
        public static final int i = 4105;
        public static final int j = 4106;
        public static final int k = 8193;
        public static final int l = 8194;
        public static final int m = 8195;
        public static final int n = 8196;
        public static final int o = 8197;
        public static final int p = 8198;
        public static final int q = 8199;
        public static final int r = 8200;
        public static final int s = 8201;
        public static final int t = 8202;
    }

    private static class b {
        private static final i a = new i();
    }

    public class c {
        final /* synthetic */ i a;
        private ReportStrategy b = null;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private int f = -1;

        public c(i iVar) {
            this.a = iVar;
        }

        public void a() {
            try {
                int[] a = a(-1, -1);
                this.c = a[0];
                this.d = a[1];
            } catch (Throwable th) {
            }
        }

        public int[] a(int i, int i2) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(i.a, "report_policy", "-1")).intValue();
            int intValue2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(i.a, "report_interval", "-1")).intValue();
            if (intValue == -1 || !ReportPolicy.isValid(intValue)) {
                return new int[]{i, i2};
            }
            if (intValue2 == -1 || intValue2 < 90 || intValue2 > ACache.TIME_DAY) {
                intValue2 = 90;
            }
            return new int[]{intValue, intValue2 * 1000};
        }

        public int a(int i) {
            int intValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(i.a, "test_report_interval", "-1")).intValue();
            return (intValue == -1 || intValue < 90 || intValue > ACache.TIME_DAY) ? i : intValue * 1000;
        }

        protected void b() {
            int i = 1;
            int i2 = 0;
            Defcon service = Defcon.getService(i.a);
            if (service.isOpen()) {
                ReportStrategy reportStrategy;
                if (!((this.b instanceof DefconPolicy) && this.b.isValid())) {
                    i = 0;
                }
                if (i != 0) {
                    reportStrategy = this.b;
                } else {
                    reportStrategy = new DefconPolicy(StatTracer.getInstance(i.a), service);
                }
                this.b = reportStrategy;
            } else {
                int i3 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(i.a, "integrated_test", "-1")).intValue() == 1 ? 1 : 0;
                if (MLog.DEBUG && i3 != 0) {
                    this.b = new DebugPolicy(StatTracer.getInstance(i.a));
                } else if (this.a.b.isInTest() && "RPT".equals(this.a.b.getTestName())) {
                    if (this.a.b.getTestPolicy() == 6) {
                        if (Integer.valueOf(UMEnvelopeBuild.imprintProperty(i.a, "test_report_interval", "-1")).intValue() == -1) {
                            i = 0;
                        }
                        if (i != 0) {
                            i2 = a(90000);
                        } else if (this.d > 0) {
                            i2 = this.d;
                        } else {
                            i2 = this.f;
                        }
                    }
                    this.b = b(this.a.b.getTestPolicy(), i2);
                } else {
                    i = this.e;
                    i3 = this.f;
                    if (this.c != -1) {
                        i = this.c;
                        i3 = this.d;
                    }
                    this.b = b(i, i3);
                }
            }
            MLog.d("Report policy : " + this.b.getClass().getSimpleName());
        }

        public ReportStrategy c() {
            b();
            return this.b;
        }

        private ReportStrategy b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.b instanceof ReportRealtime ? this.b : new ReportRealtime();
                case 1:
                    return this.b instanceof ReportAtLaunch ? this.b : new ReportAtLaunch();
                case 4:
                    if (this.b instanceof ReportDaily) {
                        return this.b;
                    }
                    return new ReportDaily(StatTracer.getInstance(i.a));
                case 5:
                    if (this.b instanceof ReportWifiOnly) {
                        return this.b;
                    }
                    return new ReportWifiOnly(i.a);
                case 6:
                    if (!(this.b instanceof ReportByInterval)) {
                        return new ReportByInterval(StatTracer.getInstance(i.a), (long) i2);
                    }
                    ReportStrategy reportStrategy = this.b;
                    ((ReportByInterval) reportStrategy).setReportInterval((long) i2);
                    return reportStrategy;
                case 8:
                    if (this.b instanceof SmartPolicy) {
                        return this.b;
                    }
                    return new SmartPolicy(StatTracer.getInstance(i.a));
                default:
                    if (this.b instanceof ReportAtLaunch) {
                        return this.b;
                    }
                    return new ReportAtLaunch();
            }
        }
    }

    public static class d {
        private Map<String, Object> a = null;
        private String b = null;
        private String c = null;
        private long d = 0;

        private d() {
        }

        public d(String str, Map<String, Object> map, String str2, long j) {
            this.a = map;
            this.b = str;
            this.d = j;
            this.c = str2;
        }

        public Map<String, Object> a() {
            return this.a;
        }

        public String b() {
            return this.c;
        }

        public String c() {
            return this.b;
        }

        public long d() {
            return this.d;
        }
    }

    private i() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 10;
        this.l = new JSONArray();
        this.m = 5000;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 28800000;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            this.p = sharedPreferences.getLong("thtstart", 0);
            this.n = sharedPreferences.getInt("gkvc", 0);
            this.o = sharedPreferences.getInt("ekvc", 0);
            this.d = new c(this);
            this.b = ABTest.getService(a);
            this.c = ImLatent.getService(a, StatTracer.getInstance(a));
        } catch (Throwable th) {
        }
    }

    public static i a(Context context) {
        if (a == null && context != null) {
            a = context.getApplicationContext();
        }
        return b.a;
    }

    public void a(Object obj, int i) {
        switch (i) {
            case 4097:
                if (obj != null) {
                    try {
                        d(obj);
                    } catch (Throwable th) {
                        return;
                    }
                }
                if (!"-1".equals(((JSONObject) obj).optString("__i"))) {
                    a(false);
                    return;
                }
                return;
            case 4098:
                if (obj != null) {
                    try {
                        d(obj);
                    } catch (Throwable th2) {
                        return;
                    }
                }
                if (!"-1".equals(((JSONObject) obj).optString("__i"))) {
                    a(false);
                    return;
                }
                return;
            case 4099:
                p.a(a);
                return;
            case a.d /*4100*/:
                h.a(a);
                return;
            case a.e /*4101*/:
                e(obj);
                return;
            case a.f /*4102*/:
                q();
                return;
            case a.g /*4103*/:
                o.a().a(a, obj);
                return;
            case a.h /*4104*/:
                o.a().b(a, obj);
                return;
            case a.i /*4105*/:
                a();
                return;
            case a.j /*4106*/:
                f(obj);
                return;
            case a.k /*8193*/:
                a(obj, 1, false);
                return;
            case 8194:
                m.a(a).a(obj);
                return;
            case a.m /*8195*/:
                com.umeng.analytics.b.a().a(obj);
                return;
            case a.n /*8196*/:
                com.umeng.analytics.b.a().j();
                return;
            case a.o /*8197*/:
                com.umeng.analytics.b.a().h();
                return;
            case a.p /*8198*/:
                if (!TextUtils.isEmpty(o.a().c())) {
                    g();
                    return;
                }
                return;
            case a.q /*8199*/:
            case a.r /*8200*/:
                com.umeng.analytics.b.a().b(obj);
                return;
            case a.s /*8201*/:
                com.umeng.analytics.b.a().b(null);
                return;
            case a.t /*8202*/:
                f();
                return;
            default:
                return;
        }
    }

    private void f() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("loadNativeData", new Class[]{Context.class}).invoke(null, new Object[]{a});
        } catch (Exception e) {
        }
    }

    private void g() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("processCommond", new Class[]{Context.class, String.class}).invoke(null, new Object[]{a, AnalyticsConfig.getAppkey(a)});
        } catch (Exception e) {
        }
    }

    public void a(Object obj, int i, boolean z) {
        int i2 = 0;
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                JSONArray jSONArray;
                String optString = jSONObject.optString("__ii");
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                if (i == 0) {
                    long j = sharedPreferences.getLong("dstk_last_time", 0);
                    int i3 = sharedPreferences.getInt("dstk_cnt", 0);
                    if (a(j, i3)) {
                        sharedPreferences.edit().putLong("dstk_last_time", System.currentTimeMillis()).putInt("dstk_cnt", i3 == 5000 ? 0 : i3 + 1).commit();
                    } else {
                        return;
                    }
                }
                Object string = sharedPreferences.getString("n_sess_dp", "");
                if (TextUtils.isEmpty(string)) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string);
                }
                JSONObject i4 = com.umeng.analytics.b.a().i();
                if ("-1".equals(optString)) {
                    jSONObject.put("n_sess_dp_type", i);
                    if (!jSONObject.has(b.ab) && i4.length() > 0) {
                        jSONObject.put(b.ab, i4);
                    }
                    jSONArray.put(jSONObject);
                    sharedPreferences.edit().putString("n_sess_dp", jSONArray.toString()).commit();
                    return;
                }
                if (i != 3 && jSONArray.length() > 0) {
                    while (i2 < jSONArray.length()) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                        if (jSONObject2 != null && jSONObject2.length() > 0) {
                            int i5 = jSONObject2.getInt("n_sess_dp_type");
                            if (!jSONObject2.has(b.ab) && i4.length() > 0) {
                                jSONObject2.put(b.ab, i4);
                            }
                            switch (i5) {
                                case 0:
                                    jSONObject2.put("_$!ts", System.currentTimeMillis());
                                    break;
                                case 1:
                                    jSONObject2.put("_$!ts", System.currentTimeMillis());
                                    break;
                            }
                            jSONObject2.put("__ii", optString);
                            jSONObject2.remove("n_sess_dp_type");
                            g.a(a).a(jSONObject2, i5);
                        }
                        i2++;
                    }
                    com.umeng.analytics.b.a().d();
                    sharedPreferences.edit().remove("n_sess_dp").commit();
                }
                g.a(a).a(jSONObject, i);
                if (z) {
                    c(true);
                } else if (UMEnvelopeBuild.isReadyBuild(a, UMBusinessType.U_DPLUS)) {
                    Defcon service = Defcon.getService(a);
                    if (!service.isOpen()) {
                        c(true);
                    } else if (new DefconPolicy(StatTracer.getInstance(a), service).shouldSendMessage(false)) {
                        c(true);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public void a(boolean z) {
        if (e(z) && UMEnvelopeBuild.isReadyBuild(a, UMBusinessType.U_APP)) {
            c(false);
        }
    }

    private void c(boolean z) {
        JSONObject a;
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            a = a(UMEnvelopeBuild.maxDataSpace(a));
        } else if (AnalyticsConfig.FLAG_DPLUS) {
            a = b(UMEnvelopeBuild.maxDataSpace(a));
        } else {
            a = jSONObject;
        }
        if (a != null && a.length() >= 1) {
            jSONObject = (JSONObject) a.opt("header");
            a = (JSONObject) a.opt("content");
            if (a != null && jSONObject != null && a != null) {
                Object buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, jSONObject, a);
                if (buildEnvelopeWithExtHeader != null) {
                    b((JSONObject) buildEnvelopeWithExtHeader);
                    a(buildEnvelopeWithExtHeader);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject a(long r8) {
        /*
        r7 = this;
        r6 = 0;
        r5 = 3;
        r0 = 0;
        r1 = com.umeng.analytics.pro.o.a();
        r2 = a;
        r1 = r1.c(r2);
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r1 = r7.b(r6);
        r2 = com.umeng.analytics.pro.l.a();
        r3 = a;
        r2 = r2.a(r3);
        r3 = r1.length();
        if (r3 <= 0) goto L_0x0013;
    L_0x0028:
        r3 = r1.length();
        r4 = 1;
        if (r3 != r4) goto L_0x007e;
    L_0x002f:
        r3 = "active_user";
        r3 = r1.optJSONObject(r3);
        if (r3 == 0) goto L_0x0039;
    L_0x0037:
        if (r2 != r5) goto L_0x0013;
    L_0x0039:
        r3 = "userlevel";
        r3 = r1.optString(r3);
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x0047;
    L_0x0045:
        if (r2 != r5) goto L_0x0013;
    L_0x0047:
        r3 = r7.d(r6);
        r0 = new org.json.JSONObject;
        r0.<init>();
        r4 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x00ab }
        r4.<init>();	 Catch:{ Throwable -> 0x00ab }
        if (r2 != r5) goto L_0x009d;
    L_0x0057:
        r1 = "analytics";
        r2 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x00ab }
        r2.<init>();	 Catch:{ Throwable -> 0x00ab }
        r4.put(r1, r2);	 Catch:{ Throwable -> 0x00ab }
    L_0x0061:
        if (r3 == 0) goto L_0x006e;
    L_0x0063:
        r1 = r3.length();	 Catch:{ Throwable -> 0x00ab }
        if (r1 <= 0) goto L_0x006e;
    L_0x0069:
        r1 = "header";
        r0.put(r1, r3);	 Catch:{ Throwable -> 0x00ab }
    L_0x006e:
        r1 = r4.length();	 Catch:{ Throwable -> 0x00ab }
        if (r1 <= 0) goto L_0x0079;
    L_0x0074:
        r1 = "content";
        r0.put(r1, r4);	 Catch:{ Throwable -> 0x00ab }
    L_0x0079:
        r0 = r7.a(r0, r8);	 Catch:{ Throwable -> 0x00ab }
        goto L_0x0013;
    L_0x007e:
        r3 = r1.length();
        r4 = 2;
        if (r3 != r4) goto L_0x0047;
    L_0x0085:
        r3 = "active_user";
        r3 = r1.optJSONObject(r3);
        if (r3 == 0) goto L_0x0047;
    L_0x008d:
        r3 = "userlevel";
        r3 = r1.optString(r3);
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x0047;
    L_0x0099:
        if (r2 == r5) goto L_0x0047;
    L_0x009b:
        goto L_0x0013;
    L_0x009d:
        if (r1 == 0) goto L_0x0061;
    L_0x009f:
        r2 = r1.length();	 Catch:{ Throwable -> 0x00ab }
        if (r2 <= 0) goto L_0x0061;
    L_0x00a5:
        r2 = "analytics";
        r4.put(r2, r1);	 Catch:{ Throwable -> 0x00ab }
        goto L_0x0061;
    L_0x00ab:
        r1 = move-exception;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.i.a(long):org.json.JSONObject");
    }

    public JSONObject b(long j) {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            return null;
        }
        if (TextUtils.isEmpty(o.a().c(a))) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        if (AnalyticsConfig.FLAG_DPLUS) {
            jSONObject = g.a(a).c();
            l.a().a(jSONObject, a);
            Defcon service = Defcon.getService(a);
            if (service.isOpen() && !new DefconPolicy(StatTracer.getInstance(a), service).shouldSendMessage(false)) {
                jSONObject = new JSONObject();
            }
        }
        if (jSONObject.length() <= 0) {
            return null;
        }
        JSONObject d = d(true);
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject != null && jSONObject.length() > 0) {
                jSONObject3.put("dplus", jSONObject);
            }
            if (d != null && d.length() > 0) {
                jSONObject2.put("header", d);
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put("content", jSONObject3);
            }
            return a(jSONObject2, j);
        } catch (Throwable th) {
            return jSONObject2;
        }
    }

    private JSONObject a(JSONObject jSONObject, long j) {
        try {
            if (k.a(jSONObject) > j) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("header");
                jSONObject2.put(b.an, k.a(jSONObject));
                jSONObject.put("header", jSONObject2);
                jSONObject = k.a(a, j, jSONObject);
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private JSONObject d(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
                jSONObject.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                jSONObject.put("wrapper_type", AnalyticsConfig.mWrapperType);
            }
            int verticalType = AnalyticsConfig.getVerticalType(a);
            if (verticalType == EScenarioType.E_DUM_NORMAL.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
                verticalType = EScenarioType.E_UM_NORMAL.toValue();
            } else if (verticalType == EScenarioType.E_DUM_GAME.toValue()) {
                AnalyticsConfig.FLAG_DPLUS = true;
                verticalType = EScenarioType.E_UM_GAME.toValue();
            } else {
                AnalyticsConfig.FLAG_DPLUS = false;
            }
            jSONObject.put(b.i, verticalType);
            jSONObject.put("sdk_version", q.a);
            CharSequence MD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(a));
            if (!TextUtils.isEmpty(MD5)) {
                jSONObject.put("secret", MD5);
            }
            MD5 = UMEnvelopeBuild.imprintProperty(a, "pr_ve", null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            Object string;
            String format;
            if (z) {
                jSONObject.put(b.l, j());
                jSONObject.put(b.m, k());
                if (sharedPreferences != null) {
                    string = sharedPreferences.getString("dp_vers_name", "");
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put("app_version", string);
                        jSONObject.put("version_code", sharedPreferences.getInt("dp_vers_code", 0));
                        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (TextUtils.isEmpty(MD5)) {
                            jSONObject.put(b.l, sharedPreferences.getString("dp_vers_pre_version", "0"));
                            jSONObject.put(b.m, sharedPreferences.getString("dp_vers_date", format));
                        }
                        sharedPreferences.edit().putString("dp_pre_version", string).putString("dp_cur_version", DeviceConfig.getAppVersionName(a)).putString("dp_pre_date", format).remove("dp_vers_name").remove("dp_vers_code").remove("dp_vers_date").remove("dp_vers_pre_version").commit();
                    }
                }
            } else {
                jSONObject.put(b.l, h());
                jSONObject.put(b.m, i());
                if (sharedPreferences != null) {
                    string = sharedPreferences.getString("vers_name", "");
                    if (!TextUtils.isEmpty(string)) {
                        jSONObject.put("app_version", string);
                        jSONObject.put("version_code", sharedPreferences.getInt("vers_code", 0));
                        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (TextUtils.isEmpty(MD5)) {
                            jSONObject.put(b.l, sharedPreferences.getString("vers_pre_version", "0"));
                            jSONObject.put(b.m, sharedPreferences.getString("vers_date", format));
                        }
                        sharedPreferences.edit().putString("pre_version", string).putString("cur_version", DeviceConfig.getAppVersionName(a)).putString("pre_date", format).remove("vers_name").remove("vers_code").remove("vers_date").remove("vers_pre_version").commit();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2;
            jSONObject = g.a(a).a(z);
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
            if (sharedPreferences != null) {
                CharSequence string = sharedPreferences.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            if (p()) {
                this.j = c();
                if (this.j != 0) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("ts", this.j);
                    jSONObject.put(b.D, jSONObject2);
                    sharedPreferences.edit().putLong("ana_is_f", 0).commit();
                }
            }
            String[] a = com.umeng.analytics.c.a(a);
            if (!(a == null || TextUtils.isEmpty(a[0]) || TextUtils.isEmpty(a[1]))) {
                jSONObject2 = new JSONObject();
                jSONObject2.put(b.H, a[0]);
                jSONObject2.put(b.I, a[1]);
                if (jSONObject2.length() > 0) {
                    jSONObject.put(b.G, jSONObject2);
                }
            }
            if (ABTest.getService(a).isInTest()) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(ABTest.getService(a).getTestName(), ABTest.getService(a).getGroupInfo());
                jSONObject.put("group_info", jSONObject3);
            }
            l.a().b(jSONObject, a);
            if (jSONObject.length() > 0) {
                new JSONObject().put("analytics", jSONObject);
            }
        } catch (Throwable th) {
        }
        return jSONObject;
    }

    private String h() {
        String str;
        String str2 = null;
        try {
            CharSequence imprintProperty = UMEnvelopeBuild.imprintProperty(a, "pr_ve", null);
            CharSequence charSequence;
            try {
                if (!TextUtils.isEmpty(imprintProperty)) {
                    charSequence = imprintProperty;
                } else if (!TextUtils.isEmpty(this.f)) {
                    return this.f;
                } else {
                    if (this.e == null) {
                        this.e = PreferenceWrapper.getDefault(a);
                    }
                    CharSequence string = this.e.getString("pre_version", "");
                    String appVersionName = DeviceConfig.getAppVersionName(a);
                    if (TextUtils.isEmpty(string)) {
                        this.e.edit().putString("pre_version", "0").putString("cur_version", appVersionName).commit();
                        str = "0";
                    } else {
                        str = this.e.getString("cur_version", "");
                        if (appVersionName.equals(str)) {
                            charSequence = string;
                        } else {
                            this.e.edit().putString("pre_version", str).putString("cur_version", appVersionName).commit();
                        }
                    }
                }
            } catch (Throwable th) {
                charSequence = imprintProperty;
            }
        } catch (Throwable th2) {
            str = str2;
        }
        this.f = str;
        return str;
    }

    private String i() {
        String format;
        String str = null;
        try {
            CharSequence imprintProperty = UMEnvelopeBuild.imprintProperty(a, "ud_da", null);
            try {
                if (!TextUtils.isEmpty(imprintProperty)) {
                    CharSequence charSequence = imprintProperty;
                } else if (!TextUtils.isEmpty(this.g)) {
                    return this.g;
                } else {
                    if (this.e == null) {
                        this.e = PreferenceWrapper.getDefault(a);
                    }
                    String string = this.e.getString("pre_date", "");
                    if (TextUtils.isEmpty(string)) {
                        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        this.e.edit().putString("pre_date", format).commit();
                    } else {
                        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (string.equals(format)) {
                            format = string;
                        } else {
                            this.e.edit().putString("pre_date", format).commit();
                        }
                    }
                }
            } catch (Throwable th) {
                charSequence = imprintProperty;
            }
        } catch (Throwable th2) {
            format = str;
        }
        this.g = format;
        return format;
    }

    private String j() {
        String str;
        String str2 = null;
        try {
            CharSequence imprintProperty = UMEnvelopeBuild.imprintProperty(a, "pr_ve", null);
            CharSequence charSequence;
            try {
                if (!TextUtils.isEmpty(imprintProperty)) {
                    charSequence = imprintProperty;
                } else if (!TextUtils.isEmpty(this.h)) {
                    return this.h;
                } else {
                    if (this.e == null) {
                        this.e = PreferenceWrapper.getDefault(a);
                    }
                    CharSequence string = this.e.getString("dp_pre_version", "");
                    String appVersionName = DeviceConfig.getAppVersionName(a);
                    if (TextUtils.isEmpty(string)) {
                        this.e.edit().putString("dp_pre_version", "0").putString("dp_cur_version", appVersionName).commit();
                        str = "0";
                    } else {
                        str = this.e.getString("dp_cur_version", "");
                        if (appVersionName.equals(str)) {
                            charSequence = string;
                        } else {
                            this.e.edit().putString("dp_pre_version", str).putString("dp_cur_version", appVersionName).commit();
                        }
                    }
                }
            } catch (Throwable th) {
                charSequence = imprintProperty;
            }
        } catch (Throwable th2) {
            str = str2;
        }
        this.h = str;
        return str;
    }

    private String k() {
        String format;
        String str = null;
        try {
            CharSequence imprintProperty = UMEnvelopeBuild.imprintProperty(a, "ud_da", null);
            try {
                if (!TextUtils.isEmpty(imprintProperty)) {
                    CharSequence charSequence = imprintProperty;
                } else if (!TextUtils.isEmpty(this.i)) {
                    return this.i;
                } else {
                    if (this.e == null) {
                        this.e = PreferenceWrapper.getDefault(a);
                    }
                    String string = this.e.getString("dp_pre_date", "");
                    if (TextUtils.isEmpty(string)) {
                        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        this.e.edit().putString("dp_pre_date", format).commit();
                    } else {
                        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                        if (string.equals(format)) {
                            format = string;
                        } else {
                            this.e.edit().putString("dp_pre_date", format).commit();
                        }
                    }
                }
            } catch (Throwable th) {
                charSequence = imprintProperty;
            }
        } catch (Throwable th2) {
            format = str;
        }
        this.i = format;
        return format;
    }

    public void a() {
        try {
            if (this.l.length() > 0) {
                g.a(a).a(this.l);
                this.l = new JSONArray();
            }
            PreferenceWrapper.getDefault(a).edit().putLong("thtstart", this.p).putInt("gkvc", this.n).putInt("ekvc", this.o).commit();
        } catch (Throwable th) {
        }
    }

    private void d(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (g.b == jSONObject.getInt("__t")) {
                if (a(this.p, this.n)) {
                    this.n++;
                } else {
                    return;
                }
            } else if (g.a == jSONObject.getInt("__t")) {
                if (a(this.p, this.o)) {
                    this.o++;
                } else {
                    return;
                }
            }
            if (this.l.length() >= this.k) {
                g.a(a).a(this.l);
                this.l = new JSONArray();
            }
            if (this.p == 0) {
                this.p = System.currentTimeMillis();
            }
            this.l.put(jSONObject);
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    private boolean a(long j, int i) {
        if (j == 0) {
            return true;
        }
        if (System.currentTimeMillis() - j > 28800000) {
            l();
            return true;
        } else if (i >= 5000) {
            return false;
        } else {
            return true;
        }
    }

    private void l() {
        try {
            this.n = 0;
            this.o = 0;
            this.p = System.currentTimeMillis();
            PreferenceWrapper.getDefault(a).edit().putLong("dstk_last_time", System.currentTimeMillis()).putInt("dstk_cnt", 0).commit();
        } catch (Throwable th) {
        }
    }

    private boolean e(boolean z) {
        if (p()) {
            return true;
        }
        if (this.d == null) {
            this.d = new c(this);
        }
        this.d.a();
        ReportStrategy c = this.d.c();
        boolean shouldSendMessage = c.shouldSendMessage(z);
        if (!shouldSendMessage) {
            return shouldSendMessage;
        }
        if ((!(c instanceof ReportByInterval) && !(c instanceof DebugPolicy)) || !m()) {
            return shouldSendMessage;
        }
        a();
        return shouldSendMessage;
    }

    private boolean m() {
        try {
            if (!TextUtils.isEmpty(o.a().c())) {
                b(a);
            }
            if (this.l.length() <= 0) {
                return false;
            }
            for (int i = 0; i < this.l.length(); i++) {
                JSONObject optJSONObject = this.l.optJSONObject(i);
                if (optJSONObject != null && optJSONObject.length() > 0) {
                    CharSequence optString = optJSONObject.optString("__i");
                    if (TextUtils.isEmpty(optString) || "-1".equals(optString)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable th) {
        }
    }

    private void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() > 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    if (jSONObject.has("analytics")) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("analytics");
                        if (jSONObject3.has(b.N)) {
                            jSONObject2.put(b.N, jSONObject3.getJSONArray(b.N));
                        }
                        if (jSONObject3.has(b.O)) {
                            jSONObject2.put(b.O, jSONObject3.getJSONArray(b.O));
                        }
                        if (jSONObject3.has(b.J)) {
                            jSONObject2.put(b.J, jSONObject3.getJSONArray(b.J));
                        }
                        if (jSONObject3.has(b.n)) {
                            JSONArray jSONArray = jSONObject3.getJSONArray(b.n);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                                if (jSONObject4 != null && jSONObject4.length() > 0) {
                                    if (jSONObject4.has(b.t)) {
                                        jSONObject4.remove(b.t);
                                    }
                                    jSONArray2.put(jSONObject4);
                                }
                            }
                            jSONObject2.put(b.n, jSONArray2);
                        }
                        if (jSONObject3.has(b.D)) {
                            jSONObject2.put(b.D, jSONObject3.getJSONObject(b.D));
                        }
                        if (jSONObject3.has(b.G)) {
                            jSONObject2.put(b.G, jSONObject3.getJSONObject(b.G));
                        }
                    }
                    if (jSONObject.has("dplus")) {
                        jSONObject2.put("dplus", jSONObject.getJSONObject("dplus"));
                    }
                    if (jSONObject.has("header") && jSONObject.has("header")) {
                        JSONObject jSONObject5 = jSONObject.getJSONObject("header");
                        if (jSONObject5 != null && jSONObject5.length() > 0) {
                            if (jSONObject5.has("sdk_version")) {
                                jSONObject2.put("sdk_version", jSONObject5.getString("sdk_version"));
                            }
                            if (jSONObject5.has("device_id")) {
                                jSONObject2.put("device_id", jSONObject5.getString("device_id"));
                            }
                            if (jSONObject5.has("device_model")) {
                                jSONObject2.put("device_model", jSONObject5.getString("device_model"));
                            }
                            if (jSONObject5.has("version_code")) {
                                jSONObject2.put("version", jSONObject5.getInt("version_code"));
                            }
                            if (jSONObject5.has("appkey")) {
                                jSONObject2.put("appkey", jSONObject5.getString("appkey"));
                            }
                            if (jSONObject5.has("channel")) {
                                jSONObject2.put("channel", jSONObject5.getString("channel"));
                            }
                        }
                    }
                    if (jSONObject2.length() > 0) {
                        MLog.d("constructMessage:" + jSONObject2.toString());
                    }
                }
            } catch (Throwable th) {
                MLog.e(th);
            }
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() <= 0) {
                    return;
                }
                if (!jSONObject.has(b.ao)) {
                    c(jSONObject);
                } else if (101 != jSONObject.getInt(b.ao)) {
                    c(jSONObject);
                }
            } catch (Throwable th) {
            }
        }
    }

    private void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            if (jSONObject.getJSONObject("header").has(b.an)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    jSONObject2 = jSONObject.getJSONObject("analytics");
                    if (jSONObject2.has(b.n)) {
                        jSONObject2 = jSONObject2.getJSONArray(b.n).getJSONObject(0);
                        if (jSONObject2 != null) {
                            g.a(a).a(true, jSONObject2.getString("id"));
                        }
                    } else {
                        g.a(a).g();
                    }
                } else if (jSONObject.has("dplus")) {
                    jSONObject2 = jSONObject.getJSONObject("dplus");
                    if (jSONObject2.has(b.Y)) {
                        g.a(a).b(0);
                    }
                    if (jSONObject2.has("session")) {
                        g.a(a).b(4);
                    }
                    if (jSONObject2.has(b.ah)) {
                        g.a(a).b(1);
                    }
                }
                g.a(a).a();
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics")) {
                jSONObject2 = jSONObject.optJSONObject("analytics");
                if (jSONObject2 != null && jSONObject2.length() > 0) {
                    if (jSONObject2.has(b.n)) {
                        g.a(a).a(true, false);
                    }
                    if (jSONObject2.has(b.N) || jSONObject2.has(b.O)) {
                        g.a(a).d();
                    }
                    if (jSONObject2.has(b.J)) {
                        g.a(a).e();
                    }
                }
            }
            if (jSONObject.has("dplus")) {
                g.a(a).f();
            }
            g.a(a).a();
        } catch (Exception e) {
        }
    }

    public void b(Object obj) {
        r = true;
        b(a);
        a();
        c(false);
        if (AnalyticsConfig.FLAG_DPLUS) {
            obj = (JSONObject) obj;
            if (obj == null) {
                obj = new JSONObject();
            }
            JSONObject g = com.umeng.analytics.b.a().g(a);
            if (g != null && g.length() > 0) {
                Iterator keys = g.keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        try {
                            String obj2 = keys.next().toString();
                            if (!Arrays.asList(b.au).contains(obj2)) {
                                obj.put(obj2, g.get(obj2));
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            a(obj, 2, false);
        }
    }

    public void b(Context context) {
        try {
            g.a(context).b();
            n();
        } catch (Throwable th) {
        }
    }

    public void b() {
        r = true;
        c(false);
    }

    public void c(Object obj) {
        o();
        s = true;
        h();
        i();
        j();
        k();
        a(true);
        if (AnalyticsConfig.FLAG_DPLUS) {
            obj = (JSONObject) obj;
            if (obj == null) {
                obj = new JSONObject();
            }
            JSONObject g = com.umeng.analytics.b.a().g(a);
            if (g != null && g.length() > 0) {
                Iterator keys = g.keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        try {
                            String obj2 = keys.next().toString();
                            if (!Arrays.asList(b.au).contains(obj2)) {
                                obj.put(obj2, g.get(obj2));
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
            a(obj, 2, false);
        }
    }

    private void n() {
        if (this.l.length() > 0) {
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            while (i < this.l.length()) {
                try {
                    JSONObject jSONObject = this.l.getJSONObject(i);
                    if (jSONObject == null || jSONObject.length() <= 0) {
                        jSONArray.put(jSONObject);
                        i++;
                    } else {
                        CharSequence optString = jSONObject.optString("__i");
                        if (TextUtils.isEmpty(optString) || "-1".equals(optString)) {
                            Object c = o.a().c();
                            if (TextUtils.isEmpty(c)) {
                                c = "-1";
                            }
                            jSONObject.put("__i", c);
                        }
                        jSONArray.put(jSONObject);
                        i++;
                    }
                } catch (Throwable th) {
                }
            }
            this.l = jSONArray;
        }
    }

    private void o() {
        try {
            if (p() && a != null) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                if (sharedPreferences != null && sharedPreferences.getLong("first_activate_time", 0) == 0) {
                    sharedPreferences.edit().putLong("first_activate_time", System.currentTimeMillis()).commit();
                }
            }
        } catch (Throwable th) {
        }
    }

    public long c() {
        try {
            if (a != null) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                if (sharedPreferences != null) {
                    long j = sharedPreferences.getLong("first_activate_time", 0);
                    if (j != 0) {
                        return j;
                    }
                    try {
                        j = System.currentTimeMillis();
                        sharedPreferences.edit().putLong("first_activate_time", j).commit();
                        return j;
                    } catch (Throwable th) {
                        return j;
                    }
                }
            }
            return 0;
        } catch (Throwable th2) {
            return 0;
        }
    }

    private boolean p() {
        try {
            if (a != null) {
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                if (!(sharedPreferences == null || sharedPreferences.getLong("ana_is_f", -1) == 0)) {
                    return true;
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    private void q() {
        try {
            b(a);
            a();
            String[] a = com.umeng.analytics.c.a(a);
            if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                boolean a2 = o.a().a(a, System.currentTimeMillis());
                com.umeng.analytics.c.b(a);
                if (a2) {
                    o.a().b(a, System.currentTimeMillis());
                }
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    private void e(Object obj) {
        try {
            b(a);
            a();
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                String string = jSONObject.getString(b.H);
                String string2 = jSONObject.getString("uid");
                String[] a = com.umeng.analytics.c.a(a);
                if (a == null || !string.equals(a[0]) || !string2.equals(a[1])) {
                    boolean a2 = o.a().a(a, System.currentTimeMillis());
                    com.umeng.analytics.c.a(a, string, string2);
                    if (a2) {
                        o.a().b(a, System.currentTimeMillis());
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    private void f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0 && jSONObject.has("__ii")) {
                String optString = jSONObject.optString("__ii");
                jSONObject.remove("__ii");
                if (!TextUtils.isEmpty(optString)) {
                    g.a(a).a(optString, obj.toString(), 2);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void d() {
        try {
            CharSequence c = o.a().c();
            if (!TextUtils.isEmpty(c)) {
                JSONArray jSONArray;
                SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(a);
                Object string = sharedPreferences.getString("n_sess_dp", "");
                if (TextUtils.isEmpty(string)) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string);
                }
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null && jSONObject.length() > 0) {
                            int i2 = jSONObject.getInt("n_sess_dp_type");
                            switch (i2) {
                                case 0:
                                    jSONObject.put("_$!ts", System.currentTimeMillis());
                                    break;
                                case 1:
                                    jSONObject.put("_$!ts", System.currentTimeMillis());
                                    break;
                            }
                            jSONObject.put("__ii", c);
                            jSONObject.remove("n_sess_dp_type");
                            g.a(a).a(jSONObject, i2);
                        }
                    }
                    sharedPreferences.edit().remove("n_sess_dp").commit();
                    if (UMEnvelopeBuild.isReadyBuild(a, UMBusinessType.U_DPLUS)) {
                        c(true);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public void a(JSONObject jSONObject) {
        try {
            if (UMEnvelopeBuild.isReadyBuild(a, UMBusinessType.U_DPLUS) && jSONObject != null) {
                JSONObject d = d(true);
                if (d != null && d.length() > 0) {
                    JSONObject jSONObject2 = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(jSONObject);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("session", jSONArray);
                    jSONObject2.put("dplus", jSONObject3);
                    if (a != null && d != null && jSONObject2 != null) {
                        UMEnvelopeBuild.buildEnvelopeWithExtHeader(a, d, jSONObject2);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }
}
