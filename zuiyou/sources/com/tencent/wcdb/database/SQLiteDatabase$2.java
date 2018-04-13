package com.tencent.wcdb.database;

import java.io.File;
import java.io.FileFilter;

class SQLiteDatabase$2 implements FileFilter {
    final /* synthetic */ String a;

    SQLiteDatabase$2(String str) {
        this.a = str;
    }

    public boolean accept(File file) {
        return file.getName().startsWith(this.a);
    }
}
