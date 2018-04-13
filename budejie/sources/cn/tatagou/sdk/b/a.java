package cn.tatagou.sdk.b;

import android.content.SharedPreferences;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.util.p;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

public class a {
    protected static SharedPreferences a = null;

    public static boolean getBoolean(String str) {
        if (getSP() != null) {
            return getSP().getBoolean(str, false);
        }
        return false;
    }

    public static synchronized void saveBoolean(String str, boolean z) {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().putBoolean(str, z).commit();
            }
        }
    }

    public static int getInt(String str) {
        if (getSP() != null) {
            return getSP().getInt(str, 0);
        }
        return 0;
    }

    public static synchronized void saveInt(String str, int i) {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().putInt(str, i).commit();
            }
        }
    }

    public static long getLong(String str) {
        if (getSP() != null) {
            return getSP().getLong(str, 0);
        }
        return 0;
    }

    public static synchronized void saveLong(String str, long j) {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().putLong(str, j).commit();
            }
        }
    }

    public static float getFloat(String str) {
        if (getSP() != null) {
            return (float) getSP().getInt(str, 0);
        }
        return 0.0f;
    }

    public static synchronized void saveFloat(String str, float f) {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().putFloat(str, f).commit();
            }
        }
    }

    public static String getStr(String str) {
        if (getSP() != null) {
            return getSP().getString(str, null);
        }
        return null;
    }

    public static synchronized void saveStr(String str, String str2) {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().putString(str, str2).commit();
            }
        }
    }

    public static <T> T getObj(String str, Class<T> cls) {
        String str2 = getStr(str);
        return str2 == null ? null : p.json2Obj(str2, cls);
    }

    public static synchronized <T> void saveObj(String str, T t) {
        synchronized (a.class) {
            saveStr(str, t != null ? JSON.toJSONString(t) : null);
        }
    }

    public static synchronized void deleteKey(String str) {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().remove(str).commit();
            }
        }
    }

    public static void clearUselessKeys() {
        try {
            List<String> arrayList = new ArrayList();
            arrayList.add("openburied");
            arrayList.add("AppCate");
            for (String str : arrayList) {
                if (getSP() != null) {
                    getSP().edit().remove(str).commit();
                }
            }
        } catch (Exception e) {
        }
    }

    public static synchronized void clearDate() {
        synchronized (a.class) {
            if (getSP() != null) {
                getSP().edit().clear().commit();
            }
        }
    }

    public static SharedPreferences getSP() {
        if (a == null && TtgSDK.getContext() != null) {
            a = TtgSDK.getContext().getSharedPreferences("ttjxDB", 0);
        }
        return a;
    }
}
