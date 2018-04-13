package cn.dreamtobe.kpswitch.b;

import android.content.Context;
import android.content.SharedPreferences;

class b {
    private static volatile SharedPreferences a;

    b() {
    }

    public static boolean a(Context context, int i) {
        return a(context).edit().putInt("sp.key.keyboard.height", i).commit();
    }

    private static SharedPreferences a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = context.getSharedPreferences("keyboard.common", 0);
                }
            }
        }
        return a;
    }

    public static int b(Context context, int i) {
        return a(context).getInt("sp.key.keyboard.height", i);
    }
}
