package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.xiaomi.channel.commonutils.misc.b;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;

public class ah {
    private static volatile ah b;
    protected SharedPreferences a;

    private ah(Context context) {
        this.a = context.getSharedPreferences(MiPushClient.PREF_EXTRA, 0);
    }

    public static ah a(Context context) {
        if (b == null) {
            synchronized (ah.class) {
                if (b == null) {
                    b = new ah(context);
                }
            }
        }
        return b;
    }

    private String a(int i) {
        return "normal_oc_" + i;
    }

    private void a(Editor editor, Pair<Integer, Object> pair, String str) {
        if (pair.second instanceof Integer) {
            editor.putInt(str, ((Integer) pair.second).intValue());
        } else if (pair.second instanceof Long) {
            editor.putLong(str, ((Long) pair.second).longValue());
        } else if (pair.second instanceof String) {
            editor.putString(str, (String) pair.second);
        } else if (pair.second instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) pair.second).booleanValue());
        }
    }

    private String b(int i) {
        return "custom_oc_" + i;
    }

    public int a(int i, int i2) {
        String b = b(i);
        if (this.a.contains(b)) {
            return this.a.getInt(b, 0);
        }
        b = a(i);
        return this.a.contains(b) ? this.a.getInt(b, 0) : i2;
    }

    public String a(int i, String str) {
        String b = b(i);
        if (this.a.contains(b)) {
            return this.a.getString(b, null);
        }
        b = a(i);
        return this.a.contains(b) ? this.a.getString(b, null) : str;
    }

    public void a(List<Pair<Integer, Object>> list) {
        if (!b.a(list)) {
            Editor edit = this.a.edit();
            for (Pair pair : list) {
                if (!(pair.first == null || pair.second == null)) {
                    a(edit, pair, a(((Integer) pair.first).intValue()));
                }
            }
            edit.commit();
        }
    }

    public boolean a(int i, boolean z) {
        String b = b(i);
        if (this.a.contains(b)) {
            return this.a.getBoolean(b, false);
        }
        b = a(i);
        return this.a.contains(b) ? this.a.getBoolean(b, false) : z;
    }

    public void b(List<Pair<Integer, Object>> list) {
        if (!b.a(list)) {
            Editor edit = this.a.edit();
            for (Pair pair : list) {
                if (pair.first != null) {
                    String b = b(((Integer) pair.first).intValue());
                    if (pair.second == null) {
                        edit.remove(b);
                    } else {
                        a(edit, pair, b);
                    }
                }
            }
            edit.commit();
        }
    }
}
