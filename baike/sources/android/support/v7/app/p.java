package android.support.v7.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.VisibleForTesting;
import android.support.v7.view.SupportActionModeWrapper.CallbackWrapper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Window;
import android.view.Window.Callback;

@RequiresApi(14)
class p extends o {
    private int t = -100;
    private boolean u;
    private boolean v = true;
    private b w;

    class a extends b {
        final /* synthetic */ p c;

        a(p pVar, Callback callback) {
            this.c = pVar;
            super(pVar, callback);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            if (this.c.isHandleNativeActionModesEnabled()) {
                return a(callback);
            }
            return super.onWindowStartingActionMode(callback);
        }

        final ActionMode a(ActionMode.Callback callback) {
            Object callbackWrapper = new CallbackWrapper(this.c.a, callback);
            android.support.v7.view.ActionMode startSupportActionMode = this.c.startSupportActionMode(callbackWrapper);
            if (startSupportActionMode != null) {
                return callbackWrapper.getActionModeWrapper(startSupportActionMode);
            }
            return null;
        }
    }

    @VisibleForTesting
    final class b {
        final /* synthetic */ p a;
        private ai b;
        private boolean c;
        private BroadcastReceiver d;
        private IntentFilter e;

        b(p pVar, @NonNull ai aiVar) {
            this.a = pVar;
            this.b = aiVar;
            this.c = aiVar.a();
        }

        final int a() {
            this.c = this.b.a();
            return this.c ? 2 : 1;
        }

        final void b() {
            boolean a = this.b.a();
            if (a != this.c) {
                this.c = a;
                this.a.applyDayNight();
            }
        }

        final void c() {
            d();
            if (this.d == null) {
                this.d = new q(this);
            }
            if (this.e == null) {
                this.e = new IntentFilter();
                this.e.addAction("android.intent.action.TIME_SET");
                this.e.addAction("android.intent.action.TIMEZONE_CHANGED");
                this.e.addAction("android.intent.action.TIME_TICK");
            }
            this.a.a.registerReceiver(this.d, this.e);
        }

        final void d() {
            if (this.d != null) {
                this.a.a.unregisterReceiver(this.d);
                this.d = null;
            }
        }
    }

    p(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null && this.t == -100) {
            this.t = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    Callback a(Callback callback) {
        return new a(this, callback);
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
        this.v = z;
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.v;
    }

    public boolean applyDayNight() {
        boolean z = false;
        int j = j();
        int a = a(j);
        if (a != -1) {
            z = e(a);
        }
        if (j == 0) {
            k();
            this.w.c();
        }
        this.u = true;
        return z;
    }

    public void onStart() {
        super.onStart();
        applyDayNight();
    }

    public void onStop() {
        super.onStop();
        if (this.w != null) {
            this.w.d();
        }
    }

    public void setLocalNightMode(int i) {
        switch (i) {
            case -1:
            case 0:
            case 1:
            case 2:
                if (this.t != i) {
                    this.t = i;
                    if (this.u) {
                        applyDayNight();
                        return;
                    }
                    return;
                }
                return;
            default:
                Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
                return;
        }
    }

    int a(int i) {
        switch (i) {
            case -100:
                return -1;
            case 0:
                k();
                return this.w.a();
            default:
                return i;
        }
    }

    private int j() {
        return this.t != -100 ? this.t : AppCompatDelegate.getDefaultNightMode();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.t != -100) {
            bundle.putInt("appcompat:local_night_mode", this.t);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.w != null) {
            this.w.d();
        }
    }

    private boolean e(int i) {
        Resources resources = this.a.getResources();
        Configuration configuration = resources.getConfiguration();
        int i2 = configuration.uiMode & 48;
        int i3 = i == 2 ? 32 : 16;
        if (i2 == i3) {
            return false;
        }
        if (l()) {
            ((Activity) this.a).recreate();
        } else {
            Configuration configuration2 = new Configuration(configuration);
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            configuration2.uiMode = i3 | (configuration2.uiMode & -49);
            resources.updateConfiguration(configuration2, displayMetrics);
            if (VERSION.SDK_INT < 26) {
                ad.a(resources);
            }
        }
        return true;
    }

    private void k() {
        if (this.w == null) {
            this.w = new b(this, ai.a(this.a));
        }
    }

    private boolean l() {
        if (!this.u || !(this.a instanceof Activity)) {
            return false;
        }
        try {
            if ((this.a.getPackageManager().getActivityInfo(new ComponentName(this.a, this.a.getClass()), 0).configChanges & 512) == 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e);
            return true;
        }
    }
}
