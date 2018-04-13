package com.bumptech.glide.a;

import com.ali.auth.third.core.rpc.protocol.RpcException.ErrorCode;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class a implements Closeable {
    final ThreadPoolExecutor a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File b;
    private final File c;
    private final File d;
    private final File e;
    private final int f;
    private long g;
    private final int h;
    private long i = 0;
    private Writer j;
    private final LinkedHashMap<String, b> k = new LinkedHashMap(0, 0.75f, true);
    private int l;
    private long m = 0;
    private final Callable<Void> n = new Callable<Void>(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object call() throws Exception {
            return a();
        }

        public Void a() throws Exception {
            synchronized (this.a) {
                if (this.a.j == null) {
                } else {
                    this.a.g();
                    if (this.a.e()) {
                        this.a.d();
                        this.a.l = 0;
                    }
                }
            }
            return null;
        }
    };

    public final class a {
        final /* synthetic */ a a;
        private final b b;
        private final boolean[] c;
        private boolean d;

        private a(a aVar, b bVar) {
            this.a = aVar;
            this.b = bVar;
            this.c = bVar.f ? null : new boolean[aVar.h];
        }

        public File a(int i) throws IOException {
            File b;
            synchronized (this.a) {
                if (this.b.g != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.f) {
                    this.c[i] = true;
                }
                b = this.b.b(i);
                if (!this.a.b.exists()) {
                    this.a.b.mkdirs();
                }
            }
            return b;
        }

        public void a() throws IOException {
            this.a.a(this, true);
            this.d = true;
        }

        public void b() throws IOException {
            this.a.a(this, false);
        }

        public void c() {
            if (!this.d) {
                try {
                    b();
                } catch (IOException e) {
                }
            }
        }
    }

    private final class b {
        File[] a;
        File[] b;
        final /* synthetic */ a c;
        private final String d;
        private final long[] e;
        private boolean f;
        private a g;
        private long h;

        private b(a aVar, String str) {
            this.c = aVar;
            this.d = str;
            this.e = new long[aVar.h];
            this.a = new File[aVar.h];
            this.b = new File[aVar.h];
            StringBuilder append = new StringBuilder(str).append('.');
            int length = append.length();
            for (int i = 0; i < aVar.h; i++) {
                append.append(i);
                this.a[i] = new File(aVar.b, append.toString());
                append.append(FileType.TEMP);
                this.b[i] = new File(aVar.b, append.toString());
                append.setLength(length);
            }
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.e) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != this.c.h) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.e[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return this.a[i];
        }

        public File b(int i) {
            return this.b[i];
        }
    }

    public final class c {
        final /* synthetic */ a a;
        private final String b;
        private final long c;
        private final long[] d;
        private final File[] e;

        private c(a aVar, String str, long j, File[] fileArr, long[] jArr) {
            this.a = aVar;
            this.b = str;
            this.c = j;
            this.e = fileArr;
            this.d = jArr;
        }

        public File a(int i) {
            return this.e[i];
        }
    }

    private a(File file, int i, int i2, long j) {
        this.b = file;
        this.f = i;
        this.c = new File(file, "journal");
        this.d = new File(file, "journal.tmp");
        this.e = new File(file, "journal.bkp");
        this.h = i2;
        this.g = j;
    }

    public static a a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            a aVar = new a(file, i, i2, j);
            if (aVar.c.exists()) {
                try {
                    aVar.b();
                    aVar.c();
                    return aVar;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    aVar.a();
                }
            }
            file.mkdirs();
            aVar = new a(file, i, i2, j);
            aVar.d();
            return aVar;
        }
    }

    private void b() throws IOException {
        Closeable bVar = new b(new FileInputStream(this.c), c.a);
        int i;
        try {
            String a = bVar.a();
            String a2 = bVar.a();
            String a3 = bVar.a();
            String a4 = bVar.a();
            String a5 = bVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.f).equals(a3) && Integer.toString(this.h).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    d(bVar.a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.l = i - this.k.size();
            if (bVar.b()) {
                d();
            } else {
                this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c, true), c.a));
            }
            c.a(bVar);
        } catch (Throwable th) {
            c.a(bVar);
        }
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
                this.k.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        b bVar = (b) this.k.get(str2);
        if (bVar == null) {
            bVar = new b(str2);
            this.k.put(str2, bVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            bVar.f = true;
            bVar.g = null;
            bVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            bVar.g = new a(bVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void c() throws IOException {
        a(this.d);
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i;
            if (bVar.g == null) {
                for (i = 0; i < this.h; i++) {
                    this.i += bVar.e[i];
                }
            } else {
                bVar.g = null;
                for (i = 0; i < this.h; i++) {
                    a(bVar.a(i));
                    a(bVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void d() throws IOException {
        if (this.j != null) {
            this.j.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d), c.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.k.values()) {
                if (bVar.g != null) {
                    bufferedWriter.write("DIRTY " + bVar.d + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + bVar.d + bVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.c.exists()) {
                a(this.c, this.e, true);
            }
            a(this.d, this.c, false);
            this.e.delete();
            this.j = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c, true), c.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized c a(String str) throws IOException {
        c cVar = null;
        synchronized (this) {
            f();
            b bVar = (b) this.k.get(str);
            if (bVar != null) {
                if (bVar.f) {
                    for (File exists : bVar.a) {
                        if (!exists.exists()) {
                            break;
                        }
                    }
                    this.l++;
                    this.j.append("READ");
                    this.j.append(' ');
                    this.j.append(str);
                    this.j.append('\n');
                    if (e()) {
                        this.a.submit(this.n);
                    }
                    cVar = new c(str, bVar.h, bVar.a, bVar.e);
                }
            }
        }
        return cVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    private synchronized a a(String str, long j) throws IOException {
        a aVar;
        f();
        b bVar = (b) this.k.get(str);
        if (j == -1 || (bVar != null && bVar.h == j)) {
            b bVar2;
            if (bVar == null) {
                bVar = new b(str);
                this.k.put(str, bVar);
                bVar2 = bVar;
            } else if (bVar.g != null) {
                aVar = null;
            } else {
                bVar2 = bVar;
            }
            aVar = new a(bVar2);
            bVar2.g = aVar;
            this.j.append("DIRTY");
            this.j.append(' ');
            this.j.append(str);
            this.j.append('\n');
            this.j.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            b a = aVar.b;
            if (a.g != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.f) {
                    int i2 = 0;
                    while (i2 < this.h) {
                        if (!aVar.c[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.h) {
                File b = a.b(i);
                if (!z) {
                    a(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.e[i];
                    long length = a2.length();
                    a.e[i] = length;
                    this.i = (this.i - j) + length;
                }
                i++;
            }
            this.l++;
            a.g = null;
            if ((a.f | z) != 0) {
                a.f = true;
                this.j.append("CLEAN");
                this.j.append(' ');
                this.j.append(a.d);
                this.j.append(a.a());
                this.j.append('\n');
                if (z) {
                    long j2 = this.m;
                    this.m = 1 + j2;
                    a.h = j2;
                }
            } else {
                this.k.remove(a.d);
                this.j.append("REMOVE");
                this.j.append(' ');
                this.j.append(a.d);
                this.j.append('\n');
            }
            this.j.flush();
            if (this.i > this.g || e()) {
                this.a.submit(this.n);
            }
        }
    }

    private boolean e() {
        return this.l >= ErrorCode.SERVER_SESSIONSTATUS && this.l >= this.k.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            f();
            b bVar = (b) this.k.get(str);
            if (bVar == null || bVar.g != null) {
                z = false;
            } else {
                while (i < this.h) {
                    File a = bVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.i -= bVar.e[i];
                        bVar.e[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.l++;
                this.j.append("REMOVE");
                this.j.append(' ');
                this.j.append(str);
                this.j.append('\n');
                this.k.remove(str);
                if (e()) {
                    this.a.submit(this.n);
                }
                z = true;
            }
        }
        return z;
    }

    private void f() {
        if (this.j == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.j != null) {
            Iterator it = new ArrayList(this.k.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.g != null) {
                    bVar.g.b();
                }
            }
            g();
            this.j.close();
            this.j = null;
        }
    }

    private void g() throws IOException {
        while (this.i > this.g) {
            c((String) ((Entry) this.k.entrySet().iterator().next()).getKey());
        }
    }

    public void a() throws IOException {
        close();
        c.a(this.b);
    }
}
