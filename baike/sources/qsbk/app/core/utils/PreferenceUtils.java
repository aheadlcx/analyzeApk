package qsbk.app.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import java.util.Map;

public class PreferenceUtils {
    private static PreferenceUtils a;
    private SharedPreferences b;
    private Editor c = this.b.edit();

    public static PreferenceUtils instance() {
        if (a == null) {
            synchronized (PreferenceUtils.class) {
                if (a == null) {
                    a = new PreferenceUtils(AppUtils.getInstance().getAppContext());
                }
            }
        }
        return a;
    }

    private PreferenceUtils(Context context) {
        this.b = context.getSharedPreferences("ye", 4);
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.b.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.b.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public Map<String, ?> getAll() {
        return this.b.getAll();
    }

    public boolean contains(String str) {
        return this.b.contains(str);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public float getFloat(String str, float f) {
        return this.b.getFloat(str, f);
    }

    public int getInt(String str, int i) {
        return this.b.getInt(str, i);
    }

    public long getLong(String str, long j) {
        return this.b.getLong(str, j);
    }

    public String getString(String str, String str2) {
        return this.b.getString(str, str2);
    }

    public boolean putBoolean(String str, boolean z) {
        this.c.putBoolean(str, z);
        return this.c.commit();
    }

    public boolean putInt(String str, int i) {
        this.c.putInt(str, i);
        return this.c.commit();
    }

    public boolean putFloat(String str, float f) {
        this.c.putFloat(str, f);
        return this.c.commit();
    }

    public boolean putLong(String str, long j) {
        this.c.putLong(str, j);
        return this.c.commit();
    }

    public boolean putString(String str, String str2) {
        this.c.putString(str, str2);
        return this.c.commit();
    }

    public boolean removeKey(String str) {
        this.c.remove(str);
        return this.c.commit();
    }
}
