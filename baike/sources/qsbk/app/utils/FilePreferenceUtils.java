package qsbk.app.utils;

import qsbk.app.utils.FilePreference.CommitType;

public class FilePreferenceUtils {
    private static FilePreference a;

    private static void a() {
        if (a == null) {
            a = new FilePreference("preference");
        }
    }

    public static String getPreferencesValue(String str) {
        a();
        return a.getString(str, null);
    }

    public static int getPreferencesIntValue(String str) {
        a();
        return a.getInt(str, 0);
    }

    public static long getPreferencesLongValue(String str) {
        a();
        return a.getLong(str, 0);
    }

    public static boolean getPreferencesBoolValue(String str) {
        a();
        return a.getBoolean(str, false);
    }

    public static void setPreferencesValue(String str, Object obj) {
        a();
        a.set(str, obj, CommitType.IMMEDIATE);
    }
}
