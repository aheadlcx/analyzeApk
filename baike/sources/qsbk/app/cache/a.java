package qsbk.app.cache;

import java.io.File;
import java.io.FilenameFilter;

final class a implements FilenameFilter {
    a() {
    }

    public boolean accept(File file, String str) {
        return str.startsWith("cache_");
    }
}
