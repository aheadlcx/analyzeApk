package com.crashlytics.android;

import java.io.File;
import java.util.Comparator;

final class h implements Comparator<File> {
    h() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((File) obj2).getName().compareTo(((File) obj).getName());
    }
}
