package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class s implements FilenameFilter {
    private final String a;

    public s(String str) {
        this.a = str;
    }

    public final boolean accept(File file, String str) {
        if (str.equals(this.a + ".cls") || !str.contains(this.a) || str.endsWith(".cls_temp")) {
            return false;
        }
        return true;
    }
}
