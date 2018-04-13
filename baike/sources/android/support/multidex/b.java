package android.support.multidex;

import java.io.File;
import java.io.FileFilter;

final class b implements FileFilter {
    final /* synthetic */ String a;

    b(String str) {
        this.a = str;
    }

    public boolean accept(File file) {
        return !file.getName().startsWith(this.a);
    }
}
