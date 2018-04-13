package org.apache.commons.io;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

class e {
    private static final boolean a;
    private static Method b;
    private static Method c;
    private static Method d;
    private static Method e;
    private static Method f;
    private static Method g;
    private static Method h;
    private static Object i;
    private static Object j;

    static {
        boolean z = true;
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Class loadClass = contextClassLoader.loadClass("java.nio.file.Files");
            Class loadClass2 = contextClassLoader.loadClass("java.nio.file.Path");
            Class loadClass3 = contextClassLoader.loadClass("java.nio.file.attribute.FileAttribute");
            Class loadClass4 = contextClassLoader.loadClass("java.nio.file.LinkOption");
            b = loadClass.getMethod("isSymbolicLink", new Class[]{loadClass2});
            c = loadClass.getMethod(RequestParameters.SUBRESOURCE_DELETE, new Class[]{loadClass2});
            g = loadClass.getMethod("readSymbolicLink", new Class[]{loadClass2});
            j = Array.newInstance(loadClass3, 0);
            h = loadClass.getMethod("createSymbolicLink", new Class[]{loadClass2, loadClass2, j.getClass()});
            i = Array.newInstance(loadClass4, 0);
            e = loadClass.getMethod("exists", new Class[]{loadClass2, i.getClass()});
            d = File.class.getMethod("toPath", new Class[0]);
            f = loadClass2.getMethod("toFile", new Class[0]);
        } catch (ClassNotFoundException e) {
            z = false;
        } catch (NoSuchMethodException e2) {
            z = false;
        }
        a = z;
    }

    public static boolean a(File file) {
        try {
            Object invoke = d.invoke(file, new Object[0]);
            return ((Boolean) b.invoke(null, new Object[]{invoke})).booleanValue();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean a() {
        return a;
    }
}
