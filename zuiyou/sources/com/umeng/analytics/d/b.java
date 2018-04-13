package com.umeng.analytics.d;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.umeng.analytics.b.f;
import com.umeng.analytics.b.f.a;
import com.umeng.analytics.b.g;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class b {
    public static String a = null;
    private static JSONObject d = new JSONObject();
    ActivityLifecycleCallbacks b = new ActivityLifecycleCallbacks(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityResumed(Activity activity) {
            this.a.b(activity);
        }

        public void onActivityPaused(Activity activity) {
            this.a.c(activity);
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }
    };
    private final Map<String, Long> c = new HashMap();
    private Application e = null;

    public b(Activity activity) {
        if (activity != null) {
            this.e = activity.getApplication();
            a(activity);
        }
    }

    private void a(Activity activity) {
        this.e.registerActivityLifecycleCallbacks(this.b);
        if (a == null) {
            b(activity);
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.unregisterActivityLifecycleCallbacks(this.b);
        }
    }

    public void a(Context context) {
        c(null);
        a();
    }

    public static void b(Context context) {
        try {
            synchronized (d) {
                if (d.length() > 0) {
                    f.a(context).a(p.a(), d, a.AUTOPAGE);
                    d = new JSONObject();
                }
            }
        } catch (Throwable th) {
        }
    }

    private void b(Activity activity) {
        a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.c) {
            this.c.put(a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    private void c(Activity activity) {
        long j = 0;
        try {
            synchronized (this.c) {
                if (this.c.containsKey(a)) {
                    j = System.currentTimeMillis() - ((Long) this.c.get(a)).longValue();
                    this.c.remove(a);
                }
            }
            synchronized (d) {
                try {
                    d = new JSONObject();
                    d.put(g.ab, a);
                    d.put("duration", j);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
