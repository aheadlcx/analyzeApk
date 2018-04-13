package com.baidu.location.b;

import com.xiaomi.mipush.sdk.Constants;
import java.util.List;

class f {
    public static String a = null;
    public int b = 0;
    private boolean c = false;
    private String d = "";
    private boolean e = false;
    private double f = 0.0d;
    private double g = 0.0d;

    public f(List<String> list, String str, String str2, String str3) {
        this.d = str3;
        d();
    }

    private boolean a(String str) {
        if (str == null || str.length() <= 8) {
            return false;
        }
        int i = 0;
        for (int i2 = 1; i2 < str.length() - 3; i2++) {
            i ^= str.charAt(i2);
        }
        return Integer.toHexString(i).equalsIgnoreCase(str.substring(str.length() + -2, str.length()));
    }

    private void d() {
        int i = 0;
        if (a(this.d)) {
            String substring = this.d.substring(0, this.d.length() - 3);
            int i2 = 0;
            while (i < substring.length()) {
                if (substring.charAt(i) == ',') {
                    i2++;
                }
                i++;
            }
            String[] split = substring.split(Constants.ACCEPT_TIME_SEPARATOR_SP, i2 + 1);
            if (split.length >= 6) {
                if (!(split[2].equals("") || split[split.length - 3].equals("") || split[split.length - 2].equals("") || split[split.length - 1].equals(""))) {
                    try {
                        this.f = Double.valueOf(split[split.length - 3]).doubleValue();
                        this.g = Double.valueOf(split[split.length - 2]).doubleValue();
                    } catch (Exception e) {
                    }
                    this.e = true;
                }
            } else {
                return;
            }
        }
        this.c = this.e;
    }

    public boolean a() {
        return this.c;
    }

    public double b() {
        return this.f;
    }

    public double c() {
        return this.g;
    }
}
