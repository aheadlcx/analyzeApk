package qsbk.app.utils.comm;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

public class SharedPreferencesCache {
    private Context a;
    private String b;

    public SharedPreferencesCache(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    private void a(Editor editor) {
        if (VERSION.SDK_INT > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public void remove(String str) {
        Editor edit = this.a.getSharedPreferences(this.b, 0).edit();
        edit.remove(str);
        a(edit);
    }

    public void put(String str, String str2) {
        Editor edit = this.a.getSharedPreferences(this.b, 0).edit();
        edit.putString(str, str2);
        a(edit);
    }

    public void put(String str, int i) {
        Editor edit = this.a.getSharedPreferences(this.b, 0).edit();
        edit.putInt(str, i);
        a(edit);
    }

    public void put(String str, boolean z) {
        Editor edit = this.a.getSharedPreferences(this.b, 0).edit();
        edit.putBoolean(str, z);
        a(edit);
    }

    public String get(String str) {
        return this.a.getSharedPreferences(this.b, 0).getString(str, null);
    }

    public boolean get(String str, boolean z) {
        return this.a.getSharedPreferences(this.b, 0).getBoolean(str, z);
    }

    public int get(String str, int i) {
        return this.a.getSharedPreferences(this.b, 0).getInt(str, i);
    }
}
