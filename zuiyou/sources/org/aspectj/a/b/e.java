package org.aspectj.a.b;

import org.aspectj.lang.a.c;

class e extends a implements c {
    Class d;

    e(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        super(i, str, cls, clsArr, strArr, clsArr2);
        this.d = cls2;
    }

    public Class c() {
        if (this.d == null) {
            this.d = c(6);
        }
        return this.d;
    }

    protected String a(h hVar) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(hVar.a(d()));
        if (hVar.b) {
            stringBuffer.append(hVar.a(c()));
        }
        if (hVar.b) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(hVar.a(f(), g()));
        stringBuffer.append(".");
        stringBuffer.append(e());
        hVar.b(stringBuffer, a());
        hVar.c(stringBuffer, b());
        return stringBuffer.toString();
    }
}
