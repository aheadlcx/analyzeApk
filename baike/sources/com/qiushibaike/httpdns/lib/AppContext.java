package com.qiushibaike.httpdns.lib;

import android.content.Context;
import java.util.List;

public class AppContext {
    private static Context a;

    public static Context getContext() {
        return a;
    }

    static void a(Context context) {
        a = context.getApplicationContext();
    }

    public static boolean isDebug() {
        return GlobalConfig.isDebug();
    }

    public static void setDebug(boolean z) {
        GlobalConfig.setDebug(z);
    }

    public static boolean isHttpDnsEnable() {
        return GlobalConfig.isHttpDnsEnable();
    }

    public static void setHttpDnsEnable(boolean z) {
        GlobalConfig.setHttpDnsEnable(z);
    }

    public static int getTTL() {
        return GlobalConfig.getTTL();
    }

    public static void setTTL(int i) {
        GlobalConfig.setTTL(i);
    }

    public static List<String> getDomainWhiteList() {
        return GlobalConfig.getDomainWhiteList();
    }

    public static boolean addWhite(String str) {
        return GlobalConfig.getDomainWhiteList().add(str);
    }

    public static boolean removeWhite(String str) {
        return GlobalConfig.getDomainWhiteList().remove(str);
    }
}
