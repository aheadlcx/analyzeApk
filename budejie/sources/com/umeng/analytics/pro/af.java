package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class af {
    private static final String a = ".imprint";
    private static final byte[] b = "pbl0".getBytes();
    private static af f;
    private az c;
    private a d = new a();
    private bn e = null;
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

        a(bn bnVar) {
            a(bnVar);
        }

        public void a(bn bnVar) {
            if (bnVar != null) {
                this.a = a(bnVar, "defcon");
                this.b = a(bnVar, x.an);
                this.c = a(bnVar, "codex");
                this.d = a(bnVar, "report_policy");
                this.e = a(bnVar, "report_interval");
                this.f = b(bnVar, "client_test");
                this.g = a(bnVar, "test_report_interval");
                this.h = b(bnVar, "umid");
                this.i = a(bnVar, "integrated_test");
                this.j = a(bnVar, "latent_hours");
                this.k = b(bnVar, x.G);
                this.l = b(bnVar, "domain_p");
                this.m = b(bnVar, "domain_s");
                this.n = b(bnVar, x.Q);
                this.o = b(bnVar, "track_list");
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
            if (this.d == -1 || !ca.a(this.d)) {
                return new int[]{i, i2};
            }
            if (this.e == -1 || this.e < 90 || this.e > 86400) {
                this.e = 90;
            }
            return new int[]{this.d, this.e * 1000};
        }

        public String f(String str) {
            return (this.f == null || !bg.a(this.f)) ? str : this.f;
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

        private int a(bn bnVar, String str) {
            if (bnVar != null) {
                try {
                    if (bnVar.f()) {
                        bo boVar = (bo) bnVar.d().get(str);
                        if (boVar == null || TextUtils.isEmpty(boVar.c())) {
                            return -1;
                        }
                        try {
                            return Integer.parseInt(boVar.c().trim());
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

        private String b(bn bnVar, String str) {
            if (bnVar == null) {
                return null;
            }
            String c;
            try {
                if (!bnVar.f()) {
                    return null;
                }
                bo boVar = (bo) bnVar.d().get(str);
                if (boVar == null || TextUtils.isEmpty(boVar.c())) {
                    return null;
                }
                c = boVar.c();
                return c;
            } catch (Exception e) {
                e.printStackTrace();
                c = null;
            }
        }
    }

    af(Context context) {
        this.g = context;
    }

    public static synchronized af a(Context context) {
        af afVar;
        synchronized (af.class) {
            if (f == null) {
                f = new af(context);
                f.c();
            }
            afVar = f;
        }
        return afVar;
    }

    public void a(az azVar) {
        this.c = azVar;
    }

    public String a(bn bnVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(bnVar.d()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            if (((bo) entry.getValue()).e()) {
                stringBuilder.append(((bo) entry.getValue()).c());
            }
            stringBuilder.append(((bo) entry.getValue()).f());
            stringBuilder.append(((bo) entry.getValue()).i());
        }
        stringBuilder.append(bnVar.b);
        return bw.a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    private boolean c(bn bnVar) {
        if (!bnVar.j().equals(a(bnVar))) {
            return false;
        }
        for (bo boVar : bnVar.d().values()) {
            byte[] a = bt.a(boVar.i());
            byte[] a2 = a(boVar);
            for (int i = 0; i < 4; i++) {
                if (a[i] != a2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(bo boVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(boVar.f());
        byte[] array = allocate.array();
        byte[] bArr = b;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(bn bnVar) {
        String str = null;
        if (bnVar != null && c(bnVar)) {
            Object obj = null;
            synchronized (this) {
                bn d;
                bn bnVar2 = this.e;
                String j = bnVar2 == null ? null : bnVar2.j();
                if (bnVar2 == null) {
                    d = d(bnVar);
                } else {
                    d = a(bnVar2, bnVar);
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

    private bn a(bn bnVar, bn bnVar2) {
        if (bnVar2 != null) {
            Map d = bnVar.d();
            for (Entry entry : bnVar2.d().entrySet()) {
                if (((bo) entry.getValue()).e()) {
                    d.put(entry.getKey(), entry.getValue());
                } else {
                    d.remove(entry.getKey());
                }
            }
            bnVar.a(bnVar2.g());
            bnVar.a(a(bnVar));
        }
        return bnVar;
    }

    private bn d(bn bnVar) {
        Map d = bnVar.d();
        List<String> arrayList = new ArrayList(d.size() / 2);
        for (Entry entry : d.entrySet()) {
            if (!((bo) entry.getValue()).e()) {
                arrayList.add(entry.getKey());
            }
        }
        for (String remove : arrayList) {
            d.remove(remove);
        }
        return bnVar;
    }

    public synchronized bn a() {
        return this.e;
    }

    public a b() {
        return this.d;
    }

    public void c() {
        InputStream openFileInput;
        byte[] b;
        Exception e;
        bn bnVar;
        Throwable th;
        InputStream inputStream = null;
        if (new File(this.g.getFilesDir(), a).exists()) {
            try {
                openFileInput = this.g.openFileInput(a);
                try {
                    b = bw.b(openFileInput);
                    bw.c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        bw.c(openFileInput);
                        if (b == null) {
                            try {
                                bnVar = new bn();
                                new cj().a((cg) bnVar, b);
                                this.e = bnVar;
                                this.d.a(bnVar);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openFileInput;
                        bw.c(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                openFileInput = inputStream;
                e3.printStackTrace();
                bw.c(openFileInput);
                if (b == null) {
                    bnVar = new bn();
                    new cj().a((cg) bnVar, b);
                    this.e = bnVar;
                    this.d.a(bnVar);
                }
            } catch (Throwable th3) {
                th = th3;
                bw.c(inputStream);
                throw th;
            }
            if (b == null) {
                bnVar = new bn();
                new cj().a((cg) bnVar, b);
                this.e = bnVar;
                this.d.a(bnVar);
            }
        }
    }

    public void d() {
        if (this.e != null) {
            try {
                bw.a(new File(this.g.getFilesDir(), a), new cp().a(this.e));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean e() {
        return new File(this.g.getFilesDir(), a).delete();
    }
}
