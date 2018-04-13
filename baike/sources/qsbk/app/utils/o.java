package qsbk.app.utils;

import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import java.io.FilenameFilter;

class o implements FilenameFilter {
    final /* synthetic */ n a;

    o(n nVar) {
        this.a = nVar;
    }

    public boolean accept(File file, String str) {
        return !str.endsWith(FileType.TEMP);
    }
}
