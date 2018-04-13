package qsbk.app.exception;

import java.io.File;
import java.io.FilenameFilter;

class b implements FilenameFilter {
    final /* synthetic */ CrashHandler a;

    b(CrashHandler crashHandler) {
        this.a = crashHandler;
    }

    public boolean accept(File file, String str) {
        return str.endsWith(".sent");
    }
}
