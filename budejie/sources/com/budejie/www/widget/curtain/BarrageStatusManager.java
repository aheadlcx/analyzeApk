package com.budejie.www.widget.curtain;

import android.content.SharedPreferences;
import android.os.Build.VERSION;

public class BarrageStatusManager {

    public enum BarrageState {
        CLOSE,
        SINGLE,
        MULTI
    }

    public static BarrageState a(SharedPreferences sharedPreferences) {
        boolean z = sharedPreferences.getBoolean("barrage_status", true);
        boolean z2 = sharedPreferences.getBoolean("barrage_multiple", true);
        if (z) {
            return z2 ? BarrageState.MULTI : BarrageState.SINGLE;
        } else {
            return BarrageState.CLOSE;
        }
    }

    public static void a(SharedPreferences sharedPreferences, BarrageState barrageState) {
        switch (BarrageStatusManager$1.a[barrageState.ordinal()]) {
            case 1:
                a(sharedPreferences, false);
                return;
            case 2:
                a(sharedPreferences, true);
                b(sharedPreferences, false);
                return;
            case 3:
                a(sharedPreferences, true);
                b(sharedPreferences, true);
                return;
            default:
                return;
        }
    }

    private static void a(SharedPreferences sharedPreferences, boolean z) {
        if (VERSION.SDK_INT >= 9) {
            sharedPreferences.edit().putBoolean("barrage_status", z).apply();
        } else {
            sharedPreferences.edit().putBoolean("barrage_status", z).commit();
        }
    }

    private static void b(SharedPreferences sharedPreferences, boolean z) {
        if (VERSION.SDK_INT >= 9) {
            sharedPreferences.edit().putBoolean("barrage_multiple", z).apply();
        } else {
            sharedPreferences.edit().putBoolean("barrage_multiple", z).commit();
        }
    }
}
