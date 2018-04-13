package com.alibaba.mtl.appmonitor;

import android.text.TextUtils;
import com.alibaba.mtl.appmonitor.a.f;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import com.alibaba.mtl.appmonitor.model.MeasureValueSet;
import com.alibaba.mtl.log.e.i;

public class AppMonitor$Stat {
    public static void setStatisticsInterval(final int i) {
        if (AppMonitor.c()) {
            AppMonitor.a().a(new Runnable() {
                public void run() {
                    try {
                        AppMonitor.f4a.stat_setStatisticsInterval(i);
                    } catch (Exception e) {
                        AppMonitor.b(e);
                    }
                }
            });
        }
    }

    public static void setSampling(final int i) {
        if (AppMonitor.c()) {
            AppMonitor.a().a(new Runnable() {
                public void run() {
                    try {
                        AppMonitor.f4a.stat_setSampling(i);
                    } catch (Exception e) {
                        AppMonitor.b(e);
                    }
                }
            });
        }
    }

    public static boolean checkSampled(String str, String str2) {
        boolean z = false;
        if (AppMonitor.f4a != null) {
            try {
                z = AppMonitor.f4a.stat_checkSampled(str, str2);
            } catch (Exception e) {
                AppMonitor.b(e);
            }
        }
        return z;
    }

    public static void begin(final String str, final String str2, final String str3) {
        if (AppMonitor.c()) {
            AppMonitor.a().a(new Runnable() {
                public void run() {
                    try {
                        AppMonitor.f4a.stat_begin(str, str2, str3);
                    } catch (Exception e) {
                        AppMonitor.b(e);
                    }
                }
            });
        }
    }

    public static void end(final String str, final String str2, final String str3) {
        if (AppMonitor.c()) {
            AppMonitor.a().a(new Runnable() {
                public void run() {
                    try {
                        AppMonitor.f4a.stat_end(str, str2, str3);
                    } catch (Exception e) {
                        AppMonitor.b(e);
                    }
                }
            });
        }
    }

    public static Transaction createTransaction(String str, String str2) {
        return createTransaction(str, str2, null);
    }

    public static Transaction createTransaction(String str, String str2, DimensionValueSet dimensionValueSet) {
        return new Transaction(Integer.valueOf(f.STAT.a()), str, str2, dimensionValueSet);
    }

    public static void commit(String str, String str2, double d) {
        commit(str, str2, null, d);
    }

    public static void commit(String str, String str2, DimensionValueSet dimensionValueSet, double d) {
        if (AppMonitor.c()) {
            final String str3 = str;
            final String str4 = str2;
            final DimensionValueSet dimensionValueSet2 = dimensionValueSet;
            final double d2 = d;
            AppMonitor.a().a(new Runnable() {
                public void run() {
                    try {
                        AppMonitor.f4a.stat_commit2(str3, str4, dimensionValueSet2, d2);
                    } catch (Exception e) {
                        AppMonitor.b(e);
                    }
                }
            });
        }
    }

    public static void commit(String str, String str2, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
        DimensionValueSet dimensionValueSet;
        int i;
        MeasureValueSet measureValueSet;
        i.a(AppMonitor.TAG, "[commit from jni]");
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            dimensionValueSet = null;
        } else {
            DimensionValueSet create = DimensionValueSet.create();
            for (i = 0; i < strArr2.length; i++) {
                create.setValue(strArr[i], strArr2[i]);
            }
            dimensionValueSet = create;
        }
        if (strArr3 == null || strArr4 == null || strArr3.length != strArr4.length) {
            i.a(AppMonitor.TAG, "measure is null ,or lenght not match");
            measureValueSet = null;
        } else {
            MeasureValueSet create2 = MeasureValueSet.create();
            for (i = 0; i < strArr4.length; i++) {
                double d = 0.0d;
                if (!TextUtils.isEmpty(strArr4[i])) {
                    try {
                        d = Double.valueOf(strArr4[i]).doubleValue();
                    } catch (Exception e) {
                        i.a(AppMonitor.TAG, "measure's value cannot convert to double. measurevalue:" + strArr4[i]);
                    }
                }
                create2.setValue(strArr3[i], d);
            }
            measureValueSet = create2;
        }
        commit(str, str2, dimensionValueSet, measureValueSet);
    }

    public static void commit(final String str, final String str2, final DimensionValueSet dimensionValueSet, final MeasureValueSet measureValueSet) {
        if (AppMonitor.c()) {
            AppMonitor.a().a(new Runnable() {
                public void run() {
                    try {
                        AppMonitor.f4a.stat_commit3(str, str2, dimensionValueSet, measureValueSet);
                    } catch (Exception e) {
                        AppMonitor.b(e);
                    }
                }
            });
        }
    }
}
