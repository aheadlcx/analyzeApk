package qsbk.app.core.utils;

import java.io.File;
import qsbk.app.core.utils.ACache.ACacheManager;

class b implements Runnable {
    final /* synthetic */ ACacheManager a;

    b(ACacheManager aCacheManager) {
        this.a = aCacheManager;
    }

    public void run() {
        int i = 0;
        File[] listFiles = this.a.a.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                File file = listFiles[i];
                i3 = (int) (((long) i3) + this.a.b(file));
                i2++;
                this.a.g.put(file, Long.valueOf(file.lastModified()));
                i++;
            }
            this.a.c.set((long) i3);
            this.a.d.set(i2);
        }
    }
}
