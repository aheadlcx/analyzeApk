package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class k implements FilenameFilter {
    k() {
    }

    public final boolean accept(File file, String str) {
        return ba.d.matcher(str).matches();
    }
}
