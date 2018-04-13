package com.umeng.commonsdk.stateless;

import java.io.File;
import java.util.Comparator;

final class k implements Comparator<File> {
    k() {
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        long lastModified = file.lastModified() - file2.lastModified();
        if (lastModified > 0) {
            return 1;
        }
        if (lastModified == 0) {
            return 0;
        }
        return -1;
    }
}
