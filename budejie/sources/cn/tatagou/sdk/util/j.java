package cn.tatagou.sdk.util;

public class j {
    public static int a = 20;

    public static int allPager(int i) {
        if (i % a == 0) {
            return i / a;
        }
        return (i / a) + 1;
    }

    public static int lastPage(int i, int i2) {
        int i3 = a * i;
        return i3 > i2 ? i2 : i3;
    }

    public static int up(int i) {
        if (i <= 1) {
            return 1;
        }
        return i - 1;
    }

    public static int next(int i, int i2) {
        if (i >= i2) {
            return -1;
        }
        return i + 1;
    }

    public static int nextPage(int i, int i2) {
        if (i > i2) {
            return -1;
        }
        return i + 1;
    }
}
