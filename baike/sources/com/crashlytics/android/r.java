package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class r implements FilenameFilter {
    private final String a;

    public r(String str) {
        this.a = str;
    }

    public final boolean accept(File file, String str) {
        return str.contains(this.a) && !str.endsWith(".cls_temp");
    }
}
