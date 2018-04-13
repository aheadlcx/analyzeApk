package com.crashlytics.android.internal;

import android.content.Context;

public abstract class p {
    private Context a;
    private boolean b;

    protected abstract void c();

    public abstract String getVersion();

    protected final synchronized void a(Context context) {
        if (!this.b) {
            if (context == null) {
                throw new IllegalArgumentException("context cannot be null.");
            }
            this.a = new cm(context.getApplicationContext(), getName());
            this.b = true;
            c();
        }
    }

    public final Context getContext() {
        return this.a;
    }

    public final synchronized boolean isInitialized() {
        return this.b;
    }

    public final String getName() {
        return getClass().getSimpleName().toLowerCase();
    }

    public String getComponentPath() {
        return ".TwitterSdk/" + getName();
    }
}
