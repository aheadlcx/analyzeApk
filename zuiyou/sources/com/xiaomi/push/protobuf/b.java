package com.xiaomi.push.protobuf;

public final class b {

    public static final class a extends com.google.protobuf.micro.e {
        private boolean a;
        private int b = 0;
        private boolean c;
        private long d = 0;
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private boolean i;
        private String j = "";
        private boolean k;
        private String l = "";
        private boolean m;
        private String n = "";
        private boolean o;
        private int p = 1;
        private boolean q;
        private int r = 0;
        private boolean s;
        private int t = 0;
        private boolean u;
        private String v = "";
        private int w = -1;

        public int a() {
            if (this.w < 0) {
                b();
            }
            return this.w;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public a a(int i) {
            this.a = true;
            this.b = i;
            return this;
        }

        public a a(long j) {
            this.c = true;
            this.d = j;
            return this;
        }

        public a a(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.b(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
            if (r()) {
                cVar.a(7, q());
            }
            if (s()) {
                cVar.a(8, t());
            }
            if (v()) {
                cVar.a(9, u());
            }
            if (x()) {
                cVar.a(10, w());
            }
            if (z()) {
                cVar.a(11, y());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.c(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.d(2, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.b(3, h());
            }
            if (k()) {
                i += com.google.protobuf.micro.c.b(4, j());
            }
            if (m()) {
                i += com.google.protobuf.micro.c.b(5, l());
            }
            if (o()) {
                i += com.google.protobuf.micro.c.b(6, n());
            }
            if (r()) {
                i += com.google.protobuf.micro.c.b(7, q());
            }
            if (s()) {
                i += com.google.protobuf.micro.c.c(8, t());
            }
            if (v()) {
                i += com.google.protobuf.micro.c.c(9, u());
            }
            if (x()) {
                i += com.google.protobuf.micro.c.c(10, w());
            }
            if (z()) {
                i += com.google.protobuf.micro.c.b(11, y());
            }
            this.w = i;
            return i;
        }

        public a b(int i) {
            this.o = true;
            this.p = i;
            return this;
        }

        public a b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.e());
                        continue;
                    case 16:
                        a(bVar.d());
                        continue;
                    case 26:
                        a(bVar.g());
                        continue;
                    case 34:
                        b(bVar.g());
                        continue;
                    case 42:
                        c(bVar.g());
                        continue;
                    case 50:
                        d(bVar.g());
                        continue;
                    case 58:
                        e(bVar.g());
                        continue;
                    case 64:
                        b(bVar.e());
                        continue;
                    case 72:
                        c(bVar.e());
                        continue;
                    case 80:
                        d(bVar.e());
                        continue;
                    case 90:
                        f(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public a b(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public a c(int i) {
            this.q = true;
            this.r = i;
            return this;
        }

        public a c(String str) {
            this.i = true;
            this.j = str;
            return this;
        }

        public int d() {
            return this.b;
        }

        public a d(int i) {
            this.s = true;
            this.t = i;
            return this;
        }

        public a d(String str) {
            this.k = true;
            this.l = str;
            return this;
        }

        public a e(String str) {
            this.m = true;
            this.n = str;
            return this;
        }

        public boolean e() {
            return this.a;
        }

        public long f() {
            return this.d;
        }

        public a f(String str) {
            this.u = true;
            this.v = str;
            return this;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public String l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public String n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }

        public a p() {
            this.k = false;
            this.l = "";
            return this;
        }

        public String q() {
            return this.n;
        }

        public boolean r() {
            return this.m;
        }

        public boolean s() {
            return this.o;
        }

        public int t() {
            return this.p;
        }

        public int u() {
            return this.r;
        }

        public boolean v() {
            return this.q;
        }

        public int w() {
            return this.t;
        }

        public boolean x() {
            return this.s;
        }

        public String y() {
            return this.v;
        }

        public boolean z() {
            return this.u;
        }
    }

    public static final class b extends com.google.protobuf.micro.e {
        private boolean a;
        private boolean b = false;
        private boolean c;
        private int d = 0;
        private boolean e;
        private int f = 0;
        private boolean g;
        private int h = 0;
        private int i = -1;

        public static b b(byte[] bArr) {
            return (b) new b().a(bArr);
        }

        public int a() {
            if (this.i < 0) {
                b();
            }
            return this.i;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public b a(int i) {
            this.c = true;
            this.d = i;
            return this;
        }

        public b a(boolean z) {
            this.a = true;
            this.b = z;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(3, f());
            }
            if (i()) {
                cVar.a(4, h());
            }
            if (k()) {
                cVar.a(5, j());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.c(3, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.c(4, h());
            }
            if (k()) {
                i += com.google.protobuf.micro.c.c(5, j());
            }
            this.i = i;
            return i;
        }

        public b b(int i) {
            this.e = true;
            this.f = i;
            return this;
        }

        public b b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.f());
                        continue;
                    case 24:
                        a(bVar.e());
                        continue;
                    case 32:
                        b(bVar.e());
                        continue;
                    case 40:
                        c(bVar.e());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public b c(int i) {
            this.g = true;
            this.h = i;
            return this;
        }

        public boolean d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public int f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public int h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public int j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }
    }

    public static final class c extends com.google.protobuf.micro.e {
        private boolean a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private boolean i;
        private String j = "";
        private boolean k;
        private String l = "";
        private int m = -1;

        public int a() {
            if (this.m < 0) {
                b();
            }
            return this.m;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public c a(String str) {
            this.a = true;
            this.b = str;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.b(3, h());
            }
            if (k()) {
                i += com.google.protobuf.micro.c.b(4, j());
            }
            if (m()) {
                i += com.google.protobuf.micro.c.b(5, l());
            }
            if (o()) {
                i += com.google.protobuf.micro.c.b(6, n());
            }
            this.m = i;
            return i;
        }

        public c b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.g());
                        continue;
                    case 18:
                        b(bVar.g());
                        continue;
                    case 26:
                        c(bVar.g());
                        continue;
                    case 34:
                        d(bVar.g());
                        continue;
                    case 42:
                        e(bVar.g());
                        continue;
                    case 50:
                        f(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public c b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public c c(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public c d(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public c e(String str) {
            this.i = true;
            this.j = str;
            return this;
        }

        public boolean e() {
            return this.a;
        }

        public c f(String str) {
            this.k = true;
            this.l = str;
            return this;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public String l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public String n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }
    }

    public static final class d extends com.google.protobuf.micro.e {
        private boolean a;
        private boolean b = false;
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private int i = -1;

        public static d b(byte[] bArr) {
            return (d) new d().a(bArr);
        }

        public int a() {
            if (this.i < 0) {
                b();
            }
            return this.i;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public d a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public d a(boolean z) {
            this.a = true;
            this.b = z;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.b(3, h());
            }
            if (k()) {
                i += com.google.protobuf.micro.c.b(4, j());
            }
            this.i = i;
            return i;
        }

        public d b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.f());
                        continue;
                    case 18:
                        a(bVar.g());
                        continue;
                    case 26:
                        b(bVar.g());
                        continue;
                    case 34:
                        c(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public d b(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public d c(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public boolean d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }
    }

    public static final class e extends com.google.protobuf.micro.e {
        private boolean a;
        private int b = 0;
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private boolean g;
        private String h = "";
        private boolean i;
        private int j = 0;
        private boolean k;
        private String l = "";
        private boolean m;
        private String n = "";
        private boolean o;
        private String p = "";
        private boolean q;
        private b r = null;
        private boolean s;
        private int t = 0;
        private int u = -1;

        public int a() {
            if (this.u < 0) {
                b();
            }
            return this.u;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public e a(int i) {
            this.a = true;
            this.b = i;
            return this;
        }

        public e a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.q = true;
            this.r = bVar;
            return this;
        }

        public e a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.b(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
            if (q()) {
                cVar.a(7, p());
            }
            if (s()) {
                cVar.a(8, r());
            }
            if (t()) {
                cVar.a(9, u());
            }
            if (w()) {
                cVar.a(10, v());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.d(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.b(3, h());
            }
            if (k()) {
                i += com.google.protobuf.micro.c.b(4, j());
            }
            if (m()) {
                i += com.google.protobuf.micro.c.c(5, l());
            }
            if (o()) {
                i += com.google.protobuf.micro.c.b(6, n());
            }
            if (q()) {
                i += com.google.protobuf.micro.c.b(7, p());
            }
            if (s()) {
                i += com.google.protobuf.micro.c.b(8, r());
            }
            if (t()) {
                i += com.google.protobuf.micro.c.b(9, u());
            }
            if (w()) {
                i += com.google.protobuf.micro.c.c(10, v());
            }
            this.u = i;
            return i;
        }

        public e b(int i) {
            this.i = true;
            this.j = i;
            return this;
        }

        public e b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.i());
                        continue;
                    case 18:
                        a(bVar.g());
                        continue;
                    case 26:
                        b(bVar.g());
                        continue;
                    case 34:
                        c(bVar.g());
                        continue;
                    case 40:
                        b(bVar.e());
                        continue;
                    case 50:
                        d(bVar.g());
                        continue;
                    case 58:
                        e(bVar.g());
                        continue;
                    case 66:
                        f(bVar.g());
                        continue;
                    case 74:
                        b bVar2 = new b();
                        bVar.a((com.google.protobuf.micro.e) bVar2);
                        a(bVar2);
                        continue;
                    case 80:
                        c(bVar.e());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public e b(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public e c(int i) {
            this.s = true;
            this.t = i;
            return this;
        }

        public e c(String str) {
            this.g = true;
            this.h = str;
            return this;
        }

        public int d() {
            return this.b;
        }

        public e d(String str) {
            this.k = true;
            this.l = str;
            return this;
        }

        public e e(String str) {
            this.m = true;
            this.n = str;
            return this;
        }

        public boolean e() {
            return this.a;
        }

        public e f(String str) {
            this.o = true;
            this.p = str;
            return this;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public String j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public int l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public String n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }

        public String p() {
            return this.n;
        }

        public boolean q() {
            return this.m;
        }

        public String r() {
            return this.p;
        }

        public boolean s() {
            return this.o;
        }

        public boolean t() {
            return this.q;
        }

        public b u() {
            return this.r;
        }

        public int v() {
            return this.t;
        }

        public boolean w() {
            return this.s;
        }
    }

    public static final class f extends com.google.protobuf.micro.e {
        private boolean a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private b f = null;
        private int g = -1;

        public static f b(byte[] bArr) {
            return (f) new f().a(bArr);
        }

        public int a() {
            if (this.g < 0) {
                b();
            }
            return this.g;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public f a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.e = true;
            this.f = bVar;
            return this;
        }

        public f a(String str) {
            this.a = true;
            this.b = str;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (h()) {
                cVar.a(3, i());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            if (h()) {
                i += com.google.protobuf.micro.c.b(3, i());
            }
            this.g = i;
            return i;
        }

        public f b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.g());
                        continue;
                    case 18:
                        b(bVar.g());
                        continue;
                    case 26:
                        b bVar2 = new b();
                        bVar.a((com.google.protobuf.micro.e) bVar2);
                        a(bVar2);
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public f b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public boolean h() {
            return this.e;
        }

        public b i() {
            return this.f;
        }
    }

    public static final class g extends com.google.protobuf.micro.e {
        private boolean a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private String f = "";
        private int g = -1;

        public static g b(byte[] bArr) {
            return (g) new g().a(bArr);
        }

        public int a() {
            if (this.g < 0) {
                b();
            }
            return this.g;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public g a(String str) {
            this.a = true;
            this.b = str;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.b(3, h());
            }
            this.g = i;
            return i;
        }

        public g b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.g());
                        continue;
                    case 18:
                        b(bVar.g());
                        continue;
                    case 26:
                        c(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public g b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public g c(String str) {
            this.e = true;
            this.f = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public String h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }
    }

    public static final class h extends com.google.protobuf.micro.e {
        private boolean a;
        private int b = 0;
        private boolean c;
        private String d = "";
        private int e = -1;

        public static h b(byte[] bArr) {
            return (h) new h().a(bArr);
        }

        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public h a(int i) {
            this.a = true;
            this.b = i;
            return this;
        }

        public h a(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.c(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            this.e = i;
            return i;
        }

        public h b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.e());
                        continue;
                    case 18:
                        a(bVar.g());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public int d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }
    }

    public static final class i extends com.google.protobuf.micro.e {
        private boolean a;
        private com.google.protobuf.micro.a b = com.google.protobuf.micro.a.a;
        private int c = -1;

        public static i b(byte[] bArr) {
            return (i) new i().a(bArr);
        }

        public int a() {
            if (this.c < 0) {
                b();
            }
            return this.c;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public i a(com.google.protobuf.micro.a aVar) {
            this.a = true;
            this.b = aVar;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            this.c = i;
            return i;
        }

        public i b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.h());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public com.google.protobuf.micro.a d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }
    }

    public static final class j extends com.google.protobuf.micro.e {
        private boolean a;
        private com.google.protobuf.micro.a b = com.google.protobuf.micro.a.a;
        private boolean c;
        private b d = null;
        private int e = -1;

        public static j b(byte[] bArr) {
            return (j) new j().a(bArr);
        }

        public int a() {
            if (this.e < 0) {
                b();
            }
            return this.e;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public j a(com.google.protobuf.micro.a aVar) {
            this.a = true;
            this.b = aVar;
            return this;
        }

        public j a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.c = true;
            this.d = bVar;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (f()) {
                cVar.a(2, g());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (f()) {
                i += com.google.protobuf.micro.c.b(2, g());
            }
            this.e = i;
            return i;
        }

        public j b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.h());
                        continue;
                    case 18:
                        b bVar2 = new b();
                        bVar.a((com.google.protobuf.micro.e) bVar2);
                        a(bVar2);
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public com.google.protobuf.micro.a d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public boolean f() {
            return this.c;
        }

        public b g() {
            return this.d;
        }
    }

    public static final class k extends com.google.protobuf.micro.e {
        private boolean a;
        private String b = "";
        private boolean c;
        private String d = "";
        private boolean e;
        private long f = 0;
        private boolean g;
        private long h = 0;
        private boolean i;
        private boolean j = false;
        private boolean k;
        private int l = 0;
        private int m = -1;

        public static k b(byte[] bArr) {
            return (k) new k().a(bArr);
        }

        public int a() {
            if (this.m < 0) {
                b();
            }
            return this.m;
        }

        public /* synthetic */ com.google.protobuf.micro.e a(com.google.protobuf.micro.b bVar) {
            return b(bVar);
        }

        public k a(int i) {
            this.k = true;
            this.l = i;
            return this;
        }

        public k a(long j) {
            this.e = true;
            this.f = j;
            return this;
        }

        public k a(String str) {
            this.a = true;
            this.b = str;
            return this;
        }

        public k a(boolean z) {
            this.i = true;
            this.j = z;
            return this;
        }

        public void a(com.google.protobuf.micro.c cVar) {
            if (e()) {
                cVar.a(1, d());
            }
            if (g()) {
                cVar.a(2, f());
            }
            if (i()) {
                cVar.a(3, h());
            }
            if (k()) {
                cVar.a(4, j());
            }
            if (m()) {
                cVar.a(5, l());
            }
            if (o()) {
                cVar.a(6, n());
            }
        }

        public int b() {
            int i = 0;
            if (e()) {
                i = 0 + com.google.protobuf.micro.c.b(1, d());
            }
            if (g()) {
                i += com.google.protobuf.micro.c.b(2, f());
            }
            if (i()) {
                i += com.google.protobuf.micro.c.c(3, h());
            }
            if (k()) {
                i += com.google.protobuf.micro.c.c(4, j());
            }
            if (m()) {
                i += com.google.protobuf.micro.c.b(5, l());
            }
            if (o()) {
                i += com.google.protobuf.micro.c.c(6, n());
            }
            this.m = i;
            return i;
        }

        public k b(long j) {
            this.g = true;
            this.h = j;
            return this;
        }

        public k b(com.google.protobuf.micro.b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 10:
                        a(bVar.g());
                        continue;
                    case 18:
                        b(bVar.g());
                        continue;
                    case 24:
                        a(bVar.c());
                        continue;
                    case 32:
                        b(bVar.c());
                        continue;
                    case 40:
                        a(bVar.f());
                        continue;
                    case 48:
                        a(bVar.e());
                        continue;
                    default:
                        if (!a(bVar, a)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public k b(String str) {
            this.c = true;
            this.d = str;
            return this;
        }

        public String d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public String f() {
            return this.d;
        }

        public boolean g() {
            return this.c;
        }

        public long h() {
            return this.f;
        }

        public boolean i() {
            return this.e;
        }

        public long j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public boolean l() {
            return this.j;
        }

        public boolean m() {
            return this.i;
        }

        public int n() {
            return this.l;
        }

        public boolean o() {
            return this.k;
        }
    }
}
