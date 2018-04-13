package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.v;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public final class CacheManager {
    @Deprecated
    public static boolean cacheDisabled() {
        bi b = bi.b();
        if (b != null && b.c()) {
            return ((Boolean) b.d().c()).booleanValue();
        }
        Object a = v.a("android.webkit.CacheManager", "cacheDisabled");
        return a == null ? false : ((Boolean) a).booleanValue();
    }

    public static InputStream getCacheFile(String str, boolean z) {
        bi b = bi.b();
        return (b == null || !b.c()) ? null : b.d().a(str, z);
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        bi b = bi.b();
        if (b != null && b.c()) {
            return b.d().g();
        }
        try {
            return v.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", new Class[]{String.class, Map.class}, new Object[]{str, map});
        } catch (Exception e) {
            return null;
        }
    }

    @Deprecated
    public static File getCacheFileBaseDir() {
        bi b = bi.b();
        return (b == null || !b.c()) ? (File) v.a("android.webkit.CacheManager", "getCacheFileBaseDir") : (File) b.d().g();
    }
}
