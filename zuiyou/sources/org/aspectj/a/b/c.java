package org.aspectj.a.b;

import org.aspectj.lang.a.d;
import org.aspectj.lang.b;

class c implements b {
    Object a;
    Object b;
    Object[] c;
    org.aspectj.lang.a.a d;
    private org.aspectj.a.a.a e;

    static class a implements org.aspectj.lang.a.a {
        String a;
        org.aspectj.lang.c b;
        d c;
        private int d;

        public a(int i, String str, org.aspectj.lang.c cVar, d dVar) {
            this.a = str;
            this.b = cVar;
            this.c = dVar;
            this.d = i;
        }

        public String b() {
            return this.a;
        }

        public org.aspectj.lang.c c() {
            return this.b;
        }

        public d a() {
            return this.c;
        }

        String a(h hVar) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(hVar.a(b()));
            stringBuffer.append("(");
            stringBuffer.append(((f) c()).b(hVar));
            stringBuffer.append(")");
            return stringBuffer.toString();
        }

        public final String toString() {
            return a(h.k);
        }
    }

    public c(org.aspectj.lang.a.a aVar, Object obj, Object obj2, Object[] objArr) {
        this.d = aVar;
        this.a = obj;
        this.b = obj2;
        this.c = objArr;
    }

    public Object a() {
        return this.b;
    }

    public d b() {
        return this.d.a();
    }

    public final String toString() {
        return this.d.toString();
    }

    public void a(org.aspectj.a.a.a aVar) {
        this.e = aVar;
    }

    public Object c() throws Throwable {
        if (this.e == null) {
            return null;
        }
        return this.e.run(this.e.getState());
    }
}
