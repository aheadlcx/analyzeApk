package com.umeng.analytics.c;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a.b;
import com.umeng.a.i;
import com.umeng.analytics.b.g;
import com.umeng.analytics.d.l;
import com.umeng.analytics.f.e;
import com.umeng.analytics.f.f;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class h {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private static h f;
    private l c;
    private a d = new a();
    private e e = null;
    private Context g;

    public static class a {
        private int a = -1;
        private int b = -1;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private String f = null;
        private int g = -1;
        private String h = null;
        private int i = -1;
        private int j = -1;
        private String k = null;
        private String l = null;
        private String m = null;
        private String n = null;
        private String o = null;

        a() {
        }

        a(e eVar) {
            a(eVar);
        }

        public void a(e eVar) {
            if (eVar != null) {
                this.a = a(eVar, "defcon");
                this.b = a(eVar, g.an);
                this.c = a(eVar, "codex");
                this.d = a(eVar, "report_policy");
                this.e = a(eVar, "report_interval");
                this.f = b(eVar, "client_test");
                this.g = a(eVar, "test_report_interval");
                this.h = b(eVar, "umid");
                this.i = a(eVar, "integrated_test");
                this.j = a(eVar, "latent_hours");
                this.k = b(eVar, g.G);
                this.l = b(eVar, "domain_p");
                this.m = b(eVar, "domain_s");
                this.n = b(eVar, g.Q);
                this.o = b(eVar, "track_list");
            }
        }

        public String a(String str) {
            if (this.n != null) {
                return this.n;
            }
            return str;
        }

        public String b(String str) {
            if (this.o != null) {
                return this.o;
            }
            return str;
        }

        public String c(String str) {
            if (this.m != null) {
                return this.m;
            }
            return str;
        }

        public String d(String str) {
            if (this.l != null) {
                return this.l;
            }
            return str;
        }

        public String e(String str) {
            if (this.k != null) {
                return this.k;
            }
            return str;
        }

        public int a(int i) {
            return (this.a != -1 && this.a <= 3 && this.a >= 0) ? this.a : i;
        }

        public int b(int i) {
            return (this.b != -1 && this.b >= 0 && this.b <= 1800) ? this.b * 1000 : i;
        }

        public int c(int i) {
            if (this.c == 0 || this.c == 1 || this.c == -1) {
                return this.c;
            }
            return i;
        }

        public int[] a(int i, int i2) {
            if (this.d == -1 || !i.a(this.d)) {
                return new int[]{i, i2};
            }
            if (this.e == -1 || this.e < 90 || this.e > 86400) {
                this.e = 90;
            }
            return new int[]{this.d, this.e * 1000};
        }

        public String f(String str) {
            return (this.f == null || !com.umeng.analytics.e.a.a(this.f)) ? str : this.f;
        }

        public int d(int i) {
            return (this.g == -1 || this.g < 90 || this.g > 86400) ? i : this.g * 1000;
        }

        public boolean a() {
            return this.g != -1;
        }

        public String g(String str) {
            return this.h;
        }

        public boolean b() {
            return this.i == 1;
        }

        public long a(long j) {
            return (this.j != -1 && this.j >= 48) ? com.umeng.analytics.a.j * ((long) this.j) : j;
        }

        private int a(e eVar, String str) {
            if (eVar != null) {
                try {
                    if (eVar.f()) {
                        f fVar = (f) eVar.d().get(str);
                        if (fVar == null || TextUtils.isEmpty(fVar.c())) {
                            return -1;
                        }
                        try {
                            return Integer.parseInt(fVar.c().trim());
                        } catch (Exception e) {
                            return -1;
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return -1;
                }
            }
            return -1;
        }

        private String b(e eVar, String str) {
            if (eVar == null) {
                return null;
            }
            String c;
            try {
                if (!eVar.f()) {
                    return null;
                }
                f fVar = (f) eVar.d().get(str);
                if (fVar == null || TextUtils.isEmpty(fVar.c())) {
                    return null;
                }
                c = fVar.c();
                return c;
            } catch (Exception e) {
                e.printStackTrace();
                c = null;
            }
        }
    }

    h(Context context) {
        this.g = context;
    }

    public static synchronized h a(Context context) {
        h hVar;
        synchronized (h.class) {
            if (f == null) {
                f = new h(context);
                f.c();
            }
            hVar = f;
        }
        return hVar;
    }

    public void a(l lVar) {
        this.c = lVar;
    }

    public String a(e eVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(eVar.d()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((f) entry.getValue()).e()) {
                stringBuilder.append(((f) entry.getValue()).c());
            }
            stringBuilder.append(((f) entry.getValue()).f());
            stringBuilder.append(((f) entry.getValue()).i());
        }
        stringBuilder.append(eVar.b);
        return com.umeng.a.e.a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    private boolean c(e eVar) {
        if (!eVar.j().equals(a(eVar))) {
            return false;
        }
        for (f fVar : eVar.d().values()) {
            byte[] a = b.a(fVar.i());
            byte[] a2 = a(fVar);
            for (int i = 0; i < 4; i++) {
                if (a[i] != a2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(f fVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(fVar.f());
        byte[] array = allocate.array();
        byte[] bArr = b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(e eVar) {
        String str = null;
        if (eVar != null && c(eVar)) {
            Object obj = null;
            synchronized (this) {
                e d;
                e eVar2 = this.e;
                String j = eVar2 == null ? null : eVar2.j();
                if (eVar2 == null) {
                    d = d(eVar);
                } else {
                    d = a(eVar2, eVar);
                }
                this.e = d;
                if (d != null) {
                    str = d.j();
                }
                if (!a(j, str)) {
                    obj = 1;
                }
            }
            if (this.e != null && r0 != null) {
                this.d.a(this.e);
                if (this.c != null) {
                    this.c.a(this.d);
                }
            }
        }
    }

    private boolean a(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 != null) {
            return false;
        }
        return true;
    }

    private e a(e eVar, e eVar2) {
        if (eVar2 != null) {
            Map d = eVar.d();
            for (Entry entry : eVar2.d().entrySet()) {
                if (((f) entry.getValue()).e()) {
                    d.put(entry.getKey(), entry.getValue());
                } else {
                    d.remove(entry.getKey());
                }
            }
            eVar.a(eVar2.g());
            eVar.a(a(eVar));
        }
        return eVar;
    }

    private e d(e eVar) {
        Map d = eVar.d();
        List<String> arrayList = new ArrayList(d.size() / 2);
        for (Entry entry : d.entrySet()) {
            if (!((f) entry.getValue()).e()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String remove : arrayList) {
            d.remove(remove);
        }
        return eVar;
    }

    public synchronized e a() {
        return this.e;
    }

    public a b() {
        return this.d;
    }

    public void c() {
        byte[] b;
        Exception e;
        e eVar;
        Throwable th;
        InputStream inputStream = null;
        if (new File(this.g.getFilesDir(), a).exists()) {
            InputStream openFileInput;
            try {
                openFileInput = this.g.openFileInput(a);
                try {
                    b = com.umeng.a.e.b(openFileInput);
                    com.umeng.a.e.c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        com.umeng.a.e.c(openFileInput);
                        if (b == null) {
                            try {
                                eVar = new e();
                                new a.a.a.e().a(eVar, b);
                                this.e = eVar;
                                this.d.a(eVar);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openFileInput;
                        com.umeng.a.e.c(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                openFileInput = inputStream;
                e3.printStackTrace();
                com.umeng.a.e.c(openFileInput);
                if (b == null) {
                    eVar = new e();
                    new a.a.a.e().a(eVar, b);
                    this.e = eVar;
                    this.d.a(eVar);
                }
            } catch (Throwable th3) {
                th = th3;
                com.umeng.a.e.c(inputStream);
                throw th;
            }
            if (b == null) {
                eVar = new e();
                new a.a.a.e().a(eVar, b);
                this.e = eVar;
                this.d.a(eVar);
            }
        }
    }

    public void d() {
        if (this.e != null) {
            try {
                com.umeng.a.e.a(new File(this.g.getFilesDir(), a), new a.a.a.g().a(this.e));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean e() {
        return new File(this.g.getFilesDir(), a).delete();
    }
}
