package com.qiniu.android.storage;

import java.io.File;

class a implements KeyGenerator {
    final /* synthetic */ Configuration a;

    a(Configuration configuration) {
        this.a = configuration;
    }

    public String gen(String str, File file) {
        return str + "_._" + new StringBuffer(file.getAbsolutePath()).reverse();
    }
}
