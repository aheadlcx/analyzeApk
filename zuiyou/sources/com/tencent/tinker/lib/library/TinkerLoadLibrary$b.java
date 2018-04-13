package com.tencent.tinker.lib.library;

import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

final class TinkerLoadLibrary$b {
    private static void b(ClassLoader classLoader, File file) throws Throwable {
        Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
        List list = (List) ShareReflectUtil.findField(obj, "nativeLibraryDirectories").get(obj);
        list.add(0, file);
        List list2 = (List) ShareReflectUtil.findField(obj, "systemNativeLibraryDirectories").get(obj);
        Method findMethod = ShareReflectUtil.findMethod(obj, "makePathElements", new Class[]{List.class, File.class, List.class});
        ArrayList arrayList = new ArrayList();
        list.addAll(list2);
        Object[] objArr = (Object[]) findMethod.invoke(obj, new Object[]{list, null, arrayList});
        Field findField = ShareReflectUtil.findField(obj, "nativeLibraryPathElements");
        findField.setAccessible(true);
        findField.set(obj, objArr);
    }
}
