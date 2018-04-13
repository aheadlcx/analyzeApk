package qsbk.app.utils;

import java.io.File;
import java.util.Comparator;

class p implements Comparator<File> {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public int compare(File file, File file2) {
        return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
    }
}
