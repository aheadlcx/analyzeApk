package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileFilter;

class an implements FileFilter {
    final /* synthetic */ am a;

    an(am amVar) {
        this.a = amVar;
    }

    public boolean accept(File file) {
        return file.getName().endsWith(".dex");
    }
}
