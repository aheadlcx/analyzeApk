package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.pro.r;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

class b implements r {
    public static boolean a = true;
    private Context A;
    private com.umeng.analytics.b b = MobclickAgent.getAgent();
    private a c = null;
    private final int d = 100;
    private final int e = 1;
    private final int f = 0;
    private final int g = -1;
    private final int h = 1;
    private final String i = "level";
    private final String j = "pay";
    private final String k = "buy";
    private final String l = "use";
    private final String m = "bonus";
    private final String n = "item";
    private final String o = "cash";
    private final String p = "coin";
    private final String q = "source";
    private final String r = "amount";
    private final String s = "user_level";
    private final String t = "bonus_source";
    private final String u = "level";
    private final String v = "status";
    private final String w = "duration";
    private final String x = "curtype";
    private final String y = "orderid";
    private final String z = "UMGameAgent.init(Context) should be called before any game api";

    public b() {
        a = true;
    }

    void a(Context context) {
        if (context == null) {
            MLog.e("Context is null, can't init GameAgent");
            return;
        }
        this.A = context.getApplicationContext();
        this.b.a((r) this);
        this.c = new a(this.A);
    }

    void a(boolean z) {
        MLog.d(String.format("Trace sleep time : %b", new Object[]{Boolean.valueOf(z)}));
        a = z;
    }

    void a(String str) {
        try {
            if (this.A == null) {
                MLog.e("UMGameAgent.init(Context) should be called before any game api");
                return;
            }
            this.c.b = str;
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.A);
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("userlevel", str).commit();
            }
        } catch (Throwable th) {
        }
    }

    void b(String str) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            this.c.a = str;
            this.c.a(str);
            HashMap hashMap = new HashMap();
            hashMap.put("level", str);
            hashMap.put("status", Integer.valueOf(0));
            if (this.c.b != null) {
                hashMap.put("user_level", this.c.b);
            }
            this.b.a(this.A, "level", hashMap);
        }
    }

    private void a(String str, int i) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            a b = this.c.b(str);
            if (b != null) {
                long e = b.e();
                if (e <= 0) {
                    MLog.d("level duration is 0");
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("level", str);
                hashMap.put("status", Integer.valueOf(i));
                hashMap.put("duration", Long.valueOf(e));
                if (this.c.b != null) {
                    hashMap.put("user_level", this.c.b);
                }
                this.b.a(this.A, "level", hashMap);
                return;
            }
            MLog.w(String.format("finishLevel(or failLevel) called before startLevel", new Object[0]));
        }
    }

    void c(String str) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            a(str, 1);
        }
    }

    void d(String str) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            a(str, -1);
        }
    }

    void a(double d, double d2, int i) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
            hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
            hashMap.put("source", Integer.valueOf(i));
            if (this.c.b != null) {
                hashMap.put("user_level", this.c.b);
            }
            if (this.c.a != null) {
                hashMap.put("level", this.c.a);
            }
            this.b.a(this.A, "pay", hashMap);
        }
    }

    void a(double d, String str, int i, double d2, int i2) {
        if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        a(d, d2 * ((double) i), i2);
        a(str, i, d2);
    }

    void a(String str, int i, double d) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("item", str);
            hashMap.put("amount", Integer.valueOf(i));
            hashMap.put("coin", Long.valueOf((long) ((((double) i) * d) * 100.0d)));
            if (this.c.b != null) {
                hashMap.put("user_level", this.c.b);
            }
            if (this.c.a != null) {
                hashMap.put("level", this.c.a);
            }
            this.b.a(this.A, "buy", hashMap);
        }
    }

    void b(String str, int i, double d) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("item", str);
            hashMap.put("amount", Integer.valueOf(i));
            hashMap.put("coin", Long.valueOf((long) ((((double) i) * d) * 100.0d)));
            if (this.c.b != null) {
                hashMap.put("user_level", this.c.b);
            }
            if (this.c.a != null) {
                hashMap.put("level", this.c.a);
            }
            this.b.a(this.A, "use", hashMap);
        }
    }

    void a(double d, int i) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("coin", Long.valueOf((long) (100.0d * d)));
            hashMap.put("bonus_source", Integer.valueOf(i));
            if (this.c.b != null) {
                hashMap.put("user_level", this.c.b);
            }
            if (this.c.a != null) {
                hashMap.put("level", this.c.a);
            }
            this.b.a(this.A, "bonus", hashMap);
        }
    }

    void a(String str, int i, double d, int i2) {
        if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
            return;
        }
        a(((double) i) * d, i2);
        a(str, i, d);
    }

    public void a() {
        MLog.d("App resume from background");
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else if (a) {
            this.c.b();
        }
    }

    public void b() {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else if (a) {
            this.c.a();
        }
    }

    void a(double d, String str, double d2, int i, String str2) {
        if (this.A == null) {
            MLog.e("UMGameAgent.init(Context) should be called before any game api");
        } else if (AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_DUM_NORMAL.toValue() || AnalyticsConfig.getVerticalType(this.A) == EScenarioType.E_UM_NORMAL.toValue()) {
            MLog.e("UMGameAgent class is UMGameAgent API, can't be use in no-game scenario. ");
        } else if (d >= 0.0d && d2 >= 0.0d) {
            HashMap hashMap = new HashMap();
            if (!TextUtils.isEmpty(str) && str.length() > 0 && str.length() <= 3) {
                hashMap.put("curtype", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    int length = str2.getBytes("UTF-8").length;
                    if (length > 0 && length <= 1024) {
                        hashMap.put("orderid", str2);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
            hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
            hashMap.put("source", Integer.valueOf(i));
            if (this.c.b != null) {
                hashMap.put("user_level", this.c.b);
            }
            if (this.c.a != null) {
                hashMap.put("level", this.c.a);
            }
            this.b.a(this.A, "pay", hashMap);
        }
    }
}
