package com.xiaomi.a;

import com.ixintui.smartlink.a.a;
import dalvik.system.DexClassLoader;

public final class b extends DexClassLoader {
    public b(String str, String str2, String str3, ClassLoader classLoader) {
        super(str, str2, null, classLoader);
    }

    protected final Class loadClass(String str, boolean z) {
        try {
            if (str.equals("com.xiaomi.mipush.sdk.MiPushMessage") || str.equals("com.xiaomi.mipush.sdk.MiPushCommandMessage") || str.equals("com.xiaomi.mipush.sdk.MessageHandleService") || str.equals("com.xiaomi.mipush.sdk.PushMessageHandler") || str.equals("com.xiaomi.mipush.sdk.PushMessageReceiver")) {
                Class findLoadedClass = findLoadedClass(str);
                if (findLoadedClass == null) {
                    findLoadedClass = findClass(str);
                }
                if (z) {
                    resolveClass(findLoadedClass);
                }
                if (findLoadedClass != null) {
                    return findLoadedClass;
                }
            }
            return super.loadClass(str, z);
        } catch (Exception e) {
            a.a(e);
            return null;
        } catch (Exception e2) {
            a.a(e2);
            return null;
        }
    }
}
