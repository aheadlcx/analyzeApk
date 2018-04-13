package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a;
import com.umeng.commonsdk.statistics.internal.d;
import com.xiaomi.mipush.sdk.Constants;

public class ABTest implements d {
    private static ABTest i = null;
    private boolean a = false;
    private int b = -1;
    private int c = -1;
    private int d = -1;
    private float e = 0.0f;
    private float f = 0.0f;
    private String g = null;
    private Context h = null;

    public static synchronized ABTest getService(Context context) {
        ABTest aBTest;
        synchronized (ABTest.class) {
            if (i == null) {
                i = new ABTest(context, UMEnvelopeBuild.imprintProperty(context, "client_test", null), Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "test_report_interval", "0")).intValue());
            }
            aBTest = i;
        }
        return aBTest;
    }

    private ABTest(Context context, String str, int i) {
        this.h = context;
        onExperimentChanged(str, i);
    }

    private float a(String str, int i) {
        int i2 = i * 2;
        if (str == null) {
            return 0.0f;
        }
        return ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    public void onExperimentChanged(String str, int i) {
        this.c = i;
        String signature = Envelope.getSignature(this.h);
        if (TextUtils.isEmpty(signature) || TextUtils.isEmpty(str)) {
            this.a = false;
            return;
        }
        try {
            this.e = a(signature, 12);
            this.f = a(signature, 6);
            if (str.startsWith("SIG7")) {
                a(str);
            } else if (str.startsWith("FIXED")) {
                b(str);
            }
        } catch (Throwable e) {
            this.a = false;
            MLog.e("v:" + str, e);
        }
    }

    public static boolean validate(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (split[0].startsWith("SIG7") && split[1].split(Constants.ACCEPT_TIME_SEPARATOR_SP).length == split[5].split(Constants.ACCEPT_TIME_SEPARATOR_SP).length) {
            return true;
        }
        if (!split[0].startsWith("FIXED")) {
            return false;
        }
        int length = split[5].split(Constants.ACCEPT_TIME_SEPARATOR_SP).length;
        int parseInt = Integer.parseInt(split[1]);
        if (length < parseInt || parseInt < 1) {
            return false;
        }
        return true;
    }

    private void a(String str) {
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
                split2 = split[1].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            }
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                this.g = "RPT";
                split2 = split[5].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                int[] iArr2 = new int[split2.length];
                for (int i2 = 0; i2 < split2.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split2[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.a = true;
                this.g = "DOM";
                try {
                    split2 = split[5].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
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

    private void b(String str) {
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
                split2 = split[5].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                int[] iArr2 = new int[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    iArr2[i] = Integer.valueOf(split2[i]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.g = "DOM";
                this.a = true;
                try {
                    split2 = split[5].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
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

    public boolean isInTest() {
        return this.a;
    }

    public int getTestPolicy() {
        return this.b;
    }

    public int getTestInterval() {
        return this.c;
    }

    public int getGroup() {
        return this.d;
    }

    public String getGroupInfo() {
        if (this.a) {
            return String.valueOf(this.d);
        }
        return b.J;
    }

    public String getTestName() {
        return this.g;
    }

    public void onImprintChanged(a aVar) {
        onExperimentChanged(aVar.a("client_test", null), Integer.valueOf(aVar.a("test_report_interval", "0")).intValue());
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
