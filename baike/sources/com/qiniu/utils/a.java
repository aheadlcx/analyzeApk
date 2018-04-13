package com.qiniu.utils;

import com.qiniu.utils.InputStreamAt.CleanCallBack;
import java.io.File;

final class a implements CleanCallBack {
    final /* synthetic */ File a;

    a(File file) {
        this.a = file;
    }

    public void clean() {
        this.a.delete();
    }
}
