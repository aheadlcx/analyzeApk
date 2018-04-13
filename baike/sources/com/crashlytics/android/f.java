package com.crashlytics.android;

import java.io.File;
import java.io.FilenameFilter;

final class f implements FilenameFilter {
    private /* synthetic */ String a;

    f(ba baVar, String str) {
        this.a = str;
    }

    public final boolean accept(File file, String str) {
        return str.startsWith(this.a);
    }
}
