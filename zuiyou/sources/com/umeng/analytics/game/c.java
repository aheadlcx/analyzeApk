package com.umeng.analytics.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.umeng.a.f;
import com.umeng.a.g;
import com.umeng.a.h;
import com.umeng.a.j;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.b;
import com.umeng.analytics.d.m;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

class c implements f {
    private b a = MobclickAgent.getAgent();
    private b b = null;
    private final int c = 100;
    private final int d = 1;
    private final int e = 0;
    private final int f = -1;
    private final int g = 1;
    private final String h = "level";
    private final String i = "pay";
    private final String j = "buy";
    private final String k = "use";
    private final String l = "bonus";
    private final String m = "item";
    private final String n = "cash";
    private final String o = "coin";
    private final String p = "source";
    private final String q = "amount";
    private final String r = "user_level";
    private final String s = "bonus_source";
    private final String t = "level";
    private final String u = NotificationCompat.CATEGORY_STATUS;
    private final String v = "duration";
    private final String w = "curtype";
    private final String x = "orderid";
    private final String y = "UMGameAgent.init(Context) should be called before any game api";
    private Context z;

    public c() {
        a.a = true;
    }

    void a(Context context) {
        if (context == null) {
            g.d("Context is null, can't init GameAgent");
            return;
        }
        this.z = context.getApplicationContext();
        this.a.a((f) this);
        this.b = new b(this.z);
        this.a.a(context, 1);
    }

    void a(boolean z) {
        g.a(String.format("Trace sleep time : %b", new Object[]{Boolean.valueOf(z)}));
        a.a = z;
    }

    void a(String str) {
        this.b.b = str;
        SharedPreferences a = m.a(this.z);
        if (a != null) {
            Editor edit = a.edit();
            edit.putString("userlevel", str);
            edit.commit();
        }
    }

    void b(final String str) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        this.b.a = str;
        h.a(new j(this) {
            final /* synthetic */ c b;

            public void a() {
                this.b.b.a(str);
                HashMap hashMap = new HashMap();
                hashMap.put("level", str);
                hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(0));
                if (this.b.b.b != null) {
                    hashMap.put("user_level", this.b.b.b);
                }
                this.b.a.a(this.b.z, "level", hashMap);
            }
        });
    }

    private void a(final String str, final int i) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
        } else {
            h.a(new j(this) {
                final /* synthetic */ c c;

                public void a() {
                    a b = this.c.b.b(str);
                    if (b != null) {
                        long e = b.e();
                        if (e <= 0) {
                            g.a("level duration is 0");
                            return;
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("level", str);
                        hashMap.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
                        hashMap.put("duration", Long.valueOf(e));
                        if (this.c.b.b != null) {
                            hashMap.put("user_level", this.c.b.b);
                        }
                        this.c.a.a(this.c.z, "level", hashMap);
                        return;
                    }
                    g.c(String.format("finishLevel(or failLevel) called before startLevel", new Object[0]));
                }
            });
        }
    }

    void c(String str) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
        } else {
            a(str, 1);
        }
    }

    void d(String str) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
        } else {
            a(str, -1);
        }
    }

    void a(double d, double d2, int i) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("cash", Long.valueOf((long) (d * 100.0d)));
        hashMap.put("coin", Long.valueOf((long) (d2 * 100.0d)));
        hashMap.put("source", Integer.valueOf(i));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "pay", hashMap);
    }

    void a(double d, String str, int i, double d2, int i2) {
        a(d, d2 * ((double) i), i2);
        a(str, i, d2);
    }

    void a(String str, int i, double d) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("item", str);
        hashMap.put("amount", Integer.valueOf(i));
        hashMap.put("coin", Long.valueOf((long) ((((double) i) * d) * 100.0d)));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "buy", hashMap);
    }

    void b(String str, int i, double d) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("item", str);
        hashMap.put("amount", Integer.valueOf(i));
        hashMap.put("coin", Long.valueOf((long) ((((double) i) * d) * 100.0d)));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "use", hashMap);
    }

    void a(double d, int i) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("coin", Long.valueOf((long) (100.0d * d)));
        hashMap.put("bonus_source", Integer.valueOf(i));
        if (this.b.b != null) {
            hashMap.put("user_level", this.b.b);
        }
        if (this.b.a != null) {
            hashMap.put("level", this.b.a);
        }
        this.a.a(this.z, "bonus", hashMap);
    }

    void a(String str, int i, double d, int i2) {
        a(((double) i) * d, i2);
        a(str, i, d);
    }

    public void a() {
        g.a("App resume from background");
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
        } else if (a.a) {
            this.b.b();
        }
    }

    public void b() {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
        } else if (a.a) {
            this.b.a();
        }
    }

    void a(double d, String str, double d2, int i, String str2) {
        if (this.z == null) {
            g.d("UMGameAgent.init(Context) should be called before any game api");
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
            if (this.b.b != null) {
                hashMap.put("user_level", this.b.b);
            }
            if (this.b.a != null) {
                hashMap.put("level", this.b.a);
            }
            this.a.a(this.z, "pay", hashMap);
        }
    }
}
