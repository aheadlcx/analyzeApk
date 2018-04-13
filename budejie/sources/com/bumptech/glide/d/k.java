package com.bumptech.glide.d;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.i.h;
import java.util.HashMap;
import java.util.Map;

public class k implements Callback {
    private static final k c = new k();
    final Map<FragmentManager, j> a = new HashMap();
    final Map<android.support.v4.app.FragmentManager, n> b = new HashMap();
    private volatile com.bumptech.glide.k d;
    private final Handler e = new Handler(Looper.getMainLooper(), this);

    public static k a() {
        return c;
    }

    k() {
    }

    private com.bumptech.glide.k b(Context context) {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = new com.bumptech.glide.k(context.getApplicationContext(), new b(), new f());
                }
            }
        }
        return this.d;
    }

    public com.bumptech.glide.k a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (h.c() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return a((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return a((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return a(((ContextWrapper) context).getBaseContext());
            }
        }
        return b(context);
    }

    public com.bumptech.glide.k a(FragmentActivity fragmentActivity) {
        if (h.d()) {
            return a(fragmentActivity.getApplicationContext());
        }
        b((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager());
    }

    public com.bumptech.glide.k a(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (h.d()) {
            return a(fragment.getActivity().getApplicationContext());
        } else {
            return a(fragment.getActivity(), fragment.getChildFragmentManager());
        }
    }

    @TargetApi(11)
    public com.bumptech.glide.k a(Activity activity) {
        if (h.d() || VERSION.SDK_INT < 11) {
            return a(activity.getApplicationContext());
        }
        b(activity);
        return a((Context) activity, activity.getFragmentManager());
    }

    @TargetApi(17)
    private static void b(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    j a(FragmentManager fragmentManager) {
        j jVar = (j) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (jVar != null) {
            return jVar;
        }
        jVar = (j) this.a.get(fragmentManager);
        if (jVar != null) {
            return jVar;
        }
        android.app.Fragment jVar2 = new j();
        this.a.put(fragmentManager, jVar2);
        fragmentManager.beginTransaction().add(jVar2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(1, fragmentManager).sendToTarget();
        return jVar2;
    }

    @TargetApi(11)
    com.bumptech.glide.k a(Context context, FragmentManager fragmentManager) {
        j a = a(fragmentManager);
        com.bumptech.glide.k b = a.b();
        if (b != null) {
            return b;
        }
        b = new com.bumptech.glide.k(context, a.a(), a.c());
        a.a(b);
        return b;
    }

    n a(android.support.v4.app.FragmentManager fragmentManager) {
        n nVar = (n) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (nVar != null) {
            return nVar;
        }
        nVar = (n) this.b.get(fragmentManager);
        if (nVar != null) {
            return nVar;
        }
        Fragment nVar2 = new n();
        this.b.put(fragmentManager, nVar2);
        fragmentManager.beginTransaction().add(nVar2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.e.obtainMessage(2, fragmentManager).sendToTarget();
        return nVar2;
    }

    com.bumptech.glide.k a(Context context, android.support.v4.app.FragmentManager fragmentManager) {
        n a = a(fragmentManager);
        com.bumptech.glide.k b = a.b();
        if (b != null) {
            return b;
        }
        b = new com.bumptech.glide.k(context, a.a(), a.c());
        a.a(b);
        return b;
    }

    public boolean handleMessage(Message message) {
        Object obj = null;
        boolean z = true;
        Object remove;
        switch (message.what) {
            case 1:
                FragmentManager fragmentManager = (FragmentManager) message.obj;
                remove = this.a.remove(fragmentManager);
                break;
            case 2:
                android.support.v4.app.FragmentManager fragmentManager2 = (android.support.v4.app.FragmentManager) message.obj;
                remove = this.b.remove(fragmentManager2);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && r1 == null && Log.isLoggable("RMRetriever", 5)) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj);
        }
        return z;
    }
}
