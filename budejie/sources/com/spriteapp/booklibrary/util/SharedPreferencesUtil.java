package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil prefsUtil;
    public Context context;
    public Editor editor;
    public SharedPreferences prefs;

    public static synchronized SharedPreferencesUtil getInstance() {
        SharedPreferencesUtil sharedPreferencesUtil;
        synchronized (SharedPreferencesUtil.class) {
            sharedPreferencesUtil = prefsUtil;
        }
        return sharedPreferencesUtil;
    }

    public static void init(Context context, String str, int i) {
        prefsUtil = new SharedPreferencesUtil();
        prefsUtil.context = context;
        prefsUtil.prefs = prefsUtil.context.getSharedPreferences(str, i);
        prefsUtil.editor = prefsUtil.prefs.edit();
    }

    private SharedPreferencesUtil() {
    }

    public boolean getBoolean(String str, boolean z) {
        return this.prefs.getBoolean(str, z);
    }

    public boolean getBoolean(String str) {
        return this.prefs.getBoolean(str, false);
    }

    public String getString(String str, String str2) {
        return this.prefs.getString(str, str2);
    }

    public String getString(String str) {
        return this.prefs.getString(str, "");
    }

    public int getInt(String str, int i) {
        return this.prefs.getInt(str, i);
    }

    public int getInt(String str) {
        return this.prefs.getInt(str, 0);
    }

    public float getFloat(String str, float f) {
        return this.prefs.getFloat(str, f);
    }

    public float getFloat(String str) {
        return this.prefs.getFloat(str, 0.0f);
    }

    public long getLong(String str, long j) {
        return this.prefs.getLong(str, j);
    }

    public long getLong(String str) {
        return this.prefs.getLong(str, 0);
    }

    public boolean exists(String str) {
        return this.prefs.contains(str);
    }

    public SharedPreferencesUtil putString(String str, String str2) {
        this.editor.putString(str, str2);
        this.editor.commit();
        return this;
    }

    public SharedPreferencesUtil putInt(String str, int i) {
        this.editor.putInt(str, i);
        this.editor.commit();
        return this;
    }

    public SharedPreferencesUtil putFloat(String str, float f) {
        this.editor.putFloat(str, f);
        this.editor.commit();
        return this;
    }

    public SharedPreferencesUtil putLong(String str, long j) {
        this.editor.putLong(str, j);
        this.editor.commit();
        return this;
    }

    public SharedPreferencesUtil putBoolean(String str, boolean z) {
        this.editor.putBoolean(str, z);
        this.editor.commit();
        return this;
    }

    public SharedPreferencesUtil remove(String str) {
        this.editor.remove(str);
        this.editor.commit();
        return this;
    }

    public SharedPreferencesUtil removeAll() {
        this.editor.clear();
        this.editor.commit();
        return this;
    }
}
