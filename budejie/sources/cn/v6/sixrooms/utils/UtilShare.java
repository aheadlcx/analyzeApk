package cn.v6.sixrooms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UtilShare {
    public static int[] getBeauty(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shader_beauty", 0);
        return new int[]{sharedPreferences.getInt("alpha", 30), sharedPreferences.getInt("beta", 9)};
    }

    public static void setBeauty(Context context, int i, int i2) {
        Editor edit = context.getSharedPreferences("shader_beauty", 0).edit();
        edit.putInt("alpha", i);
        edit.putInt("beta", i2);
        edit.commit();
    }

    public static boolean isFirstShowGift(Context context) {
        return context.getSharedPreferences("gift_show", 0).getBoolean("first", true);
    }

    public static void setGiftShown(Context context) {
        Editor edit = context.getSharedPreferences("gift_show", 0).edit();
        edit.putBoolean("first", false);
        edit.commit();
    }
}
