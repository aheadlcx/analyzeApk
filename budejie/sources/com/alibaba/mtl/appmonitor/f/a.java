package com.alibaba.mtl.appmonitor.f;

public class a {
    public static int a(String str) {
        if (b.c(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }
}
