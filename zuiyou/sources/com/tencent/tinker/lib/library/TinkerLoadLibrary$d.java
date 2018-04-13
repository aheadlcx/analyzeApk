package com.tencent.tinker.lib.library;

import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

final class TinkerLoadLibrary$d {
    private static void b(ClassLoader classLoader, File file) throws Throwable {
        String path = file.getPath();
        Field findField = ShareReflectUtil.findField(classLoader, "libPath");
        StringBuilder stringBuilder = new StringBuilder((String) findField.get(classLoader));
        stringBuilder.append(':').append(path);
        findField.set(classLoader, stringBuilder.toString());
        findField = ShareReflectUtil.findField(classLoader, "libraryPathElements");
        List list = (List) findField.get(classLoader);
        list.add(0, path);
        findField.set(classLoader, list);
    }
}
