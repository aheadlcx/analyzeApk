package okhttp3.internal.d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import okio.k;
import okio.q;
import okio.r;

public interface a {
    public static final a a = new a() {
        public r a(File file) throws FileNotFoundException {
            return k.a(file);
        }

        public q b(File file) throws FileNotFoundException {
            try {
                return k.b(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return k.b(file);
            }
        }

        public q c(File file) throws FileNotFoundException {
            try {
                return k.c(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return k.c(file);
            }
        }

        public void d(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
            }
        }

        public boolean e(File file) {
            return file.exists();
        }

        public long f(File file) {
            return file.length();
        }

        public void a(File file, File file2) throws IOException {
            d(file2);
            if (!file.renameTo(file2)) {
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }

        public void g(File file) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    g(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }
    };

    r a(File file) throws FileNotFoundException;

    void a(File file, File file2) throws IOException;

    q b(File file) throws FileNotFoundException;

    q c(File file) throws FileNotFoundException;

    void d(File file) throws IOException;

    boolean e(File file);

    long f(File file);

    void g(File file) throws IOException;
}
