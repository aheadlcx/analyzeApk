package com.danikula.videocache.a;

import com.izuiyou.a.a.b;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class e implements a {
    private final ExecutorService a = Executors.newSingleThreadExecutor();

    private class a implements Callable<Void> {
        final /* synthetic */ e a;
        private final File b;

        public /* synthetic */ Object call() throws Exception {
            return a();
        }

        public a(e eVar, File file) {
            this.a = eVar;
            this.b = file;
        }

        public Void a() throws Exception {
            this.a.b(this.b);
            return null;
        }
    }

    protected abstract boolean a(File file, long j, int i);

    public void a(File file) throws IOException {
        this.a.submit(new a(this, file));
    }

    private void b(File file) throws IOException {
        d.c(file);
        a(d.b(file.getParentFile()));
    }

    private void a(List<File> list) {
        long b = b((List) list);
        int size = list.size();
        int i = size;
        for (File file : list) {
            if (!a(file, b, i)) {
                long length = file.length();
                if (file.delete()) {
                    i--;
                    b -= length;
                    b.d("Cache file " + file + " is deleted because it exceeds cache limit");
                    size = i;
                    i = size;
                } else {
                    b.e("Error deleting file " + file + " for trimming cache");
                }
            }
            size = i;
            i = size;
        }
    }

    private long b(List<File> list) {
        long j = 0;
        for (File length : list) {
            j = length.length() + j;
        }
        return j;
    }
}
