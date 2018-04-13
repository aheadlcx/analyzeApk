package org.a.a.a;

public class c {
    private d a;
    private a b;
    private c c;
    private boolean d;

    public c(d dVar, boolean z) {
        this.a = dVar;
        if (z) {
            this.b = new a(dVar.f());
        } else {
            this.b = new a();
        }
        this.c = null;
        this.d = false;
    }

    public a a() {
        return this.b;
    }

    public c b() {
        return this.c;
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    public String c() {
        return this.a.a();
    }

    public String d() {
        return this.a.b();
    }

    public String e() {
        return this.a.c();
    }

    public int f() {
        return this.a.d();
    }

    public int g() {
        return this.a.e();
    }

    public d h() {
        return this.a.g();
    }

    public boolean b(c cVar) {
        return this.a.a(cVar.a);
    }

    public void a(String str, String str2, String str3) {
        this.a.a(this.b, str, str2, str3);
    }

    public void i() {
        int length = this.b.getLength() - 1;
        while (length >= 0) {
            if (this.b.getType(length).equals("ID") || this.b.getQName(length).equals("name")) {
                this.b.a(length);
            }
            length--;
        }
    }

    public void j() {
        for (int length = this.b.getLength() - 1; length >= 0; length--) {
            String localName = this.b.getLocalName(length);
            if (this.b.getValue(length) == null || localName == null || localName.length() == 0) {
                this.b.a(length);
            }
        }
    }

    public void k() {
        this.d = true;
    }

    public boolean l() {
        return this.d;
    }
}
