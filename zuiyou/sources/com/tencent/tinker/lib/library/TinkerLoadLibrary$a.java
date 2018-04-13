package com.tencent.tinker.lib.library;

import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.io.File;

final class TinkerLoadLibrary$a {
    private static void b(ClassLoader classLoader, File file) throws Throwable {
        ShareReflectUtil.expandFieldArray(ShareReflectUtil.findField(classLoader, "pathList").get(classLoader), "nativeLibraryDirectories", new File[]{file});
    }
}
