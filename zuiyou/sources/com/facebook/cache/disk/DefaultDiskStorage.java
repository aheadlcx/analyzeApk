package com.facebook.cache.disk;

import android.os.Environment;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.h;
import com.facebook.common.file.FileUtils;
import com.facebook.common.file.FileUtils.CreateDirectoryException;
import com.facebook.common.file.FileUtils.ParentDirNotFoundException;
import com.facebook.common.internal.g;
import com.facebook.common.time.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DefaultDiskStorage implements c {
    static final long a = TimeUnit.MINUTES.toMillis(30);
    private static final Class<?> b = DefaultDiskStorage.class;
    private final File c;
    private final boolean d;
    private final File e;
    private final CacheErrorLogger f;
    private final com.facebook.common.time.a g = c.b();

    private enum FileType {
        CONTENT(".cnt"),
        TEMP(".tmp");
        
        public final String extension;

        private FileType(String str) {
            this.extension = str;
        }

        public static FileType fromExtension(String str) {
            if (".cnt".equals(str)) {
                return CONTENT;
            }
            if (".tmp".equals(str)) {
                return TEMP;
            }
            return null;
        }
    }

    private static class IncompleteFileException extends IOException {
        public final long actual;
        public final long expected;

        public IncompleteFileException(long j, long j2) {
            super("File was not written completely. Expected: " + j + ", found: " + j2);
            this.expected = j;
            this.actual = j2;
        }
    }

    private class a implements com.facebook.common.file.b {
        final /* synthetic */ DefaultDiskStorage a;
        private final List<com.facebook.cache.disk.c.a> b;

        private a(DefaultDiskStorage defaultDiskStorage) {
            this.a = defaultDiskStorage;
            this.b = new ArrayList();
        }

        public void a(File file) {
        }

        public void b(File file) {
            DefaultDiskStorage$c a = this.a.b(file);
            if (a != null && a.a == FileType.CONTENT) {
                this.b.add(new b(a.b, file));
            }
        }

        public void c(File file) {
        }

        public List<com.facebook.cache.disk.c.a> a() {
            return Collections.unmodifiableList(this.b);
        }
    }

    static class b implements com.facebook.cache.disk.c.a {
        private final String a;
        private final com.facebook.a.b b;
        private long c;
        private long d;

        private b(String str, File file) {
            g.a((Object) file);
            this.a = (String) g.a((Object) str);
            this.b = com.facebook.a.b.a(file);
            this.c = -1;
            this.d = -1;
        }

        public String a() {
            return this.a;
        }

        public long b() {
            if (this.d < 0) {
                this.d = this.b.c().lastModified();
            }
            return this.d;
        }

        public com.facebook.a.b c() {
            return this.b;
        }

        public long d() {
            if (this.c < 0) {
                this.c = this.b.b();
            }
            return this.c;
        }
    }

    class d implements com.facebook.cache.disk.c.b {
        final File a;
        final /* synthetic */ DefaultDiskStorage b;
        private final String c;

        public d(DefaultDiskStorage defaultDiskStorage, String str, File file) {
            this.b = defaultDiskStorage;
            this.c = str;
            this.a = file;
        }

        public void a(h hVar, Object obj) throws IOException {
            try {
                OutputStream fileOutputStream = new FileOutputStream(this.a);
                try {
                    OutputStream cVar = new com.facebook.common.internal.c(fileOutputStream);
                    hVar.a(cVar);
                    cVar.flush();
                    long a = cVar.a();
                    if (this.a.length() != a) {
                        throw new IncompleteFileException(a, this.a.length());
                    }
                } finally {
                    fileOutputStream.close();
                }
            } catch (Throwable e) {
                this.b.f.a(CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, DefaultDiskStorage.b, "updateResource", e);
                throw e;
            }
        }

        public com.facebook.a.a a(Object obj) throws IOException {
            File a = this.b.a(this.c);
            try {
                FileUtils.a(this.a, a);
                if (a.exists()) {
                    a.setLastModified(this.b.g.a());
                }
                return com.facebook.a.b.a(a);
            } catch (Throwable e) {
                CacheErrorCategory cacheErrorCategory;
                Throwable th = e;
                Throwable e2 = th.getCause();
                if (e2 == null) {
                    cacheErrorCategory = CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                } else if (e2 instanceof ParentDirNotFoundException) {
                    cacheErrorCategory = CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
                } else if (e2 instanceof FileNotFoundException) {
                    cacheErrorCategory = CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
                } else {
                    cacheErrorCategory = CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
                }
                this.b.f.a(cacheErrorCategory, DefaultDiskStorage.b, "commit", th);
                throw th;
            }
        }

        public boolean a() {
            return !this.a.exists() || this.a.delete();
        }
    }

    private class e implements com.facebook.common.file.b {
        final /* synthetic */ DefaultDiskStorage a;
        private boolean b;

        private e(DefaultDiskStorage defaultDiskStorage) {
            this.a = defaultDiskStorage;
        }

        public void a(File file) {
            if (!this.b && file.equals(this.a.e)) {
                this.b = true;
            }
        }

        public void b(File file) {
            if (!this.b || !d(file)) {
                file.delete();
            }
        }

        public void c(File file) {
            if (!(this.a.c.equals(file) || this.b)) {
                file.delete();
            }
            if (this.b && file.equals(this.a.e)) {
                this.b = false;
            }
        }

        private boolean d(File file) {
            boolean z = false;
            DefaultDiskStorage$c a = this.a.b(file);
            if (a == null) {
                return false;
            }
            if (a.a == FileType.TEMP) {
                return e(file);
            }
            if (a.a == FileType.CONTENT) {
                z = true;
            }
            g.b(z);
            return true;
        }

        private boolean e(File file) {
            return file.lastModified() > this.a.g.a() - DefaultDiskStorage.a;
        }
    }

    public /* synthetic */ Collection d() throws IOException {
        return c();
    }

    public DefaultDiskStorage(File file, int i, CacheErrorLogger cacheErrorLogger) {
        g.a((Object) file);
        this.c = file;
        this.d = a(file, cacheErrorLogger);
        this.e = new File(this.c, a(i));
        this.f = cacheErrorLogger;
        f();
    }

    private static boolean a(File file, CacheErrorLogger cacheErrorLogger) {
        String str = null;
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory == null) {
                return false;
            }
            CharSequence file2 = externalStorageDirectory.toString();
            try {
                str = file.getCanonicalPath();
                if (str.contains(file2)) {
                    return true;
                }
                return false;
            } catch (Throwable e) {
                cacheErrorLogger.a(CacheErrorCategory.OTHER, b, "failed to read folder to check if external: " + str, e);
                return false;
            }
        } catch (Throwable e2) {
            cacheErrorLogger.a(CacheErrorCategory.OTHER, b, "failed to get the external storage directory!", e2);
            return false;
        }
    }

    static String a(int i) {
        return String.format((Locale) null, "%s.ols%d.%d", new Object[]{"v2", Integer.valueOf(100), Integer.valueOf(i)});
    }

    public boolean a() {
        return this.d;
    }

    private void f() {
        Object obj = 1;
        if (this.c.exists()) {
            if (this.e.exists()) {
                obj = null;
            } else {
                com.facebook.common.file.a.b(this.c);
            }
        }
        if (obj != null) {
            try {
                FileUtils.a(this.e);
            } catch (CreateDirectoryException e) {
                this.f.a(CacheErrorCategory.WRITE_CREATE_DIR, b, "version directory could not be created: " + this.e, null);
            }
        }
    }

    File a(String str) {
        return new File(d(str));
    }

    private String b(String str) {
        return this.e + File.separator + String.valueOf(Math.abs(str.hashCode() % 100));
    }

    private File c(String str) {
        return new File(b(str));
    }

    public void b() {
        com.facebook.common.file.a.a(this.c, new e());
    }

    private void a(File file, String str) throws IOException {
        try {
            FileUtils.a(file);
        } catch (Throwable e) {
            this.f.a(CacheErrorCategory.WRITE_CREATE_DIR, b, str, e);
            throw e;
        }
    }

    public com.facebook.cache.disk.c.b a(String str, Object obj) throws IOException {
        DefaultDiskStorage$c defaultDiskStorage$c = new DefaultDiskStorage$c(FileType.TEMP, str, null);
        File c = c(defaultDiskStorage$c.b);
        if (!c.exists()) {
            a(c, "insert");
        }
        try {
            return new d(this, str, defaultDiskStorage$c.a(c));
        } catch (Throwable e) {
            this.f.a(CacheErrorCategory.WRITE_CREATE_TEMPFILE, b, "insert", e);
            throw e;
        }
    }

    public com.facebook.a.a b(String str, Object obj) {
        File a = a(str);
        if (!a.exists()) {
            return null;
        }
        a.setLastModified(this.g.a());
        return com.facebook.a.b.a(a);
    }

    private String d(String str) {
        DefaultDiskStorage$c defaultDiskStorage$c = new DefaultDiskStorage$c(FileType.CONTENT, str, null);
        return defaultDiskStorage$c.a(b(defaultDiskStorage$c.b));
    }

    public long a(com.facebook.cache.disk.c.a aVar) {
        return a(((b) aVar).c().c());
    }

    private long a(File file) {
        if (!file.exists()) {
            return 0;
        }
        return !file.delete() ? -1 : file.length();
    }

    public List<com.facebook.cache.disk.c.a> c() throws IOException {
        Object aVar = new a();
        com.facebook.common.file.a.a(this.e, aVar);
        return aVar.a();
    }

    private DefaultDiskStorage$c b(File file) {
        DefaultDiskStorage$c b = DefaultDiskStorage$c.b(file);
        if (b == null) {
            return null;
        }
        if (!c(b.b).equals(file.getParentFile())) {
            b = null;
        }
        return b;
    }
}
