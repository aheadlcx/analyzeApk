package okhttp3.internal.a;

import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.e.e;
import okio.k;
import okio.q;
import okio.r;

public final class d implements Closeable, Flushable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    static final /* synthetic */ boolean m = (!d.class.desiredAssertionStatus());
    final okhttp3.internal.d.a b;
    final File c;
    final int d;
    okio.d e;
    final LinkedHashMap<String, b> f = new LinkedHashMap(0, 0.75f, true);
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
    private final Runnable v = new Runnable(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void run() {
            int i = 1;
            synchronized (this.a) {
                if (this.a.i) {
                    i = 0;
                }
                if ((i | this.a.j) != 0) {
                    return;
                }
                try {
                    this.a.e();
                } catch (IOException e) {
                    this.a.k = true;
                }
                try {
                    if (this.a.c()) {
                        this.a.b();
                        this.a.g = 0;
                    }
                } catch (IOException e2) {
                    this.a.l = true;
                    this.a.e = k.a(k.a());
                }
            }
        }
    };

    public final class a {
        final b a;
        final boolean[] b;
        final /* synthetic */ d c;
        private boolean d;

        a(d dVar, b bVar) {
            this.c = dVar;
            this.a = bVar;
            this.b = bVar.e ? null : new boolean[dVar.d];
        }

        void a() {
            if (this.a.f == this) {
                for (int i = 0; i < this.c.d; i++) {
                    try {
                        this.c.b.d(this.a.d[i]);
                    } catch (IOException e) {
                    }
                }
                this.a.f = null;
            }
        }

        public q a(int i) {
            q a;
            synchronized (this.c) {
                if (this.d) {
                    throw new IllegalStateException();
                } else if (this.a.f != this) {
                    a = k.a();
                } else {
                    if (!this.a.e) {
                        this.b[i] = true;
                    }
                    try {
                        a = new e(this, this.c.b.b(this.a.d[i])) {
                            final /* synthetic */ a a;

                            protected void a(IOException iOException) {
                                synchronized (this.a.c) {
                                    this.a.a();
                                }
                            }
                        };
                    } catch (FileNotFoundException e) {
                        a = k.a();
                    }
                }
            }
            return a;
        }

        public void b() throws IOException {
            synchronized (this.c) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.a.f == this) {
                    this.c.a(this, true);
                }
                this.d = true;
            }
        }

        public void c() throws IOException {
            synchronized (this.c) {
                if (this.d) {
                    throw new IllegalStateException();
                }
                if (this.a.f == this) {
                    this.c.a(this, false);
                }
                this.d = true;
            }
        }
    }

    private final class b {
        final String a;
        final long[] b;
        final File[] c;
        final File[] d;
        boolean e;
        a f;
        long g;
        final /* synthetic */ d h;

        b(d dVar, String str) {
            this.h = dVar;
            this.a = str;
            this.b = new long[dVar.d];
            this.c = new File[dVar.d];
            this.d = new File[dVar.d];
            StringBuilder append = new StringBuilder(str).append('.');
            int length = append.length();
            for (int i = 0; i < dVar.d; i++) {
                append.append(i);
                this.c[i] = new File(dVar.c, append.toString());
                append.append(FileType.TEMP);
                this.d[i] = new File(dVar.c, append.toString());
                append.setLength(length);
            }
        }

        void a(String[] strArr) throws IOException {
            if (strArr.length != this.h.d) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        void a(okio.d dVar) throws IOException {
            for (long k : this.b) {
                dVar.i(32).k(k);
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        c a() {
            int i = 0;
            if (Thread.holdsLock(this.h)) {
                r[] rVarArr = new r[this.h.d];
                long[] jArr = (long[]) this.b.clone();
                int i2 = 0;
                while (i2 < this.h.d) {
                    try {
                        rVarArr[i2] = this.h.b.a(this.c[i2]);
                        i2++;
                    } catch (FileNotFoundException e) {
                        while (i < this.h.d && rVarArr[i] != null) {
                            okhttp3.internal.c.a(rVarArr[i]);
                            i++;
                        }
                        try {
                            this.h.a(this);
                        } catch (IOException e2) {
                        }
                        return null;
                    }
                }
                return new c(this.h, this.a, this.g, rVarArr, jArr);
            }
            throw new AssertionError();
        }
    }

    public final class c implements Closeable {
        final /* synthetic */ d a;
        private final String b;
        private final long c;
        private final r[] d;
        private final long[] e;

        c(d dVar, String str, long j, r[] rVarArr, long[] jArr) {
            this.a = dVar;
            this.b = str;
            this.c = j;
            this.d = rVarArr;
            this.e = jArr;
        }

        public a a() throws IOException {
            return this.a.a(this.b, this.c);
        }

        public r a(int i) {
            return this.d[i];
        }

        public void close() {
            for (Closeable a : this.d) {
                okhttp3.internal.c.a(a);
            }
        }
    }

    d(okhttp3.internal.d.a aVar, File file, int i, int i2, long j, Executor executor) {
        this.b = aVar;
        this.c = file;
        this.q = i;
        this.n = new File(file, "journal");
        this.o = new File(file, "journal.tmp");
        this.p = new File(file, "journal.bkp");
        this.d = i2;
        this.r = j;
        this.u = executor;
    }

    public synchronized void a() throws IOException {
        if (!m && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (!this.i) {
            if (this.b.e(this.p)) {
                if (this.b.e(this.n)) {
                    this.b.d(this.p);
                } else {
                    this.b.a(this.p, this.n);
                }
            }
            if (this.b.e(this.n)) {
                try {
                    h();
                    j();
                    this.i = true;
                } catch (Throwable e) {
                    e.b().a(5, "DiskLruCache " + this.c + " is corrupt: " + e.getMessage() + ", removing", e);
                    f();
                    this.j = false;
                } catch (Throwable th) {
                    this.j = false;
                }
            }
            b();
            this.i = true;
        }
    }

    public static d a(okhttp3.internal.d.a aVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            return new d(aVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), okhttp3.internal.c.a("OkHttp DiskLruCache", true)));
        }
    }

    private void h() throws IOException {
        int i;
        Closeable a = k.a(this.b.a(this.n));
        try {
            String r = a.r();
            String r2 = a.r();
            String r3 = a.r();
            String r4 = a.r();
            String r5 = a.r();
            if ("libcore.io.DiskLruCache".equals(r) && "1".equals(r2) && Integer.toString(this.q).equals(r3) && Integer.toString(this.d).equals(r4) && "".equals(r5)) {
                i = 0;
                while (true) {
                    d(a.r());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + r + ", " + r2 + ", " + r4 + ", " + r5 + "]");
            }
        } catch (EOFException e) {
            this.g = i - this.f.size();
            if (a.f()) {
                this.e = i();
            } else {
                b();
            }
            okhttp3.internal.c.a(a);
        } catch (Throwable th) {
            okhttp3.internal.c.a(a);
        }
    }

    private okio.d i() throws FileNotFoundException {
        return k.a(new e(this, this.b.c(this.n)) {
            static final /* synthetic */ boolean a = (!d.class.desiredAssertionStatus());
            final /* synthetic */ d b;

            protected void a(IOException iOException) {
                if (a || Thread.holdsLock(this.b)) {
                    this.b.h = true;
                    return;
                }
                throw new AssertionError();
            }
        });
    }

    private void d(String str) throws IOException {
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
        b bVar = (b) this.f.get(str2);
        if (bVar == null) {
            bVar = new b(this, str2);
            this.f.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.e = true;
            bVar.f = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.f = new a(this, bVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void j() throws IOException {
        this.b.d(this.o);
        Iterator it = this.f.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.f == null) {
                for (i = 0; i < this.d; i++) {
                    this.s += bVar.b[i];
                }
            } else {
                bVar.f = null;
                for (i = 0; i < this.d; i++) {
                    this.b.d(bVar.c[i]);
                    this.b.d(bVar.d[i]);
                }
                it.remove();
            }
        }
    }

    synchronized void b() throws IOException {
        if (this.e != null) {
            this.e.close();
        }
        okio.d a = k.a(this.b.b(this.o));
        try {
            a.b("libcore.io.DiskLruCache").i(10);
            a.b("1").i(10);
            a.k((long) this.q).i(10);
            a.k((long) this.d).i(10);
            a.i(10);
            for (b bVar : this.f.values()) {
                if (bVar.f != null) {
                    a.b("DIRTY").i(32);
                    a.b(bVar.a);
                    a.i(10);
                } else {
                    a.b("CLEAN").i(32);
                    a.b(bVar.a);
                    bVar.a(a);
                    a.i(10);
                }
            }
            a.close();
            if (this.b.e(this.n)) {
                this.b.a(this.n, this.p);
            }
            this.b.a(this.o, this.n);
            this.b.d(this.p);
            this.e = i();
            this.h = false;
            this.l = false;
        } catch (Throwable th) {
            a.close();
        }
    }

    public synchronized c a(String str) throws IOException {
        c cVar;
        a();
        k();
        e(str);
        b bVar = (b) this.f.get(str);
        if (bVar == null || !bVar.e) {
            cVar = null;
        } else {
            cVar = bVar.a();
            if (cVar == null) {
                cVar = null;
            } else {
                this.g++;
                this.e.b("READ").i(32).b(str).i(10);
                if (c()) {
                    this.u.execute(this.v);
                }
            }
        }
        return cVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    synchronized a a(String str, long j) throws IOException {
        a aVar;
        a();
        k();
        e(str);
        b bVar = (b) this.f.get(str);
        if (j == -1 || (bVar != null && bVar.g == j)) {
            if (bVar != null) {
                if (bVar.f != null) {
                    aVar = null;
                }
            }
            if (this.k || this.l) {
                this.u.execute(this.v);
                aVar = null;
            } else {
                this.e.b("DIRTY").i(32).b(str).i(10);
                this.e.flush();
                if (this.h) {
                    aVar = null;
                } else {
                    b bVar2;
                    if (bVar == null) {
                        bVar = new b(this, str);
                        this.f.put(str, bVar);
                        bVar2 = bVar;
                    } else {
                        bVar2 = bVar;
                    }
                    aVar = new a(this, bVar2);
                    bVar2.f = aVar;
                }
            }
        } else {
            aVar = null;
        }
        return aVar;
    }

    synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b bVar = aVar.a;
            if (bVar.f != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!bVar.e) {
                    int i2 = 0;
                    while (i2 < this.d) {
                        if (!aVar.b[i2]) {
                            aVar.c();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!this.b.e(bVar.d[i2])) {
                            aVar.c();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.d) {
                File file = bVar.d[i];
                if (!z) {
                    this.b.d(file);
                } else if (this.b.e(file)) {
                    File file2 = bVar.c[i];
                    this.b.a(file, file2);
                    long j = bVar.b[i];
                    long f = this.b.f(file2);
                    bVar.b[i] = f;
                    this.s = (this.s - j) + f;
                }
                i++;
            }
            this.g++;
            bVar.f = null;
            if ((bVar.e | z) != 0) {
                bVar.e = true;
                this.e.b("CLEAN").i(32);
                this.e.b(bVar.a);
                bVar.a(this.e);
                this.e.i(10);
                if (z) {
                    long j2 = this.t;
                    this.t = 1 + j2;
                    bVar.g = j2;
                }
            } else {
                this.f.remove(bVar.a);
                this.e.b("REMOVE").i(32);
                this.e.b(bVar.a);
                this.e.i(10);
            }
            this.e.flush();
            if (this.s > this.r || c()) {
                this.u.execute(this.v);
            }
        }
    }

    boolean c() {
        return this.g >= ErrorCode.SERVER_SESSIONSTATUS && this.g >= this.f.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        a();
        k();
        e(str);
        b bVar = (b) this.f.get(str);
        if (bVar == null) {
            z = false;
        } else {
            z = a(bVar);
            if (z && this.s <= this.r) {
                this.k = false;
            }
        }
        return z;
    }

    boolean a(b bVar) throws IOException {
        if (bVar.f != null) {
            bVar.f.a();
        }
        for (int i = 0; i < this.d; i++) {
            this.b.d(bVar.c[i]);
            this.s -= bVar.b[i];
            bVar.b[i] = 0;
        }
        this.g++;
        this.e.b("REMOVE").i(32).b(bVar.a).i(10);
        this.f.remove(bVar.a);
        if (c()) {
            this.u.execute(this.v);
        }
        return true;
    }

    public synchronized boolean d() {
        return this.j;
    }

    private synchronized void k() {
        if (d()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.i) {
            k();
            e();
            this.e.flush();
        }
    }

    public synchronized void close() throws IOException {
        if (!this.i || this.j) {
            this.j = true;
        } else {
            for (b bVar : (b[]) this.f.values().toArray(new b[this.f.size()])) {
                if (bVar.f != null) {
                    bVar.f.c();
                }
            }
            e();
            this.e.close();
            this.e = null;
            this.j = true;
        }
    }

    void e() throws IOException {
        while (this.s > this.r) {
            a((b) this.f.values().iterator().next());
        }
        this.k = false;
    }

    public void f() throws IOException {
        close();
        this.b.g(this.c);
    }

    public synchronized void g() throws IOException {
        synchronized (this) {
            a();
            for (b a : (b[]) this.f.values().toArray(new b[this.f.size()])) {
                a(a);
            }
            this.k = false;
        }
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }
}
