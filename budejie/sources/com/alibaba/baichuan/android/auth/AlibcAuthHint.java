package com.alibaba.baichuan.android.auth;

import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.AlibcContext.Environment;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AlibcAuthHint {
    public static final AlibcAuthHint WANT_ADD_CART = new AlibcAuthHint("1", "24", "添加商品到您的淘宝购物车");
    private static Map a;
    private static Map b;
    private static Map c;
    private String d;
    private String e;
    public String hintInfo;

    private AlibcAuthHint() {
    }

    private AlibcAuthHint(String str, String str2, String str3) {
        this.d = str;
        this.hintInfo = str3;
        this.e = str2;
        synchronized (this) {
            if (a == null) {
                a = new ConcurrentHashMap();
            }
        }
        a.put(getHintID(), this);
    }

    public static synchronized Set getApiAndHint(String str) {
        Set set;
        synchronized (AlibcAuthHint.class) {
            set = b == null ? null : (Set) c.get(str);
        }
        return set;
    }

    public static String getHintInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return "访问您淘宝账号信息的权限(" + str + ")";
        }
        Object obj = a.get(str) == null ? "" : ((AlibcAuthHint) a.get(str)).hintInfo;
        if (TextUtils.isEmpty(obj)) {
            if (b == null) {
                obj = "";
            } else {
                String str2 = (String) b.get(str);
            }
        }
        return TextUtils.isEmpty(obj) ? "访问您淘宝账号信息的权限(" + str + ")" : obj;
    }

    public static synchronized void putApiAndHint(String str, Set set) {
        synchronized (AlibcAuthHint.class) {
            if (c == null) {
                c = new HashMap();
            }
            c.put(str, set);
        }
    }

    public static synchronized void putExpandList(String str, String str2) {
        synchronized (AlibcAuthHint.class) {
            if (b == null) {
                b = new HashMap();
            }
            b.put(str, str2);
        }
    }

    public String getHintID() {
        return AlibcContext.environment == Environment.TEST ? this.e : this.d;
    }
}
