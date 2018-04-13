package com.loc;

public final class bz {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final int[] b = new int[]{16, 8, 4, 2, 1};

    public static final String a(double d, double d2) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        int i = 0;
        int i2 = 0;
        double[] dArr = new double[]{-90.0d, 90.0d};
        double[] dArr2 = new double[]{-180.0d, 180.0d};
        while (stringBuilder.length() < 6) {
            double d3;
            if (obj != null) {
                d3 = (dArr2[0] + dArr2[1]) / 2.0d;
                if (d2 > d3) {
                    i2 |= b[i];
                    dArr2[0] = d3;
                } else {
                    dArr2[1] = d3;
                }
            } else {
                d3 = (dArr[0] + dArr[1]) / 2.0d;
                if (d > d3) {
                    i2 |= b[i];
                    dArr[0] = d3;
                } else {
                    dArr[1] = d3;
                }
            }
            obj = obj == null ? 1 : null;
            if (i < 4) {
                i++;
            } else {
                stringBuilder.append(a[i2]);
                i = 0;
                i2 = 0;
            }
        }
        return stringBuilder.toString();
    }
}
