package c.a.a;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import c.a.c;
import c.a.g.b;
import c.a.i.b.e;
import c.a.i.x;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

public class a implements ActivityLifecycleCallbacks {
    private static volatile a a = null;
    private WeakHashMap<Context, c> b;
    private WeakHashMap<Context, b> c;

    public static a a(Application application) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(application);
                }
            }
        }
        return a;
    }

    private a(Application application) {
        application.registerActivityLifecycleCallbacks(this);
        a((Context) application);
        c.e().a(c(application));
    }

    private void a(Context context) {
        Exception e;
        LayoutInflater from = LayoutInflater.from(context);
        try {
            Field declaredField = LayoutInflater.class.getDeclaredField("mFactorySet");
            declaredField.setAccessible(true);
            declaredField.setBoolean(from, false);
            LayoutInflaterCompat.setFactory(from, b(context));
            return;
        } catch (NoSuchFieldException e2) {
            e = e2;
        } catch (IllegalArgumentException e3) {
            e = e3;
        } catch (IllegalAccessException e4) {
            e = e4;
        }
        e.printStackTrace();
    }

    private c b(Context context) {
        if (this.b == null) {
            this.b = new WeakHashMap();
        }
        c cVar = (c) this.b.get(context);
        if (cVar == null) {
            cVar = c.a(context);
        }
        this.b.put(context, cVar);
        return cVar;
    }

    private b c(final Context context) {
        if (this.c == null) {
            this.c = new WeakHashMap();
        }
        b bVar = (b) this.c.get(context);
        if (bVar == null) {
            bVar = new b(this) {
                final /* synthetic */ a b;

                public void a(c.a.g.a aVar, Object obj) {
                    if (context instanceof Activity) {
                        this.b.a((Activity) context);
                        this.b.b((Activity) context);
                    }
                    this.b.b(context).a();
                }
            };
        }
        this.c.put(context, bVar);
        return bVar;
    }

    private void a(Activity activity) {
        if (VERSION.SDK_INT >= 21 && c.e().j() && com.android.a.a.c.a()) {
            int d = x.d(activity);
            int b = x.b(activity);
            if (e.b(d) != 0) {
                activity.getWindow().setStatusBarColor(c.a.d.a.a.a().a(d));
            } else if (e.b(b) != 0) {
                activity.getWindow().setStatusBarColor(c.a.d.a.a.a().a(b));
            }
        }
    }

    private void b(Activity activity) {
        if (c.e().k()) {
            int e = x.e(activity);
            if (e.b(e) != 0) {
                Drawable b = c.a.d.a.a.a().b(e);
                if (b != null) {
                    activity.getWindow().setBackgroundDrawable(b);
                }
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        a((Context) activity);
        a(activity);
        b(activity);
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        c.e().a(c(activity));
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        c.e().b(c(activity));
        this.c.remove(activity);
        this.b.remove(activity);
    }
}
