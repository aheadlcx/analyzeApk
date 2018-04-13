package com.tencent.tinker.loader.hotplug.interceptor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public abstract class Interceptor<T_TARGET> {
    private static final String TAG = "Tinker.Interceptor";
    private volatile boolean mInstalled = false;
    private T_TARGET mTarget = null;

    protected interface ITinkerHotplugProxy {
    }

    @Nullable
    protected abstract T_TARGET fetchTarget() throws Throwable;

    protected abstract void inject(@Nullable T_TARGET t_target) throws Throwable;

    @NonNull
    protected T_TARGET decorate(@Nullable T_TARGET t_target) throws Throwable {
        return t_target;
    }

    public synchronized void install() throws InterceptFailedException {
        try {
            Object fetchTarget = fetchTarget();
            this.mTarget = fetchTarget;
            Object decorate = decorate(fetchTarget);
            if (decorate != fetchTarget) {
                inject(decorate);
            } else {
                Log.w(TAG, "target: " + fetchTarget + " was already hooked.");
            }
            this.mInstalled = true;
        } catch (Throwable th) {
            this.mTarget = null;
            InterceptFailedException interceptFailedException = new InterceptFailedException(th);
        }
    }

    public synchronized void uninstall() throws InterceptFailedException {
        if (this.mInstalled) {
            try {
                inject(this.mTarget);
                this.mTarget = null;
                this.mInstalled = false;
            } catch (Throwable th) {
                InterceptFailedException interceptFailedException = new InterceptFailedException(th);
            }
        }
    }
}
