package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.b;
import com.umeng.analytics.pro.g.a;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class h {
    public static String a = null;
    private static JSONObject f = new JSONObject();
    boolean b = false;
    boolean c = false;
    ActivityLifecycleCallbacks d = new x(this);
    private final Map<String, Long> e = new HashMap();
    private Application g = null;

    public h(Activity activity) {
        synchronized (this) {
            if (this.g == null && activity != null) {
                this.g = activity.getApplication();
                a(activity);
            }
        }
    }

    private void a(Activity activity) {
        if (!this.b) {
            this.b = true;
            this.g.registerActivityLifecycleCallbacks(this.d);
            if (a == null) {
                this.c = true;
                b(activity);
            }
        }
    }

    public void a() {
        this.b = false;
        if (this.g != null) {
            this.g.unregisterActivityLifecycleCallbacks(this.d);
            this.g = null;
        }
    }

    public void b() {
        c(null);
        a();
    }

    public static void a(Context context) {
        try {
            synchronized (f) {
                if (context != null) {
                    if (f.length() > 0) {
                        g.a(context).a(o.a().d(), f, a.AUTOPAGE);
                        f = new JSONObject();
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    private void b(Activity activity) {
        a = activity.getPackageName() + "." + activity.getLocalClassName();
        if (AnalyticsConfig.FLAG_DPLUS) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(b.ai, a);
                jSONObject.put("_$!ts", System.currentTimeMillis());
                JSONObject i = b.a().i();
                if (i.length() > 0) {
                    jSONObject.put(b.ab, i);
                }
                Object c = o.a().c();
                String str = "__ii";
                if (TextUtils.isEmpty(c)) {
                    c = "-1";
                }
                jSONObject.put(str, c);
                if (o.a().b()) {
                    jSONObject.put("__ii", "-1");
                }
                i = b.a().g(activity.getApplicationContext());
                if (i != null && i.length() > 0) {
                    Iterator keys = i.keys();
                    if (keys != null) {
                        while (keys.hasNext()) {
                            try {
                                String obj = keys.next().toString();
                                if (!Arrays.asList(b.au).contains(obj)) {
                                    jSONObject.put(obj, i.get(obj));
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                UMWorkDispatch.sendEvent(activity.getApplicationContext(), i.a.k, CoreProtocol.getInstance(activity.getApplicationContext()), jSONObject);
            } catch (JSONException e2) {
            }
        }
        synchronized (this.e) {
            this.e.put(a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    private void c(Activity activity) {
        long j = 0;
        try {
            synchronized (this.e) {
                if (a == null && activity != null) {
                    a = activity.getPackageName() + "." + activity.getLocalClassName();
                }
                if (!TextUtils.isEmpty(a) && this.e.containsKey(a)) {
                    j = System.currentTimeMillis() - ((Long) this.e.get(a)).longValue();
                    this.e.remove(a);
                }
            }
            synchronized (f) {
                try {
                    f = new JSONObject();
                    f.put(b.u, a);
                    f.put("duration", j);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
