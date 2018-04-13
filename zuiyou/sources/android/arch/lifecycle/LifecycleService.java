package android.arch.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

public class LifecycleService extends Service implements g {
    private final n a = new n(this);

    @CallSuper
    public void onCreate() {
        this.a.a();
        super.onCreate();
    }

    @Nullable
    @CallSuper
    public IBinder onBind(Intent intent) {
        this.a.b();
        return null;
    }

    @CallSuper
    public void onStart(Intent intent, int i) {
        this.a.c();
        super.onStart(intent, i);
    }

    @CallSuper
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @CallSuper
    public void onDestroy() {
        this.a.d();
        super.onDestroy();
    }

    public Lifecycle getLifecycle() {
        return this.a.e();
    }
}
