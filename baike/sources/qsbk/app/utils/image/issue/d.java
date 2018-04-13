package qsbk.app.utils.image.issue;

import java.io.File;
import java.io.FilenameFilter;

class d implements FilenameFilter {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public boolean accept(File file, String str) {
        return str.endsWith(".sent");
    }
}
