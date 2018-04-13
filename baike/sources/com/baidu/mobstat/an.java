package com.baidu.mobstat;

import java.io.File;
import java.util.Comparator;

class an implements Comparator<File> {
    final /* synthetic */ al a;

    an(al alVar) {
        this.a = alVar;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        return (int) (file2.lastModified() - file.lastModified());
    }
}
