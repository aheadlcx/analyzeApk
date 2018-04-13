package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class ac implements FilenameFilter {
    ac() {
    }

    public final boolean accept(File file, String str) {
        return str.endsWith(".cls") && !str.contains("Session");
    }
}
