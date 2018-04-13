package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

class ap implements FileFilter {
    final /* synthetic */ aj a;

    ap(aj ajVar) {
        this.a = ajVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(".jar");
    }
}
