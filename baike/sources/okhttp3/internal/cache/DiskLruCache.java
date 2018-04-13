package okhttp3.internal.cache;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public final class DiskLruCache implements Closeable, Flushable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    static final /* synthetic */ boolean m = (!DiskLruCache.class.desiredAssertionStatus());
    final FileSystem b;
    final File c;
    final int d;
    BufferedSink e;
    final LinkedHashMap<String, DiskLruCache$a> f = new LinkedHashMap(0, 0.75f, true);
    int g;
    boolean h;
    boolean i;
    boolean j;
    boolean k;
    boolean l;
    private final File n;
    private final File o;
    private final File p;
    private final int q;
    private long r;
    private long s = 0;
    private long t = 0;
    private final Executor u;
    private final Runnable v = new b(this);

    public final class Snapshot implements Closeable {
        final /* synthetic */ DiskLruCache a;
        private final String b;
        private final long c;
        private final Source[] d;
        private final long[] e;

        Snapshot(DiskLruCache diskLruCache, String str, long j, Source[] sourceArr, long[] jArr) {
            this.a = diskLruCache;
            this.b = str;
            this.c = j;
            this.d = sourceArr;
            this.e = jArr;
        }

        public String key() {
            return this.b;
        }

        @Nullable
        public DiskLruCache$Editor edit() throws IOException {
            return this.a.a(this.b, this.c);
        }

        public Source getSource(int i) {
            return this.d[i];
        }

        public long getLength(int i) {
            return this.e[i];
        }

        public void close() {
            for (Closeable closeQuietly : this.d) {
                Util.closeQuietly(closeQuietly);
            }
        }
    }

    DiskLruCache(FileSystem fileSystem, File file, int i, int i2, long j, Executor executor) {
        this.b = fileSystem;
        this.c = file;
        this.q = i;
        this.n = new File(file, "journal");
        this.o = new File(file, "journal.tmp");
        this.p = new File(file, "journal.bkp");
        this.d = i2;
        this.r = j;
        this.u = executor;
    }

    public synchronized void initialize() throws IOException {
        if (!m && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (!this.i) {
            if (this.b.exists(this.p)) {
                if (this.b.exists(this.n)) {
                    this.b.delete(this.p);
                } else {
                    this.b.rename(this.p, this.n);
                }
            }
            if (this.b.exists(this.n)) {
                try {
                    d();
                    f();
                    this.i = true;
                } catch (Throwable e) {
                    Platform.get().log(5, "DiskLruCache " + this.c + " is corrupt: " + e.getMessage() + ", removing", e);
                    delete();
                    this.j = false;
                } catch (Throwable th) {
                    this.j = false;
                }
            }
            a();
            this.i = true;
        }
    }

    public static DiskLruCache create(FileSystem fileSystem, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            return new DiskLruCache(fileSystem, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
        }
    }

    private void d() throws IOException {
        Closeable buffer = Okio.buffer(this.b.source(this.n));
        int i;
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            if ("libcore.io.DiskLruCache".equals(readUtf8LineStrict) && "1".equals(readUtf8LineStrict2) && Integer.toString(this.q).equals(readUtf8LineStrict3) && Integer.toString(this.d).equals(readUtf8LineStrict4) && "".equals(readUtf8LineStrict5)) {
                i = 0;
                while (true) {
                    a(buffer.readUtf8LineStrict());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + "]");
            }
        } catch (EOFException e) {
            this.g = i - this.f.size();
            if (buffer.exhausted()) {
                this.e = e();
            } else {
                a();
            }
            Util.closeQuietly(buffer);
        } catch (Throwable th) {
            Util.closeQuietly(buffer);
        }
    }

    private BufferedSink e() throws FileNotFoundException {
        return Okio.buffer(new c(this, this.b.appendingSink(this.n)));
    }

    private void a(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.f.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        DiskLruCache$a diskLruCache$a = (DiskLruCache$a) this.f.get(str2);
        if (diskLruCache$a == null) {
            diskLruCache$a = new DiskLruCache$a(this, str2);
            this.f.put(str2, diskLruCache$a);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            diskLruCache$a.e = true;
            diskLruCache$a.f = null;
            diskLruCache$a.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            diskLruCache$a.f = new DiskLruCache$Editor(this, diskLruCache$a);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void f() throws IOException {
        this.b.delete(this.o);
        Iterator it = this.f.values().iterator();
        while (it.hasNext()) {
            DiskLruCache$a diskLruCache$a = (DiskLruCache$a) it.next();
            int i;
            if (diskLruCache$a.f == null) {
                for (i = 0; i < this.d; i++) {
                    this.s += diskLruCache$a.b[i];
                }
            } else {
                diskLruCache$a.f = null;
                for (i = 0; i < this.d; i++) {
                    this.b.delete(diskLruCache$a.c[i]);
                    this.b.delete(diskLruCache$a.d[i]);
                }
                it.remove();
            }
        }
    }

    synchronized void a() throws IOException {
        if (this.e != null) {
            this.e.close();
        }
        BufferedSink buffer = Okio.buffer(this.b.sink(this.o));
        try {
            buffer.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
            buffer.writeUtf8("1").writeByte(10);
            buffer.writeDecimalLong((long) this.q).writeByte(10);
            buffer.writeDecimalLong((long) this.d).writeByte(10);
            buffer.writeByte(10);
            for (DiskLruCache$a diskLruCache$a : this.f.values()) {
                if (diskLruCache$a.f != null) {
                    buffer.writeUtf8("DIRTY").writeByte(32);
                    buffer.writeUtf8(diskLruCache$a.a);
                    buffer.writeByte(10);
                } else {
                    buffer.writeUtf8("CLEAN").writeByte(32);
                    buffer.writeUtf8(diskLruCache$a.a);
                    diskLruCache$a.a(buffer);
                    buffer.writeByte(10);
                }
            }
            buffer.close();
            if (this.b.exists(this.n)) {
                this.b.rename(this.n, this.p);
            }
            this.b.rename(this.o, this.n);
            this.b.delete(this.p);
            this.e = e();
            this.h = false;
            this.l = false;
        } catch (Throwable th) {
            buffer.close();
        }
    }

    public synchronized Snapshot get(String str) throws IOException {
        Snapshot snapshot;
        initialize();
        g();
        b(str);
        DiskLruCache$a diskLruCache$a = (DiskLruCache$a) this.f.get(str);
        if (diskLruCache$a == null || !diskLruCache$a.e) {
            snapshot = null;
        } else {
            snapshot = diskLruCache$a.a();
            if (snapshot == null) {
                snapshot = null;
            } else {
                this.g++;
                this.e.writeUtf8("READ").writeByte(32).writeUtf8(str).writeByte(10);
                if (b()) {
                    this.u.execute(this.v);
                }
            }
        }
        return snapshot;
    }

    @Nullable
    public DiskLruCache$Editor edit(String str) throws IOException {
        return a(str, -1);
    }

    synchronized DiskLruCache$Editor a(String str, long j) throws IOException {
        DiskLruCache$Editor diskLruCache$Editor;
        initialize();
        g();
        b(str);
        DiskLruCache$a diskLruCache$a = (DiskLruCache$a) this.f.get(str);
        if (j == -1 || (diskLruCache$a != null && diskLruCache$a.g == j)) {
            if (diskLruCache$a != null) {
                if (diskLruCache$a.f != null) {
                    diskLruCache$Editor = null;
                }
            }
            if (this.k || this.l) {
                this.u.execute(this.v);
                diskLruCache$Editor = null;
            } else {
                this.e.writeUtf8("DIRTY").writeByte(32).writeUtf8(str).writeByte(10);
                this.e.flush();
                if (this.h) {
                    diskLruCache$Editor = null;
                } else {
                    DiskLruCache$a diskLruCache$a2;
                    if (diskLruCache$a == null) {
                        diskLruCache$a = new DiskLruCache$a(this, str);
                        this.f.put(str, diskLruCache$a);
                        diskLruCache$a2 = diskLruCache$a;
                    } else {
                        diskLruCache$a2 = diskLruCache$a;
                    }
                    diskLruCache$Editor = new DiskLruCache$Editor(this, diskLruCache$a2);
                    diskLruCache$a2.f = diskLruCache$Editor;
                }
            }
        } else {
            diskLruCache$Editor = null;
        }
        return diskLruCache$Editor;
    }

    public File getDirectory() {
        return this.c;
    }

    public synchronized long getMaxSize() {
        return this.r;
    }

    public synchronized void setMaxSize(long j) {
        this.r = j;
        if (this.i) {
            this.u.execute(this.v);
        }
    }

    public synchronized long size() throws IOException {
        initialize();
        return this.s;
    }

    synchronized void a(DiskLruCache$Editor diskLruCache$Editor, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            DiskLruCache$a diskLruCache$a = diskLruCache$Editor.a;
            if (diskLruCache$a.f != diskLruCache$Editor) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!diskLruCache$a.e) {
                    int i2 = 0;
                    while (i2 < this.d) {
                        if (!diskLruCache$Editor.b[i2]) {
                            diskLruCache$Editor.abort();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!this.b.exists(diskLruCache$a.d[i2])) {
                            diskLruCache$Editor.abort();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.d) {
                File file = diskLruCache$a.d[i];
                if (!z) {
                    this.b.delete(file);
                } else if (this.b.exists(file)) {
                    File file2 = diskLruCache$a.c[i];
                    this.b.rename(file, file2);
                    long j = diskLruCache$a.b[i];
                    long size = this.b.size(file2);
                    diskLruCache$a.b[i] = size;
                    this.s = (this.s - j) + size;
                }
                i++;
            }
            this.g++;
            diskLruCache$a.f = null;
            if ((diskLruCache$a.e | z) != 0) {
                diskLruCache$a.e = true;
                this.e.writeUtf8("CLEAN").writeByte(32);
                this.e.writeUtf8(diskLruCache$a.a);
                diskLruCache$a.a(this.e);
                this.e.writeByte(10);
                if (z) {
                    long j2 = this.t;
                    this.t = 1 + j2;
                    diskLruCache$a.g = j2;
                }
            } else {
                this.f.remove(diskLruCache$a.a);
                this.e.writeUtf8("REMOVE").writeByte(32);
                this.e.writeUtf8(diskLruCache$a.a);
                this.e.writeByte(10);
            }
            this.e.flush();
            if (this.s > this.r || b()) {
                this.u.execute(this.v);
            }
        }
    }

    boolean b() {
        return this.g >= 2000 && this.g >= this.f.size();
    }

    public synchronized boolean remove(String str) throws IOException {
        boolean z;
        initialize();
        g();
        b(str);
        DiskLruCache$a diskLruCache$a = (DiskLruCache$a) this.f.get(str);
        if (diskLruCache$a == null) {
            z = false;
        } else {
            z = a(diskLruCache$a);
            if (z && this.s <= this.r) {
                this.k = false;
            }
        }
        return z;
    }

    boolean a(DiskLruCache$a diskLruCache$a) throws IOException {
        if (diskLruCache$a.f != null) {
            diskLruCache$a.f.a();
        }
        for (int i = 0; i < this.d; i++) {
            this.b.delete(diskLruCache$a.c[i]);
            this.s -= diskLruCache$a.b[i];
            diskLruCache$a.b[i] = 0;
        }
        this.g++;
        this.e.writeUtf8("REMOVE").writeByte(32).writeUtf8(diskLruCache$a.a).writeByte(10);
        this.f.remove(diskLruCache$a.a);
        if (b()) {
            this.u.execute(this.v);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.j;
    }

    private synchronized void g() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.i) {
            g();
            c();
            this.e.flush();
        }
    }

    public synchronized void close() throws IOException {
        if (!this.i || this.j) {
            this.j = true;
        } else {
            for (DiskLruCache$a diskLruCache$a : (DiskLruCache$a[]) this.f.values().toArray(new DiskLruCache$a[this.f.size()])) {
                if (diskLruCache$a.f != null) {
                    diskLruCache$a.f.abort();
                }
            }
            c();
            this.e.close();
            this.e = null;
            this.j = true;
        }
    }

    void c() throws IOException {
        while (this.s > this.r) {
            a((DiskLruCache$a) this.f.values().iterator().next());
        }
        this.k = false;
    }

    public void delete() throws IOException {
        close();
        this.b.deleteContents(this.c);
    }

    public synchronized void evictAll() throws IOException {
        synchronized (this) {
            initialize();
            for (DiskLruCache$a a : (DiskLruCache$a[]) this.f.values().toArray(new DiskLruCache$a[this.f.size()])) {
                a(a);
            }
            this.k = false;
        }
    }

    private void b(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new d(this);
    }
}
