package com.spriteapp.booklibrary.e;

import com.spriteapp.booklibrary.util.ScreenUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;

public class c {
    public static int a;
    private static volatile c b;

    public static c a() {
        if (b != null) {
            return b;
        }
        c cVar = new c();
        b = cVar;
        return cVar;
    }

    public void a(String str, int i) {
        SharedPreferencesUtil.getInstance().putInt(e(str), i);
    }

    public void a(int i) {
        a("", i);
    }

    public int a(String str) {
        return SharedPreferencesUtil.getInstance().getInt(e(str), ScreenUtil.dpToPxInt(17.0f));
    }

    public int b() {
        return a("");
    }

    private String e(String str) {
        return str + "-readFontSize";
    }

    public synchronized void a(String str, int i, int i2, int i3) {
        SharedPreferencesUtil.getInstance().putInt(d(str), i).putInt(f(str), i2).putInt(g(str), i3);
    }

    public int[] b(String str) {
        int i = SharedPreferencesUtil.getInstance().getInt(d(str), a);
        int i2 = SharedPreferencesUtil.getInstance().getInt(f(str), 0);
        int i3 = SharedPreferencesUtil.getInstance().getInt(g(str), 0);
        return new int[]{i, i2, i3};
    }

    public int b(String str, int i) {
        return SharedPreferencesUtil.getInstance().getInt(d(str), i);
    }

    public void c(String str) {
        SharedPreferencesUtil.getInstance().remove(d(str)).remove(f(str)).remove(g(str));
    }

    public String d(String str) {
        return str + "-chapter";
    }

    private String f(String str) {
        return str + "-startPos";
    }

    private String g(String str) {
        return str + "-endPos";
    }
}
