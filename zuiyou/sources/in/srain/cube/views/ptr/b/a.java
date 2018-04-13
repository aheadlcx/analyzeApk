package in.srain.cube.views.ptr.b;

import android.util.Log;

public class a {
    private static int a = 0;

    public static void a(String str, String str2, Object... objArr) {
        if (a <= 0) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }
    }

    public static void a(String str, String str2) {
        if (a <= 1) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (a <= 1) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (a <= 2) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        if (a <= 2) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (a <= 4) {
            Log.e(str, str2);
        }
    }
}
