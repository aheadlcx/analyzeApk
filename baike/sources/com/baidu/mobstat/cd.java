package com.baidu.mobstat;

import java.io.File;
import java.io.FilenameFilter;

class cd implements FilenameFilter {
    final /* synthetic */ cc a;

    cd(cc ccVar) {
        this.a = ccVar;
    }

    public boolean accept(File file, String str) {
        if (str.startsWith(Config.PREFIX_SEND_DATA)) {
            return true;
        }
        return false;
    }
}
