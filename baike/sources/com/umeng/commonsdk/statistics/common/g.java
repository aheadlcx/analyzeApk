package com.umeng.commonsdk.statistics.common;

import com.umeng.commonsdk.statistics.common.d.a;
import java.io.File;
import java.io.FilenameFilter;

class g implements FilenameFilter {
    final /* synthetic */ a a;

    g(a aVar) {
        this.a = aVar;
    }

    public boolean accept(File file, String str) {
        return str.startsWith("um");
    }
}
