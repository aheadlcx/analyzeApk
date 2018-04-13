package com.qiushibaike.httpdns.lib;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfig {
    private static final List<String> a = new ArrayList();
    private static String b = "";
    private static boolean c = false;
    private static boolean d = true;
    private static int e = 600;
    public static final String sVersion = "0.1";

    static {
        a.add("qiushibaike.com");
        a.add("app-remix.com");
        a.add("werewolf.mobi");
    }

    private GlobalConfig() {
    }

    public static List<String> getDomainWhiteList() {
        return a;
    }

    public static boolean isDebug() {
        return c;
    }

    public static void setDebug(boolean z) {
        c = z;
    }

    public static boolean isHttpDnsEnable() {
        return d;
    }

    public static void setHttpDnsEnable(boolean z) {
        d = z;
    }

    public static String getAppId() {
        return b;
    }

    public static void setAppId(String str) {
        b = str;
    }

    public static int getTTL() {
        return e;
    }

    public static void setTTL(int i) {
        e = i;
    }
}
