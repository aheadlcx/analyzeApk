package com.crashlytics.android.internal;

import java.io.File;
import java.util.Comparator;

final class bq implements Comparator<File> {
    bq() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return (int) (((File) obj).lastModified() - ((File) obj2).lastModified());
    }
}
