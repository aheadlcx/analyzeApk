package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class al implements FilenameFilter {
    al() {
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(".cls_temp");
    }
}
