package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class q implements FilenameFilter {
    private q() {
    }

    public final boolean accept(File file, String str) {
        return !ba.a.accept(file, str) && ba.d.matcher(str).matches();
    }
}
