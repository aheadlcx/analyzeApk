package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class SaveSettingsUtils {
    public static void saveSettings(Context context, String str, boolean z) {
        Editor edit = context.getSharedPreferences("appSettings", 32768).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static boolean getBooleanSettings(Context context, String str) {
        return context.getSharedPreferences("appSettings", 32768).getBoolean(str, true);
    }

    public static void saveSettings(Context context, String str, int i) {
        Editor edit = context.getSharedPreferences("appSettings", 32768).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static int getIntSettings(Context context, String str) {
        return context.getSharedPreferences("appSettings", 32768).getInt(str, -1);
    }
}
