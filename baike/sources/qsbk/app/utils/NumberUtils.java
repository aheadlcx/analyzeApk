package qsbk.app.utils;

import com.baidu.mobstat.Config;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
    private static final DecimalFormat a = new DecimalFormat("0.0");
    private static final String[] b = new String[]{"w"};
    private static final double[] c = new double[]{10000.0d};

    public static String format0(int i) {
        String str = i + "";
        int length = c.length;
        for (int i2 = 0; i2 < length; i2++) {
            double d;
            if (i2 != length - 1) {
                d = ((double) i) + (c[i2] / 200.0d);
            } else {
                d = (double) i;
            }
            if (d >= c[i2]) {
                return a.format(d / c[i2]) + b[i2];
            }
        }
        return str;
    }

    public static String format1(int i) {
        String str = i + "";
        int length = c.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (((double) i) >= c[i2]) {
                return new BigDecimal(((double) i) / c[i2]).setScale(1, 1) + b[i2];
            }
        }
        return str;
    }

    public static String format(int i) {
        if (i <= 1000) {
            return i + "";
        }
        return new BigDecimal((double) ((((float) i) * 1.0f) / 1000.0f)).setScale(0, 1).toString() + Config.APP_KEY;
    }
}
