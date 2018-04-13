package com.crashlytics.android.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

public class aJ {
    private final String a;
    private final Context b;

    public aJ(u uVar) {
        if (uVar.getContext() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Sdk.start() first");
        }
        this.b = uVar.getContext();
        this.a = uVar.getClass().getName();
    }

    public SharedPreferences a() {
        return this.b.getSharedPreferences(this.a, 0);
    }

    public Editor b() {
        return a().edit();
    }

    public boolean a(Editor editor) {
        if (VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
