package com.tencent.bugly.beta.utils;

import android.util.Log;
import com.tencent.bugly.proguard.an;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class c {
    private static HashMap<Long, String> u = new HashMap();
    private a a = null;
    private String b = null;
    private long c = 0;
    private long d = 0;
    private HashMap<String, b> e = null;
    private byte[] f = new byte[16];
    private long g = 0;
    private long h = 0;
    private String i = null;
    private long j = 0;
    private long k = 0;
    private long l = 0;
    private long m = 0;
    private long n = 0;
    private long o = 0;
    private long p = 0;
    private long q = 0;
    private long r = 0;
    private long s = 0;
    private long t = 0;

    public static class a {
        private long a = -1;
        private long b = 0;
        private long c = 0;
        private long d = -1;
        private long e = -1;
        private long f = 0;
        private long g = 0;
        private long h = 0;
        private long i = 0;
        private long j = 0;

        public long a() {
            return this.a;
        }

        public long b() {
            return this.d;
        }

        public long c() {
            return this.e;
        }

        public long d() {
            return this.f;
        }

        public long e() {
            return this.j;
        }

        public synchronized void a(long j) {
            this.a = j;
        }

        public synchronized void b(long j) {
            this.b = j;
        }

        public synchronized void c(long j) {
            this.c = j;
        }

        public synchronized void d(long j) {
            this.e = j;
        }

        public synchronized void e(long j) {
            this.d = j;
        }

        public synchronized void f(long j) {
            this.f = j;
        }

        public synchronized void g(long j) {
            this.g = j;
        }

        public synchronized void h(long j) {
            this.h = j;
        }

        public synchronized void i(long j) {
            this.i = j;
        }

        public synchronized void j(long j) {
            this.j = j;
        }
    }

    public static class b {
        private String a = null;
        private long b = -1;
        private long c = -1;
        private long d = 0;
        private long e = 0;

        public long a() {
            return this.c;
        }

        public synchronized void a(String str) {
            this.a = str;
        }

        public synchronized void a(long j) {
            this.b = j;
        }

        public synchronized void b(long j) {
            this.c = j;
        }

        public synchronized void c(long j) {
            this.d = j;
        }

        public synchronized void d(long j) {
            this.e = j;
        }
    }

    public c(String str) {
        this.b = str;
        u.put(Long.valueOf(3), "x86");
        u.put(Long.valueOf(7), "x86");
        u.put(Long.valueOf(8), "mips");
        u.put(Long.valueOf(10), "mips");
        u.put(Long.valueOf(40), "armeabi");
        u.put(Long.valueOf(62), "x86_64");
        u.put(Long.valueOf(183), "arm64-v8a");
    }

    private String b() {
        return this.i;
    }

    private long c() {
        return this.m;
    }

    private long d() {
        return this.s;
    }

    private long e() {
        return this.t;
    }

    public synchronized String a() {
        String str;
        if (f()) {
            String b = b();
            if (!b.equals("armeabi")) {
                str = b;
            } else if (i()) {
                b bVar = (b) this.e.get(".ARM.attributes");
                if (bVar == null) {
                    Log.e("ElfParser", "No .ARM.attributes section in the elf file");
                    str = b;
                } else {
                    str = b.a(this.b, this.d, bVar.a());
                }
            } else {
                Log.e("ElfParser", "Failed to parseElfHeader section table");
                str = b;
            }
        } else {
            Log.e("ElfParser", "Failed to parseElfHeader elf header");
            str = null;
        }
        return str;
    }

    private static String a(long j, long j2) {
        String str = (String) u.get(Long.valueOf(j));
        if (64 == j2 && str.equals("mips")) {
            return "mips64";
        }
        return str;
    }

    private boolean f() {
        if (!l()) {
            return false;
        }
        if (g()) {
            j();
            return true;
        }
        j();
        return false;
    }

    private synchronized boolean g() {
        boolean z = false;
        synchronized (this) {
            if (h()) {
                try {
                    this.g = this.a.g();
                    this.h = this.a.g();
                    this.i = a(this.h, this.c);
                    this.j = this.a.h();
                    long h;
                    if (32 == this.c) {
                        h = this.a.h();
                        this.j = h;
                        this.k = h;
                        this.l = this.a.h();
                        this.m = this.a.h();
                    } else if (64 == this.c) {
                        h = this.a.i();
                        this.j = h;
                        this.k = h;
                        this.l = this.a.i();
                        this.m = this.a.i();
                    } else {
                        Log.e("ElfParser", "File format error");
                    }
                    this.n = this.a.h();
                    this.o = this.a.g();
                    this.p = this.a.g();
                    this.q = this.a.g();
                    this.r = this.a.g();
                    this.s = this.a.g();
                    this.t = this.a.g();
                    z = true;
                } catch (IOException e) {
                    Log.e("ElfParser", e.getMessage());
                }
            } else {
                Log.e("ElfParser", "Faile to parseElfHeader header indent of elf");
            }
        }
        return z;
    }

    private synchronized boolean h() {
        boolean z = false;
        synchronized (this) {
            if (!this.a.a(this.f)) {
                Log.e("ElfParser", "Fail to parseElfHeader elf indentification");
            } else if (a(this.f)) {
                this.c = a(this.f[4]);
                if (0 == this.c) {
                    Log.e("ElfParser", "File format error: " + this.f[4]);
                } else {
                    this.d = b(this.f[5]);
                    if (a.a == this.d) {
                        Log.e("ElfParser", "Endian error: " + this.f[5]);
                    } else {
                        this.a.a(this.d);
                        z = true;
                    }
                }
            } else {
                Log.e("ElfParser", "Not a elf file: " + this.b);
            }
        }
        return z;
    }

    private static boolean a(byte[] bArr) {
        if (bArr.length >= 3 && Byte.MAX_VALUE == bArr[0] && (byte) 69 == bArr[1] && (byte) 76 == bArr[2] && (byte) 70 == bArr[3]) {
            return true;
        }
        return false;
    }

    private long a(byte b) {
        if ((byte) 1 == b) {
            return 32;
        }
        if ((byte) 2 == b) {
            return 64;
        }
        return 0;
    }

    private long b(byte b) {
        if ((byte) 1 == b) {
            return a.c;
        }
        if ((byte) 2 == b) {
            return a.b;
        }
        return a.a;
    }

    private synchronized boolean i() {
        boolean z;
        this.e = a(c(), d(), e());
        if (this.e == null) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    private synchronized void j() {
        if (this.a != null) {
            if (this.a.a()) {
                this.a = null;
            }
        }
    }

    private synchronized boolean k() {
        boolean z;
        if (this.a != null) {
            j();
        }
        try {
            this.a = new a(this.b, this.d);
            z = true;
        } catch (Exception e) {
            Log.e("ElfParser", e.getMessage());
            z = false;
        }
        return z;
    }

    private synchronized boolean l() {
        boolean z;
        if (this.a != null) {
            j();
        }
        try {
            this.a = new a(this.b);
            z = true;
        } catch (Exception e) {
            Log.e("ElfParser", e.getMessage());
            z = false;
        }
        return z;
    }

    private synchronized HashMap<String, b> a(long j, long j2, long j3) {
        HashMap<String, b> hashMap = null;
        synchronized (this) {
            if (!k()) {
                j();
            } else if (this.a.b(j)) {
                hashMap = b(j2, j3);
                j();
            } else {
                j();
            }
        }
        return hashMap;
    }

    private synchronized HashMap<String, b> b(long j, long j2) {
        HashMap<String, b> hashMap;
        synchronized (this) {
            if (j <= 0 || j2 <= 0) {
                an.d("The SO file is invalid or has a shell.", new Object[0]);
                hashMap = null;
            } else {
                Vector vector = new Vector();
                for (int i = 0; ((long) i) < j; i++) {
                    vector.add(m());
                }
                a aVar = (a) vector.get((int) j2);
                an.c("File length = %d", new Object[]{Long.valueOf(new File(this.b).length())});
                if (aVar.c() >= new File(this.b).length()) {
                    an.d("The SO file is invalid or has a shell.", new Object[0]);
                    hashMap = null;
                } else {
                    d dVar = new d(this.b, aVar.c(), aVar.d());
                    HashMap<String, b> hashMap2 = new HashMap();
                    Iterator it = vector.iterator();
                    while (it.hasNext()) {
                        a aVar2 = (a) it.next();
                        String a = dVar.a(aVar2.a());
                        b bVar = new b();
                        bVar.a(a);
                        bVar.a(aVar2.b());
                        bVar.b(aVar2.c());
                        bVar.c(aVar2.d());
                        bVar.d(aVar2.e());
                        hashMap2.put(a, bVar);
                    }
                    dVar.a();
                    hashMap = hashMap2;
                }
            }
        }
        return hashMap;
    }

    private synchronized a m() {
        a aVar = null;
        synchronized (this) {
            a aVar2 = new a();
            try {
                aVar2.a(this.a.h());
                aVar2.b(this.a.h());
                if (32 == this.c) {
                    aVar2.c(this.a.h());
                    aVar2.e(this.a.h());
                    aVar2.d(this.a.h());
                    aVar2.f(this.a.h());
                } else if (64 == this.c) {
                    aVar2.c(this.a.i());
                    aVar2.e(this.a.i());
                    aVar2.d(this.a.i());
                    aVar2.f(this.a.i());
                } else {
                    Log.e("ElfParser", "File format error");
                }
                aVar2.g(this.a.h());
                aVar2.h(this.a.h());
                if (32 == this.c) {
                    aVar2.i(this.a.h());
                    aVar2.j(this.a.h());
                } else if (64 == this.c) {
                    aVar2.i(this.a.i());
                    aVar2.j(this.a.i());
                } else {
                    Log.e("ElfParser", "File format error");
                }
                aVar = aVar2;
            } catch (IOException e) {
                Log.e("ElfParser", e.getMessage());
            }
        }
        return aVar;
    }
}
