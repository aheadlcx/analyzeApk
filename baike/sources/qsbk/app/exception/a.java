package qsbk.app.exception;

import java.io.File;
import java.io.FilenameFilter;

class a implements FilenameFilter {
    final /* synthetic */ CrashHandler a;

    a(CrashHandler crashHandler) {
        this.a = crashHandler;
    }

    public boolean accept(File file, String str) {
        return str.endsWith(".cr") || str.endsWith(".cr.qbk");
    }
}
