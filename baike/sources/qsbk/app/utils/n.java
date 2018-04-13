package qsbk.app.utils;

import java.io.File;
import java.util.Arrays;
import qsbk.app.utils.FileUtils.Filter;

final class n extends Thread {
    final /* synthetic */ File a;
    final /* synthetic */ Filter b;
    final /* synthetic */ int c;

    n(String str, File file, Filter filter, int i) {
        this.a = file;
        this.b = filter;
        this.c = i;
        super(str);
    }

    public void run() {
        File[] listFiles = this.a.listFiles(new o(this));
        if (listFiles != null && listFiles.length != 0) {
            Arrays.sort(listFiles, new p(this));
            int i = 0;
            for (int length = listFiles.length - 1; length >= 0; length--) {
                File file = listFiles[length];
                if (this.b == null || !this.b.filter(file)) {
                    if (file.isDirectory()) {
                        FileUtils.deleteFile(file);
                    } else if (((long) i) + file.length() > ((long) this.c)) {
                        FileUtils.deleteFile(file);
                    } else {
                        i = (int) (((long) i) + file.length());
                    }
                }
            }
        }
    }
}
