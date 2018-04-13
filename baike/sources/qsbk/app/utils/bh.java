package qsbk.app.utils;

import java.io.File;
import java.io.FileFilter;

final class bh implements FileFilter {
    bh() {
    }

    public boolean accept(File file) {
        return file.isFile() && file.getName().length() >= 20;
    }
}
