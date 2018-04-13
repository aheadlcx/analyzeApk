package com.facebook.common.file;

import java.io.File;

public class a {
    public static void a(File file, b bVar) {
        bVar.a(file);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    a(file2, bVar);
                } else {
                    bVar.b(file2);
                }
            }
        }
        bVar.c(file);
    }

    public static boolean a(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            int i = 0;
            while (i < listFiles.length) {
                boolean b = b(listFiles[i]) & z;
                i++;
                z = b;
            }
        }
        return z;
    }

    public static boolean b(File file) {
        if (file.isDirectory()) {
            a(file);
        }
        return file.delete();
    }
}
