package com.microquation.linkedme.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import com.facebook.common.util.UriUtil;
import com.microquation.linkedme.android.b.d;
import com.microquation.linkedme.android.b.k;
import com.microquation.linkedme.android.b.o;
import com.microquation.linkedme.android.b.t;
import com.microquation.linkedme.android.b.u;
import com.microquation.linkedme.android.f.b;
import com.microquation.linkedme.android.f.c;
import com.microquation.linkedme.android.indexing.LMUniversalObject;
import com.microquation.linkedme.android.util.LinkProperties;
import com.microquation.linkedme.android.util.c.g;
import com.microquation.linkedme.android.util.e;
import com.microquation.linkedme.android.util.h;
import com.tencent.mm.sdk.constants.ConstantsAPI.Token;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static final String a = a.class.getName();
    private static volatile a c;
    private static boolean d = false;
    private static boolean e = false;
    private static a$a f = a$a.a;
    private int A = 200;
    private com.microquation.linkedme.android.c.a B;
    private com.microquation.linkedme.android.c.a C;
    private String D;
    private boolean E = false;
    private String F;
    private boolean G = true;
    private boolean H = false;
    private String I = "lm_act_ref_name";
    private boolean J = false;
    private boolean K = false;
    private ScheduledFuture L;
    private Timer M;
    private boolean N = false;
    private int O = 0;
    private Uri P = null;
    private boolean Q = false;
    final Object b;
    private final ConcurrentHashMap<String, String> g;
    private JSONObject h;
    private d i;
    private b j;
    private e k;
    private com.microquation.linkedme.android.d.b l;
    private Context m;
    private Timer n;
    private Timer o;
    private boolean p;
    private Semaphore q;
    private o r;
    private int s;
    private boolean t;
    private Map<c, String> u;
    private String v;
    private a$d w = a$d.c;
    private WeakReference<Activity> x;
    private boolean y = false;
    private boolean z = true;

    private a(@NonNull Context context) {
        this.m = context;
        this.j = b.a(this.m);
        this.i = new d(this.m);
        this.k = new h(this.m);
        this.r = o.a(this.m);
        this.q = new Semaphore(1);
        this.n = new Timer();
        this.o = new Timer();
        this.b = new Object();
        this.p = false;
        this.s = 0;
        this.t = true;
        this.u = new ArrayMap();
        this.g = new ConcurrentHashMap();
        this.l = new com.microquation.linkedme.android.d.a(context);
        com.microquation.linkedme.android.e.a.a(context.getApplicationContext());
        this.k.a(this.m);
        this.k.b(this.m);
    }

    @TargetApi(14)
    public static a a() {
        if (c == null) {
            Log.e(a, "LinkedMe没有初始化.[如果您调整后依然看到这个提示,请尝试使用getInstance(Context ctx).进行初始化工作]");
        } else if (d && !e) {
            Log.e(a, "LinkedMe没有初始化成功. 请确保您的Application继承自LMApp或者您已经在您的Application#onCreate中初始化LinkedMe. ");
        }
        return c;
    }

    public static a a(@NonNull Context context) {
        boolean z = false;
        if (VERSION.SDK_INT >= 14) {
            z = true;
        }
        return a(context, null, z);
    }

    private static a a(@NonNull Context context, String str, boolean z) {
        if (c == null) {
            c = b(context);
            if (TextUtils.isEmpty(str)) {
                str = c.j.i();
            }
            a(context, str);
        }
        c.m = context.getApplicationContext();
        if (z && VERSION.SDK_INT >= 14) {
            d = true;
            c.a((Application) context.getApplicationContext());
        }
        return c;
    }

    private String a(LinkProperties linkProperties) {
        if (linkProperties != null) {
            ArrayMap b = linkProperties.b();
            if (b != null) {
                return (String) b.get(this.I);
            }
        }
        return null;
    }

    private JSONObject a(String str) {
        if (str.equals("lkme_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            try {
                return new JSONObject(new String(Base64.decode(str.getBytes(), 2)));
            } catch (JSONException e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
    }

    private JSONObject a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (this.h != null) {
                    if (this.h.length() > 0) {
                        this.l.c(a, "当前使用调试模式参数");
                    }
                    Iterator keys = this.h.keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        jSONObject.put(str, this.h.get(str));
                    }
                }
            } catch (Exception e) {
            }
        }
        return jSONObject;
    }

    private void a(int i, int i2) {
        a(i >= this.r.a() ? this.r.a(this.r.a() - 1) : this.r.a(i), i2);
    }

    @TargetApi(14)
    private void a(Application application) {
        try {
            ActivityLifecycleCallbacks a_b = new a$b(this, null);
            application.unregisterActivityLifecycleCallbacks(a_b);
            application.registerActivityLifecycleCallbacks(a_b);
            e = true;
            return;
        } catch (NoSuchMethodError e) {
        } catch (NoClassDefFoundError e2) {
        }
        e = false;
        d = false;
        Log.w(a, new com.microquation.linkedme.android.f.a("", -108).a());
    }

    private static void a(@NonNull Context context, String str) {
        boolean c;
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "lkme_no_value")) {
            c.l.b("!LinkedME: 请确认您已经在manifest正确声明了LinkedMe的AppKey!");
            c = c.j.c("lkme_no_value");
        } else {
            c = c.j.c(str);
        }
        if (c) {
            c.u.clear();
            c.r.d();
        }
    }

    private void a(Intent intent, JSONObject jSONObject, LinkProperties linkProperties) throws JSONException {
        intent.putExtra("linkedme.sdk.auto_linked", Constants.SERVICE_SCOPE_FLAG_VALUE);
        Parcelable a = LMUniversalObject.a();
        if (linkProperties == null) {
            b.a(a, "跳转无相关参数！");
        } else {
            b.a(a, "跳转的参数为：" + linkProperties.a());
            ArrayMap b = linkProperties.b();
            if (!(b == null || b.isEmpty())) {
                for (String str : b.keySet()) {
                    String str2;
                    intent.putExtra(str2, (String) b.get(str2));
                }
            }
        }
        intent.putExtra("lmLinkProperties", linkProperties);
        intent.putExtra("lmUniversalObject", a);
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            str2 = (String) keys.next();
            intent.putExtra(str2, jSONObject.getString(str2));
        }
    }

    private void a(com.microquation.linkedme.android.b.h hVar, int i) {
        if (hVar != null) {
            hVar.a(i, "");
            if (t.e(hVar)) {
                x();
                w();
            }
        }
    }

    private void a(com.microquation.linkedme.android.b.h hVar, com.microquation.linkedme.android.c.d dVar) {
        if (this.r.f()) {
            this.r.a(dVar);
            this.r.a(hVar, this.s, dVar);
        } else {
            b(hVar);
        }
        n();
    }

    private void a(com.microquation.linkedme.android.c.c cVar) {
        b.a(a, "executeClose status start ===  " + this.w);
        if (this.w != a$d.c) {
            com.microquation.linkedme.android.b.h a;
            if (this.t) {
                if (!this.r.e()) {
                    a = k.a(this.m, cVar);
                    if (this.j.Y()) {
                        c(a);
                    } else {
                        a.a(new u(g.d.a(), 200), c);
                    }
                }
                b.a(a, "executeClose status central ===  " + this.w);
            } else {
                a = this.r.c();
                if ((a != null && t.c(a)) || t.d(a)) {
                    this.r.b();
                }
            }
            this.w = a$d.c;
        }
        b.a(a, "executeClose status end ===  " + this.w);
    }

    private void a(com.microquation.linkedme.android.c.d dVar) {
        if (this.j.h() == null || this.j.h().equalsIgnoreCase("lkme_no_value")) {
            this.w = a$d.c;
            if (dVar != null) {
                dVar.a(null, new com.microquation.linkedme.android.f.a("初始化LinkedME问题。", -1234));
            }
            this.l.c("LinkedME 警告: 请在manifest配置文件中配置你的linkedme_key并且在Application的onCreate()方法中调用LinkedME.getInstance(this);");
        } else if (u() && this.k.a(true) == 1) {
            a(k.a(this.m, this.i.a(), dVar), dVar);
        } else {
            a(k.a(this.m, this.j.n(), this.i.a(), dVar), dVar);
        }
    }

    private void a(com.microquation.linkedme.android.c.d dVar, Activity activity, boolean z) {
        if (activity != null) {
            this.x = new WeakReference(activity);
        }
        if (u() && s() && this.w == a$d.a) {
            if (dVar != null) {
                if (!d) {
                    dVar.a(new JSONObject(), null);
                } else if (this.y) {
                    dVar.a(new JSONObject(), null);
                } else {
                    dVar.a(d(), null);
                    this.y = true;
                }
            }
            p();
            r();
            return;
        }
        if (z) {
            this.j.t();
        } else {
            this.j.u();
        }
        if (this.w == a$d.b) {
            this.r.a(dVar);
            return;
        }
        this.w = a$d.b;
        a(dVar);
    }

    private boolean a(Intent intent) {
        if (intent != null) {
            Set<String> categories = intent.getCategories();
            if (categories == null) {
                return false;
            }
            Object obj;
            for (String equals : categories) {
                if (TextUtils.equals(equals, "android.intent.category.LAUNCHER")) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                return false;
            }
            if (intent.getData() != null) {
                return false;
            }
            if (intent.getExtras() != null && intent.getExtras().containsKey(Token.WX_TOKEN_PLATFORMID_KEY)) {
                return true;
            }
            if (intent.getSourceBounds() != null) {
                return false;
            }
            if (TextUtils.isEmpty(intent.getPackage())) {
                return false;
            }
            if (intent.getExtras() == null && !TextUtils.isEmpty(intent.getPackage())) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            return uri.getQueryParameter(com.microquation.linkedme.android.util.c.a.f.a()) != null || com.microquation.linkedme.android.util.c.a.g.a().equals(uri.getHost()) || com.microquation.linkedme.android.util.c.a.h.a().equals(uri.getHost());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean a(JSONObject jSONObject, ActivityInfo activityInfo) {
        Object string = activityInfo.metaData.getString("linkedme.sdk.auto_link_keys");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        for (String str : TextUtils.split(string, ",")) {
            if (jSONObject.has(str) || "linkedme".equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static a b(@NonNull Context context) {
        return new a(context.getApplicationContext());
    }

    private void b(com.microquation.linkedme.android.b.h hVar) {
        if (this.s == 0) {
            this.r.a(hVar, 0);
        } else {
            this.r.a(hVar, 1);
        }
    }

    private boolean b(Uri uri, Activity activity) {
        b.a(a, "调用了readAndStripParam() 方法。");
        if (uri != null) {
            try {
                if (a(uri)) {
                    b.a(a, "调用了readAndStripParam() 方法并且是深度链接跳转，uri 为：" + uri);
                    this.j.h(uri.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (activity != null) {
            try {
                if (!(activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
                    Bundle extras = activity.getIntent().getExtras();
                    Set<String> keySet = extras.keySet();
                    if (keySet.size() > 0) {
                        JSONObject jSONObject = new JSONObject();
                        for (String str : keySet) {
                            String str2;
                            jSONObject.put(str2, extras.get(str2));
                        }
                        this.j.i(jSONObject.toString());
                    }
                }
            } catch (Exception e2) {
            }
        }
        if (!(uri == null || !uri.isHierarchical() || activity == null)) {
            if (uri.getQueryParameter(com.microquation.linkedme.android.util.c.a.e.a()) != null) {
                b.a(a, "调用了readAndStripParam() 方法且是uri scheme方式。");
                this.j.j(uri.getQueryParameter(com.microquation.linkedme.android.util.c.a.e.a()));
                str2 = com.microquation.linkedme.android.util.c.a.e.a() + LoginConstants.EQUAL + uri.getQueryParameter(com.microquation.linkedme.android.util.c.a.e.a()) + "&" + com.microquation.linkedme.android.util.c.a.f.a() + LoginConstants.EQUAL + uri.getQueryParameter(com.microquation.linkedme.android.util.c.a.f.a());
                String dataString = activity.getIntent().getDataString();
                str2 = uri.getQuery().length() == str2.length() ? "\\?" + str2 : dataString.length() - str2.length() == dataString.indexOf(str2) ? "&" + str2 : str2 + "&";
                activity.getIntent().setData(Uri.parse(dataString.replaceFirst(str2, "")));
                return true;
            }
            b.a(a, "调用了readAndStripParam() 方法且是app links方式。");
            str2 = uri.getScheme();
            if (str2 != null && (activity.getIntent().getFlags() & 1048576) == 0) {
                if ((str2.equalsIgnoreCase(UriUtil.HTTP_SCHEME) || str2.equalsIgnoreCase("https")) && uri.getHost() != null && uri.getHost().length() > 0 && uri.getQueryParameter(com.microquation.linkedme.android.util.c.a.i.a()) == null) {
                    this.j.k(uri.toString());
                    str2 = uri.toString();
                    if (a(uri)) {
                        str2 = str2.replace(uri.getHost(), "");
                    }
                    activity.getIntent().setData(Uri.parse(str2));
                    return false;
                }
                this.l.a("通过App links 启动！");
            }
        }
        return false;
    }

    private boolean b(String str, String str2) {
        String[] split = str.split("\\?")[0].split("/");
        String[] split2 = str2.split("\\?")[0].split("/");
        if (split.length != split2.length) {
            return false;
        }
        int i = 0;
        while (i < split.length && i < split2.length) {
            String str3 = split[i];
            if (!str3.equals(split2[i]) && !str3.contains("*")) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean b(JSONObject jSONObject, ActivityInfo activityInfo) {
        String str = null;
        if (jSONObject.has(com.microquation.linkedme.android.util.c.a.ax.a())) {
            JSONObject optJSONObject = jSONObject.optJSONObject(com.microquation.linkedme.android.util.c.a.ax.a());
            if (optJSONObject != null && optJSONObject.has(com.microquation.linkedme.android.util.c.a.aB.a())) {
                optJSONObject = optJSONObject.optJSONObject(com.microquation.linkedme.android.util.c.a.aB.a());
                if (optJSONObject.has(com.microquation.linkedme.android.util.c.a.q.a())) {
                    str = optJSONObject.optString(com.microquation.linkedme.android.util.c.a.q.a());
                } else if (optJSONObject.has(com.microquation.linkedme.android.util.c.a.r.a())) {
                    str = optJSONObject.optString(com.microquation.linkedme.android.util.c.a.r.a());
                }
            }
        }
        Object string = activityInfo.metaData.getString("linkedme.sdk.auto_link_path");
        if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(str))) {
            for (String trim : TextUtils.split(string, ",")) {
                if (b(trim.trim(), str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void c(com.microquation.linkedme.android.b.h hVar) {
        if (!(this.w == a$d.a || t.e(hVar) || t.f(hVar) || t.g(hVar))) {
            if (t.b(hVar) && this.w == a$d.c) {
                this.l.b("LinkedME 没有完成session初始化，不需要关闭。");
                return;
            }
            Activity activity = this.x != null ? (Activity) this.x.get() : null;
            if (f == a$a.a) {
                a((com.microquation.linkedme.android.c.d) null, activity, true);
            } else {
                a((com.microquation.linkedme.android.c.d) null, activity, f == a$a.b);
            }
        }
        this.r.a(hVar);
        hVar.l();
        n();
    }

    private void k() {
        if (!(this.L == null || this.L.isCancelled())) {
            this.L.cancel(true);
        }
        a(null);
        l();
    }

    @TargetApi(9)
    private void l() {
        if (this.j.y() && this.j.H() && !this.N) {
            m();
            this.N = true;
        }
        int R = this.j.R();
        if (R == 0) {
            com.microquation.linkedme.android.a.d.a().g();
        } else if (R > 0 && this.M == null) {
            b.a("durationTimer is created");
            this.M = new Timer();
            this.M.schedule(new a$2(this), TimeUnit.MINUTES.toMillis((long) R));
        }
    }

    private void m() {
        b.a("scheduleListOfApps: start");
        com.microquation.linkedme.android.b.h a = k.a(this.m);
        if (!a.n() && !a.a(this.m)) {
            new a$c(this, a).execute(new Void[0]);
        }
    }

    private void n() {
        try {
            this.q.acquire();
            if (this.s != 0 || this.r.a() <= 0) {
                this.q.release();
                return;
            }
            this.s = 1;
            com.microquation.linkedme.android.b.h c = this.r.c();
            this.q.release();
            if (c == null) {
                this.r.b(null);
            } else if (!t.c(c) && !u()) {
                this.l.b("LinkedME 错误: 用户session没有被初始化!");
                this.s = 0;
                a(this.r.a() - 1, -101);
            } else if (t.e(c) || (s() && t())) {
                new a$c(this, c).execute(new Void[0]);
            } else {
                this.s = 0;
                a(this.r.a() - 1, -101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void o() {
        int i = 0;
        while (i < this.r.a()) {
            try {
                com.microquation.linkedme.android.b.h a = this.r.a(i);
                if (a.h() != null) {
                    Iterator keys = a.h().keys();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        if (str.equals(com.microquation.linkedme.android.util.c.a.d.a())) {
                            a.h().put(str, this.j.k());
                        } else if (str.equals(com.microquation.linkedme.android.util.c.a.a.a())) {
                            a.h().put(str, this.j.l());
                        } else if (str.equals(com.microquation.linkedme.android.util.c.a.c.a())) {
                            a.h().put(str, this.j.j());
                        }
                    }
                    continue;
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void p() {
        if (this.o != null) {
            this.o.cancel();
            this.o.purge();
            this.o = new Timer();
        }
    }

    private void q() {
        if (this.n != null) {
            this.n.cancel();
            this.n.purge();
            this.n = new Timer();
        }
    }

    private void r() {
        this.p = true;
        synchronized (this.b) {
            q();
            this.n.schedule(new a$3(this), 2000);
        }
    }

    private boolean s() {
        return !this.j.k().equals("lkme_no_value");
    }

    private boolean t() {
        return !this.j.j().equals("lkme_no_value");
    }

    private boolean u() {
        return !this.j.l().equals("lkme_no_value");
    }

    private synchronized void v() {
        int i = SocketUtil.TYPEID_1501;
        synchronized (this) {
            if (!this.J || this.H) {
                JSONObject d = d();
                b.a(a, "参数原始数据为：" + d);
                String str = null;
                try {
                    if (d.optBoolean(com.microquation.linkedme.android.util.c.a.az.a(), false) && d.length() > 0) {
                        int i2;
                        LinkProperties f = LinkProperties.f();
                        String a = a(f);
                        if (!TextUtils.isEmpty(a)) {
                            i2 = SocketUtil.TYPEID_1501;
                        } else if (TextUtils.isEmpty(this.D)) {
                            ApplicationInfo applicationInfo = this.m.getPackageManager().getApplicationInfo(this.m.getPackageName(), 128);
                            if (applicationInfo.metaData == null || !applicationInfo.metaData.getBoolean("linkedme.sdk.auto_link_disable", false)) {
                                ActivityInfo[] activityInfoArr = this.m.getPackageManager().getPackageInfo(this.m.getPackageName(), 129).activities;
                                if (activityInfoArr != null) {
                                    for (ActivityInfo activityInfo : activityInfoArr) {
                                        if (activityInfo != null && activityInfo.metaData != null && ((activityInfo.metaData.getString("linkedme.sdk.auto_link_keys") != null || activityInfo.metaData.getString("linkedme.sdk.auto_link_path") != null) && (a(d, activityInfo) || b(d, activityInfo)))) {
                                            str = activityInfo.name;
                                            i = activityInfo.metaData.getInt("linkedme.sdk.auto_link_request_code", SocketUtil.TYPEID_1501);
                                            break;
                                        }
                                    }
                                }
                                i2 = i;
                                a = str;
                            }
                        } else {
                            b.a(a, "设置的中间处理页面为：" + this.D);
                            a = this.D;
                            i2 = SocketUtil.TYPEID_1501;
                        }
                        if (a == null || this.x == null) {
                            this.l.c(a, "无接收深度链接跳转参数的中转页面。");
                        } else {
                            new Handler().postDelayed(new a$4(this, a, d, f, i2), (long) this.A);
                        }
                    }
                } catch (NameNotFoundException e) {
                    this.l.c("LinkedME Warning: 请确保自动深度链接Activity正确配置！");
                }
            }
        }
    }

    private void w() {
        if (this.C == null) {
            b.a(a, "LMDLResultListener 不能为null");
            return;
        }
        JSONObject d = d();
        if (d.isNull("params")) {
            b.a(a, "Params no data ");
            this.C.a(null);
            return;
        }
        Object optString = d.optString("params");
        if (TextUtils.isEmpty(optString)) {
            b.a(a, "Params no data ");
            this.C.a(null);
            return;
        }
        b.a(a, "Params: " + optString);
        this.C.a(LinkProperties.f());
        a().b();
    }

    private void x() {
        if (this.B != null && !this.J) {
            this.B.a(null, new com.microquation.linkedme.android.f.a("LinkedME 提示信息：", -118));
        }
    }

    @TargetApi(9)
    private void y() {
        if (this.L == null || this.L.isCancelled()) {
            this.L = ((ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1)).scheduleAtFixedRate(new a$5(this), 1, TimeUnit.MINUTES.toSeconds((long) this.j.I()), TimeUnit.SECONDS);
        } else {
            b.a("GAL任务已经执行。");
        }
    }

    public void a(com.microquation.linkedme.android.b.h hVar) {
        if (!hVar.n() && !hVar.a(this.m)) {
            new a$c(this, hVar).execute(new Void[0]);
        }
    }

    public void a(String str, String str2) {
        this.g.put(str, str2);
    }

    public void a(boolean z) {
        this.j.a(z);
    }

    public boolean a(Uri uri, Activity activity) {
        b(uri, activity);
        return a((com.microquation.linkedme.android.c.d) null, activity);
    }

    public boolean a(com.microquation.linkedme.android.c.d dVar, Activity activity) {
        boolean z = true;
        if (f == a$a.a) {
            a(dVar, activity, true);
        } else {
            if (f != a$a.b) {
                z = false;
            }
            a(dVar, activity, z);
        }
        return false;
    }

    public void b() {
        this.j.l("lkme_no_value");
    }

    public a c() {
        this.j.w();
        return this;
    }

    public JSONObject d() {
        return a(a(this.j.p()));
    }

    public JSONObject e() {
        if (this.h != null && this.h.length() > 0) {
            this.l.c(a, "当前使用调试模式参数");
        }
        return this.h;
    }

    public String f() {
        return (TextUtils.isEmpty(this.v) || "lkme_no_value".equals(this.v)) ? this.k.x() : this.v;
    }

    public Activity g() {
        return this.x != null ? (Activity) this.x.get() : null;
    }

    public e h() {
        if (this.k == null) {
            this.k = new h(this.m);
        }
        return this.k;
    }

    public Context i() {
        return this.m;
    }
}
