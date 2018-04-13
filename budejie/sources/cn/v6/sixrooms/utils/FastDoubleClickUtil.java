package cn.v6.sixrooms.utils;

public class FastDoubleClickUtil {
    private static long a;

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - a;
        if (0 < j && j < 800) {
            return true;
        }
        a = currentTimeMillis;
        return false;
    }

    public static boolean isFastLongClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - a;
        if (0 < j && j < 1000) {
            return true;
        }
        a = currentTimeMillis;
        return false;
    }
}
