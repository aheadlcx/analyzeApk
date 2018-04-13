package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.b;
import com.google.protobuf.micro.c;
import com.google.protobuf.micro.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class a {

    public static final class a extends e {
        private boolean a;
        private int b = 0;
        private boolean c;
        private boolean d = false;
        private boolean e;
        private int f = 0;
        private boolean g;
        private boolean h = false;
        private List<String> i = Collections.emptyList();
        private int j = -1;

        public static a b(byte[] bArr) {
            return (a) new a().a(bArr);
        }

        public static a c(b bVar) {
            return new a().b(bVar);
        }

        public int a() {
            if (this.j < 0) {
                b();
            }
            return this.j;
        }

        public /* synthetic */ e a(b bVar) {
            return b(bVar);
        }

        public a a(int i) {
            this.a = true;
            this.b = i;
            return this;
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (this.i.isEmpty()) {
                this.i = new ArrayList();
            }
            this.i.add(str);
            return this;
        }

        public a a(boolean z) {
            this.c = true;
            this.d = z;
            return this;
        }

        public void a(c cVar) {
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
            for (String a : l()) {
                cVar.a(5, a);
            }
        }

        public int b() {
            int i = 0;
            int d = e() ? c.d(1, d()) + 0 : 0;
            if (g()) {
                d += c.b(2, f());
            }
            if (i()) {
                d += c.c(3, h());
            }
            int b = k() ? d + c.b(4, j()) : d;
            for (String b2 : l()) {
                i += c.b(b2);
            }
            d = (b + i) + (l().size() * 1);
            this.j = d;
            return d;
        }

        public a b(int i) {
            this.e = true;
            this.f = i;
            return this;
        }

        public a b(b bVar) {
            while (true) {
                int a = bVar.a();
                switch (a) {
                    case 0:
                        break;
                    case 8:
                        a(bVar.i());
                        continue;
                    case 16:
                        a(bVar.f());
                        continue;
                    case 24:
                        b(bVar.e());
                        continue;
                    case 32:
                        b(bVar.f());
                        continue;
                    case 42:
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

        public a b(boolean z) {
            this.g = true;
            this.h = z;
            return this;
        }

        public int d() {
            return this.b;
        }

        public boolean e() {
            return this.a;
        }

        public boolean f() {
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

        public boolean j() {
            return this.h;
        }

        public boolean k() {
            return this.g;
        }

        public List<String> l() {
            return this.i;
        }

        public int m() {
            return this.i.size();
        }
    }
}
