package com.crashlytics.android;

import java.io.File;
import java.util.Comparator;

final class j implements Comparator<File> {
    j() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((File) obj).getName().compareTo(((File) obj2).getName());
    }
}
