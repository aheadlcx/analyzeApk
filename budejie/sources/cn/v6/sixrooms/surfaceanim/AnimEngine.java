package cn.v6.sixrooms.surfaceanim;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import cn.v6.sixrooms.surfaceanim.service.AnimService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimEngine {
    private static Activity a;
    private static Messenger b;
    private static ServiceConnection c;
    private static Class<? extends AnimSceneFactory> d;
    private static ExecutorService e;

    @TargetApi(14)
    static class a implements ActivityLifecycleCallbacks {
        a() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public final void onActivityStarted(Activity activity) {
        }

        public final void onActivityResumed(Activity activity) {
        }

        public final void onActivityPaused(Activity activity) {
        }

        public final void onActivityStopped(Activity activity) {
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public final void onActivityDestroyed(Activity activity) {
            if (activity == AnimEngine.a) {
                activity.getApplication().unregisterActivityLifecycleCallbacks(this);
                activity.unbindService(AnimEngine.c);
                AnimEngine.c = null;
                AnimEngine.b = null;
                AnimEngine.a = null;
                AnimEngine.d = null;
                AnimEngine.e.shutdownNow();
                AnimEngine.e = null;
            }
        }
    }

    static class b implements ServiceConnection {
        b() {
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AnimEngine.b = new Messenger(iBinder);
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private static void b(Class<? extends AnimSceneFactory> cls) {
        if (c == null) {
            c = new b();
        }
        Intent intent = new Intent();
        intent.setAction("cn.v6.sixrooms.anim");
        intent.putExtra(AnimService.BUNDLE_ANIM_FACTORY_KEY, cls.getName());
        intent.setPackage(a.getPackageName());
        a.bindService(intent, c, 1);
    }

    public static <T> void addAnimScene(T t) {
        e.submit(new a(t));
    }

    @TargetApi(14)
    public static void init(Activity activity, Class<? extends AnimSceneFactory> cls) {
        a = activity;
        d = cls;
        b(cls);
        activity.getApplication().registerActivityLifecycleCallbacks(new a());
        e = Executors.newSingleThreadExecutor();
    }
}
