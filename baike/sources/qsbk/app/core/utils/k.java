package qsbk.app.core.utils;

import java.io.File;
import java.util.Comparator;

final class k implements Comparator<File> {
    k() {
    }

    public int compare(File file, File file2) {
        long lastModified = file.lastModified() - file2.lastModified();
        if (lastModified > 0) {
            return 1;
        }
        if (lastModified == 0) {
            return 0;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        return true;
    }
}
