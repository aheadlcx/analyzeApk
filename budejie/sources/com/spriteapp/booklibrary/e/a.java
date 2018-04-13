package com.spriteapp.booklibrary.e;

import com.spriteapp.booklibrary.util.FileUtils;

public class a {
    public static void a(String str) {
        FileUtils.writeFile(FileUtils.getNativeStoreFile().getAbsolutePath(), str);
    }

    public static String a() {
        return FileUtils.getFileContent(FileUtils.getNativeStorePath());
    }
}
