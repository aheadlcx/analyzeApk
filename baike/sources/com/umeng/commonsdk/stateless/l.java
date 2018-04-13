package com.umeng.commonsdk.stateless;

import java.io.File;
import java.util.Comparator;

final class l implements Comparator<File> {
    l() {
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        if (file != null && file2 != null && file.lastModified() < file2.lastModified()) {
            return -1;
        }
        if (file == null || file2 == null || file.lastModified() != file2.lastModified()) {
            return 1;
        }
        return 0;
    }
}
