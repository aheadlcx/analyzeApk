package com.umeng.analytics.e;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a.g;
import com.umeng.analytics.c.c;
import com.umeng.analytics.c.h;
import com.umeng.analytics.d.l;

public class a implements l {
    private static a i = null;
    private boolean a = false;
    private int b = -1;
    private int c = -1;
    private int d = -1;
    private float e = 0.0f;
    private float f = 0.0f;
    private String g = null;
    private Context h = null;

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (i == null) {
                com.umeng.analytics.c.h.a b = h.a(context).b();
                i = new a(context, b.f(null), b.d(0));
            }
            aVar = i;
        }
        return aVar;
    }

    private a(Context context, String str, int i) {
        this.h = context;
        a(str, i);
    }

    private float b(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    public void a(String str, int i) {
        this.c = i;
        String a = c.a(this.h);
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
            this.a = false;
            return;
        }
        try {
            this.e = b(a, 12);
            this.f = b(a, 6);
            if (str.startsWith("SIG7")) {
                b(str);
            } else if (str.startsWith("FIXED")) {
                c(str);
            }
        } catch (Throwable e) {
            this.a = false;
            g.a("v:" + str, e);
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(",").length == split[5].split(",").length) {
            return true;
        }
        if (!split[0].startsWith("FIXED")) {
            return false;
        }
        int length = split[5].split(",").length;
        int parseInt = Integer.parseInt(split[1]);
        if (length < parseInt || parseInt < 1) {
            return false;
        }
        return true;
    }

    private void b(String str) {
        if (str != null) {
            float floatValue;
            String[] split = str.split("\\|");
            if (split[2].equals("SIG13")) {
                floatValue = Float.valueOf(split[3]).floatValue();
            } else {
                floatValue = 0.0f;
            }
            if (this.e > floatValue) {
                this.a = false;
                return;
            }
            String[] split2;
            float[] fArr = null;
            if (split[0].equals("SIG7")) {
                split2 = split[1].split(",");
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                this.g = "RPT";
                split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i2 = 0; i2 < split2.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split2[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.a = true;
                this.g = "DOM";
                try {
                    split2 = split[5].split(",");
                    iArr = new int[split2.length];
                    for (int i3 = 0; i3 < split2.length; i3++) {
                        iArr[i3] = Integer.valueOf(split2[i3]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            float f = 0.0f;
            int i4 = 0;
            while (i4 < fArr.length) {
                f += fArr[i4];
                if (this.f < f) {
                    break;
                }
                i4++;
            }
            i4 = -1;
            if (i4 != -1) {
                this.a = true;
                this.d = i4 + 1;
                if (iArr != null) {
                    this.b = iArr[i4];
                    return;
                }
                return;
            }
            this.a = false;
        }
    }

    private void c(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            float f = 0.0f;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.e > f) {
                this.a = false;
                return;
            }
            int intValue;
            if (split[0].equals("FIXED")) {
                intValue = Integer.valueOf(split[1]).intValue();
            } else {
                intValue = -1;
            }
            int[] iArr = null;
            String[] split2;
            if (split[4].equals("RPT")) {
                this.g = "RPT";
                split2 = split[5].split(",");
                int[] iArr2 = new int[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    iArr2[i] = Integer.valueOf(split2[i]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.g = "DOM";
                this.a = true;
                try {
                    split2 = split[5].split(",");
                    iArr = new int[split2.length];
                    for (int i2 = 0; i2 < split2.length; i2++) {
                        iArr[i2] = Integer.valueOf(split2[i2]).intValue();
                    }
                } catch (Exception e) {
                }
            }
            if (intValue != -1) {
                this.a = true;
                this.d = intValue;
                if (iArr != null) {
                    this.b = iArr[intValue - 1];
                    return;
                }
                return;
            }
            this.a = false;
        }
    }

    public boolean a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public String e() {
        if (this.a) {
            return String.valueOf(this.d);
        }
        return "error";
    }

    public String f() {
        return this.g;
    }

    public void a(com.umeng.analytics.c.h.a aVar) {
        a(aVar.f(null), aVar.d(0));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" p13:");
        stringBuilder.append(this.e);
        stringBuilder.append(" p07:");
        stringBuilder.append(this.f);
        stringBuilder.append(" policy:");
        stringBuilder.append(this.b);
        stringBuilder.append(" interval:");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }
}
