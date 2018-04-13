package qsbk.app.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DebugUtil {
    public static boolean DEBUG = false;
    public static final String TAG = "DebugUtil";

    public static void toast(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public static void debug(String str, String str2) {
        if (DEBUG) {
            Log.d(str, str2);
        }
    }

    public static void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public static void error(String str, String str2) {
        Log.e(str, str2);
    }

    public static void error(String str) {
        Log.e(TAG, str);
    }
}
