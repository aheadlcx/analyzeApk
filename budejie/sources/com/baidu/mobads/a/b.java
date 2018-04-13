package com.baidu.mobads.a;

public class b {
    public static final Boolean a = Boolean.valueOf(false);
    public static final Boolean b = Boolean.valueOf(false);

    public static double a() {
        try {
            return Double.parseDouble("8.7018");
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public static int b() {
        int i = 0;
        try {
            i = Integer.valueOf("8.7018".split("\\.")[0]).intValue();
        } catch (Exception e) {
        }
        return i;
    }
}
