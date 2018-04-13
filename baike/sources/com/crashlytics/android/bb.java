package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class bb implements FilenameFilter {
    bb() {
    }

    public final boolean accept(File file, String str) {
        return str.length() == 39 && str.endsWith(".cls");
    }
}
