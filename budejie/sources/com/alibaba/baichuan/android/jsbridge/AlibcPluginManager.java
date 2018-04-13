package com.alibaba.baichuan.android.jsbridge;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.baichuan.android.jsbridge.plugin.AlibcApiPlugin;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.c.b.d;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import java.util.HashMap;
import java.util.Map;

public class AlibcPluginManager {
    public static final String KEY_METHOD = "method";
    public static final String KEY_NAME = "name";
    private static final Map a = new HashMap();
    private static final Map b = new HashMap();

    static class a {
        private String a;
        private ClassLoader b;
        private Object c;

        a(String str, ClassLoader classLoader) {
            this.a = str;
            this.b = classLoader;
        }

        public String a() {
            return this.a;
        }

        public void a(Object obj) {
            this.c = obj;
        }

        public ClassLoader b() {
            return this.b;
        }
    }

    public static AlibcApiPlugin createPlugin(String str, Context context, d dVar) {
        a aVar = (a) a.get(str);
        if (aVar == null || aVar.a() == null) {
            return null;
        }
        String a = aVar.a();
        try {
            ClassLoader b = aVar.b();
            Class cls = b == null ? Class.forName(a) : b.loadClass(a);
            if (cls != null && AlibcApiPlugin.class.isAssignableFrom(cls)) {
                AlibcApiPlugin alibcApiPlugin = (AlibcApiPlugin) cls.newInstance();
                if (alibcApiPlugin.paramObj != null) {
                    alibcApiPlugin.initialize(context, dVar, alibcApiPlugin.paramObj);
                    return alibcApiPlugin;
                }
                alibcApiPlugin.initialize(context, dVar);
                return alibcApiPlugin;
            }
        } catch (Exception e) {
            AlibcLogger.e("AlibcPluginManager", "create plugin error: " + str + ". " + e.getMessage());
        }
        if (AlibcContext.isDebuggable()) {
            AlibcLogger.w("AlibcPluginManager", "create plugin failed: " + str);
        }
        return null;
    }

    public static Map getOriginalPlugin(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AlibcLogger.w("AlibcPluginManager", "getOriginalPlugin failed, alias is empty.");
            return null;
        }
        String str3 = (String) b.get(str + "::" + str2);
        if (!TextUtils.isEmpty(str3)) {
            int indexOf = str3.indexOf("::");
            if (indexOf > 0) {
                String substring = str3.substring(0, indexOf);
                String substring2 = str3.substring(indexOf + "::".length());
                Map hashMap = new HashMap();
                hashMap.put("name", substring);
                hashMap.put("method", substring2);
                return hashMap;
            }
        }
        return null;
    }

    public static void registerAlias(String str, String str2, String str3, String str4) {
        if (!a.containsKey(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AlibcLogger.w("AlibcPluginManager", "registerAlias quit, this is no original plugin or alias is invalid.");
        } else if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            b.put(str + "::" + str2, str3 + "::" + str4);
        }
    }

    public static void registerPlugin(String str, Class cls) {
        registerPlugin(str, cls, true);
    }

    public static void registerPlugin(String str, Class cls, boolean z) {
        if (!TextUtils.isEmpty(str) && cls != null) {
            ClassLoader classLoader = null;
            if (z) {
                classLoader = cls.getClassLoader();
            }
            a.put(str, new a(cls.getName(), classLoader));
        }
    }

    public static void registerPluginwithParam(String str, Class cls, Object... objArr) {
        if (!TextUtils.isEmpty(str) && cls != null) {
            a aVar = new a(cls.getName(), cls.getClassLoader());
            if (objArr != null) {
                aVar.a(objArr);
            }
            a.put(str, aVar);
        }
    }

    public static void unregisterAlias(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AlibcLogger.w("AlibcPluginManager", "unregisterAlias quit, alias is invalid.");
        } else {
            b.remove(str + "::" + str2);
        }
    }

    public static void unregisterPlugin(String str) {
        a.remove(str);
    }
}
